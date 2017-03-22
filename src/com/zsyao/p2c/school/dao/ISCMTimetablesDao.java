package com.zsyao.p2c.school.dao;

import java.util.List;

import com.zsyao.p2c.school.model.SCMTimetables;


public interface ISCMTimetablesDao
{
	List<SCMTimetables> getTimetablesList(Integer classesId, String useDate) throws Exception;
}
