package com.duang.fuli.domain.form;

import java.io.Serializable;
import com.duang.fuli.domain.User;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:56:57
 */
public class WelfareForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private int[] welfareTagIds;
	private User author;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int[] getWelfareTagIds() {
		return welfareTagIds;
	}
	public void setWelfareTagIds(int[] welfareTagIds) {
		this.welfareTagIds = welfareTagIds;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}


}
