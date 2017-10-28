package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
public class Aspects {

	@Before("execution(* home*(..))")
	public void beforeHome(JoinPoint jp) {
		System.out.println("before method: " + jp.getSignature());
	}
	
	@Before("execution(* login*(..))")
	public void beforeLogin(JoinPoint jp) {
		System.out.println("before method: " + jp.getSignature());
	}
	
}
