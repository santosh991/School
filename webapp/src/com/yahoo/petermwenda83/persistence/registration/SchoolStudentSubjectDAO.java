/**##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without approval of from
 * ###### owner.#############################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.registration;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.StudentSubject;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolStudentSubjectDAO {
	  
		/**
		 * 
		 * @param stusubject
		 * @return whether StudentSubject was got successfully
		 */
		public StudentSubject getStudentSubject(String studentuuid);
		/**
		 * 
		 * @param studentSuper
		 * @param stusubject
		 * 
		 */
		public boolean putStudentSubject(StudentSubject stusubject);
		
		/**
		 * 
		 * @param studentSuper
		 * @param stusubject
		 * 
		 */
		public boolean  editStudentSubject(StudentSubject stusubject);
		/**
		 * 
		 * @param studentSuper
		 * @param stusubject
		 * 
		 */
		public boolean deleteStudentSubject(StudentSubject stusubject);
		
		   /**
		    * @return AllStudents
		    */
		public List<StudentSubject> getAllStudentSubject();
		

}
