package com.zsyao.p2c.base.dao.impl;

import java.net.URLDecoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.base.dao.ISMExceptionDao;
import com.zsyao.p2c.base.model.SMException;

@Repository
public class SMExceptionDaoImpl implements ISMExceptionDao
{
	@Resource
	private IDao dao;

	@Override
	public boolean addSMException(String openId, String exceptionName, String className, String methodName) throws Exception
	{
		SMException exception = new SMException();
		exception.setOpenId(openId);
		exception.setExceptionName(exceptionName);
		exception.setClassName(className);
		exception.setMethodName(methodName);
		exception.setHostAddress(URLDecoder.decode(SMExceptionDaoImpl.class.getResource("/").getPath(), "UTF-8"));
		return dao.insert("SMException.addSMException", exception) == 1;
	}
}
