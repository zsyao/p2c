package com.zsyao.p2c.wechat.model.even;

public class ClickEventMessage extends AbsEventMessage
{
	public ClickEventMessage()
	{
		super(EVENT_TYPE_OF_CLICK);
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
