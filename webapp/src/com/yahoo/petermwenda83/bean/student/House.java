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
package com.yahoo.petermwenda83.bean.student;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class House extends StudentSuper  {
	
	private String housename;
	/**
	 * 
	 */
	public House() {
		super();
		housename = "";
	}
	
	
	/**
	 * @return the housename
	 */
	public String getHousename() {
		return housename;
	}


	/**
	 * @param housename the housename to set
	 */
	public void setHousename(String housename) {
		this.housename = housename;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("House");
		builder.append("getUuid()");
		builder.append(getUuid());
		builder.append(", getStudentUuid() =");
		builder.append(getStudentUuid());
		builder.append(", housename =");
		builder.append(housename);
		builder.append("]");
		return builder.toString(); 
		}


}
