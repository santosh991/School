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
package com.yahoo.petermwenda83.dao.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.student.Location;
import com.yahoo.petermwenda83.dao.DBConnectDAO;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class LocationDAO extends DBConnectDAO implements SchoolLocationDAO {
	private static LocationDAO locationDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static LocationDAO getInstance(){
		
		if(locationDAO == null){
			locationDAO = new LocationDAO();		
		}
		return locationDAO;
	}
	
	/**
	 * 
	 */
	public LocationDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public LocationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolLocationDAO#getStudent(java.lang.String)
	 */
	@Override
	public Location getStudent(String studentuuid) {
		Location location =null;
		ResultSet rset = null;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Location"
						+ " WHERE StudentUuid =?;");
		){
			     pstmt.setString(1, studentuuid); 
		         rset = pstmt.executeQuery();
		         while(rset.next()){
		    	 location  = beanProcessor.toBean(rset,Location.class);
		         }
		         
		 }catch(SQLException e){
			  logger.error("SQL Exception trying to put Student with studentuuid: "+studentuuid);
              logger.error(ExceptionUtils.getStackTrace(e)); 
              System.out.println(ExceptionUtils.getStackTrace(e)); 
            
		 }
		 
		
		
		return location;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolLocationDAO#putStudent(com.yahoo.petermwenda83.bean.student.Location)
	 */
	@Override
	public boolean putStudent(Location location) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Location" 
			        		+"(Uuid, StudentUuid, County,Location,Sublocation) VALUES (?,?,?,?,?);");
		){
			   
	            pstmt.setString(1, location.getUuid());
	            pstmt.setString(2, location.getStudentUuid());
	            pstmt.setString(3, location.getCounty());
	            pstmt.setString(4, location.getLocation());
	            pstmt.setString(5, location.getSublocation());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+location);
     logger.error(ExceptionUtils.getStackTrace(e)); 
     success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolLocationDAO#editStudent(com.yahoo.petermwenda83.bean.student.Location, java.lang.String)
	 */
	@Override
	public boolean editStudent(Location location, String studentuuid) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
  	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Location SET County=?, "
  			+ " Location =?, Sublocation =? WHERE StudentUuid = ?;");
  	) {
	           
	            pstmt.setString(1, location.getCounty());
	            pstmt.setString(2, location.getLocation());
	            pstmt.setString(3, location.getSublocation());
	            pstmt.setString(4, studentuuid);
	           
	            pstmt.executeUpdate();

  } catch (SQLException e) {
      logger.error("SQL Exception when updating editStudent " + location);
      logger.error(ExceptionUtils.getStackTrace(e));
      
      success = false;
  } 
  		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolLocationDAO#deleteStudent(com.yahoo.petermwenda83.bean.student.Location)
	 */
	@Override
	public boolean deleteStudent(Location location) {
		 boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_Location"
	         	      		+ " WHERE StudentUuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, location.getStudentUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting location : " +location);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolLocationDAO#getAllLocation()
	 */
	@Override
	public List<Location> getAllLocation() {
		List<Location> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Location;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, Location.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all Location");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

}
