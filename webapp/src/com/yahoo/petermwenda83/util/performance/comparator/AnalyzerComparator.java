/**
 * 
 */
package com.yahoo.petermwenda83.util.performance.comparator;

import java.util.Comparator;

import com.yahoo.petermwenda83.bean.exam.Analyzer;



/**
 * @author peter
 *
 */
public class AnalyzerComparator implements Comparator<Analyzer> {

	/**
	 * 
	 */
	public AnalyzerComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Analyzer a1, Analyzer a2) {
		return a1.getTotal() > a2.getTotal() ? 1 :  a1.getTotal() < a2.getTotal() ? -1 : 0;
	}

}
