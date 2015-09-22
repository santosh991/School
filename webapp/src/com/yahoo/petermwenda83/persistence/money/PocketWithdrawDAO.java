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

import com.yahoo.petermwenda83.bean.money.pocket.Withdraw;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class PocketWithdrawDAO  extends DBConnectDAO implements StudentPocketWithdrawDAO {
	private static PocketDepositDAO pocketDepositDAO;
	
	private static PocketWithdrawDAO pocketWithdrawDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static PocketWithdrawDAO getInstance() {
		if(pocketWithdrawDAO == null){
			pocketWithdrawDAO = new PocketWithdrawDAO();		
			}
		return pocketWithdrawDAO;
	}
	
	public PocketWithdrawDAO() {
		super();
		pocketDepositDAO = PocketDepositDAO.getInstance();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public PocketWithdrawDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		pocketDepositDAO = new PocketDepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.money.StudentPocketWithdrawDAO#get(java.lang.String)
	 */
	@Override
	public Withdraw get(String StudentUuid) {
		Withdraw wthd = null;
		try(
				 Connection conn = dbutils.getConnection();
			   PreparedStatement ps = conn.prepareStatement("SELECT * FROM Withdraw WHERE StudentUuid =?");
		     
				){
			
	
			             ps.setString(1, StudentUuid);
			        
			             try(
			            		 ResultSet  rs1 = ps.executeQuery();			
			            	
			            		 ){
			            	 if(rs1.next()){
			            		 StudentUuid = rs1.getString("StudentUuid");
					        if(StudentUuid != null)	 {
					        	wthd = beanProcessor.toBean(rs1, Withdraw.class);	
					        }				   
					           
					        	   }
				
			            	 
			             } 
			             
		}
			            
		catch(SQLException e) {
			logger.error("SQLException while trying to get student Withdraws by " + StudentUuid);
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		
		return wthd;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.money.StudentPocketWithdrawDAO#put(com.yahoo.petermwenda83.contoller.money.pocket.Deposit)
	 */
	@Override
	public boolean put(Withdraw withdraw) {
		boolean success = true;
		double amount = withdraw.getAmount();
		
		try(
				 Connection conn = dbutils.getConnection();		
			PreparedStatement pst = conn.prepareStatement("INSERT INTO Withdraw(Uuid, StudentUuid,"
				+ "Amount, withdrawdate) VALUES(?,?,?,?);");									    
			){
			
				pst.setString(1, withdraw.getUuid());
				pst.setString(2,withdraw.getStudentUuid());
				pst.setDouble(3, withdraw.getAmount());
				pst.setTimestamp(4, new Timestamp(withdraw.getWithdrawdate().getTime()));	
				pst.executeUpdate();
				
				Withdraw wd = new Withdraw();
				wd.setStudentUuid(withdraw.getStudentUuid()); 
				pocketDepositDAO.addBalance(wd, amount);
					
				/* System.out.println(withdraw.getStudentUuid());
				 System.out.println(amount);*/
		
		
			
		} catch(SQLException e) {
			logger.error("SQLException while trying to put " + withdraw);
			logger.error(ExceptionUtils.getStackTrace(e));
			 System.out.println(ExceptionUtils.getStackTrace(e));
			success= false;
		}
	
		return success;		
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.money.StudentPocketWithdrawDAO#getAllWithdraw()
	 */
	@Override
	public List<Withdraw> getWithdraw(Withdraw withdraw) {
      List<Withdraw> list = new LinkedList<>();
		
		try(
				 Connection conn = dbutils.getConnection();
				PreparedStatement stm = conn.prepareStatement("SELECT * FROM Withdraw "
						+ "WHERE Studentuuid = ?");
				){
			
			 stm.setString(1, withdraw.getStudentUuid());
			 ResultSet rs = stm.executeQuery();
			 list.addAll(beanProcessor.toBeanList(rs, Withdraw.class));
	 	      
        } catch (SQLException e) {
            logger.error("SQLException when getting pMoney of " + withdraw);
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e));
        }		
		
		return list;
	}

}
