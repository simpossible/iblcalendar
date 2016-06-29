package com.ibellar.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.HistoryDao;
import com.ibellar.calendar.dao.StoryDao;
import com.ibellar.calendar.entity.History;

@Transactional
@EnableTransactionManagement
@Service
public class HistoryService {
	@Autowired
	private HistoryDao historyDao;
	
	public void addHistory(History history) throws IBLException {
		try {
			historyDao.saveHistory(history);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	
	}
	
	public History getHistoryWithNameInUser(Integer uid, String name) throws IBLException {
	
		try {
			return historyDao.queryHistoryWithNameInUser(uid, name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public boolean isHistoryNameExistInUser (Integer uid, String name) throws IBLException {
		
		try {
			History history = historyDao.queryHistoryWithNameInUser(uid, name);
			if (history == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	
	}
			
	
	public Integer getNumberOfHistoryOfUser(Integer uid) throws IBLException {
		try {
			return historyDao.queryNumberOfHistoryOfUser(uid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public List<History> getHistoryOfUser(Integer uid,Integer start,Integer length) throws IBLException  {
		try {
			return historyDao.queryHistoryOfUser(uid, start, length);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	
	
	public List<History> getHotHistory (Integer start,Integer length) throws IBLException {
		if (start < 0 || length <= 0 ) {
			throw new IBLException(IBLErrorCode.ILLEGAL_PARAM);
		}
		try {
			return historyDao.queryHistoryOrderByHotdegree(start, length);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
}
