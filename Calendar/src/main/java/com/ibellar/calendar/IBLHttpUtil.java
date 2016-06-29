package com.ibellar.calendar;

import javax.servlet.http.HttpServletRequest;

public class IBLHttpUtil {

	public static String getSenderOfRequest(HttpServletRequest request) {
	 return	request.getHeader("access_token");
	}
	
	public static boolean isLoginValid(HttpServletRequest request) {
		return true;
	}
}
