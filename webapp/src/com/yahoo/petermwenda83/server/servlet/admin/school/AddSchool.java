package com.yahoo.petermwenda83.server.servlet.admin.school;

import java.io.IOException;
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

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO;
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
	
	private final String ERROR_INVALID_EMAIL = "School email address Invalid";
	
	private final String SCHOOL_ADD_SUCCESS = "School account created successfully";
	private final String SCHOOL_ADD_ERROR = "School account Not Created";
	
	private EmailValidator emailValidator;
	private final String STATUS_ACTIVE_UUID = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
	private static AccountDAO accountDAO;
	private CacheManager cacheManager;


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
   }
   
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String schoolname = StringUtils.trimToEmpty(request.getParameter("schoolname"));
       String schoolusername = StringUtils.trimToEmpty(request.getParameter("schusername"));
       String schoolpassword = StringUtils.trimToEmpty(request.getParameter("schpassword"));
       String schoolphone = StringUtils.trimToEmpty(request.getParameter("schphone"));
       String schoolemail = StringUtils.trimToEmpty(request.getParameter("schemail"));
       //System.out.println(schoolname);
    // This is used to store parameter names and values from the form.
	   	Map<String, String> paramHash = new HashMap<>();    	
	   	paramHash.put("schoolname", schoolname);
	   	paramHash.put("schoolusername", schoolusername);
	   	paramHash.put("schoolpassword", schoolpassword);
	   	paramHash.put("schoolphone", schoolphone);
	   	paramHash.put("schoolemail", schoolemail);
      
       if(StringUtils.isBlank(schoolname)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_NAME); 
    	   
       }else if(StringUtils.isBlank(schoolusername)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_USERNAME); 
    	   
       }else if(StringUtils.isBlank(schoolpassword)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_PASSWORD); 
    	   
       }else if(StringUtils.isBlank(schoolphone)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_PHONE); 
    	   
       }else if(StringUtils.isBlank(schoolemail)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_SCHOOL_EMAIL); 
    	   
       }else if(!emailValidator.isValid(schoolemail)){		     
		   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_INVALID_EMAIL);  
		  	   
	   }else{
		   SchoolAccount account = new SchoolAccount();
		   account.setUuid(account.getUuid()); 
		   account.setStatusUuid(STATUS_ACTIVE_UUID);		   
		   account.setSchoolName(schoolname); 
		   account.setUsername(schoolusername);
		   account.setPassword(schoolpassword);
		   account.setMobile(schoolphone); 
		   account.setEmail(schoolemail); 
		   updateStudentCache(account);
		   if(accountDAO.put(account)){			   
			   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_SUCCESS, SCHOOL_ADD_SUCCESS);
		   }else{
			   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, SCHOOL_ADD_ERROR);  
		   }
    	   
		  
    	   
       }
       //session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY, schoolusername);
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
