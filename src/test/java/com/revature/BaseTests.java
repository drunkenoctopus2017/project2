package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.service.MainService;


@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({locations="classpath:applicationContext.xml"})
public class BaseTests {
	
	@Autowired
	MainService service;
	
	@Test
	public void helloWorld() {
		assertNotNull("Hello World!", new String("Hello World"));
	}
	
	@Test
	public void testLanes() {
		
		assertEquals(6, service.getScrumBoardLanes().size());
		
		
	}
}
