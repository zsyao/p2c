package com.zsyao.p2c.wechat.dao;

import com.zsyao.p2c.wechat.model.WMKeywordReply;


public interface IWMKeywordReplyDao
{
	public WMKeywordReply getReplyByKeyword(int wechatId, String keyword) throws Exception;
}
