package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.entity.Attention;
import com.ibellar.calendar.entity.History;

@Repository
public class AttentionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveAttention(Attention at) {
		sessionFactory.getCurrentSession().save(at);
	}

	public List<Attention> getAttentionWithUid(Integer uid, Integer start, Integer length) {
		String hql = "from Attention as attention where attention.userId=?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		query.setFirstResult(start);
		query.setMaxResults(length);

		List<Attention> list = query.list();

		if (list == null || list.size() == 0) {
			return null;
		}
		return list;

	}

	public List<Attention> getAttentionWithHistoryId(Integer hid, Integer start, Integer length) {

		String hql = "from Attention as attention where attention.historyId=?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		query.setFirstResult(start);
		query.setMaxResults(length);
		List<Attention> list = query.list();

		if (list == null || list.size() == 0) {
			return null;
		}
		return list;

	}

	public Integer getAttentionNumberInHistoryId(Integer hid) {
		String hql = "select count(*) from Attention as attention where attention.historyId=?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return ((Number) query.uniqueResult()).intValue();
	}

	public Attention queryAttentionWithHistoryAndUser(Integer hid, Integer uid) {
		String hql = "from Attention as attention where attention.historyId=? and attention.userId=?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		query.setParameter(1, uid);

		List<Attention> list = query.list();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Integer QueryAttentionNumberOfUser(Integer uid) {
		String hql = "select count(*) from Attention as attention where attention.userId=?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return ((Number) query.uniqueResult()).intValue();
	}

	public void deleteAttentionWithUidAndHid(Integer hid, Integer uid) {
		String hql = "delete Attention as attention where attention.historyId=? and attention.userId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		query.setParameter(1, uid);
		query.executeUpdate();
	}

	public List<History> queryAttenionsHistory(Integer uid,Integer start,Integer length) {

		String hql = "from History as history where history.historyId in ( select at from Attention order by at.attentionTime desc where at.uid =?)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		query.setFirstResult(start);
		query.setMaxResults(length);
		
		return query.list();
		}
	
}


