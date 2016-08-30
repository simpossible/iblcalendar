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
import com.ibellar.calendar.IBLDefine;
import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.IBLTokenUtil;
import com.ibellar.calendar.entity.Domain;
import com.ibellar.calendar.entity.Interest;
import com.ibellar.calendar.entity.Story;
import com.ibellar.calendar.service.InterestService;

@Controller
public class InterestController {

	@Autowired
	private InterestService service;

	// 对一个领域感兴趣
	@RequestMapping(value = "/interest/interest", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String InterestDomain(HttpServletRequest request) {

		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer domainId = Integer.parseInt(request.getParameter("domainId"));
		Map<String, Object> map = new HashMap<String, Object>();

		Interest inter = new Interest();
		inter.setDomainId(domainId);
		inter.setUid(uid);
		try {
			if (token == null || uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.addInterest(inter);
				;
				map.put("code", IBLErrorCode.ALL_OK);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		return new Gson().toJson(map);
	}

	// 取消感兴趣
	@RequestMapping(value = "/interest/cancelinterest", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String DeInterestDomain(HttpServletRequest request) {

		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer domainId = Integer.parseInt(request.getParameter("domainId"));
		Map<String, Object> map = new HashMap<String, Object>();
		Interest inter = new Interest();
		inter.setDomainId(domainId);
		inter.setUid(uid);
		try {
			if (token == null || uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.deleteInterest(inter);
				map.put("code", IBLErrorCode.ALL_OK);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		return new Gson().toJson(map);
	}

	// 返回所有感兴趣的领域
	@RequestMapping(value = "/interest/allInterest", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String allInterestedDomain(HttpServletRequest request) {
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object> list = service.getAllInterestForUser(uid,start,length);
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

}
