/**##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without approval of from
 * ###### owner.#############################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.model.registration;

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
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class StudentSubjectDAO extends DBConnectDAO implements SchoolStudentSubjectDAO {
	
	private static StudentSubjectDAO studentSubjectDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentSubjectDAO getInstance(){
		
		if(studentSubjectDAO == null){
			studentSubjectDAO = new StudentSubjectDAO();		
		}
		return studentSubjectDAO;
	}
	
	/**
	 * 
	 */
	public StudentSubjectDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public StudentSubjectDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentSubjectDAO#getStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public StudentSubject getStudentSubject(String StudentUuid) {
		StudentSubject stusubject = null;
		ResultSet rset = null;
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Subject "
						+ "WHERE StudentUuid =?;");
		){
	            pstmt.setString(1, StudentUuid);
	            rset = pstmt.executeQuery();
	                while(rset.next()){
	                	stusubject  = beanProcessor.toBean(rset,StudentSubject.class);
			         }
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+stusubject);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
            
		 }		
		return stusubject;
	}
	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentSubjectDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean putStudentSubject(StudentSubject stusubject) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Subject" 
			        		+"(Uuid, StudentUuid, SubjectUuid,roomnameUuid) VALUES (?,?,?,?);");
		){
			   
	            pstmt.setString(1, stusubject.getUuid());
	            pstmt.setString(2, stusubject.getStudentUuid());
	            pstmt.setString(3, stusubject.getSubjectUuid());
	            pstmt.setString(4, stusubject.getRoomnameUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+stusubject);
             logger.error(ExceptionUtils.getStackTrace(e)); 
              System.out.println(ExceptionUtils.getStackTrace(e));
              success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentSubjectDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject, java.lang.String)
	 */
	@Override
	public boolean editStudentSubject(StudentSubject stusubject) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
  	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Subject SET roomnameUuid =? "
  			+ "WHERE SubjectUuid=? AND StudentUuid = ?;");
  	) {
	           
	           
	            pstmt.setString(1, stusubject.getRoomnameUuid());
	            pstmt.setString(2, stusubject.getSubjectUuid());
	            pstmt.setString(3, stusubject.getStudentUuid());
	            pstmt.executeUpdate();

  } catch (SQLException e) {
      logger.error("SQL Exception when updating editStudent " + stusubject);
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e));
      success = false;
  } 
  		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentSubjectDAO#deleteStudentSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean deleteStudentSubject(StudentSubject stusubject) {
		  boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student_Subject"
	         	      		+ " WHERE StudentUuid = ? AND SubjectUuid =?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, stusubject.getStudentUuid());
	      	 pstmt.setString(2, stusubject.getSubjectUuid()); 
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting pastusubjectrent : " +stusubject);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentSubjectDAO#getAllStudentSubject()
	 */
	@Override
	public List<StudentSubject> getAllStudentSubject() {
		List<StudentSubject>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Subject ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, StudentSubject.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all StudentParent");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	}

}
