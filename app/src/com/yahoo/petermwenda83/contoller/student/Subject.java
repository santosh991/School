/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;



/**
 * @author peter
 *
 */
public class Subject extends StudentSuper{
	
	private String subjectname;
	private String subjectcategory;
	
	
    public Subject(){
    	super();
    	
    	subjectname = "";
    	subjectcategory = "";
    	
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
		builder.append("getSubjectcode()=");
		builder.append(getSubjectcode());
		builder.append(", subjectname =");
		builder.append(subjectname);
		builder.append("subjectcategory=");
		builder.append(subjectcategory);
		builder.append("]");
		return builder.toString(); 
		}
}

