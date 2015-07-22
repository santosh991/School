package com.yahoo.petermwenda83.contoller.guardian;

import com.yahoo.petermwenda83.contoller.AllBean;

public class StudentSponser extends AllBean {
	private String studentUuid;
	private String sponsername;
	private int sponserphone;
	private int sponserID;
	
	private String sponseroccupation;
	private String county;
	private String country;
	public StudentSponser(){
		super();
		 sponsername = "";
		 sponserID = 0;
		 sponserphone = 0;
		 sponseroccupation = "";
		 county = "";
		 country = "";
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
	 * @return the sponsername
	 */
	public String getSponsername() {
		return sponsername;
	}



	/**
	 * @param sponsername the sponsername to set
	 */
	public void setSponsername(String sponsername) {
		this.sponsername = sponsername;
	}



	/**
	 * @return the sponserphone
	 */
	public int getSponserphone() {
		return sponserphone;
	}



	/**
	 * @param sponserphone the sponserphone to set
	 */
	public void setSponserphone(int sponserphone) {
		this.sponserphone = sponserphone;
	}



	/**
	 * @return the sponserID
	 */
	public int getSponserID() {
		return sponserID;
	}



	/**
	 * @param sponserID the sponserID to set
	 */
	public void setSponserID(int sponserID) {
		this.sponserID = sponserID;
	}



	/**
	 * @return the sponseroccupation
	 */
	public String getSponseroccupation() {
		return sponseroccupation;
	}



	/**
	 * @param sponseroccupation the sponseroccupation to set
	 */
	public void setSponseroccupation(String sponseroccupation) {
		this.sponseroccupation = sponseroccupation;
	}



	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}



	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}



	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}



	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}



	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentSponser");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", sponsername = ");
		builder.append("sponsername");
		builder.append(", sponserID =");
		builder.append("sponserID");
		builder.append(", sponserphone =");
		builder.append("sponserphone");
		builder.append(" , sponseroccupation =");
		builder.append("sponseroccupation");
		builder.append(", county =");
		builder.append("county");
		builder.append(", county =");
		builder.append("county");
		builder.append("]");
		return builder.toString(); 
		} 
		

	
}
