package com.revature.dao;

import java.util.List;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumUser;

public interface DAO {
	
	public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	
<<<<<<< HEAD
	public List<ScrumUser> getAllUsers();
=======
	public ScrumBoard updateScrumBoard(ScrumBoard sb);
>>>>>>> 7a9f60e2f012ff95ab9494a311f93d6397d0c044
	
	public ScrumUser getScrumUserByUsername(ScrumUser user);
	
	public ScrumUser updateScrumUser(ScrumUser su);
<<<<<<< HEAD
	
	
  
=======

>>>>>>> 7a9f60e2f012ff95ab9494a311f93d6397d0c044
}
