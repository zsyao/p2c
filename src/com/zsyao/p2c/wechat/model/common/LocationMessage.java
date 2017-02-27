package com.zsyao.p2c.wechat.model.common;

public class LocationMessage extends AbsBaseMessage
{
	public LocationMessage()
	{
		super(MESSAGE_TYPE_LOCATION);
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
}
