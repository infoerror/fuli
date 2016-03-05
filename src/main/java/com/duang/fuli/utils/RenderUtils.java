package com.duang.fuli.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

abstract public class RenderUtils {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger LOG = Logger.getLogger(RenderUtils.class);

	// private static final String DEFAULT_CONTENT_TYPE = "text/plain; charset="
	// + Constants.DEFAULT_ENCODING;
	

	private static final String JSON_TYPE = "application/json; charset="
			+ DEFAULT_ENCODING;
	private static final String TEXT_TYPE = "text/plain; charset="
			+ DEFAULT_ENCODING;
	


	
	public static void renderText(String content,HttpServletResponse resp) throws IOException {
		render(content, TEXT_TYPE, resp);
	}

	public static void render(String content, String mime,
			HttpServletResponse resp) throws IOException {
		PrintWriter writer = null;
		try {
			resp.setHeader("Pragma", "no-cache"); // HTTP/1.0 caches might not
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			resp.setContentType(mime);
			writer = resp.getWriter();
			if (LOG.isDebugEnabled())
				LOG.debug("write content to client --->" + content);
			writer.write(content);
			writer.flush();
		} finally {
			if (writer != null)
				writer.close();
		}

	}

	public static void renderJson(String content, HttpServletResponse resp) throws IOException {
		render(content, JSON_TYPE, resp);
	}
}
