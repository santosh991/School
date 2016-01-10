/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.StudentExam;

/**
 * @author peter
 *
 */
public interface SchoolStudentExamDAO {
	
	public StudentExam getStudentExam(String schoolAccountUuid,String studentUuid);
	
	public boolean putStudentExam(StudentExam studentExam);
	
	public List<StudentExam> getStudentExamList(String schoolAccountUuid,String classRoomUuid);

}
