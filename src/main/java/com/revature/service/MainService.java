package com.revature.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAO;
import com.revature.model.NewStoryDTO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;
import com.revature.model.StoryLaneDTO;
import com.revature.model.StoryTaskDTO;
import com.revature.model.TaskStatusDTO;
import com.revature.model.UserBoardDTO;


@Service(value="MainService") //will be applied as a bean, and used with the transactionManager when needed
@Transactional
public class MainService {
	@Autowired
	private DAO dao;
	
	//Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb, int userId) {
		sb.setUserId(userId);
		ScrumUser su = dao.getScrumUserById(userId);
		ArrayList<ScrumUser> initialUsers = new ArrayList<>();
		initialUsers.add(su);
		sb.setScrumUsers(initialUsers);
		sb = dao.createNewScrumBoard(sb);
		su.getScrumBoards().add(sb);
		//su = dao.updateScrumUser(su);
		return sb;
	}
	
	public ScrumBoardStory createNewStory(NewStoryDTO dto) {
		ScrumBoard sb = dao.getScrumBoardById(dto.sbId);
		ScrumBoardStory sbs = new ScrumBoardStory(sb, dto.description, dto.points, 10);
		sbs = dao.createNewStory(sbs);
		return sbs;
	}
	
	public ScrumBoardTask createNewScrumBoardTask(StoryTaskDTO newTask) {
		ScrumBoardStory story = dao.getScrumBoardStoryById(newTask.storyId);
		ScrumBoardTask task = new ScrumBoardTask(newTask.description, story);
		task = dao.createNewScrumBoardTask(task);
		return task;
	}
	
	//Read
	public List<ScrumUser> getAllUsers(){
		return dao.getAllUsers();
	}
	
	public List<ScrumBoard> getAllScrumBoards() {
		return dao.getAllScrumBoards();
	}
	
	public List<ScrumBoard> getScrumBoardsByUserId(int userId) {
		return dao.getScrumBoardsByUserId(userId);
	}
	
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
	
	//Update
	public ScrumBoard editExistingScrumBoard(ScrumBoard sb, int userId) {
		sb.setUserId(userId);
		//this is necessary^
		//TODO: explain why this is necessary! Even if you don't know why. SAY you don't know why! ;-)
		sb = dao.updateScrumBoard(sb);
		/*
		//make sure the scrumboard object in the user's list is updated, dunno if it would automatically do this
		for(ScrumBoard osb : su.getScrumBoards()) {
			if(osb.getId() == sb.getId()) {
				su.getScrumBoards().remove(osb);
				su.getScrumBoards().add(sb);
				return sb;
			}
		}
		*/
		return sb;
	}	
	
	public ScrumBoard addUserToBoard(UserBoardDTO ub) {
		//TODO: This is not the right way.
		//But it is currently, the ONLY way to get this to work.
		return dao.addUserToBoard(ub);
	}
	
	
	public ScrumBoardStory changeScrumBoardStoryLane(StoryLaneDTO params) {
		ScrumBoardStory story = dao.getScrumBoardStoryById(params.storyId);
		story.setLaneId(params.laneId);
		story.setFinishTime(new Date());
		story = dao.updateScrumBoardStory(story);
		return story;
	}
	
	public ScrumBoardTask updateScrumBoardTaskStatus(TaskStatusDTO params) {
		ScrumBoardTask task = dao.getScrumBoardTaskById(params.id);
		task.setStatus(params.status);
		task = dao.updateScrumBoardTask(task);
		return task;
	}
	
	public ScrumBoardStory updateScrumBoardStory(ScrumBoardStory sbs) {
		ScrumBoardStory story = dao.getScrumBoardStoryById(sbs.getId());
		story.setFinishTime(new Date());
		story.setdescription(sbs.getdescription());
		story.setPoints(sbs.getPoints());
		story = dao.updateScrumBoardStory(story);
		return story;
	}

	//Delete
	public ScrumBoard deleteExistingScrumBoard(ScrumBoard s) {
		ScrumBoard sb = dao.getScrumBoardById(s.getId());
		dao.deleteScrumBoard(sb);
		return sb;
	}
}