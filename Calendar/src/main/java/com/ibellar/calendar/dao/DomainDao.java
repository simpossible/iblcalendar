package com.ibellar.calendar.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Domain;

@Repository
public class DomainDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveDomain(Domain domain) {
		
		sessionFactory.getCurrentSession().save(domain);
		
	}
	public Domain queryDomainWithId (Integer did) {
		
		String hql = "from Domain as domain where domain.domainId = ?";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, did);
		List<Domain> list = query.list();
		
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public Domain getDomainWithName (String name) {
		
		String hql = "from Domain as domain where domain.domainName = ?";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, name);
		List<Domain> list = query.list();
		
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public boolean isDoaminExist(String name) {
		Domain domain = getDomainWithName(name);
		if (domain == null) {
			return false;
		}
		return true;
	}
}
