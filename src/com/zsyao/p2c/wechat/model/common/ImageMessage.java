package com.zsyao.p2c.wechat.model.common;


public class ImageMessage extends AbsBaseMessage
{
	private String picUrl;
	private String mediaId;
	
	public ImageMessage()
	{
		super(MESSAGE_TYPE_IMAGE);
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	@Override
	public String toXML()
	{
		return null;
	}

	@Override
	public String toSendXML()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
