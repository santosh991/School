/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.main.MainDetails;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class MainDetailsDAO extends DBConnectDAO implements SchoolMainDetailsDAO {

	private static MainDetailsDAO mainDetailsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static MainDetailsDAO getInstance() {
		if(mainDetailsDAO == null){
			mainDetailsDAO = new MainDetailsDAO();		
			}
		return mainDetailsDAO;
	}
	
	public MainDetailsDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public MainDetailsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDetailsDAO#get(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public MainDetails get(String mainUuid, String roomNameUuid, String subjectUuid) {
		MainDetails mainDetails = new MainDetails();
		  ResultSet rset = null;
		     try(
		     		 Connection conn = dbutils.getConnection();
		        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MainDetails WHERE mainUuid = ?"
		        	      		+ "AND roomNameUuid = ? AND subjectUuid = ? ;");       
		     		
		     		){
		     	
		     	 pstmt.setString(1, mainUuid);
		     	 pstmt.setString(1, roomNameUuid);
		     	 pstmt.setString(1, subjectUuid);
			         rset = pstmt.executeQuery();
			     while(rset.next()){
			
			    	 mainDetails  = beanProcessor.toBean(rset,MainDetails.class);
			   }
		     	
		     	
		     	
		     }catch(SQLException e){
		     	 logger.error("SQL Exception when getting MainDetails with mainUuid: " +mainUuid+"And roomNameUuid"
		     	 		+ ""+roomNameUuid+"And subjectUuid "+subjectUuid);
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		     }
		
		return mainDetails;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDetailsDAO#get(com.yahoo.petermwenda83.contoller.exam.main.MainDetails)
	 */
	@Override
	public boolean put(MainDetails maindetails) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MainDetails" 
			        		+"(Uuid, Mainname) VALUES (?,?);");
  		){
			   
	            pstmt.setString(1, main.getUuid());
	            pstmt.setString(2, main.getMainname());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Main: "+main);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       System.out.println(ExceptionUtils.getStackTrace(e));
       success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDetailsDAO#edit(com.yahoo.petermwenda83.contoller.exam.main.MainDetails)
	 */
	@Override
	public boolean edit(MainDetails maindetails) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDetailsDAO#delete(com.yahoo.petermwenda83.contoller.exam.main.MainDetails)
	 */
	@Override
	public boolean delete(MainDetails maindetails) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("DELETE MainDetails WHERE mainUuid = ?"
		        	      		+ "AND roomNameUuid = ? AND subjectUuid = ? ;");
       		){
	           pstmt.setString(1, maindetails.getMainuuid());
	           pstmt.setString(2, maindetails.getRoomnameuuid());
	           pstmt.setString(3, maindetails.getStudentUuid());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			logger.error("SQL Exception trying to delete maindetails: "+maindetails);
            logger.error(ExceptionUtils.getStackTrace(e)); 
            System.out.println(ExceptionUtils.getStackTrace(e));
            success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDetailsDAO#getAllMainDetails()
	 */
	@Override
	public List<MainDetails> getAllMainDetails() {
		List<MainDetails> list =new  ArrayList<>(); 
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM MainDetails ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, MainDetails.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all MainDetails");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDetailsDAO#getAllMainDetails(java.lang.String)
	 */
	@Override
	public List<MainDetails> getAllMainDetails(String Term) {
		List<MainDetails> list =new  ArrayList<>(); 
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM MainDetails WHERE Term =? ;");   
				
	      		
	  		) {
	      	
			  pstmt.setString(1, Term);
			  ResultSet rset = pstmt.executeQuery();
	          list = beanProcessor.toBeanList(rset, MainDetails.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all MainDetails where term is:"+Term);
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

}
