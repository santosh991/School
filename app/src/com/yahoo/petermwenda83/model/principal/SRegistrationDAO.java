/**
 * 
 */
package com.yahoo.petermwenda83.model.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.staff.Employees;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;
import com.yahoo.petermwenda83.contoller.staff.teaching.Position;
import com.yahoo.petermwenda83.contoller.staff.teaching.Teacher;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 *@author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class SRegistrationDAO extends DBConnectDAO  implements StaffRegistrationDAO {

	
	private static SRegistrationDAO srDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static SRegistrationDAO getInstance() {
		if(srDAO == null){
			srDAO = new SRegistrationDAO();		
			}
		return srDAO;
	}
	
	public SRegistrationDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public SRegistrationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getTeacher(java.lang.String)
	 */
	@Override
	public Teacher getStaff(String uuid) {
		Teacher teacher  = null;
         ResultSet rset = null;
      try(
      		 Connection conn = dbutils.getConnection();
         	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Teacher WHERE Uuid = ?;");       
      		
      		){
      	
      	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 teacher  = beanProcessor.toBean(rset,Teacher.class);
	   }
      	
      	
      	
      }catch(SQLException e){
      	  logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
           logger.error(ExceptionUtils.getStackTrace(e));
      }
      
		return teacher; 
		 
	}

	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getstaffPos(java.lang.String)
	 */
	public Position getstaffPos(String Uuid) {
		Position pos = new Position();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Position WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 pos  = beanProcessor.toBean(rset,Position.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting ExamType with uuid: " + Uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return pos; 
	}

	@Override
	public Employees getNtStaff(String uuid) {
		NTstaff ntstaff = new NTstaff();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Ntstaff WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 ntstaff  = beanProcessor.toBean(rset,NTstaff.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return ntstaff; 
		 
	}

	@Override
	public NTPosition getNtstaffPos(String uuid) {
		NTPosition ntsstaffpos = new NTPosition();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Ntposition WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 ntsstaffpos  = beanProcessor.toBean(rset,NTPosition.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting ExamType with uuid: " + uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
     }
     
		return ntsstaffpos; 
		 
	}

	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#putStaff(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
	public boolean putStaff(Employees emp) {
		boolean success = true;
		Teacher teacher = new Teacher();
		NTstaff ntstaff = new NTstaff();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Teacher" 
			        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nhifno,Phone"
			        		+ "DOB,NationalID,TeacherNumber,County) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO Ntstaff" 
		        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nhifno,Phone"
			        		+ "DOB,NationalID,County) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
        		){
			  if(emp instanceof Teacher ){
	            pstmt.setString(1, teacher.getUuid());
	            pstmt.setString(2, teacher.getFirstName());
	            pstmt.setString(3, teacher.getLastName());
	            pstmt.setString(4, teacher.getSurname());
	            pstmt.setString(5, teacher.getNhifno());
	            pstmt.setString(6, teacher.getNssfno());
	            pstmt.setString(7, teacher.getNhifno());
	            pstmt.setString(8, teacher.getPhone());
	            pstmt.setString(9, teacher.getDOB());
	            pstmt.setString(10, teacher.getNationalID());
	            pstmt.setString(11, teacher.getTeacherNumber());
	            pstmt.setString(12, teacher.getCounty());
	          
	            
	            
	            pstmt.executeUpdate();
			  }else{         //NTstaff
				    pstmt2.setString(1, ntstaff.getUuid());
		            pstmt2.setString(2, ntstaff.getFirstName());
		            pstmt2.setString(3, ntstaff.getLastName());
		            pstmt2.setString(4, ntstaff.getSurname());
		            pstmt2.setString(5, ntstaff.getNhifno());
		            pstmt2.setString(6, ntstaff.getNssfno());
		            pstmt2.setString(7, ntstaff.getNhifno());
		            pstmt2.setString(8, ntstaff.getPhone());
		            pstmt2.setString(9, ntstaff.getDOB());
		            pstmt2.setString(10, ntstaff.getNationalID());
		            pstmt2.setString(11, ntstaff.getCounty());
				   
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+emp);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}
	



	@Override
	public boolean putstaffPOss(Employees emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editStaff(Employees emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editStaffPos(Employees emp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStaf(Employees emp, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStaffPOs(Employees emp, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}
	

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getTeacher()
	 */
	@Override
	public List<Teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getNTstaff()
	 */
	@Override
	public List<Teacher> getAllNTstaff() {
		// TODO Auto-generated method stub
		return null;
	}
}
