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
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.student.House;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class HouseDAO extends DBConnectDAO implements SchoolHouseDAO {
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


	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolHouseDAO#getStudent(java.lang.String)
	 */
	public House getStudent(String studentuuid) {
		House house = null;
		ResultSet rset = null;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_House"
						+ " WHERE StudentUuid =?;");
		){
			  pstmt.setString(1, studentuuid); 
		         rset = pstmt.executeQuery();
		     while(rset.next()){
		    	 house  = beanProcessor.toBean(rset,House.class);
		   }
	        	
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent with studentuuid: "+studentuuid);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
    
		 }
		 
		
		
		return house;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolHouseDAO#putStudent(com.yahoo.petermwenda83.contoller.student.House)
	 */
	public boolean putStudent(House house) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_House" 
			        		+"(Uuid, StudentUuid, Housename) VALUES (?,?,?);");
  		){
			   
	            pstmt.setString(1, house.getUuid());
	            pstmt.setString(2, house.getStudentUuid());
	            pstmt.setString(3, house.getHousename());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+house);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolHouseDAO#editStudent(com.yahoo.petermwenda83.contoller.student.House, java.lang.String)
	 */
	public boolean editStudent(House house, String studentuuid) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
  	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_House SET Housename=? "
  			+ "WHERE StudentUuid = ?;");
  	) {           			 	            
	            pstmt.setString(1, house.getHousename());
	            pstmt.setString(2, house.getStudentUuid());
	           
	            pstmt.executeUpdate();

  } catch (SQLException e) {
      logger.error("SQL Exception when updating editStudent " + house);
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e));
      success = false;
  } 
  		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolHouseDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.House)
	 */
	@Override
	public boolean deleteStudent(House house) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_House"
	         	      		+ " WHERE StudentUuid =?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, house.getStudentUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting house : " +house);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolHouseDAO#getAllHouse()
	 */
	public List<House> getAllHouse() {
		List<House> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_House;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, House.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all House");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

}
