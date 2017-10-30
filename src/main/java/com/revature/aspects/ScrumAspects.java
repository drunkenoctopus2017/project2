package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspects")
public class ScrumAspects {

	static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	@Before("execution(* *Controller(..))")
	public void beforeCtrlLog(JoinPoint jp) {
		log.debug("executing: " + jp.getSignature());
	}
}
