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

import com.yahoo.petermwenda83.contoller.student.Activity;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ActivityDAO extends DBConnectDAO implements SchoolActivityDAO {
	private static ActivityDAO activityDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static ActivityDAO getInstance(){
		
		if(activityDAO == null){
			activityDAO = new ActivityDAO();		
		}
		return activityDAO;
	}
	
	/**
	 * 
	 */
	public ActivityDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public ActivityDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolActivityDAO#getStudent(java.lang.String)
	 */
	public Activity getStudent(String studentuuid) {
		 ResultSet rset = null;
		 Activity activity = null;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Activities"
						+ " WHERE StudentUuid =?;");
  		){
	        	 pstmt.setString(1, studentuuid); 
		         rset = pstmt.executeQuery();
		     while(rset.next()){
		
		    	 activity  = beanProcessor.toBean(rset,Activity.class);
		   }
	        	
	        	
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent with studentuuid: "+studentuuid);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e)); 
       
		 }
		 
		
		
		return activity;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolActivityDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	public boolean putStudent(Activity activity) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Activities" 
			        		+"(Uuid, StudentUuid, Activity) VALUES (?,?,?);");
    		){
			   
	            pstmt.setString(1, activity.getUuid());
	            pstmt.setString(2, activity.getStudentUuid());
	            pstmt.setString(3, activity.getActivity());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+activity);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e)); 
              success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolActivityDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Activity, java.lang.String)
	 */
	public boolean editStudent(Activity activity, String studentuuid) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Activities SET Activity=?"
    			+ "WHERE StudentUuid = ?;");
    	) {
	           
	            pstmt.setString(1, activity.getActivity());
	            pstmt.setString(2, activity.getStudentUuid());
	           
	            pstmt.executeUpdate();

    } catch (SQLException e) {
        logger.error("SQL Exception when updating editStudent " + activity);
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e)); 
        success = false;
    } 
    		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolActivityDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	public boolean deleteStudent(Activity activity) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_Activities"
	         	      		+ " WHERE StudentUuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, activity.getStudentUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting activity : " +activity);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolActivityDAO#getAllActivity()
	 */
	@Override
	public List<Activity> getAllActivity() {
		List<Activity> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Activities;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, Activity.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all Activity");
	    logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

}
