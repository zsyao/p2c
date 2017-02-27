package com.zsyao.p2c.wechat.model;


public class WechatMenuMatchRule
{
	private String groupId;
	private String sex;
	private String country;
	private String province;
	private String city;
	private String clientPlatformType;
	
	public String getGroupId()
	{
		return groupId;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getClientPlatformType()
	{
		return clientPlatformType;
	}
	
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setClientPlatformType(String clientPlatformType)
	{
		this.clientPlatformType = clientPlatformType;
	}
}
