package com.ibellar.calendar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.ibellar.calendar.entity.IBLUser;

@Entity
public class History {

	@Id
	private int historyId;
	
	private String historyName;
	
	private String summary;
	
	private int creatorId;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public String getHistoryName() {
		return historyName;
	}

	public void setHistoryName(String historyName) {
		this.historyName = historyName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}


	
	
	
}
