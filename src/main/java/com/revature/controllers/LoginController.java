package com.revature.controllers;

import javax.persistence.NoResultException;
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

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardTask;
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
	public ResponseEntity<ScrumUser> authenticateLogin(@RequestBody ScrumUser loginUserCredentials, HttpServletRequest request) {
		ScrumUser su = service.getScrumUserByUsernameAndPassword(loginUserCredentials);
		request.getSession().setAttribute("user", su);
		
		System.out.println(su.toString());
		

		ScrumBoard testBoard = su.getScrumBoards().get(0);
		System.out.println(testBoard.toString());
		System.out.println("test: " + testBoard.getStories());
		
		ScrumBoardTask testTask = testBoard.getStories().get(0).getTasks().get(0);
		System.out.println("testTask" + testTask.toString());
		
		return new ResponseEntity<ScrumUser>(su, HttpStatus.OK); //200
	}
	
	/**
	 * Respond to an invalid credentials attempt and return 401.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Exception> handleException(NoResultException e) {
		return new ResponseEntity<Exception>(e, HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * All other exceptions are caught here and return 409.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e) {
		e.printStackTrace(); //TODO wrap this in Aspect, send to Log on DB
		return new ResponseEntity<Exception>(e, HttpStatus.CONFLICT);
	}
}
