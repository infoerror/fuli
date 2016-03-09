package com.duang.fuli.controller.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.duang.fuli.domain.User;
import com.duang.fuli.utils.RenderUtils;
import com.duang.fuli.utils.SessionFlagUtils;

/**
 * @author fengorz
 * 
 *         springmvc异常的统一处理一般有两种方式：
 *         1，实现HandlerExceptionResolver接口，并在xml配置文件中指定对应的bean。
 *         2，用注解@ExceptionHandler,被这个注解标记的方法值负责本类的一异常处理，
 *         所以比较合理的方法就是集成一个拥有这种标记的父类。
 * 
 */
public class BaseController {

	Logger logger = Logger.getLogger(getClass());

	@ExceptionHandler
	public String exception(HttpServletRequest request, Exception e) {
		// 添加自己的异常处理逻辑
		// 想要用户看到的错误信息可以通过异常的构造器传进一些信息
		// e.getMessage()会取到穿进去的参数。
		request.setAttribute("exception", e.getMessage());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		logger.error("系统出现异常情况（" + df.format(new Date()) + "）");
		// 根据不同的异常类型进行不同处理

		e.printStackTrace();

		return "error/error";
	}

	/**
	 * @param json
	 * @param response
	 * @throws IOException
	 */
	protected void writeJson(String json, HttpServletResponse response)
			throws IOException {
		RenderUtils.renderJson(json, response);
	}

	protected void writeJson(String json) throws IOException {
          writeJson(json, getResponse());
	}
	protected final HttpServletResponse getResponse() {
		ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
		return servletContainer.getResponse();
	}
	
	protected final static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	}
	
	protected final static User getCurrentUser(){
		return (User) getRequest().getSession().getAttribute(SessionFlagUtils.LOGINED_USER_FLAG);
	}

}
