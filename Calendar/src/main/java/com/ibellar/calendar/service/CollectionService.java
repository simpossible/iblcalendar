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
import com.ibellar.calendar.entity.CollectionFolder;

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
	
	public void addCollectionFolder(CollectionFolder folder) throws IBLException {
		try {
			dao.savaCollecctionFolder(folder);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void deleteCollectionFolder(CollectionFolder folder) throws IBLException {
		try {
			dao.deleteCollectionFolder(folder);
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
	
	//获取用户的所有收藏
	public List<Object> getAllFolderForUid(Integer folderId,Integer start,Integer length) throws IBLException {
		try {
			List<Object> origin = dao.queryAllCollectionInFolder(folderId, start, length);
			return origin;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	//获取一个收藏夹下面的所有收藏
	public List<Object> getAllCollectInFolder(Integer folderId,Integer start,Integer length) throws IBLException {
		try {
			List<Object> origin = dao.queryAllCollectionInFolder(folderId, start, length);
			return origin;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	
}
