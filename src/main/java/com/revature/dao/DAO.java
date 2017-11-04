package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumUser;

public interface DAO {
	
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);

	public List<ScrumUser> getAllUsers();
	
	public ScrumUser getScrumUserById(int userId);
	
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	
	public ScrumBoard getScrumBoardById(int boardId);
	
	public ScrumUser updateScrumUser(ScrumUser su);
	
	public ScrumBoard updateScrumBoard(ScrumBoard sb);

}
