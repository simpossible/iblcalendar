package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.History;
import com.ibellar.calendar.entity.Story;


@Repository
public class StoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveStory (Story story) {
		sessionFactory.getCurrentSession().save(story);
	}
	
	public Story queryStoryWithId(Integer sid) {
		String hql  = "from Story as story where story.storyId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, sid);
		List<Story> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public List<Story> queryStoryInHistoryId(Integer hid,Integer start,Integer length) {
		String hql  = "from Story as story where story.historyId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		query.setParameter(0, hid);
		List<Story> list = query.list();
		return list;
	}
	
	public Integer qureyNumberOfStoryInHistoryId(Integer hid) {
		String hql  = "select count(*) from Story as story where story.historyId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		return  ((Number)query.uniqueResult()).intValue();
	}
	
	public List<Story> queryStoryOfUser(Integer uid,Integer start,Integer length) {
		String hql = "from Story as story where story.creatorId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		query.setParameter(0, uid);
		
		return query.list();
	}
	
	public Integer queryNumberOfStoryOfUser(Integer uid) {
		String hql = "select count(*) from Story as story where story.creatorId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, uid);
		
		return  ((Number)query.uniqueResult()).intValue();
	}
	
	public Story queryStoryWithHistorydAndName(Integer hid,String name) {
		String hql  = "from Story as story where story.historyId=? and story.storyName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, hid);
		query.setParameter(1, name);
		List<Story> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	/***
	 * 获取热度story
	 */
	
	public List<Story> queryStoryOrderByHotdegree(Integer start,Integer length) {
		String hql = "from Story as story order by story.hotDegree desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	public Integer queryTotalNumberOfStory() {
		String hql = "select count(*) from Story as story";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return  ((Number)query.uniqueResult()).intValue();
	}
}
