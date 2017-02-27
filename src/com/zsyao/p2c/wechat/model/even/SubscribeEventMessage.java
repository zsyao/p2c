package com.zsyao.p2c.wechat.model.even;

public class SubscribeEventMessage extends AbsEventMessage
{
	private String eventKey;
	
	public SubscribeEventMessage()
	{
		super(EVENT_TYPE_OF_SUBSCRIBE);
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

	public String getEventKey()
	{
		return eventKey;
	}

	public void setEventKey(String eventKey)
	{
		this.eventKey = eventKey;
	}
}
