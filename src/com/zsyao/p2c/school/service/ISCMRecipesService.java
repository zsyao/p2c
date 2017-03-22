package com.zsyao.p2c.school.service;

import java.util.List;

import com.zsyao.p2c.school.model.SCMRecipes;


public interface ISCMRecipesService
{
	List<SCMRecipes> getSCMRecipesList(Integer classesId, String useDate) throws Exception;
}
