package com.zsyao.p2c.wechat.model.even;

public class LocationEventMessage extends AbsEventMessage
{
	private String latitude;//地理位置纬度
	private String longitude;//地理位置经度
	private String precision;//地理位置精度
	
	public LocationEventMessage()
	{
		super(EVENT_TYPE_OF_LOCATION);
	}
	
	@Override
	public String toXML()
	{
		return null;
	}

	@Override
	public String toSendXML()
	{
		return null;
	}

	
	public String getLatitude()
	{
		return latitude;
	}

	
	public String getLongitude()
	{
		return longitude;
	}

	
	public String getPrecision()
	{
		return precision;
	}

	
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	
	public void setPrecision(String precision)
	{
		this.precision = precision;
	}
}
