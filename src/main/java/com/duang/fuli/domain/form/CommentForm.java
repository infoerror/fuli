package com.duang.fuli.domain.form;

import java.util.Date;
/**
 * 
 * @author zgq
 * @date 2016年3月22日 下午11:20:19
 */
public class CommentForm extends NeedLoginForm{
	
	private int welfareId;
	private int replyId;
	private String content;
	private Date commentTime;
	
	public int getWelfareId() {
		return welfareId;
	}
	public void setWelfareId(int welfareId) {
		this.welfareId = welfareId;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}


}
