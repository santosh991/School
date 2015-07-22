/**
 * 
 */
package com.yahoo.petermwenda83.model.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	

	public ExamDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

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
        }
        
		return examType; 
		 
	}

	@Override
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
      	 logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
           logger.error(ExceptionUtils.getStackTrace(e));
      }
      
		return mainMarks; 
	}

	@Override
	public MainResults getExamResults(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatMarks getCatMarks(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatResults getCatResults(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam putExamType(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam putExamMarks(Exam exam, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam putExamResults(Exam exam, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam editExamType(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam editExamMarks(Exam exam, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam editExamResults(Exam exam, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteExamType(String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteExamMarks(String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteExamResults(String uuid) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
