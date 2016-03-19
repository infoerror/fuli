package com.duang.fuli.controller.base;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.duang.fuli.utils.JsonUtils;

public class JSONController extends BaseController {

	protected HttpServletResponse response;

	@ModelAttribute
	public void setReqAndRes(HttpServletResponse response) {
		this.response = response;
	}
	protected void writeJson(Object json) throws IOException {
		writeJson(JsonUtils.toString(json), response);
	}
}
