package com.zsyao.p2c.school.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.school.dao.ISCMTimetablesDao;
import com.zsyao.p2c.school.model.SCMTimetables;
import com.zsyao.p2c.school.service.ISCMTimetablesService;

@Service
@Transactional
public class SCMTimetablesServiceImpl implements ISCMTimetablesService
{
	@Resource
	private ISCMTimetablesDao timetablesDao;

	@Override
	public List<SCMTimetables> getTimetablesList(Integer classesId, String useDate) throws Exception
	{
		return timetablesDao.getTimetablesList(classesId, useDate);
	}

}
