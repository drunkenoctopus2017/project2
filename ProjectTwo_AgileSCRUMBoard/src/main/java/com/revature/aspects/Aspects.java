package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
public class Aspects {

	@Before("execution(public*(..))")
	public void printSomething(JoinPoint jp) {
		System.out.println("before method: " + jp.getSignature());
	}
	
}
