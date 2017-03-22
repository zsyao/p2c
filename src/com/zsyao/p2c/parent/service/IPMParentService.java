package com.zsyao.p2c.parent.service;

import java.util.List;

import com.zsyao.p2c.parent.model.PMParent;


public interface IPMParentService
{
	List<PMParent> getParentList(String openId) throws Exception;
	
	List<PMParent> getParentList(String openId, Integer schoolId) throws Exception;
	
	PMParent getParent(String openId, Integer studentId) throws Exception;
}
