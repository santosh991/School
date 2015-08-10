/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;



/**
 * @author peter
 *
 */
public class Student extends StudentSuper  {

	/**
	 * 
	 */
	public Student() {
		super();
	}
	
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Students [ getUuid() =");
		builder.append(getUuid());
		builder.append(",getFirstname() =");
		builder.append(getFirstname());
		builder.append(",getLastname() =");
		builder.append(getLastname());
		builder.append(",getSurname() =");
		builder.append(getSurname());
		builder.append(",getAdmno() =");
		builder.append(getAdmno());
		builder.append(",getForm() =");
		builder.append(getForm());
		builder.append(",getYear() =");
		builder.append(getYear());
		builder.append(",getDOB() =");
		builder.append(getDOB());
		builder.append(",getBcertno() =");
		builder.append(getBcertno()); 
		builder.append(",getAdmissiondate() =");
		builder.append(getAdmissiondate());
		builder.append("]");
		return builder.toString(); 
		}


}
