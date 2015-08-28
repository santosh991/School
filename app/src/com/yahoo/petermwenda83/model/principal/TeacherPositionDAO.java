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
package com.yahoo.petermwenda83.model.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.staff.teaching.TeacherPosition;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TeacherPositionDAO extends DBConnectDAO implements SchoolTeacherPositionDAO {

	private static TeacherPositionDAO teacherPositionDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static TeacherPositionDAO getInstance() {
		if(teacherPositionDAO == null){
			teacherPositionDAO = new TeacherPositionDAO();		
			}
		return teacherPositionDAO;
	}
	
	public TeacherPositionDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public TeacherPositionDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolTeacherPositionDAO#getstaffPos(java.lang.String)
	 */
	@Override
	public TeacherPosition getstaffPos(String Uuid) {
		TeacherPosition tp = new TeacherPosition();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TeacherPosition WHERE Uuid = ?;");       
     		
     		){
     	
     	     pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	    	 tp  = beanProcessor.toBean(rset,TeacherPosition.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting TeacherPosition with Uuid: " + Uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return tp; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolTeacherPositionDAO#putTeacherPosition(com.yahoo.petermwenda83.contoller.staff.teaching.TeacherPosition)
	 */
	@Override
	public boolean putTeacherPosition(TeacherPosition teacherPos) {
		boolean success = true;
		TeacherPosition pos = new TeacherPosition();
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TeacherPosition" 
			        		+"(Uuid, TeacherUuid, TeacherPosition, Salary) VALUES (?,?,?,?);");
      		){
	            pstmt.setString(1, pos.getUuid());
	            pstmt.setString(2, teacherPos.getEmployeeUuid());
	            pstmt.setString(3, teacherPos.getPosition());
	            pstmt.setString(4, teacherPos.getSalary());
	            pstmt.executeUpdate();
			  
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Employees TeacherPosition: "+teacherPos);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolTeacherPositionDAO#editTeacherPosition(com.yahoo.petermwenda83.contoller.staff.teaching.TeacherPosition, java.lang.String)
	 */
	@Override
	public boolean editTeacherPosition(TeacherPosition teacherPos, String Uuid) {
		boolean success = true;
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE TeacherPosition SET "
	        			+ "TeacherPosition=?, Salary=? WHERE TeacherUuid = ?;");
      		){
			 
	            pstmt.setString(1, teacherPos.getPosition());
	            pstmt.setString(2, teacherPos.getSalary());
	            pstmt.setString(3, teacherPos.getTeacherUuid());
	            pstmt.executeUpdate();
			   
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit Employees TeacherPosition: "+teacherPos);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolTeacherPositionDAO#deleteTeacherPosition(com.yahoo.petermwenda83.contoller.staff.teaching.TeacherPosition, java.lang.String)
	 */
	@Override
	public boolean deleteTeacherPosition(TeacherPosition teacherPos, String Uuid) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM TeacherPosition"
	         	      		+ " WHERE TeacherUuid = ?;");  
	      		
	      		){
	      	     pstmt.setString(1, teacherPos.getTeacherUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting TeacherPosition : " +teacherPos);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolTeacherPositionDAO#getAllTeacherPosition()
	 */
	@Override
	public List<TeacherPosition> getAllTeacherPosition() {
		List<TeacherPosition>  list = null;
		
		 try(   
   		Connection conn = dbutils.getConnection();
   		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM TeacherPosition ;");   
   		ResultSet rset = pstmt.executeQuery();
		) {
   	
       list = beanProcessor.toBeanList(rset, TeacherPosition.class);

   } catch(SQLException e){
   	   logger.error("SQL Exception when getting all TeacherPosition");
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
   }
 
		
		return list;
	}

}
