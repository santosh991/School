package com.yahoo.petermwenda83.server.servlet.admin.school;

import java.io.IOException;

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

public class UpdateSchool extends HttpServlet{

	private final String ERROR_EMPTY_SCHOOL_NAME = "School name can't be empty";
	private final String ERROR_EMPTY_SCHOOL_USERNAME = "School username can't be empty";
	private final String ERROR_EMPTY_SCHOOL_PASSWORD = "School password can't be empty";
	private final String ERROR_UNEXPECTED_ERROR = "Unexpected error occured";
	private final String ERROR_EMPTY_SCHOOL_PHONE = "School phone number can't be empty";
	private final String ERROR_EMPTY_SCHOOL_EMAIL = "School email address can't be empty";
	private final String ERROR_EMPTY_POSTAL_ADDRESS = "School postal address can't be empty";
	private final String ERROR_EMPTY_TOWN = "School home town address can't be empty";
	
	private final String ERROR_INVALID_EMAIL = "School email address can't be empty";
	
	private final String SCHOOL_UPDATE_SUCCESS = "School account Updated successfully";
	private final String SCHOOL_UPDATE_ERROR = "School account Not Updated";
	
	
	private EmailValidator emailValidator;
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
       String schoolusername = StringUtils.trimToEmpty(request.getParameter("username"));
       String schoolpassword = StringUtils.trimToEmpty(request.getParameter("password"));
       String schoolphone = StringUtils.trimToEmpty(request.getParameter("mobile"));
       String schoolemail = StringUtils.trimToEmpty(request.getParameter("email"));
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String schoolpostaladdress = StringUtils.trimToEmpty(request.getParameter("postaladdress"));
       String schoolhometown = StringUtils.trimToEmpty(request.getParameter("hometown"));
    
      
       if(StringUtils.isBlank(schoolname)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, ERROR_EMPTY_SCHOOL_NAME); 
    	   
       }else if(StringUtils.isBlank(schoolusername)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, ERROR_EMPTY_SCHOOL_USERNAME); 
    	   
       }else if(StringUtils.isBlank(schoolpassword)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, ERROR_EMPTY_SCHOOL_PASSWORD); 
    	   
       }else if(StringUtils.isBlank(schoolphone)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, ERROR_EMPTY_SCHOOL_PHONE); 
    	   
       }else if(StringUtils.isBlank(schoolemail)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, ERROR_EMPTY_SCHOOL_EMAIL); 
    	   
       }else if(!emailValidator.isValid(schoolemail)){		     
		   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_INVALID_EMAIL);  
		  	   
	   }else if(StringUtils.isBlank(schoolpostaladdress)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_POSTAL_ADDRESS); 
    	   
       }else if(StringUtils.isBlank(schoolhometown)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_ADD_ERROR, ERROR_EMPTY_TOWN); 
    	   
       }else if(StringUtils.isBlank(schooluuid)){
    	   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, ERROR_UNEXPECTED_ERROR); 
    	   
       }else{
    	   
    	   SchoolAccount account =  accountDAO.get(schooluuid); 
    	  
		   account.setSchoolName(schoolname); 
		   account.setUsername(schoolusername);
		   account.setPassword(schoolpassword); 
		   account.setMobile(schoolphone); 
		   account.setEmail(schoolemail); 
		   account.setPostalAddress(schoolpostaladdress); 
		   account.setTown(schoolhometown); 
		   updateSchoolCache(account);
		   if(accountDAO.update(account) ){ 
			   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_SUCCESS, SCHOOL_UPDATE_SUCCESS); 
		   }else{
			   session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, SCHOOL_UPDATE_ERROR);  
		   }
    	   
    	  
    	    
       }
       
       //session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY, schoolusername);
       response.sendRedirect("adminIndex.jsp"); 
       return;
       
   }
   
   

private void updateSchoolCache(SchoolAccount accnt) {
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
private static final long serialVersionUID = 1L;

}
