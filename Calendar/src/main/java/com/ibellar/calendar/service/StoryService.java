package com.ibellar.calendar.service;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.dao.StoryDao;
import com.ibellar.calendar.entity.Story;

@Transactional
@EnableTransactionManagement
@Service
public class StoryService {

	@Autowired
	private StoryDao storydao;
	
	public void addStory(Story story) {
		storydao.saveStory(story);
	}
	
	// 判断是否已经有同名的类
	public boolean isHistoryHaveStoryWithName(Integer hid,String name) {
		Story st = storydao.queryStoryWithHistorydAndName(hid, name);
		if (st == null) {
			return false;
		}
		return true;
	}
	
	public Story getHistoryHaveStoryWithName(Integer hid,String name) {
		return storydao.queryStoryWithHistorydAndName(hid, name);
	}
	
	public void getStoryWithStoryId(Integer sid) {
		storydao.queryStoryWithId(sid);
	}
	
	public List<Story> getStoryInHistoryId(Integer hid,Integer start,Integer length) {
		return storydao.queryStoryInHistoryId(hid, start, length);
	}
	
	public Integer getNumberOfStoryInHistory(Integer hid) {
		return storydao.qureyNumberOfStoryInHistoryId(hid);
	}
	
	public List<Story> getStoryOfUser(Integer uid,Integer start,Integer length) {
		return storydao.queryStoryOfUser(uid, start, length);
	}
	
	public Integer getNumberOfStoryOfUser(Integer uid) {
		return storydao.queryNumberOfStoryOfUser(uid);
	}
	
}
