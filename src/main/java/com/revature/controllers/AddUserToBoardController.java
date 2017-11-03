package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.ScrumUser;
import com.revature.service.MainService;

@RestController
public class AddUserToBoardController {

	@Autowired
	private MainService service;
	
	@RequestMapping(value="/getAllExistingUsers")
	public ResponseEntity<List<ScrumUser>> getAllExistingUsers() {
		List<ScrumUser> users = service.getAllUsers();
		return new ResponseEntity<List<ScrumUser>>(users, HttpStatus.OK); //200
	}
}
