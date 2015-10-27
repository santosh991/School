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
package com.yahoo.petermwenda83.persistence.student;
import java.util.List;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
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
	    * @param firstname
	    * @return	a {@link List} of {@link Student}s whose firstname partly or wholly
	    * matches the firstname and belongs to a particular school account. Matching is case 
	    * insensitive. An empty list is returned if no Student matches the firstname.
	    */
	  public List<Student> getStudentByName(SchoolAccount schoolaccount, String firstname);
	  
	  

	   /**
	    * 
	    * @param schoolaccount
	    * @param admno
	    * @return	a {@link List} of {@link Student}s whose admno partly or wholly
	    * matches the admno and belongs to a particular school account. Matching is case 
	    * insensitive. An empty list is returned if no Student matches the admno.
	    */
	  public List<Student> getStudentAdmNo(String schoolaccountUuid, String admno);
	     
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
	public boolean editStudents(Student student);
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean deleteStudents(Student student);
	
	 
	   /**
	    * @return AllStudents
	    */
	public List<Student> getAllStudents();
	  
	
	

	
	

}
