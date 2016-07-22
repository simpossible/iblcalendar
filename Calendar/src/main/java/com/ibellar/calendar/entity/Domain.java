package com.ibellar.calendar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Domain {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer domainId;
	
	@Column(unique = true,nullable  = false)
	private String domainName;

	private String domainSummary;
	
	private String domainLogoPath;//logo的静态地址
	
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

	public String getDomainSummary() {
		return domainSummary;
	}

	public void setDomainSummary(String domainSummary) {
		this.domainSummary = domainSummary;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public String getDomainLogPath() {
		return domainLogoPath;
	}

	public void setDomainLogoPath(String domainLogoPath) {
		this.domainLogoPath = domainLogoPath;
	}
	
	
	
	
}
