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
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.CacheManager;

*//**
 * @author peter
 *
 *//*
public class EditUser extends HttpServlet{
	private static UsresDAO usresDAO;
    public User user = new User();
    
    
    final String ERROR_USERNAME_EXIST = "Error! Username Already Exist In The System.";
    final String ERROR_EMPTY_FIELDS = "Error! Username OR Position Can't Be Empty .";
    final String SUCCESS_USER_ADDED = "User Added Successfully";
	
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
       
       String username = StringUtils.trimToEmpty(request.getParameter("username"));
       String position = StringUtils.trimToEmpty(request.getParameter("position"));
       String uuid = StringUtils.trimToEmpty(request.getParameter("uuid"));
       String accountuuid = StringUtils.trimToEmpty(request.getParameter("accountuuid"));
       
       
       System.out.println(username);
       System.out.println(position);
       System.out.println(uuid);
       System.out.println(accountuuid);
       
       if(StringUtils.isEmpty(username) || StringUtils.isEmpty(position)){
    	   session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_ERROR, ERROR_EMPTY_FIELDS); 
    	   response.sendRedirect("school/staff.jsp");  
       }else if(usresDAO.getUserByUsername(username, accountuuid) !=null){
    	   session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_ERROR, ERROR_USERNAME_EXIST); 
    	   response.sendRedirect("school/staff.jsp");  
       }else{
    	   
    	   user = (User) usresDAO.getUser(uuid);
    	   user.setUsername(username);
    	   user.setUserType(position);
    	   user.setUuid(uuid);
    	   user.setSchoolAccountUuid(accountuuid);
    	   
    	   usresDAO.editUser(user, uuid);
    	   
    	   session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_SUCCESS, SUCCESS_USER_ADDED); 
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