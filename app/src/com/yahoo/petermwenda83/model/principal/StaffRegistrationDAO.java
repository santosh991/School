/**##########################################################
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
public class StaffRegistrationDAO extends DBConnectDAO  implements SchoolStaffRegistrationDAO {

	
	private static StaffRegistrationDAO srDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static StaffRegistrationDAO getInstance() {
		if(srDAO == null){
			srDAO = new StaffRegistrationDAO();		
			}
		return srDAO;
	}
	
	public StaffRegistrationDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public StaffRegistrationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#getTeacher(java.lang.String)
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
           System.out.println(ExceptionUtils.getStackTrace(e));
      }
      
		return teacher; 
		 
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#getstaffPos(java.lang.String)
	 */
	public Position getstaffPos(String TeacherUuid) {
		Position pos = new Position();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Position WHERE TeacherUuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, TeacherUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 pos  = beanProcessor.toBean(rset,Position.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception when getting ExamType with TeacherUuid: " + TeacherUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return pos; 
	}

	
	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#putStaff(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
	public boolean putStaff(Employees emp) {
		boolean success = true;
		Teacher teacher = new Teacher();
		NTstaff ntstaff = new NTstaff();
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Teacher" 
			        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nssfno,Phone,"
			        		+ "DOB,NationalID,TeacherNumber,County,Location) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO Ntstaff" 
		        		+"(Uuid, FirstName, LastName, Surname,Nhifno,Nssfno,Phone,"
			        		+ "DOB,NationalID,County,Location,Sublocation) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        		){
			  if(emp instanceof Teacher ){
	            pstmt.setString(1, teacher.getUuid());
	            pstmt.setString(2, emp.getFirstName());
	            pstmt.setString(3, emp.getLastName());
	            pstmt.setString(4, emp.getSurname());
	            pstmt.setString(5, emp.getNhifno());
	            pstmt.setString(6, emp.getNssfno());
	            pstmt.setString(7, emp.getPhone());
	            pstmt.setString(8, emp.getDOB());
	            pstmt.setString(9, emp.getNationalID());
	            pstmt.setString(10, emp.getTeacherNumber());
	            pstmt.setString(11, emp.getCounty());
	            pstmt.setString(12, emp.getLocation());
	           	            
	            pstmt.executeUpdate();
			  }else{         //NTstaff
				    pstmt2.setString(1, ntstaff.getUuid());
		            pstmt2.setString(2, emp.getFirstName());
		            pstmt2.setString(3, emp.getLastName());
		            pstmt2.setString(4, emp.getSurname());
		            pstmt2.setString(5, emp.getNhifno());
		            pstmt2.setString(6, emp.getNssfno());
		            pstmt2.setString(7, emp.getPhone());
		            pstmt2.setString(8, emp.getDOB());
		            pstmt2.setString(9, emp.getNationalID());
		            pstmt2.setString(10, emp.getCounty());
		            pstmt2.setString(11, emp.getLocation());
		            pstmt2.setString(12, emp.getSublocation());
				   
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+emp);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}
	



	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#putstaffPOss(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
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
	            pstmt.setString(2, emp.getEmployeeUuid());
	            pstmt.setString(3, emp.getPosition());
	            pstmt.setString(4, emp.getSalary());
	            pstmt.executeUpdate();
			   }else{
				    pstmt2.setString(1, ntpos.getUuid());
		            pstmt2.setString(2, emp.getEmployeeUuid());
		            pstmt2.setString(3, emp.getPosition());
		            pstmt2.setString(4, emp.getSalary());
		            pstmt2.executeUpdate(); 
			   }
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Employees Position: "+emp);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#editStaff(com.yahoo.petermwenda83.contoller.staff.Employees)
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
	           
	            pstmt.setString(1, emp.getFirstName());
	            pstmt.setString(2, emp.getLastName());
	            pstmt.setString(3, emp.getSurname());
	            pstmt.setString(4, emp.getNhifno());
	            pstmt.setString(5, emp.getNssfno());
	            pstmt.setString(6, emp.getPhone());
	            pstmt.setString(7, emp.getDOB());
	            pstmt.setString(8, emp.getNationalID());
	            pstmt.setString(9, emp.getTeacherNumber());
	            pstmt.setString(10, emp.getCounty());
	            pstmt.setString(11, emp.getUuid());
	          
	            
	            
	            pstmt.executeUpdate();
			  }else{         //NTstaff
				   
		            pstmt2.setString(1, emp.getFirstName());
		            pstmt2.setString(2, emp.getLastName());
		            pstmt2.setString(3, emp.getSurname());
		            pstmt2.setString(4, emp.getNhifno());
		            pstmt2.setString(5, emp.getNssfno());
		            pstmt2.setString(6, emp.getPhone());
		            pstmt2.setString(7, emp.getDOB());
		            pstmt2.setString(8, emp.getNationalID());
		            pstmt2.setString(9, emp.getCounty());
		            pstmt2.setString(10, emp.getUuid());
				   
		            pstmt2.executeUpdate(); 
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+emp);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#editStaffPos(com.yahoo.petermwenda83.contoller.staff.Employees)
	 */
	public boolean editStaffPos(Employees emp) {
		boolean success = true;
		Position pos = new Position();
		NTPosition ntpos = new NTPosition();
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Position SET "
	        			+ "Position=?, Salary=? WHERE TeacherUuid = ?;");
				  PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Ntposition SET "
		        			+ "Position=?, Salary=? WHERE NTstaffUuid = ?;");
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
             System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#deleteStaf(com.yahoo.petermwenda83.contoller.staff.Employees, java.lang.String)
	 */
	@Override
	public boolean deleteStaf(Employees emp, String uuid) {
		boolean success = true; 
		Teacher teacher = new Teacher();
		NTstaff ntstaff = new NTstaff();
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Teacher"
	         	      		+ " WHERE TeacherUuid = ?;");     
	    		  PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Ntstaff"
	         	      		+ " WHERE NTstaffUuid = ?;");     
	      		
	      		){
	      	 if(emp instanceof Teacher){
	      	     pstmt.setString(1, teacher.getUuid());
		         pstmt.executeUpdate();
	      	   }else{
	      		 pstmt.setString(1, ntstaff.getUuid());
		         pstmt.executeUpdate(); 
	      	   }
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting activity : " +teacher);
	      	   logger.error("SQL Exception when deletting activity : " +ntstaff);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#deleteStaffPOs(com.yahoo.petermwenda83.contoller.staff.Employees, java.lang.String)
	 */
	@Override
	public boolean deleteStaffPOs(Employees emp, String uuid) {
		boolean success = true; 
		Position pos = new Position();
		NTPosition ntpos = new NTPosition();
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Position"
	         	      		+ " WHERE TeacherUuid = ?;");  
	    		  PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Ntposition"
	         	      		+ " WHERE NTstaffUuid = ?;");  
	      		
	      		){
	    	  if(emp instanceof Teacher){
	      	     pstmt.setString(1, pos.getTeacherUuid());
		         pstmt.executeUpdate();
	    	  }else{
	    		  pstmt2.setString(1, ntpos.getNTstaffUuid());
			      pstmt2.executeUpdate();
	    	  }
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting Position : " +pos);
	      	   logger.error("SQL Exception when deletting NTPosition : " +ntpos);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}
	

	/**
	 * @see com.yahoo.petermwenda83.model.principal.SchoolStaffRegistrationDAO#getTeacher()
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
         System.out.println(ExceptionUtils.getStackTrace(e));
     }
   
		
		return list;
	}

	@Override
	public List<Position> getAllStaffPos() {
		List<Position>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Position ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, Position.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all Position");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	}

	
}
