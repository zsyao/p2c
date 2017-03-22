package com.zsyao.p2c.base.serivce.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.base.dao.ISMParameterDao;
import com.zsyao.p2c.base.model.SMParameter;
import com.zsyao.p2c.base.serivce.ISMParameterService;

@Service
@Transactional
public class SMParameterServiceImpl implements ISMParameterService
{
	@Resource
	private ISMParameterDao parameterDao;

	@Override
	public List<SMParameter> getParameterList(String parameterName) throws Exception
	{
		return parameterDao.getParameterList(parameterName);
	}

	@Override
	public SMParameter getParameter(String parameterName, String parameterValue) throws Exception
	{
		return parameterDao.getParameter(parameterName, parameterValue);
	}

}
