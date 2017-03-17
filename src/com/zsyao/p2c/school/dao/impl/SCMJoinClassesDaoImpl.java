package com.zsyao.p2c.school.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.school.dao.ISCMJoinClassesDao;
import com.zsyao.p2c.school.model.SCMJoinClasses;

@Repository
public class SCMJoinClassesDaoImpl implements ISCMJoinClassesDao
{
	@Resource
	private IDao dao;

	@Override
	public boolean insertToDb(SCMJoinClasses joinClasses) throws Exception
	{
		return dao.insert("SCMJoinClasses.insert", joinClasses) == 1;
	}

}
