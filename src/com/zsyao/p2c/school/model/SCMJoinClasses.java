package com.zsyao.p2c.school.model;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class SCMJoinClasses extends BaseEntity
{
	private Integer serialNo;
	private Integer classesId;
	private String openId;
	private String createTime;
	private String reason;
	private Integer status;
	private String checkOpenId;
	private String checkTime;
	
	public static final int STATUS_OF_UNCHECK = 1;
	public static final int STATUS_OF_PASS = 2;
	public static final int STATUS_OF_REJECT = 3;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public Integer getClassesId()
	{
		return classesId;
	}
	
	public String getOpenId()
	{
		return openId;
	}
	
	public String getCreateTime()
	{
		return createTime;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public String getCheckOpenId()
	{
		return checkOpenId;
	}
	
	public String getCheckTime()
	{
		return checkTime;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setClassesId(Integer classesId)
	{
		this.classesId = classesId;
	}
	
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
	public void setCheckOpenId(String checkOpenId)
	{
		this.checkOpenId = checkOpenId;
	}
	
	public void setCheckTime(String checkTime)
	{
		this.checkTime = checkTime;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
}
