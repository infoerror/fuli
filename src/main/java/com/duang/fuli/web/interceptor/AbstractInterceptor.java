package com.duang.fuli.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午2:26:12
 */
public abstract class AbstractInterceptor extends  HandlerInterceptorAdapter{



	@SuppressWarnings("unchecked")
	protected static <T> T getSessionAttr(HttpServletRequest request,
			String name, Class<T> u) {
		return (T) request.getSession().getAttribute(name);
	}

	protected static  Object getSessionAttr(HttpServletRequest request,
			String name) {
		return request.getSession().getAttribute(name);
	}
	
	protected static List<String> getCookieValues(HttpServletRequest request,List<String> names){
		
		return getCookieValues(request,names.toArray(new String[]{}));
		
		
	}
	
	
	protected static List<String> getCookieValues(HttpServletRequest request,
			String... names) {
		List<String> values;
		Cookie[] cookies = request.getCookies();
		if (names == null) {
			values = new ArrayList<String>(cookies.length);
			for (Cookie cookie : cookies) {
				values.add(cookie.getValue());
			}

			return values;
		}
	
		values = new ArrayList<String>(names.length);
		for (String name : names) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					values.add(cookie.getValue());
					break;
				}
			}
		}

		return values;

	}

}
