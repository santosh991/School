/**
 * 
 */
package com.yahoo.petermwenda83.bean.smsapi;

/**
 * @author peter
 *
 */
public class AfricasTalking {
	
	private String username;
	private String apiKey;
	private String recipients;
	private String message;

	/**
	 * 
	 */
	public AfricasTalking() {
		username = "mwendapeter72@gmail.com";
		apiKey = "a565d7d375de2c69da745d0297e92bd50b25f52b402c5404ddd72b3ecc421b3b";
		recipients = "";
	    message = "";
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the recipients
	 */
	public String getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
