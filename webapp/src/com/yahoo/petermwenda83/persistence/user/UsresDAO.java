/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.systemuser.User;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;
import com.yahoo.petermwenda83.server.servlet.util.SecurityUtil;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class UsresDAO extends DBConnectDAO implements SystemUsersDAO {


    private static UsresDAO usresDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * 
	 * @return the usresDAO
	 */
	public static UsresDAO getInstance(){
		if(usresDAO == null){
			usresDAO = new UsresDAO();		
		}
		return usresDAO;
	}
	
	/**
	 * 
	 */
	public UsresDAO() {
		super();
	}
	 
	public UsresDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#getUser(java.lang.String)
	 */
	
	
	public User getUser(String Uuid) {
		User user = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SystemUser WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 user  = beanProcessor.toBean(rset,User.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with uuid: " + Uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return user; 
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#getUserBySchUuid(java.lang.String)
	 */
	
	public User getUserByUsername(String UserUsername,String SchoolAccountUuid) {
		User user = new User();
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SystemUser WHERE Username = ? AND SchoolAccountUuid =?;");       
        		
        		){
        	
        	 pstmt.setString(1, UserUsername);
        	 pstmt.setString(2, SchoolAccountUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	  user  = beanProcessor.toBean(rset,User.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with UserUsername: " + UserUsername);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return user; 
	}
	

	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#getUserName(com.yahoo.petermwenda83.bean.systemuser.User)
	 */
	
	public User getUserName(User use) { 
		User user = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SystemUser WHERE"
           	      		+ " username =? AND Password =? AND userType =? AND SchoolAccountUuid =?;");       
        		
        		){
        	
        	 pstmt.setString(1, use.getUsername());
        	 pstmt.setString(2, use.getPassword());
        	 pstmt.setString(3, use.getUserType());
        	 pstmt.setString(4, use.getSchoolAccountUuid());
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 user  = beanProcessor.toBean(rset,User.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting user with: " + use);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return user; 
	}
	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#putUser(com.yahoo.petermwenda83.contoller.user.User)
	 */
	
	public boolean putUser(User user) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SystemUser" 
			        		+"(Uuid,SchoolAccountUuid, userType, username, password) VALUES (?,?,?,?,?);");
        		){
			   
	            pstmt.setString(1, user.getUuid());
	            pstmt.setString(2, user.getSchoolAccountUuid());
	            pstmt.setString(3, user.getUserType());
	            pstmt.setString(4, user.getUsername());
	            pstmt.setString(5,  SecurityUtil.getMD5Hash(user.getPassword()));
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+user);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#editUser(com.yahoo.petermwenda83.contoller.user.User, java.lang.String)
	 */
	
	public boolean editUser(User user, String Uuid) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement("UPDATE SystemUser SET userType=?, "
        			+ "username=?, password=?,SchoolAccountUuid =? WHERE Uuid = ?;");
        	) {
                
	            pstmt.setString(1, user.getUserType());
	            pstmt.setString(2, user.getUsername());
	            pstmt.setString(3, user.getPassword());
	            pstmt.setString(4, user.getSchoolAccountUuid());
	            pstmt.setString(5, Uuid);
	            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL Exception when updating SubjectUi with uuid " + user);
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e));
            success = false;
        } 
        		
		return success;
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#deleteUser(com.yahoo.petermwenda83.contoller.user.User)
	 */
	
	public boolean deleteUser(User user) {
		 boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM SystemUser"
	         	      		+ " WHERE Uuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, user.getUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting user : " +user);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.persistence.user.SystemUsersDAO#getAllUsers()
	 */
	public List<User> getAllUsers(String SchoolAccountUuid) {
		List<User>  list = null;
		
		 try(   
      		Connection conn = dbutils.getConnection();
      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM SystemUser WHERE SchoolAccountUuid =? ;");   
      		
  		) {

			  pstmt.setString(1,SchoolAccountUuid);
			  try(ResultSet rset = pstmt.executeQuery();) {
				  list = beanProcessor.toBeanList(rset, User.class);
	      	   } 
            

      } catch(SQLException e){
      	logger.error("SQL Exception when getting all StudentSubject");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
      }
    
		
		return list;
	}

	

	

}
