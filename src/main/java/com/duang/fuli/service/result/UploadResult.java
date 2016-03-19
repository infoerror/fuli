package com.duang.fuli.service.result;

import com.duang.fuli.service.result.Protocols.ModifyAvatar;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:54:34
 */
public class UploadResult extends DataResult {

	public static final UploadResult NO_FILE_UPLOAD = new UploadResult();

	static {
		NO_FILE_UPLOAD.setCode(ModifyAvatar.UPLOAD_AVATAR_FAIL);
		NO_FILE_UPLOAD.setMsg("沒有上传文件哦！");
	}

	public static UploadResult uploadSucc(String fileName) {
		UploadResult UPLOAD_SUCC = new UploadResult();
		UPLOAD_SUCC.setCode(ModifyAvatar.UPLOAD_SUCC);
		UPLOAD_SUCC.setMsg("上传成功");
		UPLOAD_SUCC.setData(fileName);
		return UPLOAD_SUCC;
	}

}