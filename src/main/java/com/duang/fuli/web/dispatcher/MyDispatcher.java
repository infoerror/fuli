package com.duang.fuli.web.dispatcher;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:57:30
 */
public class MyDispatcher extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String errorUrl = "/error/404.html";

	
	
	@Override
	protected void doDispatch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.doDispatch(request, response);
	}



	@Override
	protected LocaleContext buildLocaleContext(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.buildLocaleContext(request);
	}



	@Override
	protected void noHandlerFound(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.sendRedirect(request.getContextPath() + errorUrl);

	}

}
