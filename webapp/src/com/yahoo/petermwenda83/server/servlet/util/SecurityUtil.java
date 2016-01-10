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
 * ##### SchoolAccount Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.server.servlet.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class SecurityUtil {

	private static Logger logger = Logger.getLogger(SecurityUtil.class);
	
	
	/**
	 * Return the MD5 hahs of a String. It will work correctly for most 
	 * strings. A word which does not work correctly is "michael" (check 
	 * against online MD5 hash tools).	
	 *
	 * @param toHash plain text string to encryption
	 * @return an md5 hashed string
	 */
	public static String getMD5Hash(String toHash) {	
	    String md5Hash = "";
	
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        
	        md.update(toHash.getBytes(), 0, toHash.length());
	
	        md5Hash = new BigInteger(1, md.digest()).toString(16);
			
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("NoSuchAlgorithmException while getting MD5 hash of '" + toHash + "'");
			logger.error(ExceptionUtils.getStackTrace(e));
	    }
	
	    return md5Hash;
	}

}
