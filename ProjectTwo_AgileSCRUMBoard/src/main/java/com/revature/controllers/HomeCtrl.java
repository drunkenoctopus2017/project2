package com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeCtrl {
	
	@RequestMapping("/")
	public String homeController() {
		System.out.println("home");
		
		return "/resources/index.html";
	}
}
