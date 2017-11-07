package com.revature.controllers;

import java.util.List;

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
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumUser;
import com.revature.service.MainService;
import com.revature.model.NewStoryDTO;

@RestController
public class ScrumBoardStoryController {
	
	@Autowired
	private MainService service;
//	
//	@RequestMapping(value="/createNewStory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ScrumUser> createNewStory(@RequestBody ScrumBoardStory newStory, HttpServletRequest request){
//		ScrumUser su = (ScrumUser ) request.getSession().getAttribute("user");
//		List <ScrumBoard> boards = su.getScrumBoards();
//
//		return new ResponseEntity<ScrumUser>(su, HttpStatus.OK);
//	}
//	
	@RequestMapping(value="/createNewStory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoardStory> createNewStory(@RequestBody NewStoryDTO dto, HttpServletRequest request){
		System.out.println("create New Story");
		System.out.println(dto.toString());
		System.out.println(dto.sbId);
		ScrumBoardStory sbs = service.createNewStory(dto);
		
		return new ResponseEntity<ScrumBoardStory>(sbs, HttpStatus.OK);
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
