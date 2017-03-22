package com.zsyao.p2c.base.filter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.zsyao.p2c.Constants;
import com.zsyao.p2c.base.exception.SystemException;
import com.zsyao.p2c.base.serivce.ISMExceptionService;
import com.zsyao.util.OpenIdUtil;


public class ExceptionHandler implements HandlerExceptionResolver
{
	private static Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	@Resource
	private ISMExceptionService exceptionService;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{
		logger.info("#####################################Exception");
		ex.printStackTrace();
		HandlerMethod method = (HandlerMethod)handler;
		ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
		
		if (responseBody != null)
		{
			MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("isSuccess", false);
			
			int errorCode = Constants.ERROR_CODE_OF_DEFAULT;
			if (ex instanceof SystemException)
			{
				errorCode = ((SystemException) ex).getErrorCode();
			}
			exceptionService.addSMException(OpenIdUtil.getOpenId(request), ex, "", "" + errorCode);
			attributes.put("errorCode", errorCode);
			
			jsonView.setContentType("text/plain; charset=utf-8");
			jsonView.setAttributesMap(attributes);
			ModelAndView view = new ModelAndView();
			view.setView(jsonView);
			return view;
		}
		else
		{
			int errorCode = Constants.ERROR_CODE_OF_DEFAULT;
			if (ex instanceof SystemException)
			{
				errorCode = ((SystemException) ex).getErrorCode();
			}
			exceptionService.addSMException(OpenIdUtil.getOpenId(request), ex, "", "" + errorCode);
		}
		return new ModelAndView("/common/errorPage");
	}

}
