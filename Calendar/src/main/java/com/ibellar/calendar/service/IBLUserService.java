package com.ibellar.calendar.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.IBLUserDao;
import com.ibellar.calendar.entity.IBLUser;

@Transactional
@EnableTransactionManagement
@Service
public class IBLUserService {

	@Autowired
	private IBLUserDao dao;
	
	
	public IBLUser loginWithEmail(String email, String password) throws Exception {

		if (!this.isEmailRight(email)) {
			throw new IBLException(IBLErrorCode.ACCOUNT_FORMAT_ERROR);
		}
		IBLUser u = dao.quearyUserWithEmail(email);

		if (u != null) {
			if (u.getPasswd().equals(password)) {
				return u;
			} else {
				throw new IBLException(IBLErrorCode.ACCOUNT_PASS_EROOR);
			}
		}
		throw new IBLException(IBLErrorCode.ACCOUNT_DOES_NOT_EXIST);
	}


	
	
	/***
	 * 注册
	 * @param u ibluser 对象
	 * @throws IBLException 
	 */
	public void registerUserWithEmail(IBLUser u) throws IBLException {
		String email = u.getEmail();
		if(!this.isEmailRight(email)) {
			throw new IBLException(IBLErrorCode.ACCOUNT_FORMAT_ERROR);
		}
		
		IBLUser resultUser = dao.quearyUserWithEmail(email);
		if (resultUser == null) {
			dao.saveUser(u);
		}else {
			throw new IBLException(IBLErrorCode.ACCOUNT_ALREADY_EXIST);
		}
	}
	
	
	public boolean isEmailAccountExist(String email) throws IBLException {
		if(!this.isEmailRight(email)) {
			throw new IBLException(IBLErrorCode.ACCOUNT_FORMAT_ERROR);
		}
		
		IBLUser resultUser = dao.quearyUserWithEmail(email);
		if (resultUser == null) {
			return false;
		}else {
			return true;
		}
	}
	
	//通过token 获得用户
	public  IBLUser getUserWithToken(String token) throws IBLException {
		IBLUser u = dao.quearyUserWithToken(token);
		if (u != null) {
			return u;
		} else {
			throw new IBLException(IBLErrorCode.ACCOUNT_DOES_NOT_EXIST);
		}
	}
	
	//通过帐号获得用户
	public  IBLUser getUserWithEmail(String email) throws IBLException {
		if (!this.isEmailRight(email)) {
			throw new IBLException(IBLErrorCode.ACCOUNT_FORMAT_ERROR);
		}
		IBLUser u = dao.quearyUserWithEmail(email);

		if (u != null) {
				return u;
			} else {
				throw new IBLException(IBLErrorCode.ACCOUNT_DOES_NOT_EXIST);
			}
		
	}
	
	public void updateUser(IBLUser u) throws IBLException {
		try {
			dao.upDateUser(u);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
		}
	}
	
	
	
	/***
	 *  邮件地址合法性检查
	 * @param email
	 * @return
	 */
	private boolean isEmailRight(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher m = p.matcher(email);
		
		boolean b = m.matches();
		return b;
	}
}
