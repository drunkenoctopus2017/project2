package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspects")
public class ScrumAspects {

	private static final Logger log = LogManager.getRootLogger();
	
	@Before("execution(* *handle*(..))")
	public void beforeCtrlLog(JoinPoint jp) {
		
		log.info("executing: " + jp.getSignature());
		System.out.println("EXECUTING: " + jp.getSignature());
	}
	
	@Around("execution(* getScrum*(..))")
	public void aroundDaoMethods(JoinPoint jp) {
		log.info("executing: " + jp.getSignature());
		System.out.println("EXECUTING: " + jp.getSignature());
	}
}
