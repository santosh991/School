package com.yahoo.petermwenda83.server.servlet.school;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.jasypt.util.text.BasicTextEncryptor;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.Staff;
import com.yahoo.petermwenda83.persistence.staff.StaffDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.servlet.util.FontImageGenerator;
import com.yahoo.petermwenda83.server.servlet.util.SecurityUtil;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;
import com.yahoo.petermwenda83.server.session.SessionStatisticsFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Login extends HttpServlet {
     
	// Error message provided when incorrect captcha is submitted
    final String ACCOUNT_SIGN_IN_BAD_CAPTCHA = "The characters you entered did not match those provided in the image.";
	final String ERROR_ACCEPT_TERMS_AND_CONDITION = "You must agree with terms and conditions.";
	final String ERROR_WRONG_USER_DETAIL = "Incorrect staff credentials.";
	final String BLANK_FIELDS_NOT_ALLOWED = "blank fields are not allowed.";
	

	/**
	 * 
	 */
	 private static final long serialVersionUID = 4366123889354229389L;
	 
	 private static StaffDAO staffDAO;
	 private BasicTextEncryptor textEncryptor;
	 private String hiddenCaptchaStr = "";
	 private Cache schoolCache, statisticsCache;
	 private Logger logger;
     String SchoolAccountUuid;

	/**
    *
    * @param config
    * @throws ServletException
    */
    @Override
    public void init(ServletConfig config) throws ServletException {
       super.init(config);
       CacheManager mgr = CacheManager.getInstance();
       textEncryptor = new BasicTextEncryptor();
       textEncryptor.setPassword(FontImageGenerator.SECRET_KEY);
       schoolCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
       statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_SCHOOL_ACCOUNT);
       logger = Logger.getLogger(this.getClass());
       staffDAO = StaffDAO.getInstance();
  
     }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(false);
       
    		   
       if (session != null) {
           session.invalidate();  
       }
       session = request.getSession(true);
       
       String schoolusername = StringUtils.trimToEmpty(request.getParameter("schoolusername"));
       String staffposition = StringUtils.trimToEmpty(request.getParameter("staffposition"));
       String staffusername = StringUtils.trimToEmpty(request.getParameter("staffusername"));
       String staffpassword = StringUtils.trimToEmpty(request.getParameter("staffpassword"));
       hiddenCaptchaStr = request.getParameter("captchaHidden");
       String captchaAnswer = request.getParameter("captchaAnswer").trim();
       
       
       SchoolAccount school = new SchoolAccount();
       Element element;
       if ((element = schoolCache.get(schoolusername)) != null) {
    	   school = (SchoolAccount) element.getObjectValue();
    	  SchoolAccountUuid = school.getUuid();
    	  
        }
     
      
       
       if(StringUtils.isEmpty(schoolusername)){
    	   session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, BLANK_FIELDS_NOT_ALLOWED);
           response.sendRedirect("index.jsp");
           return;
    	   
       }else if(StringUtils.isEmpty(staffposition)){
    	   session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, BLANK_FIELDS_NOT_ALLOWED);
           response.sendRedirect("index.jsp");
           return;
    	   
       }else if(StringUtils.isEmpty(staffusername)){
    	   session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, BLANK_FIELDS_NOT_ALLOWED);
           response.sendRedirect("index.jsp");
           return;
    	   
       }else if(StringUtils.isEmpty(staffpassword)){
    	   session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, BLANK_FIELDS_NOT_ALLOWED);
           response.sendRedirect("index.jsp");
           return;
    	   
       }else{
    	   
    	   
    	   
    	   if (school != null) {
    		   Staff staff = staffDAO.getStaffByUsername(SchoolAccountUuid, staffusername);
    		   
               if (!validateCaptcha(hiddenCaptchaStr, captchaAnswer)) {
                   session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, ACCOUNT_SIGN_IN_BAD_CAPTCHA);
                   response.sendRedirect("index.jsp");
                   return;
               }
              
               else if(!StringUtils.equals(staffposition, staff.getPositionUuid())){ 
            	 session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, ERROR_WRONG_USER_DETAIL);
            	 response.sendRedirect("index.jsp");
                 return;
               }
               
              else{
            	   
               
    	       if (StringUtils.equals(SecurityUtil.getMD5Hash(staffpassword), staff.getPassword())) {
        	   //System.out.println("staff password="+SecurityUtil.getMD5Hash(staffpassword));
        	   
        	   updateCache(school.getUuid());
	           session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_ACCOUNTUUID, school.getUuid());
	           session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY, schoolusername);
	           session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_SUCCESS, SessionConstants.SCHOOL_ACCOUNT_LOGIN_SUCCESS); 
	           session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_TIME, String.valueOf(new Date().getTime()));
	           request.getSession().setAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME, staff.getUserName()); 
	           request.getSession().setAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID, staff.getUuid()); 
	           response.sendRedirect("school/schoolIndex.jsp"); 
            
              }else {
             session.setAttribute(SessionConstants.SCHOOL_ACCOUNT_LOGIN_ERROR, ERROR_WRONG_USER_DETAIL);
             response.sendRedirect("index.jsp");
             return;
                   } 
                 }
  	   
           }
      	   
        }
     
   }
   
   
   
   private boolean validateCaptcha(String encodedSystemCaptcha, String userCaptcha) {
	   boolean valid = false;
       String decodedHiddenCaptcha = "";

       try {
           decodedHiddenCaptcha = textEncryptor.decrypt(URLDecoder.decode(encodedSystemCaptcha, "UTF-8"));

       } catch (UnsupportedEncodingException e) {
           logger.error("UnsupportedEncodingException while trying to validate captcha.");
           logger.error(ExceptionUtils.getStackTrace(e));
       }

       if (StringUtils.equalsIgnoreCase(decodedHiddenCaptcha, userCaptcha)) {
           valid = true;
       }

       return valid;
}

private void updateCache(String accountuuid) {
	   SessionStatistics statistics = SessionStatisticsFactory.getSessionStatistics(accountuuid);
       statisticsCache.put(new Element(accountuuid, statistics));
	
}

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }
}
