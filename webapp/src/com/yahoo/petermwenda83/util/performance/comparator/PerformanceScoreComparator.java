/**
 * 
 */
package com.yahoo.petermwenda83.util.performance.comparator;

import java.util.Comparator;

import com.yahoo.petermwenda83.bean.exam.Perfomance;

/**
 * @author peter
 *
 */
public class PerformanceScoreComparator implements Comparator<Perfomance> {

	@Override
	public int compare(Perfomance p1, Perfomance p2) {
		return (p1.getTotals()) > (p2.getTotals()) ? 1 : (p1.getTotals() < p2.getTotals() ? -1 : 0);
		
	}

	/**
	 * Indicates whether some other object is "equal to" this comparator.
	 * 
	 * @param obj
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		return false;				
	}
}
