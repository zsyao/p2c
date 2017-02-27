package com.zsyao.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class InputStreamUtil
{
	public static byte[] inputStreamToBytes(InputStream input) throws Exception
	{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		int pos = -1;
		byte[] tmp = new byte[1024];
		
		while ((pos = input.read(tmp)) != -1)
		{
			bo.write(tmp, 0, pos);
		}
		return bo.toByteArray();
	}
}
