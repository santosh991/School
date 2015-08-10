/**
 * 
 */
package com.yahoo.petermwenda83.contoller;

import java.util.UUID;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class AllBean {
	private int id;
	private String uuid;
	/**
	 * 
	 */
	public AllBean() {
		id = 0;
		uuid = UUID.randomUUID().toString();
	} 
	/**
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	 /**
	  * 
	  * @return the uuid
	  */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

}
