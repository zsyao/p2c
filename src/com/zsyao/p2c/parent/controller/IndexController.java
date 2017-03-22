package com.zsyao.p2c.parent.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsyao.p2c.Constants;
import com.zsyao.p2c.parent.model.PMParent;
import com.zsyao.p2c.parent.service.IPMParentService;
import com.zsyao.p2c.school.model.SCMClasses;
import com.zsyao.p2c.school.model.SCMSchool;
import com.zsyao.p2c.school.service.ISCMClassesService;
import com.zsyao.p2c.school.service.ISCMSchoolService;
import com.zsyao.util.OpenIdUtil;

@Controller
@RequestMapping("/parent")
public class IndexController
{
	@Resource
	private ISCMSchoolService schoolService;
	
	@Resource
	private ISCMClassesService classesService;
	
	@Resource
	private IPMParentService parentService;
	
	@RequestMapping("/index")
	public String index(Integer schoolId, Model model, HttpServletRequest request) throws Exception
	{
		String openId = OpenIdUtil.getOpenId(request);
		//判断是否已加入
		List<PMParent> parentList = parentService.getParentList(openId, schoolId);
		
		if (parentList.size() > 1)
		{
			//学生首页
		}
		else if (parentList.size() == 1)
		{
			PMParent parent = parentList.get(0);
			return "redirect:/child/view?schoolId=" + schoolId + "&studentId=" + parent.getStudentId();
		}
		else
		{
			//待加入首页
			return "redirect:/school/join?schoolId=" + schoolId;
		}
		return "/parent/index";
	}
	
	@RequestMapping("/listSchool")
	public String listSchool(Model model) throws Exception
	{
		List<SCMSchool> schoolList = schoolService.getSchoolList();
		if (schoolList.size() > 5)
		{
			schoolList = schoolList.subList(0, 5);
		}
		model.addAttribute("schoolList", schoolList);
		return "/parent/listSchool";
	}

	@RequestMapping("/listClasses")
	public String listClasses(Model model) throws Exception
	{
		return "/parent/listClasses";
	}

	@RequestMapping(value = {"/searchSchool"}, produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String searchSchool(String name) throws Exception
	{
		List<SCMSchool> schoolList = schoolService.getSchoolListByName(name);
		
		JSONObject json = new JSONObject();
		json.put(Constants.JSON_OF_IS_SUCCESS, true);
		int count = 1;
		
		JSONArray arrySchool = new JSONArray();
		for (SCMSchool school : schoolList)
		{
			arrySchool.add(JSONObject.fromObject(school));
			if (count >= 5)
			{
				break;
			}
			count++;
		}
		json.put("schoolList", arrySchool);
		return json.toString();
	}
	
	@RequestMapping(value = {"/searchClasses"}, produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String searchClasses(Integer id) throws Exception
	{
		List<SCMClasses> classesList = classesService.getClassesList(id);
		JSONObject json = new JSONObject();
		json.put(Constants.JSON_OF_IS_SUCCESS, true);
		
		JSONArray arryClasses = new JSONArray();
		for (SCMClasses classes : classesList)
		{
			arryClasses.add(JSONObject.fromObject(classes));
		}
		json.put("classesList", arryClasses);
		return json.toString();
	}
	
	@RequestMapping(value = {"/requestJoinClasses"}, produces = {"text/html;charset=UTF-8"})
	@ResponseBody
	public String requestJoinClasses(Integer classesId, String reason) throws Exception
	{
		String openId = "";
		JSONObject json = new JSONObject();
		return json.toString();
	}
	
}
