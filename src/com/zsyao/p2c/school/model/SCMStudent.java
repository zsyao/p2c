package com.zsyao.p2c.school.model;

import com.zsyao.core.model.BaseEntity;


@SuppressWarnings("serial")
public class SCMStudent extends BaseEntity
{
	private Integer serialNo;
	private Integer classesId;
	private String name;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public Integer getClassesId()
	{
		return classesId;
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
