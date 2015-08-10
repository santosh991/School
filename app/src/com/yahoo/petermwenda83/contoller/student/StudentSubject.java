/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;



/**
 * @author peter
 *
 */
public class StudentSubject extends StudentSuper{
	   

	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("StudentSubject [ getUuid() =");
	builder.append(getUuid());
	builder.append(", getStudentUuid() =");
	builder.append(getStudentUuid());
	builder.append(", getSubjectUuid() =");
	builder.append(getSubjectUuid());
	builder.append(", getSubjectcode() =");
	builder.append(getSubjectcode());
	builder.append(", getClasz() =");
	builder.append(getClasz());
	
	builder.append("]");
	return builder.toString(); 
	}
}
