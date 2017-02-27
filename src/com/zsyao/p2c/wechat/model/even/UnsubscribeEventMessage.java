package com.zsyao.p2c.wechat.model.even;

public class UnsubscribeEventMessage extends AbsEventMessage
{

	public UnsubscribeEventMessage()
	{
		super(EVENT_TYPE_OF_UNSUBSCRIBE);
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
