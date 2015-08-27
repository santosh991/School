/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;



/**
 * @author peter
 *
 */
public class StudentSubject extends StudentSuper{
	   public StudentSubject(){
		   super();
	   }

	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("StudentSubject [ getUuid() =");
	builder.append(getUuid());
	builder.append(", getStudentUuid() =");
	builder.append(getStudentUuid());
	builder.append(", getSubjectUuid() =");
	builder.append(getSubjectUuid());
	builder.append(", getClasz() =");
	builder.append(getClasz());
	
	builder.append("]");
	return builder.toString(); 
	}
}
