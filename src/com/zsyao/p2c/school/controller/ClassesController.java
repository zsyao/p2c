package com.zsyao.p2c.school.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsyao.p2c.Constants;
import com.zsyao.p2c.school.service.ISCMClassesService;
import com.zsyao.util.OpenIdUtil;

@Controller
@RequestMapping("/classes")
public class ClassesController
{
	@Resource
	private ISCMClassesService classesService;
	
	@RequestMapping(value = {"/joinClasses"}, produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String joinClasses(Integer schoolId, Integer classesId, String relation, String mobile, HttpServletRequest request) throws Exception
	{
		String openId = OpenIdUtil.getOpenId(request);
		
		JSONObject json = new JSONObject();
		json.put(Constants.JSON_OF_IS_SUCCESS, classesService.joinClasses(schoolId, classesId, openId, relation, mobile));
		return json.toString();
	}
}
