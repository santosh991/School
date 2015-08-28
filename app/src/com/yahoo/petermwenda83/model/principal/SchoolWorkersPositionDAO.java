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
package com.yahoo.petermwenda83.model.principal;

import java.util.List;

import com.yahoo.petermwenda83.contoller.staff.nonteaching.WorkerPosition;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolWorkersPositionDAO {
	 /**
	 * 
	 * @param Uuid
	 * @return Worker Position
	 */
	public WorkerPosition getWorkerPosition(String Uuid);
	  /**
	   * 
	   * @param workerPos
	   * @return Whether WorkerPosition was put successfully 
	   */
	public boolean put(WorkerPosition workerPos);
	  /**
	   * 
	   * @param workerPos
	   * @param Uuid
	   * @return Whether WorkerPosition was edited successfully 
	   */
	public boolean edit(WorkerPosition workerPos,String Uuid);
	  /**
	   * 
	   * @param workerPos
	   * @param Uuid
	   * @return Whether WorkerPosition was deleted successfully 
	   */
	public boolean delete(WorkerPosition workerPos,String Uuid);
	 /**
	   * 
	   * @return List of all Workers Position
	   */
	public List<WorkerPosition> getAllWorkerPosition();

}
