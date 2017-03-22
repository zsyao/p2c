package com.zsyao.p2c.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/msg")
public class MessageController
{
	@RequestMapping("/warn")
	public String warn()
	{
		return "/common/msgWarn";
	}

	@RequestMapping("/success")
	public String success()
	{
		return "/common/msgSuccess";
	}
}
