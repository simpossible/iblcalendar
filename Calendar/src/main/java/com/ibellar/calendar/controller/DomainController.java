package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ibellar.calendar.IBLErrorCode;
import com.ibellar.calendar.IBLException;
import com.ibellar.calendar.entity.Domain;
import com.ibellar.calendar.service.DomainService;

@Controller
public class DomainController {

	@Autowired
	private DomainService domainService;

	@RequestMapping(value = "/domain/createdomain", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String createDomain(HttpServletRequest request) {

		String domainName = request.getParameter("domain_name");
		String domainsumm = request.getParameter("domain_desc");
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (this.isDomainNameIllegle(domainName) && this.isDomaindesc(domainsumm)) {

				Domain domain = domainService.getDomainWithName(domainName);
				
				if (domain == null) {
					domain = new Domain();
					domain.setDomainSummary(domainsumm);
					domain.setDomainName(domainName);
					domainService.addDomain(domain);
					map.put("code", IBLErrorCode.ALL_OK);
					map.put("domain", domain);
				}
			} else {
				map.put("code", IBLErrorCode.DOMAIN_ALREADY_EXIST);
				map.put("error", IBLErrorCode.codeToString(IBLErrorCode.DOMAIN_ALREADY_EXIST));

			}
			Gson json = new Gson();
			String result = json.toJson(map);
			System.out.println(result);
			return result;
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			return new Gson().toJson(map);
		}

	}

	@RequestMapping(value = "/domain/allDoaminCount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String AllDoaminNumber(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer number = domainService.getAllDomainCount();
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("number", number);
			Gson json = new Gson();
			String reslt = json.toJson(map);
			System.out.println(reslt);
			return reslt;
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			return new Gson().toJson(map);
		}

	}

	@RequestMapping(value = "/domain/domainWithRange", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getDomainWithRange(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer start = Integer.parseInt(request.getParameter("start"));
		;
		Integer length = Integer.parseInt(request.getParameter("length"));

		try {
			List<Domain> list = domainService.getDomains(start, length);
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("domains", list);
			Gson json = new Gson();
			String reslt = json.toJson(map);
			System.out.println(reslt);
			return reslt;
		} catch (IBLException e) {
			map.put("code", e.getErrorcode());
			map.put("error", e.getErrorMessage());
			return new Gson().toJson(map);
		}

	}

	/// 判断域名的合法性
	private boolean isDomainNameIllegle(String domainName) {
		return true;
	}
	
	private boolean isDomaindesc(String desc) {
		return true;
	}

}
