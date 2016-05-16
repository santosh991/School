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

import com.yahoo.petermwenda83.bean.money.StudentAmount;
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
	public TermFee getTermFee(String schoolAccountUuid,String Term) {
		TermFee termFee = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TermFee WHERE schoolAccountUuid = ? AND Term =?;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
        	 pstmt.setString(2, Term);
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
			        		+"(Uuid,SchoolAccountUuid,Term,TermAmount) VALUES (?,?,?,?);");
		             ){
			   
	            pstmt.setString(1, termFee.getUuid());
	            pstmt.setString(2, termFee.getSchoolAccountUuid());
	            pstmt.setString(3, termFee.getTerm());
	            pstmt.setDouble(4, termFee.getTermAmount());
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
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE termFee SET TermAmount =? WHERE  Term =? AND SchoolAccountUuid = ?;");
	               ) {           			 	            
			    
			    pstmt.setDouble(1, termFee.getTermAmount());
	            pstmt.setString(2, termFee.getTerm());
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

	/**
	 * @see com.yahoo.petermwenda83.persistence.money.SchoolTermFeeDAO#termFeeList(java.lang.String)
	 */
	@Override
	public List<TermFee> getTermFeeList(String schoolAccountUuid) {
		List<TermFee> List = null;
		try(
				Connection conn = dbutils.getConnection();
				PreparedStatement psmt= conn.prepareStatement("SELECT * FROM TermFee WHERE "
						+ "schoolAccountUuid = ?;");
				) {
			psmt.setString(1, schoolAccountUuid);
			try(ResultSet rset = psmt.executeQuery();){
			
				List = beanProcessor.toBeanList(rset, TermFee.class);
			}
		} catch (SQLException e) {
			logger.error("SQLException when trying to get a Fee List for school " +schoolAccountUuid);
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e)); 
	    }
		
		return List;
	}

}
