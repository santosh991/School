/**##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without approval of from
 * ###### owner.#############################################
 * ##########################################################
 * ##### SchoolAccount Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.student.StudentSubClassRoom;
import com.yahoo.petermwenda83.persistence.GenericDAO;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class StudentSubjectDAO extends GenericDAO implements SchoolStudentSubjectDAO {
	
	private static StudentSubjectDAO studentSubjectDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentSubjectDAO getInstance(){
		
		if(studentSubjectDAO == null){
			studentSubjectDAO = new StudentSubjectDAO();		
		}
		return studentSubjectDAO;
	}
	
	/**
	 * 
	 */
	public StudentSubjectDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public StudentSubjectDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentSubjectDAO#getStudent(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom)
	 */
	/*@Override
	public StudentSubClassRoom getStudentSubject(String StudentUuid) {
		StudentSubClassRoom stusubject = null;
		ResultSet rset = null;
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM studentsubject "
						+ "WHERE StudentUuid =?;");
		){
	            pstmt.setString(1, StudentUuid);
	            rset = pstmt.executeQuery();
	                while(rset.next()){
	                	stusubject  = beanProcessor.toBean(rset,StudentSubClassRoom.class);
			         }
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+stusubject);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
            
		 }		
		return stusubject;
	}
	
	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentSubjectDAO#putStudent(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom)
	 *//*
	@Override
	public boolean putStudentSubject(StudentSubClassRoom stusubject) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO studentsubject" 
			        		+"(Uuid, StudentUuid, SubjectUuid,ClassRoomUuid) VALUES (?,?,?,?);");
		){
			   
	            pstmt.setString(1, stusubject.getUuid());
	            pstmt.setString(2, stusubject.getStudentUuid());
	            pstmt.setString(3, stusubject.getSubjectUuid());
	            pstmt.setString(4, stusubject.getClassRoomUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+stusubject);
             logger.error(ExceptionUtils.getStackTrace(e)); 
              System.out.println(ExceptionUtils.getStackTrace(e));
              success = false;
		 }
		 
		
		
		return success;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentSubjectDAO#editStudent(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom, java.lang.String)
	 *//*
	@Override
	public boolean editStudentSubject(StudentSubClassRoom stusubject) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
  	PreparedStatement pstmt = conn.prepareStatement("UPDATE studentsubject SET ClassRoomUuid =? "
  			+ "WHERE SubjectUuid=? AND StudentUuid = ?;");
  	) {
	           
	           
	            pstmt.setString(1, stusubject.getClassRoomUuid());
	            pstmt.setString(2, stusubject.getSubjectUuid());
	            pstmt.setString(3, stusubject.getStudentUuid());
	            pstmt.executeUpdate();

  } catch (SQLException e) {
      logger.error("SQL Exception when updating editStudent " + stusubject);
      logger.error(ExceptionUtils.getStackTrace(e));
      System.out.println(ExceptionUtils.getStackTrace(e));
      success = false;
  } 
  		
		return success;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentSubjectDAO#deleteStudentSubject(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom)
	 *//*
	@Override
	public boolean deleteStudentSubject(StudentSubClassRoom stusubject) {
		  boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM studentsubject"
	         	      		+ " WHERE StudentUuid = ? AND SubjectUuid =?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, stusubject.getStudentUuid());
	      	 pstmt.setString(2, stusubject.getSubjectUuid()); 
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting pastusubjectrent : " +stusubject);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	*//**
	 * @see com.yahoo.petermwenda83.persistence.student.SchoolStudentSubjectDAO#getAllStudentSubject()
	 *//*
	@Override
	public List<StudentSubClassRoom> getAllStudentSubject() {
		List<StudentSubClassRoom>  list = null;
		
		 try(   
    		Connection conn = dbutils.getConnection();
    		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM studentsubject ;");   
    		ResultSet rset = pstmt.executeQuery();
		) {
    	
        list = beanProcessor.toBeanList(rset, StudentSubClassRoom.class);

    } catch(SQLException e){
    	logger.error("SQL Exception when getting all StudentParent");
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
    }
  
		
		return list;
	}
*/
	@Override
	public StudentSubClassRoom getSubRoom(String studentuuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putSubRoom(StudentSubClassRoom SubRoom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSubRoom(StudentSubClassRoom SubRoom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSubRoom(StudentSubClassRoom SubRoom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StudentSubClassRoom> getSubRoomList() {
		List<StudentSubClassRoom>  list = null;
		
		 try(   
   		Connection conn = dbutils.getConnection();
   		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM StudentSubClassRoom ;");   
   		ResultSet rset = pstmt.executeQuery();
		) {
   	
       list = beanProcessor.toBeanList(rset, StudentSubClassRoom.class);

   } catch(SQLException e){
   	logger.error("SQL Exception when getting all StudentParent");
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
   }
 
		
		return list;
	}

}
