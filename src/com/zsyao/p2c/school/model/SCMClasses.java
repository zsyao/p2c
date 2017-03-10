package com.zsyao.p2c.school.model;

import com.zsyao.core.model.BaseEntity;


@SuppressWarnings("serial")
public class SCMClasses extends BaseEntity
{
	private Integer serialNo;
	private String name;
	private Integer schoolId;
	private String createTime;
	private Integer status;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getSchoolId()
	{
		return schoolId;
	}
	
	public String getCreateTime()
	{
		return createTime;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setSchoolId(Integer schoolId)
	{
		this.schoolId = schoolId;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
}
