/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

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
import com.yahoo.petermwenda83.contoller.exam.main.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.main.MainResults;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class MainSubMarkDAO extends DBConnectDAO  implements SchoolMainSubMarkDAO {

	private static MainSubMarkDAO mainSubMarkDAO;
	
	private static MainResultsDAO mainResultsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static MainSubMarkDAO getInstance() {
		if(mainSubMarkDAO == null){
			mainSubMarkDAO = new MainSubMarkDAO();		
			}
		return mainSubMarkDAO;
	}
	
	public MainSubMarkDAO() {
		super();
		mainResultsDAO = MainResultsDAO.getInstance();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public MainSubMarkDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		mainResultsDAO = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainSubMarkDAO#getMainMark(java.lang.String, java.lang.String)
	 */
	@Override
	public MainMarks getMainMark(String StudentUuid, String Subjectuuid) {
		MainMarks mainMarks = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MainSubjectMark"
        	      		+ " WHERE StudentUuid = ? AND Subjectuuid=?;");       
     		
     		){
     	
     	 pstmt.setString(1, StudentUuid);
     	 pstmt.setString(2, Subjectuuid);
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

	@Override
	public boolean hasMainMark(Exam exam,Double Total) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainSubMarkDAO#addMainMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean addMainMark(Exam exam,Double Marks,Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO MainSubjectMark" 
		        		+"(Uuid, StudentUuid,SubjectUuid,"
		        		+ "Marks,Percent,Grade,Points,Submitdate) VALUES (?,?,?,?,?,?,?,?);");
        		){
			  if(exam instanceof MainMarks ){
				    pstmt2.setString(1, exam.getUuid());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setString(3, exam.getSubjectUuid());
		            pstmt2.setDouble(4, exam.getMarks());
		            pstmt2.setDouble(5, exam.getPercent());
		            pstmt2.setString(6, exam.getGrade());
		            pstmt2.setDouble(7, exam.getPoints());
		            pstmt2.setTimestamp(8,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt2.executeUpdate(); 
		            
		            MainResults mr = new MainResults();
		            mr.setStudentUuid(exam.getStudentUuid()); 
		            mr.setRemarks(exam.getRemarks()); 
		            mr.setGrade(exam.getGrade()); 
		            mainResultsDAO.addMainResult(mr, Marks, Points);
		            
		           
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
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainSubMarkDAO#editMainMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean editMainMark(Exam exam,Double Marks,Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE MainSubjectMark SET marks =?,Points =?, "
						+ "submitdate =? "
						+ " WHERE StudentUuid = ? AND SubjectUuid = ? ;");
        		){
			  if(exam instanceof MainMarks ){
				    pstmt.setDouble(1, exam.getMarks());
				    pstmt.setDouble(2, exam.getPoints());
		            pstmt.setTimestamp(3, new Timestamp(exam.getSubmitdate().getTime()));
		            pstmt.setString(4, exam.getStudentUuid());
		            pstmt.setString(5, exam.getSubjectUuid());
		            pstmt.executeUpdate(); 
		            
		            MainResults mr = new MainResults();
		            mr.setStudentUuid(exam.getStudentUuid()); 
		            mainResultsDAO.addMainResult(mr, Marks, Points);
	           
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to update Exam: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainSubMarkDAO#deleteMainMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deleteMainMark(Exam exam) {
		 boolean success = true; 
         try(
   		  Connection conn = dbutils.getConnection();     
          PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM MainSubjectMark WHERE"
      	       + " StudentUuid = ? AND SubjectUuid = ?;");       
      	      		
   		      ){
         	 if(exam instanceof MainMarks ){
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
	

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolMainSubMarkDAO#getAllMainSubMark()
	 */
	@Override
	public List<MainMarks> getAllMainSubMark() {
		List<MainMarks> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM MainSubjectMark ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, MainMarks.class);

	      } catch(SQLException e){
	      	logger.error("SQL Exception when getting all ExamType");
	        logger.error(ExceptionUtils.getStackTrace(e));
	        System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

	

}
