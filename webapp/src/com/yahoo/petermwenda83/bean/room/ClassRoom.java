/**
 * 
 */
package com.yahoo.petermwenda83.bean.room;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter
 *
 */
public class ClassRoom  extends AllBean{
	private String RoomName;
	private String Room;
	private String SchoolAccountUuid;
	/**
	 * 
	 */
	public ClassRoom() {
		super();
		RoomName ="";
		Room ="";
		SchoolAccountUuid ="";
	}
	
	
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return RoomName;
	}


	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}


	/**
	 * @return the room
	 */
	public String getRoom() {
		return Room;
	}


	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		Room = room;
	}


	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return SchoolAccountUuid;
	}

	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		SchoolAccountUuid = schoolAccountUuid;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Room Details");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid()=");
		builder.append( getUuid());
		builder.append(", Room =");
		builder.append(Room);
		builder.append(", RoomName =");
		builder.append(RoomName);
		builder.append(", SchoolAccountUuid =");
		builder.append(SchoolAccountUuid);
		return builder.toString(); 
		}


}
