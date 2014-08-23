package org.sayantan.javabrains;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component // allows to create only one bean instance for Circle, instead of specifying explicitly in XML 
public class Circle implements Shape, ApplicationEventPublisherAware{
	private Point center ;
	private ApplicationEventPublisher publisher; 
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void draw() {
		//System.out.println("Circle Drawing"); alternatively
		//System.out.println("Center-Point is " + center.getX()+"," + center.getY());
		
		// using message-source
		System.out.println(this.messageSource.getMessage("drawing.circle", null, "Default draw message", null));
		// parameterized use of message-source
		System.out.println(this.messageSource.getMessage("drawing.point", new Object[] {center.getX(), center.getY()}, "Default point message", null));;
		
		// publish the DrawEvent here
		publisher.publishEvent(new DrawEvent(this));
	}
	public Point getCenter() {
		return center;
	}
	@Required
	
	//@Autowired
	//@Qualifier("circleRelated") 
	
	@Resource(name="pointC")
	public void setCenter(Point center) {
		this.center = center;
	}
	@PostConstruct
	public void initCircle(){
		System.out.println("initialization of circle");
	}
	@PreDestroy
	public void destroyCircle(){
		System.out.println("destruction of circle");
	}
//	public MessageSource getMessageSource() {
//		return messageSource;
//	}
//	public void setMessageSource(MessageSource messageSource) {
//		this.messageSource = messageSource;
//	}
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
				this.publisher  = publisher ;
	}
}
