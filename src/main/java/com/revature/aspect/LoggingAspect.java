package com.revature.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	
	private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("within(com.revature.controller.*)")
	public void logControllerMethods(JoinPoint joinPoint) {
		if (log.isInfoEnabled() && joinPoint != null)
			log.info(String.format("%s INVOKED %s", joinPoint.getTarget(), joinPoint.getSignature()));
	}
	
	@Before("within(com.revature.service.*)")
	public void logServiceMethods(JoinPoint joinPoint) {
		if (log.isInfoEnabled() && joinPoint != null)
			log.info(String.format("%s INVOKED %s", joinPoint.getTarget(), joinPoint.getSignature()));
	}
	
	
	@AfterThrowing(pointcut="execution(* login(..))", throwing="exception")
	public void postMappingExceptions(JoinPoint joinPoint, Exception exception) {
		if (log.isWarnEnabled() && joinPoint != null && exception != null)
			log.warn(String .format("%s INVOKED %s THROWING %s, %s", joinPoint.getTarget(), joinPoint.getSignature(), exception.getClass(), exception));
	}
	
	@AfterThrowing(pointcut="execution(* getUserById(..))", throwing="exception")
	public void getMappingExceptions(JoinPoint joinPoint, Exception exception) {
		if (log.isWarnEnabled() && joinPoint != null && exception != null)
			log.warn(String .format("%s INVOKED %s THROWING %s, %s", joinPoint.getTarget(), joinPoint.getSignature(), exception.getClass(), exception));
	}

}
