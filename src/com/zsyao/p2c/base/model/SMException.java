package com.zsyao.p2c.base.model;

import com.zsyao.core.model.BaseEntity;


@SuppressWarnings("serial")
public class SMException extends BaseEntity
{
	private int serialNo;
	private String openId;
	private String exceptionName;
	private String className;
	private String methodName;
	private String hostAddress;
	private String createTime;
	
	public int getSerialNo()
	{
		return serialNo;
	}
	
	public void setSerialNo(int serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public String getOpenId()
	{
		return openId;
	}
	
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	
	public String getExceptionName()
	{
		return exceptionName;
	}
	
	public void setExceptionName(String exceptionName)
	{
		this.exceptionName = exceptionName;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public void setClassName(String className)
	{
		this.className = className;
	}
	
	public String getMethodName()
	{
		return methodName;
	}
	
	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}
	
	
	public String getHostAddress()
	{
		return hostAddress;
	}

	
	public void setHostAddress(String hostAddress)
	{
		this.hostAddress = hostAddress;
	}

	public String getCreateTime()
	{
		return createTime;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	
	
}
