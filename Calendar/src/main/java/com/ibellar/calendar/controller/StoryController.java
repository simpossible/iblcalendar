package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ibellar.calendar.entity.Story;
import com.ibellar.calendar.service.StoryService;

@Controller
public class StoryController {

	@Autowired
	private StoryService storyService;

	@RequestMapping(value = "/Story/myStorys", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMyAllCreateStory(HttpServletRequest request) {// 获取我创建所有的story
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number)IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (token == null || uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				List<Story> list = storyService.getStoryOfUser(uid, start, length);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("result", list);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@RequestMapping(value = "/story/myCreateNumber", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMyAllCreateStoryNumber(HttpServletRequest request) {
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number)IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (token == null || uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				Integer number = storyService.getNumberOfStoryOfUser(uid);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("result", number);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		Gson gson = new Gson();
		return gson.toJson(map);
	}
	
	@RequestMapping(value = "/story/hotHistory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHotStory(HttpServletRequest request) {
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				List<Story> list = storyService.gethotStory(start, length);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("result", list);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			return new Gson().toJson(map);
			// TODO: handle exception
		}
		return new Gson().toJson(map);
	}
	
	@RequestMapping(value = "/story/createStory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String savaStory(@ModelAttribute("story")Story story, HttpServletRequest request,HttpServletResponse response) {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number)IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (story.getStoryName() == null|| story.getHistoryId() == 0 || story.getStoryDate() == 100000000) {
				throw new IBLException(IBLErrorCode.ILLEGAL_PARAM);
			}
			story.setCreateTime(IBLDateUtil.currentTimeMillis());
			story.setCreatorId(uid);
			storyService.addStory(story);
			map.put("code", IBLErrorCode.ALL_OK);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			return new Gson().toJson(map);
		}
		Gson gson = new Gson();
		return gson.toJson(map);
	}
	// 获取热门的hitsory 在用户没有登录的时候


	
}


