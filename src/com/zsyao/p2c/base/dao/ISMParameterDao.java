package com.zsyao.p2c.base.dao;

import java.util.List;

import com.zsyao.p2c.base.model.SMParameter;


public interface ISMParameterDao
{
	List<SMParameter> getParameterList(String parameterName) throws Exception;
	
	SMParameter getParameter(String parameterName, String parameterValue) throws Exception;
}
