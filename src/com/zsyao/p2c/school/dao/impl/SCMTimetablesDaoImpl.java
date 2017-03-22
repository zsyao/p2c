package com.zsyao.p2c.school.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.school.dao.ISCMTimetablesDao;
import com.zsyao.p2c.school.model.SCMTimetables;

@SuppressWarnings("unchecked")
@Repository
public class SCMTimetablesDaoImpl implements ISCMTimetablesDao
{
	@Resource
	private IDao dao;

	@Override
	public List<SCMTimetables> getTimetablesList(Integer classesId, String useDate) throws Exception
	{
		SCMTimetables parameter = new SCMTimetables();
		parameter.setClassesId(classesId);
		parameter.setClassesDate(useDate);
		return (List<SCMTimetables>)dao.selectList("SCMTimetables.select", parameter);
	}

}
