package com.ibellar.calendar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Domain {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int domainId;
	
	@Column(unique = true,nullable  = false)
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
