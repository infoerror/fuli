package com.duang.fuli.service.result;

import com.duang.fuli.domain.Comment;
import com.duang.fuli.domain.form.CommentForm;
import com.duang.fuli.service.annotation.Login;
import com.duang.fuli.service.result.Protocols.Welfare;
import com.duang.fuli.utils.StringUtils;

public class CommentResult extends DataServiceResult{
	
	
	public static final CommentResult CONTENT_FORMAT_ERROR;

	public static final CommentResult COMMENT_INEXISTENT;
	
	static{
		
		CONTENT_FORMAT_ERROR= new CommentResult();
		CONTENT_FORMAT_ERROR.setCode(Welfare.CONTENT_FORMAT_ERROR);
		CONTENT_FORMAT_ERROR.setMsg("评论内容不能少于10个字哦!");
		
		COMMENT_INEXISTENT= new CommentResult();
		COMMENT_INEXISTENT.setCode(Welfare.COMMENT_INEXISTENT);
		COMMENT_INEXISTENT.setMsg("回复评论不存在哦!");
	}
	
	@Login
	public static CommentResult validate(CommentForm commentForm){
		if(StringUtils.isBlank(commentForm.getContent()) || commentForm.getContent().length()<10){
			return CONTENT_FORMAT_ERROR;
		}
		return null;
	}
	

	public static CommentResult succ(Comment comment){
		CommentResult COMMENT_SUCC= new CommentResult();
		COMMENT_SUCC.setCode(Welfare.ADD_COMMENT_SUCC);
		COMMENT_SUCC.setMsg("评论成功！");
		COMMENT_SUCC.setData(comment);
		return COMMENT_SUCC;
	}



}
