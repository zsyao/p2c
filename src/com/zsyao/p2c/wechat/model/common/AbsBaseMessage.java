package com.zsyao.p2c.wechat.model.common;

import com.zsyao.p2c.wechat.model.Message;

public abstract class AbsBaseMessage extends Message
{
	private String msgId;
	
	public AbsBaseMessage(String msgType)
	{
		super(msgType);
	}

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}
}
