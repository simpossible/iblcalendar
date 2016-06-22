package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
				
		return "IBLLogin";
	}
	
	
	@RequestMapping(value = "/login_param", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loginwithparam(HttpServletRequest request) {
		System.out.println(request.getParameter("uid"));
		System.out.println(request.getParameter("passwd"));
		
		
		Map<String,Object> map = new HashMap<String,Object>();  
        map.put("code", true); 
        System.out.println(map);
		return map;
	}
	
}
