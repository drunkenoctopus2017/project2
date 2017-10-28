package com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
 * This is the base controller for handling URL get requests.
 * It will handle directing these requests to the SPA url.
 * 
 * @author jpwru
 *
 */
public class BaseController {
	
	@RequestMapping("/main")
	public ModelAndView mainRequest() {
		System.out.println("BaseController redirects /main to app.html");
		return new ModelAndView("/resources/app.html");
	}
		
	/*
	 * consumes=MediaType.APPLICATION_JSON_VALUE
	 * produces=
	 * 
	 * remember to get the faster xml dependencies
	 * 
	 * 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e) {
		return new ReponseEntity<Execption>(e, HttpStatus.CONFLICT);
	}
	*/
}
