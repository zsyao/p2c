package com.zsyao.p2c.school.dao;

import java.util.List;

import com.zsyao.p2c.school.model.SCMClasses;


public interface ISCMClassesDao
{
	List<SCMClasses> getClassesList(Integer schoolId) throws Exception;
	
	SCMClasses getClassesById(Integer serialNo) throws Exception;
}
