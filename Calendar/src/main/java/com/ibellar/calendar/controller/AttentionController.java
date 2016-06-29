package com.ibellar.calendar.controller;
//这个类 处理关注所需要的所有逻辑

import java.util.HashMap;
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
import com.ibellar.calendar.entity.Attention;
import com.ibellar.calendar.service.AttentiontionService;

@Controller
public class AttentionController {

	@Autowired
	private AttentiontionService attentionService;

	@RequestMapping(value = "/attention/attention", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String AttentionAHistory(@ModelAttribute("attention") Attention attention, HttpServletRequest request) {

		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = Integer.parseInt((String) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key));
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				attentionService.addAttention(attention);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("attention", attention);
			}

		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}

		return new Gson().toJson(map);
	}

	public String cancelAttention(HttpServletRequest request) {
		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = Integer.parseInt((String) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key));

		Integer historyId = Integer.parseInt(request.getParameter("historyId"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			attentionService.cancelAttentionWithHidAndUid(historyId, uid);
			map.put("code", IBLErrorCode.ALL_OK);
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
		}
		return new Gson().toJson(map);
	}

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
		}
		return new Gson().toJson(map);
	}
	
	public String attentionHistorys(HttpServletRequest request) {
		String token = request.getHeader(IBLDefine.Token_key);
		Integer uid = Integer.parseInt((String) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key));
		return "";
	}

}