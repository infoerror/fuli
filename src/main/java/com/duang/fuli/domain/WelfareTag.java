package com.duang.fuli.domain;

import java.io.Serializable;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:56:12
 */
public class WelfareTag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int markCount;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMarkCount() {
		return markCount;
	}
	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}
	

}
