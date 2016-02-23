package com.yahoo.petermwenda83.server.servlet.school.staff;

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

import com.yahoo.petermwenda83.bean.staff.StaffDetails;
import com.yahoo.petermwenda83.persistence.staff.StaffDetailsDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;


public class FinderStaff extends HttpServlet{
	
	
	final String ERROR_STAFF_NOT_FOUND = " The staff number you provided was not found in the system.";
	final String STAFF_FIND_SUCCESS = "The staff was found , proceed";
    final String ERROR_NO_EMP_NO = "You didn't provide any staff  number?.";
    
    private static StaffDetailsDAO staffDetailsDAO;


	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       staffDetailsDAO = StaffDetailsDAO.getInstance();
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

	   HttpSession session = request.getSession(true);
       String employeeNumber = StringUtils.trimToEmpty(request.getParameter("employeeNumber"));
      
       Map<String, String> paramHash = new HashMap<>();   

       if(StringUtils.isBlank(employeeNumber)){
		     session.setAttribute(SessionConstants.STAFF_FIND_ERROR, ERROR_NO_EMP_NO); 
		   
	   }else if(staffDetailsDAO.getStaffDetailByemployeeNo(employeeNumber)==null){ 
		   session.setAttribute(SessionConstants.STAFF_FIND_ERROR, ERROR_STAFF_NOT_FOUND); 
		  
	   }else{
		   
		   StaffDetails staff = staffDetailsDAO.getStaffDetailByemployeeNo(employeeNumber);
		   paramHash.put("staffuuid", staff.getStaffUuid()); 
		   paramHash.put("staffNumber", staff.getEmployeeNo());
		   paramHash.put("firstname", staff.getFirstName()); 
		   paramHash.put("lastname", staff.getLastName()); 
		   paramHash.put("surname", staff.getSurname()); 
		   
		 
		   
	       session.setAttribute(SessionConstants.STAFF_FIND_SUCCESS, STAFF_FIND_SUCCESS);     
	   }
 
       session.setAttribute(SessionConstants.STAFF_PARAM, paramHash); 
       response.sendRedirect("teacherSubjects.jsp");  
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
private static final long serialVersionUID = 2758416139449181921L;

}
