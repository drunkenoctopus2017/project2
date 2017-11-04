package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;

public interface DAO {
	
	//Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);

	public List<ScrumUser> getAllUsers();
	
<<<<<<< HEAD
	public ScrumUser getScrumUserById(int userId);
	
=======
	//Read
>>>>>>> d46aa66e2099e34b9682cd2bc8da0772fa8d8f1f
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	public List<ScrumBoardLane> getScrumBoardLanes();
	public ScrumBoardTask getScrumBoardTaskById(int id);
	
<<<<<<< HEAD
	public ScrumBoard getScrumBoardById(int boardId);
	
	public ScrumUser updateScrumUser(ScrumUser su);
	
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
=======
	//Update
	public ScrumUser updateScrumUser(ScrumUser su);
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task);
	
	//Delete
>>>>>>> d46aa66e2099e34b9682cd2bc8da0772fa8d8f1f

}
