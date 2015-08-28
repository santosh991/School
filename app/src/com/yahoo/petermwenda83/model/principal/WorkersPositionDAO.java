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

import com.yahoo.petermwenda83.contoller.staff.nonteaching.WorkerPosition;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class WorkersPositionDAO  extends DBConnectDAO implements SchoolWorkersPositionDAO {

	private static WorkersPositionDAO workersPositionDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static WorkersPositionDAO getInstance() {
		if(workersPositionDAO == null){
			workersPositionDAO = new WorkersPositionDAO();		
			}
		return workersPositionDAO;
	}
	
	public WorkersPositionDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public WorkersPositionDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolWorkersPositionDAO#getNtstaffPos(java.lang.String)
	 */
	@Override
	public WorkerPosition getWorkerPosition(String Uuid) {
		WorkerPosition wp = new WorkerPosition();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM WorkerPosition WHERE Uuid = ?;");       
     		
     		){
     	
     	     pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	    	 wp  = beanProcessor.toBean(rset,WorkerPosition.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting WorkerPosition with Uuid: " + Uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return wp; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolWorkersPositionDAO#put(com.yahoo.petermwenda83.contoller.staff.nonteaching.WorkerPosition)
	 */
	@Override
	public boolean put(WorkerPosition workerPos) {
		boolean success = true;
		WorkerPosition p = new WorkerPosition();
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO WorkerPosition" 
			        		+"(Uuid, WorkerUuid, TeacherPosition, Salary) VALUES (?,?,?,?);");
      		){
	            pstmt.setString(1, p.getUuid());
	            pstmt.setString(2, workerPos.getEmployeeUuid());
	            pstmt.setString(3, workerPos.getPosition());
	            pstmt.setString(4, workerPos.getSalary());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Employees WorkerPosition: "+workerPos);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolWorkersPositionDAO#edit(com.yahoo.petermwenda83.contoller.staff.nonteaching.WorkerPosition, java.lang.String)
	 */
	@Override
	public boolean edit(WorkerPosition workerPos, String Uuid) {
		boolean success = true;
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE WorkerPosition SET "
	        			+ "TeacherPosition=?, Salary=? WHERE WorkerUuid = ?;");
      		){
			  
	            pstmt.setString(1, workerPos.getPosition());
	            pstmt.setString(2, workerPos.getSalary());
	            pstmt.setString(3, workerPos.getWorkerUuid());
	            pstmt.executeUpdate();
			   
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit Employees Worker Position: "+workerPos);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolWorkersPositionDAO#delete(com.yahoo.petermwenda83.contoller.staff.nonteaching.WorkerPosition, java.lang.String)
	 */
	@Override
	public boolean delete(WorkerPosition workerPos, String Uuid) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	    		  PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM WorkerPosition"
	         	      		+ " WHERE WorkerUuid = ?;");  
	      		
	      		){
	    		  pstmt2.setString(1, workerPos.getWorkerUuid());
			      pstmt2.executeUpdate();
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting WorkerPosition : " +workerPos);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolWorkersPositionDAO#getAllWorkerPosition()
	 */
	@Override
	public List<WorkerPosition> getAllWorkerPosition() {
		List<WorkerPosition>  list = null;
		 try(   
  		Connection conn = dbutils.getConnection();
  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM WorkerPosition ;");   
  		ResultSet rset = pstmt.executeQuery();
		) {
  	
      list = beanProcessor.toBeanList(rset, WorkerPosition.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all WorkerPosition");
        logger.error(ExceptionUtils.getStackTrace(e));
  }

		
		return list;
	}

}
