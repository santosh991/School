/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;
import java.util.List;

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
	 * @param admno
	 * @return
	 */
	public Student getStudents(String admno);
	/**
	 * 
	 * @param studentSuper
	 * @return
	 */
	
	public boolean getStudents(Student student);
	/**
	 * 
	 * @param studentSuper
	 * @param uuid
	 * @return
	 */
	public boolean putStudent(Student student,Activity activity);
	/**
	 * 
	 * @param studentSuper
	 * @param house
	 * @return
	 */
	public boolean putStudent(Student studentSuper,House house);
	  /**
	   * 
	   * @param studentSuper
	   * @param location
	   * @return
	   */
	
	public boolean putStudent(Student studentSuper,Location location);
	
	/**
	 * 
	 * @param studentSuper
	 * @param stusubject
	 * @return
	 */
	public boolean putStudent(Student studentSuper,StudentSubject stusubject);
	/**
	 * 
	 * @param studentSuper
	 * @return
	 */
	public boolean editStudent(Student studentSuper,Activity activity);
	 /**
	  * 
	  * @param studentSuper
	  * @param house
	  * @return
	  */
	public boolean editStudent(Student studentSuper,House house);
	   /**
	    * 
	    * @param studentSuper
	    * @param location
	    * @return
	    */
	public boolean editStudent(Student studentSuper,Location location);
	
	/**
	 * 
	 * @param studentSuper
	 * @param stusubject
	 * @return
	 */
	public boolean  editStudent(Student studentSuper,StudentSubject stusubject);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteStudent(Student studentSuper,Activity activity);
	  /**
	   * 
	   * @param studentSuper
	   * @param house
	   * @return
	   */
	public boolean deleteStudent(Student studentSuper,House house);
	   /**
	    * 
	    * @param studentSuper
	    * @param location
	    * @return
	    */
	public boolean deleteStudent(Student studentSuper,Location location);
	
	/**
	 * 
	 * @param studentSuper
	 * @param stusubject
	 * @return
	 */
	public boolean deleteStudent(Student studentSuper,StudentSubject stusubject);
	   /**
	    * @return
	    */
	public List<Student> getAllStudents();
	    /**
	     * 
	     * @return
	     */
	public List<House> getAllHouse();
	/**
	 * 
	 * @return
	 */
	public List<Location> getAllLocation();
	

}
