package com.revature.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	
	private static Logger log = LogManager.getLogger(LoggingAspect.class);
	
	@Before("within(com.revature.controller.*)")
	public void logControllerMethods(JoinPoint joinPoint) {
		log.debug(joinPoint.getTarget() + " invoked " + joinPoint.getSignature());
	}
	
	@Before("within(com.revature.service.*)")
	public void logServiceMethods(JoinPoint joinPoint) {
		log.debug(joinPoint.getTarget() + " invoked " + joinPoint.getSignature());
	}

}
