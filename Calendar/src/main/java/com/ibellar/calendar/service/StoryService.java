package com.ibellar.calendar.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.dao.StoryDao;
import com.ibellar.calendar.entity.Attention;
import com.ibellar.calendar.entity.Domain;
import com.ibellar.calendar.entity.History;
import com.ibellar.calendar.entity.IBLUser;
import com.ibellar.calendar.entity.Story;

@Transactional
@EnableTransactionManagement
@Service
public class StoryService {

	@Autowired
	private StoryDao storydao;

	public void addStory(Story story) throws IBLException {

		try {
			storydao.saveStory(story);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	// 判断是否已经有同名的类
	public boolean isHistoryHaveStoryWithName(Integer hid, String name) throws IBLException {

		try {

			Story st = storydao.queryStoryWithHistorydAndName(hid, name);
			if (st == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	public Story getHistoryHaveStoryWithName(Integer hid, String name) throws IBLException {
		try {

			return storydao.queryStoryWithHistorydAndName(hid, name);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	public Story getStoryWithStoryId(Integer sid) throws IBLException {

		try {
			return storydao.queryStoryWithId(sid);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	public List<Story> getStoryInHistoryId(Integer hid, Integer start, Integer length) throws IBLException {

		try {
			return storydao.queryStoryInHistoryId(hid, start, length);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	public Integer getNumberOfStoryInHistory(Integer hid) throws IBLException {

		try {
			return storydao.qureyNumberOfStoryInHistoryId(hid);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	public List<Story> getStoryOfUser(Integer uid, Integer start, Integer length) throws IBLException {

		try {
			return storydao.queryStoryOfUser(uid, start, length);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

	public Integer getNumberOfStoryOfUser(Integer uid) throws IBLException {
		try {
			return storydao.queryNumberOfStoryOfUser(uid);
		} catch (Exception e) {
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}

	}

	public List<HashMap> getMyStorys(Integer start, Integer length, Integer uid) throws IBLException {
		try {

			List<Object> origin;
			if (uid>0) {
				origin = storydao.queryAttentionStory(start, length, uid);
			}else {
				origin = storydao.queryStoryOrderByHotdegree(start, length);
			}
			List<HashMap> result = new ArrayList<HashMap>();

			for (int i = 0; i < origin.size(); i++) {

				Object[] a = (Object[]) origin.get(i);
				Story story = (Story) a[0];
				History his = (History) a[1];
				Domain dom = (Domain) a[2];
				
				

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("storyId", story.getStoryId());
				map.put("storyText", story.getStoryText());
				map.put("storyName", story.getStoryName());
				map.put("storyTime", story.getStoryDate());
				map.put("createTime", story.getCreateTime());
				map.put("historyId", his.getHistoryId());
				map.put("historyName", his.getHistoryName());
				map.put("domainId", dom.getDomainId());
				map.put("domainName", dom.getDomainName());
				
				IBLUser user;
				if (a.length >=4) {
					user = (IBLUser)a[3];
					map.put("creatorName", user.getEmail());
				}
				result.add(map);
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IBLException(IBLErrorCode.DATABASE_ERROR);
			// TODO: handle exception
		}
	}

}
