package com.zsyao.p2c.base.dao;


public interface ISMExceptionDao
{
	public boolean addSMException(String openId, String exceptionName, String className, String methodName) throws Exception;
}
