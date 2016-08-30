package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Agree;

@Repository
public class AgreeDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void savaAgree(Agree agree) {
		sessionFactory.getCurrentSession().save(agree);
	}
	
	public void deleteAgree(Agree agree) {
		sessionFactory.getCurrentSession().delete(agree);
	}
	
	public List<Object> queryAllAgreeStory(Integer uid,Integer start,Integer length) {
		String hql = "from Story as story "
				+ "inner join Agree as agree with agree.storyId=story.storyId and agree.uid=?"
				+ "inner join History as history with history.historyId=story.historyId "
				+ "inner join Domain as domain with domain.domainId=history.domainId "
				+ "inner join IBLUser as user with user.uid=story.creatorId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
}
