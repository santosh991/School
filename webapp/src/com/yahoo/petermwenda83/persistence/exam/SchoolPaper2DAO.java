package com.yahoo.petermwenda83.persistence.exam;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

public interface SchoolPaper2DAO {

	/**
	 * 
	 * @param perfomance
	 * @param Paper2
	 * @return
	 */
	public boolean hasPaper2(Perfomance perfomance);
	
	
	/**
	 * 
	 * @param perfomance
	 * @param Paper2
	 * @return
	 */
	 
	public boolean putPaper2(Perfomance perfomance,double Paper2);
	

}
