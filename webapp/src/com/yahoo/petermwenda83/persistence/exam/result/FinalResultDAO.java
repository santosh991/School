/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.Exam;
import com.yahoo.petermwenda83.bean.exam.results.FinalResult;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class FinalResultDAO extends DBConnectDAO implements SchoolFinalResultDAO {

	private static FinalResultDAO finalResultDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static FinalResultDAO getInstance() {
		if(finalResultDAO == null){
			finalResultDAO = new FinalResultDAO();		
			}
		return finalResultDAO;
	}
	
	public FinalResultDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public FinalResultDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.result.SchoolFinalResultDAO#get(java.lang.String)
	 */
	@Override
	public FinalResult get(String StudentUuid) {
		FinalResult finalResult = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FinalResult WHERE"
        	      		+ " StudentUuid =?;");       
     		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
	     rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 finalResult  = beanProcessor.toBean(rset,FinalResult.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting FinalResult with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return finalResult; 
	}
	
	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.result.SchoolFinalResultDAO#hasResult(com.yahoo.petermwenda83.bean.exam.results.FinalResult)
	 */
	@Override
	public boolean hasPoints(Exam exam,double Points) {
		boolean hasResult = false;
		double Point = 0;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Points FROM FinalResult "
	        			+ "WHERE StudentUuid = ?;");
      		){
			 
	            pstmt.setString(1, exam.getStudentUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						 
						) {
					
					if(rset.next()) {
						Point = rset.getDouble("Points");	
						hasResult = (Point >= Points) ? true : false;	 
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to ......: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasResult = false;
		 }
		
		return hasResult;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.result.SchoolFinalResultDAO#put(com.yahoo.petermwenda83.bean.exam.results.FinalResult)
	 */
	@Override
	public boolean addPoints(Exam exam,double Points) {
		boolean success = true;
		if(hasPoints(exam,0)) {
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE FinalResult " +
						"SET Points = (SELECT Points FROM FinalResult WHERE StudentUuid=? "
						+ ") +?" +				
						"WHERE Uuid = (SELECT Uuid FROM FinalResult WHERE StudentUuid=? );");	
				
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE FinalResult " +
						"SET Grade = ?, Remarks =? WHERE StudentUuid=?;" );	
				
				
				
        		){
			  if(exam instanceof FinalResult ){
				    pstmt.setString(1, exam.getStudentUuid());
				    pstmt.setDouble(2, Points);
		            pstmt.setString(3, exam.getStudentUuid());
		            pstmt.executeUpdate(); 
		            
		            pstmt2.setString(1, exam.getGrade());
		            pstmt2.setString(2, exam.getRemarks());
		            pstmt2.setString(3, exam.getStudentUuid());
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
					PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO FinalResult" 
			        		+"(Uuid,Studentuuid,Points,Grade,Position,Remarks) VALUES (?,?,?,?");
	        		){
				  if(exam instanceof FinalResult ){
					    pstmt2.setString(1, exam.getUuid());
			            pstmt2.setString(2, exam.getStudentUuid());
			            pstmt2.setDouble(5, Points);
			            pstmt2.setString(5, exam.getGrade());
			            pstmt2.setDouble(5, exam.getPosition());
			            pstmt2.setString(5, exam.getRemarks());
			           
			            pstmt2.executeUpdate(); 
				  }
			 }catch(SQLException e){
				 logger.error("SQL Exception trying to add: "+exam);
	             logger.error(ExceptionUtils.getStackTrace(e)); 
	             System.out.print(ExceptionUtils.getStackTrace(e));
	             success = false;
			 }
			
			
		}
		
		return success;
	}

	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.result.SchoolFinalResultDAO#deductPoints(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deductPoints(Exam exam,double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE FinalResult " +
						"SET Points = (SELECT Points FROM FinalResult WHERE StudentUuid=? "
						+ ") -?" +				
						"WHERE Uuid = (SELECT Uuid FROM FinalResult WHERE StudentUuid=? );");	
				
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE FinalResult " +
						"SET Grade = ?, Remarks =? WHERE StudentUuid=?;" );	
				
				
				
				
        		){
			  if(exam instanceof FinalResult ){
				    pstmt.setString(1, exam.getStudentUuid());
				    pstmt.setDouble(2, Points);
		            pstmt.setString(3, exam.getStudentUuid());
		            pstmt.executeUpdate(); 
		            
		            pstmt2.setString(1, exam.getGrade());
		            pstmt2.setString(2, exam.getRemarks());
		            pstmt2.setString(3, exam.getStudentUuid());
		            pstmt2.executeUpdate(); 
		        		            
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to deduct: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.result.SchoolFinalResultDAO#getAllFinalResult()
	 */
	@Override
	public List<FinalResult> getAllFinalResult() {
		
		List<FinalResult> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM FinalResult ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, FinalResult.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all FinalResult");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
		return list;
	}

	

}
