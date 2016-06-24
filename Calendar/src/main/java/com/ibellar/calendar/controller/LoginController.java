package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.entity.IBLUser;
import com.ibellar.calendar.service.LoginServerce;

@Controller
public class LoginController {
	
	@Autowired
	private LoginServerce service;

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
				
		return "IBLLogin";
	}
	
	
	@RequestMapping(value = "/login_email_param", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loginwithparam(HttpServletRequest request) throws Exception {
		
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		try {
			IBLUser user = service.loginWithEmail(email, passwd);
			map.put("email", user.getEmail());
			map.put("nickName", user.getNickName());
			
		} catch (IBLException e) {
			// TODO: handle exception
		}
		 
        map.put("code", true); 
        System.out.println(map);
		return map;
	}
	
}
