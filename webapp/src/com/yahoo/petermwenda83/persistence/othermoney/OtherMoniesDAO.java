/**
 * 
 */
package com.yahoo.petermwenda83.persistence.othermoney;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.othermoney.OtherMonies;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/** 
 * @author peter
 *
 */
public class OtherMoniesDAO extends GenericDAO implements SchoolOtherMoniesDAO {

	private static OtherMoniesDAO otherMoniesDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static OtherMoniesDAO getInstance() {
		if(otherMoniesDAO == null){
			otherMoniesDAO = new OtherMoniesDAO();		
			}
		return otherMoniesDAO;
	}
	
	public OtherMoniesDAO() {
		super();
		
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public OtherMoniesDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolOtherMoniesDAO#getOtherMonies(java.lang.String)
	 */
	@Override
	public OtherMonies getOtherMonies(String studentUuid) {
		OtherMonies otherMonies = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM OtherMonies WHERE studentUuid = ?;");       
        		
        		){
        	 pstmt.setString(1, studentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 otherMonies  = beanProcessor.toBean(rset,OtherMonies.class);
	   }
        		
        }catch(SQLException e){
        	 logger.error("SQL Exception while getting OtherMonies with StudentUuid: " + studentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e)); 
        }
       
		return otherMonies;
	}
	
	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolOtherMoniesDAO#hasAmount(java.lang.String, double)
	 */
	@Override
	public boolean hasAmount(String studentUuid, double amount) {
		boolean hasBalance = false;
		double balance = 0;
		try(    Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT Amount FROM OtherMonies "
	        			+ "WHERE StudentUuid = ?");
      		){
			 
	            pstmt.setString(1, studentUuid);
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						balance = rset.getDouble("amount");	
						hasBalance = (balance >= amount) ? true : false;	
						
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to get student OtherMonies of amount "+amount);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             hasBalance = false;
		 }
		
		return hasBalance;
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolOtherMoniesDAO#putOtherMonies(com.yahoo.petermwenda83.bean.othermoney.OtherMonies)
	 */
	@Override
	public boolean AddOtherMonies(OtherMonies otherMonies,double amount) {
		boolean success = true;
		if(getOtherMonies(otherMonies.getStudentUuid()) !=null) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE OtherMonies " +
							"SET amount = (SELECT amount FROM OtherMonies WHERE StudentUuid=? "
							+ ") + ? " +				
							"WHERE Uuid = (SELECT Uuid FROM OtherMonies WHERE StudentUuid=? );");	
					
					) {
				
					pstmt.setString(1, otherMonies.getStudentUuid());									
					pstmt.setDouble(2, amount);
					pstmt.setString(3, otherMonies.getStudentUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the balance for '" + otherMonies +
						"' of amount " + amount+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO OtherMonies(Uuid,"
							+ "StudentUuid,amount,Status) VALUES(?,?,?,?);");	
					
					) {
					pstmt.setString(1, otherMonies.getUuid());
					pstmt.setString(2, otherMonies.getStudentUuid());	
					pstmt.setDouble(3, amount);
					pstmt.setString(4, otherMonies.getStatus());
					pstmt.executeUpdate();	
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the balance for '" + otherMonies +
						"' of amount " + amount + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolOtherMoniesDAO#updateOtherMonies(com.yahoo.petermwenda83.bean.othermoney.OtherMonies)
	 */
	@Override
	public boolean deductOtherMonies(OtherMonies otherMonies,double amount) {
		
		boolean success = true;
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE OtherMonies " +
							"SET Amount = (SELECT Amount FROM OtherMonies WHERE StudentUuid=? "
							+ ") - ? " +				
							"WHERE Uuid = (SELECT Uuid FROM OtherMonies WHERE StudentUuid=? );");
	
					) {
				
				
				pstmt.setString(1, otherMonies.getStudentUuid());	
				pstmt.setDouble(2, amount);
				pstmt.setString(3, otherMonies.getStudentUuid());	
			    pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the balance for '" + otherMonies +
			    "' of amount " + amount+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolOtherMoniesDAO#changeOtherMoniesStatus(com.yahoo.petermwenda83.bean.othermoney.OtherMonies)
	 */
	@Override
	public boolean changeOtherMoniesStatus(OtherMonies otherMonies) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE OtherMonies SET Status = ? WHERE Studentuuid = ?;");
	               ) {           			 	            
			  
	            pstmt.setString(1,otherMonies.getStatus());
	            pstmt.setString(2, otherMonies.getStudentUuid());
	            pstmt.executeUpdate();
	
	  } catch (SQLException e) {
	    logger.error("SQL Exception when updating otherMonies " + otherMonies);
	    logger.error(ExceptionUtils.getStackTrace(e));
	    System.out.println(ExceptionUtils.getStackTrace(e));
	    success = false;
	  } 
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.persistence.othermoney.SchoolOtherMoniesDAO#getOtherMoniesList()
	 */
	@Override
	public List<OtherMonies> getOtherMoniesList() {
		List<OtherMonies>  list = null;		
		 try(   
      		Connection conn = dbutils.getConnection();
      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM OtherMonies;");          		
  		) {			     
			 try( ResultSet rset = pstmt.executeQuery();){
	     	       
				 list = beanProcessor.toBeanList(rset, OtherMonies.class);
	      }
	
      } catch(SQLException e){
      	logger.error("SQL Exception when getting all OtherMonies");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
      }
    
		
		return list;
	}

	
	
}
