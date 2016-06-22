package com.ibellar.calendar.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Domain {

	@Id
	private int domainId;
	
	private String domainName;

	
	
	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	
	
	
}
