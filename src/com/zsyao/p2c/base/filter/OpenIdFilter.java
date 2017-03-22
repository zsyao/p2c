package com.zsyao.p2c.base.filter;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zsyao.p2c.school.model.SCMSchool;
import com.zsyao.p2c.school.service.ISCMSchoolService;
import com.zsyao.p2c.wechat.service.IWechatService;
import com.zsyao.util.HttpServletRequestUtil;
import com.zsyao.util.OpenIdUtil;
import com.zsyao.util.StringUtil;
import com.zsyao.util.WechatUtil;


public class OpenIdFilter implements HandlerInterceptor
{
	private static Logger logger = Logger.getLogger(OpenIdFilter.class);
	
	@Value("#{systemConfig.IS_DEBUG}")
	private boolean isDebug;
	
	@Value("#{systemConfig.DEBUG_USER}")
	private String debugUserOpenId;
	
	@Resource
	private IWechatService wechatService;
	
	@Resource
	private ISCMSchoolService schoolService;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object action, Exception arg3) throws Exception
	{
		logger.info("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object action, ModelAndView model) throws Exception
	{
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object action) throws Exception
	{
		logger.info("##############################################");
		logger.info("do OpenIdInterceptor");
		logger.info("##############################################");
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		
		if (HttpServletRequestUtil.getRequestURL(servletRequest).contains("/resource/"))
		{
			return true;
		}
		
		String openId = OpenIdUtil.getOpenId(servletRequest);
		if (isDebug && StringUtil.stringIsEmptyStr(openId))
		{
			openId = debugUserOpenId;
			OpenIdUtil.setOpenId(openId, servletRequest, servletResponse);
		}
		logger.info("OpenId:" + openId);
		//未找到openId
		String url = HttpServletRequestUtil.getRequestURLWithParam2(servletRequest);
		try
		{
			url = URLEncoder.encode(url, "UTF-8");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if (StringUtil.stringIsEmptyStr(openId))
		{
			String code = servletRequest.getParameter("code");
			if (StringUtil.stringIsEmptyStr(code))
			{
				int wechatId = getWechatConfigId(servletRequest);
				
				logger.info("需要刷新OpenId");
				//跳转
				String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechatService.getAppId(wechatId) + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
				logger.info("跳转至：" + redirectUrl);
				servletResponse.sendRedirect(redirectUrl);
				return false;
			}
			else
			{
				int wechatId = getWechatConfigId(servletRequest);
				openId = WechatUtil.getOpenId(wechatService.getAppId(wechatId), wechatService.getAppSecret(wechatId), code);
				logger.info("获取到OpenId:" + openId);
				if (!StringUtil.stringIsEmptyStr(openId))
				{
					OpenIdUtil.setOpenId(openId, servletRequest, servletResponse);
				}
				else
				{
					String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechatService.getAppId(wechatId) + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
					logger.info("跳转至：" + redirectUrl);
					servletResponse.sendRedirect(redirectUrl);
					return false;
				}
			}
		}
		logger.info("##############################################");
		logger.info("do OpenIdInterceptor End");
		logger.info("##############################################");
        return true;
	}
	
	private int getWechatConfigId(HttpServletRequest request) throws Exception
	{
		int defaultSchoolId = 0;
		String strSchoolId = request.getParameter("schoolId");
		int schoolId = defaultSchoolId;
		if (!StringUtil.stringIsEmptyStr(strSchoolId))
		{
			schoolId = Integer.parseInt(strSchoolId);
		}
		
		SCMSchool school = schoolService.getSchoolById(schoolId);
		if (school != null)
		{
			return school.getWechatConfigId();
		}
		return defaultSchoolId;
	}
}
