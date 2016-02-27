/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.money;

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

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.Deposit;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.PMoneyDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/** 
 * @author peter
 *
 */
public class PMDeposit extends HttpServlet{
	
	final String ERROR_AMOUNT_NOT_IN_RANGE = "You can only deposit Money within the range of KSH 100 - KSH 10,000";
	final String EMPTY_CREDENTIALS = "Looks like you didn't search for any student";
	final String ERROR_DEPOSIT_FAILED = "Something went wrong , transaction failed";
	final String SUCCESS_DEPOSIT_SUCCESSFUL = "Pocket money deposited successfully";
	final String NUMBER_FORMAT_ERROR = "Amount can only be Numeric";
	final String EMPTY_AMOUNT = "Amount cant be empty";
	
	 private static StudentDAO studentDAO;
	 private static PMoneyDAO pMoneyDAO;
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
       pMoneyDAO = PMoneyDAO.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String admissionNumber = StringUtils.trimToEmpty(request.getParameter("admnumber"));
       String schoolUuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String amount = StringUtils.trimToEmpty(request.getParameter("depositeAmount"));
       String systemuser = StringUtils.trimToEmpty(request.getParameter("systemuser"));
       
       Map<String, String> paramHash = new HashMap<>();   
       paramHash.put("amount", amount);
      
       System.out.println(amount); 
       
       if(StringUtils.isBlank(admissionNumber)){
    	   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, EMPTY_CREDENTIALS);
    	   
       }else if(StringUtils.isBlank(amount)){
    	   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, EMPTY_AMOUNT);
    	   
       }else if(!isNumericRange(amount)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, NUMBER_FORMAT_ERROR); 
			   
	   }else if(StringUtils.isBlank(schoolUuid)){
    	   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, EMPTY_CREDENTIALS);
    	   
       }else if(StringUtils.isBlank(systemuser)){
    	   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, EMPTY_CREDENTIALS);
    	   
       }else{
    	   
    	   Student student = new Student();
		   if(studentDAO.getStudentObjByadmNo(schoolUuid, admissionNumber) !=null){
			   student = studentDAO.getStudentObjByadmNo(schoolUuid, admissionNumber);
			   
		   }
		   examConfig = examConfigDAO.getExamConfig(schoolUuid);
		   Deposit d = new Deposit();
    	   d.setStudentUuid(student.getUuid());
    	   d.setSystemUser(systemuser);
    	   d.setTerm(examConfig.getTerm());
           d.setYear(examConfig.getYear());
    	   
    	   if(pMoneyDAO.addBalance(d, Double.parseDouble(amount))){ 
    		   session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, SUCCESS_DEPOSIT_SUCCESSFUL);
    	   }else{
    		   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_DEPOSIT_FAILED);
    	   }
    	 
       }
       session.setAttribute(SessionConstants.STUENT_POCKET_MONEY_PARAM, paramHash); 
       response.sendRedirect("pocketM.jsp");    
	   return;
   }
   
   

/**
 * @param amount
 * @return
 */
private boolean isNumericRange(String amount) {
	   boolean valid = true;
		String regex = "[0-9]+";
		if(amount.matches(regex)){ 
			valid = true;
		}else{
			valid = false;
		}
		
		return valid;
}



/**
 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doPost(request, response);
     }
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 847435422831970402L;
}
