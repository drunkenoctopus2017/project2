package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.models.ScrumUser;
import com.revature.service.AppService;

@Controller
public class LoginCtrl {
	
	@Autowired
	private AppService service;
	
	@Autowired
	private ApplicationContext ac;
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("login");
		
		service = (AppService) ac.getBean("AppService");
		ScrumUser su = new ScrumUser();
		su.setUserId(21);
		
		su = service.getScrumUserById(su);
		
		System.out.println("user: " + su);
		
		return "/resources/Login/LoginPage.html";
	}

}
