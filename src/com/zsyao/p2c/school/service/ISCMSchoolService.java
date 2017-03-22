package com.zsyao.p2c.school.service;

import java.util.List;

import com.zsyao.p2c.school.model.SCMSchool;


public interface ISCMSchoolService
{
	List<SCMSchool> getSchoolListByName(String name) throws Exception;

	List<SCMSchool> getSchoolList() throws Exception;
	
	SCMSchool getSchoolById(Integer schoolId) throws Exception;
}
