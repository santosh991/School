/**
 * 
 *//*
package com.yahoo.petermwenda83.server.servlet.school.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.systemuser.User;
import com.yahoo.petermwenda83.persistence.user.UsresDAO;
import com.yahoo.petermwenda83.server.servlet.util.SecurityUtil;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.CacheManager;

*//**
 * @author peter
 *
 *//*
public class EditUserPassword extends HttpServlet{
    private static UsresDAO usresDAO;
    public User user = new User();
    
    final String ERROR_EMPTY_FIELDS = "Error! Password Can't Be Empty .";
    final String ERROR_PASSWORD_MISMATCH = "Error! Password Mismatch.";
    final String SUCCESS_PASSWORD_EDITED = "Password ediited Successfully.";
	
	
	*//**
    *
    * @param config
    * @throws ServletException
    *//*
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       CacheManager mgr = CacheManager.getInstance();
       usresDAO = UsresDAO.getInstance();
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String password = StringUtils.trimToEmpty(request.getParameter("password"));
       String password2 = StringUtils.trimToEmpty(request.getParameter("password2"));
       String uuid = StringUtils.trimToEmpty(request.getParameter("uuid"));
       String accountuuid = StringUtils.trimToEmpty(request.getParameter("accountuuid"));
       
       System.out.println(password);
       System.out.println(password2);
       System.out.println(uuid);
       System.out.println(accountuuid);
      if(StringUtils.isEmpty(password) || StringUtils.isEmpty(password2)){
    	   session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_ERROR, ERROR_EMPTY_FIELDS); 
  	       response.sendRedirect("school/staff.jsp");  
       }
      else  if(!StringUtils.equals(password, password2)){
    	   session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_ERROR, ERROR_PASSWORD_MISMATCH); 
   	       response.sendRedirect("school/staff.jsp");  
       }else{
    	   user = (User) usresDAO.getUser(uuid);
    	   user.setPassword(SecurityUtil.getMD5Hash(password));
    	   user.setUuid(uuid);
    	   user.setSchoolAccountUuid(accountuuid); 
    	   
    	   usresDAO.editUser(user, uuid);
    	   
    	   session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_SUCCESS, SUCCESS_PASSWORD_EDITED); 
  	       response.sendRedirect("school/staff.jsp");  
    	  
    	   
       }
       
       
       
       
       
	}
   

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }

}
*/