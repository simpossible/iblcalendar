package com.ibellar.calendar.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.CollectionDao;
import com.ibellar.calendar.entity.Collection;

@Transactional
@EnableTransactionManagement
@Service
public class CollectionService {
	@Autowired
	private CollectionDao dao;
	
	public void addCollection(Collection collect) throws IBLException {
		try {
			dao.saveCollection(collect);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void deleteCollection(Collection collect) throws IBLException {
		try {
			dao.deleteCollection(collect);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public  List<Object> getAllCollectionForUser(Integer uid,Integer start,Integer length) throws IBLException{
		try {
			List<Object> origin = dao.queryAllCollectStory(uid, start, length);
			return origin;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
}
