package com.ibellar.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.AttentionDao;
import com.ibellar.calendar.entity.Attention;
import com.ibellar.calendar.entity.History;

@Transactional
@EnableTransactionManagement
@Service
public class AttentiontionService {
	@Autowired
	private AttentionDao attentionDao;

	public void addAttention(Attention attention) throws IBLException {

		try {
			attentionDao.saveAttention(attention);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}

	public Attention getAttentionWithHistoryAndUser(Integer hid, Integer uid) throws IBLException {

		try {
			return attentionDao.queryAttentionWithHistoryAndUser(hid, uid);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}

	public boolean isUserAttentionTheHistory(Integer uid, Integer hid) throws IBLException {

		try {
			Attention atten = attentionDao.queryAttentionWithHistoryAndUser(hid, uid);
			if (atten == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}

	public List<Attention> getAttentionWithUid(Integer uid, Integer start, Integer length) throws IBLException {

		try {
			return attentionDao.getAttentionWithUid(uid, start, length);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}

	public List<Attention> getAttentionWithHistoryId(Integer hid, Integer start, Integer length) throws IBLException {

		try {
			return attentionDao.getAttentionWithHistoryId(hid, start, length);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public void cancelAttentionWithHidAndUid(Integer uid,Integer hid) throws IBLException {
		try {
			attentionDao.deleteAttentionWithUidAndHid(hid, uid);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	public Integer getAttentionNumberForUid(Integer uid) throws IBLException {
		try {
			return attentionDao.QueryAttentionNumberOfUser(uid);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}
	
	public List<History> getattenionsHistory(Integer uid,Integer start,Integer length) throws IBLException {
		try {
			return attentionDao.queryAttenionsHistory(uid, start, length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}
}
