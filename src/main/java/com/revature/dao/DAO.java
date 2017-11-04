package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;

public interface DAO {
	
	//Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	
	//Read
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	public List<ScrumBoardLane> getScrumBoardLanes();
	public ScrumBoardStory getScrumBoardStoryById(int id);
	public ScrumBoardTask getScrumBoardTaskById(int id);
	
	//Update
	public ScrumUser updateScrumUser(ScrumUser su);
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
	public ScrumBoardStory updateScrumBoardStory(ScrumBoardStory story);
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task);
	
	//Delete

}
