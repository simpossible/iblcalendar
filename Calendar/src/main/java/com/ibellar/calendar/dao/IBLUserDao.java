package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.entity.IBLUser;

@Repository
public class IBLUserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveUser(IBLUser u) {
		sessionFactory.getCurrentSession().save(u);
	}

	public void deleteUser(IBLUser u) {
		sessionFactory.getCurrentSession().delete(u);
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public IBLUser quearyUserWithEmail(String email) {

		String hql = "from IBLUser as user where user.email=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, email);

		List<IBLUser> list = query.list();

		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);

	}
	
	public IBLUser quearyUserWithToken(String token) {

		String hql = "from IBLUser as user where user.accessToken=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, token);

		List<IBLUser> list = query.list();

		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);

	}
	
	public void upDateUser(IBLUser u) throws IBLException  {
				sessionFactory.getCurrentSession().update(u);

	}
}
