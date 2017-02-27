package com.zsyao.p2c.wechat.dao;

import com.zsyao.p2c.wechat.model.WMWechatConfig;


public interface IWMWechatConfigDao
{
	WMWechatConfig getWechatConfigByAppId(String appId) throws Exception;
	
	WMWechatConfig getWechatConfigBySerialNo(int serialNo) throws Exception;
	
	boolean updateWechatConfig(WMWechatConfig config) throws Exception;
}
