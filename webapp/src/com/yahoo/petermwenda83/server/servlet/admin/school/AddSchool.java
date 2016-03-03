package com.yahoo.petermwenda83.server.servlet.admin.school;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.exam.GradingSystem;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.House;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.exam.GradingSystemDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO;
import com.yahoo.petermwenda83.persistence.student.HouseDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.AdminSessionConstants;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class AddSchool extends HttpServlet{
     
	private final String ERROR_EMPTY_SCHOOL_NAME = "School name can't be empty";
	private final String ERROR_EMPTY_SCHOOL_USERNAME = "School username can't be empty";
	private final String ERROR_EMPTY_SCHOOL_PASSWORD = "School password can't be empty";
	private final String ERROR_EMPTY_SCHOOL_PHONE = "School phone number can't be empty";
	private final String ERROR_EMPTY_SCHOOL_EMAIL = "School email address can't be empty";
	private final String ERROR_EMPTY_POSTAL_ADDRESS = "School postal address can't be empty";
	private final String ERROR_EMPTY_TOWN = "School home town address can't be empty";
	private final String ERROR_SCHOOL_USERNAME_EXIST = "The School username already exists in the system";
	private final String ERROR_INVALID_EMAIL = "School email address Invalid";
	
	private final String SCHOOL_ADD_SUCCESS = "School account created successfully";
	private final String SCHOOL_ADD_ERROR = "School account Not Created";
	
	private EmailValidator emailValidator;
	private final String STATUS_ACTIVE_UUID = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
	private static AccountDAO accountDAO;
	private CacheManager cacheManager;
	private static ExamConfigDAO examConfigDAO;
	private static GradingSystemDAO gradingSystemDAO;
	private static HouseDAO houseDAO;
	private static RoomDAO roomDAO;
	private static TermFeeDAO termFeeDAO;


	/**   
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       emailValidator = EmailValidator.getInstance();
       accountDAO = AccountDAO.getInstance();
       cacheManager = CacheManager.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
       gradingSystemDAO = GradingSystemDAO.getInstance();
       houseDAO = HouseDAO.getInstance();
       roomDAO = RoomDAO.getInstance();
       termFeeDAO = TermFeeDAO.getInstance();
   }
   
  
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String schoolname = StringUtils.trimToEmpty(request.getParameter("schoolname"));
       String schoolusername = StringUtils.trimToEmpty(request.getParameter("schusername"));
       String schoolpassword = StringUtils.trimToEmpty(request.getParameter("schpassword"));
       String schoolphone = StringUtils.trimToEmpty(request.getParameter("schphone"));
       String schoolemail = StringUtils.trimToEmpty(request.getParameter("schemail"));
       String schoolpostaladdress = StringUtils.trimToEmpty(request.getParameter("postaladdress"));
       String schoolhometown = StringUtils.trimToEmpty(request.getParameter("hometown"));
     
    // This is used to store parameter names and values from the form.
	   	Map<String, String> paramHash = new HashMap<>();    	
	   	paramHash.put("schoolname", schoolname);
	   	paramHash.put("schoolusername", schoolusername);
	   	paramHash.put("schoolpassword", schoolpassword);
	   	paramHash.put("schoolphone", schoolphone);
	   	paramHash.put("schoolemail", schoolemail);
		paramHash.put("schoolpostaladdress", schoolpostaladdress);
	   	paramHash.put("schoolhometown", schoolhometown);
      
       if(StringUtils.isBlank(schoolname)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_NAME); 
    	   
       }else if(StringUtils.isBlank(schoolusername)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_USERNAME); 
    	   
       }else if(accountDAO.getSchoolByUsername(schoolusername) !=null){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_SCHOOL_USERNAME_EXIST); 
    	   
       }else if(StringUtils.isBlank(schoolpassword)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_PASSWORD); 
    	   
       }else if(StringUtils.isBlank(schoolphone)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_PHONE); 
    	   
       }else if(StringUtils.isBlank(schoolemail)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_EMAIL); 
    	   
       }else if(!emailValidator.isValid(schoolemail)){		     
		   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_INVALID_EMAIL);  
		  	   
	   }else if(StringUtils.isBlank(schoolpostaladdress)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_POSTAL_ADDRESS); 
    	   
       }else if(StringUtils.isBlank(schoolhometown)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_TOWN); 
    	   
       }else{
		   SchoolAccount account = new SchoolAccount();
		   account.setUuid(account.getUuid()); 
		   account.setStatusUuid(STATUS_ACTIVE_UUID);		   
		   account.setSchoolName(schoolname); 
		   account.setUsername(schoolusername);
		   account.setPassword(schoolpassword);
		   account.setMobile(schoolphone); 
		   account.setEmail(schoolemail); 
		   account.setPostalAddress(schoolpostaladdress); 
		   account.setTown(schoolhometown); 
		   updateStudentCache(account);
		   
		   Calendar calendar = Calendar.getInstance();
		   final int YEAR = calendar.get(Calendar.YEAR);
		   ExamConfig examConfig = new ExamConfig();
    	   examConfig.setSchoolAccountUuid(account.getUuid());
    	   examConfig.setTerm("1");
    	   examConfig.setYear(""+YEAR);
    	   examConfig.setExam("C1");
    	   examConfig.setExamMode("ON"); 
    	   
    	   GradingSystem gradingSystem = new GradingSystem();
    	   gradingSystem.setSchoolAccountUuid(account.getUuid()); 
    	   gradingSystem.setGradeAplain(Integer.parseInt("83"));
    	   gradingSystem.setGradeAminus(Integer.parseInt("71"));
    	   gradingSystem.setGradeBplus(Integer.parseInt("67"));
    	   gradingSystem.setGradeBplain(Integer.parseInt("62"));
    	   gradingSystem.setGradeBminus(Integer.parseInt("54"));
    	   gradingSystem.setGradeCplus(Integer.parseInt("50"));
    	   gradingSystem.setGradeCplain(Integer.parseInt("45"));
    	   gradingSystem.setGradeCminus(Integer.parseInt("40"));
    	   gradingSystem.setGradeDplus(Integer.parseInt("35"));
    	   gradingSystem.setGradeDplain(Integer.parseInt("30"));
    	   gradingSystem.setGradeDminus(Integer.parseInt("25"));
    	   gradingSystem.setGradeE(Integer.parseInt("0")); 
		   
    	   
    	   String [] defaultClasses = {"FORM 1 N","FORM 2 N","FORM 3 N","FORM 3 N"};
    	   String [] defaultHouse = {"Suswa","Tana","Longonot","Chania"};
    	   
		   
		   if(accountDAO.put(account) &&examConfigDAO.putExamConfig(examConfig)&&gradingSystemDAO.putGradingSystem(gradingSystem)){	
			   
			   for(int i=0;i<defaultClasses.length;i++){
	    		   ClassRoom room = new ClassRoom();
	        	   room.setSchoolAccountUuid(account.getUuid());
	        	   room.setRoomName(defaultClasses[i]);
	        	   roomDAO.putroom(room);
	    	   }
			   
			   for(int i=0;i<defaultHouse.length;i++){
	    		   House house = new House();
	    		   house.setSchoolAccountUuid(account.getUuid());
	    		   house.setHouseName(defaultHouse[i]);
	    		   houseDAO.putHouse(house);
	    	   }
			   
			   String [] terms = {"1","2","3"};
			   double [] fee = {18000,12000,8000};
			   for(int i=0; i<terms.length;i++){
				   TermFee termFee = new TermFee();
				   termFee.setSchoolAccountUuid(account.getUuid());
				   termFee.setTerm(terms[i]); 
				   termFee.setTermAmount(fee[i]);
				   termFeeDAO.putTermFee(termFee);
			   }
			   
			   
	    	 
			   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_SUCCESS, SCHOOL_ADD_SUCCESS);
		   }else{
			   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, SCHOOL_ADD_ERROR);  
		   }
    	   
		  
    	   
       }
      
       session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_PARAM, paramHash);
       response.sendRedirect("addSchool.jsp");
	   return;
   }
   
   

private void updateStudentCache(SchoolAccount accnt) {
	cacheManager.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME).put(new Element(accnt.getUsername(), accnt));
	cacheManager.getCache(CacheVariables.CACHE_ACCOUNTS_BY_UUID).put(new Element(accnt.getUuid(), accnt));
}



@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }

/**
 * 
 */
private static final long serialVersionUID = -1176743144090333411L;


}
