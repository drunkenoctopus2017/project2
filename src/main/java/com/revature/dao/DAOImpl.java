package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardTask;
import com.revature.model.ScrumUser;

@Transactional
@Repository	//Makes it a bean, and used with the TransactionManager
public class DAOImpl implements DAO{
	/*
	 * <!-- create a transaction manager and give the sessionfactory -->
	 * <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
	 * 	<property name="sessionFactory" ref="mySessionFactory"></property>
	 * </bean>
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
<<<<<<< HEAD
	@Override
=======
	//Create
>>>>>>> d46aa66e2099e34b9682cd2bc8da0772fa8d8f1f
	public ScrumBoard createNewScrumBoard(ScrumBoard sb) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(sb);
		sb.setId(id);
		return sb;
	}
	
<<<<<<< HEAD
	@Override
	public ScrumBoard updateScrumBoard(ScrumBoard sb) {
		Session session = sessionFactory.getCurrentSession();
		sb = (ScrumBoard) session.merge(sb);
		return sb;
	}
	@Override
=======
	//Read
>>>>>>> d46aa66e2099e34b9682cd2bc8da0772fa8d8f1f
	public ScrumUser getScrumUserByUsername(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumUser where username = :username");
		query.setParameter("username", su.getUsername());
		ScrumUser user = (ScrumUser) query.getSingleResult();
		return user;
	}
<<<<<<< HEAD
	@Override
=======
	
	public List<ScrumBoardLane> getScrumBoardLanes() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumBoardLane");
		List<ScrumBoardLane> results = (List<ScrumBoardLane>) query.getResultList();
		return results;
	}
	
	public ScrumBoardTask getScrumBoardTaskById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ScrumBoardTask.class, id);
	}
	
	//Update
>>>>>>> d46aa66e2099e34b9682cd2bc8da0772fa8d8f1f
	public ScrumUser updateScrumUser(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		su = (ScrumUser) session.merge(su);
		return su;
	}
<<<<<<< HEAD
	@Override
	public List<ScrumUser> getAllUsers(){
		Session session = sessionFactory.getCurrentSession();
		List<ScrumUser> users = new ArrayList<>();
		Query query = session.createQuery("from ScrumUser");
		users = query.getResultList();
		return users;
	}

	@Override
	public ScrumUser getScrumUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumUser user = session.get(ScrumUser.class, userId);
		return user;
	}

	@Override
	public ScrumBoard getScrumBoardById(int boardId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumBoard board = session.get(ScrumBoard.class, boardId);
		return board;
=======
	
	public ScrumBoard updateScrumBoard(ScrumBoard sb) {
		Session session = sessionFactory.getCurrentSession();
		sb = (ScrumBoard) session.merge(sb);
		return sb;
	}
	
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task) {
		Session session = sessionFactory.getCurrentSession();
		ScrumBoardTask updateTask = session.get(ScrumBoardTask.class, task.getId());
		return task;
>>>>>>> d46aa66e2099e34b9682cd2bc8da0772fa8d8f1f
	}
}
