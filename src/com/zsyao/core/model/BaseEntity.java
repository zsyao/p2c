package com.zsyao.core.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseEntity implements Serializable
{
	private int page;
	private int rows;
	private int skipResult;
	private int limitRows;
	
	private String orderProperty;
	
	/**
	 * ASC
	 * DESC
	 */
	private String orderType;

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getSkipResult()
	{
		return skipResult;
	}

	public void setSkipResult(int skipResult)
	{
		this.skipResult = skipResult;
	}

	public int getLimitRows()
	{
		return limitRows;
	}

	public void setLimitRows(int limitRows)
	{
		this.limitRows = limitRows;
	}

	
	public String getOrderProperty()
	{
		return orderProperty;
	}

	
	public String getOrderType()
	{
		return orderType;
	}

	
	public void setOrderProperty(String orderProperty)
	{
		this.orderProperty = orderProperty;
	}

	
	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}

}
