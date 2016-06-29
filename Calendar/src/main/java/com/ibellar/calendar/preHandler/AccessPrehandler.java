package com.ibellar.calendar.preHandler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ibellar.calendar.IBLDefine;
import com.ibellar.calendar.IBLTokenUtil;

public class AccessPrehandler implements HandlerInterceptor {

	private ArrayList<String> whitList;

	public AccessPrehandler() {
		// TODO Auto-generated constructor stub
		super();
		System.out.println("handler");
		this.whitList = new ArrayList<String>();
		this.initialWhitename();
	}

	/***
	 *   初始化白名单
	 */
	private void initialWhitename() {
		this.whitList.add("/calendar/loginpage");
		this.whitList.add("/calendar/login_email_param");
		this.whitList.add("/calendar/registerpage");
		this.whitList.add("/calendar/register_email_param");
		this.whitList.add("/calendar/");
	}

	/***
	 * 检查uri 是否事白名单
	 * 
	 * @return
	 */
	private boolean isUriInwhiteList(String uri) {

		for (String string : whitList) {
			if (string.equals(uri)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("收到请求");
		String uri = request.getRequestURI();
		if (this.isUriInwhiteList(uri)) {
			System.out.println("放行请求");
			return true;
		} else {
			HttpSession session = request.getSession();
			String sessionAccount = (String) session.getAttribute(IBLDefine.Account_key);

			String accessToken = request.getHeader(IBLDefine.Token_key);

			String account = (String) IBLTokenUtil.getvalueFromTokenWithKey(accessToken, IBLDefine.Account_key);
			
			if (sessionAccount == null || account == null) {
				System.out.println("未授权的请求");
				return false;
			}
			if (account.equals(sessionAccount)) {
				System.out.println("放行请求");
				return true;
			} else {
				System.out.println("授权错误的请求");
				return false;
			}

		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Post-handle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("After completion handle");
	}
}
