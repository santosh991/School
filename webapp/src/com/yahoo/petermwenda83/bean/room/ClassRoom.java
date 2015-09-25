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
	private String roomname;
	private String SchoolAccountUuid;
	/**
	 * 
	 */
	public ClassRoom() {
		super();
		roomname ="";
		SchoolAccountUuid ="";
	}
	
	/**
	 * @return the roomname
	 */
	public String getRoomname() {
		return roomname;
	}

	/**
	 * @param roomname the roomname to set
	 */
	public void setRoomname(String roomname) {
		this.roomname = roomname;
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
		builder.append("Room Name");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid()=");
		builder.append( getUuid());
		builder.append(", roomname =");
		builder.append(roomname);
		builder.append(", SchoolAccountUuid =");
		builder.append(SchoolAccountUuid);
		return builder.toString(); 
		}


}
