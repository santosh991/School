/**##########################################################
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
package com.yahoo.petermwenda83.bean.exam;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ExamDetail extends AllBean{

	private String SchoolAccountUuid;
	private String ClassRoomUuid;
	private String SubjectUuid;
	private String Term;
	private String Year;
	private int OutOf;
	
	/**
	 * 
	 */
	public ExamDetail() {
        super();
      	SchoolAccountUuid ="";
      	ClassRoomUuid ="";
      	SubjectUuid ="";
        Term ="";
      	Year ="";
      	OutOf =0;
	}
	
	
	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return SchoolAccountUuid;
	}


	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		SchoolAccountUuid = schoolAccountUuid;
	}


	/**
	 * @return the examUuid
	 */
	public String getExamUuid() {
		return getExamUuid();
	}


	/**
	 * @param examUuid the examUuid to set
	 */
	public void setExamUuid(String examUuid) {
		setExamUuid(examUuid);
	}


	/**
	 * @return the classRoomUuid
	 */
	public String getClassRoomUuid() {
		return ClassRoomUuid;
	}


	/**
	 * @param classRoomUuid the classRoomUuid to set
	 */
	public void setClassRoomUuid(String classRoomUuid) {
		ClassRoomUuid = classRoomUuid;
	}


	/**
	 * @return the subjectUuid
	 */
	public String getSubjectUuid() {
		return SubjectUuid;
	}


	/**
	 * @param subjectUuid the subjectUuid to set
	 */
	public void setSubjectUuid(String subjectUuid) {
		SubjectUuid = subjectUuid;
	}


	/**
	 * @return the term
	 */
	public String getTerm() {
		return Term;
	}


	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		Term = term;
	}


	/**
	 * @return the year
	 */
	public String getYear() {
		return Year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		Year = year;
	}


	/**
	 * @return the outOf
	 */
	public int getOutOf() {
		return OutOf;
	}


	/**
	 * @param outOf the outOf to set
	 */
	public void setOutOf(int outOf) {
		OutOf = outOf;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Employee Detail [ getUuid() =");
		builder.append(getUuid());
		builder.append(", SchoolAccountUuid =");
		builder.append(SchoolAccountUuid);
		builder.append(",getExamUuid()=");
		builder.append(getExamUuid());
		builder.append(", ClassRoomUuid =");
		builder.append(ClassRoomUuid);
		builder.append(", SubjectUuid =");
		builder.append(SubjectUuid);
		builder.append(", Term =");
		builder.append(Term);
		builder.append(", Year =");
		builder.append(Year);
		builder.append(", OutOf =");
		builder.append(OutOf);
		builder.append("]");
		return builder.toString(); 
		}
	

}
