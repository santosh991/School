/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.main.Main;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class MainDAO extends DBConnectDAO implements SchoolMainDAO {
	private static MainDAO mainDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static MainDAO getInstance() {
		if(mainDAO == null){
			mainDAO = new MainDAO();		
			}
		return mainDAO;
	}
	
	public MainDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public MainDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDAO#get(java.lang.String)
	 */
	@Override
	public Main get(String mainname) {
		Main main = new Main();
		  ResultSet rset = null;
		     try(
		     		 Connection conn = dbutils.getConnection();
		        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Main WHERE mainname = ?;");       
		     		
		     		){
		     	
		     	 pstmt.setString(1, mainname);
			         rset = pstmt.executeQuery();
			     while(rset.next()){
			
			    	 main  = beanProcessor.toBean(rset,Main.class);
			   }
		     	
		     	
		     	
		     }catch(SQLException e){
		     	 logger.error("SQL Exception when getting Main with mainname: " + mainname);
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		     }
		
		return main;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDAO#put(com.yahoo.petermwenda83.contoller.exam.main.Main)
	 */
	@Override
	public boolean put(Main main) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Main" 
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
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDAO#delete(com.yahoo.petermwenda83.contoller.exam.main.Main)
	 */
	@Override
	public boolean delete(Main main) {
		 boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Main WHERE Mainname = ? ;");
        		){
	            pstmt.setString(1, main.getMainname());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to delete main: "+main);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.main.SchoolMainDAO#getAllMain()
	 */
	@Override
	public List<Main> getAllMain() {
		List<Main> list =new  ArrayList<>(); 
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Main ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, Main.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all Main");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

}
