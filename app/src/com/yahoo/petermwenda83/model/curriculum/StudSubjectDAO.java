/**
 * 
 */
package com.yahoo.petermwenda83.model.curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.student.StudentSubject;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class StudSubjectDAO extends DBConnectDAO  implements SchoolStudSubjectDAO {

	private static StudSubjectDAO studSubjectDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * 
	 * @return subjectDAO
	 */
	public static StudSubjectDAO getInstance(){
		if(studSubjectDAO == null){
			studSubjectDAO = new StudSubjectDAO();		
		}
		return studSubjectDAO;
	}
	
	/**
	 * 
	 */
	public StudSubjectDAO() {
		super();
	}

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public StudSubjectDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolStudSubjectDAO#getStudentSubject(java.lang.String)
	 */
	@Override
	public StudentSubject getStudentSubject(String Uuid) {
		StudentSubject StuSubject = null;
        ResultSet rset = null;
        try(
        		 Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Subject WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 StuSubject  = beanProcessor.toBean(rset,StudentSubject.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an StudentSubject with uuid: " + Uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return StuSubject; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolStudSubjectDAO#putStudentSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean putStudentSubject(StudentSubject stusubject) {
		boolean success = true;
		
		 try(
      		 Connection conn = dbutils.getConnection();
				 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Subject" 
			        		+"(Uuid, studentUuid, subjectUuid) VALUES (?,?,?);");
      		){
			
			    pstmt.setString(1, stusubject.getUuid());
	            pstmt.setString(2, stusubject.getStudentUuid());
	            pstmt.setString(3, stusubject.getSubjectUuid());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: " + stusubject);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           success = false;
		 }
		
		
		return success;
	}

	@Override
	public boolean editSubject(StudentSubject subject, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolStudSubjectDAO#deleteSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean deleteSubject(StudentSubject suject) {
		boolean success = true; 
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_Subject WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, suject.getUuid());
	         pstmt.executeUpdate();
	     
        }catch(SQLException e){
        	 logger.error("SQL Exception when deletting: " + suject);
             logger.error(ExceptionUtils.getStackTrace(e));
             success = false;
            
        }
        
		return success; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentSubjectDAO#getAllStudentSubject()
	 */
	@Override
	public List<StudentSubject> getAllStudentSubject() {
		List<StudentSubject>  list = null;
		
		 try(   
      		Connection conn = dbutils.getConnection();
      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Subject;");   
      		ResultSet rset = pstmt.executeQuery();
  		) {
      	
          list = beanProcessor.toBeanList(rset, StudentSubject.class);

      } catch(SQLException e){
      	logger.error("SQL Exception when getting all StudentSubject");
          logger.error(ExceptionUtils.getStackTrace(e));
      }
    
		
		return list;
	}


}