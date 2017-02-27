package com.zsyao.p2c.wechat.dao;

import com.zsyao.p2c.wechat.model.WMUserInfo;


public interface IWMUserInfoDao
{
	WMUserInfo getUserByOpenId(String openId) throws Exception;
	
	boolean updateToDb(WMUserInfo user) throws Exception;
	
	boolean insertToDb(WMUserInfo user) throws Exception;
}
