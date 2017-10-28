package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.ScrumUser;

@Transactional
@Repository	//Makes it a bean, and used with the TransactionManager
public class DAOImpl {
	/*
	 * <!-- create a transaction manager and give the sessionfactory -->
	 * <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
	 * 	<property name="sessionFactory" ref="mySessionFactory"></property>
	 * </bean>
	 */
	@Autowired
	private SessionFactory sessionFactory;
	public ScrumUser getScrumUserByUsernameAndPassword(ScrumUser su) {
		Session session = sessionFactory.getCurrentSession();
		//insert hibernate query here.
		
		//Dummy response
		return new ScrumUser(1, "Patrick", "Runyan", su.getUsername(), su.getPassword(), "jpwrunyan@hotmail.com", 100);
	}
}
