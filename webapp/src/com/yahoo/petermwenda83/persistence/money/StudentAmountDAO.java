/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.money.StudentAmount;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */  
public class StudentAmountDAO extends GenericDAO implements SchoolStudentAmountDAO {

	private static StudentAmountDAO studentAmountDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentAmountDAO getInstance() {
		if(studentAmountDAO == null){
			studentAmountDAO = new StudentAmountDAO();		
			}
		return studentAmountDAO;
	}
	
	public StudentAmountDAO() {
		super();
		
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public StudentAmountDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolStudentAmountDAO#getStudentAmount(java.lang.String, java.lang.String)
	 */
	@Override
	public StudentAmount getStudentAmount(String schoolAccountUuid, String studentUuid) {
		StudentAmount studentAmount = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentAmount WHERE schoolAccountUuid = ? AND studentUuid =?;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
        	 pstmt.setString(2, studentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentAmount  = beanProcessor.toBean(rset,StudentAmount.class);
	   }
        		
        }catch(SQLException e){
        	 logger.error("SQL Exception while getting PocketMoney with StudentUuid: " + studentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e)); 
        }
       
		return studentAmount;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolStudentAmountDAO#hasAmount(com.yahoo.petermwenda83.bean.money.StudentAmount, double)
	 */
	@Override
	public boolean hasAmount(String schoolAccountUuid,String studentUuid, double amount) {
		boolean hasBalance = false;
		double balance = 0;
		try(    Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Amount FROM StudentAmount "
	        			+ "WHERE StudentUuid = ? AND SchoolAccountUuid =?;");
      		){
			 
	            pstmt.setString(1, studentUuid);
	            pstmt.setString(2, schoolAccountUuid);
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						balance = rset.getDouble("amount");	
						hasBalance = (balance >= amount) ? true : false;	
						
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to get student PocketMoney of amount "+amount);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasBalance = false;
		 }
		
		return hasBalance;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolStudentAmountDAO#addAmount(com.yahoo.petermwenda83.bean.money.StudentAmount, double)
	 */
	@Override
	public boolean addAmount(String schoolAccountUuid,String studentUuid, double amount,String Status) {
		StudentAmount studentAmount = new StudentAmount();
		boolean success = true;
		if(getStudentAmount(schoolAccountUuid,studentUuid) !=null) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentAmount " +
							"SET amount = (SELECT amount FROM StudentAmount WHERE StudentUuid=? "
							+ ") + ? " +				
							"WHERE Uuid = (SELECT Uuid FROM StudentAmount WHERE StudentUuid=? );");	
					
					) {
				
					pstmt.setString(1, studentUuid);									
					pstmt.setDouble(2, amount);
					pstmt.setString(3, studentUuid);
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the balance for '" + studentUuid +
						"' of amount " + amount+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO StudentAmount(Uuid,"
							+ "SchoolAccountUuid,Studentuuid,amount,status) VALUES(?,?,?,?,?);");	
					
					) {
					pstmt.setString(1, studentAmount.getUuid());
					pstmt.setString(2,schoolAccountUuid);	
					pstmt.setString(3, studentUuid);	
					pstmt.setDouble(4, amount);
					pstmt.setString(5, Status);
					pstmt.executeUpdate();	
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the balance for '" + studentAmount +
						"' of amount " + amount + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolStudentAmountDAO#deductAmount(com.yahoo.petermwenda83.bean.money.StudentAmount, double)
	 */
	@Override
	public boolean deductAmount(String schoolAccountUuid,String studentUuid, double amount) {
		StudentAmount studentAmount = new StudentAmount();
		boolean success = true;
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentAmount " +
							"SET Amount = (SELECT Amount FROM StudentAmount WHERE StudentUuid=? "
							+ ") - ? " +				
							"WHERE Uuid = (SELECT Uuid FROM StudentAmount WHERE StudentUuid=? );");
	
					) {
				
					pstmt.setString(1, studentUuid);									
					pstmt.setDouble(2, amount);
					pstmt.setString(3, studentUuid);
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the balance for '" + studentAmount +
			    "' of amount " + amount+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolStudentAmountDAO#changeStatus(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changeStatus(String Status,String schoolAccountUuid, String studentUuid) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentAmount SET Status = ? WHERE SchoolAccountUuid = ? AND Studentuuid = ?;");
	               ) {           			 	            
			  
	            pstmt.setString(1,Status);
	            pstmt.setString(2, schoolAccountUuid);
	            pstmt.setString(3, studentUuid);
	            pstmt.executeUpdate();
	
	  } catch (SQLException e) {
	    logger.error("SQL Exception when updating StudentAmount for student" + studentUuid);
	    logger.error(ExceptionUtils.getStackTrace(e));
	    System.out.println(ExceptionUtils.getStackTrace(e));
	    success = false;
	  } 
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolStudentAmountDAO#getAmountList(java.lang.String)
	 */
	@Override
	public List<StudentAmount> getAmountList(String schoolAccountUuid) {
		List<StudentAmount> feeList = null;
		try(
				Connection conn = dbutils.getConnection();
				PreparedStatement psmt= conn.prepareStatement("SELECT * FROM StudentAmount WHERE "
						+ "schoolAccountUuid = ?;");
				) {
			psmt.setString(1, schoolAccountUuid);
			try(ResultSet rset = psmt.executeQuery();){
			
				feeList = beanProcessor.toBeanList(rset, StudentAmount.class);
			}
		} catch (SQLException e) {
			logger.error("SQLException when trying to get a Student List for school " +schoolAccountUuid);
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e)); 
	    }
		
		return feeList;
	}

}
