package com.duang.fuli.service.aop;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

import com.duang.fuli.domain.page.PageBase;
import com.duang.fuli.service.annotation.PageResult;
import com.duang.fuli.service.result.PageServiceResult;

/**
 * 
 * @author zgq
 * @date 2016年3月22日 下午11:51:32
 */
public class PageCalculator {
	
	private static final int DEFAULT_PAGE_SIZE=10;
	
	private static final int MAX_PAGE_SIZE=30;
	
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		Object page=jp.getArgs()[0];
		if(page instanceof PageBase){
			//计算分页
			PageBase pageBase=(PageBase) page;
			int currentPage=pageBase.getCurrentPage();
			int pageSize=pageBase.getPageSize();
			currentPage=currentPage<=1?1:currentPage;
			pageSize=pageSize<=0?DEFAULT_PAGE_SIZE:pageSize>MAX_PAGE_SIZE?MAX_PAGE_SIZE:pageSize;
			
		    
		    PageResult pageResult=page.getClass().getAnnotation(PageResult.class);
		    if(pageResult==null)
		    	throw new IllegalStateException("必须关联页面结果");
		    
		    Class<?> pageClass= pageResult.value();
		    Method pageCountMethod=pageClass.getMethod("getRecordCount", Object.class);
		    PageServiceResult<?> pageResultInstance=(PageServiceResult<?>) pageClass.newInstance();
		    
		    int pageCount = pageBase.getPageCount();
			// 强制
			if (pageBase.isForciblyFetchCount()) {
				int count = (int) pageCountMethod.invoke(null, page);
				pageCount = count / pageBase.getPageSize() + 1;
			} else {
				if(pageCount<=0){
					int count =(int) pageCountMethod.invoke(null, page);
					pageCount = count / pageBase.getPageSize() + 1;
				}
			}
			
			if(currentPage>pageCount){
				currentPage = pageCount;
			}
			//计算下标
			int startIndex = (currentPage-1)*pageSize;
		    pageBase.setStartIndex(startIndex);
		   
			pageResultInstance.setResults((List)jp.proceed());
			pageResultInstance.setCurrentPage(currentPage);
			pageResultInstance.setPageCount(pageCount);
			return pageResultInstance;
			
		}
		
		return jp.proceed();
	}
}
