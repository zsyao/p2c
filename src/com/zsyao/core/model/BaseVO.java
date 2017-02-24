package com.zsyao.core.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseVO implements Serializable
{
	public static final int RESULT_OF_SUCCESS = 1;
	public static final int RESULT_OF_FAILED = -1;
	
	private int executeResult;
	private String reason;
	private Object resultObject;

	public int getExecuteResult()
	{
		return executeResult;
	}

	public void setExecuteResult(int executeResult)
	{
		this.executeResult = executeResult;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public Object getResultObject()
	{
		return resultObject;
	}

	public void setResultObject(Object resultObject)
	{
		this.resultObject = resultObject;
	}
}
