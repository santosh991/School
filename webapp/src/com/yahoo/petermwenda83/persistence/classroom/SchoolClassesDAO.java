/**
 * 
 */
package com.yahoo.petermwenda83.persistence.classroom;

import java.util.List;

import com.yahoo.petermwenda83.bean.classroom.Classes;

/**
 * @author peter
 *
 */
public interface SchoolClassesDAO {
	
	public Classes getClass(String Uuid);
	
	public List<Classes> getClassList();
	
	

}
