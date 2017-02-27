package com.zsyao.p2c.wechat.model.common;

public class LinkMessage extends AbsBaseMessage
{
	public LinkMessage()
	{
		super(MESSAGE_TYPE_LINK);
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
