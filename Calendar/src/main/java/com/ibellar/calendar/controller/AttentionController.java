package com.ibellar.calendar.controller;
//这个类 处理关注所需要的所有逻辑

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
import com.ibellar.calendar.IBLTokenUtil;
import com.ibellar.calendar.entity.Attention;
import com.ibellar.calendar.entity.History;
import com.ibellar.calendar.service.AttentiontionService;

@Controller
public class AttentionController {

	@Autowired
	private AttentiontionService attentionService;

	@RequestMapping(value = "/attention/attention", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String AttentionAHistory(@ModelAttribute("attention") Attention attention, HttpServletRequest request) {

		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = ((Number)IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				attention.setUserId(uid);
				attention.setAttentionTime(IBLDateUtil.currentTimeMillis());
				attentionService.addAttention(attention);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("attention", attention);
			}

		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());

			return new Gson().toJson(map);
			// TODO: handle exception
		}

		return new Gson().toJson(map);
	}

	@RequestMapping(value = "/attention/cancelAttention", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cancelAttention(HttpServletRequest request) {
		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = ((Number)IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();

		Integer historyId = Integer.parseInt(request.getParameter("historyId"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			attentionService.cancelAttentionWithHidAndUid(historyId, uid);
			map.put("code", IBLErrorCode.ALL_OK);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());

			return new Gson().toJson(map);
		}
		return new Gson().toJson(map);
	}

	
	@RequestMapping(value = "/attention/attentionCount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attentionNumber(HttpServletRequest request) {
		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = Integer.parseInt((String) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key));

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer number = attentionService.getAttentionNumberForUid(uid);
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("result", number);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());// TODO: handle exception

			return new Gson().toJson(map);
		}
		return new Gson().toJson(map);
	}
	
	@RequestMapping(value = "/attention/attentions", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attentionHistorys(HttpServletRequest request) {
		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<History> result = attentionService.getattenionsHistory(uid, start, length);
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("result", result);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());// TODO: handle exception
			return new Gson().toJson(map);
		}
		return new Gson().toJson(map);
	}

}