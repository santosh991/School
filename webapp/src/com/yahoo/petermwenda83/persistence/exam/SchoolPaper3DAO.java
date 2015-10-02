package com.yahoo.petermwenda83.persistence.exam;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

public interface SchoolPaper3DAO {

	/**
	 * 
	 * @param perfomance
	 * @param Paper3
	 * @return
	 */
	public boolean hasPaper3(Perfomance perfomance);
	
	
	/**
	 * 
	 * @param perfomance
	 * @param Paper3
	 * @return
	 */
	 
	public boolean putPaper3(Perfomance perfomance,double Paper3);
	 
}
