package com.ibellar.calendar.service;

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
	
	public void getStoryWithStoryId(Integer sid) {
		storydao.queryStoryWithId(sid);
	}
	
	public 
	
}
