package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Collection;
import com.ibellar.calendar.entity.CollectionFolder;

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
	
	
	
	//这里是收藏家的
	
	public void savaCollecctionFolder(CollectionFolder folder) {
		sessionFactory.getCurrentSession().save(folder);
	}
	
	public void deleteCollectionFolder(CollectionFolder folder) {
		sessionFactory.getCurrentSession().delete(folder);
	}
	
	public List<Object> queryAllCollectionFolderForUser(Integer uid,Integer start,Integer length) {
		String hql = "select folder from CollectionFolder as folder where folder.uid=? ";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	
	public List<Object>queryAllCollectionInFolder(Integer folderId,Integer start,Integer length) {
		String hql = "select collect from Collection as collect"
				+ "inner join CollectionFolder as folder with folder.folderId=? "
				+ "inner join Story as story with story.storyId=collect.storyId";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, folderId);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
}

