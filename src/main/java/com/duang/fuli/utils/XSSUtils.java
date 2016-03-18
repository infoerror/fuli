package com.duang.fuli.utils;

public class XSSUtils {

	public static boolean simplyValidate(String str){
		if(str.contains("<") || str.contains(">") || str.contains("script")){
          return true;			
		}
		 return false;
	}

}
