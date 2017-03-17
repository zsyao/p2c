package com.zsyao.p2c.school.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.school.dao.ISCMClassesDao;
import com.zsyao.p2c.school.dao.ISCMJoinClassesDao;
import com.zsyao.p2c.school.model.SCMClasses;
import com.zsyao.p2c.school.model.SCMJoinClasses;
import com.zsyao.p2c.school.service.ISCMClassesService;
import com.zsyao.util.DateUtil;

@Service
@Transactional
public class SCMClassesServiceImpl implements ISCMClassesService
{
	@Resource
	private ISCMClassesDao classesDao;
	
	@Resource
	private ISCMJoinClassesDao joinClassesDao;

	@Override
	public List<SCMClasses> getClassesList(Integer schoolId) throws Exception
	{
		return classesDao.getClassesList(schoolId);
	}

	@Override
	public boolean join(Integer classesId, String openId, String reason) throws Exception
	{
		SCMJoinClasses joinClasses = new SCMJoinClasses();
		joinClasses.setClassesId(classesId);
		joinClasses.setOpenId(openId);
		joinClasses.setReason(reason);
		joinClasses.setCreateTime(DateUtil.getNowDateTime());
		joinClasses.setStatus(SCMJoinClasses.STATUS_OF_UNCHECK);
		return joinClassesDao.insertToDb(joinClasses);
	}

}
