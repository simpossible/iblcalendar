package com.ibellar.calendar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ibellar.calendar.entity.IBLUser;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int historyId;
	
	@Column(unique = true,nullable  = false)
	private String historyName;
	
	private Integer domainId;//所属领域
	


	private String summary;
	
	private int creatorId;
	
	private long createTime;
	
	private Integer hotDegree;//热度
	
//	private Integer agree;//赞的个数
	
	private String historyLogoPath;//图标的静态地址
	
	private Integer priority;//权限 0为默认值 公开 1为私人 

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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public Integer getHotDegree() {
		return hotDegree;
	}

	public void setHotDegree(Integer hotDegree) {
		this.hotDegree = hotDegree;
	}


	public String getHistoryLogoPath() {
		return historyLogoPath;
	}

	public void setHistoryLogoPath(String historyLogoPath) {
		this.historyLogoPath = historyLogoPath;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	
	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	
}
