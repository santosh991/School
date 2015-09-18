/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.exam.cat.Cat;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class CatDAO extends DBConnectDAO implements SchoolCatDAO {
	private static CatDAO catDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static CatDAO getInstance() {
		if(catDAO == null){
			catDAO = new CatDAO();		
			}
		return catDAO;
	}
	
	public CatDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public CatDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDAO#get(java.lang.String)
	 */
	@Override
	public Cat get(String CatName) {
		 Cat cat = new Cat();
		  ResultSet rset = null;
		     try(
		     		 Connection conn = dbutils.getConnection();
		        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Cat WHERE CatName = ?;");       
		     		
		     		){
		     	
		     	 pstmt.setString(1, CatName);
			         rset = pstmt.executeQuery();
			     while(rset.next()){
			
			    	 cat  = beanProcessor.toBean(rset,Cat.class);
			   }
		     	
		     	
		     	
		     }catch(SQLException e){
		     	 logger.error("SQL Exception when getting Cat with CatName: " + CatName);
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		     }
		 
		return cat;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDAO#put(com.yahoo.petermwenda83.contoller.exam.cat.Cat)
	 */
	@Override
	public boolean put(Cat cat) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Cat" 
			        		+"(Uuid, Catname) VALUES (?,?);");
    		){
			   
	            pstmt.setString(1, cat.getUuid());
	            pstmt.setString(2, cat.getCatname());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Cat: "+cat);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDAO#delete(com.yahoo.petermwenda83.contoller.exam.cat.Cat)
	 */
	@Override
	public boolean delete(Cat cat) {
		 boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Cat WHERE Catname = ? ;");
        		){
	            pstmt.setString(1, cat.getCatname());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to delete Cat: "+cat);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDAO#getAllCats()
	 */
	@Override
	public List<Cat> getAllCats() {
		 List<Cat> list =new  ArrayList<>(); 
			try(   
		      		Connection conn = dbutils.getConnection();
		      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Cat ;");   
		      		ResultSet rset = pstmt.executeQuery();
		  		) {
		      	
		          list = beanProcessor.toBeanList(rset, Cat.class);

		      } catch(SQLException e){
		      	  logger.error("SQL Exception when getting all Cat");
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		      }
			return list;
	}

}
