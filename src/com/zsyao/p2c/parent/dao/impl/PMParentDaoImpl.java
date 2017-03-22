package com.zsyao.p2c.parent.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.parent.dao.IPMParentDao;
import com.zsyao.p2c.parent.model.PMParent;

@SuppressWarnings("unchecked")
@Repository
public class PMParentDaoImpl implements IPMParentDao
{
	@Resource
	private IDao dao;

	@Override
	public List<PMParent> getParentList(String openId) throws Exception
	{
		PMParent parameter = new PMParent();
		parameter.setOpenId(openId);
		return (List<PMParent>)dao.selectList("PMParent.select", parameter);
	}

	@Override
	public List<PMParent> getParentList(String openId, Integer schoolId) throws Exception
	{
		PMParent parameter = new PMParent();
		parameter.setOpenId(openId);
		parameter.setSchoolId(schoolId);
		return (List<PMParent>)dao.selectList("PMParent.select", parameter);
	}
	
	@Override
	public boolean insert(PMParent parent) throws Exception
	{
		return dao.insert("PMParent.insert", parent) == 1;
	}

	@Override
	public PMParent getParent(String openId, Integer studentId) throws Exception
	{
		PMParent parameter = new PMParent();
		parameter.setOpenId(openId);
		parameter.setStudentId(studentId);
		List<PMParent> parentList = (List<PMParent>)dao.selectList("PMParent.select", parameter);
		
		if (parentList.size() > 0)
		{
			return parentList.get(0);
		}
		return null;
	}

}
