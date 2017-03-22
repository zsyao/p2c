package com.zsyao.p2c.school.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsyao.core.dao.IDao;
import com.zsyao.p2c.school.dao.ISCMRecipesDao;
import com.zsyao.p2c.school.model.SCMRecipes;

@SuppressWarnings("unchecked")
@Repository
public class SCMRecipesDaoImpl implements ISCMRecipesDao
{
	@Resource
	private IDao dao;

	@Override
	public List<SCMRecipes> getSCMRecipesList(Integer classesId, String useDate) throws Exception
	{
		SCMRecipes parameter = new SCMRecipes();
		parameter.setClassesId(classesId);
		parameter.setUseDate(useDate);
		return (List<SCMRecipes>)dao.selectList("SCMRecipes.select", parameter);
	}

}
