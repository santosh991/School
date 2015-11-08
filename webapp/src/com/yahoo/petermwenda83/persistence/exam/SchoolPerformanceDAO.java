package com.yahoo.petermwenda83.persistence.exam;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

public interface SchoolPerformanceDAO {

	/**
	 * 
	 * @param StudentUuid
	 * @return the student Performance
	 */
	public Perfomance get(String StudentUuid,String ExamDetailUuid);
	
	
	/**
	 * 
	 * @param perfomance
	 * @return
	 */
	public boolean delete(Perfomance perfomance);
	
	/**
	 * 
	 * @return
	 */
	public List<Perfomance>  getAllPerfomance(String ExamDetailUuid);
}
