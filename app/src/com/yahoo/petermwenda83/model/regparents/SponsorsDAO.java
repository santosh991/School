/**
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
 * @author peter
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
	public StudentSponsor getStudentSponser(StudentSponsor Sponser) {
		ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_sponsor"
           	      		+ " WHERE studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Sponser.getStudentUuid()); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 Sponser  = beanProcessor.toBean(rset,StudentSponsor.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting StudentSponsor : " + Sponser);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return Sponser; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#putStudentSponser(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	public boolean putStudentSponser(StudentSponsor sponsor) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student_sponsor ("
						+ "StudentUuid "
  			+ "sponsorName,sponsorPhone,nationalID,sponsorOccupation,"
  			+ "sponsorCoutry,sponsorCounty) VALUES (?,?,?,?);");
  		){     
			   
			    pstmt.setString(1, sponsor.getStudentUuid());
			    
			    pstmt.setString(2, sponsor.getName()); 
			    pstmt.setString(3, sponsor.getPhone());
	            pstmt.setString(3, sponsor.getNationalID());
	            pstmt.setString(4, sponsor.getOccupation());
	            pstmt.setString(4, sponsor.getCountry());
	            pstmt.setString(4, sponsor.getCounty());
	           	           
	    	    pstmt.executeUpdate();

			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put StudentSponsor: "+sponsor);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#editStudentSponser(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	public boolean editStudentSponser(StudentSponsor sponsor) {
		boolean success = true;
		  try (  Connection conn = dbutils.getConnection();
  	PreparedStatement pstmt = conn.prepareStatement("UPDATE student_sponsor SET sponsorName=?,"
  			+ "sponsorPhone=?, nationalID=?,sponsorOccupation =?"
  			+ ",sponsorCoutry =?,sponsorCounty =? WHERE studentUuid = ?;");
  	) {		   
  			
			   			    
			    pstmt.setString(1, sponsor.getName()); 
			    pstmt.setString(2, sponsor.getPhone());
	            pstmt.setString(3, sponsor.getNationalID());
	            pstmt.setString(4, sponsor.getOccupation());
	            pstmt.setString(4, sponsor.getCountry());
	            pstmt.setString(6, sponsor.getCounty());
	            pstmt.setString(7, sponsor.getStudentUuid());
	           	           

  } catch (SQLException e) {
      logger.error("SQL Exception when updating StudentSponsor: " + sponsor);
      logger.error(ExceptionUtils.getStackTrace(e));
      success = false;
  } 
  		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#deleteStudentSponser(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)
	 */
	@Override
	public boolean deleteStudentSponser(StudentSponsor sponser) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolSponsorsDAO#getAllStudentSponser()
	 */
	public List<StudentSponsor> getAllStudentSponser() {
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
