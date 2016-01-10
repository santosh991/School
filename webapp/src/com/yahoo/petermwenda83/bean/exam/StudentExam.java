/**
 * 
 */
package com.yahoo.petermwenda83.bean.exam;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter   
 *
 */
public class StudentExam extends StorableBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String schoolAccountUuid;
	private String studentUuid;
	private String classRoomUuid;
	private String term;
	private String year;
	

	/**
	 * 
	 */
	public StudentExam() {
		 schoolAccountUuid ="";
		 studentUuid ="";
		 classRoomUuid ="";
		 term ="";
		 year ="";

	}
	

	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return schoolAccountUuid;
	}


	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		this.schoolAccountUuid = schoolAccountUuid;
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


	/**
	 * @return the classRoomUuid
	 */
	public String getClassRoomUuid() {
		return classRoomUuid;
	}


	/**
	 * @param classRoomUuid the classRoomUuid to set
	 */
	public void setClassRoomUuid(String classRoomUuid) {
		this.classRoomUuid = classRoomUuid;
	}


	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}


	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}


	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Performance, [");
		builder.append("schoolAccountUuid =");
		builder.append(schoolAccountUuid);
		builder.append(",studentUuid=");
		builder.append(studentUuid);
		builder.append(", classRoomUuid =");
		builder.append(classRoomUuid); 
		builder.append(", term =");
		builder.append(term); 
		builder.append(", year =");
		builder.append(year); 
		builder.append("]");
		return builder.toString(); 
		}

}
