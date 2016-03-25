package com.duang.fuli.utils;

/**
 * 
 * @author zgq
 * @date 2016年3月25日 下午4:54:52
 */
public class PageUtils {
	
	public static int showCount = 5;
	public static int showImgCount = 4;
	
	
	public static int computerStartPage(int showCount,int currentPage,int totalPages){
		if(showCount<3)
			throw new IllegalStateException("分页异常");
		
		if(totalPages<=showCount){
			return 1;
		}
		int y=showCount%2;
		int half2=0;
		int half3= 0;
		if(y==0){
			half2=showCount/2;
			half3 =half2-1;
		}else{
			half2=showCount/2+1;
			half3 =showCount/2;
		}
		
		if(currentPage<=half2){
			return 1;
		}
		return currentPage - half3;
		
	}
	public static int computerEndPage(int showCount,int currentPage,int totalPages){
		if(showCount<3)
			throw new IllegalStateException("分页异常");
		
		if(totalPages<=showCount){
			return totalPages;
		}
		int half3= showCount/2;
		int endPage= currentPage + half3;
		return endPage>totalPages?totalPages:endPage;
	}
	
	
}
