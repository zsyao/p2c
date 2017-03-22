package com.zsyao.p2c.base.model;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class SMParameter extends BaseEntity
{
	private Integer serialNo;
	private String parameterName;
	private String displayName;
	private String parameterValue;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public String getParameterName()
	{
		return parameterName;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public String getParameterValue()
	{
		return parameterValue;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}
	
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
	
	public void setParameterValue(String parameterValue)
	{
		this.parameterValue = parameterValue;
	}
}
