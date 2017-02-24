package com.zsyao.core.service;

import javax.annotation.Resource;

import com.zsyao.core.dao.IDao;
import com.zsyao.core.model.BaseEntity;

public abstract class CommonService<T extends BaseEntity>
{
	@Resource
	private IDao dao;

	public IDao getDao()
	{
		return dao;
	}

	public void setDao(IDao dao)
	{
		this.dao = dao;
	}
}
