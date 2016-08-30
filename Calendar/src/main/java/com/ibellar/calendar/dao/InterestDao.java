package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Interest;

@Repository
public class InterestDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void savaInterest(Interest inter) {
		sessionFactory.getCurrentSession().save(inter);
	}
	
	
	public void deleteInterest(Interest inter) {
		sessionFactory.getCurrentSession().delete(inter);	
	}
	
	public List<Object> queryInterestForUser(Integer uid){
		String hql = "select inter from Interest as inter where inter.uid=?";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		return query.list();
	}
	
	
	public List<Object> queryAllInterestDoaminForUser(Integer uid,Integer start,Integer length) {
		String hql = "select domain from Domain as domain "
				+ "inner join Interest as inter with inter.uid=? and inter.domainId=domain.domainId";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
}