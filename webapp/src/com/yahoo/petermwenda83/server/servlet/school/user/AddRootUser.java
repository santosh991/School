package com.yahoo.petermwenda83.server.servlet.school.user;

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

import com.yahoo.petermwenda83.bean.staff.Staff;
import com.yahoo.petermwenda83.persistence.staff.StaffDAO;
import com.yahoo.petermwenda83.server.session.AdminSessionConstants;

/**
 * @author peter
 * 
 */
public class AddRootUser extends HttpServlet{


	private static StaffDAO staffDAO;
    
    final String ERROR_EMPTY_ACCOUNT = "Please Select an Account.";
    final String ERROR_EMPTY_CATEGORY = "Please Select a Category.";
    final String ERROR_EMPTY_POSITION = "Please Select a Position";
    final String ERROR_EMPTY_USERNAME = "Username can't be empty";
    final String ERROR_EMPTY_PASSWORD = "Password cant be empty";
    
    final String ERROR_PRINCIPAL_NOT_ADDED = "Principal add error";
    final String SUCCESS_PRINCIPAL_ADDED = "Principal Added Successfully";
	
	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       staffDAO = StaffDAO.getInstance();
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String accountUuid = StringUtils.trimToEmpty(request.getParameter("accountUuid"));
       String Category = StringUtils.trimToEmpty(request.getParameter("Category"));
       String PositionUuid = StringUtils.trimToEmpty(request.getParameter("PositionUuid"));
       String principalusername = StringUtils.trimToEmpty(request.getParameter("principalusername"));
       String principalpassword = StringUtils.trimToEmpty(request.getParameter("principalpassword"));
       
    // This is used to store parameter names and values from the form.
	   	Map<String, String> paramHash = new HashMap<>();    	
	   	paramHash.put("principalusername", principalusername);
	   	paramHash.put("principalpassword", principalpassword);
	   
       if(StringUtils.isEmpty(accountUuid)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_ACCOUNT); 
    	   
       }else if(StringUtils.isEmpty(Category)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_CATEGORY); 
    	   
       }else if(StringUtils.isEmpty(PositionUuid)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_POSITION); 
    	   
       }else if(StringUtils.isEmpty(principalusername)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_USERNAME); 
    	   
       }else if(StringUtils.isEmpty(principalpassword)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_PASSWORD); 
    	   
       }else{
    	   
    	   Staff staff = new Staff();
    	   staff.setSchoolAccountUuid(accountUuid); 
    	   staff.setCategory(Category);
    	   staff.setPositionUuid(PositionUuid); 
    	   staff.setUserName(principalusername); 
    	   staff.setPassword(principalpassword); 
    	   
    	   if(staffDAO.putStaff(staff)){
    		   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_SUCCESS, SUCCESS_PRINCIPAL_ADDED);  
    	   }else{
    		   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_PRINCIPAL_NOT_ADDED);  
    	   }
    	   
    	   
       }
       
       
       session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_PARAM, paramHash);
       response.sendRedirect("rootUser.jsp");  
	   return;
       
       
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
