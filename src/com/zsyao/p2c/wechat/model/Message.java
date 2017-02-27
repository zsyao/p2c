package com.zsyao.p2c.wechat.model;

public abstract class Message
{
	public static final String MESSAGE_TYPE_EVENT = "event";
	public static final String MESSAGE_TYPE_IMAGE = "image"; 
	public static final String MESSAGE_TYPE_NEWS = "news";
	public static final String MESSAGE_TYPE_LINK = "link"; 
	public static final String MESSAGE_TYPE_LOCATION = "location"; 
	public static final String MESSAGE_TYPE_SHORT_VIDEO = "shortvideo"; 
	public static final String MESSAGE_TYPE_TEXT = "text";  
	public static final String MESSAGE_TYPE_VIDEO = "video"; 
	public static final String MESSAGE_TYPE_VOICE = "voice"; 
	
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	
	public Message(String msgType)
	{
		setMsgType(msgType);
	}
	
	public String getToUserName()
	{
		return toUserName;
	}

	public void setToUserName(String toUserName)
	{
		this.toUserName = toUserName;
	}

	public String getFromUserName()
	{
		return fromUserName;
	}

	public void setFromUserName(String fromUserName)
	{
		this.fromUserName = fromUserName;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getMsgType()
	{
		return msgType;
	}

	public void setMsgType(String msgType)
	{
		this.msgType = msgType;
	}
	
	public abstract String toXML();
	
	public abstract String toSendXML();
}
