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
		return new ModelAndView("/resources/app.html");
	}
}
