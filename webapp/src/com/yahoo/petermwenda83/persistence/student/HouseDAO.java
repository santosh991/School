/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### SchoolAccount Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.student.StudentHouse;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class HouseDAO extends GenericDAO implements SchoolHouseDAO {
	private static HouseDAO houseDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static HouseDAO getInstance(){
		
		if(houseDAO == null){
			houseDAO = new HouseDAO();		
		}
		return houseDAO;
	}
	
	/**
	 * 
	 */
	public HouseDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public HouseDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

/*
	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolHouseDAO#getStudent(java.lang.String)
	 *//*
	@Override
	public StudentHouse getStudent(String studentuuid) {
		StudentHouse studentHouse = null;
		ResultSet rset = null;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_House"
						+ " WHERE StudentUuid =?;");
		){
			  pstmt.setString(1, studentuuid); 
		         rset = pstmt.executeQuery();
		     while(rset.next()){
		    	 studentHouse  = beanProcessor.toBean(rset,StudentHouse.class);
		   }
	        	
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent with studentuuid: "+studentuuid);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
    
		 }
		 
		
		
		return studentHouse;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolHouseDAO#putStudent(com.yahoo.petermwenda83.bean.student.StudentHouse)
	 *//*
	@Override
	public boolean putStudent(StudentHouse studentHouse) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_House" 
			        		+"(Uuid, StudentUuid, Housename) VALUES (?,?,?);");
  		){
			   
	            pstmt.setString(1, studentHouse.getUuid());
	            pstmt.setString(2, studentHouse.getStudentUuid());
	            pstmt.setString(3, studentHouse.getHousename());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+studentHouse);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		 
		
		
		return success;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolHouseDAO#editStudent(com.yahoo.petermwenda83.bean.student.StudentHouse, java.lang.String)
	 *//*
	@Override
	public boolean editStudent(StudentHouse studentHouse, String studentuuid) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
  	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_House SET Housename=? "
  			+ "WHERE StudentUuid = ?;");
  	) {           			 	            
	            pstmt.setString(1, studentHouse.getHousename());
	            pstmt.setString(2, studentHouse.getStudentUuid());
	           
	            pstmt.executeUpdate();

  } catch (SQLException e) {
      logger.error("SQL Exception when updating editStudent " + studentHouse);
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e));
      success = false;
  } 
  		
		return success;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolHouseDAO#deleteStudent(com.yahoo.petermwenda83.bean.student.StudentHouse)
	 *//*
	@Override
	public boolean deleteStudent(StudentHouse studentHouse) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_House"
	         	      		+ " WHERE StudentUuid =?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, studentHouse.getStudentUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting house : " +studentHouse);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolHouseDAO#getAllHouse()
	 *//*
	@Override
	public List<StudentHouse> getAllHouse() {
		List<StudentHouse> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_House;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, StudentHouse.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all StudentHouse");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}*/

	@Override
	public StudentHouse getHouse(String studentuuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putHouse(StudentHouse studentHouse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatetHouse(StudentHouse studentHouse, String studentuuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteHouse(StudentHouse studentHouse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StudentHouse> getHouseList() {
		// TODO Auto-generated method stub
		return null;
	}

}
