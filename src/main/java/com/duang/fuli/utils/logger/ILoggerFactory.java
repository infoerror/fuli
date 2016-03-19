package com.duang.fuli.utils.logger;

/**
 * ILoggerFactory.
 */
public interface ILoggerFactory {
	
	Logger getLogger(Class<?> clazz);
	
	Logger getLogger(String name);
}
