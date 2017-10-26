package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.models.ScrumUser;

public class Main {
	public static void main(String[] args) {
		//Patrick Onion pusername password role=2 email=jprunyan
		int id = 1;
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sf.openSession();
		
		ScrumUser su = (ScrumUser) session.get(ScrumUser.class, id);
        session.close();
        
        System.out.println(su.toString());
	}
}
