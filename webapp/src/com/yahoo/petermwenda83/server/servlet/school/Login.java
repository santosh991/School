package com.yahoo.petermwenda83.server.servlet.school;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;
import com.yahoo.petermwenda83.server.session.SessionStatisticsFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4366123889354229389L;
	 private Cache schoolCache, statisticsCache;
	 private Logger logger;




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

       logger = Logger.getLogger(this.getClass());
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(false);

       if (session != null) {
           session.invalidate();  
       }
       session = request.getSession(true);
       
       String username = StringUtils.trimToEmpty(request.getParameter("username"));
       String password = StringUtils.trimToEmpty(request.getParameter("password"));
       
       //System.out.println(username);
       //System.out.println(password);
      // String pass ="demo";
      // String user ="demo";
       
       SchoolAccount school = new SchoolAccount();
       
       Element element;
       if ((element = schoolCache.get(username)) != null) {
    	   school = (SchoolAccount) element.getObjectValue();
       }
       //StringUtils.equals(SecurityUtil.getMD5Hash(password), account.getLogpassword())
       if (school != null) {
    	   
     
       if (!StringUtils.equals(password,school.getPassword())){
    	   
    	   response.sendRedirect("index.jsp");
       }else if (!StringUtils.equals(username,school.getUsername())){
    	   response.sendRedirect("index.jsp");
       }else{
    	   updateCache(school.getUuid());
           session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_ACCOUNTUUID, school.getUuid());
           session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_KEY, username);

           session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_TIME, String.valueOf(new Date().getTime()));
    	   response.sendRedirect("school/home.jsp");  
       }
      
       
       
       }else{
    	   session.setAttribute(SessionConstants.ACCOUNT_SIGN_IN_ERROR_KEY, SessionConstants.ACCOUNT_SIGN_IN_NO_EMAIL);
           response.sendRedirect("index.jsp");
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
