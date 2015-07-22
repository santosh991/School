/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;

import com.yahoo.petermwenda83.contoller.AllBean;


/**
 * @author peter
 *
 */
public class StudentSubject extends AllBean{
	    private String subjectUuid;
	    private String studentUuid;
	   
	    
	/**
	 * 
	 */
	public StudentSubject() {
		super();
		subjectUuid = "";
    	studentUuid = "";
	
	}


	/**
	 * @return the subjectUuid
	 */
	public String getSubjectUuid() {
		return subjectUuid;
	}


	/**
	 * @param subjectUuid the subjectUuid to set
	 */
	public void setSubjectUuid(String subjectUuid) {
		this.subjectUuid = subjectUuid;
	}


	/**
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return studentUuid;
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
	}


	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("StudentSubject [ getUuid() =");
	builder.append(getUuid());
	builder.append(", subjectUuid =");
	builder.append(subjectUuid);
	builder.append(", studentUuid =");
	builder.append(studentUuid);
	builder.append("]");
	return builder.toString(); 
	}
}
