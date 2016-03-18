package com.duang.fuli.service.result;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;



public class CommonResults {

	private static final ConcurrentHashMap<Class<?>, Object> RESULT_CACHE=new ConcurrentHashMap<Class<?>,Object>();

	public static <T> T USER_NO_LOGIN(Class<?> result) {
		T obj =  (T) RESULT_CACHE.get(result);
		if(obj==null){
			try {
				obj=(T) result.newInstance();
				Field codeField= obj.getClass().getField("code");
				Field msgField= obj.getClass().getField("msg");
				codeField.setAccessible(true);
				codeField.set(obj, Protocols.USER_NO_LOGIN_CODE);
				msgField.setAccessible(true);
                msgField.set(obj, "还没有登录网站哦");
                RESULT_CACHE.put(result, obj);
                return obj;
			} catch (Throwable e) {
				return null;
			}
		}
	     return obj;
	}

}
