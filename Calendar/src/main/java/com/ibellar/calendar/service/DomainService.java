package com.ibellar.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.DomainDao;
import com.ibellar.calendar.entity.Domain;

@Transactional
@EnableTransactionManagement
@Service
public class DomainService {

	@Autowired
	private DomainDao domaindao;
	
	public Domain getDomainWithName(String name) throws IBLException {

		try {
			return domaindao.queryDomainWithName(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void addDomain(Domain domain) throws IBLException {
		
		try {
			domaindao.saveDomain(domain);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	
	public Integer getAllDomainCount() throws IBLException {
		
		try {
			return domaindao.QueryAllDomainCount();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
		
	}
	
	public List<Domain> getDomains(Integer start,Integer length) throws IBLException {
		
		try {
			return domaindao.queryDomain(start, length);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
		
	}
}
