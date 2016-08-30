package com.ibellar.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.AgreeDao;
import com.ibellar.calendar.entity.Agree;

@Transactional
@EnableTransactionManagement
@Service
public class AgreeService {

	@Autowired
	private AgreeDao agreeDao;
	
	public void addAgree(Agree agree) throws IBLException {
		try {
			agreeDao.savaAgree(agree);
		} catch (Exception e) {
			// TODO: handle exception
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void deleteAgree(Agree agree) throws IBLException {
		try {
			agreeDao.deleteAgree(agree);
		} catch (Exception e) {
			// TODO: handle exception
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public  List<Object> getAllAgreeForUser(Integer uid,Integer start,Integer length) throws IBLException{
		try {
			List<Object> origin = agreeDao.queryAllAgreeStory(uid, start, length);
			return origin;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
}
