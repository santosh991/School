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
 * ##### SchoolAccount Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.money.Deposit;
import com.yahoo.petermwenda83.bean.money.Withdraw;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class WithdrawDAO  extends GenericDAO implements SchoolWithdrawDAO {
	private static DepositDAO depositDAO;
	
	private static WithdrawDAO withdrawDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static WithdrawDAO getInstance() {
		if(withdrawDAO == null){
			withdrawDAO = new WithdrawDAO();		
			}
		return withdrawDAO;
	}
	
	public WithdrawDAO() {
		super();
		depositDAO = DepositDAO.getInstance();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public WithdrawDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		depositDAO = new DepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	@Override
	public boolean hasBalance(Withdraw withdraw, Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deductBalance(Withdraw withdraw, Double amount) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.money.StudentPocketWithdrawDAO#getAllWithdraw()
	 */
	@Override
	public List<Withdraw> getWithdrawList() {
		List<Withdraw>  list = null;
		
		 try(   
 		Connection conn = dbutils.getConnection();
 		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Withdraw ;");   
 		ResultSet rset = pstmt.executeQuery();
		) {
 	
     list = beanProcessor.toBeanList(rset, Withdraw.class);

 } catch(SQLException e){
 	   logger.error("SQL Exception when getting all Withdraw");
     logger.error(ExceptionUtils.getStackTrace(e));
     System.out.println(ExceptionUtils.getStackTrace(e));
 }
       return list;
	}


}
