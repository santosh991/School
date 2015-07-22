/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;

import com.yahoo.petermwenda83.contoller.AllBean;


/**
 * @author peter
 *
 */
public class Subject extends AllBean{
	private String subjectcode;
	private String subjectname;
	private String subjectcategory;
	
    public Subject(){
    	super();
    	subjectcode = "";
    	subjectname = "";
    	subjectcategory = "";
	}

	/**
	 * @return the subjectcode
	 */
	public String getSubjectcode() {
		return subjectcode;
	}

	/**
	 * @param subjectcode the subjectcode to set
	 */
	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	/**
	 * @return the subjectname
	 */
	public String getSubjectname() {
		return subjectname;
	}

	/**
	 * @param subjectname the subjectname to set
	 */
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	/**
	 * @return the subjectcategory
	 */
	public String getSubjectcategory() {
		return subjectcategory;
	}

	/**
	 * @param sujectcategory the subjectcategory to set
	 */
	public void setSubjectcategory(String subjectcategory) {
		this.subjectcategory = subjectcategory;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("SubjectUi [ getUuid() =");
		builder.append(getUuid());
		builder.append("subjectcode=");
		builder.append(subjectcode);
		builder.append(", subjectname =");
		builder.append(subjectname);
		builder.append("subjectcategory=");
		builder.append(subjectcategory);
		builder.append("]");
		return builder.toString(); 
		}
}

