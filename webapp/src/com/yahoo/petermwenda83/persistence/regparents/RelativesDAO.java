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
package com.yahoo.petermwenda83.persistence.regparents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.guardian.StudentRelative;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class RelativesDAO extends DBConnectDAO implements SchoolRelativesDAO {
	private static RelativesDAO relativesDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static RelativesDAO getInstance(){
		
		if(relativesDAO == null){
			relativesDAO = new RelativesDAO();		
		}
		return relativesDAO;
	}
	
	/**
	 * 
	 */
	public RelativesDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public RelativesDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolRelativesDAO#getStudentRelative(com.yahoo.petermwenda83.bean.guardian.StudentRelative)
	 */
	
	@Override
	public StudentRelative getStudentRelative(String StudentUuid) {
		StudentRelative relative = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_relative"
           	      		+ " WHERE StudentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, StudentUuid); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 relative  = beanProcessor.toBean(rset,StudentRelative.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentRelative  by StudentUuid: " + StudentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
        
		return relative; 
	}
	

	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolRelativesDAO#getStudentRelativeByNationalID(java.lang.String)
	 */
	
	@Override
	public StudentRelative getStudentRelativeByNationalID(String NationalID) {
		StudentRelative relative = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_relative"
           	      		+ " WHERE NationalID = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, NationalID); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 relative  = beanProcessor.toBean(rset,StudentRelative.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentRelative By NationalID : " + NationalID);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
        
		return relative; 
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolRelativesDAO#putStudentRelative(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.bean.guardian.StudentRelative)
	 */
	
	@Override
	public boolean putStudentRelative(StudentRelative relative) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student_relative ("
						+ "Uuid,StudentUuid,"
    			+ "RelativeName,RelativePhone,NationalID) VALUES (?,?,?,?,?);");
    		){
			   
			    pstmt.setString(1, relative.getUuid());
			    pstmt.setString(2, relative.getStudentUuid());
			    pstmt.setString(3, relative.getRelativeName());
	            pstmt.setString(4, relative.getRelativePhone());
	            pstmt.setString(5, relative.getNationalID());
	           
	    	    pstmt.executeUpdate();

			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put StudentRelative: "+relative);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolRelativesDAO#editStudentRelative(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.bean.guardian.StudentRelative)
	 */
	@Override
	public boolean editStudentRelative(StudentRelative relative,String StudentUuid) {
		boolean success = true;
		  try (  Connection conn = dbutils.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement("UPDATE student_relative SET RelativeName=?,"
    			+ "RelativePhone=?, NationalID=? WHERE studentUuid = ?;");
    	) {		    
			    pstmt.setString(1, relative.getRelativeName());
	            pstmt.setString(2, relative.getRelativePhone());
	            pstmt.setString(3, relative.getNationalID());
	            pstmt.setString(4, StudentUuid);
	          
	            pstmt.executeUpdate();

    } catch (SQLException e) {
        logger.error("SQL Exception when updating Student_Parent: " + relative);
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
        success = false;
    } 
    		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolRelativesDAO#deleteStudentRelative(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.bean.guardian.StudentRelative)
	 */
	@Override
	public boolean deleteStudentRelative(StudentRelative relative) {
		  boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM student_relative"
	         	      		+ " WHERE StudentUuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, relative.getStudentsUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting relative : " +relative);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolRelativesDAO#getAllStudentRelative()
	 */
	@Override
	public List<StudentRelative> getAllStudentRelative() {
		List<StudentRelative>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM student_relative ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, StudentRelative.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all StudentRelative");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	
	}

}
