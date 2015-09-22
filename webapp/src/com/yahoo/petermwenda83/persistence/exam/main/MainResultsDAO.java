/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.Exam;
import com.yahoo.petermwenda83.bean.exam.main.MainResults;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class MainResultsDAO extends DBConnectDAO  implements SchoolMainResultsDAO {

	private static MainResultsDAO mainResultsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static MainResultsDAO getInstance() {
		if(mainResultsDAO == null){
			mainResultsDAO = new MainResultsDAO();		
			}
		return mainResultsDAO;
	}
	
	public MainResultsDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public MainResultsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainResultsDAO#getMainResult(java.lang.String)
	 */
	@Override
	public MainResults getMainResult(String StudentUuid) {
		MainResults mainResults = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MainResult WHERE"
        	      		+ " StudentUuid =?;");       
     		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
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
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainResultsDAO#hasMainResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double)
	 */
	@Override
	public boolean hasMainResult(Exam exam,Double Totals,Double Points) {
		boolean hasMainTotal = false;
		double Total = 0;
		double Point = 0;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Total,Points FROM MainResult "
	        			+ "WHERE StudentUuid = ?;");
      		){
			 
	            pstmt.setString(1, exam.getStudentUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						Total = rset.getDouble("Total");	
						Point = rset.getDouble("Points");	
						hasMainTotal = (Total >= Totals) ? true : false;	
						hasMainTotal = (Point >= Points) ? true : false;	
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit student Exam: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasMainTotal = false;
		 }
		 
		
		
		return hasMainTotal;
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainResultsDAO#addMainResult(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean addMainResult(Exam exam,Double Total,Double Points) {
		boolean success = true;
		//MainResults mainr = new MainResults();
		if(hasMainResult(exam,0.0,0.0)) {
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE MainResult " +
						"SET Total = (SELECT Total FROM MainResult WHERE StudentUuid=? "
						+ ") +?" +				
						"WHERE Uuid = (SELECT Uuid FROM MainResult WHERE StudentUuid=? );");	
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MainResult " +
						"SET Points = (SELECT Points FROM MainResult WHERE StudentUuid=? "
						+ ") +?" +				
						"WHERE Uuid = (SELECT Uuid FROM MainResult WHERE StudentUuid=? );");		
				
			  PreparedStatement pstmt3 = conn.prepareStatement("UPDATE MainResult " +
						"SET Grade =? WHERE StudentUuid=?;");		
				
				PreparedStatement pstmt4 = conn.prepareStatement("UPDATE MainResult " +
						"SET Remarks =? WHERE StudentUuid=?;");	
				
        		){
			  if(exam instanceof MainResults ){
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
		            
		           
		            pstmt4.setString(1, exam.getRemarks());
		            pstmt4.setString(2, exam.getStudentUuid());
		            pstmt4.executeUpdate();  
		            
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		}else{
			
			try(   Connection conn = dbutils.getConnection();
					PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO MainResult" 
			        		+"(Uuid,Studentuuid,Total,"
			        		+ "Points,Grade,Position,Remarks,Submitdate) VALUES (?,?,?,?,?,?,?,?);");
	        		){
				  if(exam instanceof MainResults ){
					    pstmt2.setString(1, exam.getUuid());
			            pstmt2.setString(2, exam.getStudentUuid());
			            pstmt2.setDouble(3, exam.getTotal());
			            pstmt2.setDouble(4, exam.getPoints());
			            pstmt2.setString(5, exam.getGrade());
			            pstmt2.setInt(6, exam.getPosition()); 
			            pstmt2.setString(7, exam.getRemarks());
			            pstmt2.setTimestamp(8,  new Timestamp(exam.getSubmitdate().getTime()));
			            
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
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainResultsDAO#deductMainResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)
	 */
	@Override
	public boolean deductMainResult(Exam exam, Double Total, Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE MainResult " +
						"SET Total = (SELECT Total FROM MainResult WHERE StudentUuid=? "
						+ ") -?" +				
						"WHERE Uuid = (SELECT Uuid FROM MainResult WHERE StudentUuid=? );");	
				
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MainResult " +
						"SET Points = (SELECT Points FROM MainResult WHERE StudentUuid=? "
						+ ") -?" +				
						"WHERE Uuid = (SELECT Uuid FROM MainResult WHERE StudentUuid=? );");		
		
				
        		){
			  if(exam instanceof MainResults ){
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
	
	

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainResultsDAO#deleteMainResult(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deleteMainResult(Exam exam) {
		boolean success = true; 
        try(
  		  Connection conn = dbutils.getConnection();      
          PreparedStatement pstmt = conn.prepareStatement("DELETE FROM MainResult WHERE"
     	        		+ " StudentUuid = ?;");       
     	      		
  		      ){
        	 if(exam instanceof MainResults ){
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
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainResultsDAO#getAllMainResults()
	 */
	@Override
	public List<MainResults> getAllMainResults() {
		List<MainResults> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM MainResult ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, MainResults.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all ExamType");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
		return list;
	}

	

	
}
