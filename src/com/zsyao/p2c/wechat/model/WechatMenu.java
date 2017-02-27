package com.zsyao.p2c.wechat.model;


public class WechatMenu
{
	private String menuId;
	private int menuType;
	private WechatMenuItem[] menuItemList;
	private WechatMenuMatchRule matchRule;
	
	public static final int MENU_TYPE_OF_NORMAL = 1;
	public static final int MENU_TYPE_OF_CONDITIONAL = 2;
	
	public String getMenuId()
	{
		return menuId;
	}
	
	public WechatMenuItem[] getMenuItemList()
	{
		return menuItemList;
	}
	
	public WechatMenuMatchRule getMatchRule()
	{
		return matchRule;
	}
	
	public void setMenuId(String menuId)
	{
		this.menuId = menuId;
	}
	
	public void setMenuItemList(WechatMenuItem[] menuItemList)
	{
		this.menuItemList = menuItemList;
	}
	
	public void setMatchRule(WechatMenuMatchRule matchRule)
	{
		this.matchRule = matchRule;
	}

	public int getMenuType()
	{
		return menuType;
	}

	public void setMenuType(int menuType)
	{
		this.menuType = menuType;
	}
}
