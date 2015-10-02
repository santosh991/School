package com.yahoo.petermwenda83.persistence.exam;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

public interface SchoolCat1DAO {
	
	/**
	 * 
	 * @param perfomance
	 * @param cat1
	 * @return  whether the student has cat1 and cat2 marks
	 */
	public boolean hasCat1(Perfomance perfomance);
	/**
	 * 
	 * @param perfomance
	 * @param cat1
	 * @return
	 */
	public boolean putCat1(Perfomance perfomance,double cat1);
	 
	

}
