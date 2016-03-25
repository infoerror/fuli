package com.duang.fuli.controller.base;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.duang.fuli.domain.User;
import com.duang.fuli.service.result.PageServiceResult;
import com.duang.fuli.utils.PageUtils;
import com.duang.fuli.utils.RenderUtils;
import com.duang.fuli.web.utils.SessionFlags;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午3:19:23
 */
public class BaseController {

	
	@ExceptionHandler
	public String exception(HttpServletRequest request, Exception e) {
		// 添加自己的异常处理逻辑
		// 想要用户看到的错误信息可以通过异常的构造器传进一些信息
		// e.getMessage()会取到穿进去的参数。
		request.setAttribute("exception", e.getMessage());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		// 根据不同的异常类型进行不同处理
		e.printStackTrace();
		return "error/error";
	}
	

	/**
	 * @param json
	 * @param response
	 * @throws IOException
	 */
	protected  static final void writeJson(String json, HttpServletResponse response)
			throws IOException {
		RenderUtils.renderJson(json, response);
	}
	
	
	protected static final HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	}
	
	protected  static final User getCurrentUser(){
		return (User) getRequest().getSession().getAttribute(SessionFlags.LOGINED_USER_FLAG);
	}
	
	protected  static final String getContextPath(){
		return getRequest().getServletContext().getContextPath();
	}
	
	protected static final String getRealPath(String path){
		return getRequest().getServletContext().getRealPath(path);
	}
	
	protected static final void addCookies(HttpServletResponse response,Cookie... cookies){
		for(Cookie cookie:cookies){
			response.addCookie(cookie);
		}
	}

	protected static final void setSession(String key,Object value){
		getRequest().getSession().setAttribute(key, value);
	}
	
	protected static final <T> void sequencePage(PageServiceResult<T> pageServiceResult,Model model){
		model.addAttribute("currentPage",pageServiceResult.getCurrentPage());
		int startPage=PageUtils.computerStartPage(5,1,pageServiceResult.getPageCount());
		model.addAttribute("startPage", startPage);
		int endPage =PageUtils.computerEndPage(5,1,pageServiceResult.getPageCount());
		model.addAttribute("endPage", endPage);
	}
}
