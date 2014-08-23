package org.sayantan.javabrains;

import java.sql.SQLException;

import org.sayantan.javabrains.dao.JdbcDaoImpl;
import org.sayantan.javabrains.dao.SimpleJdbcDaoImpl;
import org.sayantan.javabrains.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Circle circle = new JdbcDaoImpl().getCircle(1); -- non spring way
		
		// Spring way
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
		//Circle circle = dao.getCircle(1);
		//System.out.println(circle.getName());
		System.out.println("Number of circles = "+dao.getCircleCounts());
		System.out.println("Name of circle = "+dao.getCircleName(1));
		System.out.println("Name of circle from the object = "+dao.getCircleforId(1).getName());
		System.out.println("Circle objects total size = "+dao.getAllCircles().size());
		// insertion
		//dao.insertCircle(new Circle(4, "fourth Circle"));
		System.out.println("Circle objects total size after insertion = "+dao.getAllCircles().size());
		
		// table creation - dml
		//dao.createTriangleTable();
    	//SimpleJdbcDaoImpl sdao = ctx.getBean("simpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);
		//System.out.println("Number of circles from new DAO= "+sdao.getCircleCounts());
	}

}
