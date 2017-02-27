package com.zsyao.p2c.wechat.model;

import com.zsyao.core.model.BaseEntity;

@SuppressWarnings("serial")
public class WMWechatConfig extends BaseEntity
{
	private Integer serialNo;
	private String appId;
	private String appSecret;
	private String token;
	private String accessToken;
	private Long accessTokenExpiresIn;
	private String jsapiTicket;
	private Long jsapiTicketExpiresIn;
	private String subscribeMessage;
	private Integer status;
	
	public static final int STATUS_OF_ENABLE = 1;
	public static final int STATUS_OF_DISABLE = 2;
	
	public Integer getSerialNo()
	{
		return serialNo;
	}
	
	public String getAppId()
	{
		return appId;
	}
	
	public String getAppSecret()
	{
		return appSecret;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public String getAccessToken()
	{
		return accessToken;
	}
	
	public Long getAccessTokenExpiresIn()
	{
		return accessTokenExpiresIn;
	}
	
	public String getJsapiTicket()
	{
		return jsapiTicket;
	}
	
	public Long getJsapiTicketExpiresIn()
	{
		return jsapiTicketExpiresIn;
	}
	
	public String getSubscribeMessage()
	{
		return subscribeMessage;
	}
	
	public void setSerialNo(Integer serialNo)
	{
		this.serialNo = serialNo;
	}
	
	public void setAppId(String appId)
	{
		this.appId = appId;
	}
	
	public void setAppSecret(String appSecret)
	{
		this.appSecret = appSecret;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	
	public void setAccessTokenExpiresIn(Long accessTokenExpiresIn)
	{
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}
	
	public void setJsapiTicket(String jsapiTicket)
	{
		this.jsapiTicket = jsapiTicket;
	}
	
	public void setJsapiTicketExpiresIn(Long jsapiTicketExpiresIn)
	{
		this.jsapiTicketExpiresIn = jsapiTicketExpiresIn;
	}
	
	public void setSubscribeMessage(String subscribeMessage)
	{
		this.subscribeMessage = subscribeMessage;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
}
