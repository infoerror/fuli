package com.duang.fuli.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
	
	public static String toString(Object object){
		return JSON.toJSONString(object);
	}

}
