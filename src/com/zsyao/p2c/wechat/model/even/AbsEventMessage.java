package com.zsyao.p2c.wechat.model.even;

import com.zsyao.p2c.wechat.model.Message;

public abstract class AbsEventMessage extends Message
{
	private String event;
	
	public static final String EVENT_TYPE_OF_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_OF_UNSUBSCRIBE = "unsubscribe";
	public static final String EVENT_TYPE_OF_SCAN = "SCAN";
	public static final String EVENT_TYPE_OF_LOCATION = "LOCATION";
	public static final String EVENT_TYPE_OF_CLICK = "CLICK";
	public static final String EVENT_TYPE_OF_VIEW = "VIEW";

	public AbsEventMessage(String event)
	{
		super(MESSAGE_TYPE_EVENT);
		setEvent(event);
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}
}
