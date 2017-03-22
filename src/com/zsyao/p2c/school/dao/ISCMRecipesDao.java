package com.zsyao.p2c.school.dao;

import java.util.List;

import com.zsyao.p2c.school.model.SCMRecipes;


public interface ISCMRecipesDao
{
	List<SCMRecipes> getSCMRecipesList(Integer classesId, String useDate) throws Exception;
}
