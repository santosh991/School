package com.yahoo.petermwenda83.server.servlet.promote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class ActivateByClass extends HttpServlet{
	
	
	final String ERROR = "We are Sorry, something went wrong , try again later";
	final String SUCCESS_STATUS_CHANGE = "Students activated successfully";
	final String ERROR_STATUS_CHANGE = "Students activation failed";
	final String EMPTY_FIELDS = "Empty fields not allowed";
	
	final String INACTIVE = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
	
	
	private static StudentDAO studentDAO;
	List<Student> studentlist = null;


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
	   
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String currentclassuuid = StringUtils.trimToEmpty(request.getParameter("currentclassuuid"));
   
      if(StringUtils.isBlank(currentclassuuid)){
    	  session.setAttribute(SessionConstants.STATUS_CHANGE_ERROR, EMPTY_FIELDS); 
       }
       studentlist = new ArrayList<>();
       studentlist = studentDAO.getAllStudents(schooluuid, currentclassuuid);
       for(Student stu : studentlist){
    	   Student student = new Student();
    	   student = studentDAO.getStudentByuuid(schooluuid, stu.getUuid());
    	   student.setStatusUuid(INACTIVE); 
    	   if(studentDAO.updateStudents(student)){
    		   //session response
    	       session.setAttribute(SessionConstants.STATUS_CHANGE_SUCCESS, SUCCESS_STATUS_CHANGE); 
    	   }else{
    		   session.setAttribute(SessionConstants.STATUS_CHANGE_ERROR, ERROR_STATUS_CHANGE);   
    	   }
         }
       
       
      
       response.sendRedirect("activateClass.jsp");  
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
