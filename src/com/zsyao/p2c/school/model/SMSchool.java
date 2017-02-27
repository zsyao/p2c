package com.zsyao.p2c.school.model;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class SMSchool extends BaseEntity
{
	private Integer serialNo;
	private Integer wechatId;
	private String name;
	private String contacts;
	private String contactPhone;
	private Integer status;
	
	public static final int STATUS_OF_CHECKING = 0;
	public static final int STATUS_OF_ENABLE = 1;
	public static final int STATUS_OF_DISABLE = 2;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public Integer getWechatId()
	{
		return wechatId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getContacts()
	{
		return contacts;
	}
	
	public String getContactPhone()
	{
		return contactPhone;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setWechatId(Integer wechatId)
	{
		this.wechatId = wechatId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setContacts(String contacts)
	{
		this.contacts = contacts;
	}
	
	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}
	
	public void setStatus(Integer status)
	{
		this.status = status;
	}
}
