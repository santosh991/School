package com.yahoo.petermwenda83.server.servlet.school;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.systemuser.User;
import com.yahoo.petermwenda83.persistence.user.UsresDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.servlet.util.SecurityUtil;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;
import com.yahoo.petermwenda83.server.session.SessionStatisticsFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Login extends HttpServlet {
	
	final String ERROR_WRONG_SCH_PASS = "School Password or Username Incorrect.";
	final String ERROR_WRONG_SCH_USERNAME = "School Password or Username Incorrect.";
	final String ERROR_WRONG_USER_DETAIL = "Incorrect User Details.";
	final String ERROR_WRONG_TERMS_CONDITION = "You Must Agree With Terms and Conditions.";
	
	final String SUCCESS = "You have Successfully Logged in.";

	/**
	 * 
	 */
	 private static final long serialVersionUID = 4366123889354229389L;
	 private Cache schoolCache, statisticsCache;
	 private Logger logger;
	 private static UsresDAO usresDAO;
     private User user = new User();
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
       schoolCache = mgr.getCache(CacheVariables.CACHE_ACCOUNTS_BY_USERNAME);
       statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_ACCOUNT);
       usresDAO = UsresDAO.getInstance();
       logger = Logger.getLogger(this.getClass());
   }
   
   /* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(false);

       if (session != null) {
           session.invalidate();  
       }
       session = request.getSession(true);
       
       String schoolusername = StringUtils.trimToEmpty(request.getParameter("schoolusername"));
      // String schoolpassword = StringUtils.trimToEmpty(request.getParameter("schoolpassword"));
       String position = StringUtils.trimToEmpty(request.getParameter("position"));
       String user_username = StringUtils.trimToEmpty(request.getParameter("user_username"));
       String user_password = StringUtils.trimToEmpty(request.getParameter("user_password"));
       String remember_me = StringUtils.trimToEmpty(request.getParameter("remember_me"));
       
       
       
      // System.out.println(user);
      // System.out.println(remember_me);
     
     
       SchoolAccount school = new SchoolAccount();
     
       
       Element element;
       if ((element = schoolCache.get(schoolusername)) != null) {
    	   school = (SchoolAccount) element.getObjectValue();
    	  SchoolAccountUuid = school.getUuid();
    	  
       }
       
       user.setUsername(user_username);
       user.setPassword(SecurityUtil.getMD5Hash(user_password));
       user.setUserType(position); 
       user.setSchoolAccountUuid(SchoolAccountUuid);  
       boolean recordfound = usresDAO.getUserName(user) != null;
     //  System.out.println(user);    
      
      if (!StringUtils.equals(schoolusername,school.getUsername())){
    	   session.setAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_ERROR, ERROR_WRONG_SCH_USERNAME); 
    	   response.sendRedirect("index.jsp");
       } /*else if ( (!StringUtils.equals(schoolpassword,school.getPassword()) )){
    	   session.setAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_ERROR, ERROR_WRONG_SCH_PASS); 
    	   response.sendRedirect("index.jsp");
       }*/
       else if (!recordfound){
    	   session.setAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_ERROR, ERROR_WRONG_USER_DETAIL); 
    	   response.sendRedirect("index.jsp");
       }
      
      else if (!StringUtils.equals(remember_me,StringUtils.trimToEmpty("on"))){
    	  session.setAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_ERROR, ERROR_WRONG_TERMS_CONDITION); 
    	   response.sendRedirect("index.jsp");
       }
       else{
    	   updateCache(school.getUuid());
           session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_ACCOUNTUUID, school.getUuid());
           session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_KEY, schoolusername);
           session.setAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_SUCCESS, SUCCESS); 
           session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_TIME, String.valueOf(new Date().getTime()));
           request.getSession().setAttribute("user_username", user_username); 
           request.getSession().setAttribute("position", position); 
    	   response.sendRedirect("school/home.jsp");  
       }
       
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
