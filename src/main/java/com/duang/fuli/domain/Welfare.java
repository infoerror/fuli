package com.duang.fuli.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Welfare implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int viewCount;
	private String title;
	private String content;
	private User author;
	private Set<WelfareTag> welfareTags;
	private Date publishTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public Set<WelfareTag> getWelfareTags() {
		return welfareTags;
	}
	public void setWelfareTags(Set<WelfareTag> welfareTags) {
		this.welfareTags = welfareTags;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

}
