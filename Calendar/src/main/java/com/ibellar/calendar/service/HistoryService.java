package com.ibellar.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.dao.HistoryDao;
import com.ibellar.calendar.dao.StoryDao;
import com.ibellar.calendar.entity.History;

@Transactional
@EnableTransactionManagement
@Service
public class HistoryService {
	@Autowired
	private HistoryDao historyDao;
	
	public void addHistory(History history) {
		historyDao.saveHistory(history);
	}
	
	public History getHistoryWithNameInUser(Integer uid, String name) {
		return historyDao.queryHistoryWithNameInUser(uid, name);
	}
	
	public boolean isHistoryNameExistInUser (Integer uid, String name) {
		History history = historyDao.queryHistoryWithNameInUser(uid, name);
		if (history == null) {
			return false;
		}
		return true;
	}
			
	
	public Integer getNumberOfHistoryOfUser(Integer uid) {
		return historyDao.queryNumberOfHistoryOfUser(uid);
	}
	
	public List<History> getHistoryOfUser(Integer uid,Integer start,Integer length)  {
		return historyDao.queryHistoryOfUser(uid, start, length);
	}
}
