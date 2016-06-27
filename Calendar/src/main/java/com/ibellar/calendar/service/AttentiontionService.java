package com.ibellar.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.dao.AttentionDao;
import com.ibellar.calendar.entity.Attention;

@Transactional
@EnableTransactionManagement
@Service
public class AttentiontionService {
	@Autowired
	private AttentionDao attentionDao;
	
	
	public void addAttention(Attention attention) {
		attentionDao.saveAttention(attention);
	}
	
	public List<Attention> getAttentionWithUid(Integer uid,Integer start,Integer length) {
		return attentionDao.getAttentionWithUid(uid, start, length);
	}
	
	public List<Attention> getAttentionWithHistoryId(Integer hid,Integer start,Integer length) {
		return attentionDao.getAttentionWithHistoryId(hid, start, length);
	}
}
