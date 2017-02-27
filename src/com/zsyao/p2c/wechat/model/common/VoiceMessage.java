package com.zsyao.p2c.wechat.model.common;


public class VoiceMessage extends AbsBaseMessage
{
	private String mediaId;
	private String format;

	public VoiceMessage()
	{
		super(MESSAGE_TYPE_VOICE);
	}
	
	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}
	
	@Override
	public String toXML()
	{
		String xml = "<xml>" 
			+ "<ToUserName><![CDATA[" + getToUserName() +"]]></ToUserName>"
			+ "<FromUserName><![CDATA[" + getFromUserName() +"]]></FromUserName>"
			+ "<CreateTime>" + getCreateTime() +"</CreateTime>"
			+ "<MsgType><![CDATA[" + getMsgType() + "]]></MsgType>"
			+ "<MediaId><![CDATA[" + getMediaId() + "]]></MediaId>"
			+ "<Format><![CDATA[" + getFormat() + "]]></Format>"
			+ "<MsgId>" + getMsgId() + "</MsgId>"
			+ "</xml>";
		return xml;
	}

	@Override
	public String toSendXML()
	{
		return null;
	}
}
