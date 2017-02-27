package com.zsyao.p2c.wechat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsyao.p2c.wechat.service.IWechatService;
import com.zsyao.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/wechat")
public class WechatMessageController
{
	private static Logger logger = Logger.getLogger(WechatMessageController.class);
	
	@Resource
	private IWechatService wechatService;
	
	@RequestMapping(value = {"/receiveMessage"}, method = RequestMethod.GET, produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String platAuthentication(int sourceId, String signature, String timestamp, String nonce, String echostr) throws Exception
	{
		logger.info("sourceId:" + sourceId);
		logger.info("signature:" + signature);
		logger.info("timestamp:" + timestamp);
		logger.info("nonce:" + nonce);
		logger.info("echostr:" + echostr);
		
		if (wechatService.checkEchostr(sourceId, signature, timestamp, nonce))
		{
			return echostr;
		}
		return "";
	}
	
	@RequestMapping(value = {"receiveMessage"}, method = RequestMethod.POST, produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String receiveMessage(int sourceId, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return wechatService.handleMessage(sourceId, new String(HttpServletRequestUtil.getRequestInput(request), "UTF-8"));
	}
}
