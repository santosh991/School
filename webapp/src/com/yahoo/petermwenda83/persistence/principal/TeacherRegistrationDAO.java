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
package com.yahoo.petermwenda83.persistence.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.staff.teaching.Teachers;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 *@author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TeacherRegistrationDAO extends DBConnectDAO  implements SchoolTeacherRegistrationDAO {

	
	private static TeacherRegistrationDAO srDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static TeacherRegistrationDAO getInstance() {
		if(srDAO == null){
			srDAO = new TeacherRegistrationDAO();		
			}
		return srDAO;
	}
	
	public TeacherRegistrationDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public TeacherRegistrationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolTeacherRegistrationDAO#getTeacher(java.lang.String)
	 */
	@Override
	public Teachers getTeacher(String TeacherNumber) {
		 Teachers teachers  = null;
         ResultSet rset = null;
      try(
         Connection conn = dbutils.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Teacher WHERE TeacherNumber = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, TeacherNumber);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 teachers  = beanProcessor.toBean(rset,Teachers.class);
	   }
      	
      	
      	
      }catch(SQLException e){
    	   logger.error("SQL Exception when getting Teachers with TeacherNumber: " + TeacherNumber);
           logger.error(ExceptionUtils.getStackTrace(e));
           System.out.println(ExceptionUtils.getStackTrace(e));
      }
      
		return teachers; 
		 
	}



	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolTeacherRegistrationDAO#putTeacher(com.yahoo.petermwenda83.bean.staff.teaching.Teachers)
	 */
	@Override
	public boolean putTeacher(Teachers Teacher) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Teacher" 
			        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nssfno,Phone,"
			        		+ "DOB,NationalID,TeacherNumber,County,Location) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
				){
	            pstmt.setString(1, Teacher.getUuid());
	            pstmt.setString(2, Teacher.getFirstName());
	            pstmt.setString(3, Teacher.getLastName());
	            pstmt.setString(4, Teacher.getSurname());
	            pstmt.setString(5, Teacher.getNhifno());
	            pstmt.setString(6, Teacher.getNssfno());
	            pstmt.setString(7, Teacher.getPhone());
	            pstmt.setString(8, Teacher.getDOB());
	            pstmt.setString(9, Teacher.getNationalID());
	            pstmt.setString(10, Teacher.getTeacherNumber());
	            pstmt.setString(11, Teacher.getCounty());
	            pstmt.setString(12, Teacher.getLocation());
	           	pstmt.executeUpdate();            
	         
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Teacher: "+Teacher);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolTeacherRegistrationDAO#editTeacher(com.yahoo.petermwenda83.bean.staff.teaching.Teachers, java.lang.String)
	 */
	@Override
	public boolean editTeacher(Teachers Teacher, String Uuid) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Teacher SET "
	        			+ "FirstName=?, LastName=?, Surname =?, Nhifno =?, Nssfno =?, "
	        			+ "Phone =?, DOB =?, NationalID =?, TeacherNumber =?,"
	        			+ " County =?,Location =? WHERE Uuid = ?;");
        		){           
	            pstmt.setString(1, Teacher.getFirstName());
	            pstmt.setString(2, Teacher.getLastName());
	            pstmt.setString(3, Teacher.getSurname());
	            pstmt.setString(4, Teacher.getNhifno());
	            pstmt.setString(5, Teacher.getNssfno());
	            pstmt.setString(6, Teacher.getPhone());
	            pstmt.setString(7, Teacher.getDOB());
	            pstmt.setString(8, Teacher.getNationalID());
	            pstmt.setString(9, Teacher.getTeacherNumber());
	            pstmt.setString(10, Teacher.getCounty());
	            pstmt.setString(11, Teacher.getLocation());
	            pstmt.setString(12, Teacher.getUuid());
	          	            	   
	            pstmt.executeUpdate();
			
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Teacher: "+Teacher);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}
   

	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolTeacherRegistrationDAO#deleteTeacher(com.yahoo.petermwenda83.bean.staff.teaching.Teachers, java.lang.String)
	 */
	@Override
	public boolean deleteTeacher(Teachers Teacher, String uuid) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Teacher"
	         	      		+ " WHERE Uuid = ?;");     	      		
	      		){
	      	     pstmt.setString(1, Teacher.getUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting Teacher : " +Teacher);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolTeacherRegistrationDAO#getAllTeachers()
	 */
	@Override
	public List<Teachers> getAllTeachers() {
		List<Teachers>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Teacher ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, Teachers.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all Teachers");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	}

	
}
