/**
 * 
 */
package com.yahoo.petermwenda83.dao.room;

import java.util.List;

import com.yahoo.petermwenda83.bean.room.ClassRoom;

/**
 * @author peter
 *
 */
public interface SchoolRoomDAO {
	  /**
	   * 
	   * @param Uuid
	   * @return
	   */
	public ClassRoom getroom(String Uuid);
	  /**
	   * 
	   * @param RoomName
	   * @return
	   */
	public ClassRoom get(String RoomName);
	   /**
	    * 
	    * @param room
	    * @return
	    */
	public boolean putroom(ClassRoom room);
	   /**
	    * 
	    * @param room
	    * @param roomname
	    * @return
	    */
	public boolean editroom(ClassRoom room,String roomname);
	    /**
	     * 
	     * @param room
	     * @param roomname
	     * @return
	     */
	public boolean deleteroom(ClassRoom room,String roomname);
	    /**
	     * 
	     * @return
	     */
	public List<ClassRoom> getAllRooms();
	

}
