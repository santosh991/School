/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.guardian.StudentRelative;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class RelativesDAO extends DBConnectDAO implements SchoolRelativesDAO {
	private static RelativesDAO relativesDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static RelativesDAO getInstance(){
		
		if(relativesDAO == null){
			relativesDAO = new RelativesDAO();		
		}
		return relativesDAO;
	}
	
	/**
	 * 
	 */
	public RelativesDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public RelativesDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolRelativesDAO#getStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)
	 */
	
	public StudentRelative getStudentRelative(StudentRelative Relative) {
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_relative"
           	      		+ " WHERE studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Relative.getStudentUuid()); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 Relative  = beanProcessor.toBean(rset,StudentRelative.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentRelative : " + Relative);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return Relative; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolRelativesDAO#putStudentRelative(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentRelative)
	 */
	
	public boolean putStudentRelative(StudentRelative relative) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student_relative ("
						+ "StudentUuid "
    			+ "RelativeName,RelativePhone,NationalID) VALUES (?,?,?,?);");
    		){
			   
			    pstmt.setString(1, relative.getStudentUuid());
			    
			    pstmt.setString(2, relative.getRelativeName());
	            pstmt.setString(3, relative.getRelativePhone());
	            pstmt.setString(4, relative.getNationalID());
	           
	    	    pstmt.executeUpdate();

			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put StudentRelative: "+relative);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolRelativesDAO#editStudentRelative(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentRelative)
	 */
	public boolean editStudentRelative(StudentRelative relative) {
		boolean success = true;
		  try (  Connection conn = dbutils.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement("UPDATE student_relative SET RelativeName=?,"
    			+ "RelativePhone=?, NationalID=? WHERE studentUuid = ?;");
    	) {		    
			    pstmt.setString(1, relative.getRelativeName());
	            pstmt.setString(2, relative.getRelativePhone());
	            pstmt.setString(3, relative.getNationalID());
	            pstmt.setString(4, relative.getStudentUuid());
	          
	            pstmt.executeUpdate();

    } catch (SQLException e) {
        logger.error("SQL Exception when updating Student_Parent: " + relative);
        logger.error(ExceptionUtils.getStackTrace(e));
        success = false;
    } 
    		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolRelativesDAO#deleteStudentRelative(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentRelative)
	 */
	@Override
	public boolean deleteStudentRelative(StudentRelative relative) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolRelativesDAO#getAllStudentRelative()
	 */
	@Override
	public List<StudentRelative> getAllStudentRelative() {
		List<StudentRelative>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM student_relative ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, StudentRelative.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all StudentRelative");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	
	}

}
