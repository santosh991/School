
/*************************************************************
 * Online School Management System                           *
 * Forth Year Project                                        *
 * Maasai Mara University                                    *
 * Bachelor of Science(Computer Science)                     *
 * Year:2015-2016                                            *
 * Name: Njeru Mwenda Peter                                  *
 * ADM NO : BS02/009/2012                                    *
 *                                                           *
 *************************************************************/
package com.yahoo.petermwenda83.bean.exam;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * An exam in a school
 * 
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Exam extends StorableBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2625893752188549331L;
	private String examName;
	private String schoolAccountUuid;
	
	
	
	/**
	 * 
	 */
	public Exam() {
		super();
		examName ="";
		schoolAccountUuid ="";
	}
	
	/**
	 * @return the examName
	 */
	public String getExamName() {
		return examName;
	}

	/**
	 * @param examName the examName to set
	 */
	public void setExamName(String examName) {
		this.examName = examName;
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

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exam [ getUuid() = ,");
		builder.append(getUuid());
		builder.append(", schoolAccountUuid=");
		builder.append(schoolAccountUuid);
		builder.append(", examName=");
		builder.append(examName);
		builder.append("]");
		return builder.toString(); 
		}
	
}
