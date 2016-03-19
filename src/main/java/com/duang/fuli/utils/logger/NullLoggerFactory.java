package com.duang.fuli.utils.logger;

/**
 * NullLoggerFactory.
 */
public class NullLoggerFactory implements ILoggerFactory {
	
	public Logger getLogger(Class<?> clazz) {
		return INSTANCE;
	}

	public Logger getLogger(String name) {
		return INSTANCE;
	}
	
	private static final Logger INSTANCE = new Logger() {
		
		public void debug(String message) {
		}
		
		public void debug(String message, Throwable t) {
		}
		
		public void error(String message) {
		}
		
		public void error(String message, Throwable t) {
		}
		
		public void info(String message) {
		}
		
		public void info(String message, Throwable t) {
		}
		
		public boolean isDebugEnabled() {
			return false;
		}

		public boolean isInfoEnabled() {
			return false;
		}

		public boolean isWarnEnabled() {
			return false;
		}
		
		public boolean isErrorEnabled() {
			return false;
		}
		
		public boolean isFatalEnabled() {
			return false;
		}
		
		public void warn(String message) {
		}
		
		public void warn(String message, Throwable t) {
		}
		
		public void fatal(String message) {
		}
		
		public void fatal(String message, Throwable t) {
		}
	};
}
