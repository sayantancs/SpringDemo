package org.sayantan.javabrains;

import org.sayantan.javabrains.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		// ShapeService shapeService = (ShapeService) ctx.getBean("shapeService");
		ShapeService shapeService = ctx.getBean("shapeService", ShapeService.class);
		System.out.println(shapeService.getTriangle().getName());
		System.out.println(shapeService.getCircle().getName()); 
	}

}
 