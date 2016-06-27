package com.ibellar.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.dao.DomainDao;
import com.ibellar.calendar.entity.Domain;

@Transactional
@EnableTransactionManagement
@Service
public class DomainService {

	@Autowired
	private DomainDao domaindao;
	
	public Domain getDomainWithName(String name) {
		return domaindao.queryDomainWithName(name);
	}
	
	public void addDomain(Domain domain) {
		domaindao.saveDomain(domain);
	}
	
}
