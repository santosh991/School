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
package com.yahoo.petermwenda83.contoller.student;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Primary extends StudentSuper {
	
	private String schoolname;
	private String index;
	private String kcpeyear;
	private String kcpemarks;
	
	/**
	 * 
	 */
	public Primary() {
		super();
		schoolname ="";
		index ="";
		kcpeyear ="";
		kcpemarks ="";
	}
	
	/**
	 * @return the schoolname
	 */
	public String getSchoolname() {
		return schoolname;
	}

	/**
	 * @param schoolname the schoolname to set
	 */
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the kcpeyear
	 */
	public String getKcpeyear() {
		return kcpeyear;
	}

	/**
	 * @param kcpeyear the kcpeyear to set
	 */
	public void setKcpeyear(String kcpeyear) {
		this.kcpeyear = kcpeyear;
	}

	/**
	 * @return the kcpemarks
	 */
	public String getKcpemarks() {
		return kcpemarks;
	}

	/**
	 * @param kcpemarks the kcpemarks to set
	 */
	public void setKcpemarks(String kcpemarks) {
		this.kcpemarks = kcpemarks;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(" Primary");
		builder.append("[ id ="); 
		builder.append(getId()); 
		builder.append(", getStudentUuid() =");
		builder.append(getStudentUuid());
		builder.append(",schoolname =");
		builder.append(schoolname);
		builder.append(",index =");
		builder.append(index);
		builder.append(",kcpeyear =");
		builder.append(kcpeyear);
		builder.append(",kcpemarks =");
		builder.append(kcpemarks);
		builder.append("]");
		return builder.toString(); 
		}
}
