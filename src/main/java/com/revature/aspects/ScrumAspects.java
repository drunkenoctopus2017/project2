package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspects")
public class ScrumAspects {

	private static final Logger log = LogManager.getRootLogger();
	
	@Before("execution(* getScrumUserByUsername*(..))")
	public void beforeGetting(JoinPoint jp) {
		log.info("BEFORE: " + jp.getKind() + " - " + jp.getSignature());

	}
	
	@AfterReturning("execution(* getScrumUserByUsername*(..))")
	public void afterGetSucceeds(JoinPoint jp) {
		log.info("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}
	
	@AfterThrowing("execution(* getScrumUserByUsername*(..))")
	public void afterGetFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}
}

