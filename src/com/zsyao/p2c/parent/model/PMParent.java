package com.zsyao.p2c.parent.model;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class PMParent extends BaseEntity
{
	private String openId;
	private Integer studentId;
	private String relation;
	
	public String getOpenId()
	{
		return openId;
	}
	
	public Integer getStudentId()
	{
		return studentId;
	}
	
	public String getRelation()
	{
		return relation;
	}
	
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	
	public void setStudentId(Integer studentId)
	{
		this.studentId = studentId;
	}
	
	public void setRelation(String relation)
	{
		this.relation = relation;
	}
	
}
