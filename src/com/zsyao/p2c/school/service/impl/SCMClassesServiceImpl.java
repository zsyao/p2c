package com.zsyao.p2c.school.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.Constants;
import com.zsyao.p2c.base.exception.SystemException;
import com.zsyao.p2c.parent.dao.IPMParentDao;
import com.zsyao.p2c.parent.model.PMParent;
import com.zsyao.p2c.school.dao.ISCMClassesDao;
import com.zsyao.p2c.school.dao.ISCMStudentDao;
import com.zsyao.p2c.school.model.SCMClasses;
import com.zsyao.p2c.school.model.SCMStudent;
import com.zsyao.p2c.school.service.ISCMClassesService;

@Service
@Transactional
public class SCMClassesServiceImpl implements ISCMClassesService
{
	@Resource
	private ISCMClassesDao classesDao;
	
	@Resource
	private ISCMStudentDao studentDao;
	
	@Resource
	private IPMParentDao parentDao;
	
	@Override
	public List<SCMClasses> getClassesList(Integer schoolId) throws Exception
	{
		return classesDao.getClassesList(schoolId);
	}
	
	@Override
	public SCMClasses getClasses(Integer classesId) throws Exception
	{
		return classesDao.getClassesById(classesId);
	}

	@Override
	public boolean joinClasses(Integer schoolId, Integer classesId, String openId, String relation, String mobile) throws Exception
	{
		List<SCMStudent> studentList = studentDao.selectStudent(classesId, mobile);
		
		if (studentList.size() == 0)
		{
			throw new SystemException(Constants.ERROR_CODE_OF_NOT_EXISTS_STUDENT);
		}
		
		if (studentList.size() != 1)
		{
			throw new SystemException(Constants.ERROR_CODE_OF_NOT_SURE_STUDENT);
		}
		
		PMParent parent = new PMParent();
		parent.setOpenId(openId);
		parent.setStudentId(studentList.get(0).getSerialNo());
		parent.setSchoolId(schoolId);
		parent.setClassesId(classesId);
		parent.setRelation(relation);
		if (!parentDao.insert(parent))
		{
			throw new SystemException(Constants.ERROR_CODE_OF_SAVE_PARENT_FAIL);
		}
		return true;
	}

}
