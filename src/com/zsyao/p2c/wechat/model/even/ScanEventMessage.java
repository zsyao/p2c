package com.zsyao.p2c.wechat.model.even;

public class ScanEventMessage extends AbsEventMessage
{
	private String eventKey;
	
	public ScanEventMessage() 
	{
		super(EVENT_TYPE_OF_SCAN);
	}

	public String getEventKey()
	{
		return eventKey;
	}

	public void setEventKey(String eventKey)
	{
		this.eventKey = eventKey;
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
