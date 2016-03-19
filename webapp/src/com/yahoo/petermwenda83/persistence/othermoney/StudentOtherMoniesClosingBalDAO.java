/**
 * 
 */
package com.yahoo.petermwenda83.persistence.othermoney;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMoniesClosingBal;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/** 
 * @author peter
 *
 */
public class StudentOtherMoniesClosingBalDAO extends GenericDAO implements SchoolStudentOtherMoniesClosingBalDAO {

	private static StudentOtherMoniesClosingBalDAO studentOtherMoniesClosingBalDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static StudentOtherMoniesClosingBalDAO getInstance() {
		if(studentOtherMoniesClosingBalDAO == null){
			studentOtherMoniesClosingBalDAO = new StudentOtherMoniesClosingBalDAO();		
			}
		return studentOtherMoniesClosingBalDAO;
	}
	
	public StudentOtherMoniesClosingBalDAO() {
		super();
		
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public StudentOtherMoniesClosingBalDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolStudentOtherMoniesClosingBalDAO#getStudentOtherMoniesClosingBal(java.lang.String)
	 */
	@Override
	public StudentOtherMoniesClosingBal getStudentOtherMoniesClosingBal(String studentUuid,String OtherstypeUuid) {
		StudentOtherMoniesClosingBal studentOtherMoniesClosingBal = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentOtherMoniesClosingBal WHERE studentUuid = ? AND OtherstypeUuid =?;");       
        		
        		){
        	 pstmt.setString(1, studentUuid);
        	 pstmt.setString(2, OtherstypeUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentOtherMoniesClosingBal  = beanProcessor.toBean(rset,StudentOtherMoniesClosingBal.class);
	   }
        		
        }catch(SQLException e){
        	 logger.error("SQL Exception while getting studentOtherMoniesClosingBal with StudentUuid: " + studentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e)); 
        }
       
		return studentOtherMoniesClosingBal;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolStudentOtherMoniesClosingBalDAO#putStudentOtherMoniesClosingBal(com.yahoo.petermwenda83.bean.othermoney.StudentOtherMoniesClosingBal)
	 */
	@Override
	public boolean putStudentOtherMoniesClosingBal(StudentOtherMoniesClosingBal studentOtherMoniesClosingBal) {
		boolean success = true;
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO StudentOtherMoniesClosingBal" 
			        		+"(Uuid,StudentUuid,OtherstypeUuid,Balance) VALUES (?,?,?,?);");
    		){
			   
	            pstmt.setString(1, studentOtherMoniesClosingBal.getUuid());
	            pstmt.setString(2, studentOtherMoniesClosingBal.getStudentUuid());
	            pstmt.setString(3, studentOtherMoniesClosingBal.getOtherstypeUuid());
	            pstmt.setDouble(4, studentOtherMoniesClosingBal.getBalance());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			logger.error("SQL Exception trying to put studentOtherMoniesClosingBal: "+studentOtherMoniesClosingBal);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolStudentOtherMoniesClosingBalDAO#updateStudentOtherMoniesClosingBal(com.yahoo.petermwenda83.bean.othermoney.StudentOtherMoniesClosingBal)
	 */
	@Override
	public boolean updateStudentOtherMoniesClosingBal(StudentOtherMoniesClosingBal studentOtherMoniesClosingBal) {
		boolean success = true;
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentOtherMoniesClosingBal SET Balance = ? WHERE StudentUuid =?"
						+ "AND OtherstypeUuid =?;");
    		){
			   
	           
	            pstmt.setDouble(1, studentOtherMoniesClosingBal.getBalance());
	            pstmt.setString(2, studentOtherMoniesClosingBal.getStudentUuid());
	            pstmt.setString(3, studentOtherMoniesClosingBal.getOtherstypeUuid());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			logger.error("SQL Exception trying to put studentOtherMoniesClosingBal: "+studentOtherMoniesClosingBal);
          logger.error(ExceptionUtils.getStackTrace(e)); 
          System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolStudentOtherMoniesClosingBalDAO#getStudentOtherMoniesClosingBalList()
	 */
	@Override
	public List<StudentOtherMoniesClosingBal> getStudentOtherMoniesClosingBalList() {
		List<StudentOtherMoniesClosingBal>  list = null;		
		 try(   
     		Connection conn = dbutils.getConnection();
     		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM StudentOtherMoniesClosingBal =?;");          		
 		) {			     
			 try( ResultSet rset = pstmt.executeQuery();){
	     	       
				 list = beanProcessor.toBeanList(rset, StudentOtherMoniesClosingBal.class);
	         	   }
			
        

     } catch(SQLException e){
     	logger.error("SQL Exception when getting all OtherMonies");
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
     }
   
		
		return list;
	}

}
