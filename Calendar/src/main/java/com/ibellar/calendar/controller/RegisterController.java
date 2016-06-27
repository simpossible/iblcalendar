package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.entity.IBLUser;
import com.ibellar.calendar.service.IBLUserService;

@Controller
public class RegisterController {

	@Autowired
	private IBLUserService service;
	
	@RequestMapping(value = "/registerpage", method = RequestMethod.GET)
	public String registerPage() {
		return "Register";
	}	
	
	
	@RequestMapping(value = "/register_email_param",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> registeWithParam(@ModelAttribute("user")IBLUser user) throws IBLException {

		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			service.registerUserWithEmail(user);
			map.put("result", "succ");
		} catch (IBLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
