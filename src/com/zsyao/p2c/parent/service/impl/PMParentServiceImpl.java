package com.zsyao.p2c.parent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.parent.dao.IPMParentDao;
import com.zsyao.p2c.parent.model.PMParent;
import com.zsyao.p2c.parent.service.IPMParentService;

@Service
@Transactional
public class PMParentServiceImpl implements IPMParentService
{
	@Resource
	private IPMParentDao parentDao;

	@Override
	public List<PMParent> getParentList(String openId) throws Exception
	{
		return parentDao.getParentList(openId);
	}

	@Override
	public List<PMParent> getParentList(String openId, Integer schoolId) throws Exception
	{
		return parentDao.getParentList(openId, schoolId);
	}

	@Override
	public PMParent getParent(String openId, Integer studentId) throws Exception
	{
		return parentDao.getParent(openId, studentId);
	}

}
