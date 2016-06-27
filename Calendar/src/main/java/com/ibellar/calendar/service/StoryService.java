package com.ibellar.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.dao.StoryDao;

@Transactional
@EnableTransactionManagement
@Service
public class StoryService {

	@Autowired
	private StoryDao storydao;
	
	
}
