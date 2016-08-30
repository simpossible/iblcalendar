package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Collection;

@Repository
public class CollectionDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveCollection(Collection collect) {
		sessionFactory.getCurrentSession().save(collect);
	}
	
	public void deleteCollection(Collection collect) {
		sessionFactory.getCurrentSession().delete(collect);
	}
	
	public List<Object> queryAllCollectStory(Integer uid,Integer start,Integer length) {
		String hql = "select story from Story as story "
				+ "inner join Collection as collect with collect.uid=? and collect.storyId=story.storyId";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
}

