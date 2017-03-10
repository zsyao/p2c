package com.zsyao.p2c.school.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.school.dao.ISCMClassesDao;
import com.zsyao.p2c.school.model.SCMClasses;
import com.zsyao.p2c.school.service.ISCMClassesService;

@Service
@Transactional
public class SCMClassesServiceImpl implements ISCMClassesService
{
	@Resource
	private ISCMClassesDao classesDao;

	@Override
	public List<SCMClasses> getClassesList(Integer schoolId) throws Exception
	{
		return classesDao.getClassesList(schoolId);
	}

}
