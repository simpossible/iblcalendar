package com.ibellar.calendar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibellar.calendar.entity.Comment;

@Repository
public class CommentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void savaComment(Comment comment) {
		sessionFactory.getCurrentSession().save(comment);
	}
	
	public void deleteComment(Comment comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}
	
	public List<Object> queryAllCommentForStory(Integer storyId,Integer start,Integer length) {
		String hql = "from Comment as comm "
				+ "left join IBLUser as apUser with comm.applyUid=apUser.uid "
				+ "inner join IBLuser as cuser with comm.uid=cuser.uid "
				+ "where comm.storyId=? ordered by comm.createTime ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	
}
