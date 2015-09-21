/**
 * 
 */
package com.yahoo.petermwenda83.dao.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.student.Primary;
import com.yahoo.petermwenda83.dao.DBConnectDAO;

/**
 * @author peter
 *
 */
public class PrimaryDAO extends DBConnectDAO implements SchoolPrimaryDAO {
	
	private static PrimaryDAO primaryDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static PrimaryDAO getInstance(){
		if(primaryDAO == null){
			primaryDAO = new PrimaryDAO();
		}
		return primaryDAO;
	}

	/**
	 * 
	 */
	public PrimaryDAO() {
		super();
	}
	public PrimaryDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolPrimaryDAO#get(java.lang.String)
	 */
	@Override
	public Primary get(String StudentUuid) {
		Primary primary = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentPrimary"
           	      		+ " WHERE StudentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, StudentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 primary  = beanProcessor.toBean(rset,Primary.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting Student Primary details with StudentUuid: " + StudentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e)); 
        }
        
		return primary; 
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolPrimaryDAO#put(com.yahoo.petermwenda83.bean.student.Primary)
	 */
	@Override
	public boolean put(Primary primary) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO StudentPrimary" 
			        		+"(Uuid, StudentUuid, SchoolName,Index,KcpeYear,KcpeMarks)"
			        		+ " VALUES (?,?,?,?,?,?);");
		){
			   
	            pstmt.setString(1, primary.getUuid());
	            pstmt.setString(2, primary.getStudentUuid());
	            pstmt.setString(3, primary.getSchoolname());
	            pstmt.setString(4, primary.getIndex());
	            pstmt.setString(5, primary.getKcpeyear());
	            pstmt.setString(6, primary.getKcpemarks()); 
	          
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put primary: "+primary);
              logger.error(ExceptionUtils.getStackTrace(e)); 
              System.out.println(ExceptionUtils.getStackTrace(e)); 
               success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolPrimaryDAO#edit(com.yahoo.petermwenda83.bean.student.Primary, java.lang.String)
	 */
	@Override
	public boolean edit(Primary primary, String StudentUuid) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentPrimary SET SchoolName =?," 
			        +"Index =?,KcpeYear =?,KcpeMarks =? WHERE StudentUuid = ?; ");
		){
	            
	            pstmt.setString(1, primary.getSchoolname());
	            pstmt.setString(2, primary.getIndex());
	            pstmt.setString(3, primary.getKcpeyear());
	            pstmt.setString(4, primary.getKcpemarks()); 
	            pstmt.setString(5, primary.getStudentUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put primary: "+primary);
             logger.error(ExceptionUtils.getStackTrace(e));  
              System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolPrimaryDAO#delete(com.yahoo.petermwenda83.bean.student.Primary, java.lang.String)
	 */
	@Override
	public boolean delete(Primary primary, String StudentUuid) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM StudentPrimary"
	         	      		+ " WHERE StudentUuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, primary.getStudentUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting primary : " +primary);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.registration.SchoolPrimaryDAO#getAllPrimary()
	 */
	@Override
	public List<Primary> getAllPrimary() {
		List<Primary> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM StudentPrimary;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, Primary.class);

	  } catch(SQLException e){
	  	 logger.error("SQL Exception when getting all Students Primary School details");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

}
