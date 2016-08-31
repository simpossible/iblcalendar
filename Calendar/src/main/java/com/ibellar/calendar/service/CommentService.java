package com.ibellar.calendar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.CommentDao;
import com.ibellar.calendar.entity.Comment;
import com.ibellar.calendar.entity.Domain;
import com.ibellar.calendar.entity.History;
import com.ibellar.calendar.entity.IBLUser;
import com.ibellar.calendar.entity.Story;

@Transactional
@EnableTransactionManagement
@Service
public class CommentService {
	
	@Autowired
	private CommentDao dao;
	
	public void addComment(Comment comm) throws IBLException {
		try {
			dao.savaComment(comm);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void deleteComment(Comment comm) throws IBLException {
		try {
			dao.deleteComment(comm);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public List<HashMap> getCommentForStory(Integer storyId,Integer start,Integer length) throws IBLException {
		
	try {
		List<Object> origin = dao.queryAllCommentForStory(storyId, start, length);
		List<HashMap> result = new ArrayList<HashMap>();
		
		for (int i = 0; i < origin.size(); i++) {

			Object[] a = (Object[]) origin.get(i);
		
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			result.add(map);
		}
		return result;
	} catch (Exception e) {
		throw new IBLException(IBLErrorCode.DATABASE_ERROR);
	}
	}
}
