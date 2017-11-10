package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.ScrumUser;
import com.revature.model.UserBoardDTO;
import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
import com.revature.model.ScrumBoardStory;
import com.revature.model.ScrumBoardTask;

@Transactional
@Repository	//Makes it a bean, and used with the TransactionManager
public class DAOImpl implements DAO {
	/*
	 * <!-- create a transaction manager and give the sessionfactory -->
	 * <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
	 * 	<property name="sessionFactory" ref="mySessionFactory"></property>
	 * </bean>
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	//Create
	public ScrumBoard createNewScrumBoard(ScrumBoard sb) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(sb);
		sb.setId(id);
		return sb;
	}
	
	public ScrumBoardStory createNewStory(ScrumBoardStory s) {
		System.out.println("createNewStory()");
		Session session = sessionFactory.getCurrentSession();
		System.out.println("about to save");
		Integer id = (Integer) session.save(s);
		System.out.println("saved with id " + id);
		s.setId(id);
		System.out.println("id was set");
		return s;
	}
	
	public ScrumBoardTask createNewScrumBoardTask(ScrumBoardTask task) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(task);
		//Maybe instead of setting the id (which might be dangerous???),
		//we should session.get(id) to return the newly created task...?
		task.setId(id);
		return task;
	}
	
	public ScrumBoard addUserToBoard(UserBoardDTO ub) {
		Session session = sessionFactory.getCurrentSession();
		ScrumBoard sb = session.get(ScrumBoard.class, ub.getBoardId());
		ScrumUser su = session.get(ScrumUser.class, ub.getUserId());
		sb.getScrumUsers().add(su);
		su.getScrumBoards().add(sb);
		session.save(sb);
		session.save(su);
		sb.getScrumUsers().size(); //to avoid lazy instantiation error
		
		/*
		ScrumUsersBoards userBoardJoin = new ScrumUsersBoards();
		userBoardJoin.setSbId(ub.getBoardId());
		userBoardJoin.setUserId(ub.getUserId());
		session.save(userBoardJoin);
		
		ScrumBoard sb = session.get(ScrumBoard.class, ub.getBoardId());
		*/
		return sb;
	}
	
	//Read
	public List<ScrumUser> getAllUsers(){
		Session session = sessionFactory.getCurrentSession();
		List<ScrumUser> users = new ArrayList<>();
		Query query = session.createQuery("from ScrumUser");
		users = query.getResultList();
		return users;
	}
	
	public List<ScrumBoard> getAllScrumBoards() {
		Session session = sessionFactory.getCurrentSession();
		List<ScrumBoard> allBoards = new ArrayList<>();
		Query query = session.createQuery("from ScrumBoard");
		allBoards = query.getResultList();
		return allBoards;
	}
	
	public List<ScrumBoard> getScrumBoardsByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumUser su = session.get(ScrumUser.class, userId);
		List<ScrumBoard> boards = su.getScrumBoards();
		//boards.size();
		for (ScrumBoard board : boards) {
			board.getScrumUsers().size();
		}
		return boards;
	}

	public ScrumUser getScrumUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumUser user = session.get(ScrumUser.class, userId);
		return user;
	}

	public ScrumUser getScrumUserByUsername(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumUser where username = :username");
		query.setParameter("username", su.getUsername());
		ScrumUser user = (ScrumUser) query.getSingleResult();
		return user;
	}
	
	public ScrumBoard getScrumBoardById(int boardId) {
		Session session = sessionFactory.getCurrentSession();
		ScrumBoard board = session.get(ScrumBoard.class, boardId);
		return board;
	}

	public List<ScrumBoardLane> getScrumBoardLanes() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumBoardLane");
		List<ScrumBoardLane> results = (List<ScrumBoardLane>) query.getResultList();
		System.err.println("test: " + results);
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
	
	//Delete
	@Override
	public void deleteScrumBoard(ScrumBoard sb) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		sb = session.load(ScrumBoard.class, sb.getId());
		//remove the user board associations in the database manually to avoid violating join table foreign key constraint when i delete
		List<ScrumUser> users = sb.getScrumUsers();
		for(ScrumUser u : users) {
			u.getScrumBoards().remove(sb);
			session.update(u);
		}
		session.delete(sb);
	}
}
