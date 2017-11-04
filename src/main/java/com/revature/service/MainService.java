package com.revature.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumUser;
import com.revature.model.UserBoardDTO;

@Service(value="MainService") //will be applied as a bean, and used with the transactionManager when needed
@Transactional
public class MainService {

	@Autowired
	private DAO dao;
	
	public ScrumUser getScrumUserByUsernameAndPassword(ScrumUser su) {
		ScrumUser user = dao.getScrumUserByUsername(su);
		if(su.getUsername().equals(user.getUsername()) && su.getPassword().equals(su.getPassword())) {
			return user;
		}
		return null;
	}
	
	public ScrumBoard createNewScrumBoard(ScrumBoard sb, ScrumUser su) {
		sb.setUserId(su.getId());
		ArrayList<ScrumUser> initialUsers = new ArrayList<>();
		initialUsers.add(su);
		sb.setScrumUsers(initialUsers);
		sb = dao.createNewScrumBoard(sb);
		su.getScrumBoards().add(sb);
		su = dao.updateScrumUser(su);
		return sb;
	}
	

	public List<ScrumUser> getAllUsers(){
		return dao.getAllUsers();
	}

	public ScrumBoard editExistingScrumBoard(ScrumBoard sb, ScrumUser su) {
		sb.setUserId(su.getId());
		//this is necessary^
		sb = dao.updateScrumBoard(sb);
		//make sure the scrumboard object in the user's list is updated, dunno if it would automatically do this
		for(ScrumBoard osb : su.getScrumBoards()) {
			if(osb.getId() == sb.getId()) {
				su.getScrumBoards().remove(osb);
				su.getScrumBoards().add(sb);
				return sb;
			}
		}
		return sb;
	}
	
	
	
	public void addUserToBoard(UserBoardDTO ub) {
		ScrumUser user = dao.getScrumUserById(ub.getUserId());
		ScrumBoard board = dao.getScrumBoardById(ub.getBoardId());
		user.getScrumBoards().add(board);
		user = dao.updateScrumUser(user);
	}
}
