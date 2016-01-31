package com.yahoo.petermwenda83.server.session;

public class AdminSessionConstants {
	
	final public static int SESSION_TIMEOUT = 500; 
	
	 //Admin 
	public static final String ADMIN_SIGN_IN_ERROR_KEY = "Error AdminLogin";
	final public static String ADMIN_SESSION_KEY = "Admin Session Key";
	public static final Object ADMIN_SIGN_IN_NO_EMAIL = "Sorry, there is no user with that email. Please try again.";
	public static final String ADMIN_SIGN_IN_ACCOUNTUUID = "Admin Signin AccountUuid";
	public static final String ADMIN_SIGN_IN_KEY = "admin Signin Key";
	public static final String ADMIN_SIGN_IN_TIME = "Admin Signin Time";
	final public static String ADMIN_SIGN_IN_ERROR_VALUE = "Sorry, the administrator username and/or " +
			"password are incorrect. Please try again.";
	
	final public static String SCHOOL_ACCOUNT_ADD_SUCCESS = "SchoolAccount Account Added Successfully";
	final public static String SCHOOL_ACCOUNT_ADD_KEY = "SchoolAccount Account Add Key";
	final public static String SCHOOL_ACCOUNT_ADD_ERROR = "SchoolAccount Account Add Error";
	final public static String SCHOOL_ACCOUNT_UPDATE_ERROR = "SchoolAccount Account Update Error";
	final public static String SCHOOL_ACCOUNT_UPDATE_SUCCESS = "SchoolAccount Account Update Success";
	final public static String SCHOOL_ACCOUNT_PARAM = "SchoolAccount Account Parameters";
	
	final public static String PRINCIPAL_ADD_ERROR = "Principal add error";
	final public static String PRINCIPAL_ADD_SUCCESS = "Principal added Successfully";
	

	public AdminSessionConstants() {
		// TODO Auto-generated constructor stub 
	}

}
