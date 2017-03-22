package com.zsyao.p2c.school.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.school.dao.ISCMSchoolDao;
import com.zsyao.p2c.school.model.SCMSchool;
import com.zsyao.p2c.school.service.ISCMSchoolService;

@Service
@Transactional
public class SCMSchoolServiceImpl implements ISCMSchoolService
{
	@Resource
	private ISCMSchoolDao schoolDao;

	@Override
	public List<SCMSchool> getSchoolListByName(String name) throws Exception
	{
		return schoolDao.getSchoolListByName(name);
	}

	@Override
	public List<SCMSchool> getSchoolList() throws Exception
	{
		return schoolDao.getSchoolList();
	}

	@Override
	public SCMSchool getSchoolById(Integer schoolId) throws Exception
	{
		return schoolDao.getSchoolById(schoolId);
	}
	
}
