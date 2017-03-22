package com.zsyao.p2c.school.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsyao.p2c.Constants;
import com.zsyao.p2c.base.model.SMParameter;
import com.zsyao.p2c.base.serivce.ISMParameterService;
import com.zsyao.p2c.school.model.SCMClasses;
import com.zsyao.p2c.school.service.ISCMClassesService;

@Controller
@RequestMapping("/school")
public class SchoolController
{
	@Resource
	private ISCMClassesService classesService;
	
	@Resource
	private ISMParameterService parameterService;
	
	@RequestMapping("/join")
	public String join(Integer schoolId, Model model) throws Exception
	{
		List<SCMClasses> classesList = classesService.getClassesList(schoolId);
		model.addAttribute("classesList", classesList);
		
		List<SMParameter> parameterList = parameterService.getParameterList(Constants.PARAMETER_OF_RELATION);
		model.addAttribute("parameterList", parameterList);
		
		model.addAttribute("schoolId", schoolId);
		
		return "/school/join";
	}
}
