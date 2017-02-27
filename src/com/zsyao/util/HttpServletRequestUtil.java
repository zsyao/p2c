package com.zsyao.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil
{
	public static String getRequestPath(HttpServletRequest request)
	{
		if (request == null)
		{
			return "";
		}
		
		String url = request.getPathInfo();
		return url;
	}
	
	public static String getRequestURLWithParam2(HttpServletRequest request)
	{
		if (request == null)
		{
			return "";
		}
		String url = request.getRequestURL().toString().trim();
		
		if (request.getQueryString() != null && request.getQueryString().trim().length() > 0)
		{
			url = url + "?" + request.getQueryString();
		}
		return url;
	}
	
	public static String getRequestURLWithParam(HttpServletRequest request)
	{
		if (request == null)
		{
			return "";
		}
		
		String url = request.getRequestURL().toString().trim();

		Enumeration<String> names = request.getParameterNames();
		int i = 0;

		if (names != null)
		{
			while (names.hasMoreElements())
			{
				String name = names.nextElement();
				if (i == 0)
				{
					url = url + "?";
				}
				else
				{
					url = url + "&";
				}
				i++;

				String value = request.getParameter(name);
				if (value == null)
				{
					value = "";
				}
				url = url + name + "=" + value;
			}
		}
		return url;
	}

	public static String getRequestURL(HttpServletRequest request)
	{
		if (request == null)
		{
			return "";
		}
		
		String url = request.getRequestURL().toString().trim();
		return url;
	}
	
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("PRoxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static byte[] getRequestInput(HttpServletRequest request) throws Exception
	{
		InputStream is = null;
		byte[] byteMsg = new byte[1024];
		int pos = -1;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try
		{
			is = request.getInputStream();
			
			while ((pos = is.read(byteMsg)) != -1)
			{
				bo.write(byteMsg, 0, pos);
			}
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
				}
			}
		}
		
		return bo.toByteArray();
	}
}
