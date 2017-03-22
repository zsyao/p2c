package com.zsyao.p2c.school.dao;

import java.util.List;

import com.zsyao.p2c.school.model.SCMStudent;


public interface ISCMStudentDao
{
	List<SCMStudent> selectStudent(Integer classesId, String bindMobile) throws Exception;
	
	SCMStudent getStudent(Integer studentId) throws Exception;
}
