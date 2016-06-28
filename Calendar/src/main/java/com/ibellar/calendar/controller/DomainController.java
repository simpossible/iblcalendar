package com.ibellar.calendar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibellar.calendar.service.DomainService;

@Controller
public class DomainController {

	@Autowired
	private DomainService domainService;
	
	@RequestMapping(value = "/domain/createdomain", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String createDomain(HttpServletRequest request) {
		
		
		return "";
	}
	
}
