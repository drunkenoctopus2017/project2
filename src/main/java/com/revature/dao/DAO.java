package com.revature.dao;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumUser;

public interface DAO {
	
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	
	public ScrumUser updateScrumUser(ScrumUser su);

}
