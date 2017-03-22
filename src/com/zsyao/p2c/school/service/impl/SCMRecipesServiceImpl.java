package com.zsyao.p2c.school.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsyao.p2c.school.dao.ISCMRecipesDao;
import com.zsyao.p2c.school.model.SCMRecipes;
import com.zsyao.p2c.school.service.ISCMRecipesService;

@Service
@Transactional
public class SCMRecipesServiceImpl implements ISCMRecipesService
{
	@Resource
	private ISCMRecipesDao recipesDao;

	@Override
	public List<SCMRecipes> getSCMRecipesList(Integer classesId, String useDate) throws Exception
	{
		return recipesDao.getSCMRecipesList(classesId, useDate);
	}

}
