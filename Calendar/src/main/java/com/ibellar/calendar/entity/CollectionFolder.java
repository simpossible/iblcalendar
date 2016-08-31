package com.ibellar.calendar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CollectionFolder {
	@Id
	public Integer folderId;
	
	public Integer uid;
	
	public String floderName;

	public Integer getFolderId() {
		return folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getFloderName() {
		return floderName;
	}

	public void setFloderName(String floderName) {
		this.floderName = floderName;
	}
	
	
}
