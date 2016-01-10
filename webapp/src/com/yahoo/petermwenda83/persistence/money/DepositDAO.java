
package com.yahoo.petermwenda83.persistence.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.money.Deposit;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class DepositDAO extends GenericDAO implements SchoolDepositDAO {

	private static DepositDAO depositDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static DepositDAO getInstance() {
		if(depositDAO == null){
			depositDAO = new DepositDAO();		
			}
		return depositDAO;
	}
	
	public DepositDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public DepositDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolDepositDAO#hasBalance(com.yahoo.petermwenda83.bean.money.Deposit)
	 */
	@Override
	public boolean hasBalance(Deposit deposit,Double bal) {
		boolean hasBalance = false;
		double balance = 0;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Amount FROM Deposit "
	        			+ "WHERE StudentUuid = ?;");
      		){
			 
	            pstmt.setString(1, deposit.getStudentUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						balance = rset.getDouble("Amount");	
						hasBalance = (balance >= bal) ? true : false;		
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit student Deposit: "+deposit);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasBalance = false;
		 }
		
		return hasBalance;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolDepositDAO#addBalance(com.yahoo.petermwenda83.bean.money.Deposit)
	 */
	@Override
	public boolean addBalance(Deposit deposit,Double bal) {
		boolean success = true;
		if(hasBalance(deposit,0.0)) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Deposit " +
							"SET Amount = (SELECT Amount FROM Deposit WHERE StudentUuid=? "
							+ ") + ? " +				
							"WHERE Uuid = (SELECT Uuid FROM Deposit WHERE StudentUuid=? );");				
					) {
				
					pstmt.setString(1, deposit.getStudentUuid());									
					pstmt.setDouble(2, bal);
					pstmt.setString(3, deposit.getStudentUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the balance of '" + deposit +
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
					pstmt.setString(1, deposit.getUuid());
					pstmt.setString(2, deposit.getStudentUuid());									
					pstmt.setDouble(3, bal);
					pstmt.setString(4, deposit.getUuid());
					pstmt.executeUpdate();			
						
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the balance of '" + deposit +
						"' of amount " + bal + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

	

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolDepositDAO#getAllBalanaces()
	 */
	@Override
	public List<Deposit> getDepositeList() {
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
