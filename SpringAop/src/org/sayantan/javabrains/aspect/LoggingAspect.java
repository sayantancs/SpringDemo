package org.sayantan.javabrains.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	// The following executes advice before all getNames, irrespective of the object which calls it
	// @Before("execution(public String getName())")
	
	// In this case, advice is restricted to only circle's getname.
	//@Before("execution(public String org.sayantan.javabrains.model.Circle.getName())")
	
	// The following is wild-card for any getters
	@Before("execution( * get*())")
	//@Before("allGetters()") 			// <= using pointcut
	public void loggingAdvice(){
		System.out.println("Advice run, Get method called");
	}
	
	// modified logging advice to handle the joint-points
	//	@Before("allCircleMethods()")
	//	public void loggingAdvice(JoinPoint joinPoint){
	//		System.out.println(joinPoint.toString());
	//	}
	
	//@Before("execution( * get*())") 	// <= this place-holders are called Pointcuts, can be dynamically assigned
	//@Before ("allGetters()")			// <= using pointcut
	//	public void secondAdvice(){
	//		System.out.println("Second Advice executed, checking if called with Get method.");
	//	}
	
	//@Pointcut("execution( * get*())")  // <= definition of pointcut, which can be used with previous @Before annotations
	//public void allGetters() {}
	
	//@Pointcut ("whithin(org.sayantan.javabrains.model.Circle)")
	//public void allCircleMethods() {}

}
