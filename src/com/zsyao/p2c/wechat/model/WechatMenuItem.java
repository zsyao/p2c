package com.zsyao.p2c.wechat.model;


public class WechatMenuItem
{
	private String name;
	private String type;
	private String key;
	private WechatMenuItem[] subButton;
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public WechatMenuItem[] getSubButton()
	{
		return subButton;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public void setSubButton(WechatMenuItem[] subButton)
	{
		this.subButton = subButton;
	}
}
