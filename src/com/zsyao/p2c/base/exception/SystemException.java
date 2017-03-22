package com.zsyao.p2c.base.exception;


@SuppressWarnings("serial")
public class SystemException extends RuntimeException
{
	private int errorCode;
	
	public SystemException(int errorCode)
	{
		this.setErrorCode(errorCode);
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
}
