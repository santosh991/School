/**
 * 
 */
package com.yahoo.petermwenda83.server.session;

/**
 * @author peter
 *
 */
public class SessionConstants {
	
	final public static int SESSION_TIMEOUT = 500; 
	
	
	 //SchoolAccount Management
	public static final String ACCOUNT_SIGN_IN_ERROR_KEY = "Error Login";
	public static final Object ACCOUNT_SIGN_IN_NO_EMAIL = "Sorry, there is no user with that email. Please try again.";
	public static final String SCHOOL_ACCOUNT_SIGN_IN_ACCOUNTUUID = "Account Signin AccountUuid";
	public static final String SCHOOL_ACCOUNT_SIGN_IN_KEY = "Account Signin Key";
	public static final String SCHOOL_ACCOUNT_SIGN_IN_TIME = "Account Signin Time";
	
	final public static String SCHOOL_ACCOUNT_ADD_SUCCESS = "SchoolAccount Account Added Successfully";
	
	//staff management
	public static final String SCHOOL_STAFF_SIGN_IN_USERNAME = "Staff signin username"; 
	public static final String SCHOOL_STAFF_SIGN_IN_ID = "Staff signin ID";
	public static final String SCHOOL_STAFF_SIGN_IN_POSITION = "Staff signin Position";
	
	
	//SchoolAccount login
	public static final String SCHOOL_ACCOUNT_LOGIN_SUCCESS = "Login Success";
	public static final String SCHOOL_ACCOUNT_LOGIN_ERROR = "Login Error";
	public static final String ACCOUNT_SIGN_IN_WRONG_PASSWORD = "wrong password";
	
	
	//Student Management
	public static final String ACCOUNT_STUDENT_ADD_SUCCESS = "Student Added Successfully";
	public static final String ACCOUNT_STUDENT_ADD_ERROR = "Student Add Error";
	
	
	//Student management
	public static final String SCHOOL_USER_EDIT_SUCCESS = "User Edited Successfully";
	public static final String SCHOOL_USER_EDIT_ERROR = "User Edit Error";
	
	   //file upload management
    public static final String FILE_UPLOAD_SUCCESS = "Filed uploaded Successfully";
    public static final String FILE_UPLOAD_ERROR = "Filed upload Error";
	
    final public static String EXAM_CONFIG_UPDATE_ERROR = "Exam Config update error";
	final public static String EXAM_CONFIG_UPDATE_SUCCESS = "Exam Config update Successfully";
	/**
	 * 
	 */
	public SessionConstants() {
		// TODO Auto-generated constructor stub
	}

}
