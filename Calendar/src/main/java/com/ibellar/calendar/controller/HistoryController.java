package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ibellar.calendar.IBLHttpUtil;
import com.ibellar.calendar.IBLTokenUtil;
import com.ibellar.calendar.entity.History;
import com.ibellar.calendar.entity.IBLUser;
import com.ibellar.calendar.service.HistoryService;
import com.ibellar.calendar.service.IBLUserService;

@Controller
public class HistoryController {
	@Autowired
	private HistoryService historyService;
	

	@RequestMapping(value = "/history/hotHistory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHotHistory(HttpServletRequest request) {

		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<History> list = historyService.getHotHistory(start, length);
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("historys", list);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
		}
		Gson json = new Gson();
		String result = json.toJson(map);

		return result;
	}

	@RequestMapping(value = "/history/createHistory", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String createHistory(@ModelAttribute("history") History history, HttpServletRequest request) {//这里不用传uid 传用户帐号就行


		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			String token = request.getHeader("access_token");
			Integer uid = Integer.parseInt((String) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key));
			
			if (uid != 0) {
				history.setCreatorId(uid);
				history.setCreateTime(IBLDateUtil.currentTimeMillis());
				historyService.addHistory(history);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("history", history);
			}else {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			}
				
		
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		Gson json = new Gson();
		String results = json.toJson(map);

		return results;
	}

	@RequestMapping(value = "/history/getHistorysWithUid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHistory(HttpServletRequest request) {
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Integer start = Integer.parseInt(request.getParameter("start"));
		;
		Integer length = Integer.parseInt(request.getParameter("length"));

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<History> result = historyService.getHistoryOfUser(uid, start, length);
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("historys", result);
		} catch (IBLException e) {

			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}

		Gson json = new Gson();
		String results = json.toJson(map);

		return results;
	}

	
	@RequestMapping(value = "/history/countHistoryInUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getHistoryNumberOfUid(HttpServletRequest request) {
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Integer count = historyService.getNumberOfHistoryOfUser(uid);
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("count", count);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}

		Gson json = new Gson();
		String results = json.toJson(map);

		return results;
	}
}
