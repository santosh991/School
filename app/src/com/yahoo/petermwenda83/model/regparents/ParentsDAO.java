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

import com.yahoo.petermwenda83.contoller.guardian.StudentParent;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter
 *
 */
public class ParentsDAO extends DBConnectDAO  implements SchoolParentsDAO {

	private static ParentsDAO parentsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static ParentsDAO getInstance(){
		
		if(parentsDAO == null){
			parentsDAO = new ParentsDAO();		
		}
		return parentsDAO;
	}
	
	/**
	 * 
	 */
	public ParentsDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public ParentsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolParentsDAO#getStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)
	 */
	public StudentParent getStudentParent(StudentParent Parent) {
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Parent WHERE studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Parent.getStudentUuid()); 
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 Parent  = beanProcessor.toBean(rset,StudentParent.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with uuid: " + Parent);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return Parent; 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolParentsDAO#putStudentParent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentParent)
	 */
	public boolean putStudentParent(StudentParent parent) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Parent (studentUuid" 
			        		+"fatherName,fatherPhone,fatherOccupation,fatherID,fatherEmail,"
      			+ "motherName,motherPhone,motherOccupation,"
      			+ "motherEmail,motherID,relationship) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
      		){
			   
			    pstmt.setString(1, parent.getStudentUuid());
			    
			    pstmt.setString(2, parent.getFathername());
	            pstmt.setString(3, parent.getFatherphone());
	            pstmt.setString(4, parent.getFatheroccupation());
	            pstmt.setString(5, parent.getFatherID());
	            pstmt.setString(6, parent.getFatherEmail());
	           
	            pstmt.setString(7, parent.getMotherrname());
	            pstmt.setString(8, parent.getMotherphone());
	            pstmt.setString(9, parent.getMotheroccupation());
	            pstmt.setString(10, parent.getMotherEmail());
	            pstmt.setString(11, parent.getMotherID());
	            
	            pstmt.setString(12, parent.getRelationship());
	            
	           
	            
	            
	            pstmt.executeUpdate();

			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put StudentParent: "+parent);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           success = false;
		 }
		 
		
		
		return success;
	}

	


	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolParentsDAO#ediStudentParent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentParent)
	 */
	public boolean ediStudentParent(StudentParent parent) {
		boolean success = true;
		  try (  Connection conn = dbutils.getConnection();
      	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Parent SET fatherName=?,"
      			+ "fatherPhone=?, fatherOccupation=?,fatherID =?,fatherEmail =?"
      			+ " ,motherName =?,motherPhone =?,motherOccupation =?,"
      			+ "motherEmail =?,motherID =?,relationship   WHERE studentUuid = ?;");
      	) {
              
	            pstmt.setString(1, parent.getFathername());
	            pstmt.setString(2, parent.getFatherphone());
	            pstmt.setString(3, parent.getFatheroccupation());
	            pstmt.setString(4, parent.getFatherID());
	            pstmt.setString(5, parent.getFatherEmail());
	           
	            pstmt.setString(6, parent.getMotherrname());
	            pstmt.setString(7, parent.getMotherphone());
	            pstmt.setString(8, parent.getMotheroccupation());
	            pstmt.setString(9, parent.getMotherEmail());
	            pstmt.setString(10, parent.getMotherID());
	            
	            pstmt.setString(11, parent.getRelationship());
	            
	            pstmt.setString(12, parent.getStudentUuid());
	            
	            
	            pstmt.executeUpdate();

      } catch (SQLException e) {
          logger.error("SQL Exception when updating Student_Parent: " + parent);
          logger.error(ExceptionUtils.getStackTrace(e));
          success = false;
      } 
      		
		return success;
	}

	
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolParentsDAO#deleteStudentParent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.guardian.StudentParent)
	 */
	@Override
	public boolean deleteStudentParent(StudentParent parent) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * @see com.yahoo.petermwenda83.model.regparents.SchoolParentsDAO#getAllStudentParent()
	 */
	public List<StudentParent> getAllStudentParent() {
		List<StudentParent>  list = null;
		
		 try(   
     		Connection conn = dbutils.getConnection();
     		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Parent ;");   
     		ResultSet rset = pstmt.executeQuery();
 		) {
     	
         list = beanProcessor.toBeanList(rset, StudentParent.class);

     } catch(SQLException e){
     	logger.error("SQL Exception when getting all StudentParent");
         logger.error(ExceptionUtils.getStackTrace(e));
         System.out.println(ExceptionUtils.getStackTrace(e));
     }
   
		
		return list;
	}

	

}
