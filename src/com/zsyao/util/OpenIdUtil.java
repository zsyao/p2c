package com.zsyao.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsyao.p2c.Constants;

public class OpenIdUtil
{
	public static String getOpenId(HttpServletRequest request)
	{
		String openId = "";
		openId = (String)request.getSession().getAttribute(Constants.COOKIE_NAME_OF_OPEN_ID);
		if (StringUtil.stringIsEmptyStr(openId))
		{
			openId = CookieUtil.getCookie(request, Constants.COOKIE_NAME_OF_OPEN_ID);
		}
		return openId;
	}
	
	public static void setOpenId(String openId, HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().setAttribute(Constants.COOKIE_NAME_OF_OPEN_ID, openId);
		CookieUtil.addCookie(response, Constants.COOKIE_NAME_OF_OPEN_ID, openId, 3600 * 24);
	}
}
