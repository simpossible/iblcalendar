package com.ibellar.calendar.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"uid","StoryId"})})
public class Agree {

	@Id
	public Integer agreeeId;
	
	public Integer uid;
	
	public Integer StoryId;

	public Integer getAgreeeId() {
		return agreeeId;
	}

	public void setAgreeeId(Integer agreeeId) {
		this.agreeeId = agreeeId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getStoryId() {
		return StoryId;
	}

	public void setStoryId(Integer storyId) {
		StoryId = storyId;
	}
	
	
	
}
