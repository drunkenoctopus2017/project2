package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;
import com.revature.model.ScrumUserRole;
import com.revature.service.MainService;

import junit.framework.Assert;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class BaseTests {
	
	/*
	public ScrumBoard addUserToBoard(UserBoardDTO ub);
	//Read
	public List<ScrumUser> getAllUsers();
	public List<ScrumBoard> getAllScrumBoards();
	public List<ScrumBoard> getScrumBoardsByUserId(int userId);
	public ScrumUser getScrumUserById(int userId);
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	public List<ScrumBoardLane> getScrumBoardLanes();
	public ScrumBoardStory getScrumBoardStoryById(int id);	
	public ScrumBoardTask getScrumBoardTaskById(int id);
	public ScrumBoard getScrumBoardById(int boardId);
	 */
	@Autowired 
	private DAO dao;
	
	@Autowired
	private MainService service;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreateNewScrumBoard() {
		ScrumBoard board = new ScrumBoard("TestBoard", new Date(), 14);
		ScrumBoard returnedBoard = dao.createNewScrumBoard(board);
		Assert.assertTrue(board.getName() == returnedBoard.getName() && board.getStartDate() == returnedBoard.getStartDate() && board.getDuration() == returnedBoard.getDuration()  );
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreateNewStory() {
	//	public ScrumBoardStory(ScrumBoard scrumBoard, 
		//String description, int points, int laneId) {
		ScrumBoard board = new ScrumBoard("TestBoard", new Date(), 14);
		ScrumBoardStory story = new ScrumBoardStory(board, "testing...", 5, 1);
		ScrumBoardStory returnedStory = dao.createNewStory(story);
		Assert.assertTrue(story.getScrumBoard() == returnedStory.getScrumBoard() &&
						  story.getdescription() == returnedStory.getdescription());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreateNewScrumBoardTask() {
		ScrumBoard board = new ScrumBoard("TestBoard", new Date(), 14);
		ScrumBoardStory story = new ScrumBoardStory(board, "testing...", 5, 1);
		ScrumBoardTask task = new ScrumBoardTask("Test Task", story);
		ScrumBoardTask returnedTask = dao.createNewScrumBoardTask(task);
		
		Assert.assertTrue(returnedTask.getDescription() == task.getDescription());
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testGetScrumBoardById() {
		ScrumBoard board = new ScrumBoard();
		
		board = dao.getScrumBoardById(1);
		Assert.assertEquals(1, board.getId());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetScrumUserByUsername() {
		//public ScrumUser getScrumUserByUsername(ScrumUser user);
		ScrumUser user = new ScrumUser("busername", "p4ssword");
		ScrumUser newUser = dao.getScrumUserByUsername(user);
		
		Assert.assertTrue(newUser.getUsername().equals("busername"));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetScrumBoardStoryById() {
		ScrumBoardStory story = new ScrumBoardStory();
		story = dao.getScrumBoardStoryById(1);
		Assert.assertEquals(1, story.getId());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetScrumBoardTaskById() {
		ScrumBoardTask task = new ScrumBoardTask();
		task = dao.getScrumBoardTaskById(-46);
		Assert.assertEquals(-46, task.getId());
	}

	
	@Test
	public void testLanes() {
		assertEquals(6, service.getScrumBoardLanes().size());
	}
	

}
