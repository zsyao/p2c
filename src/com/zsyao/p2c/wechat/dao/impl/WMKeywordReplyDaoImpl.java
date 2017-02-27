package com.zsyao.p2c.wechat.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.wechat.dao.IWMKeywordReplyDao;
import com.zsyao.p2c.wechat.model.WMKeywordReply;

@Repository
public class WMKeywordReplyDaoImpl implements IWMKeywordReplyDao
{
	@Resource
	private IDao dao;

	@Override
	public WMKeywordReply getReplyByKeyword(int wechatId, String keyword) throws Exception
	{
		WMKeywordReply reply = new WMKeywordReply();
		reply.setWechatId(wechatId);
		reply.setKeyword(keyword);
		return (WMKeywordReply)dao.selectObject("WMKeywordReply.select", reply);
	}

}
