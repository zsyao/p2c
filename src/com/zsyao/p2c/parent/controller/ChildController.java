package com.zsyao.p2c.parent.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsyao.p2c.Constants;
import com.zsyao.p2c.base.model.SMParameter;
import com.zsyao.p2c.base.serivce.ISMParameterService;
import com.zsyao.p2c.parent.model.PMParent;
import com.zsyao.p2c.parent.service.IPMParentService;
import com.zsyao.p2c.school.model.SCMClasses;
import com.zsyao.p2c.school.model.SCMRecipes;
import com.zsyao.p2c.school.model.SCMSchool;
import com.zsyao.p2c.school.model.SCMStudent;
import com.zsyao.p2c.school.model.SCMTimetables;
import com.zsyao.p2c.school.service.ISCMClassesService;
import com.zsyao.p2c.school.service.ISCMRecipesService;
import com.zsyao.p2c.school.service.ISCMSchoolService;
import com.zsyao.p2c.school.service.ISCMStudentService;
import com.zsyao.p2c.school.service.ISCMTimetablesService;
import com.zsyao.util.DateUtil;
import com.zsyao.util.OpenIdUtil;

@Controller
@RequestMapping("/child")
public class ChildController
{
	@Resource
	private ISCMSchoolService schoolService;
	
	@Resource
	private ISCMClassesService classesService;
	
	@Resource
	private ISCMStudentService studentService;
	
	@Resource
	private ISMParameterService parameterService;
	
	@Resource
	private ISCMRecipesService recipesService;
	
	@Resource
	private ISCMTimetablesService timetablesService;
	
	@Resource
	private IPMParentService parentService;
	
	@RequestMapping("/view")
	public String view(Integer schoolId, Integer studentId, Model model, HttpServletRequest request) throws Exception
	{
		String openId = OpenIdUtil.getOpenId(request);
		
		SCMSchool school = schoolService.getSchoolById(schoolId);
		SCMStudent student = studentService.getStudent(studentId);
		SCMClasses classes = classesService.getClasses(student.getClassesId());
		PMParent parent = parentService.getParent(openId, studentId);
		
		SMParameter parameter = parameterService.getParameter(Constants.PARAMETER_OF_RELATION, parent.getRelation());
		
		List<SCMRecipes> recipesList = recipesService.getSCMRecipesList(student.getClassesId(), DateUtil.getNowDate());
		List<SCMTimetables> timetablesList = timetablesService.getTimetablesList(student.getClassesId(), DateUtil.getNowDate());
		
		model.addAttribute("parameter", parameter);
		model.addAttribute("school", school);
		model.addAttribute("classes", classes);
		model.addAttribute("student", student);
		model.addAttribute("recipesList", recipesList);
		model.addAttribute("timetablesList", timetablesList);
		return "/child/view";
	}
}
