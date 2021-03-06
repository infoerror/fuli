package com.duang.fuli.domain;

import java.io.Serializable;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:55:39
 */
public class User extends Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5039935226716207273L;
	
	private int id;

	private String username;

	private String password;

	private String nickname;
	
	private String avatarUrl;





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}


}
