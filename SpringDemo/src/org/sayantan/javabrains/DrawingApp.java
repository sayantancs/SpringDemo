package org.sayantan.javabrains;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class DrawingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//	The following is not spring
		// Triangle triangle = new Triangle();
		
		// The following uses BeanFactory- 
		/*
		BeanFactory factory  = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		Triangle triangle = (Triangle)factory.getBean("triangle");
		*/
		
		// using application-context
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		//Triangle triangle = (Triangle)context.getBean("triangle");
		
		//using abstract application context
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		System.out.println("context loaded");
		context.registerShutdownHook();
		
		//System.out.println("Creating triangle");
		//Shape s = (Triangle)context.getBean("triangle");
		//s.draw();
		
		System.out.println("creating Circle");
		Shape s = (Circle)context.getBean("circle");
		s.draw();
		
		// printing greeting from myMessage.properties file and using application Context
		// This can also be done in the circle class itself, by creating an instance of messagesource
		//String greeting = context.getMessage("greeting", null, "Default Greeting", null);
		//System.out.println(greeting);
	}

}
