package com.ibellar.calendar.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"historyId","userId"})})
public class Attention {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int attentionId;
	
	private int historyId;
	
//	@OneToOne
//	private History histoy;
	
	private int userId;
	
	private int priority;
	
	private long attentionTime;

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

	public long getAttentionTime() {
		return attentionTime;
	}

	public void setAttentionTime(long attentionTime) {
		this.attentionTime = attentionTime;
	}

//	public History getHistoy() {
//		return histoy;
//	}
//
//	public void setHistoy(History histoy) {
//		this.histoy = histoy;
//	}
//	
	
	
	
	

	
}
