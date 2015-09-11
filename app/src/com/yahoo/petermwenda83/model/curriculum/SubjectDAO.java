/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
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

import com.yahoo.petermwenda83.contoller.student.Subject;
import com.yahoo.petermwenda83.model.DBConnectDAO;



/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class SubjectDAO extends DBConnectDAO implements SchoolSubjectDAO {

	private static SubjectDAO subjectDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * 
	 * @return subjectDAO
	 */
	public static SubjectDAO getInstance(){
		if(subjectDAO == null){
		subjectDAO = new SubjectDAO();		
		}
		return subjectDAO;
	}
	
	/**
	 * 
	 */
	public SubjectDAO() {
		super();
	}

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public SubjectDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	
	/**
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolSubjectDAO#getSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject.SubjectUi, com.yahoo.petermwenda83.view.InfoBsic, java.lang.String)
	 */
	@Override
	public Subject getSubject(String uuid) {
		Subject Subject = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Subject WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	Subject  = beanProcessor.toBean(rset,Subject.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an subject with uuid: " + uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return Subject; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolSubjectDAO#getSubjects(java.lang.String)
	 */
	@Override
	public Subject getSubjects(String subjectcode) {
		Subject Subject = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Subject WHERE Subjectcode = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, subjectcode);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	Subject  = beanProcessor.toBean(rset,Subject.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an subject with subjectcode: " + subjectcode);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return Subject; 
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolSubjectDAO#putSubject(com.yahoo.petermwenda83.contoller.student.Subject)
	 */
	@Override
	public boolean putSubject(Subject subject) {
		boolean success = true;
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Subject" 
			        		+"(Uuid, subjectcode, subjectname, subjectcategory) VALUES (?,?,?,?);");
        		){
			   
	            pstmt.setString(1, subject.getUuid());
	            pstmt.setString(2, subject.getSubjectcode());
	            pstmt.setString(3, subject.getSubjectname());
	            pstmt.setString(4, subject.getSubjectcategory());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+subject);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}


	/**
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolSubjectDAO#editSubject(com.yahoo.petermwenda83.contoller.student.Subject, java.lang.String)
	 */
	@Override
	public boolean editSubject(Subject subject,String uuid) {
		boolean success = true;
        try (  Connection conn = dbutils.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement("UPDATE Subject SET subjectcode=?, "
        			+ "subjectname=?, subjectcategory=? WHERE Uuid = ?;");
        	) {
            
            pstmt.setString(1, subject.getSubjectcode());
            pstmt.setString(2, subject.getSubjectname());
            pstmt.setString(3, subject.getSubjectcategory());
            pstmt.setString(4, subject.getUuid());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL Exception when updating SubjectUi with uuid " + subject);
            logger.error(ExceptionUtils.getStackTrace(e));
            success = false;
        } 
        
        return success;
		
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolSubjectDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Subject)
	 */
	@Override
	public boolean deleteStudent(Subject subject) {
		boolean success = true; 
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Subject WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, subject.getUuid());
	         pstmt.executeUpdate();
	     
        }catch(SQLException e){
        	 logger.error("SQL Exception when deletting: " + subject);
             logger.error(ExceptionUtils.getStackTrace(e));
             success = false;
             
        }
        
		return success; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.curriculum.SchoolSubjectDAO#getAllStudent()
	 */
	@Override
	public List<Subject> getAllSubjects() {
		List<Subject>  list = null;
		
		 try(   
        		Connection conn = dbutils.getConnection();
        		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Subject;");   
        		ResultSet rset = pstmt.executeQuery();
    		) {
        	
            list = beanProcessor.toBeanList(rset, Subject.class);

        } catch(SQLException e){
        	logger.error("SQL Exception when getting all SubjectUi");
            logger.error(ExceptionUtils.getStackTrace(e));
        }
      
		
		return list;
	}

	
}
