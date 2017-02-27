package com.zsyao.p2c.wechat.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.wechat.dao.IWMUserInfoDao;
import com.zsyao.p2c.wechat.model.WMUserInfo;

@Repository
public class WMUserInfoDaoImpl implements IWMUserInfoDao
{
	@Resource
	private IDao dao;

	@Override
	public WMUserInfo getUserByOpenId(String openId) throws Exception
	{
		WMUserInfo user = new WMUserInfo();
		user.setOpenId(openId);
		return (WMUserInfo)dao.selectObject("WMUserInfo.select", user);
	}

	@Override
	public boolean updateToDb(WMUserInfo user) throws Exception
	{
		return dao.update("WMUserInfo.updateToDb", user) == 1;
	}

	@Override
	public boolean insertToDb(WMUserInfo user) throws Exception
	{
		return dao.insert("WMUserInfo.insertToDb", user) == 1;
	}

}
