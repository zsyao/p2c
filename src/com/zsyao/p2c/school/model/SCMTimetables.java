package com.zsyao.p2c.school.model;

import com.zsyao.core.model.BaseEntity;


@SuppressWarnings("serial")
public class SCMTimetables extends BaseEntity
{
	private Integer serialNo;
	private Integer classesId;
	private String classesDate;
	private String startTime;
	private String endTime;
	private String name;
	private String content;
	
	private String queryStartDate;
	private String queryEndDate;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public Integer getClassesId()
	{
		return classesId;
	}
	
	public String getClassesDate()
	{
		return classesDate;
	}
	
	public String getStartTime()
	{
		return startTime;
	}
	
	public String getEndTime()
	{
		return endTime;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setClassesId(Integer classesId)
	{
		this.classesId = classesId;
	}
	
	public void setClassesDate(String classesDate)
	{
		this.classesDate = classesDate;
	}
	
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	
	public String getQueryStartDate()
	{
		return queryStartDate;
	}

	
	public String getQueryEndDate()
	{
		return queryEndDate;
	}

	
	public void setQueryStartDate(String queryStartDate)
	{
		this.queryStartDate = queryStartDate;
	}

	
	public void setQueryEndDate(String queryEndDate)
	{
		this.queryEndDate = queryEndDate;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

}
