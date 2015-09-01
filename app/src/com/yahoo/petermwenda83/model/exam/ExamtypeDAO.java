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
package com.yahoo.petermwenda83.model.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.model.DBConnectDAO;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ExamtypeDAO extends DBConnectDAO  implements SchoolExamtypeDAO {

	private static ExamtypeDAO examtypeDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static ExamtypeDAO getInstance() {
		if(examtypeDAO == null){
			examtypeDAO = new ExamtypeDAO();		
			}
		return examtypeDAO;
	}
	
	public ExamtypeDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public ExamtypeDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#getExamType(java.lang.String)
	 */
	@Override
	public ExamType getExamType(String uuid) {
		 ExamType examType = null;
         ResultSet rset = null;
      try(
      		 Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM exam_type WHERE Uuid = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 examType  = beanProcessor.toBean(rset,ExamType.class);
	   }
      	
      	
      	
      }catch(SQLException e){
      	 logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
           logger.error(ExceptionUtils.getStackTrace(e));
           System.out.println(ExceptionUtils.getStackTrace(e));
      }
      
		return examType; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#getExamTypes(java.lang.String)
	 */
	@Override
	public ExamType getExamTypes(String examno) {
		 ExamType examType = null;
         ResultSet rset = null;
      try(
      		 Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM exam_type WHERE Examno = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, examno);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 examType  = beanProcessor.toBean(rset,ExamType.class);
	   }
      	
      	
      	
      }catch(SQLException e){
      	 logger.error("SQL Exception when getting ExamType with uuid: " + examno);
           logger.error(ExceptionUtils.getStackTrace(e));
           System.out.println(ExceptionUtils.getStackTrace(e));
      }
	return examType;
	}
  
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#get(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ExamType get(String examtype, String clasz, String description) {
		 ExamType examType = null;
         ResultSet rset = null;
      try(
      		 Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM exam_type "
         	      		+ "WHERE examtype = ? AND clasz =? AND description =? ;");       
      		
      		){
      	
      	 pstmt.setString(1, examtype);
      	 pstmt.setString(2, clasz);
      	 pstmt.setString(3, description);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 examType  = beanProcessor.toBean(rset,ExamType.class);
	   }
      	
      	
      	
      }catch(SQLException e){
      	 logger.error("SQL Exception when getting ExamType with: "+examtype+" and "+clasz+" and "+description);
           logger.error(ExceptionUtils.getStackTrace(e));
           System.out.println(ExceptionUtils.getStackTrace(e));
      }
	return examType;
	}
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#putExamType(com.yahoo.petermwenda83.contoller.exam.ExamType)
	 */
	@Override
	public boolean putExamType(ExamType examType) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO exam_type" 
			        		+"(Uuid, examtype, term, year,clasz,outof,description,examno,subjectUuid) VALUES (?,?,?,?,?,?,?,?,?);");
       		){
			   
	            pstmt.setString(1, examType.getUuid());
	            pstmt.setString(2, examType.getExamtype());
	            pstmt.setString(3, examType.getTerm());
	            pstmt.setString(4, examType.getYear());
	            pstmt.setString(5, examType.getClasz());
	            pstmt.setString(6, examType.getOutof());
	            pstmt.setString(7, examType.getDescription());
	            pstmt.setString(8, examType.getExamno());
	            pstmt.setString(9, examType.getSubjectUuid());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+examType);
            logger.error(ExceptionUtils.getStackTrace(e)); 
            System.out.println(ExceptionUtils.getStackTrace(e));
            success = false;
		 }
		
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#editExamType(com.yahoo.petermwenda83.contoller.exam.ExamType, java.lang.String)
	 */
	@Override
	public boolean editExamType(ExamType type, String Uuid) {
		  boolean success = true; 
		  
			 try(   Connection conn = dbutils.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("UPDATE exam_type SET examtype =?,"
				+ " term =?, year =?,clasz =?,outof =?,description =?,examno =?,subjectUuid=? WHERE Uuid = ? ;");
	        		){
		            pstmt.setString(1, type.getExamtype());
		            pstmt.setString(2, type.getTerm());
		            pstmt.setString(3, type.getYear());
		            pstmt.setString(4, type.getClasz());
		            pstmt.setString(5, type.getOutof()); 
		            pstmt.setString(6, type.getDescription());
		            pstmt.setString(7, type.getExamno());
		            pstmt.setString(8, type.getSubjectUuid());
		            pstmt.setString(9, Uuid);
		            pstmt.executeUpdate();
				 
			 }catch(SQLException e){
				 logger.error("SQL Exception trying to update: "+type);
	             logger.error(ExceptionUtils.getStackTrace(e)); 
	             System.out.println(ExceptionUtils.getStackTrace(e));
	             success = false;
			 }
			
			
			return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#deleteExamType(com.yahoo.petermwenda83.contoller.exam.ExamType)
	 */
	@Override
	public boolean deleteExamType(ExamType type) {

		  boolean success = true; 
      try(
      		  Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM exam_type WHERE Uuid = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, type.getUuid());
	         pstmt.executeUpdate();
	     
      }catch(SQLException e){
      	 logger.error("SQL Exception when deletting : " +type);
           logger.error(ExceptionUtils.getStackTrace(e));
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
           
      }
      
		return success; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamtypeDAO#getAllExamtype()
	 */
	@Override
	public List<ExamType> getAllExamtype() {
		List<ExamType> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM exam_type ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, ExamType.class);

	      } catch(SQLException e){
	      	logger.error("SQL Exception when getting all ExamType");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
			
		
		return list;
	}

	

}
