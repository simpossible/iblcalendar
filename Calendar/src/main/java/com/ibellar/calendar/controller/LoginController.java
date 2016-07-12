package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ibellar.calendar.IBLDateUtil;
import com.ibellar.calendar.IBLDefine;
import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.IBLTokenUtil;
import com.ibellar.calendar.entity.IBLUser;
import com.ibellar.calendar.service.IBLUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IBLUserService service;

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
				
		return "IBLLogin";
	}
	
	
	@RequestMapping(value = "/login_email_param", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String loginwithparam(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws Exception {

		response.setHeader("Access-Control-Allow-Origin", "*");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		Map<String,Object> tokenMap = new HashMap<String,Object>(); 
		try {
			IBLUser user = service.loginWithEmail(email, passwd);
			map.put("user", user);
	        map.put("code", IBLErrorCode.ALL_OK); 
	    
	        tokenMap.put(IBLDefine.Account_key, user.getEmail());
	        tokenMap.put(IBLDefine.Uid_Key, user.getUid());
	        tokenMap.put(IBLDefine.Date_key, IBLDateUtil.currentTimeMillis());
	        
	        String token = IBLTokenUtil.encryptMap(tokenMap);
	        map.put(IBLDefine.Token_key, token);
	        session.setAttribute(IBLDefine.Account_key, user.getEmail());
	        
	              
		} catch (IBLException e) {
			// TODO: handle exception
	        map.put("code", e.getErrorcode());
	        map.put("error", e.getErrorMessage());
	        return new Gson().toJson(map);
		}
		
        Gson json = new Gson();
        String jj = json.toJson(map);
        System.out.println(jj);
		return jj;
	}
	
	
//	注册相关
	
	@RequestMapping(value = "/registerpage", method = RequestMethod.GET)
	public String registerPage() {
		return "Register";
	}	
	
	
	@RequestMapping(value = "/register_email_param",method = RequestMethod.POST)
	@ResponseBody
	public String registeWithParam(@ModelAttribute("user")IBLUser user) throws IBLException {

		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			service.registerUserWithEmail(user);
			map.put("code",IBLErrorCode.ALL_OK);
		} catch (IBLException e) {
			e.printStackTrace();
			map.put("error",e.getErrorMessage());
			return new Gson().toJson(map);
		}
		
		 return new Gson().toJson(map);
	}
}
