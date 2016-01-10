/**
 * 
 */
package com.yahoo.petermwenda83.bean.exam;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class Analyzer  extends StorableBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8127564628546134341L;
	
	private String studenId;
	private double eng;
	private double kis;
	private double phy;
	private double total;
	

	/**
	 * 
	 */
	public Analyzer() {
		studenId = "";
		eng = 0.0;
		kis = 0.0;
		phy = 0.0;
		total = 0.0;
	}
	
	/**
	 * @return the studenId
	 */
	public String getStudenId() {
		return studenId;
	}

	/**
	 * @param studenId the studenId to set
	 */
	public void setStudenId(String studenId) {
		this.studenId = studenId;
	}

	/**
	 * @return the eng
	 */
	public double getEng() {
		return eng;
	}

	/**
	 * @param eng the eng to set
	 */
	public void setEng(double eng) {
		this.eng = eng;
	}

	/**
	 * @return the kis
	 */
	public double getKis() {
		return kis;
	}

	/**
	 * @param kis the kis to set
	 */
	public void setKis(double kis) {
		this.kis = kis;
	}

	/**
	 * @return the phy
	 */
	public double getPhy() {
		return phy;
	}

	/**
	 * @param phy the phy to set
	 */
	public void setPhy(double phy) {
		this.phy = phy;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return getEng()+getKis()+getPhy();
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("anayazer [=,");
		builder.append(studenId);
		builder.append(", studenId="); 
		builder.append(eng);
		builder.append(", eng="); 
		builder.append(kis);
		builder.append(", kis="); 
		builder.append(phy);
		builder.append(", phy="); 
		builder.append(total);
		builder.append(", total="); 
		
		builder.append("]");
		return builder.toString();
	}
}
