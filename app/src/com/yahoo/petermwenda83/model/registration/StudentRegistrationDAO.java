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
public interface StudentRegistrationDAO {
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Student getStudent(String uuid);
	/**
	 * 
	 * @param student
	 * @param uuid
	 * @return
	 */
	public boolean putStudent(Student student,Activity activity,House house,Location location, String uuid);
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean editStudent(Student student,Activity activity,House house,Location location, String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Student deleteStudent(Student student,Activity activity,House house,Location location, String uuid);
	/**
	 * 
	 * @param student
	 * @param subjet
	 * @return
	 */
	public Student assginSubject(Student student,StudentSubject subjet);
	

}
