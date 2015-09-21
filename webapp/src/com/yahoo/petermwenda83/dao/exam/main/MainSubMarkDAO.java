/**
 * 
 */
package com.yahoo.petermwenda83.dao.exam.main;

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
import com.yahoo.petermwenda83.bean.exam.main.MainMarks;
import com.yahoo.petermwenda83.bean.exam.main.MainResults;
import com.yahoo.petermwenda83.bean.exam.results.FinalMark;
import com.yahoo.petermwenda83.bean.exam.results.FinalResult;
import com.yahoo.petermwenda83.dao.DBConnectDAO;
import com.yahoo.petermwenda83.dao.exam.result.FinalMarkDAO;
import com.yahoo.petermwenda83.dao.exam.result.FinalResultDAO;

/**
 * @author peter
 *
 */
public class MainSubMarkDAO extends DBConnectDAO  implements SchoolMainSubMarkDAO {

	private static MainSubMarkDAO mainSubMarkDAO;
	
	private static MainResultsDAO mainResultsDAO;
	private static FinalResultDAO finalResultDAO;
	private static FinalMarkDAO finalMarkDAO;
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
		finalResultDAO = FinalResultDAO .getInstance();
		finalMarkDAO =FinalMarkDAO.getInstance();
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
		finalResultDAO = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		finalMarkDAO = new FinalMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
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
	public boolean addMainMark(Exam exam,Double Percent,Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO MainSubjectMark" 
		        		+"(Uuid, StudentUuid,SubjectUuid,"
		        		+ "Marks,Submark,Percent,Grade,Points,Submitdate) VALUES (?,?,?,?,?,?,?,?,?);");
        		){
			if(exam instanceof MainMarks ){
				    pstmt2.setString(1, exam.getUuid());
		            pstmt2.setString(2, exam.getStudentUuid());
		            pstmt2.setString(3, exam.getSubjectUuid());
		            pstmt2.setDouble(4, exam.getMarks());//70 outof 100
		            pstmt2.setDouble(5, exam.getSubmark());//70/100*70=49
		            pstmt2.setDouble(6, Percent);// 70
		            pstmt2.setString(7, exam.getGrade()); //A-
		            pstmt2.setDouble(8, Points); //11
		            pstmt2.setTimestamp(9,  new Timestamp(exam.getSubmitdate().getTime()));
		            
		            pstmt2.executeUpdate(); 
		            
		            MainResults mr = new MainResults();
		            mr.setStudentUuid(exam.getStudentUuid()); 
		            mr.setRemarks(exam.getRemarks()); //excellent
		            mr.setGrade(exam.getGrade()); //A-
		            mainResultsDAO.addMainResult(mr, Percent, Points);// 70   11
			}if(exam instanceof FinalResult ){
		            
		            FinalResult fr = new FinalResult();
		            fr.setUuid(exam.getUuid()); 
		            fr.setStudentUuid(exam.getStudentUuid()); 
		            fr.setGrade(exam.getGrade());//depends on submark
		            fr.setRemarks(exam.getRemarks()); //depends on submark
		           finalResultDAO.addPoints(fr, Points);//Points depend on submark
		           
			}if(exam instanceof FinalMark ){
		           FinalMark fm = new FinalMark();
		           fm.setUuid(exam.getUuid());
		           fm.setStudentUuid(exam.getStudentUuid());
		           fm.setSubjectUuid(exam.getSubjectUuid()); 
		           fm.setGrade(exam.getGrade()); 
		           double Marks = exam.getSubmark();
		           finalMarkDAO.addMark(fm, Marks); 
		            
		            
		            
		           
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
