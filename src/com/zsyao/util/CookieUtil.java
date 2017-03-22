package com.zsyao.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CookieUtil
{
	private static Logger logger = Logger.getLogger(CookieUtil.class);
	
	public static void addCookie(HttpServletResponse response, String key, String value)
	{
		addCookie(response, key, value, 60 * 60 * 24 * 365 * 10);
	}
	
	public static void addCookie(HttpServletResponse response, String uri, String key, String value, int maxAge)
	{
		logger.info("addCookie:" + key + ":\t" + value);
		if (value != null)
		{
			try
			{
				Cookie cookieOpenId = new Cookie(key, URLEncoder.encode(value,"utf-8"));
				cookieOpenId.setMaxAge(maxAge);
				cookieOpenId.setPath(uri);
				response.addCookie(cookieOpenId);
			}
			catch(UnsupportedEncodingException e)
			{
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	
	public static void addCookie(HttpServletResponse response, String key, String value, int maxAge)
	{
		logger.info("addCookie:" + key + ":\t" + value);
		if (value != null)
		{
			try
			{
				Cookie cookieOpenId = new Cookie(key, URLEncoder.encode(value,"utf-8"));
				cookieOpenId.setPath("/");
				cookieOpenId.setMaxAge(maxAge);
				response.addCookie(cookieOpenId);
			}
			catch(UnsupportedEncodingException e)
			{
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key)
	{
		if (key == null)
		{
			return;
		}
		
		Cookie[] cookies = request.getCookies();  
		
		if (cookies == null)
		{
			return;
		}
		
		for (Cookie cookie : cookies)
		{
			if (key.equals(cookie.getName()))
			{
				cookie.setValue(null);
				response.addCookie(cookie);
				return;
			}
		}
	}
	
	public static String getCookie(HttpServletRequest request, String key)
	{
		if (key == null)
		{
			return null;
		}
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (key.equals(cookie.getName()))
				{
					try
					{
						return URLDecoder.decode(cookie.getValue(), "utf-8");
					}
					catch(UnsupportedEncodingException e)
					{
					}
				}
			}
		}
		return null;
	}
}
