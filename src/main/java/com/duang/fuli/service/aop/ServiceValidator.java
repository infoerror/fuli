package com.duang.fuli.service.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class ServiceValidator {
	/** 
     * 方法环绕拦截器，如果使用了这个，可以忽视上面的方法 
     * 注意该方法参数为ProceedingJoinPoint ，这是可以执行的，只有round可以使用 
     * @param joinPoint 
     * @return 
	 * @throws Throwable 
     */  
    public Object around(ProceedingJoinPoint jp) throws Throwable {  
    	MethodSignature methodSignature = (MethodSignature) jp.getSignature();  
		Method method = methodSignature.getMethod(); 
        Class<?> returnType=method.getReturnType();
        try{
           Object[] args=jp.getArgs();
           Method validateMethod=returnType.getMethod("validate",args[0].getClass());
           Object returnObj=validateMethod.invoke(null, args[0]);
           if(returnObj==null){
        	   return jp.proceed();   
           }
           return returnObj;
        }catch(Throwable e){
            	
        }  
         return jp.proceed(); 
    }  
	
}
