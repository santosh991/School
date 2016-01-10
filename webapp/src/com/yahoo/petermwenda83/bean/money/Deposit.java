
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
package com.yahoo.petermwenda83.bean.money;

import java.util.Date;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class Deposit extends StorableBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7582945268897264721L;
	private String studentUuid;
	private Date depositedate;
	private double amountDeposited;

	/**
	 * 
	 */
	public Deposit() {
		super();
		studentUuid = "";
		depositedate = new Date();
		amountDeposited = 0.0;
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
	 * @return the depositedate
	 */
	public Date getDepositedate() {
		return depositedate;
	}



	/**
	 * @param depositedate the depositedate to set
	 */
	public void setDepositedate(Date depositedate) {
		this.depositedate = new Date(depositedate.getTime());
	}




	/**
	 * @return the amountDeposited
	 */
	public double getAmountDeposited() {
		return amountDeposited;
	}



	/**
	 * @param amountDeposited the amountDeposited to set
	 */
	public void setAmountDeposited(double amountDeposited) {
		this.amountDeposited = amountDeposited;
	}



	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(",Student's Pocket Money Deposite");
		builder.append("[getUuid() = ");
		builder.append(getUuid());
		builder.append(", studentUuid =");
		builder.append(studentUuid);
		builder.append(", depositedate =");
		builder.append(depositedate);
		builder.append(", amountDeposited =");
		builder.append(amountDeposited);
		builder.append("]");
		return builder.toString(); 
		}
}
