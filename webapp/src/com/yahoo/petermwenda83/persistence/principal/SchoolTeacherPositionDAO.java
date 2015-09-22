/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.principal;

import java.util.List;

import com.yahoo.petermwenda83.bean.staff.teaching.TeacherPosition;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolTeacherPositionDAO {
	/**
	 * 
	 * @param Uuid
	 *  @return Teacher Position
	 */
	public TeacherPosition getstaffPos(String Uuid);
	/**
	 * 
	 * @param teacherPos
	 * @return Whether TeacherPosition was put successfully or not
	 */
	public boolean putTeacherPosition(TeacherPosition teacherPos);
	/**
	 * 
	 * @param teacherPos
	 * @param Uuid
	 * @return Whether TeacherPosition was edited successfully or not
	 */
	public boolean editTeacherPosition(TeacherPosition teacherPos,String Uuid);
	/**
	 * 
	 * @param teacherPos
	 * @param Uuid
	 * @return Whether TeacherPosition was deleted successfully or not
	 */
	public boolean deleteTeacherPosition(TeacherPosition teacherPos,String Uuid);
	/**
	 * 
	 * @return List of all teachers Positions
	 */
	public List<TeacherPosition> getAllTeacherPosition();

}
