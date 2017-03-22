package com.zsyao.p2c.base.serivce.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zsyao.p2c.base.dao.ISMExceptionDao;
import com.zsyao.p2c.base.serivce.ISMExceptionService;

@Service
public class SMExceptionServiceImpl implements ISMExceptionService
{
	@Resource
	private ISMExceptionDao exceptionDao;
	
	private static Logger logger = Logger.getLogger(SMExceptionServiceImpl.class);
	
	@Override
	public boolean addSMException(String openId, Exception e, String className, String methodName)
	{
		try
		{
			return exceptionDao.addSMException(openId, e.getMessage(), className, methodName);
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage(), ex);
		}
		return false;
	}

}
