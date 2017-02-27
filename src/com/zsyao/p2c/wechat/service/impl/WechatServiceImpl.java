package com.zsyao.p2c.wechat.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zsyao.p2c.wechat.dao.IWMKeywordReplyDao;
import com.zsyao.p2c.wechat.dao.IWMUserInfoDao;
import com.zsyao.p2c.wechat.dao.IWMWechatConfigDao;
import com.zsyao.p2c.wechat.model.Message;
import com.zsyao.p2c.wechat.model.WMKeywordReply;
import com.zsyao.p2c.wechat.model.WMUserInfo;
import com.zsyao.p2c.wechat.model.WMWechatConfig;
import com.zsyao.p2c.wechat.model.WechatMessageTool;
import com.zsyao.p2c.wechat.model.common.TextMessage;
import com.zsyao.p2c.wechat.model.even.ScanEventMessage;
import com.zsyao.p2c.wechat.model.even.SubscribeEventMessage;
import com.zsyao.p2c.wechat.model.even.UnsubscribeEventMessage;
import com.zsyao.p2c.wechat.service.IWechatService;
import com.zsyao.util.MD5;
import com.zsyao.util.SHA1;
import com.zsyao.util.StringUtil;
import com.zsyao.util.WechatUtil;

@Service
public class WechatServiceImpl implements IWechatService
{
	@Resource
	private IWMWechatConfigDao configDao;
	
	@Resource
	private IWMKeywordReplyDao replyDao;
	
	@Resource
	private IWMUserInfoDao userDao;

	private static Logger logger = Logger.getLogger(WechatServiceImpl.class);

	@Override
	public boolean checkEchostr(int serialNo, String signature, String timestamp, String nonce) throws Exception
	{
		WMWechatConfig config = configDao.getWechatConfigBySerialNo(serialNo);
		if (config == null)
		{
			return false;
		}
		
		String[] tmpArray = new String[]{config.getToken(), timestamp, nonce};
		Arrays.sort(tmpArray);
        
		String encodingStr = "";
		for (String str : tmpArray)
		{
			encodingStr += str;
		}
		
		String encodedStr = SHA1.enCoding(encodingStr);
		if (encodedStr != null && encodedStr.equalsIgnoreCase(signature))
		{
			return true;
		}
		return false;
	}

	@Override
	public String handleMessage(int wechatId, String strMessage) throws Exception
	{
		logger.info("wechatId:" + wechatId);
		logger.info("strMessage:" + strMessage);
		
		WMWechatConfig config = configDao.getWechatConfigBySerialNo(wechatId);
		if (config == null)
		{
			return null;
		}
		
		Message message = WechatMessageTool.parserFromXML(strMessage);
		if (message == null)
		{
			return null;
		}
		
		if (message instanceof TextMessage)
		{
			//判断是否为关键字
			String content = ((TextMessage)message).getContent();
			return handleEvenKey(wechatId, content, null, message.getToUserName(), message.getFromUserName());
		}
		else if (message instanceof ScanEventMessage)
		{
			//扫描事件处理
			String eventKey = ((ScanEventMessage) message).getEventKey();
			return handleEvenKey(wechatId, eventKey, config, message.getToUserName(), message.getFromUserName());
		}
		else if (message instanceof SubscribeEventMessage)
		{
			//关注事件处理
			String openId = message.getFromUserName();
			
			JSONObject jsonUser = WechatUtil.getUserInfo(getAccessToken(wechatId), openId);
			
			WMUserInfo userInfo = null;
			if (jsonUser.containsKey("openid"))
			{
				userInfo = WMUserInfo.fromJSONObject(jsonUser);
			}
			else
			{
				userInfo = new WMUserInfo();
				userInfo.setOpenId(openId);
				userInfo.setSubscribe("1");
				userInfo.setCustomerId(MD5.code(openId, 16));;
			}
			userInfo.setWechatId(wechatId);
			
			if (!userDao.updateToDb(userInfo))
			{
				userDao.insertToDb(userInfo);
			}
			
			String eventKey = ((SubscribeEventMessage) message).getEventKey();
			return handleEvenKey(wechatId, eventKey, config, message.getToUserName(), message.getFromUserName());
		}
		else if (message instanceof UnsubscribeEventMessage)
		{
			String openId = message.getFromUserName();
			WMUserInfo user = new WMUserInfo();
			user.setOpenId(openId);
			user.setSubscribe("0");
			userDao.updateToDb(user);
			return null;
		}
		return null;
	}


	private String handleEvenKey(int wechatId, String eventKey, WMWechatConfig config, String fromUserName, String toUserName) throws Exception
	{
		if (StringUtil.stringIsEmptyStr(eventKey))
		{
			return null;
		}
		
		WMKeywordReply reply = replyDao.getReplyByKeyword(wechatId, eventKey);
		if (reply != null)
		{
			String msg = reply.getReplyMessage();
			
			if (StringUtil.stringIsEmptyStr(msg))
			{
				if (config != null)
				{
					msg = config.getSubscribeMessage();
				}
			}
			
			if (StringUtil.stringIsEmptyStr(msg))
			{
				return null;
			}
			
			TextMessage replyMsg = new TextMessage();
			replyMsg.setFromUserName(fromUserName);
			replyMsg.setToUserName(toUserName);
			replyMsg.setCreateTime("" + (System.currentTimeMillis() / 1000));
			replyMsg.setContent(msg);
			return replyMsg.toSendXML();
		}
		return null;
	}

	@Override
	public String getAccessToken(int wechatId) throws Exception
	{
        return getWechatConfig(wechatId, true).getAccessToken();
	}

	@Override
	public String getJsapiTicket(int wechatId) throws Exception
	{
		return getWechatConfig(wechatId, true).getJsapiTicket();
	}


    private WMWechatConfig getWechatConfig(int wechatId, boolean isNeedUpdate) throws Exception
    {
        WMWechatConfig wechatConfig = configDao.getWechatConfigBySerialNo(wechatId);
        if (isNeedUpdate)
        {
        	updateWechatConfig(wechatId, wechatConfig);
        }
        return wechatConfig;
    }

    private void updateWechatConfig(int wechatId, WMWechatConfig config) throws Exception
    {
        boolean isUpdateConfig = false;
        if (StringUtil.stringIsEmptyStr(config.getAccessToken()) ||  config.getAccessTokenExpiresIn() <= System.currentTimeMillis() / 1000 - 1800)
        {
            String accessToken = WechatUtil.getAccessToken(config.getAppId(), config.getAppSecret());
            config.setAccessToken(accessToken);
            config.setAccessTokenExpiresIn(System.currentTimeMillis() / 1000);
            isUpdateConfig = true;
        }

        if (StringUtil.stringIsEmptyStr(config.getJsapiTicket()) ||  config.getJsapiTicketExpiresIn() <= System.currentTimeMillis() / 1000 - 1800)
        {
            String jsapiTicket = WechatUtil.getJsapiTicket(config.getAccessToken());
            config.setJsapiTicket(jsapiTicket);
            config.setJsapiTicketExpiresIn(System.currentTimeMillis() / 1000);
            isUpdateConfig = true;
        }

        if (isUpdateConfig)
        {
            configDao.updateWechatConfig(config);
        }
    }
}
