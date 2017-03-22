package com.zsyao.p2c.school.service;

import java.util.List;

import com.zsyao.p2c.school.model.SCMClasses;


public interface ISCMClassesService
{
	List<SCMClasses> getClassesList(Integer schoolId) throws Exception;
	
	SCMClasses getClasses(Integer classesId) throws Exception;
	
	boolean joinClasses(Integer schoolId, Integer classesId, String openId, String relation, String mobile) throws Exception;
}
