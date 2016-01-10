/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.StudentExam;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class StudentExamDAO extends GenericDAO implements SchoolStudentExamDAO {
	
	private static StudentExamDAO studentExamDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentExamDAO getInstance(){
		
		if(studentExamDAO == null){
			studentExamDAO = new StudentExamDAO();		
		}
		return studentExamDAO;
	}
	
	/**
	 * 
	 */
	public StudentExamDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public StudentExamDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolStudentExamDAO#getStudentExam(java.lang.String)
	 */
	@Override
	public StudentExam getStudentExam(String schoolAccountUuid,String studentUuid) {
		StudentExam studentExam = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentExam WHERE SchoolAccountUuid =? AND studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
        	 pstmt.setString(2, studentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentExam  = beanProcessor.toBean(rset,StudentExam.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentExam for studentUuid " + studentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return studentExam; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolStudentExamDAO#putStudentExam(com.yahoo.petermwenda83.bean.exam.StudentExam)
	 */
	@Override
	public boolean putStudentExam(StudentExam studentExam) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO StudentExam" 
			        		+"(SchoolAccountUuid, StudentUuid, ClassRoomUuid)"
			        		+ " VALUES (?,?,?);");
		){
			   
	            pstmt.setString(1, studentExam.getSchoolAccountUuid());
	            pstmt.setString(2, studentExam.getStudentUuid());
	            pstmt.setString(3, studentExam.getClassRoomUuid());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put StudentExam: "+studentExam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolStudentExamDAO#getStudentExamList()
	 */
	@Override
	public List<StudentExam> getStudentExamList(String schoolAccountUuid,String classRoomUuid) {
		List<StudentExam> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentExam WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);  
         	   pstmt.setString(2, classRoomUuid);  
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, StudentExam.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting Students List of a StudentExam with schoolAccountUuid " + schoolAccountUuid ); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

}
