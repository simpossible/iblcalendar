package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
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
	
	public void deleteUser(IBLUser u) {
		sessionFactory.getCurrentSession().delete(u);
	}
	
	public IBLUser quearyUserWithEmail(String email) {
		String hql = "from IBLUser as IBLUser where user.email=:name";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("name", email);
		
		List<IBLUser> list = query.list();
		
		return list.get(0);
		
	}
}
