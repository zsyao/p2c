package com.zsyao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
	public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	
	public static String dateToString(Date date, String format)
	{
		SimpleDateFormat simpleDF = new SimpleDateFormat(format);
		return simpleDF.format(date);
	}
	
	public static Date stringToDate(String date, String format)
	{
		try
		{
			SimpleDateFormat simpleDF = new SimpleDateFormat(format);
			return simpleDF.parse(date);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static String getNowDate()
	{
		return dateToString(new Date(), FORMAT_DATE);
	}
	
	public static String getNowDateTime()
	{
		return dateToString(new Date(), FORMAT_TIMESTAMP);
	}
	
}
