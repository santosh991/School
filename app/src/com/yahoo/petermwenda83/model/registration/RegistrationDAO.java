/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author peter
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
	
	public Student getStudent(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudents(java.lang.String)
	 */
	@Override
	public Student getStudents(String admno) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudents(com.yahoo.petermwenda83.contoller.student.StudentSuper)
	 */
	@Override
	public boolean getStudents(Student studentSuper) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	@Override
	public boolean putStudent(Student studentSuper, Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.House)
	 */
	@Override
	public boolean putStudent(Student studentSuper, House house) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Location)
	 */
	@Override
	public boolean putStudent(Student studentSuper, Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean putStudent(Student studentSuper, StudentSubject stusubject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	@Override
	public boolean editStudent(Student studentSuper, Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.House)
	 */
	@Override
	public boolean editStudent(Student studentSuper, House house) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Location)
	 */
	@Override
	public boolean editStudent(Student studentSuper, Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean editStudent(Student studentSuper, StudentSubject stusubject) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Activity)
	 */
	@Override
	public boolean deleteStudent(Student studentSuper, Activity activity) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.House)
	 */
	@Override
	public boolean deleteStudent(Student studentSuper, House house) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.Location)
	 */
	@Override
	public boolean deleteStudent(Student studentSuper, Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSuper, com.yahoo.petermwenda83.contoller.student.StudentSubject)
	 */
	@Override
	public boolean deleteStudent(Student studentSuper, StudentSubject stusubject) {
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
  	logger.error("SQL Exception when getting all StudentSuper");
     logger.error(ExceptionUtils.getStackTrace(e));
  }

	
	return list;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getAllHouse()
	 */
	@Override
	public List<House> getAllHouse() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getAllLocation()
	 */
	@Override
	public List<Location> getAllLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
