package com.zsyao.util;

import java.util.regex.Pattern;

public class StringUtil
{
	public static boolean stringIsEmptyStr(String str)
	{
		if (str == null || str.trim().length() < 1)
		{
			return true;
		}
		return false;
	}
	
	public static String nullStringConvert(String str)
	{
		if (str == null)
			return "";
		
		return str;
	}
	
	public static boolean equals(String str1, String str2)
	{
		if (str1 == null)
		{
			if (str2 == null)
			{
				return true;
			}
			return false;
		}
		
		return str1.equals(str2);
	}
	
	public static String fillFrontZero(String str, int length)
	{
		String zero = "";
		for (int i = 0; i < length; i++)
		{
			zero += "0";
		}
		
		str = zero + str;
		return str.substring(str.length() - length);
	}
	
	public static String html2Text(String inputString)
	{
		if (inputString == null)
		{
			return inputString;
		}
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try
		{
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		}
		catch (Exception e)
		{
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}
}
