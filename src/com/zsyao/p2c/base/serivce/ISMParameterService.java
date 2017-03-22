package com.zsyao.p2c.base.serivce;

import java.util.List;

import com.zsyao.p2c.base.model.SMParameter;


public interface ISMParameterService
{
	List<SMParameter> getParameterList(String parameterName) throws Exception;
	
	SMParameter getParameter(String parameterName, String parameterValue) throws Exception;
}
