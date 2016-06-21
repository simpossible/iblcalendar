package com.ibellar.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.dao.IBLUserDao;
import com.ibellar.calendar.entity.IBLUser;

@EnableTransactionManagement
@Service
public class IBLUserService {

	@Autowired
	private IBLUserDao dao;
	
	@Transactional
	public void saveUser(IBLUser u)
	{
		dao.saveUser(u);		
	}
}
