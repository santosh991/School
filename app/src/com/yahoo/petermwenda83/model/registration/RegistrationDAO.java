/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.contoller.student.Activity;
import com.yahoo.petermwenda83.contoller.student.House;
import com.yahoo.petermwenda83.contoller.student.Location;
import com.yahoo.petermwenda83.contoller.student.Student;
import com.yahoo.petermwenda83.contoller.student.StudentSubject;
import com.yahoo.petermwenda83.model.DBConnectDAO;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class RegistrationDAO extends DBConnectDAO implements StudentRegistrationDAO {
     

	private static RegistrationDAO registrationDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static RegistrationDAO getInstance(){
		
		if(registrationDAO == null){
			registrationDAO = new RegistrationDAO();		
		}
		return registrationDAO;
	}
	
	/**
	 * 
	 */
	public RegistrationDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public RegistrationDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudent(java.lang.String)
	 */
	
	public Student getStudent(String Uuid) {
		Student student = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 student  = beanProcessor.toBean(rset,Student.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with uuid: " + Uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return student; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudents(java.lang.String)
	 */
	
	public Student getStudents(String admno) {
		Student student = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student WHERE admno = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, admno);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 student  = beanProcessor.toBean(rset,Student.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting an users with admno: " + admno);
             logger.error(ExceptionUtils.getStackTrace(e));
        }
        
		return student; 
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudents(com.yahoo.petermwenda83.contoller.student.StudentSuper)
	 */
	@Override
	public boolean getStudents(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	
	public boolean putStudent(Activity activity) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Activities" 
			        		+"(Uuid, StudentUuid, Activity) VALUES (?,?,?);");
      		){
			   
	            pstmt.setString(1, activity.getUuid());
	            pstmt.setString(2, activity.getStudentUuid());
	            pstmt.setString(3, activity.getActivity());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+activity);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.House)
	 */
	
	public boolean putStudent(House house) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_House" 
			        		+"(Uuid, StudentUuid, Housename) VALUES (?,?,?);");
    		){
			   
	            pstmt.setString(1, house.getUuid());
	            pstmt.setString(2, house.getStudentUuid());
	            pstmt.setString(3, house.getHousename());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+house);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Location)
	 */
	
	public boolean putStudent(Location location) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Location" 
			        		+"(Uuid, StudentUuid, County,Location,Sublocation) VALUES (?,?,?,?,?);");
  		){
			   
	            pstmt.setString(1, location.getUuid());
	            pstmt.setString(2, location.getStudentUuid());
	            pstmt.setString(3, location.getCounty());
	            pstmt.setString(4, location.getLocation());
	            pstmt.setString(5, location.getSublocation());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+location);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	
	public boolean putStudent(StudentSubject stusubject) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student_Subject" 
			        		+"(Uuid, StudentUuid, SubjectUuid,Subjectcode,Clasz) VALUES (?,?,?,?,?);");
  		){
			   
	            pstmt.setString(1, stusubject.getUuid());
	            pstmt.setString(2, stusubject.getStudentUuid());
	            pstmt.setString(3, stusubject.getSubjectUuid());
	            pstmt.setString(4, stusubject.getSubjectcode());
	            pstmt.setString(5, stusubject.getClasz());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+stusubject);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       success = false;
		 }
		 
		
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	
	public boolean editStudent(Activity activity) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
      	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Activities SET getActivity=?, "
      			+ "WHERE StudentUuid = ?;");
      	) {
	           
	            pstmt.setString(1, activity.getActivity());
	            pstmt.setString(2, activity.getStudentUuid());
	           
	            pstmt.executeUpdate();

      } catch (SQLException e) {
          logger.error("SQL Exception when updating editStudent " + activity);
          logger.error(ExceptionUtils.getStackTrace(e));
          success = false;
      } 
      		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.House)
	 */
	
	public boolean editStudent(House house) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_House SET Housename=?, "
    			+ "WHERE StudentUuid = ?;");
    	) {           			 	            
	            pstmt.setString(1, house.getHousename());
	            pstmt.setString(2, house.getStudentUuid());
	           
	            pstmt.executeUpdate();

    } catch (SQLException e) {
        logger.error("SQL Exception when updating editStudent " + house);
        logger.error(ExceptionUtils.getStackTrace(e));
        success = false;
    } 
    		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Location)
	 */
	
	public boolean editStudent(Location location) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Location SET County=?, "
    			+ " Location =?, Sublocation =? WHERE StudentUuid = ?;");
    	) {
	           
	            pstmt.setString(1, location.getCounty());
	            pstmt.setString(2, location.getLocation());
	            pstmt.setString(3, location.getSublocation());
	            pstmt.setString(4, location.getStudentUuid());
	           
	            pstmt.executeUpdate();

    } catch (SQLException e) {
        logger.error("SQL Exception when updating editStudent " + location);
        logger.error(ExceptionUtils.getStackTrace(e));
        success = false;
    } 
    		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	
	public boolean editStudent(StudentSubject stusubject) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
    	PreparedStatement pstmt = conn.prepareStatement("UPDATE Student_Subject SET SubjectUuid=?, "
    			+ "Subjectcode =?,Clasz =? WHERE StudentUuid = ?;");
    	) {
	           
	            pstmt.setString(1, stusubject.getSubjectUuid());
	            pstmt.setString(2, stusubject.getSubjectcode());
	            pstmt.setString(3, stusubject.getClasz());
	            pstmt.setString(4, stusubject.getStudentUuid());
	           
	           
	            pstmt.executeUpdate();

    } catch (SQLException e) {
        logger.error("SQL Exception when updating editStudent " + stusubject);
        logger.error(ExceptionUtils.getStackTrace(e));
        success = false;
    } 
    		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	@Override
	public boolean deleteStudent(Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.House)
	 */
	@Override
	public boolean deleteStudent(House house) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Location)
	 */
	@Override
	public boolean deleteStudent(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean deleteStudent(StudentSubject stusubject) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getAllStudents()
	 */
	public List<Student> getAllStudents() {
	List<Student> list = null;

	 try(   
  		Connection conn = dbutils.getConnection();
  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student;");   
  		ResultSet rset = pstmt.executeQuery();
		) {
  	
      list = beanProcessor.toBeanList(rset, Student.class);

  } catch(SQLException e){
  	logger.error("SQL Exception when getting all Student");
     logger.error(ExceptionUtils.getStackTrace(e));
     System.out.println(ExceptionUtils.getStackTrace(e)); 
  }

	
	return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getAllHouse()
	 */
	
	public List<House> getAllHouse() {
		List<House> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_House;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, House.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all House");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getAllLocation()
	 */
	
	public List<Location> getAllLocation() {
		List<Location> list = null;

		 try(   
	  		Connection conn = dbutils.getConnection();
	  		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Student_Location;");   
	  		ResultSet rset = pstmt.executeQuery();
			) {
	  	
	      list = beanProcessor.toBeanList(rset, Location.class);

	  } catch(SQLException e){
	  	logger.error("SQL Exception when getting all Location");
	     logger.error(ExceptionUtils.getStackTrace(e));
	     System.out.println(ExceptionUtils.getStackTrace(e)); 
	  }

		
		return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudent(com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	public boolean getStudent(Activity activity) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student_Activities" 
			        		+"WHERE Activity = ? AND StudentUuid =? ");
    		){
	            pstmt.setString(1, activity.getActivity());
	            pstmt.setString(2, activity.getStudentUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+activity);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         success = false;
		 }
		 
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudent(com.yahoo.petermwenda83.contoller.student.House)
	 */
	public boolean getStudent(House house) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM house" 
			        		+"WHERE Housename = ? AND StudentUuid =? ");
  		){
	            pstmt.setString(1, house.getHousename());
	            pstmt.setString(2, house.getStudentUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+house);
       logger.error(ExceptionUtils.getStackTrace(e)); 
       success = false;
		 }
		 
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudent(com.yahoo.petermwenda83.contoller.student.Location)
	 */
	public boolean getStudent(Location location) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM location" 
			        		+"WHERE StudentUuid =? ");
		){
	            pstmt.setString(1, location.getStudentUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+location);
     logger.error(ExceptionUtils.getStackTrace(e)); 
     success = false;
		 }
		 
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	public boolean getStudent(StudentSubject stusubject) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM stusubject" 
			        		+"WHERE Subjectcode = ? AND StudentUuid =? ");
		){
	            pstmt.setString(1, stusubject.getSubjectcode());
	            pstmt.setString(2, stusubject.getStudentUuid());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to putStudent: "+stusubject);
     logger.error(ExceptionUtils.getStackTrace(e)); 
     success = false;
		 }
		 
		
		
		return success;
	}

	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudents(com.yahoo.petermwenda83.contoller.student.Student)
	 */
	public boolean putStudents(Student student) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student" 
			        		+"(Uuid, Firstname, Lastname,Surname,Admno,Year,DOB,Bcertno,Admissiondate)"
			        		+ " VALUES (?,?,?,?,?,?,?,?,?);");
		){
			   
	            pstmt.setString(1, student.getUuid());
	            pstmt.setString(2, student.getFirstname());
	            pstmt.setString(3, student.getLastname());
	            pstmt.setString(4, student.getSurname());
	            pstmt.setString(5, student.getAdmno());
	            pstmt.setString(6, student.getYear());
	            pstmt.setString(7, student.getDOB());
	            pstmt.setString(8, student.getBcertno());
	            pstmt.setTimestamp(9, new Timestamp(student.getAdmissiondate().getTime()));
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Student: "+student);
     logger.error(ExceptionUtils.getStackTrace(e)); 
     success = false;
		 }
		 
		
		
		return success;
	}

	
	
	
	/**
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudents(com.yahoo.petermwenda83.contoller.student.Student)
	 */
	public boolean editStudents(Student student) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Student  SET" 
			        +"Firstnamen =?, Lastname  =?,Surname  =?,Year =?,DOB  =?,"
			        + "Bcertno  =? WHERE Admno = ?; ");
		){
			  
	            pstmt.setString(1, student.getFirstname());
	            pstmt.setString(2, student.getLastname());
	            pstmt.setString(3, student.getSurname());	           
	            pstmt.setString(4, student.getYear());
	            pstmt.setString(5, student.getDOB());
	            pstmt.setString(6, student.getBcertno());
	            pstmt.setString(7, student.getAdmno());
	           
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put Student: "+student);
   logger.error(ExceptionUtils.getStackTrace(e)); 
   success = false;
		 }
		 
		
		
		return success;
	}

}
