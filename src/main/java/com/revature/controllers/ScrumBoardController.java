package com.revature.controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;
import com.revature.model.StoryLaneDTO;
import com.revature.model.StoryTaskDTO;
import com.revature.model.TaskStatusDTO;
import com.revature.service.MainService;

@RestController
public class ScrumBoardController {
	
	@Autowired
	private MainService service;
	
	//Create
	@RequestMapping(value="/createNewScrumBoard", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoard> createNewScrumBoard(@RequestBody ScrumBoard newScrumBoard, HttpServletRequest request) {
		int userId = (Integer) request.getSession().getAttribute("userId");
		ScrumBoard sb = service.createNewScrumBoard(newScrumBoard, userId);
		return new ResponseEntity<ScrumBoard>(sb, HttpStatus.OK); //200
	}
	
	@RequestMapping(value="/createNewScrumBoardTask", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoardTask> createNewScrumBoardTask(@RequestBody StoryTaskDTO newTask, HttpServletRequest request) {
		ScrumBoardTask task = service.createNewScrumBoardTask(newTask);
		return new ResponseEntity<ScrumBoardTask>(task, HttpStatus.OK); //200
	}
	
	//Read
	@RequestMapping(value="/getScrumBoards", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScrumBoard>> getScrumBoards(HttpServletRequest request) {
		int userId = (Integer) request.getSession().getAttribute("userId");
		List<ScrumBoard> boards = service.getScrumBoardsByUserId(userId);
		return new ResponseEntity<List<ScrumBoard>>(boards, HttpStatus.OK); //200
	}
	
	
	@RequestMapping(value="/getScrumBoardLanes", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScrumBoardLane>> getScrumBoardLanes() {
		List<ScrumBoardLane> sbLanes = service.getScrumBoardLanes();
		return new ResponseEntity<List<ScrumBoardLane>>(sbLanes, HttpStatus.OK); //200
	}
	
	//Update
	@RequestMapping(value="/editExistingScrumBoard", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoard> editExistingScrumBoard(@RequestBody ScrumBoard newScrumBoard, HttpServletRequest request) {
		//ScrumUser su = (ScrumUser) request.getSession().getAttribute("user");
		int userId = (Integer) request.getSession().getAttribute("userId");
		ScrumBoard sb = service.editExistingScrumBoard(newScrumBoard, userId);
		return new ResponseEntity<ScrumBoard>(sb, HttpStatus.OK); //200
	}
	
	@RequestMapping(value="/updateScrumBoardStory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoardStory> updateScrumBoardStory(@RequestBody ScrumBoardStory sbs){
		ScrumBoardStory story = service.updateScrumBoardStory(sbs);
		return new ResponseEntity<ScrumBoardStory>(story, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateScrumBoardTask", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoardTask> updateScrumBoardTask(@RequestBody TaskStatusDTO params, HttpServletRequest request) {
		ScrumBoardTask task = service.updateScrumBoardTaskStatus(params);
		return new ResponseEntity<ScrumBoardTask>(task, HttpStatus.OK); //200
	}
	
	@RequestMapping(value="/changeScrumBoardStoryLane", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumBoardStory> changeScrumBoardStoryLane(@RequestBody StoryLaneDTO params, HttpServletRequest request) {
		ScrumBoardStory story = service.changeScrumBoardStoryLane(params);
		return new ResponseEntity<ScrumBoardStory>(story, HttpStatus.OK); //200
	}
	
	//Delete
	@RequestMapping(value="/deleteExistingScrumBoard", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScrumUser> deleteExistingScrumBoard(@RequestBody ScrumBoard sb, HttpSession session) {
		System.out.println("CONTROLLER - This is the 'board' that is passed from JS side:"+sb);
		ScrumBoard deletedSb = service.deleteExistingScrumBoard(sb);
		ScrumUser sua = (ScrumUser) session.getAttribute("user");
		// doesn't remove the board from the HttpSession user, oh well
		// returns the session's user because why not (a.k.a. don't know how to get it to work without needing to return something)
		return new ResponseEntity<ScrumUser>(sua, HttpStatus.OK);
	}
	
	/**
	 * Respond to an invalid credentials attempt and return 401.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Exception> handleException(NoResultException e){
		e.printStackTrace();
		return new ResponseEntity<Exception>(e, HttpStatus.UNAUTHORIZED);
	}
}
