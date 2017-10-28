package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.ScrumUser;

@Repository
public class DaoImpl implements Dao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public ScrumUser getScrumUserById(ScrumUser user) {
		Session session = sessionFactory.getCurrentSession();
		ScrumUser scrumUser = (ScrumUser)session.get(ScrumUser.class, user.getUserId());
		return scrumUser;
	}

}
