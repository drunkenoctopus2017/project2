package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.ScrumBoard;
import com.revature.model.ScrumBoardLane;
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
	
	public ScrumUser getScrumUserByUsername(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumUser where username = :username");
		query.setParameter("username", su.getUsername());
		ScrumUser user = (ScrumUser)query.getSingleResult();
		return user;
	}
	
	public List<ScrumBoardLane> getScrumBoardLanes() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ScrumBoardLane");
		List<ScrumBoardLane> results = (List<ScrumBoardLane>) query.getResultList();
		return results;
	}
	
	public ScrumUser updateScrumUser(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		su = (ScrumUser) session.merge(su);
		return su;
	}
}
