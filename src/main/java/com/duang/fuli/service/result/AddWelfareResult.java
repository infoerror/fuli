package com.duang.fuli.service.result;

import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.service.result.Protocols.AddWelfare;

public class AddWelfareResult extends ServiceResult {

	public static final AddWelfareResult TITLE_FORMAT_ERROR;
	public static final AddWelfareResult CONTENT_FORMAT_ERROR;
	public static final AddWelfareResult ADD_WELFARE_SUCC;

	static {
		ADD_WELFARE_SUCC = new AddWelfareResult();
		ADD_WELFARE_SUCC.setCode(AddWelfare.SUCC);
		ADD_WELFARE_SUCC.setMsg("添加成功");

		TITLE_FORMAT_ERROR = new AddWelfareResult();
		TITLE_FORMAT_ERROR.setCode(AddWelfare.TITLE_FORMAT_ERROR);
		TITLE_FORMAT_ERROR.setMsg("标题长度不能少于10字");

		CONTENT_FORMAT_ERROR = new AddWelfareResult();
		CONTENT_FORMAT_ERROR.setCode(AddWelfare.CONTENT_FORMAT_ERROR);
		CONTENT_FORMAT_ERROR.setMsg("内容长度不能少于100字");
	}

	public static AddWelfareResult validate(WelfareForm welfareForm) {
		String title = welfareForm.getTitle();
		String content = welfareForm.getContent();
		if (title == null || title.length() < 10) {
			return TITLE_FORMAT_ERROR;
		}

		if (content == null || content.length() < 50) {
			return CONTENT_FORMAT_ERROR;
		}
		return null;
	}
}
