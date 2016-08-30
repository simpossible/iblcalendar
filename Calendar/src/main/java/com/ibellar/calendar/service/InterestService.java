package com.ibellar.calendar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.InterestDao;
import com.ibellar.calendar.entity.Interest;

@Transactional
@EnableTransactionManagement
@Service
public class InterestService {

	@Autowired
	private InterestDao dao;
	public void addInterest(Interest inter) throws IBLException {
		try {
			dao.savaInterest(inter);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void deleteInterest(Interest inter) throws IBLException {
		try {
			dao.deleteInterest(inter);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	
	public  List<Object> getAllInterestForUser(Integer uid,Integer start,Integer length) throws IBLException{
		try {
			List<Object> origin = dao.queryAllInterestDoaminForUser(uid,start,length);
			return origin;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
}
