package com.duang.fuli.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.mapping.Value;


/**
 * 
 * @author zgq
 * @date 2016年3月25日 下午5:52:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PageResult {
	
   Class<?> value(); 
}
