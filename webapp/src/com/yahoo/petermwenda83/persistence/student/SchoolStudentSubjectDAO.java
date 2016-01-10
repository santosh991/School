

package com.yahoo.petermwenda83.persistence.student;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.StudentSubClassRoom;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolStudentSubjectDAO {
	  
		public StudentSubClassRoom getSubRoom(String studentuuid);
		
		public boolean putSubRoom(StudentSubClassRoom SubRoom);
		
		public boolean  updateSubRoom(StudentSubClassRoom SubRoom);
		
		public boolean deleteSubRoom(StudentSubClassRoom SubRoom);
		
		public List<StudentSubClassRoom> getSubRoomList();
		

}
