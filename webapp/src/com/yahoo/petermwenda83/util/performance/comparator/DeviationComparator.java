/**
 * 
 */
package com.yahoo.petermwenda83.util.performance.comparator;

import java.util.Comparator;

import com.yahoo.petermwenda83.bean.exam.Deviation;


/**
 * @author peter
 *
 */
public class DeviationComparator implements Comparator<Deviation> {

	@Override
	public int compare(Deviation p1, Deviation p2) {
		return 0;
		//return (p1.getTotals()) > (p2.getTotals()) ? 1 : (p1.getTotals() < p2.getTotals() ? -1 : 0);
		
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
