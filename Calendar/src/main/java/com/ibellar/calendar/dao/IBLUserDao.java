package com.ibellar.calendar.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.IBLUser;

@Repository
public class IBLUserDao {
	@Autowired
    private SessionFactory sessionFactory;

	public void saveUser(IBLUser u) {
		sessionFactory.getCurrentSession().save(u);
		System.out.println(sessionFactory+"121212");
	}
}
