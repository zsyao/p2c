package com.zsyao.p2c.parent.dao;

import java.util.List;

import com.zsyao.p2c.parent.model.PMParent;


public interface IPMParentDao
{
	List<PMParent> getParentList(String openId) throws Exception;
	
	List<PMParent> getParentList(String openId, Integer schoolId) throws Exception;
	
	PMParent getParent(String openId, Integer studentId) throws Exception;
	
	boolean insert(PMParent parent) throws Exception;
}
