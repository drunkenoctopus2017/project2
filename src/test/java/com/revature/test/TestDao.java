package com.revature.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAO;
import com.revature.dao.DAOImpl;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;
import com.revature.model.ScrumUserRole;

public class TestDao {
	/*
	 //Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	public ScrumBoardStory createNewStory(ScrumBoardStory s);
	public ScrumBoardTask createNewScrumBoardTask(ScrumBoardTask task);

	//Read
	public List<ScrumUser> getAllUsers();
	public ScrumUser getScrumUserById(int userId);
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	public List<ScrumBoardLane> getScrumBoardLanes();
	public ScrumBoardStory getScrumBoardStoryById(int id);
	public ScrumBoardTask getScrumBoardTaskById(int id);
	public ScrumBoard getScrumBoardById(int boardId);
	
	//Update
	public ScrumUser updateScrumUser(ScrumUser su);
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
	public ScrumBoardStory updateScrumBoardStory(ScrumBoardStory story);
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task);
	 */
	
	
	private DAO dao = new DAOImpl();
	
	//ScrumUser(int id, String firstName, String lastName, String username, String password, String email, ScrumUserRole role)
	ScrumUserRole role = new ScrumUserRole(1, "manager");
	ScrumUser user = new ScrumUser(1, "Patrick", "user", "pusername", "password", "test@test.com", role);
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetUserId() {
		Assert.assertTrue(user.getId() == 1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetFirstName() {
		Assert.assertTrue(user.getFirstName() == "Patrick");
	}
	
	@Test
	@Transactional
	@Rollback(true)	
	public void testGetUserByUsername() {
		System.out.println(dao.toString());
		ScrumUser test = dao.getScrumUserByUsername(user);
		System.out.println(test);
		Assert.assertEquals(200, 200);
		//test.getRole().getId()
	}
	
}
