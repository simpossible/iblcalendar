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
import com.ibellar.calendar.entity.Comment;
import com.ibellar.calendar.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService service;
	
	@RequestMapping(value = "/comment/comment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String comment(@ModelAttribute("comm")Comment comm,HttpServletRequest request){//添加一条评论
		
		String token = request.getParameter(IBLDefine.Token_key);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (token == null || comm.getUid() == 0 || comm.getStoryId() == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.addComment(comm);
				map.put("code", IBLErrorCode.ALL_OK);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		return new Gson().toJson(map);
	}
	
	@RequestMapping(value = "/comment/deletecomment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteComment(@ModelAttribute("comm")Comment comm,HttpServletRequest request){//删除一条评论
		String token = request.getParameter(IBLDefine.Token_key);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (token == null || comm.getUid() == 0 || comm.getStoryId() == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.deleteComment(comm);
				map.put("code", IBLErrorCode.ALL_OK);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		return new Gson().toJson(map);
	}
	
	
	@RequestMapping(value = "/comment/deletecomment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCommentsForStroy(HttpServletRequest request) {
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number)IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer storyId = Integer.parseInt(request.getParameter("storyId"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				List<HashMap> list = service.getCommentForStory(storyId, start, length);
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
