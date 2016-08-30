package com.ibellar.calendar.dao;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	public List<Object> queryStoryOrderByHotdegree(Integer start,Integer length) {
		String hql = "select story,h,d from Story as story ,History as h ,Domain as d where h.historyId=story.historyId and d.domainId=h.domainId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
	//获取一个
	public List<Object> queryAttentionStory(Integer start,Integer length,Integer uid) {
//		String hql = "select big.*,attention.attentionId from (select story.*, h.historyName, d.domainName from Story as story,History as h,Domain as d where h.historyId=story.historyId and d.domainId=h.domainId) as big left join Attention as attention on (big.historyId = attention.historyId  and attention.userId =?)";
//		String hqla = "select select story as ,history,domain from Story as story, History as history, Domain as domain where history.historyId=story.historyId and domain.domainId=history.domainId";
//		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		String hql = "select story,history,domain,user from Story as story "
				+ "inner join History as history with history.historyId=story.historyId "
				+ "inner join Domain as domain with domain.domainId=history.domainId "
				+ "inner join IBLUser as user with user.uid=story.creatorId";
//			
//		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		query.setParameter(0, uid);
//		query.setFirstResult(start);
//		query.setMaxResults(length);
	
		return query.list();
	}
	
	public Integer queryTotalNumberOfStory() {
		String hql = "select count(*) from Story as story";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return  ((Number)query.uniqueResult()).intValue();
	}

}
