/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.StudentPrimary;
import com.yahoo.petermwenda83.bean.student.StudentSubject;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.student.PrimaryDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;


/**
 * @author peter
 *
 */
public class AddStudentBacic extends HttpServlet{
	
	
	final String ERROR_EMPTY_CLASSROOM = "Select a classroom.";
	final String ERROR_EMPTY_ADM_NO = "Admission number can't be empty.";
	final String ERROR_EMPTY_FIRSTNAME = "Firstname can't be empty.";
	final String ERROR_EMPTY_LASTNAME = "Lastname can't be empty.";
	final String ERROR_EMPTY_SURNAME = "surname can't be empty.";
	final String ERROR_EMPTY_GENDER = "Select student gender.";
	final String ERROR_EMPTY_DAY = "Select student day of birth.";
	final String ERROR_EMPTY_MONTH = "Select student month of birth.";
	final String ERROR_EMPTY_YEAR = "Select student year of birth.";
	final String ERROR_EMPTY_BCERT_NO = "Birth certificate number can't be empty.";
	final String ERROR_EMPTY_COUNTY = "County can't be empty.";
	final String ERROR_EMPTY_PRIMARY = "Primary school name can't be empty.";
	final String ERROR_EMPTY_INDEX_NO = "KCPE index number can't be empty.";
	final String ERROR_EMPTY_KCPE_YAER = "Select KCPE year.";
	final String ERROR_EMPTY_KCPE_MARK = "KCPE mark  can't be empty.";
	final String UNEXPECTED_ERROR = "Unexpected error occured.";
	
	final String ERROR_ADMNO_EXIST = "Admission number already exist in the system.";
	final String STUDENT_ADD_ERROR = "An error occured while adding student.";
	final String STUDENT_ADD_SUCCESS = "Student added successfully";
	final String STATUS_ACTIVE = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
	
	final String NAME_ERROR = "Name format error/incorrent lenght.";
	
	private static StudentDAO studentDAO;
	private static PrimaryDAO primaryDAO;
	private static StudentSubjectDAO studentSubjectDAO;
	private static SubjectDAO subjectDAO;
	private static ExamConfigDAO examConfigDAO;
	ExamConfig examConfig;
	

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
       studentSubjectDAO = StudentSubjectDAO.getInstance();
       subjectDAO = SubjectDAO.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String BcertNo = "not-set";
       String County = "not-set";
       String primary = "not-set";
       String indexno = "not-set";
       String kcpeaddYear =  "not-set";
       String kcpemark = "200";
       
       String classroomUuid = StringUtils.trimToEmpty(request.getParameter("classroomUuid"));
       String admnumber = StringUtils.trimToEmpty(request.getParameter("admNO"));
       String firstname = StringUtils.trimToEmpty(request.getParameter("firstname"));
       String lastname = StringUtils.trimToEmpty(request.getParameter("lastname"));
       String surname = StringUtils.trimToEmpty(request.getParameter("surname"));
       String gender = StringUtils.trimToEmpty(request.getParameter("gender"));
       String dobaddDay = StringUtils.trimToEmpty(request.getParameter("dobaddDay"));
       String dobaddMonth = StringUtils.trimToEmpty(request.getParameter("dobaddMonth"));
       String dobaddYear = StringUtils.trimToEmpty(request.getParameter("dobaddYear"));
       BcertNo = StringUtils.trimToEmpty(request.getParameter("BcertNo"));
       County = StringUtils.trimToEmpty(request.getParameter("County"));
       primary = StringUtils.trimToEmpty(request.getParameter("primary"));
       indexno = StringUtils.trimToEmpty(request.getParameter("indexno"));
       kcpeaddYear = StringUtils.trimToEmpty(request.getParameter("kcpeaddYear"));
       kcpemark = StringUtils.trimToEmpty(request.getParameter("kcpemark"));
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String systemuser = StringUtils.trimToEmpty(request.getParameter("systemuser"));
       
       Map<String, String> studentParamHash = new HashMap<>(); 
       studentParamHash.put("admnumber", admnumber); 
       studentParamHash.put("firstname", firstname);
       studentParamHash.put("lastname", lastname); 
       studentParamHash.put("surname", surname); 
       studentParamHash.put("BcertNo", BcertNo); 
       studentParamHash.put("County", County); 
       studentParamHash.put("primary", primary);
       studentParamHash.put("indexno", indexno); 
       studentParamHash.put("kcpemark", kcpemark); 
      
       
       if(StringUtils.isBlank(classroomUuid)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_CLASSROOM); 
		   
	   }else if(StringUtils.isBlank(admnumber)){ 
		   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_ADM_NO); 
		   
	   }else if(studentDAO.getStudentObjByadmNo(schooluuid, admnumber) !=null){ 
		   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_ADMNO_EXIST); 
		   
	   }else if(StringUtils.isBlank(firstname)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_FIRSTNAME); 
		     
	   }else if(!lengthValid(firstname)){
	 	   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, NAME_ERROR); 
		   
	   }else if(StringUtils.isBlank(lastname)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_LASTNAME); 
			   
	   }else if(!lengthValid(lastname)){
	 	   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, NAME_ERROR); 
		   
	   }else if(StringUtils.isBlank(surname)){ 
		   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_SURNAME); 
		   
	   }else if(!lengthValid(surname)){
	 	   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, NAME_ERROR); 
		   
	   }else if(StringUtils.isBlank(gender)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_GENDER); 
		     
	   }else if(StringUtils.isBlank(dobaddDay)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_DAY); 
			   
	   }else if(StringUtils.isBlank(dobaddMonth)){ 
		   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_MONTH); 
		   
	   }else if(StringUtils.isBlank(dobaddYear)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_YEAR); 
		     
	   }/*else if(StringUtils.isBlank(BcertNo)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_BCERT_NO); 
		     
	   }else if(StringUtils.isBlank(County)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_COUNTY); 
		     
	   }else if(StringUtils.isBlank(primary)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_PRIMARY); 
		     
	   }else if(StringUtils.isBlank(indexno)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_INDEX_NO); 
		     
	   }else if(StringUtils.isBlank(kcpeaddYear)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_KCPE_YAER); 
		     
	   }else if(StringUtils.isBlank(kcpemark)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, ERROR_EMPTY_KCPE_MARK); 
		     
	   }*/else if(StringUtils.isBlank(schooluuid)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, UNEXPECTED_ERROR); 
		     
	   }else if(StringUtils.isBlank(systemuser)){
		     session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, UNEXPECTED_ERROR); 
		     
	   }else{
		   
		   BcertNo = "not-set";
	       County = "not-set";
	       primary = "not-set";
	       indexno = "not-set";
	       kcpeaddYear =  "not-set";
	       kcpemark = "200";
		   
		   examConfig = new ExamConfig();
			if(examConfigDAO.getExamConfig(schooluuid) !=null){
				examConfig = examConfigDAO.getExamConfig(schooluuid);
			}

		   
		   Student student = new Student();
		   student.setSchoolAccountUuid(schooluuid); 
		   student.setStatusUuid(STATUS_ACTIVE); 
		   student.setClassRoomUuid(classroomUuid); 
		   student.setAdmno(admnumber);
		   student.setFirstname(firstname);
		   student.setLastname(lastname);
		   student.setSurname(surname);
		   student.setGender(gender);
		   student.setdOB(dobaddDay+"/"+dobaddMonth+"/"+dobaddYear);
		   student.setBcertno(BcertNo);
		   student.setCounty(County); 
		   student.setRegTerm(examConfig.getTerm());  
		   student.setSysUser(systemuser); 
		   
		   StudentPrimary sprimary = new StudentPrimary();
		   sprimary.setStudentUuid(student.getUuid());
		   sprimary.setSchoolname(primary);
		   sprimary.setIndex(indexno);
		   sprimary.setKcpemark(kcpemark);
		   sprimary.setKcpeyear(kcpeaddYear); 
		   
		   
		  
		   List<Subject> subjectList = new ArrayList<>();
		   subjectList = subjectDAO.getAllSubjects();
		  
 		  
		   if(studentDAO.putStudents(student) && primaryDAO.putPrimary(sprimary)){ 
			   
			    for(Subject sub : subjectList){
			       StudentSubject sb = new StudentSubject(); 
				   sb.setSubjectUuid(sub.getUuid()); 
				   sb.setStudentUuid(student.getUuid());
				   sb.setSysUser(systemuser);
				   studentSubjectDAO.putstudentSub(sb); 
				  
			     }
			    
			   studentParamHash.clear();
			   session.setAttribute(SessionConstants.STUDENT_ADD_SUCCESS, STUDENT_ADD_SUCCESS);  
		   }else{
			   session.setAttribute(SessionConstants.STUDENT_ADD_ERROR, STUDENT_ADD_ERROR);  
		   }
		  
		   
	   }
	   session.setAttribute(SessionConstants.STUDENT_PARAM, studentParamHash); 
       response.sendRedirect("addStudent.jsp");  
	   return;
       
       
   }
   
   
   /**
    * @param str
    * @return
    */
   public static boolean isNumeric(String str) {  
	   try  
	   {  
		   double d = Double.parseDouble(str);  
	   }  
	   catch(NumberFormatException nfe)  
	   {  
		   return false;  
	   }  
	   return true;  
   }

   /**
    * @param mystring
    * @return
    */
   private static boolean lengthValid(String mystring) {
	   boolean isvalid = true;
	   int length = 0;
	   length = mystring.length();
	   if(length<3){
		   isvalid = false;
	   }
	   return isvalid;
   }


   
   

@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doPost(request, response);
  }

/**
 * 
 */
private static final long serialVersionUID = -7442773183552364624L;
}
