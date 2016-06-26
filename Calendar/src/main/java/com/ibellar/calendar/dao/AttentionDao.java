package com.ibellar.calendar.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Attention;

@Repository
public class AttentionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Attention getAttentionWithUid(Integer uid) {
	
		sessionFactory.getCurrentSession();
		return null;
	}
}
