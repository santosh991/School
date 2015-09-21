/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.cat.CatResults;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class CatResultsDAO extends DBConnectDAO  implements SchoolCatResultsDAO {
	
	private static CatResultsDAO catResultsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static CatResultsDAO getInstance() {
		if(catResultsDAO == null){
			catResultsDAO = new CatResultsDAO();		
			}
		return catResultsDAO;
	}
	
	public CatResultsDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public CatResultsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#getCatResults(java.lang.String)
	 */
	@Override
	public CatResults getCatResults(String StudentUuid) {
		CatResults catResults = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CatResult "
        	      		+ "WHERE StudentUuid = ?;");       
    		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 catResults  = beanProcessor.toBean(rset,CatResults.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting CatResults with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return catResults; 
	}
	
	/**
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#hasCatResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double)
	 */
	@Override
	public boolean hasCatResult(Exam exam,Double Totals,Double Points) {
		boolean hasCatTotal = false;
		double Total = 0;
		double Point = 0;
		try(   Connection conn = dbutils.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT Total,Points FROM CatResult "
	        			+ "WHERE StudentUuid = ?;");
				
      		){
			 
	             pstmt.setString(1, exam.getStudentUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						Total = rset.getDouble("Total");	
						Point = rset.getDouble("Points");
						
						//System.out.println(Total+" >= "+Totals);
						//System.out.println(Point+" >= "+Points);
						
						hasCatTotal = (Total >= Totals) ? true : false;
						hasCatTotal = (Point >= Points) ? true : false;
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit student CatResult: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasCatTotal = false;
		 }
		
		return hasCatTotal;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#addCatMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean addCatMark(Exam exam,Double Total,Double Points) {
		boolean success = true;
		if(hasCatResult(exam,0.0,0.0)) {
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE CatResult " +
						"SET Total = (SELECT Total FROM CatResult WHERE StudentUuid=? "
						+ ") +?" +				
						"WHERE Uuid = (SELECT Uuid FROM CatResult WHERE StudentUuid=? );");	
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE CatResult " +
						"SET Points = (SELECT Points FROM CatResult WHERE StudentUuid=? "
						+ ") +?" +				
						"WHERE Uuid = (SELECT Uuid FROM CatResult WHERE StudentUuid=? );");		
				
			   PreparedStatement pstmt3 = conn.prepareStatement("UPDATE CatResult " +
						"SET Grade =? WHERE StudentUuid=? ;");		
				
		      PreparedStatement pstmt4 = conn.prepareStatement("UPDATE CatResult " +
						"SET Remarks =? WHERE StudentUuid=? ;");	  
				
				
		
        		){
			  if(exam instanceof CatResults ){
	            pstmt.setString(1, exam.getStudentUuid());
	            pstmt.setDouble(2, Total);
	            pstmt.setString(3, exam.getStudentUuid());
	            pstmt.executeUpdate();
	            
	            pstmt2.setString(1, exam.getStudentUuid());
	            pstmt2.setDouble(2, Points);
	            pstmt2.setString(3, exam.getStudentUuid());
	            pstmt2.executeUpdate();
	            
	            
	            pstmt3.setString(1, exam.getGrade());
	            pstmt3.setString(2, exam.getStudentUuid());
	            pstmt3.executeUpdate();
	            
	          
	           // exam = (Exam) catResultsDAO.getAllCatResults();
	            pstmt4.setString(1, exam.getRemarks());
	            pstmt4.setString(2, exam.getStudentUuid());
	            pstmt4.executeUpdate();   
	            
	           // System.out.println(exam.getGrade());
	           // System.out.println(exam.getRemarks());
			  
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		
		
		}else{
			try(   Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CatResult" 
				        	+"(Uuid,studentuuid,total,"
				        		+ "points,grade,position,remarks,submitdate) VALUES (?,?,?,?,?,?,?,?);");
	        		){
				  if(exam instanceof CatResults ){
		            pstmt.setString(1, exam.getUuid());
		            pstmt.setString(2, exam.getStudentUuid());
		            pstmt.setDouble(3, Total); 
		            pstmt.setDouble(4, Points);
		            pstmt.setString(5, exam.getGrade());
		            pstmt.setInt(6, exam.getPosition()); 
		            pstmt.setString(7, exam.getRemarks());
		            pstmt.setTimestamp(8,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt.executeUpdate();
				   
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
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatResultsDAO#deductCatMark(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)
	 */
	@Override
	public boolean deductCatMark(Exam exam, Double Total, Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE CatResult " +
						"SET Total = (SELECT Total FROM CatResult WHERE StudentUuid=? "
						+ ") -?" +				
						"WHERE Uuid = (SELECT Uuid FROM CatResult WHERE StudentUuid=? );");	
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE CatResult " +
						"SET Points = (SELECT Points FROM CatResult WHERE StudentUuid=? "
						+ ") -?" +				
						"WHERE Uuid = (SELECT Uuid FROM CatResult WHERE StudentUuid=? );");		
	
		
        		){
			  if(exam instanceof CatResults ){
	            pstmt.setString(1, exam.getStudentUuid());
	            pstmt.setDouble(2, Total);
	            pstmt.setString(3, exam.getStudentUuid());
	            pstmt.executeUpdate();
	            
	            pstmt2.setString(1, exam.getStudentUuid());
	            pstmt2.setDouble(2, Points);
	            pstmt2.setString(3, exam.getStudentUuid());
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
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#deleteCatMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deleteCatMark(Exam exam) {
		boolean success = true; 
        try(
  		  Connection conn = dbutils.getConnection();
          PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CatResult WHERE"
    		+ " StudentUuid = ?;");       
  		      ){
        	 if(exam instanceof CatResults ){
  	          pstmt.setString(1,exam.getStudentUuid());
              pstmt.executeUpdate();
        	 }
     
       }catch(SQLException e){
  	   logger.error("SQL Exception when deletting Exam : " +exam);
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
       success = false;
       
  }
  
	return success; 
	
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatResultsDAO#getAllCatResults()
	 */
	@Override
	public List<CatResults> getAllCatResults() {
		List<CatResults> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatResult ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, CatResults.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all ExamType");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

	

	

}
