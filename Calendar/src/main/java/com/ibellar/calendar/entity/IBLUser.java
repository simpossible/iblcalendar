package com.ibellar.calendar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IBLUser {

	@Id
	private int uid;
	
	private String passwd;
	
	private String email;
	
	private String telNumber;//电话好号码
	
	private String nickName;

	private Boolean isActive;//是否已经激活 邮件激活
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
}
