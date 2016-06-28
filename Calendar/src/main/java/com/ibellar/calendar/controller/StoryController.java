package com.ibellar.calendar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StoryController {
	
	@RequestMapping(value = "/hotStory", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getStoryOfuid(HttpServletRequest request) {
		
		
		return "";
	}
	
	//获取热门的hitsory 在用户没有登录的时候
	@RequestMapping(value = "/hotHistory", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHotHistory(HttpServletRequest request) {
		
		
		return "";
	}
}
