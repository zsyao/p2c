package com.zsyao.p2c.school.dao;

import java.util.List;

import com.zsyao.p2c.school.model.SCMSchool;


public interface ISCMSchoolDao
{
	List<SCMSchool> getSchoolList() throws Exception;
	
	SCMSchool getSchoolById(Integer serialNo) throws Exception;
	
	List<SCMSchool> getSchoolListByName(String name) throws Exception;
}
