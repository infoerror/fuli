package com.duang.fuli.utils;

import java.io.File;

public class FileUtils {

	public static String getFileSuffix(String originName) {
		return originName.substring(originName.lastIndexOf('.') + 1);
	}

	public static boolean mkdirs(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return file.mkdirs();
		return false;
	}

}
