package com.ibellar.calendar.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Interest {
	@Id
	private Integer Iterestid;
	
	private Integer uid;
	
	private Integer domainId;

	public Integer getIterestid() {
		return Iterestid;
	}

	public void setIterestid(Integer iterestid) {
		Iterestid = iterestid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	
	
	
}
