/**
 * 
 */
package com.yahoo.petermwenda83.persistence.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.StudentPhoto;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class StudentPhotoDAO extends GenericDAO implements SchoolStudentPhotoDAO {


	private static StudentPhotoDAO studentPhotoDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentPhotoDAO getInstance(){
		
		if(studentPhotoDAO == null){
			studentPhotoDAO = new StudentPhotoDAO();		
		}
		return studentPhotoDAO;
	}
	
	/**
	 * 
	 */
	public StudentPhotoDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public StudentPhotoDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	/**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentPhoto#getPhoto(java.lang.String)
	 */
	public StudentPhoto getPhoto(String StudentUuid) {
		StudentPhoto photo = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentPhoto WHERE StudentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, StudentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 photo  = beanProcessor.toBean(rset,StudentPhoto.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an StudentPhoto with StudentUuid: " + StudentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return photo; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentPhoto#putPhoto(com.yahoo.petermwenda83.bean.student.StudentPhoto)
	 */
	@Override
	public boolean putPhoto(StudentPhoto photo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentPhoto#editPhoto(com.yahoo.petermwenda83.bean.student.StudentPhoto)
	 */
	@Override
	public boolean editPhoto(StudentPhoto photo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentPhoto#deletePhoto(com.yahoo.petermwenda83.bean.student.StudentPhoto)
	 */
	@Override
	public boolean deletePhoto(StudentPhoto photo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentPhoto#getAllPhotos()
	 */
	public List<StudentPhoto> getAllPhotos() {
		List<StudentPhoto> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM StudentPhoto;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, StudentPhoto.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all StudentPhoto");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }
		return list;

	}

}
