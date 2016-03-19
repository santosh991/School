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
	
	
	//STAFF Management
	public static final String STAFF_ADD_SUCCESS = "Staff Added Successfully";
	public static final String STAFF_ADD_ERROR = "Staff Add Error";
	public static final String STAFF_ADD_PARAM = "Staff Parameters";
	public static final String STAFF_UPDATE_SUCCESS = "Staff Updated Successfully";
	public static final String STAFF_UPDATE_ERROR = "Staff Update Error";
	
	public static final String STAFF_FIND_ERROR = "Staff Find Error";
	public static final String STAFF_FIND_SUCCESS = "Staff found Successfully";
	public static final String STAFF_PARAM = "Staff param";
	
	public static final String STUDENT_PARAM = "Student Parameters";
	public static final String STUDENT_ADD_ERROR = "Student add error";
	public static final String STUDENT_ADD_SUCCESS = "Student added successfully";
	public static final String STUDENT_UPDATE_ERROR = "Student add error";
	public static final String STUDENT_UPDATE_SUCCESS = "Student added successfully";
	
	public static final String FATHER_MOTHER_PARAM = "Father/Mother Parameters";
	public static final String STUDENT_SPONSOR_PARAM = "Father/Mother Parameters";
	
	public static final String PARENT_PARAM = "Parent Parameters";
	public static final String SPONSOR_PARAM = "Sponor Parameters";
	public static final String HOUSE_PARAM = "House Parameters";
	
	public static final String STUDENT_FIND_ERROR = "student not found";
	public static final String STUDENT_FIND_SUCCESS = "student found successfully";
	
	public static final String FATHER_MOTHER_ADD_ERROR = "Father/Mother add error";
	public static final String FATHER_MOTHER_ADD_SUCCESS = "Father/Mother add successfully";
	
	public static final String HOUSE_ADD_ERROR = "House add error";
	public static final String HOUSE_ADD_SUCCESS = "House add successfully";
	
	public static final String SPONSOR_ADD_ERROR = "Sponsor add error";
	public static final String SPONSOR_ADD_SUCCESS = "Sponsor add successfully";
	
	
	public static final String GRADE_PARAM = "Grade scale Parameters";
	public static final String GRADE_ADD_ERROR = "Grade add error";
	public static final String GRADE_ADD_SUCCESS = "Grade add successfully";
	
	//payment
	
	public static final String FEE_PARAM = "Fee Parameters";
	public static final String STUENT_PARAM_F = "Student Parameters";
	public static final String STUENT_FEE_ADD_PARAM = "Student Parameters";
	public static final String STUDENT_FEE_ADD_ERROR = "Student fee details add Error";
	public static final String STUDENT_FEE_ADD_SUCCESS = "Student fee details add  Success";
	public static final String STUDENT_FEE_UPDATE_ERROR = "Student fee details UPDATE Error";
	public static final String STUDENT_FEE_UPDATE_SUCCESS = "Student fee details UPDATE  Success";
	
	public static final String STUENT_FEE_UPDATE_PARAM = "Student Parameters";
	public static final String STUENT_POCKET_MONEY_FIND_PARAM = "Pocket Money Parameters";
	public static final String STUENT_POCKET_MONEY_PARAM = "pocket Money Parameters";
	public static final String STUENT_PM_DEPOSITE_PARAM = "deposit Parameters";
	public static final String STUENT_PM_WITHDRAW_PARAM = "Withdraw Parameters";
	public static final String STUENT_BALANCE_PARAM = "Balance Parameters";
	
	//Term Fee Update
	
	public static final String TERM_FEE_UPDATE_SUCCESS = "Term Fee Updated Successfully";
	public static final String TERM_FEE_UPDATE_ERROR = "Term Fee Updated Error";
	
	
	//promote students 
	public static final String PROMOTE_CALSS_SUCCESS = "Class promoted Successfully";
	public static final String PROMOTE_CALSS_ERROR = "Class promote Error";
	
	public static final String STATUS_CHANGE_SUCCESS = "Status change Successfully";
	public static final String STATUS_CHANGE_ERROR = "Status change Error";
	
	//room house management
	public static final String HOUSE_REG_SUCCESS = "House added Successfully";
	public static final String HOUSE_REG_ERROR = "House add Error";
	
	public static final String ROOM_REG_SUCCESS = "Class room added Successfully";
	public static final String ROOM_REG_ERROR = "Class room add Error";
	
	
	
	//reports
	 public static final String ERROR_REPORT_NO_PAGES = "Sorry, the report has no pages tp print";

	 
	 //other monies
	 public static final String OTHER_MONIES_ADD_ERROR = "Something went wrong";
	 public static final String OTHER_MONIES_ADD_SUCESS = "New payments added Successfully";
	 
	 public static final String STUDENT_ADD_OTHER_MONIES_ADD_ERROR = "Other monies add error";
	 public static final String STUDENT_ADD_OTHER_MONIES_ADD_SUCCESS = "Other monies add error Success";
	 
	 public static final String STUENT_O_M_PARAM = "Other M Parameters";

	 //sms send
	 public static final String SMS_SEND_SUCCESS = "SMS sent Successfully";
	 public static final String SMS_SEND_ERROR = "SMS send error";
	
	
	   //file upload management
    public static final String FILE_UPLOAD_SUCCESS = "Filed uploaded Successfully";
    public static final String FILE_UPLOAD_ERROR = "Filed upload Error";
	
    final public static String EXAM_CONFIG_UPDATE_ERROR = "Exam Config update error";
	final public static String EXAM_CONFIG_UPDATE_SUCCESS = "Exam Config update Successfully";
	final public static String EXAM_CONFIG_UPDATE_PARAM = "Exam Config Parameters";
	/**
	 * 
	 */
	public SessionConstants() {
		// TODO Auto-generated constructor stub
	}

}
