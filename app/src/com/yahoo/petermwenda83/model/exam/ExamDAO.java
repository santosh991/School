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
import java.sql.Timestamp;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;
import com.yahoo.petermwenda83.model.DBConnectDAO;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ExamDAO extends DBConnectDAO  implements SchoolExamDAO {

	private static ExamDAO examDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static ExamDAO getInstance() {
		if(examDAO == null){
			examDAO = new ExamDAO();		
			}
		return examDAO;
	}
	
	public ExamDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public ExamDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#getMainMarks(java.lang.String)
	 */
	
	@Override
	public MainMarks getMainMarks(String StudentUuid,String Subjectuuid,String ExamTypeUuid) {
		MainMarks mainMarks = null;
         ResultSet rset = null;
      try(
      		 Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MainSubjectMark"
         	      		+ " WHERE StudentUuid = ? AND Subjectuuid=? AND ExamTypeUuid =? ;");       
      		
      		){
      	
      	 pstmt.setString(1, StudentUuid);
      	 pstmt.setString(2, Subjectuuid);
      	 pstmt.setString(3, ExamTypeUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 mainMarks  = beanProcessor.toBean(rset,MainMarks.class);
	   }
      	
      	
      	
      }catch(SQLException e){
      	 logger.error("SQL Exception when getting MainMarks with StudentUuid: " + StudentUuid);
         logger.error(ExceptionUtils.getStackTrace(e));
         System.out.print(ExceptionUtils.getStackTrace(e));
      }
      
		return mainMarks; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#getExamResults(java.lang.String)
	 */
	@Override
	public MainResults getExamResults(String StudentUuid,String Subjectuuid) {
		MainResults mainResults = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ExamResult WHERE"
        	      		+ " StudentUuid =? AND Subjectuuid=?;");       
     		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
     	 pstmt.setString(2, Subjectuuid);
	     rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 mainResults  = beanProcessor.toBean(rset,MainResults.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainResults with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return mainResults; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#getCatMarks(java.lang.String)
	 */
	@Override
	public CatMarks getCatMarks(String StudentUuid,String Subjectuuid,String ExamTypeUuid) {
		CatMarks catMarks = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CatSubjectMark WHERE"
        	      		+ " StudentUuid = ? AND Subjectuuid =? AND ExamTypeUuid =? ;");       
     		
     		){
    	
     	 pstmt.setString(1, StudentUuid);
     	 pstmt.setString(2, Subjectuuid);
     	 pstmt.setString(3, ExamTypeUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 catMarks  = beanProcessor.toBean(rset,CatMarks.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainMarks with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return catMarks; 
	
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#getCatResults(java.lang.String)
	 */
	@Override
	public CatResults getCatResults(String StudentUuid,String Subjectuuid) {
		CatResults catResults = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CatResult "
        	      		+ "WHERE StudentUuid = ? AND Subjectuuid =?;");       
    		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
     	 pstmt.setString(2, Subjectuuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 catResults  = beanProcessor.toBean(rset,CatResults.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainResults with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return catResults; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#putExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean putExamMarks(Exam exam) {
		boolean success = true;
		MainMarks mainm = new MainMarks();
		CatMarks catm = new CatMarks();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CatSubjectMark" 
			        		+"(Uuid, studentuuid, examtypeuuid, subjectuuid,marks,"
			        		+ "submark,percent,Grade,submitdate) VALUES (?,?,?,?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO MainSubjectMark" 
		        		+"(Uuid, studentuuid, examtypeuuid, subjectuuid,"
		        		+ "marks,percent,Grade,submitdate) VALUES (?,?,?,?,?,?,?,?);");
        		){
			  if(exam instanceof CatMarks ){
	            pstmt.setString(1, catm.getUuid());
	            pstmt.setString(2, exam.getStudentUuid());
	            pstmt.setString(3, exam.getExamTypeUuid());
	            pstmt.setString(4, exam.getSubjectUuid());
	            pstmt.setString(5, exam.getMarks());
	            pstmt.setString(6, exam.getSubmark());
	            pstmt.setString(7, exam.getPercent());
	            pstmt.setString(8, exam.getGrade());
	            pstmt.setTimestamp(9,  new Timestamp(exam.getSubmitdate().getTime()));
	            
	            pstmt.executeUpdate();
			  }else{         //Main marks
				    pstmt2.setString(1, mainm.getUuid());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setString(3, exam.getExamTypeUuid());
		            pstmt2.setString(4, exam.getSubjectUuid());
		            pstmt2.setString(5, exam.getMarks());
		            pstmt2.setString(6, exam.getPercent());
		            pstmt2.setString(7, exam.getGrade());
		            pstmt2.setTimestamp(8,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#putExamResults(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean putExamResults(Exam exam) {
		boolean success = true;
		MainResults mainr = new MainResults();
		CatResults catr = new CatResults();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CatResult" 
			        		+"(Uuid,subjectuuid,studentuuid,examtypeuuid,total,"
			        		+ "points,grade,position,remarks,submitdate) VALUES (?,?,?,?,?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO ExamResult" 
		        		+"(Uuid,subjectuuid,studentuuid,examtypeuuid,total,"
		        		+ "points,grade,position,remarks,submitdate) VALUES (?,?,?,?,?,?,?,?,?,?);");
        		){
			  if(exam instanceof CatResults ){
	            pstmt.setString(1, catr.getUuid());
	            pstmt.setString(2, exam.getSubjectUuid());
	            pstmt.setString(3, exam.getStudentUuid());
	            pstmt.setString(4, exam.getExamTypeUuid());
	            pstmt.setString(5, exam.getTotal());
	            pstmt.setString(6, exam.getPoints());
	            pstmt.setString(7, exam.getGrade());
	            pstmt.setString(8, exam.getPosition()); 
	            pstmt.setString(9, exam.getRemarks());
	            pstmt.setTimestamp(10,  new Timestamp(exam.getSubmitdate().getTime()));
	            
	            pstmt.executeUpdate();
			  }else{         //MainResults
				    pstmt2.setString(1, mainr.getUuid());
				    pstmt2.setString(2, exam.getSubjectUuid());
		            pstmt2.setString(3, exam.getStudentUuid());
		            pstmt2.setString(4, exam.getExamTypeUuid());
		            pstmt2.setString(5, exam.getTotal());
		            pstmt2.setString(6, exam.getPoints());
		            pstmt2.setString(7, exam.getGrade());
		            pstmt2.setString(8, exam.getPosition()); 
		            pstmt2.setString(9, exam.getRemarks());
		            pstmt2.setTimestamp(10,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#editExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Subject)
	 */
	
	@Override
	public boolean editExamMarks(Exam exam) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE CatSubjectMark SET marks =?, "
						+ "submitdate =?"
						+ " WHERE Studentuuid = ? AND Subjectuuid = ? AND ExamTypeUuid=?  ;");
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MainSubjectMark SET marks =?, "
						+ "submitdate =? "
						+ " WHERE Studentuuid = ? AND Subjectuuid = ? ;");
        		){
			  if(exam instanceof CatMarks ){
	            pstmt.setString(1, exam.getMarks());
	            pstmt.setTimestamp(2, new Timestamp(exam.getSubmitdate().getTime()));
	            pstmt.setString(3, exam.getStudentUuid());
	            pstmt.setString(4, exam.getSubjectUuid());
	            pstmt.setString(5, exam.getExamTypeUuid());
	            
	            pstmt.executeUpdate();
			  }else{         //Main marks
				    pstmt2.setString(1, exam.getMarks());
		            pstmt2.setTimestamp(2, new Timestamp(exam.getSubmitdate().getTime()));
		            pstmt2.setString(3, exam.getStudentUuid());
		            pstmt2.setString(4, exam.getSubjectUuid());
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to update Exam: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#deleteExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deleteExamMarks(Exam exam) {
		  boolean success = true; 
	            try(
	      		  Connection conn = dbutils.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CatSubjectMark WHERE"
	        		+ " StudentUuid = ? AND SubjectUuid = ? AND ExamTypeUuid=?  ;");       
	        PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM MainSubjectMark WHERE"
	         	       + " StudentUuid = ? AND SubjectUuid = ?  AND ExamTypeUuid=?;");       
	         	      		
	      		      ){
	            	 if(exam instanceof CatMarks ){
	      	          pstmt.setString(1,exam.getStudentUuid());
	      	          pstmt.setString(2,exam.getSubjectUuid());
	      	          pstmt.setString(3,exam.getExamTypeUuid());
		              pstmt.executeUpdate();
	            	 }else{
	            		 pstmt2.setString(1,exam.getStudentUuid());
		      	         pstmt2.setString(2,exam.getSubjectUuid());
		      	         pstmt2.setString(3,exam.getExamTypeUuid());
		      	         pstmt2.executeUpdate();
	            	 }
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting Exam : " +exam);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamDAO#deleteExamResults(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deleteExamResults(Exam exam) {
		boolean success = true; 
        try(
  		  Connection conn = dbutils.getConnection();
    PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CatResult WHERE"
    		+ " StudentUuid = ? AND SubjectUuid = ?;");       
    PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM ExamResult WHERE"
     	        		+ " StudentUuid = ? AND SubjectUuid = ?;");       
     	      		
  		      ){
        	 if(exam instanceof CatResults ){
  	          pstmt.setString(1,exam.getStudentUuid());
  	          pstmt.setString(2,exam.getSubjectUuid());
              pstmt.executeUpdate();
        	 }else{
        		 pstmt2.setString(1,exam.getStudentUuid());
      	         pstmt2.setString(2,exam.getSubjectUuid());
      	         pstmt2.executeUpdate();
        	 }
     
       }catch(SQLException e){
  	   logger.error("SQL Exception when deletting Exam : " +exam);
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
       success = false;
       
  }
  
	return success; 
	}
	//CatResult ,ExamResult, CatSubjectMark,MainSubjectMark


}
