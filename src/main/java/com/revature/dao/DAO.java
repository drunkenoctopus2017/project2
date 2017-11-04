package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumUser;

public interface DAO {
	
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	public ScrumBoardStory createNewStory(ScrumBoardStory s);
	public List<ScrumBoardLane> getScrumBoardLanes();
	public ScrumUser updateScrumUser(ScrumUser su);
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
}
