package com.yahoo.petermwenda83.persistence.exam;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

public interface SchoolCat2DAO {

	
	/**
	 * 
	 * @param perfomance
	 * @param cat1
	 * @return  whether the student has cat1 and cat2 marks
	 */
	public boolean hasCat2(Perfomance perfomance);
	/**
	 * 
	 * @param perfomance
	 * @param cat1
	 * @return
	 */
	public boolean putCat2(Perfomance perfomance,double cat2);
	 
	
	
	
}
