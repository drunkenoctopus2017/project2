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
import com.revature.model.ScrumUser;
import com.revature.service.MainService;

@RestController
public class ScrumBoardController {
	
	@Autowired
	private MainService service;
	
	@RequestMapping(value="/createNewScrumBoard", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoard> createNewScrumBoard(@RequestBody ScrumBoard newScrumBoard, HttpServletRequest request) {
		ScrumUser su = (ScrumUser) request.getSession().getAttribute("user");
		ScrumBoard sb = service.createNewScrumBoard(newScrumBoard, su);
		return new ResponseEntity<ScrumBoard>(sb, HttpStatus.OK); //200
	}
	
	/**
	 * Respond to an invalid credentials attempt and return 401.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Exception> handleException(NoResultException e){
		return new ResponseEntity<Exception>(e, HttpStatus.UNAUTHORIZED);
	}
}
