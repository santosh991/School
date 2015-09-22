/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.cat.CatDetails;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class CatDetailsDAO extends DBConnectDAO implements SchoolCatDetailsDAO {

	private static CatDetailsDAO catDetailsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static CatDetailsDAO getInstance() {
		if(catDetailsDAO == null){
			catDetailsDAO = new CatDetailsDAO();		
			}
		return catDetailsDAO;
	}
	
	public CatDetailsDAO() {
		super();
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public CatDetailsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDetailsDAO#get(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CatDetails get(String catUuid, String roomNameUuid, String subjectUuid) {
		CatDetails catDetails = new CatDetails();
		  ResultSet rset = null;
		     try(
		     		 Connection conn = dbutils.getConnection();
		        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CatDetails WHERE catUuid = ?"
		        	      		+ "AND roomNameUuid = ?  AND subjectUuid = ?;");       
		     		
		     		){
		     	
		     	 pstmt.setString(1, catUuid);
		     	 pstmt.setString(2, roomNameUuid);
		     	 pstmt.setString(3, subjectUuid);
			         rset = pstmt.executeQuery();
			     while(rset.next()){
			
			    	 catDetails  = beanProcessor.toBean(rset,CatDetails.class);
			   }
		     	
		     	
		     	
		     }catch(SQLException e){
		     	 logger.error("SQL Exception when getting CatDetails with catUuid: "+catUuid+"And "
		     	 		+ "roomNameUuid"+roomNameUuid+"And subjectUuid"+subjectUuid);
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		     }
		
		return catDetails;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDetailsDAO#put(com.yahoo.petermwenda83.contoller.exam.cat.CatDetails)
	 */
	@Override
	public boolean put(CatDetails catdetals) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CatDetails" 
			        		+"(Uuid, Catuuid,Roomnameuuid,SubjectUuid,Term,Year,Outof) VALUES (?,?,?,?,?,?,?);");
 		){
			   
	            pstmt.setString(1, catdetals.getUuid());
	            pstmt.setString(2, catdetals.getCatuuid());
	            pstmt.setString(3, catdetals.getRoomnameuuid());
	            pstmt.setString(4, catdetals.getSubjectUuid());
	            pstmt.setString(5, catdetals.getTerm());
	            pstmt.setString(6, catdetals.getYear());
	            pstmt.setInt(7, catdetals.getOutof());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
	   logger.error("SQL Exception trying to put CatDetails: "+catdetals);
      logger.error(ExceptionUtils.getStackTrace(e)); 
      System.out.println(ExceptionUtils.getStackTrace(e));
      success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDetailsDAO#edit(com.yahoo.petermwenda83.contoller.exam.cat.CatDetails)
	 */
	@Override
	public boolean edit(CatDetails catdetals) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE CatDetails SET Term =?, Year =?,Outof =? "
						+ "WHERE Catuuid =? AND Roomnameuuid =? AND SubjectUuid =?  ;");
		){
			   
			    pstmt.setString(1, catdetals.getTerm());
	            pstmt.setString(2, catdetals.getYear());
	            pstmt.setInt(3, catdetals.getOutof());
	            pstmt.setString(4, catdetals.getCatuuid());
	            pstmt.setString(5, catdetals.getRoomnameuuid());
	            pstmt.setString(6, catdetals.getSubjectUuid());
	          
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
	   logger.error("SQL Exception trying to put CatDetails: "+catdetals);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       System.out.println(ExceptionUtils.getStackTrace(e));
       success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDetailsDAO#delete(com.yahoo.petermwenda83.contoller.exam.cat.CatDetails)
	 */
	@Override
	public boolean delete(CatDetails catdetails) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CatDetails WHERE Catuuid = ?"
		        	      		+ "AND roomNameUuid = ? AND subjectUuid = ? ;");
      		){
	           pstmt.setString(1, catdetails.getCatuuid());
	           pstmt.setString(2, catdetails.getRoomnameuuid());
	           pstmt.setString(3, catdetails.getSubjectUuid());
	           pstmt.executeUpdate();
			 
		 }catch(SQLException e){
		   logger.error("SQL Exception trying to delete maindetails: "+catdetails);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDetailsDAO#getAllCatDetails()
	 */
	@Override
	public List<CatDetails> getAllCatDetails() {
		List<CatDetails> list =new  ArrayList<>(); 
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatDetails ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, CatDetails.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all CatDetails");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.cat.SchoolCatDetailsDAO#getAllCatDetails(java.lang.String)
	 */
	@Override
	public List<CatDetails> getAllCatDetails(String Term) {
		 List<CatDetails> list =new  ArrayList<>(); 
			try(   
		      		Connection conn = dbutils.getConnection();
		      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatDetails WHERE Term =? ;");   
		      		
		  		) {
				  pstmt.setString(1, Term);
				  ResultSet rset = pstmt.executeQuery();
		          list = beanProcessor.toBeanList(rset, CatDetails.class);

		      } catch(SQLException e){
		      	  logger.error("SQL Exception when getting all CatDetails where Term is"+Term);
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		      }
			return list;
	}

	@Override
	public List<CatDetails> getAllCatDetail(String CatUuid, String RoomNameUuid, String SubjectUuid,String Term) {
		 List<CatDetails> list =new  ArrayList<>(); 
			try(   
		      		Connection conn = dbutils.getConnection();
		      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatDetails WHERE CatUuid =?"
		      				+ "AND RoomNameUuid =? AND SubjectUuid =? ;");   
		      		
		  		) {
				  pstmt.setString(1, CatUuid);
				  pstmt.setString(2, RoomNameUuid);
				  pstmt.setString(3, SubjectUuid);
				  ResultSet rset = pstmt.executeQuery();
		          list = beanProcessor.toBeanList(rset, CatDetails.class);

		      } catch(SQLException e){
		      	  logger.error("SQL Exception when getting all CatDetails where Term is");
		          logger.error(ExceptionUtils.getStackTrace(e));
		          System.out.println(ExceptionUtils.getStackTrace(e));
		      }
			return list;
	}

}
