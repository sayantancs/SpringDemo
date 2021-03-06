package org.sayantan.javabrains;

import java.util.Iterator;
import java.util.List;

// using these interfaces is too specific to spring, 
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// So, let's try w/o the interfaces
//public class Triangle implements InitializingBean, DisposableBean {
public class Triangle implements Shape {
	
	private Point pointA, pointB, pointC;
	public Point getPointA() {
		return pointA;
	}
	public void setPointA(Point pointA) { 
		this.pointA = pointA;
	}
	public Point getPointB() {
		return pointB;
	}
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	public Point getPointC() {
		return pointC;
	}
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
	/*
	private List <Point> points ;
	public List<Point> getPoints() {
		return points;
	}
	public void setPoints(List<Point> points) {
		this.points = points;
	}*/
	
	public void draw(){
	//	for (Point p : points){
	//		System.out.println("["+p.getX()+","+p.getY()+"]");
	//	}
		System.out.println("Drawing a triangle");
		System.out.println("point a=[" + getPointA().getX() +"," +getPointA().getY()  + "]");
		System.out.println("point b=[" + getPointB().getX() +"," +getPointB().getY()  + "]");
		System.out.println("point a=[" + getPointC().getX() +"," +getPointC().getY()  + "]");
	}
	
	/* when used with the interfaces for init and dispose
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Done with initializing beans for triangle.");
		
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" Destroying beans for triangle.");
	}*/

	// My own method to do some initialization
	public void myInit(){
		System.out.println("myInit gets called.");
	}
	public void myClean(){
		System.out.println("myClean gets called.");
	}
}
