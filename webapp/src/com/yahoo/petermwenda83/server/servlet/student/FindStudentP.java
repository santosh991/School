/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.student;

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

import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/**
 * @author peter
 *
 */
public class FindStudentP extends HttpServlet{
	
	/**
	 * 
	 */
	
	final String ERROR_STUDENT_NOT_FOUND = " The admission number you provided was not found in the system.";
	final String STUDENT_FIND_SUCCESS = "The student was found , proceed";
    final String ERROR_NO_ADMNO = "You didn't provide any admission  number?.";
    
	
	private static StudentDAO studentDAO;


	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentDAO = StudentDAO.getInstance();
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       String admissionNumber = StringUtils.trimToEmpty(request.getParameter("AdmNo"));
       String schoolUuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       Map<String, String> paramHash = new HashMap<>();   
       
       if(StringUtils.isBlank(admissionNumber)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_NO_ADMNO); 
		   
	   }else if(studentDAO.getStudentByadmNo(schoolUuid,admissionNumber)==null){ 
		   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_STUDENT_NOT_FOUND); 
		  
	   }else{
		   
		   Student student = studentDAO.getStudentByadmNo(schoolUuid, admissionNumber);
		   paramHash.put("studentuuid", student.getUuid()); 
		   paramHash.put("admNumber", student.getAdmno());
		   paramHash.put("firstname", student.getFirstname()); 
		   paramHash.put("lastname", student.getLastname()); 
		   paramHash.put("surname", student.getSurname()); 
		   
		 
		   
	       session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, STUDENT_FIND_SUCCESS);     
	   }
       session.setAttribute(SessionConstants.PARENT_PARAM, paramHash); 
       response.sendRedirect("addParent.jsp");  
	   return; 
   }

   
   

/**
 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doPost(request, response);
  }

private static final long serialVersionUID = -657783993417471463L;
}
