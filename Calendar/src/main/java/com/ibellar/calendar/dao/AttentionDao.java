package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.entity.Attention;

@Repository
public class AttentionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveAttention(Attention at) {
		sessionFactory.getCurrentSession().save(at);
	}
	
	public Attention getAttentionWithUid(Integer uid) {
		String hql = "from Attention as attention where attention.userId=?";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		
		List<Attention> list = query.list();
		
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
		
	}
	
	
	public Attention getAttentionWithHistoryId(Integer hid)  {

		String hql = "from Attention as attention where attention.historyId=?";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		
		List<Attention> list = query.list();
		
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
		
	}
}
