package com.ibellar.calendar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ibellar.calendar.entity.History;

@Entity
public class Story {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int storyId;
	
	private String storyName;
	
	private String storyText;
	
	private int historyId;
	
	private Integer creatorId;
	
	@Column(unique = true,nullable  = false)
	private Integer storyDate;//故事发生的时间 120110203
	
	private Integer hotDegree;//热度
	
	private Integer agree;//赞的个数

	/**
	 * 创建的时间
	 */
	private long createTime;
	
	/**
	 * 描述的日期
	 */
	private long date;
	

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}

	public String getStoryText() {
		return storyText;
	}

	public void setStoryText(String storyText) {
		this.storyText = storyText;
	}

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getHotDegree() {
		return hotDegree;
	}

	public void setHotDegree(Integer hotDegree) {
		this.hotDegree = hotDegree;
	}

	public Integer getAgree() {
		return agree;
	}

	public void setAgree(Integer agree) {
		this.agree = agree;
	}

	public Integer getStoryDate() {
		return storyDate;
	}

	public void setStoryDate(Integer storyDate) {
		if (storyDate > 99999999 || storyDate <-99999999) {
			this.storyDate = 100000000;
		}
		this.storyDate = storyDate;
		
	}


	
}
