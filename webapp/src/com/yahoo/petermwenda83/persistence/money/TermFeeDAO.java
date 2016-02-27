/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/** 
 * @author peter
 *
 */
public class TermFeeDAO extends GenericDAO implements SchoolTermFeeDAO {

	private static TermFeeDAO termFeeDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static TermFeeDAO getInstance() {
		if(termFeeDAO == null){
			termFeeDAO = new TermFeeDAO();		
			}
		return termFeeDAO;
	}
	
	public TermFeeDAO() {
		super();
		
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public TermFeeDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
	}
	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolTermFeeDAO#getTermFee(java.lang.String)
	 */
	@Override
	public TermFee getTermFee(String schoolAccountUuid) {
		TermFee termFee = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TermFee WHERE schoolAccountUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 termFee  = beanProcessor.toBean(rset,TermFee.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting TermFee for schoolAccountUuid " + schoolAccountUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return termFee; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolTermFeeDAO#putTermFee(com.yahoo.petermwenda83.bean.money.TermFee)
	 */
	@Override
	public boolean putTermFee(TermFee termFee) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TermFee" 
			        		+"(Uuid,SchoolAccountUuid,TermAmount,NextTermAmount) VALUES (?,?,?,?);");
		             ){
			   
	            pstmt.setString(1, termFee.getUuid());
	            pstmt.setString(2, termFee.getSchoolAccountUuid());
	            pstmt.setDouble(3, termFee.getTermAmount());
	            pstmt.setDouble(4, termFee.getNextTermAmount());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
		   logger.error("SQL Exception trying to put TermFee "+termFee);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolTermFeeDAO#updateTermFee(com.yahoo.petermwenda83.bean.money.TermFee)
	 */
	@Override
	public boolean updateTermFee(TermFee termFee) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE termFee SET TermAmount = ?,NextTermAmount =? WHERE SchoolAccountUuid = ?;");
	               ) {           			 	            
			  
	            pstmt.setDouble(1, termFee.getTermAmount());
	            pstmt.setDouble(2, termFee.getNextTermAmount());
	            pstmt.setString(3, termFee.getSchoolAccountUuid());
	            pstmt.executeUpdate();

    } catch (SQLException e) {
      logger.error("SQL Exception when updating TermFee " + termFee);
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e));
      success = false;
    } 
		
		return success;
	}

}
