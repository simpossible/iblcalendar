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

	public History queryHistoryWithId(Integer hid) {

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

	public Integer queryNumberOfHistoryOfUser(Integer uid) {
		String hql = "select count(*) from History as history where history.creatorId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		return  ((Number)query.uniqueResult()).intValue();
	}

	public List<History> queryHistoryOfUser(Integer uid, Integer start, Integer length) {
		String hql = "from History as history where history.creatorId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		query.setParameter(0, uid);
		return query.list();
	}

	public History queryHistoryWithNameInUser(Integer uid,String name) {
		String hql = "from History as history where history.creatorId=? and history.historyName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		query.setParameter(1, name);
		
		List<History> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public List<History> queryHistoryOrderByHotdegree(Integer start,Integer length) {
		String hql = "from History as history order by history.hotDegree desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public Integer queryTotalNumberOfHistory() {
		String hql = "select count(*) from History as history";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return  ((Number)query.uniqueResult()).intValue();
	}
}
