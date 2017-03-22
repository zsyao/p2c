package com.zsyao.p2c.school.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.zsyao.p2c.school.dao.ISCMStudentDao;
import com.zsyao.p2c.school.model.SCMStudent;
import com.zsyao.p2c.school.service.ISCMStudentService;

@Service
@Transactional
public class SCMStudentServiceImpl implements ISCMStudentService
{
	@Resource
	private ISCMStudentDao studentDao;

	@Override
	public SCMStudent getStudent(Integer studentId) throws Exception
	{
		return studentDao.getStudent(studentId);
	}

}
