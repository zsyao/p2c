package com.zsyao.p2c.school.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.school.dao.ISCMStudentDao;
import com.zsyao.p2c.school.model.SCMStudent;

@SuppressWarnings("unchecked")
@Repository
public class SCMStudentDao implements ISCMStudentDao
{
	@Resource
	private IDao dao;

	@Override
	public List<SCMStudent> selectStudent(Integer classesId, String bindMobile) throws Exception
	{
		SCMStudent parameter = new SCMStudent();
		parameter.setClassesId(classesId);
		parameter.setBindMobile(bindMobile);
		return (List<SCMStudent>)dao.selectList("SCMStudent.select", parameter);
	}

	@Override
	public SCMStudent getStudent(Integer studentId) throws Exception
	{
		SCMStudent parameter = new SCMStudent();
		parameter.setSerialNo(studentId);
		List<SCMStudent> studentList = (List<SCMStudent>)dao.selectList("SCMStudent.select", parameter);
		if (studentList.size() > 0)
		{
			return studentList.get(0);
		}
		return null;
	}

}
