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
package com.yahoo.petermwenda83.dao.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.money.pocket.Deposit;
import com.yahoo.petermwenda83.bean.money.pocket.Money;
import com.yahoo.petermwenda83.dao.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class PocketDepositDAO extends DBConnectDAO implements StudentPocketDepositDAO {

	private static PocketDepositDAO pocketDepositDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static PocketDepositDAO getInstance() {
		if(pocketDepositDAO == null){
			pocketDepositDAO = new PocketDepositDAO();		
			}
		return pocketDepositDAO;
	}
	
	public PocketDepositDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public PocketDepositDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.dao.money.StudentPocketDepositDAO#getPocketMoney(java.lang.String)
	 */
	@Override
	public Deposit getPocketMoney(String StudentUuid) {
		Deposit pm = new Deposit();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Deposit "
        	      		+ "WHERE StudentUuid = ?;");       
     		
     		){
     	
     	     pstmt.setString(1, StudentUuid);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	        	pm  = beanProcessor.toBean(rset,Deposit.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception while trying to get Student Deposit with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return pm; 
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.money.StudentPocketDepositDAO#hasBalance(com.yahoo.petermwenda83.bean.money.pocket.Deposit)
	 */
	@Override
	public boolean hasBalance(Money money,Double bal) {
		boolean hasBalance = false;
		double balance = 0;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Amount FROM Deposit "
	        			+ "WHERE StudentUuid = ?;");
      		){
			 
	            pstmt.setString(1, money.getStudentUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						balance = rset.getDouble("Amount");	
						hasBalance = (balance >= bal) ? true : false;		
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit student Deposit: "+money);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasBalance = false;
		 }
		 
		
		
		return hasBalance;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.money.StudentPocketDepositDAO#addBalance(com.yahoo.petermwenda83.bean.money.pocket.Deposit)
	 */
	@Override
	public boolean addBalance(Money money,Double bal) {
		boolean success = true;
		if(hasBalance(money,0.0)) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Deposit " +
							"SET Amount = (SELECT Amount FROM Deposit WHERE StudentUuid=? "
							+ ") + ? " +				
							"WHERE Uuid = (SELECT Uuid FROM Deposit WHERE StudentUuid=? );");				
					) {
				
					pstmt.setString(1, money.getStudentUuid());									
					pstmt.setDouble(2, bal);
					pstmt.setString(3, money.getStudentUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the balance of '" + money +
						"' of amount " + bal+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Deposit(Uuid,"
							+ "Studentuuid,balance,date) VALUES(?,?,?,?);");				
					) {
					pstmt.setString(1, money.getUuid());
					pstmt.setString(2, money.getStudentUuid());									
					pstmt.setDouble(3, bal);
					pstmt.setString(4, money.getUuid());
					pstmt.executeUpdate();			
						
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the balance of '" + money +
						"' of amount " + bal + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.money.StudentPocketDepositDAO#deductBalance(com.yahoo.petermwenda83.bean.money.pocket.Deposit)
	 */
	@Override
	public boolean deductBalance(Money money,Double bal) {
		boolean success = true;
		
		if(hasBalance(money,bal)) {
		
			try(
					Connection conn = dbutils.getConnection();			
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Deposit " +
							"SET Amount = (SELECT Amount FROM Deposit WHERE StudentUuid=? "
							+ ") - ? "
							+ "WHERE Uuid = (SELECT Uuid FROM Deposit WHERE StudentUuid=?  );");	
					) {
					pstmt.setString(1, money.getStudentUuid());									
					pstmt.setDouble(2, bal);
					pstmt.setString(3, money.getStudentUuid());	
					pstmt.executeUpdate();
									
			} catch(SQLException e) {
				logger.error("SQLException while deducting the balance of '" + bal +
						"' of amount " + bal + " for '" + money + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
						
		} else { 
			success = false;
		}
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.dao.money.StudentPocketDepositDAO#getAllBalanaces()
	 */
	@Override
	public List<Deposit> getAllBalanaces() {
		List<Deposit>  list = null;
		
		 try(   
  		Connection conn = dbutils.getConnection();
  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Deposit ;");   
  		ResultSet rset = pstmt.executeQuery();
		) {
  	
      list = beanProcessor.toBeanList(rset, Deposit.class);

  } catch(SQLException e){
  	   logger.error("SQL Exception when getting all Deposit");
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e));
  }
        return list;
	}

}
