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
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface StudentRegistrationDAO {
	/**
	 * 
	 * @param uuid
	 * @return Student
	 */
	public Student getStudent(String uuid);
	/**
	 * 
	 * @param admno
	 * @return Student
	 */
	public Student getStudents(String admno);
	/**
	 * 
	 * @param studentSuper
	 * @return Student
	 */
	
	public boolean getStudents(Student student);
	 
	
	 /**
	  * 
	  * @param activity
	  * @return 
	  */
	public boolean getStudent(Activity activity);
	
	   /**
	    * 
	    * @param house
	    * @return whether House was got successfully
	    */
	public boolean getStudent(House house);
	
	/**
	   * 
	   * @param location
	   * @return whether Location was got successfully
	   */
	public boolean getStudent(Location location);
      
	/**
	 * 
	 * @param stusubject
	 * @return whether StudentSubject was got successfully
	 */
	public boolean getStudent(StudentSubject stusubject);
      
	/**
	  * 
	  * @param student
	  * @return
	  */
	public boolean putStudents(Student student);
	
	  /**
	   * 
	   * @param studentSuper
	   * @param location
	   * 
	   */
	
	public boolean putStudent(Location location);
	   
	/**
	 * 
	 * @param studentSuper
	 * @param house 
	 * 
	 */
	public boolean putStudent(House house);
	
	/**
	 * 
	 * @param studentSuper
	 * @param uuid
	 * 
	 */
	public boolean putStudent(Activity activity);
	
	
	/**
	 * 
	 * @param studentSuper
	 * @param stusubject
	 * 
	 */
	public boolean putStudent(StudentSubject stusubject);
	
	/**
	 * 
	 * @param studentSuper
	 * 
	 */
	public boolean editStudent(Activity activity);
	 /**
	  * 
	  * @param studentSuper
	  * @param house
	  * 
	  */
	public boolean editStudent(House house);
	   /**
	    * 
	    * @param studentSuper
	    * @param location
	    * 
	    */
	public boolean editStudent(Location location);
	
	/**
	 * 
	 * @param studentSuper
	 * @param stusubject
	 * 
	 */
	public boolean  editStudent(StudentSubject stusubject);
	
	
	/**
	 * 
	 * @param student
	 * @return whether edit was successful or not
	 */
	public boolean editStudents(Student student);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteStudent(Activity activity);
	  /**
	   * 
	   * @param studentSuper
	   * @param house
	   * 
	   */
	public boolean deleteStudent(House house);
	   /**
	    * 
	    * @param studentSuper
	    * @param location
	    * 
	    */
	public boolean deleteStudent(Location location);
	
	/**
	 * 
	 * @param studentSuper
	 * @param stusubject
	 * 
	 */
	public boolean deleteStudent(StudentSubject stusubject);
	   /**
	    * @return AllStudents
	    */
	public List<Student> getAllStudents();
	    /**
	     * 
	     * @return AllHouses
	     */
	public List<House> getAllHouse();
	/**
	 * 
	 * @return all student location
	 */
	public List<Location> getAllLocation();
	

	
	

}
