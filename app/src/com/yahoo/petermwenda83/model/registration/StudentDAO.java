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
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.student.Student;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class StudentDAO extends DBConnectDAO implements SchoolStudentDAO {
     

	private static StudentDAO studentDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentDAO getInstance(){
		
		if(studentDAO == null){
			studentDAO = new StudentDAO();		
		}
		return studentDAO;
	}
	
	/**
	 * 
	 */
	public StudentDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public StudentDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#getStudent(java.lang.String)
	 */
	
	public Student getStudent(String Uuid) {
		Student student = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 student  = beanProcessor.toBean(rset,Student.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with uuid: " + Uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return student; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#getStudents(java.lang.String)
	 */
	
	public Student getStudents(String admno) {
		Student student = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student WHERE admno = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, admno);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 student  = beanProcessor.toBean(rset,Student.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with admno: " + admno);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return student; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#getStudents(com.yahoo.petermwenda83.contoller.student.StudentSuper)
	 */
	@Override
	public boolean getStudents(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#putStudents(com.yahoo.petermwenda83.contoller.student.Student)
	 */
	public boolean putStudents(Student student) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student" 
			        		+"(Uuid, Firstname, Lastname,Surname,Admno,Year,DOB,Bcertno,Admissiondate)"
			        		+ " VALUES (?,?,?,?,?,?,?,?,?);");
		){
			   
	            pstmt.setString(1, student.getUuid());
	            pstmt.setString(2, student.getFirstname());
	            pstmt.setString(3, student.getLastname());
	            pstmt.setString(4, student.getSurname());
	            pstmt.setString(5, student.getAdmno());
	            pstmt.setString(6, student.getYear());
	            pstmt.setString(7, student.getDOB());
	            pstmt.setString(8, student.getBcertno());
	            pstmt.setTimestamp(9, new Timestamp(student.getAdmissiondate().getTime()));
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Student: "+student);
     logger.error(ExceptionUtils.getStackTrace(e)); 
     success = false;
		 }
		 
		
		
		return success;
	}

	
	
	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#editStudents(com.yahoo.petermwenda83.contoller.student.Student)
	 */
	public boolean editStudents(Student student) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Student  SET Firstname =?," 
			        +"Lastname =?,Surname =?,Year =?,DOB =?,"
			        + "Bcertno =? WHERE Admno = ?; ");
		){
			  
	            pstmt.setString(1, student.getFirstname());
	            pstmt.setString(2, student.getLastname());
	            pstmt.setString(3, student.getSurname());	           
	            pstmt.setString(4, student.getYear());
	            pstmt.setString(5, student.getDOB());
	            pstmt.setString(6, student.getBcertno());
	            pstmt.setString(7, student.getAdmno());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Student: "+student);
             logger.error(ExceptionUtils.getStackTrace(e));  
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		 
		
		
		return success;
	}
	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#deleteStudents(com.yahoo.petermwenda83.contoller.student.Student)
	 */
	public boolean deleteStudents(Student student) {
		 boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Student"
	         	      		+ " WHERE Uuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, student.getUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting student : " +student);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}
	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.SchoolStudentDAO#getAllStudents()
	 */
	public List<Student> getAllStudents() {
	List<Student> list = null;

	 try(   
  		Connection conn = dbutils.getConnection();
  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student;");   
  		ResultSet rset = pstmt.executeQuery();
		) {
  	
      list = beanProcessor.toBeanList(rset, Student.class);

  } catch(SQLException e){
  	logger.error("SQL Exception when getting all Student");
     logger.error(ExceptionUtils.getStackTrace(e));
     System.out.println(ExceptionUtils.getStackTrace(e)); 
  }

	
	return list;
	}

	


}
