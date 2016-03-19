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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.money.ClosingBalance;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class ClosingBalanceDAO extends GenericDAO implements SchoolClosingBalanceDAO {
	
	private static ClosingBalanceDAO closingBalanceDAO;
	
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static ClosingBalanceDAO getInstance() {
		if(closingBalanceDAO == null){
			closingBalanceDAO = new ClosingBalanceDAO();		
			}
		return closingBalanceDAO;
	}
	
	
	public ClosingBalanceDAO() {
		super();
		
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public ClosingBalanceDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);

	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolClosingBalanceDAO#getClosingBalanceByStudentId(java.lang.String, java.lang.String)
	 */
	@Override
	public ClosingBalance getClosingBalanceByStudentId(String schoolAccountUuid, String studentUuid,String Term,String Year) {
		ClosingBalance closingBalance = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ClosingBalance WHERE schoolAccountUuid = ?"
           	      		+ " AND studentUuid =? AND Term =? AND Year =? ;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
        	 pstmt.setString(2, studentUuid);
        	 pstmt.setString(3, Term);
        	 pstmt.setString(4, Year);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 closingBalance  = beanProcessor.toBean(rset,ClosingBalance.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting ClosingBalance for student: " + studentUuid +"and schoolAccountUuid"+schoolAccountUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return closingBalance; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolClosingBalanceDAO#getClosingBalanceList(java.lang.String)
	 */
	@Override
	public List<ClosingBalance> getClosingBalanceList(String schoolAccountUuid,String Term,String Year) {
		 List<ClosingBalance> closingBalanceList = null;
			try(
					Connection conn = dbutils.getConnection();
					PreparedStatement psmt= conn.prepareStatement("SELECT * FROM ClosingBalance WHERE "
							+ "SchoolAccountUuid = ? AND Term =? AND Year =?;");
					) {
				psmt.setString(1, schoolAccountUuid);
				psmt.setString(2, Term);
				psmt.setString(3, Year);
				try(ResultSet rset = psmt.executeQuery();){
				
					closingBalanceList = beanProcessor.toBeanList(rset, ClosingBalance.class);
				}
			} catch (SQLException e) {
				logger.error("SQLException when trying to get ClosingBalance List for school " + schoolAccountUuid);
	            logger.error(ExceptionUtils.getStackTrace(e));
	            System.out.println(ExceptionUtils.getStackTrace(e)); 
		    }
			
			return closingBalanceList;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolClosingBalanceDAO#putClosingBalance(com.yahoo.petermwenda83.bean.money.ClosingBalance)
	 */
	@Override
	public boolean putClosingBalance(ClosingBalance closingBalance) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ClosingBalance" 
			        		+"(Uuid,SchoolAccountUuid,StudentUuid,Term,Year,ClosingAmount) VALUES (?,?,?,?,?,?);");
		             ){
			   
	            pstmt.setString(1, closingBalance.getUuid());
	            pstmt.setString(2, closingBalance.getSchoolAccountUuid());
	            pstmt.setString(3, closingBalance.getStudentUuid());
	            pstmt.setString(4, closingBalance.getTerm());
	            pstmt.setString(5, closingBalance.getYear());
	            pstmt.setDouble(6, closingBalance.getClosingAmount());
	            pstmt.executeUpdate();
	            
	        
			 
		 }catch(SQLException e){
		 logger.error("SQL Exception trying to put ClosingBalance "+closingBalance);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		
		return success;
	}

}
