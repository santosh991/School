package com.yahoo.petermwenda83.server.servlet.student;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.StudentPrimary;
import com.yahoo.petermwenda83.persistence.student.PrimaryDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

public class UpdateStudentBasic extends HttpServlet{
	/**
	 *  
	 */
	private static final long serialVersionUID = 2171267736101525967L;
	
	
	final String ERROR_EMPTY_ADM_NO = "Admission number can't be empty.";
	final String ERROR_EMPTY_FIRSTNAME = "Firstname can't be empty.";
	final String ERROR_EMPTY_LASTNAME = "Lastname can't be empty.";
	final String ERROR_EMPTY_SURNAME = "Surname can't be empty.";
	final String ERROR_EMPTY_GENDER = "Student gender can't be empty.";
	final String ERROR_EMPTY_DAY = "Student day of birth can't be empty.";
	final String ERROR_EMPTY_MONTH = "Student month of birth can't be empty.";
	final String ERROR_EMPTY_YEAR = "Student year of birth can't be empty.";
	final String ERROR_EMPTY_BCERT_NO = "Birth certificate number can't be empty.";
	final String ERROR_EMPTY_COUNTY = "County can't be empty.";
	final String ERROR_EMPTY_PRIMARY = "Primary school name can't be empty.";
	final String ERROR_EMPTY_INDEX_NO = "KCPE index number can't be empty.";
	final String ERROR_EMPTY_KCPE_YAER = "KCPE year can't be empty.";
	final String ERROR_EMPTY_KCPE_MARK = "KCPE mark  can't be empty.";
	final String UNEXPECTED_ERROR = "Unexpected error occured.";
	
	final String ERROR_ADMNO_EXIST = "Admission number already exist in the system.";
	final String STUDENT_UPDATE_ERROR = "An error occured while updating student.";
	final String STUDENT_UPDATE_SUCCESS = "Student updated successfully";
	final String STATUS_ACTIVE = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
	
	private static StudentDAO studentDAO;
	private static PrimaryDAO primaryDAO;
	


	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentDAO = StudentDAO.getInstance();
       primaryDAO = PrimaryDAO.getInstance();
       
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
     
       String admnumber = StringUtils.trimToEmpty(request.getParameter("admNo"));
       String firstname = StringUtils.trimToEmpty(request.getParameter("firstname"));
       String lastname = StringUtils.trimToEmpty(request.getParameter("lastname"));
       String surname = StringUtils.trimToEmpty(request.getParameter("surname"));
       String gender = StringUtils.trimToEmpty(request.getParameter("gender"));
       String dob = StringUtils.trimToEmpty(request.getParameter("dob"));
       String BcertNo = StringUtils.trimToEmpty(request.getParameter("BcertNo"));
       String County = StringUtils.trimToEmpty(request.getParameter("county"));
       String primary = StringUtils.trimToEmpty(request.getParameter("primary"));
       String indexno = StringUtils.trimToEmpty(request.getParameter("kcpeindex"));
       String kcpeaddYear = StringUtils.trimToEmpty(request.getParameter("kcpeyear"));
       String kcpemark = StringUtils.trimToEmpty(request.getParameter("kcpemark"));
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schoolUuid"));
       String studentUuid = StringUtils.trimToEmpty(request.getParameter("studentUuid"));
       String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
       
      
       
       if(StringUtils.isBlank(admnumber)){ 
		   session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_ADM_NO); 
		   
	   }else if(StringUtils.isBlank(firstname)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_FIRSTNAME); 
		     
	   }else if(StringUtils.isBlank(lastname)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_LASTNAME); 
			   
	   }else if(StringUtils.isBlank(surname)){ 
		   session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_SURNAME); 
		   
	   }else if(StringUtils.isBlank(gender)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_GENDER); 
		     
	   }else if(StringUtils.isBlank(dob)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_DAY); 
			   
	   }else if(StringUtils.isBlank(dob)){ 
		   session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_MONTH); 
		   
	   }else if(StringUtils.isBlank(dob)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_YEAR); 
		     
	   }else if(StringUtils.isBlank(BcertNo)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_BCERT_NO); 
		     
	   }else if(StringUtils.isBlank(County)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_COUNTY); 
		     
	   }else if(StringUtils.isBlank(primary)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_PRIMARY); 
		     
	   }else if(StringUtils.isBlank(indexno)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_INDEX_NO); 
		     
	   }else if(StringUtils.isBlank(kcpeaddYear)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_KCPE_YAER); 
		     
	   }else if(StringUtils.isBlank(kcpemark)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, ERROR_EMPTY_KCPE_MARK); 
		     
	   }else if(StringUtils.isBlank(schooluuid)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, UNEXPECTED_ERROR); 
		     
	   }else if(StringUtils.isBlank(staffUsername)){
		     session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, UNEXPECTED_ERROR); 
		     
	   }else{
		   
		   Student student = studentDAO.getStudentObjByadmNo(schooluuid, admnumber);
		   student.setSchoolAccountUuid(schooluuid); 
		   student.setStatusUuid(STATUS_ACTIVE); 
		   student.setAdmno(admnumber);
		   student.setFirstname(firstname);
		   student.setLastname(lastname);
		   student.setSurname(surname);
		   student.setGender(gender);
		   student.setdOB(dob); 
		   student.setBcertno(BcertNo);
		   student.setCounty(County); 
		   student.setSysUser(staffUsername); 
		   
		   StudentPrimary sprimary = primaryDAO.getPrimary(student.getUuid());
		   sprimary.setStudentUuid(student.getUuid());
		   sprimary.setSchoolname(primary);
		   sprimary.setIndex(indexno);
		   sprimary.setKcpemark(kcpemark);
		   sprimary.setKcpeyear(kcpeaddYear); 
		  
 		  
		   if(studentDAO.updateStudents(student) && primaryDAO.updatePrimary(sprimary)){  
			 
			   session.setAttribute(SessionConstants.STUDENT_UPDATE_SUCCESS, STUDENT_UPDATE_SUCCESS);  
		   }else{
			   session.setAttribute(SessionConstants.STUDENT_UPDATE_ERROR, STUDENT_UPDATE_ERROR);  
		   }
		  
		   
	   }
	  
       response.sendRedirect("studentIndex.jsp");  
	   return;
       
       
   }

   
   

@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doPost(request, response);
  }
}
