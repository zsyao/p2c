package com.zsyao.p2c.wechat.model.common;


public class VideoMessage extends AbsBaseMessage
{
	private String mediaId;
	private String thumbMediaId;
	
	public VideoMessage()
	{
		super(MESSAGE_TYPE_VIDEO);
	}

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	public String getThumbMediaId()
	{
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId)
	{
		this.thumbMediaId = thumbMediaId;
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
