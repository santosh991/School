package com.yahoo.petermwenda83.persistence.exam;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

interface SchoolPaper1DAO {


	/**
	 * 
	 * @param perfomance
	 * @param Paper1
	 * @return
	 */
	public boolean hasPaper1(Perfomance perfomance);
	
	
	/**
	 * 
	 * @param perfomance
	 * @param Paper1
	 * @return
	 */
	 
	public boolean putPaper1(Perfomance perfomance,double Paper1);
	

	
}
