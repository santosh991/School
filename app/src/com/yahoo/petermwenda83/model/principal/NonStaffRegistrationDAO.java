/**##########################################################
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
package com.yahoo.petermwenda83.model.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.staff.Employees;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;
import com.yahoo.petermwenda83.model.DBConnectDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class NonStaffRegistrationDAO extends DBConnectDAO implements SchoolNonStaffRegistrationDAO {

	private static NonStaffRegistrationDAO nstaffDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static NonStaffRegistrationDAO getInstance() {
		if(nstaffDAO == null){
			nstaffDAO = new NonStaffRegistrationDAO();		
			}
		return nstaffDAO;
	}
	
	public NonStaffRegistrationDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public NonStaffRegistrationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}



	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolNonStaffRegistrationDAO#getNtStaff(java.lang.String)
	 */
	@Override
	public Employees getNtStaff(String uuid) {
		NTstaff ntstaff = new NTstaff();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Ntstaff WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 ntstaff  = beanProcessor.toBean(rset,NTstaff.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return ntstaff; 
		 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolNonStaffRegistrationDAO#getNtstaffPos(java.lang.String)
	 */
	@Override
	public Employees getNtstaffPos(String uuid) {
		NTPosition ntsstaffpos = new NTPosition();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Ntposition WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 ntsstaffpos  = beanProcessor.toBean(rset,NTPosition.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return ntsstaffpos; 
	}
 
	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolNonStaffRegistrationDAO#getAllNTstaff()
	 */
	@Override
	public List<NTstaff> getAllNTstaff() {
		List<NTstaff>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM NTstaff ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, NTstaff.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all NTstaff");
        logger.error(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	}

	@Override
	public List<NTPosition> getAllNTPosition() {
		List<NTPosition>  list = null;
		
		 try(   
   		Connection conn = dbutils.getConnection();
   		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Ntposition ;");   
   		ResultSet rset = pstmt.executeQuery();
		) {
   	
       list = beanProcessor.toBeanList(rset, NTPosition.class);

   } catch(SQLException e){
   	logger.error("SQL Exception when getting all NTPosition");
       logger.error(ExceptionUtils.getStackTrace(e));
   }
 
		
		return list;
	}

	

}
