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
package com.yahoo.petermwenda83.persistence.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.staff.nonteaching.Workers;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class WorkersRegistrationDAO extends DBConnectDAO implements SchoolWorkersRegistrationDAO {

	private static WorkersRegistrationDAO nstaffDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static WorkersRegistrationDAO getInstance() {
		if(nstaffDAO == null){
			nstaffDAO = new WorkersRegistrationDAO();		
			}
		return nstaffDAO;
	}
	
	public WorkersRegistrationDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public WorkersRegistrationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolWorkersRegistrationDAO#getWorker(java.lang.String)
	 */
	@Override
	public Workers getWorker(String Uuid) {
		Workers w = new Workers();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Worker WHERE Uuid = ?;");       
     		
     		){
     	
     	     pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	    	 w  = beanProcessor.toBean(rset,Workers.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting Workers with Uuid: " + Uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return w; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolWorkersRegistrationDAO#putWorker(com.yahoo.petermwenda83.bean.staff.nonteaching.Workers)
	 */
	@Override
	public boolean putWorker(Workers worker) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO Worker" 
		        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nssfno,Phone,"
			        		+ "DOB,NationalID,County,Location,Sublocation) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        		){
				    pstmt2.setString(1, worker.getUuid());
		            pstmt2.setString(2, worker.getFirstName());
		            pstmt2.setString(3, worker.getLastName());
		            pstmt2.setString(4, worker.getSurname());
		            pstmt2.setString(5, worker.getNhifno());
		            pstmt2.setString(6, worker.getNssfno());
		            pstmt2.setString(7, worker.getPhone());
		            pstmt2.setString(8, worker.getDOB());
		            pstmt2.setString(9, worker.getNationalID());
		            pstmt2.setString(10, worker.getCounty());
		            pstmt2.setString(11, worker.getLocation());
		            pstmt2.setString(12, worker.getSublocation());
				   
		            pstmt2.executeUpdate(); 
			  
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put worker: "+worker);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolWorkersRegistrationDAO#editWorker(com.yahoo.petermwenda83.bean.staff.nonteaching.Workers, java.lang.String)
	 */
	@Override
	public boolean editWorker(Workers worker, String Uuid) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Worker SET "
	        			+ "FirstName=?, LastName=?, Surname =?, Nhifno =?, Nssfno =?, "
	        			+ "Phone =?, DOB =?, NationalID =?,"
	        			+ " County =?,Location =?,Sublocation =? WHERE Uuid = ?;");
        		){
			        pstmt2.setString(1, worker.getFirstName());
		            pstmt2.setString(2, worker.getLastName());
		            pstmt2.setString(3, worker.getSurname());
		            pstmt2.setString(4, worker.getNhifno());
		            pstmt2.setString(5, worker.getNssfno());
		            pstmt2.setString(6, worker.getPhone());
		            pstmt2.setString(7, worker.getDOB());
		            pstmt2.setString(8, worker.getNationalID());
		            pstmt2.setString(9, worker.getCounty());
		            pstmt2.setString(10, worker.getLocation());
		            pstmt2.setString(11, worker.getSublocation());
		            pstmt2.setString(12, worker.getUuid());
				   				   
		            pstmt2.executeUpdate(); 
			  
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put worker: "+worker);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolWorkersRegistrationDAO#deleteWorker(com.yahoo.petermwenda83.bean.staff.nonteaching.Workers, java.lang.String)
	 */
	@Override
	public boolean deleteWorker(Workers worker, String Uuid) {
		boolean success = true;
	      try(
	      		  Connection conn = dbutils.getConnection();
	    		  PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Worker"
	         	      		+ " WHERE Uuid = ?;");     
	      		
	      		){
	      		 pstmt.setString(1, worker.getUuid());
		         pstmt.executeUpdate(); 
	      	  
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting worker : " +worker);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.principal.SchoolWorkersRegistrationDAO#getAllWorker()
	 */
	@Override
	public List<Workers> getAllWorker() {
		List<Workers>  list = null;
		
		 try(   
   		Connection conn = dbutils.getConnection();
   		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Worker ;");   
   		ResultSet rset = pstmt.executeQuery();
		) {
   	
       list = beanProcessor.toBeanList(rset, Workers.class);

   } catch(SQLException e){
   	logger.error("SQL Exception when getting all Workers");
     logger.error(ExceptionUtils.getStackTrace(e));
     System.out.println(ExceptionUtils.getStackTrace(e));
   }
 
		
		return list;
	}

	

}
