package com.zsyao.p2c.base.serivce;


public interface ISMExceptionService
{
	public boolean addSMException(String openId, Exception e, String className, String methodName);
}
