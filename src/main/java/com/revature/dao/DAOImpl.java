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
import com.revature.model.ScrumBoardStory;
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
	
	public ScrumBoard createNewScrumBoard(ScrumBoard sb) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(sb);
		sb.setId(id);
		return sb;
	}
	
	public ScrumBoardTask createNewScrumBoardTask(ScrumBoardTask task) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(task);
		//Maybe instead of setting the id (which might be dangerous???),
		//we should session.get(id) to return the newly created task...?
		task.setId(id);
		return task;
	}
	
	public ScrumUser getScrumUserByUsername(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumUser where username = :username");
		query.setParameter("username", su.getUsername());
		ScrumUser user = (ScrumUser) query.getSingleResult();
		return user;
	}
	
	public List<ScrumBoardLane> getScrumBoardLanes() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumBoardLane");
		List<ScrumBoardLane> results = (List<ScrumBoardLane>) query.getResultList();
		return results;
	}
	
	public ScrumBoardStory getScrumBoardStoryById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ScrumBoardStory.class, id);
	}
	
	public ScrumBoardTask getScrumBoardTaskById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ScrumBoardTask.class, id);
	}
	
	//Update
	public ScrumUser updateScrumUser(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("--------------------------- about to merge");
		su = (ScrumUser) session.merge(su);
		System.out.println("--------------------------- merged!");
		return su;
	}
	public List<ScrumUser> getAllUsers(){
		Session session = sessionFactory.getCurrentSession();
		List<ScrumUser> users = new ArrayList<>();
		Query query = session.createQuery("from ScrumUser");
		users = query.getResultList();
		return users;
	}

	public ScrumUser getScrumUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumUser user = session.get(ScrumUser.class, userId);
		return user;
	}

	public ScrumBoard getScrumBoardById(int boardId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumBoard board = session.get(ScrumBoard.class, boardId);
		return board;
	}
	
	public ScrumBoard updateScrumBoard(ScrumBoard sb) {
		Session session = sessionFactory.getCurrentSession();
		sb = (ScrumBoard) session.merge(sb);
		return sb;
	}
	
	public ScrumBoardStory updateScrumBoardStory(ScrumBoardStory story) {
		Session session = sessionFactory.getCurrentSession();
		story = (ScrumBoardStory) session.merge(story);
		return story;
	}
	
	public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task) {
		Session session = sessionFactory.getCurrentSession();
		//Wait, what? This works????
		//Why does get update the data? This is weird.
		ScrumBoardTask updateTask = session.get(ScrumBoardTask.class, task.getId());
		return updateTask;
	}
}
