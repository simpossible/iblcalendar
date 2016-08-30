package com.ibellar.calendar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"uid","StoryId"})})
public class Comment {
	@Id
	public Integer commentId;
	
	public Integer uid;
	
	public Integer StoryId;
	
	public String commentText;
	
	public Integer aplyUid;//是否是回复的他人的uid

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	

	
	
	
}
