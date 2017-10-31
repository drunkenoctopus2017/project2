package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.ScrumUser;
import com.revature.service.MainService;


@RestController
public class LoginController {
	
	@Autowired
	private MainService service;
	
	/**
	 * Take the login credentials provided by the client and then compare them to the database.
	 * Authenticate the credentials and then return a valid ScrumUser to the client. 
	 * Otherwise an error should occur somehow.
	 * TODO: implement invalid login logic (client-side and server-side)
	 * 
	 * @param loginUserCredentials a ScrumUser object that contains the password and username to be checked against the database.
	 * @return ScrumUser matching the credentials provided
	 */
	@RequestMapping(value="/authenticateLogin", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumUser> handleTodo(@RequestBody ScrumUser loginUserCredentials, HttpServletRequest request) {
		
		ScrumUser su = service.getScrumUserByUsernameAndPassword(loginUserCredentials);

		if (su != null) {
			//save the authenticated user in session so that 
			//the client isn't constantly sending private info to the server...
			request.getSession().setAttribute("ScrumUser", su);
			
			return new ResponseEntity<ScrumUser>(su, HttpStatus.OK);
		} else {
			//TODO return something else...
			return null;
		}
	}
	
	/**
	 * Does this do the work our try/catch logic used to???
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		System.out.println(e.getMessage());
		return new ResponseEntity<Exception>(e, HttpStatus.CONFLICT);
	}
}
