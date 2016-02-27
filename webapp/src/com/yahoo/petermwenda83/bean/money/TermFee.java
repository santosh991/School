/**
 * 
 */
package com.yahoo.petermwenda83.bean.money;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class TermFee extends StorableBean{
	
	 /** 
	 * 
	 */
	private static final long serialVersionUID = 3556481789932595293L;
	private String schoolAccountUuid;
	private double termAmount;
	private double nextTermAmount;

	/** 
	 * 
	 */
	public TermFee() {
		schoolAccountUuid = "";
		termAmount = 0.0;
		nextTermAmount = 0.0;

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
	 * @return the termAmount
	 */
	public double getTermAmount() {
		return termAmount;
	}



	/**
	 * @param termAmount the termAmount to set
	 */
	public void setTermAmount(double termAmount) {
		this.termAmount = termAmount;
	}

   

	/**
	 * @return the nextTermAmount
	 */
	public double getNextTermAmount() {
		return nextTermAmount;
	}



	/**
	 * @param nextTermAmount the nextTermAmount to set
	 */
	public void setNextTermAmount(double nextTermAmount) {
		this.nextTermAmount = nextTermAmount;
	}



	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Term Fee");
		builder.append(",[getUuid() = ");
		builder.append(getUuid());
		builder.append(", schoolAccountUuid =");
		builder.append(schoolAccountUuid); 
		builder.append(", termAmount =");
		builder.append(termAmount);
		builder.append(", nextTermAmount =");
		builder.append(nextTermAmount);
		builder.append("]");
		return builder.toString(); 
		}

}
