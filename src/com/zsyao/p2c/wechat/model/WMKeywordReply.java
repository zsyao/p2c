package com.zsyao.p2c.wechat.model;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class WMKeywordReply extends BaseEntity
{
	private Integer serialNo;
	private Integer wechatId;
	private String keyword;
	private String replyMessage;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public String getKeyword()
	{
		return keyword;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

	public String getReplyMessage()
	{
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage)
	{
		this.replyMessage = replyMessage;
	}

	public Integer getWechatId()
	{
		return wechatId;
	}
	
	public void setWechatId(Integer wechatId)
	{
		this.wechatId = wechatId;
	}
	
}
