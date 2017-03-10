package com.zsyao.p2c.school.model;

import java.util.List;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class SCMSchool extends BaseEntity
{
	private Integer serialNo;
	private String name;
	private String headImage;
	private String address;
	private Integer wechatConfigId;
	private String openId;
	private String createTime;
	private String longitude;
	private String latitude;
	private Integer status;
	private List<SCMClasses> classesList;
	
	public static final int STATUS_OF_CHECKING = 0;
	public static final int STATUS_OF_ENABLE = 1;
	public static final int STATUS_OF_DISABLE = 2;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getWechatConfigId()
	{
		return wechatConfigId;
	}
	
	public String getOpenId()
	{
		return openId;
	}
	
	public String getCreateTime()
	{
		return createTime;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setWechatConfigId(Integer wechatConfigId)
	{
		this.wechatConfigId = wechatConfigId;
	}
	
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<SCMClasses> getClassesList()
	{
		return classesList;
	}

	public void setClassesList(List<SCMClasses> classesList)
	{
		this.classesList = classesList;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getHeadImage()
	{
		return headImage;
	}

	public void setHeadImage(String headImage)
	{
		this.headImage = headImage;
	}	
}
