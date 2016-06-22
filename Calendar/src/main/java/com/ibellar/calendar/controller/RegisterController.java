package com.ibellar.calendar.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

	@RequestMapping(value = "/registerpage", method = RequestMethod.GET)
	public String registerPage() {
		return "Register";
	}	
	
	
	@RequestMapping(value = "/register_param",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> registeWithParam(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		
		return null;
	}
}
