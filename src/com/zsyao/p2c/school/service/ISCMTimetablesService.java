package com.zsyao.p2c.school.service;

import java.util.List;

import com.zsyao.p2c.school.model.SCMTimetables;


public interface ISCMTimetablesService
{
	List<SCMTimetables> getTimetablesList(Integer classesId, String useDate) throws Exception;
}
