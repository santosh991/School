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


	

	/**
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

	
	/**
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

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getNtStaff(java.lang.String)
	 */
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

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getNtstaffPos(java.lang.String)
	 */
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

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#putStaff(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
	public boolean putStaff(Employees emp) {
		boolean success = true;
		Teacher teacher = new Teacher();
		NTstaff ntstaff = new NTstaff();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Teacher" 
			        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nhifno,Phone"
			        		+ "DOB,NationalID,TeacherNumber,County) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO Ntstaff" 
		        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nhifno,Phone"
			        		+ "DOB,NationalID,County) VALUES (?,?,?,?,?,?,?,?,?,?);");
        		){
			  if(emp instanceof Teacher ){
	            pstmt.setString(1, teacher.getTeacherUuid());
	            pstmt.setString(2, teacher.getFirstName());
	            pstmt.setString(3, teacher.getLastName());
	            pstmt.setString(4, teacher.getSurname());
	            pstmt.setString(5, teacher.getNhifno());
	            pstmt.setString(6, teacher.getNssfno());
	            pstmt.setString(7, teacher.getPhone());
	            pstmt.setString(8, teacher.getDOB());
	            pstmt.setString(9, teacher.getNationalID());
	            pstmt.setString(10, teacher.getTeacherNumber());
	            pstmt.setString(11, teacher.getCounty());
	           
	          
	            
	            
	            pstmt.executeUpdate();
			  }else{         //NTstaff
				    pstmt2.setString(1, ntstaff.getNTstaffUuid());
		            pstmt2.setString(2, ntstaff.getFirstName());
		            pstmt2.setString(3, ntstaff.getLastName());
		            pstmt2.setString(4, ntstaff.getSurname());
		            pstmt2.setString(5, ntstaff.getNhifno());
		            pstmt2.setString(6, ntstaff.getNssfno());
		            pstmt2.setString(7, ntstaff.getPhone());
		            pstmt2.setString(8, ntstaff.getDOB());
		            pstmt2.setString(9, ntstaff.getNationalID());
		            pstmt2.setString(10, ntstaff.getCounty());
				   
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
		boolean success = true;
		Position pos = new Position();
		NTPosition ntpos = new NTPosition();
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Position" 
			        		+"(Uuid, TeacherUuid, Position, Salary) VALUES (?,?,?,?);");
				  PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO Ntposition" 
			        		+"(Uuid, NTstaffUuid, Position, Salary) VALUES (?,?,?,?);");
      		){
			   if(emp instanceof Position){
	            pstmt.setString(1, pos.getUuid());
	            pstmt.setString(2, pos.getTeacherUuid());
	            pstmt.setString(3, pos.getPosition());
	            pstmt.setString(4, pos.getSalary());
	            pstmt.executeUpdate();
			   }else{
				    pstmt2.setString(1, ntpos.getUuid());
		            pstmt2.setString(2, ntpos.getNTstaffUuid());
		            pstmt2.setString(3, ntpos.getPosition());
		            pstmt2.setString(4, ntpos.getSalary());
		            pstmt2.executeUpdate(); 
			   }
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Employees Position: "+emp);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           success = false;
		 }
		 
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#editStaff(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
	public boolean editStaff(Employees emp) {
		boolean success = true;
		Teacher teacher = new Teacher();
		NTstaff ntstaff = new NTstaff();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Teacher SET "
	        			+ "FirstName=?, LastName=?, Surname =?, Nhifno =?, Nssfno =?, "
	        			+ "Phone =?, DOB =?, NationalID =?, TeacherNumber =?,"
	        			+ " County =? WHERE Uuid = ?;");
				PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Ntstaff SET "
	        			+ "FirstName=?, LastName=?, Surname =?, Nhifno =?, Nssfno =?, "
	        			+ "Phone =?, DOB =?, NationalID =?,"
	        			+ " County =? WHERE Uuid = ?;");
        		){
			  if(emp instanceof Teacher ){
	           
	            pstmt.setString(1, teacher.getFirstName());
	            pstmt.setString(2, teacher.getLastName());
	            pstmt.setString(3, teacher.getSurname());
	            pstmt.setString(4, teacher.getNhifno());
	            pstmt.setString(5, teacher.getNssfno());
	            pstmt.setString(6, teacher.getPhone());
	            pstmt.setString(7, teacher.getDOB());
	            pstmt.setString(8, teacher.getNationalID());
	            pstmt.setString(9, teacher.getTeacherNumber());
	            pstmt.setString(10, teacher.getCounty());
	            pstmt.setString(11, teacher.getTeacherUuid());
	          
	            
	            
	            pstmt.executeUpdate();
			  }else{         //NTstaff
				   
		            pstmt2.setString(1, ntstaff.getFirstName());
		            pstmt2.setString(2, ntstaff.getLastName());
		            pstmt2.setString(3, ntstaff.getSurname());
		            pstmt2.setString(4, ntstaff.getNhifno());
		            pstmt2.setString(5, ntstaff.getNssfno());
		            pstmt2.setString(6, ntstaff.getPhone());
		            pstmt2.setString(7, ntstaff.getDOB());
		            pstmt2.setString(8, ntstaff.getNationalID());
		            pstmt2.setString(9, ntstaff.getCounty());
		            pstmt2.setString(10, ntstaff.getNTstaffUuid());
				   
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+emp);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             success = false;
		 }
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#editStaffPos(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
	public boolean editStaffPos(Employees emp) {
		boolean success = true;
		Position pos = new Position();
		NTPosition ntpos = new NTPosition();
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Position SET "
	        			+ "Position=?, Salary=? WHERE TeacherUuid = ?;");
				  PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Ntposition SET "
		        			+ "Position=?, Salary=? WHERE TeacherUuid = ?;");
      		){
			   if(emp instanceof Position){
	            pstmt.setString(1, pos.getPosition());
	            pstmt.setString(2, pos.getSalary());
	            pstmt.setString(3, pos.getTeacherUuid());
	            pstmt.executeUpdate();
			   }else{				   
		            pstmt2.setString(1, ntpos.getPosition());
		            pstmt2.setString(2, ntpos.getSalary());
		            pstmt2.setString(3, ntpos.getNTstaffUuid());
		            pstmt2.executeUpdate(); 
			   }
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to edit Employees Position: "+emp);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           success = false;
		 }
		 
		
		
		return success;
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
	

	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getTeacher()
	 */
	@Override
	public List<Teacher> getAllTeacher() {
		List<Teacher>  list = null;
		
		 try(   
     		Connection conn = dbutils.getConnection();
     		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Teacher ;");   
     		ResultSet rset = pstmt.executeQuery();
 		) {
     	
         list = beanProcessor.toBeanList(rset, Teacher.class);

     } catch(SQLException e){
     	logger.error("SQL Exception when getting all Teacher");
         logger.error(ExceptionUtils.getStackTrace(e));
     }
   
		
		return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getNTstaff()
	 */
	@Override
	public List<NTstaff> getAllNTstaff() {
		List<NTstaff>  list = null;
		
		 try(   
     		Connection conn = dbutils.getConnection();
     		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM NTstaff ;");   
     		ResultSet rset = pstmt.executeQuery();
 		) {
     	
         list = beanProcessor.toBeanList(rset, NTstaff.class);

     } catch(SQLException e){
     	logger.error("SQL Exception when getting all NTstaff");
         logger.error(ExceptionUtils.getStackTrace(e));
     }
   
		
		return list;
	}
}
