package com.zsyao.p2c.school.model;

import com.zsyao.core.model.BaseEntity;


@SuppressWarnings("serial")
public class SCMRecipes extends BaseEntity
{
	private Integer serialNo;
	private Integer classesId;
	private String useDate;
	private String startTime;
	private String endTime;
	private String name;
	private String content;
	private String thumbnail;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public Integer getClassesId()
	{
		return classesId;
	}
	
	public String getUseDate()
	{
		return useDate;
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
	
	public String getContent()
	{
		return content;
	}
	
	public String getThumbnail()
	{
		return thumbnail;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setClassesId(Integer classesId)
	{
		this.classesId = classesId;
	}
	
	public void setUseDate(String useDate)
	{
		this.useDate = useDate;
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
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}
}
