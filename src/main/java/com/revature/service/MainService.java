package com.revature.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;
import com.revature.model.ScrumUsersBoards;
import com.revature.model.UserBoardDTO;
import com.revature.model.TaskStatusDTO;


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
	
	public List<ScrumBoardLane> getScrumBoardLanes() {
		return dao.getScrumBoardLanes();
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
		//TODO: explain why this is necessary! Even if you don't know why. SAY you don't know why! ;-)
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
	
	//--Quarantined-------------------------------------------------
	//DEBUG LATER.
	@Autowired
	private SessionFactory sessionFactory;
	
	public ScrumBoard addUserToBoard(UserBoardDTO ub) {
		//TODO: This is not the right way.
		//But it is currently, the ONLY way to get this to work.
		Session session = sessionFactory.getCurrentSession();
		ScrumUsersBoards userBoardJoin = new ScrumUsersBoards();
		userBoardJoin.setSbId(ub.getBoardId());
		userBoardJoin.setUserId(ub.getUserId());
		session.save(userBoardJoin);
		
		ScrumBoard sb = session.get(ScrumBoard.class, ub.getBoardId());
		return sb;
	}
	//--Quarantined-------------------------------------------------
	
	
	public ScrumBoardTask updateScrumBoardTaskStatus(TaskStatusDTO params) {
		ScrumBoardTask task = dao.getScrumBoardTaskById(params.id);
		task.setStatus(params.status);
		task = dao.updateScrumBoardTask(task);
		return task;
	}
}
