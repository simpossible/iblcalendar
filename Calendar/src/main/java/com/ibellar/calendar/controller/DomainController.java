package com.ibellar.calendar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ibellar.calendar.IBLErrorCode;
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
		Map<String,Object> map = new HashMap<String	,Object>();
		if (this.isDomainNameIllegle(domainName)) {

			Domain domain = domainService.getDomainWithName(domainName);
			if (domain == null) {
				domain = new Domain();
				domain.setDomainName(domainName);
				domainService.addDomain(domain);
				map.put("code", IBLErrorCode.ALL_OK);
				map.put("domain", domain);
			}else {
				map.put("code", IBLErrorCode.DOMAIN_ALREADY_EXIST);
				map.put("error", IBLErrorCode.codeToString(IBLErrorCode.DOMAIN_ALREADY_EXIST));
			}
		}

		Gson json = new Gson();
		String result = json.toJson(map);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/domain/allDoaminCount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String AllDoaminNumber(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String, Object>();
			Integer number = domainService.getAllDomainCount();
			map.put("code", IBLErrorCode.ALL_OK);
			map.put("number", number);
			Gson json = new Gson();
			String reslt = json.toJson(map);
			System.out.println(reslt);
			return reslt;
	}

	
	///判断域名的合法性
	private boolean isDomainNameIllegle(String domainName) {
		return true;
	}

}
