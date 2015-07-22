/**
 * 
 */
package com.yahoo.petermwenda83.contoller;

import java.util.UUID;

/**
 * @author peter
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

}
