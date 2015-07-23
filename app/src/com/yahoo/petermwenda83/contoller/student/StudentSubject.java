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
	  
	   
	    
	/**
	 * 
	 */
	public StudentSubject() {
		super();
		subjectUuid = "";
    	
	
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
		return getStudentUuid();
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		setStudentUuid(studentUuid);
	}


	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("StudentSubject [ getUuid() =");
	builder.append(getUuid());
	builder.append(", subjectUuid =");
	builder.append(subjectUuid);
	builder.append(", getStudentUuid() =");
	builder.append(getStudentUuid());
	builder.append("]");
	return builder.toString(); 
	}
}
