package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;

public interface DAO {
	
	//Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);

	//Read
	public List<ScrumUser> getAllUsers();
	public ScrumUser getScrumUserById(int userId);
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	public List<ScrumBoardLane> getScrumBoardLanes();
	public ScrumBoardTask getScrumBoardTaskById(int id);
	public ScrumBoard getScrumBoardById(int boardId);
	//Update
	public ScrumUser updateScrumUser(ScrumUser su);
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task);
	
	//Delete

}
