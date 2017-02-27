package com.zsyao.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class MD5
{
	private final static char[] hexDigits =
	{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	private static String bytesToHex(byte[] bytes)
	{
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++)
		{
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	// 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
	public static String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
		}
		return null;
	}

	public static String md5(String input)
	{
		return code(input, 32);
	}

	public static String code(String input, int bit)
	{
		return code(input, "utf-8", bit);
	}
	
	public static String code(String input, String code, int bit)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
			if (bit == 16)
			{
				return bytesToHex(md.digest(input.getBytes(code))).substring(8, 24);
			}
			return bytesToHex(md.digest(input.getBytes(code)));
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String md5_3(String b) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
		byte[] a = md.digest(b.getBytes());
		a = md.digest(a);
		a = md.digest(a);

		return bytesToHex(a);
	}
}
