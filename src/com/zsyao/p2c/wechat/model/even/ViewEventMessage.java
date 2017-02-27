package com.zsyao.p2c.wechat.model.even;

public class ViewEventMessage extends AbsEventMessage
{
	public ViewEventMessage()
	{
		super(EVENT_TYPE_OF_VIEW);
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
