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

import com.yahoo.petermwenda83.bean.guardian.StudentParent;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ParentsDAO extends DBConnectDAO  implements SchoolParentsDAO {

	private static ParentsDAO parentsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static ParentsDAO getInstance(){
		
		if(parentsDAO == null){
			parentsDAO = new ParentsDAO();		
		}
		return parentsDAO;
	}
	
	/**
	 * 
	 */
	public ParentsDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public ParentsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @return 
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#getStudentParent(com.yahoo.petermwenda83.bean.guardian.StudentParent)
	 */
	@Override
	public StudentParent getStudentParent(String Uuid) {
		StudentParent parent = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Parent"
           	      		+ " WHERE studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Uuid); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 parent  = beanProcessor.toBean(rset,StudentParent.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with uuid: " + parent);
             logger.error(ExceptionUtils.getStackTrace(e));
             
        }
        
		return parent; 
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#getParentByFatherId(java.lang.String)
	 */
	@Override
	public StudentParent getParentByFatherId(String FatherId) {
		StudentParent parent = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Parent"
           	      		+ " WHERE FatherId = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, FatherId); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 parent  = beanProcessor.toBean(rset,StudentParent.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with FatherId: " + FatherId);
             logger.error(ExceptionUtils.getStackTrace(e));
             
        }
        
		return parent; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#getParentByMotherId(java.lang.String)
	 */
	@Override
	public StudentParent getParentByMotherId(String MotherID) {
		StudentParent parent = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Parent"
           	      		+ " WHERE MotherID = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, MotherID); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 parent  = beanProcessor.toBean(rset,StudentParent.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with MotherID: " + MotherID);
             logger.error(ExceptionUtils.getStackTrace(e));
             
        }
        
		return parent; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#putStudentParent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.bean.guardian.StudentParent)
	 */
	@Override
	public boolean putStudentParent(StudentParent parent) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Parent"+
						"(Uuid,StudentUuid,FatherName,FatherPhone,"
						+ "FatherOccupation,FatherID,FatherEmail,"
						+ "MotherName,MotherPhone,MotherOccupation,"
						+ "MotherEmail,MotherID,Relationship)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
      		){
			   
			   
			    pstmt.setString(1, parent.getUuid());
			    pstmt.setString(2, parent.getStudentUuid());
			    pstmt.setString(3, parent.getFathername());
	            pstmt.setString(4, parent.getFatherphone());
	            pstmt.setString(5, parent.getFatheroccupation());
	            pstmt.setString(6, parent.getFatherID());
	            pstmt.setString(7, parent.getFatherEmail());
	           
	            pstmt.setString(8, parent.getMothername());
	            pstmt.setString(9, parent.getMotherphone());
	            pstmt.setString(10, parent.getMotheroccupation());
	            pstmt.setString(11, parent.getMotherEmail());
	            pstmt.setString(12, parent.getMotherID());
	            
	            pstmt.setString(13, parent.getRelationship());
	            
	           
	            
	            
	            pstmt.executeUpdate();

			 
		 }catch(SQLException e){
		   logger.error("SQL Exception trying to put StudentParent: "+parent);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	


	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#ediStudentParent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.bean.guardian.StudentParent)
	 */
	@Override
	public boolean ediStudentParent(StudentParent parent,String StudentUuid) {
		boolean success = true;
		  try (  Connection conn = dbutils.getConnection();
      	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Parent SET FatherName=?,"
      			+ "FatherPhone=?, FatherOccupation=?,FatherID=?,FatherEmail =?,"
      			+ "MotherName=?,MotherPhone =?,MotherOccupation =?,"
      			+ "MotherEmail=?,MotherID=?,Relationship=?   WHERE StudentUuid=?;");
      	) {
              
	            pstmt.setString(1, parent.getFathername());
	            pstmt.setString(2, parent.getFatherphone());
	            pstmt.setString(3, parent.getFatheroccupation());
	            pstmt.setString(4, parent.getFatherID());
	            pstmt.setString(5, parent.getFatherEmail());
	           
	            pstmt.setString(6, parent.getMothername());
	            pstmt.setString(7, parent.getMotherphone());
	            pstmt.setString(8, parent.getMotheroccupation());
	            pstmt.setString(9, parent.getMotherEmail());
	            pstmt.setString(10, parent.getMotherID());
	            
	            pstmt.setString(11, parent.getRelationship());
	            
	            pstmt.setString(12,StudentUuid);
	            
	            
	            pstmt.executeUpdate();

      } catch (SQLException e) {
          logger.error("SQL Exception when updating Student_Parent: " + parent);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
          success = false;
      } 
      		
		return success;
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#deleteStudentParent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.bean.guardian.StudentParent)
	 */
	@Override
	public boolean deleteStudentParent(StudentParent parent) {

		  boolean success = true; 
      try(
      		  Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_Parent"
         	      		+ " WHERE StudentUuid = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, parent.getStudentsUuid());
	         pstmt.executeUpdate();
	     
      }catch(SQLException e){
      	 logger.error("SQL Exception when deletting parent : " +parent);
           logger.error(ExceptionUtils.getStackTrace(e));
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
           
      }
      
		return success;
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.regparents.SchoolParentsDAO#getAllStudentParent()
	 */
	@Override
	public List<StudentParent> getAllStudentParent() {
		List<StudentParent>  list = null;
		
		 try(   
     		Connection conn = dbutils.getConnection();
     		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Parent ;");   
     		ResultSet rset = pstmt.executeQuery();
 		) {
     	
         list = beanProcessor.toBeanList(rset, StudentParent.class);

     } catch(SQLException e){
     	logger.error("SQL Exception when getting all StudentParent");
         logger.error(ExceptionUtils.getStackTrace(e));
         System.out.println(ExceptionUtils.getStackTrace(e));
     }
   
		
		return list;
	}

	

}
