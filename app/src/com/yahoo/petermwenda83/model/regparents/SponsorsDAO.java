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
package com.yahoo.petermwenda83.model.regparents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.guardian.StudentSponsor;
import com.yahoo.petermwenda83.model.DBConnectDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class SponsorsDAO extends DBConnectDAO implements SchoolSponsorsDAO {

	private static SponsorsDAO sponsorsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static SponsorsDAO getInstance(){
		
		if(sponsorsDAO == null){
			sponsorsDAO = new SponsorsDAO();		
		}
		return sponsorsDAO;
	}
	
	/**
	 * 
	 */
	public SponsorsDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public SponsorsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#getStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	public StudentSponsor getStudentSponsor(String StudentUuid) {
		StudentSponsor sponser = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_sponsor"
           	      		+ " WHERE studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, StudentUuid); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 sponser  = beanProcessor.toBean(rset,StudentSponsor.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentSponsor with StudentUuid : " + StudentUuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e)); 
        }
        
		return sponser; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#getStudentSponserById(java.lang.String)
	 */
	public StudentSponsor getStudentSponsorById(String NationalID) {
		StudentSponsor sponser = null;
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_sponsor"
           	      		+ " WHERE NationalID = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, NationalID); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 sponser  = beanProcessor.toBean(rset,StudentSponsor.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentSponsor with NationalID : " + NationalID);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e)); 
        }
        
		return sponser; 
	}
	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#putStudentSponser(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	public boolean putStudentSponsor(StudentSponsor sponsor) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student_sponsor ("
						+ "Uuid,StudentUuid ,"
  			+ "Name,Phone,nationalID,Occupation,"
  			+ "Country,County) VALUES (?,?,?,?,?,?,?,?);");
  		){     
			   
			    pstmt.setString(1, sponsor.getUuid()); 
			    pstmt.setString(2, sponsor.getStudentUuid()); 
			    pstmt.setString(3, sponsor.getName()); 
			    pstmt.setString(4, sponsor.getPhone());
	            pstmt.setString(5, sponsor.getNationalID());
	            pstmt.setString(6, sponsor.getOccupation());
	            pstmt.setString(7, sponsor.getCountry());
	            pstmt.setString(8, sponsor.getCounty());
	           	           
	    	    pstmt.executeUpdate();

			 
		 }catch(SQLException e){
	   logger.error("SQL Exception trying to put StudentSponsor: "+sponsor);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       System.out.println(ExceptionUtils.getStackTrace(e)); 
       success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#editStudentSponser(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	public boolean editStudentSponsor(StudentSponsor sponsor,String StudentUuid) {
		boolean success = true;
		  try (  Connection conn = dbutils.getConnection();
  	             PreparedStatement pstmt = conn.prepareStatement("UPDATE student_sponsor SET Name=?,"
  			+ "Phone=?, nationalID=?,Occupation =?,"
  			+ "County =?,Country =? WHERE StudentUuid = ?;");
  	          ) {		   			   			    
			    pstmt.setString(1, sponsor.getName()); 
			    pstmt.setString(2, sponsor.getPhone());
	            pstmt.setString(3, sponsor.getNationalID());
	            pstmt.setString(4, sponsor.getOccupation());
	            pstmt.setString(5, sponsor.getCounty());
	            pstmt.setString(6, sponsor.getCountry());
	            pstmt.setString(7, StudentUuid);
	            pstmt.executeUpdate();
	           	           

  } catch (SQLException e) {
      logger.error("SQL Exception when updating StudentSponsor: " + sponsor+"with StudentUuid" +StudentUuid);
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e)); 
      success = false;
  } 
  		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#deleteStudentSponser(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	public boolean deleteStudentSponsor(StudentSponsor sponser) {
		 boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM student_sponsor"
	         	      		+ " WHERE StudentUuid = ?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, sponser.getStudentsUuid());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting sponser : " +sponser);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#getAllStudentSponser()
	 */
	public List<StudentSponsor> getAllStudentSponsor() {
		List<StudentSponsor>  list = null;
		
		 try(   
   		Connection conn = dbutils.getConnection();
   		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM student_sponsor ;");   
   		ResultSet rset = pstmt.executeQuery();
		) {
   	
       list = beanProcessor.toBeanList(rset, StudentSponsor.class);

   } catch(SQLException e){
      	logger.error("SQL Exception when getting all StudentSponsor");
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e)); 
   }
 
		
		return list;
	
	}

	

}
