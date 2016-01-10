/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.staff.Staff;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class StaffDAO extends GenericDAO implements SchoolStaffDAO {
	
	private static StaffDAO staffDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StaffDAO getInstance(){
		
		if(staffDAO == null){
			staffDAO = new StaffDAO();		
		}
		return staffDAO;
	}
	
	/**
	 * 
	 */
	public StaffDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public StaffDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolStaffDAO#getStaff(java.lang.String)
	 */
	@Override
	public Staff getStaff(String schoolAccountUuid,String Uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolStaffDAO#getStaffByUsername(java.lang.String)
	 */
	@Override
	public Staff getStaffByUsername(String schoolAccountUuid,String username) {
		Staff staff = new Staff();
        ResultSet rset = null;
     try(
     		      Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Staff WHERE schoolAccountUuid =? AND Username = ?;");       
     		
     		){
     	
     	     pstmt.setString(1, schoolAccountUuid);
     	     pstmt.setString(2, username);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	    	 staff  = beanProcessor.toBean(rset,Staff.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting Staff with Username: " + username);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return staff; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolStaffDAO#putStaff(com.yahoo.petermwenda83.bean.staff.Staff)
	 */
	@Override
	public boolean putStaff(Staff staff) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolStaffDAO#updateStaff(com.yahoo.petermwenda83.bean.staff.Staff)
	 */
	@Override
	public boolean updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolStaffDAO#deleteStaff(com.yahoo.petermwenda83.bean.staff.Staff)
	 */
	@Override
	public boolean deleteStaff(Staff staff) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolStaffDAO#getStaffList(java.lang.String)
	 */
	@Override
	public List<Staff> getStaffList(String schoolAccountUuid) {
		 List<Staff> list = null;
		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Staff WHERE SchoolAccountUuid = ?;");   
			) {
			 pstmt.setString(1,schoolAccountUuid);

			 try(ResultSet rset = pstmt.executeQuery();){
					
				 list = beanProcessor.toBeanList(rset, Staff.class);
				}
	        

	  } catch(SQLException e){
	  	 logger.error("SQL Exception when getting all Staff");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

}
