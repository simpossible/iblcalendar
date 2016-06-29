package com.ibellar.calendar.service;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.StoryDao;
import com.ibellar.calendar.entity.Story;

@Transactional
@EnableTransactionManagement
@Service
public class StoryService {

	@Autowired
	private StoryDao storydao;
	
	public void addStory(Story story) throws IBLException {
		
		try {
			storydao.saveStory(story);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	// 判断是否已经有同名的类
	public boolean isHistoryHaveStoryWithName(Integer hid,String name) throws IBLException {
		
		try {

			Story st = storydao.queryStoryWithHistorydAndName(hid, name);
			if (st == null) {
				return false;
			}
			return true;
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	public Story getHistoryHaveStoryWithName(Integer hid,String name) throws IBLException {
		try {

			return storydao.queryStoryWithHistorydAndName(hid, name);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	public Story getStoryWithStoryId(Integer sid) throws IBLException {
		
		try {
			return storydao.queryStoryWithId(sid);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	public List<Story> getStoryInHistoryId(Integer hid,Integer start,Integer length) throws IBLException {
	
		try {
			return storydao.queryStoryInHistoryId(hid, start, length);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	public Integer getNumberOfStoryInHistory(Integer hid) throws IBLException {
		
		try {
			return storydao.qureyNumberOfStoryInHistoryId(hid);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	public List<Story> getStoryOfUser(Integer uid,Integer start,Integer length) throws IBLException {
		
		try {
			return storydao.queryStoryOfUser(uid, start, length);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
	}
	
	public Integer getNumberOfStoryOfUser(Integer uid) throws IBLException {
		try {
			return storydao.queryNumberOfStoryOfUser(uid);
			} catch (Exception e) {
				throw new IBLException(IBLErrorCode.DATABASE_ERROR);
				// TODO: handle exception
			}
		
	}
	public List<Story> gethotStory(Integer start,Integer length) throws IBLException {
		
		try {
		return	storydao.queryStoryOrderByHotdegree(start, length);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}
	
}
