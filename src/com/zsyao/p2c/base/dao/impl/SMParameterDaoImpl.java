package com.zsyao.p2c.base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.base.dao.ISMParameterDao;
import com.zsyao.p2c.base.model.SMParameter;

@SuppressWarnings("unchecked")
@Repository
public class SMParameterDaoImpl implements ISMParameterDao
{
	@Resource
	private IDao dao;

	@Override
	public List<SMParameter> getParameterList(String parameterName) throws Exception
	{
		SMParameter parameter = new SMParameter();
		parameter.setParameterName(parameterName);
		return (List<SMParameter>)dao.selectList("SMParameter.select", parameter);
	}

	@Override
	public SMParameter getParameter(String parameterName, String parameterValue) throws Exception
	{
		SMParameter parameter = new SMParameter();
		parameter.setParameterName(parameterName);
		parameter.setParameterValue(parameterValue);
		List<SMParameter> parameterList = (List<SMParameter>)dao.selectList("SMParameter.select", parameter);
		
		if (parameterList.size() > 0)
		{
			return parameterList.get(0);
		}
		return null;
	}

}
