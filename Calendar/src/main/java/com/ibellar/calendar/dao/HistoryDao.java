package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.History;


@Repository
public class HistoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveHistory(History his) {
		sessionFactory.getCurrentSession().save(his);
	}
	
	public History queryHistoryWithId (Integer hid) {
		
		String hql = "from History as history where history.historyId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		List<History> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public History queryHistoryWithName(String name) {
		String hql = "from History as history where history.historyName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, name);
		List<History> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
		
	}
}
