package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAO;
import com.revature.model.NewStoryDTO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumUser;

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
	
	public ScrumBoardStory createNewStory(NewStoryDTO dto) {
		ScrumBoard sb = dao.getScrumBoardById(dto.sbId);
		ScrumBoardStory sbs = new ScrumBoardStory(sb, dto.description, dto.points, 10);
		sbs = dao.createNewStory(sbs);
		return sbs;
	}
	
	/*
	public ScrumBoardTask createNewScrumBoardTask(StoryTaskDTO newTask) {
		ScrumBoardStory story = dao.getScrumBoardStoryById(newTask.storyId);
		ScrumBoardTask task = new ScrumBoardTask(newTask.description, story);
		task = dao.createNewScrumBoardTask(task);
		return task;
	}
	 */
}
