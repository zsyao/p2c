package com.zsyao.p2c.school.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.school.dao.ISCMClassesDao;
import com.zsyao.p2c.school.model.SCMClasses;


@Repository
public class SCMClassesDao implements ISCMClassesDao
{
	@Resource
	private IDao dao;

	@Override
	public List<SCMClasses> getClassesList(Integer schoolId) throws Exception
	{
		SCMClasses params = new SCMClasses();
		params.setSchoolId(schoolId);
		return getClassesList(params);
	}

	@Override
	public SCMClasses getClassesById(Integer serialNo) throws Exception
	{
		SCMClasses params = new SCMClasses();
		params.setSerialNo(serialNo);
		List<SCMClasses> classesList = getClassesList(params);
		if (classesList.size() > 0)
			return classesList.get(0);
		return null;
	}


	@SuppressWarnings("unchecked")
	private List<SCMClasses> getClassesList(SCMClasses params) throws Exception
	{
		return (List<SCMClasses>)dao.selectList("SCMClasses.getClassesList", params);
	}
}
