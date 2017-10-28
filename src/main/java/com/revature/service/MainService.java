package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DAOImpl;
import com.revature.model.ScrumUser;

@Service(value="MainService") //will be applied as a bean, and used with the transactionManager when needed
@Transactional
public class MainService {
	/*
	 * TODO: switch out for DAO interface. duh.
	 */
	@Autowired
	private DAOImpl dao;
	
	public ScrumUser getScrumUserByUsernameAndPassword(ScrumUser su) {
		return dao.getScrumUserByUsernameAndPassword(su);
	}
	
}
