package com.duang.fuli.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duang.fuli.utils.logger.Logger;

abstract public class LoginInterceptor<T> extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(LoginInterceptor.class);

	private List<String> cookieNames;

	private List<String> ignoreURL;

	protected final void addIgnoreURL(String url) {
		if (ignoreURL == null) {
			ignoreURL = new ArrayList<String>();
		}
		ignoreURL.add(url);
	}

	protected boolean isSupportCookie() {
		return true;
	}

	protected final void addCookie(String name) {
		if (cookieNames == null) {
			cookieNames = new ArrayList<String>();
		}

		this.cookieNames.add(name);
	}

	protected abstract String loginSessionFlag();

	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (ignoreURL != null) {
			for (String URL : ignoreURL) {
				if (request.getRequestURI().equals(URL)) {
					return true;
				}
			}
		}

		T user = (T) getSessionAttr(request, loginSessionFlag());
		if (LOG.isDebugEnabled())
			LOG.debug("user " + user + " is preparing to login ...");
		if (user == null) {
			List<String> values = getCookieValues(request, this.cookieNames);
			if (values.size() == this.cookieNames.size()) {
				return doLogin(request, response, handler, values);
			} else {
				return lackCookie(request, response, handler);
			}
		}

		return true;
	}

	protected abstract boolean lackCookie(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception;

	abstract protected boolean doLogin(HttpServletRequest request,
			HttpServletResponse response, Object handler, List<String> values)
			throws Exception;
}
