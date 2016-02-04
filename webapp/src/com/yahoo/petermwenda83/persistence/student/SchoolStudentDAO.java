

package com.yahoo.petermwenda83.persistence.student;
import java.util.List;

import com.yahoo.petermwenda83.bean.student.Student;



/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolStudentDAO {
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
	    * @param schoolaccount
	    * @param admno
	    * @return	a {@link List} of {@link Student}s whose admno partly or wholly
	    * matches the admno and belongs to a particular school account. Matching is case 
	    * insensitive. An empty list is returned if no Student matches the admno.
	    */
	  public List<Student> getStudentByAdmNo(String schoolaccountUuid, String admno);
	     
	/**
	  * 
	  * @param student
	  * @return
	  */
	public boolean putStudents(Student student);
	
	/**
	 * 
	 * @param student
	 * @return whether edit was successful or not
	 */
	public boolean updateStudents(Student student);
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean deleteStudents(Student student);
	
	 
	   /**
	    * @return AllStudents
	    */
	public List<Student> getAllStudents(String schoolaccountUuid,String classRoomUuid);
	
	
	
	public List<Student> getAllStudentList(String schoolaccountUuid);
	  
	
	

	
	

}
