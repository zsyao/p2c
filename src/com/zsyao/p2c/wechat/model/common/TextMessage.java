package com.zsyao.p2c.wechat.model.common;


public class TextMessage extends AbsBaseMessage
{
	private String content;
	
	public TextMessage()
	{
		super(MESSAGE_TYPE_TEXT);
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toXML()
	{
		String xml = "<xml>"
			+ "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>"
			+ "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>"
			+ "<CreateTime>" + getCreateTime() + "</CreateTime>"
			+ "<MsgType><![CDATA[" + MESSAGE_TYPE_TEXT + "]]></MsgType>"
			+ "<Content><![CDATA[" + getContent() + "]]></Content>"
			+ "</xml>";
		return xml;
	}

	@Override
	public String toSendXML()
	{
		String xml = "<xml>"
			+ "<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>"
			+ "<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>"
			+ "<CreateTime>" + getCreateTime() + "</CreateTime>"
			+ "<MsgType><![CDATA[" + getMsgType() + "]]></MsgType>"
			+ "<Content><![CDATA[" + getContent() + "]]></Content>"
			+ "</xml>";
		return xml;
	}
}
