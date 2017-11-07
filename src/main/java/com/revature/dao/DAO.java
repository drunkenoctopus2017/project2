package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumUser;
import com.revature.model.UserBoardDTO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;

public interface DAO {
	
	//Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	public ScrumBoardStory createNewStory(ScrumBoardStory s);
	public ScrumBoardTask createNewScrumBoardTask(ScrumBoardTask task);
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
	
	//Update
	public ScrumUser updateScrumUser(ScrumUser su);
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
	public ScrumBoardStory updateScrumBoardStory(ScrumBoardStory story);
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task);
	
	//Delete
	public void deleteScrumBoard(ScrumBoard sb);
}
