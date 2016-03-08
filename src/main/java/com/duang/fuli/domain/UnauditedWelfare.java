package com.duang.fuli.domain;

import java.util.Date;
import java.util.Set;

public class UnauditedWelfare {
	
	private int id;
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
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Set<WelfareTag> getWelfareTags() {
		return welfareTags;
	}
	public void setWelfareTags(Set<WelfareTag> welfareTags) {
		this.welfareTags = welfareTags;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	

}
