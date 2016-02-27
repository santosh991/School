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
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.StudentFeeDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/**  
 * @author peter
 *
 */
public class AddFeeDetails extends HttpServlet{
	
	
	 final String ERROR_AMOUNT_NOT_IN_RANGE = "Fee can only be within the range of KSH 100 - KSH 100,000";
	 final String ERROR_NO_SLIPNIMBER = "You didn't provide any admission  number?.";
	 final String ERROR_SLIP_NO_EXIST = "The bank slip has already been registered.";
	 final String ERROR_AMOUNT_NOT_ADDED = "Something went wrong, ammount not added.";
	 final String UNEXPECTED_ERROR = "Looks like you didn'd serch for a student.";
	 final String ERROR_NO_AMOUNT = "You didn't provide any admission  number?.";
	 final String SUCCESS_AMOUNT_NOT_ADDED = "Fee detils successfully added.";
	 final String NUMBER_FORMAT_ERROR = "Amount can only be Numeric";
	 
	
	 
	 private static StudentFeeDAO studentFeeDAO;
	 private static ExamConfigDAO examConfigDAO;

	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentFeeDAO = StudentFeeDAO.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String Amountpaid = StringUtils.trimToEmpty(request.getParameter("Amountpaid"));
       String slipNumber = StringUtils.trimToEmpty(request.getParameter("slipNumber"));
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String systemuser = StringUtils.trimToEmpty(request.getParameter("systemuser"));
       String studentuuid = StringUtils.trimToEmpty(request.getParameter("studentuuid"));
       
       Map<String, String> addparamHash = new HashMap<>(); 
       addparamHash.put("Amountpaid", Amountpaid);
       addparamHash.put("slipNumber", slipNumber);
       
      
       
       if(StringUtils.isBlank(Amountpaid)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_NO_AMOUNT); 
		   
	   }else if(!isNumericRange(Amountpaid)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, NUMBER_FORMAT_ERROR); 
			   
	   }else if(StringUtils.isBlank(slipNumber)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_NO_SLIPNIMBER); 
			   
	   }
	   else if(StringUtils.isBlank(schooluuid)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, UNEXPECTED_ERROR); 
			   
	   }
	   else if(StringUtils.isBlank(studentuuid)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, UNEXPECTED_ERROR); 
			   
	   }
	   else if(StringUtils.isBlank(systemuser)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, UNEXPECTED_ERROR); 
			   
	   }else if(studentFeeDAO.getStudentFeeByTransactionId(schooluuid, slipNumber) !=null){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_SLIP_NO_EXIST); 
			   
	   }else{
		   
		   ExamConfig examConfig = new ExamConfig();
		   if(examConfigDAO.getExamConfig(schooluuid) !=null){
			   examConfig = examConfigDAO.getExamConfig(schooluuid);
		   }
		   
		   StudentFee studentFee = new StudentFee();
		   studentFee.setSchoolAccountUuid(schooluuid);
		   studentFee.setStudentUuid(studentuuid);
		   studentFee.setTransactionID(slipNumber);
		   studentFee.setAmountPaid(Double.parseDouble(Amountpaid)); 
		   studentFee.setTerm(examConfig.getTerm());
		   studentFee.setYear(examConfig.getYear());
		   studentFee.setSystemUser(systemuser);
		   
		   if(studentFeeDAO.putStudentFee(studentFee)){
			   session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, SUCCESS_AMOUNT_NOT_ADDED);
		   }else{
			   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, UNEXPECTED_ERROR);
		   }
		 
	   }
       
       session.setAttribute(SessionConstants.STUENT_FEE_ADD_PARAM, addparamHash); 
       response.sendRedirect("addFee.jsp");  
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
	 private static final long serialVersionUID = 2453548782759986198L;

}
