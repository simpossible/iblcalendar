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
import com.ibellar.calendar.entity.Agree;
import com.ibellar.calendar.entity.Collection;
import com.ibellar.calendar.entity.CollectionFolder;
import com.ibellar.calendar.entity.Story;
import com.ibellar.calendar.service.CollectionService;

@Controller
public class CollectionController {

	//Todo 收藏夹 功能
	@Autowired
	private CollectionService service;
	@RequestMapping(value = "/collection/collection", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String collectin(HttpServletRequest request){
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer storyId = Integer.parseInt(request.getParameter("storyId"));
		Map<String, Object> map = new HashMap<String, Object>();

		Collection collect = new Collection();
		collect.setUid(uid);
		collect.setStoryId(storyId);
		try {
			if (token == null || uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.addCollection(collect);
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
	
	@RequestMapping(value = "/collection/collection", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String cancelCollection(HttpServletRequest request){
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();
		Integer storyId = Integer.parseInt(request.getParameter("storyId"));
		Map<String, Object> map = new HashMap<String, Object>();

		Collection collect = new Collection();
		collect.setUid(uid);
		collect.setStoryId(storyId);
		try {
			if (token == null || uid == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.deleteCollection(collect);
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
	
	@RequestMapping(value = "/collection/collection", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String myCollection(HttpServletRequest request) {
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();

		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object> list = service.getAllCollectionForUser(uid,start,length);
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
	
//收藏夹相关
	
	//创建收藏夹
	@RequestMapping(value = "/collection/createfolder", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String createCollectionFolder(@ModelAttribute("folder")CollectionFolder folder,HttpServletRequest request){
		String token = request.getParameter(IBLDefine.Token_key);
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (token == null || folder.getUid() == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.addCollectionFolder(folder);;
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
	
	
	//删除收藏夹
	@RequestMapping(value = "/collection/deletefolder", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteCollectionFolder(@ModelAttribute("folder")CollectionFolder folder,HttpServletRequest request) {
		String token = request.getParameter(IBLDefine.Token_key);
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (token == null || folder.getUid() == 0) {
				throw new IBLException(IBLErrorCode.AUTHORY_EOORY);
			} else {
				service.deleteCollectionFolder(folder);
				map.put("code", IBLErrorCode.ALL_OK);
			}
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			// TODO: handle exception
		}
		return new Gson().toJson(map);
	}
	
	//获取所有的收藏夹
	@RequestMapping(value = "/collection/folders", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAllFolders(HttpServletRequest request) {
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();

		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<Object> list = service.getAllFolderForUid(uid, start, length);
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
	
	//这个收藏夹 所有的收藏
	@RequestMapping(value = "/collection/storyinfolder", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAllstoryInFolders(HttpServletRequest request) {
		String token = request.getParameter(IBLDefine.Token_key);
		Integer uid = ((Number) IBLTokenUtil.getvalueFromTokenWithKey(token, IBLDefine.Uid_Key)).intValue();

		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));

		Integer folderId = Integer.parseInt(request.getParameter("length"));
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			List<Object> list = service.getAllCollectInFolder(folderId, start, length);
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
