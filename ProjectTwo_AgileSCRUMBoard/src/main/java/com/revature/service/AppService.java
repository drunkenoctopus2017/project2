package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.DaoImpl;
import com.revature.models.ScrumUser;

@Service(value="AppService")
@Transactional(value="transactionManager")
public class AppService {
	
	@Autowired
	DaoImpl dao;
	public ScrumUser getScrumUserById(ScrumUser user) {
		return dao.getScrumUserById(user);
	}

}
