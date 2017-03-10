package com.zsyao.p2c.school.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.school.dao.ISCMSchoolDao;
import com.zsyao.p2c.school.model.SCMSchool;

@Repository
public class SCMSchoolDaoImpl implements ISCMSchoolDao
{
	@Resource
	private IDao dao;
	
	@Override
	public List<SCMSchool> getSchoolList() throws Exception
	{
		SCMSchool params = new SCMSchool();
		params.setStatus(SCMSchool.STATUS_OF_ENABLE);
		return getSchoolList(params);
	}

	@Override
	public SCMSchool getSchoolById(Integer serialNo) throws Exception
	{
		SCMSchool params = new SCMSchool();
		params.setSerialNo(serialNo);
		params.setStatus(SCMSchool.STATUS_OF_ENABLE);
		List<SCMSchool> schoolList = getSchoolList(params);
		
		if (schoolList.size() > 0)
		{
			return schoolList.get(0);
		}
		return null;
	}

	@Override
	public List<SCMSchool> getSchoolListByName(String name) throws Exception
	{
		SCMSchool params = new SCMSchool();
		params.setName(name);
		params.setStatus(SCMSchool.STATUS_OF_ENABLE);
		return getSchoolList(params);
	}

	@SuppressWarnings("unchecked")
	private List<SCMSchool> getSchoolList(SCMSchool params) throws Exception
	{
		return (List<SCMSchool>)dao.selectList("SCMSchool.getSchoolList", params);
	}
}
