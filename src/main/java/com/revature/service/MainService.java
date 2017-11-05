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
		ScrumBoardStory sbs = new ScrumBoardStory();
		sbs.setPoints(dto.points);
		System.out.println("1" + sbs);
		sbs.setdescription(dto.description);
		System.out.println("2" +sbs);
		ScrumBoard sb = dao.getScrumBoardById(dto.sbId);
		System.out.println("3" +sb);
		sbs.setScrumBoard(sb);
		System.out.println("4" +sbs);
		sb.getStories().add(sbs);
		System.out.println("5" +sb);
		
		sbs = dao.createNewStory(sbs);
		System.out.println("6" +sbs);
		sb = dao.updateScrumBoard(sb);
		System.out.println("7" +sb);
		return sbs;
	}
	
	
}
