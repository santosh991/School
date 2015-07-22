/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import com.yahoo.petermwenda83.contoller.student.Activity;
import com.yahoo.petermwenda83.contoller.student.House;
import com.yahoo.petermwenda83.contoller.student.Location;
import com.yahoo.petermwenda83.contoller.student.Student;
import com.yahoo.petermwenda83.contoller.student.StudentSubject;

/**
 * @author peter
 *
 */
public class RegistrationDAO implements StudentRegistrationDAO {

	/**
	 * 
	 */
	public RegistrationDAO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#getStudent(java.lang.String)
	 */
	@Override
	public Student getStudent(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Activity, com.yahoo.petermwenda83.contoller.student.House, com.yahoo.petermwenda83.contoller.student.Location, java.lang.String)
	 */
	@Override
	public boolean putStudent(Student student, Activity activity, House house,
			Location location, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Activity, com.yahoo.petermwenda83.contoller.student.House, com.yahoo.petermwenda83.contoller.student.Location, java.lang.String)
	 */
	@Override
	public boolean editStudent(Student student, Activity activity, House house,
			Location location, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Activity, com.yahoo.petermwenda83.contoller.student.House, com.yahoo.petermwenda83.contoller.student.Location, java.lang.String)
	 */
	@Override
	public Student deleteStudent(Student student, Activity activity,
			House house, Location location, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.registration.StudentRegistrationDAO#assginSubject(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.subject.Subject)
	 */
	@Override
	public Student assginSubject(Student student, StudentSubject subjet) {
		// TODO Auto-generated method stub
		return null;
	}

}
