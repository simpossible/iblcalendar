package com.ibellar.calendar.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attention {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int attentionId;
	
	private int historyId;
	
	private int userId;
	
	private int priority;

	public int getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(int attentionId) {
		this.attentionId = attentionId;
	}

	public int getHistoryId() {
		return historyId;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
	
	

	
}
