package com.zsyao.p2c.wechat.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.wechat.dao.IWMWechatConfigDao;
import com.zsyao.p2c.wechat.model.WMWechatConfig;

@Repository
public class WMWechatConfigDaoImpl implements IWMWechatConfigDao
{
	@Resource
	private IDao dao;

	@Override
	public WMWechatConfig getWechatConfigByAppId(String appId) throws Exception
	{
		WMWechatConfig config = new WMWechatConfig();
		config.setAppId(appId);
		config.setStatus(WMWechatConfig.STATUS_OF_ENABLE);
		return (WMWechatConfig)dao.selectObject("WMWechatConfig.select", config);
	}

	@Override
	public WMWechatConfig getWechatConfigBySerialNo(int serialNo) throws Exception
	{
		WMWechatConfig config = new WMWechatConfig();
		config.setSerialNo(serialNo);
		config.setStatus(WMWechatConfig.STATUS_OF_ENABLE);
		return (WMWechatConfig)dao.selectObject("WMWechatConfig.select", config);
	}

	@Override
	public boolean updateWechatConfig(WMWechatConfig config) throws Exception
	{
		return dao.update("WMWechatConfig.updateToDb", config) == 1;
	}

}
