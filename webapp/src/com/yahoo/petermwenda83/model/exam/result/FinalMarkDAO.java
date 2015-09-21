/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.results.FinalMark;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class FinalMarkDAO extends DBConnectDAO implements SchoolFinalMarkDAO {
	
	private static FinalMarkDAO finalMarkDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static FinalMarkDAO getInstance() {
		if(finalMarkDAO == null){
			finalMarkDAO = new FinalMarkDAO();		
			}
		return finalMarkDAO;
	}
	
	public FinalMarkDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public FinalMarkDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.exam.result.SchoolFinalMarkDAO#get(java.lang.String)
	 */
	@Override
	public FinalMark get(String StudentUuid,String SubjectUuid) {
		FinalMark finalMark = null;
        ResultSet rset = null;
        //getSubjectUuid()
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FinalMarks WHERE"
        	      		+ " StudentUuid =? AND SubjectUuid =?;");       
     		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
     	 pstmt.setString(2, SubjectUuid);
     	 
	     rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 finalMark  = beanProcessor.toBean(rset,FinalMark.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting FinalMark with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return finalMark; 
	}
	

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolFinalMarkDAO#hasMask(com.yahoo.petermwenda83.contoller.exam.FinalMark)
	 */
	@Override
	public boolean hasMark(Exam exam,double Marks) {
		boolean hasMark = false;
		double mark = 0;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Marks FROM FinalMarks "
	        			+ "WHERE StudentUuid = ? AND SubjectUuid =?;");
      		){
			 
	            pstmt.setString(1, exam.getStudentUuid());
	            pstmt.setString(2, exam.getSubjectUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						mark = rset.getDouble("Marks");	
						hasMark = (mark >= Marks) ? true : false;	 
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to ......: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasMark = false;
		 }
		
		return hasMark;
	}


	/**
	 * @see com.yahoo.petermwenda83.model.exam.result.SchoolFinalMarkDAO#put(com.yahoo.petermwenda83.contoller.exam.results.FinalMark)
	 */
	@Override
	public boolean addMark(Exam exam,double Marks) {
		boolean success = true;
		if(hasMark(exam,0)) {
		try(   Connection conn = dbutils.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement("UPDATE FinalMarks " +
						"SET Marks = (SELECT Marks FROM FinalMarks WHERE StudentUuid =? AND SubjectUuid =? "
						+ ") +?" +				
						"WHERE Uuid = (SELECT Uuid FROM FinalMarks WHERE StudentUuid =? AND SubjectUuid =?);");	
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE FinalMarks " +
						"SET Grade =? WHERE StudentUuid=? AND SubjectUuid =?;");	
				
        		){
			  if(exam instanceof FinalMark ){
				    pstmt.setString(1, exam.getStudentUuid());
				    pstmt.setString(2, exam.getSubjectUuid());
				    pstmt.setDouble(3, Marks);
		            pstmt.setString(4, exam.getStudentUuid());
		            pstmt.setString(5, exam.getSubjectUuid());
		            pstmt.executeUpdate(); 
		            
		            pstmt2.setString(1, exam.getGrade());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setString(3, exam.getSubjectUuid());
		            pstmt2.executeUpdate(); 
		        		            
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		}else{
			
			try(   Connection conn = dbutils.getConnection();
					PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO FinalMarks" 
			        		+"(Uuid,Studentuuid,SubjectUuid,Marks,Grade) VALUES (?,?,?,?,?);");
					
	        		){
				  if(exam instanceof FinalMark ){
					    pstmt2.setString(1, exam.getUuid());
			            pstmt2.setString(2, exam.getStudentUuid());
			            pstmt2.setString(3, exam.getSubjectUuid());
			            pstmt2.setDouble(4, Marks);
			            pstmt2.setString(5, exam.getGrade());
			           
			            pstmt2.executeUpdate(); 
				  }
			 }catch(SQLException e){
				 logger.error("SQL Exception trying to put: "+exam);
	             logger.error(ExceptionUtils.getStackTrace(e)); 
	             System.out.print(ExceptionUtils.getStackTrace(e));
	             success = false;
			 }
			
			
		}
		
		return success;
	}
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.result.SchoolFinalMarkDAO#deductMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deductMark(Exam exam,double Marks) {
		boolean success = true;
		try(  Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE FinalMarks " +
						"SET Marks = (SELECT Marks FROM FinalMarks WHERE StudentUuid =? AND SubjectUuid =?"
						+ ") -?" +				
						"WHERE Uuid = (SELECT Uuid FROM FinalMarks WHERE StudentUuid=? AND SubjectUuid =?);");	
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE FinalMarks " +
						"SET Grade =? WHERE StudentUuid=? AND SubjectUuid =?;");	
				
        		){
			  if(exam instanceof FinalMark ){
				    pstmt.setString(1, exam.getStudentUuid());
				    pstmt.setDouble(2, Marks);
		            pstmt.setString(3, exam.getStudentUuid());
		            pstmt.setString(4, exam.getSubjectUuid());
		            pstmt.executeUpdate(); 
		            
		            pstmt2.setString(1, exam.getGrade());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setString(3, exam.getSubjectUuid());
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
	 * @see com.yahoo.petermwenda83.model.exam.result.SchoolFinalMarkDAO#getAllFinalMark()
	 */
	@Override
	public List<FinalMark> getAllFinalMark() {
		
		List<FinalMark> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM FinalMarks ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, FinalMark.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all FinalMark");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
		return list;
	}

	

}
