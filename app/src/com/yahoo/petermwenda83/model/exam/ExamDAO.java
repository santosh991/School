/**
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
import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class ExamDAO extends DBConnectDAO  implements TeacherExamDAO {

	private static ExamDAO examDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return 
	 * 
	 */
	public ExamDAO getInstance() {
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
	 * @see com.yahoo.petermwenda83.model.exam.TeacherExamDAO#getExamType(java.lang.String)
	 */
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
        }
        
		return examType; 
		 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.TeacherExamDAO#getMainMarks(java.lang.String)
	 */
	public MainMarks getMainMarks(String uuid) {
		MainMarks mainMarks = null;
         ResultSet rset = null;
      try(
      		 Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM exam_marks WHERE Uuid = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 mainMarks  = beanProcessor.toBean(rset,MainMarks.class);
	   }
      	
      	
      	
      }catch(SQLException e){
      	 logger.error("SQL Exception when getting MainMarks with uuid: " + uuid);
           logger.error(ExceptionUtils.getStackTrace(e));
      }
      
		return mainMarks; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.TeacherExamDAO#getExamResults(java.lang.String)
	 */
	public MainResults getExamResults(String uuid) {
		MainResults mainResults = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM exam_totals WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 mainResults  = beanProcessor.toBean(rset,MainResults.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainResults with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return mainResults; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.TeacherExamDAO#getCatMarks(java.lang.String)
	 */
	public CatMarks getCatMarks(String uuid) {
		CatMarks catMarks = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM catmarks WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 catMarks  = beanProcessor.toBean(rset,CatMarks.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainMarks with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return catMarks; 
	
	}

	@Override
	public CatResults getCatResults(String uuid) {
		CatResults catResults = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cattotals WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 catResults  = beanProcessor.toBean(rset,CatResults.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainResults with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return catResults; 
	}

	@Override
	public boolean putExamType(ExamType examType) {
		  boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO exam_type" 
			        		+"(Uuid, examtype, term, year,clasz,description) VALUES (?,?,?,?,?,?);");
        		){
			   
	            pstmt.setString(1, examType.getUuid());
	            pstmt.setString(2, examType.getExamtype());
	            pstmt.setString(3, examType.getTerm());
	            pstmt.setString(4, examType.getYear());
	            pstmt.setString(5, examType.getClasz());
	            pstmt.setString(6, examType.getDescription());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+examType);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
		 
	}

	@Override
	public boolean putExamMarks(Exam exam) {
		boolean success = true;
		MainMarks mainm = new MainMarks();
		CatMarks catm = new CatMarks();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO catmarks" 
			        		+"(Uuid, studentuuid, examtypeuuid, subjectuuid,marks,submitdate) VALUES (?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO exam_marks" 
		        		+"(Uuid, studentuuid, examtypeuuid, subjectuuid,marks,submitdate) VALUES (?,?,?,?,?,?);");
        		){
			  if(exam instanceof CatMarks ){
	            pstmt.setString(1, catm.getUuid());
	            pstmt.setString(2, exam.getStudentUuid());
	            pstmt.setString(3, exam.getExamTypeUuid());
	            pstmt.setString(4, exam.getSubjectUuid());
	            pstmt.setDouble(5, exam.getMarks());
	            pstmt.setTimestamp(6,  new Timestamp(exam.getSubmitdate().getTime()));
	            
	            pstmt.executeUpdate();
			  }else{         //Main marks
				    pstmt2.setString(1, mainm.getUuid());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setString(3, exam.getExamTypeUuid());
		            pstmt2.setString(4, exam.getSubjectUuid());
		            pstmt2.setDouble(5, exam.getMarks());
		            pstmt2.setTimestamp(6,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}

	@Override
	public boolean putExamResults(Exam exam) {
		boolean success = true;
		MainResults mainr = new MainResults();
		CatResults catr = new CatResults();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cattotals" 
			        		+"(Uuid,subjectuuid,studentuuid,total,points,grade,position,remarks,submitdate) VALUES (?,?,?,?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO exam_totals" 
		        		+"(Uuid,subjectuuid,studentuuid,total,points,grade,position,remarks,submitdate) VALUES (?,?,?,?,?,?,?,?,?);");
        		){
			  if(exam instanceof CatResults ){
	            pstmt.setString(1, catr.getUuid());
	            pstmt.setString(2, exam.getSubjectUuid());
	            pstmt.setString(3, exam.getStudentUuid());
	            pstmt.setDouble(4, exam.getTotal());
	            pstmt.setDouble(5, exam.getPoints());
	            pstmt.setString(6, exam.getGrade());
	            pstmt.setInt(7, exam.getPosition()); 
	            pstmt.setString(8, exam.getRemarks());
	            pstmt.setTimestamp(9,  new Timestamp(exam.getSubmitdate().getTime()));
	            
	            pstmt.executeUpdate();
			  }else{         //MainResults
				    pstmt2.setString(1, mainr.getUuid());
				    pstmt2.setString(2, exam.getSubjectUuid());
		            pstmt2.setString(3, exam.getStudentUuid());
		            pstmt2.setDouble(4, exam.getTotal());
		            pstmt2.setDouble(5, exam.getPoints());
		            pstmt2.setString(6, exam.getGrade());
		            pstmt2.setInt(7, exam.getPosition()); 
		            pstmt2.setString(8, exam.getRemarks());
		            pstmt2.setTimestamp(9,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}

	@Override
	public boolean editExamType(ExamType type,String Uuid) {
		  boolean success = true; 
		  
			 try(   Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE exam_type SET" 
				        		+"examtype =?, term =?, year =?,clasz =?,description =? WHERE Uuid = ? ;");
	        		){
		            pstmt.setString(1, type.getExamtype());
		            pstmt.setString(2, type.getTerm());
		            pstmt.setString(3, type.getYear());
		            pstmt.setString(4, type.getClasz());
		            pstmt.setString(5, type.getDescription());
		            pstmt.setString(6, Uuid);
		            pstmt.executeUpdate();
				 
			 }catch(SQLException e){
				 logger.error("SQL Exception trying to update: "+type);
	             logger.error(ExceptionUtils.getStackTrace(e)); 
	             success = false;
			 }
			
			
			return success;
	}

	@Override
	public boolean editExamMarks(Exam exam, String Uuid) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE catmarks SET" 
			        		+"studentuuid =?, examtypeuuid =?, subjectuuid =?"
			        		+ ",marks =?,submitdate =? WHERE Uuid = ?;");
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE exam_marks SET" 
		        		+"studentuuid =?, examtypeuuid =?, subjectuuid =?,"
		        		+ "marks =?,submitdate =?  WHERE Uuid = ? ;");
        		){
			  if(exam instanceof CatMarks ){
	            pstmt.setString(1, exam.getStudentUuid());
	            pstmt.setString(2, exam.getExamTypeUuid());
	            pstmt.setString(3, exam.getSubjectUuid());
	            pstmt.setDouble(4, exam.getMarks());
	            pstmt.setTimestamp(5,  new Timestamp(exam.getSubmitdate().getTime()));
	            pstmt.setString(6, Uuid);
	            
	            pstmt.executeUpdate();
			  }else{         //Main marks
				   
		            pstmt2.setString(1, exam.getStudentUuid());
		            pstmt2.setString(2, exam.getExamTypeUuid());
		            pstmt2.setString(3, exam.getSubjectUuid());
		            pstmt2.setDouble(4, exam.getMarks());
		            pstmt2.setTimestamp(5,  new Timestamp(exam.getSubmitdate().getTime()));
		            pstmt2.setString(6, Uuid);
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}

	@Override
	public boolean editExamResults(Exam exam, String Uuid) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE cattotals SET" 
			        		+"subjectuuid =?,studentuuid =?,total =?,points =?,"
			        		+ "grade =?,position =?,remarks =?,submitdate =?  WHERE Uuid = ? ;");
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE exam_totals SET" 
		        		+"subjectuuid =?,studentuuid =?,total =?,points =?,grade =?,"
		        		+ "position =?,remarks =?,submitdate =?  WHERE Uuid = ? ;");
        		){
			  if(exam instanceof CatResults ){
	            pstmt.setString(1, exam.getSubjectUuid());
	            pstmt.setString(2, exam.getStudentUuid());
	            pstmt.setDouble(3, exam.getTotal());
	            pstmt.setDouble(4, exam.getPoints());
	            pstmt.setString(5, exam.getGrade());
	            pstmt.setInt(6, exam.getPosition()); 
	            pstmt.setString(7, exam.getRemarks());
	            pstmt.setTimestamp(8,  new Timestamp(exam.getSubmitdate().getTime()));
	            pstmt.setString(9, Uuid);
	            
	            pstmt.executeUpdate();
			  }else{         //MainResults
				    
				    pstmt2.setString(1, exam.getSubjectUuid());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setDouble(3, exam.getTotal());
		            pstmt2.setDouble(4, exam.getPoints());
		            pstmt2.setString(5, exam.getGrade());
		            pstmt2.setInt(6, exam.getPosition()); 
		            pstmt2.setString(7, exam.getRemarks());
		            pstmt2.setTimestamp(8,  new Timestamp(exam.getSubmitdate().getTime()));
		            pstmt2.setString(9, Uuid);
		            
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}

	
	
	
	@Override
	public boolean deleteExamType(ExamType type,String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteExamMarks(Exam exam,String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteExamResults(Exam exam,String uuid) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
