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
package com.yahoo.petermwenda83.model.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ExamListsDAO  extends DBConnectDAO  implements SchoolExamListsDAO {
	private static ExamListsDAO examListsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static ExamListsDAO getInstance() {
		if(examListsDAO == null){
			examListsDAO = new ExamListsDAO();		
			}
		return examListsDAO;
	}
	
	public ExamListsDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public ExamListsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamListsDAO#getAllCatMarks()
	 */
	@Override
	public List<CatMarks> getAllCatMarks() {
		List<CatMarks> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatSubjectMark ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, CatMarks.class);

	      } catch(SQLException e){
	      	logger.error("SQL Exception when getting all ExamType");
	        logger.error(ExceptionUtils.getStackTrace(e));
	        System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
		
		
		return list;
	
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamListsDAO#getAllCatResults()
	 */
	@Override
	public List<CatResults> getAllCatResults() {
		List<CatResults> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatResult ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, CatResults.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all ExamType");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
			
		
		return list;
	
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamListsDAO#getAllMainMarks()
	 */
	@Override
	public List<MainMarks> getAllMainMarks() {
		List<MainMarks> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM MainSubjectMark ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, MainMarks.class);

	      } catch(SQLException e){
	      	logger.error("SQL Exception when getting all ExamType");
	        logger.error(ExceptionUtils.getStackTrace(e));
	        System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
		
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolExamListsDAO#getAllMainResults()
	 */
	@Override
	public List<MainResults> getAllMainResults() {
		List<MainResults> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM ExamResult ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, MainResults.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all ExamType");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	    
			
		
		return list;
	}

}
