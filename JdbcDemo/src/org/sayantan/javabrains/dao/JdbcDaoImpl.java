package org.sayantan.javabrains.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.sayantan.javabrains.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;// = new JdbcTemplate();
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;// = new NamedParameterJdbcTemplate(dataSource);
	private SimpleJdbcDaoImpl simpleJdbcDaoImpl; 
	// method of getting circle object from db, w/o template
	// Most primitive way
	/*
	public Circle getCircle(int circleId) throws SQLException, 
												 IllegalAccessException,
												 InstantiationException,
												 ClassNotFoundException{
		Connection conn = null;
		try{
		// Non-spring way
		// String driver = "org.apache.derby.jdbc.ClientDriver";
		// Class.forName(driver).newInstance();
		// conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db");
		
		// The Spring way	
		conn = dataSource.getConnection();	
		PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
		ps.setInt(1, circleId);

		Circle circle = null;
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			circle = new Circle (circleId, rs.getString("name"));
		}
		
		rs.close(); ps.close();
		return circle;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			conn.close();		
		}
	}*/
	
	@SuppressWarnings("deprecation")
	public int getCircleCounts(){
		String sql = "select count(*) from circle";
		// The following is duplication of works, which was avoided as we modified
		// setDtaSource to take care of template instantiation
		//jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.queryForInt(sql);
	}
	
	// get name from sql using jdbc template
	public String getCircleName(int circleId){
		String sql = "select name from circle where id = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId} ,String.class);
	}
	
	// intelligent method to extract obejct from db using template and row-mapper
	private static final class CircleMapper implements RowMapper<Circle>{

		@Override
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Circle circle =new Circle(rs.getInt("id"),rs.getString("name"));
			
			return circle;
		}
	}
	public Circle getCircleforId(int id){
		String sql = "select * from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new CircleMapper());
	}
	
	// returing list of circles
	public List<Circle> getAllCircles(){
		String sql = "select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	// insertion
	/*public void insertCircle(Circle circle){ // w/o named paramater
		String sql = "insert into circle values (?, ?)" ;
		jdbcTemplate.update(sql, new Object [] {circle.getId(), circle.getName() }) ;
	}*/
	
	// insertion with named paramters
	public void insertCircle(Circle circle){ 
		String sql = "insert into circle values (:id, :name)" ;
		SqlParameterSource namedParams = new MapSqlParameterSource("id", circle.getId()).addValue("name", circle.getName());
		namedParamJdbcTemplate.update(sql, namedParams);
	}
	
	// creation
	public void createTriangleTable(){
		String sql = "create table triangle (id integer, name varchar(50))";
		jdbcTemplate.execute(sql);
	}
	
	 // getters and setters
	
	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		// Normal case, but this causes duplication of works
		//this.dataSource = dataSource;
		
		// Rather instantiate the jdbc template itself
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
}
