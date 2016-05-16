/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.money;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.ClosingBalance;
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMonies;
import com.yahoo.petermwenda83.bean.schoolaccount.SmsSend;
import com.yahoo.petermwenda83.bean.smsapi.AfricasTalking;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.guardian.StudentParent;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.guardian.ParentsDAO;
import com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO;
import com.yahoo.petermwenda83.persistence.money.StudentFeeDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.StudentOtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.schoolaccount.SmsSendDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.servlet.sms.send.AfricasTalkingGateway;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/**  
 * @author peter
 *
 */
public class AddFeeDetails extends HttpServlet{
	
	
	 final String ERROR_AMOUNT_NOT_IN_RANGE = "Fee can only be within the range of KSH 100 - KSH 100,000";
	 final String ERROR_AMOUNT_NUMERIC = "Amount can only be numeric";
	 final String ERROR_NO_SLIPNIMBER = "You didn't provide any admission  number?.";
	 final String ERROR_SLIP_NO_EXIST = "The bank slip has already been registered.";
	 final String ERROR_AMOUNT_NOT_ADDED = "Something went wrong, ammount not added.";
	 final String UNEXPECTED_ERROR = "Looks like you didn'd serch for a student.";
	 final String ERROR_NO_AMOUNT = "You didn't provide any admission  number?.";
	 final String SUCCESS_AMOUNT_NOT_ADDED = "Fee detils successfully added.";
	 final String NUMBER_FORMAT_ERROR = "Amount can only be Numeric";
	 
	 HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	 HashMap<String, String>genderfinderHash = new HashMap<String, String>();
	 HashMap<String, String> studNameHash = new HashMap<String, String>();
	 HashMap<String, String> roomHash = new HashMap<String, String>();
	 
	
	 
	 private static StudentOtherMoniesDAO studentOtherMoniesDAO;
	 private static StudentFeeDAO studentFeeDAO;
	 private static ExamConfigDAO examConfigDAO;
	 private static TermFeeDAO termFeeDAO;
	 private static StudentDAO studentDAO;
	 private static ParentsDAO parentsDAO;
	 private static SmsSendDAO smsSendDAO;
	 private static ClosingBalanceDAO closingBalanceDAO;
	 
	 Locale locale = new Locale("en","KE"); 
     NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
	

	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentOtherMoniesDAO = StudentOtherMoniesDAO.getInstance();
       studentFeeDAO = StudentFeeDAO.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
       termFeeDAO = TermFeeDAO.getInstance();
       studentDAO = StudentDAO.getInstance();
       parentsDAO = ParentsDAO.getInstance();
       smsSendDAO = SmsSendDAO.getInstance();
       closingBalanceDAO = ClosingBalanceDAO.getInstance();
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
			   
	   }else if(!isNumeric(Amountpaid)){
    	   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_AMOUNT_NUMERIC); 
    	   
       }else if(!lengthValid(Amountpaid)){
	 	   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_AMOUNT_NOT_IN_RANGE); 
		   
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
		   
		   
		   double amountTopay =  Double.parseDouble(Amountpaid);
		   String feebalance = "";
		  
		   StudentFee studentFee = new StudentFee();
		   studentFee.setSchoolAccountUuid(schooluuid);
		   studentFee.setStudentUuid(studentuuid);
		   studentFee.setTransactionID(slipNumber.toUpperCase()); 
		   studentFee.setAmountPaid(amountTopay);  
		   studentFee.setTerm(examConfig.getTerm());
		   studentFee.setYear(examConfig.getYear());
		   studentFee.setSystemUser(systemuser);
		   
		   if(studentFeeDAO.putStudentFee(studentFee)){
			   session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, SUCCESS_AMOUNT_NOT_ADDED);
		   }
			   else{
				   session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, UNEXPECTED_ERROR);
			   }
			 
			   
			   
			   
			   
		   //get parent contact and name
		   String phone = "";
		   String formatedphone = "";
		   String realphone = "";
		   String parentname = "";
		   StudentParent studentParent = new StudentParent();
		   if(parentsDAO.getParent(studentuuid) !=null){
			   studentParent = parentsDAO.getParent(studentuuid);
			   parentname = studentParent.getFathername();
			   phone = studentParent.getFatherphone();
			   formatedphone = phone.replaceFirst("^0+(?!$)", "");
			   realphone = "+254"+formatedphone;
		   }
		  
		   
		   //get student name
		   Student stuudent = new Student();
		   if(studentDAO.getStudentByuuid(schooluuid, studentuuid) !=null){
			   stuudent = studentDAO.getStudentByuuid(schooluuid, studentuuid);
		   }
		  
		   String genderfinder = "";
		   String gender = "";
		   
		   if(stuudent != null){
				studentAdmNoHash.put(stuudent.getUuid(),stuudent.getAdmno()); 
				String firstNameLowecase = stuudent.getFirstname().toLowerCase();
				String lastNameLowecase = stuudent.getLastname().toLowerCase();
				//String surnameLowecase = stuudent.getSurname().toLowerCase();
				String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
				String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
				//String formatedsurname = surnameLowecase.substring(0,1).toUpperCase()+surnameLowecase.substring(1);
				studNameHash.put(stuudent.getUuid(),formatedFirstname + " " + formatedLastname +"\n"); 
				//firstnameHash.put(stuudent.getUuid(), formatedFirstname);
				gender = stuudent.getGender();
				if(StringUtils.equalsIgnoreCase(gender, "Male")) {
					genderfinder = "son";
				}else{
					genderfinder = "daughter";
				}
				genderfinderHash.put(stuudent.getUuid(), genderfinder);
			}
		   
		   
		   
		  
			   
			   //get new fee balance
			 //Get payments for the 'current year' and 'current term' 
			    double totalpaid = 0;
				List<StudentFee> feelist = new ArrayList<>();               
				feelist = studentFeeDAO.getStudentFeeByStudentUuidList(schooluuid,studentuuid,examConfig.getTerm(),examConfig.getYear());
				if(feelist !=null){
					for(StudentFee fee : feelist){
                       totalpaid +=fee.getAmountPaid();
					  }
					}
				
				
				// we should find previous term balance or over payments
				String previuosyear = "";
				String currentyear = examConfig.getYear();
				int currentyearint = Integer.parseInt(currentyear);
				int previousyearint = 0;

				String currenttermStr = examConfig.getTerm();
				int currenttermint = Integer.parseInt(currenttermStr);// can either be 1, 2, or 3
				int previousterm = currenttermint - 1;// if c = 3 , p = 2 // if c = 2 , p = 1 // if c = 1 p = 3
				if(previousterm == 0){
					previousterm = 3;
					previousyearint = currentyearint - 1;
					previuosyear = Integer.toString(previousyearint);
				}else{
					previuosyear = examConfig.getYear();
				}
				String previoustermStr = Integer.toString(previousterm);
				//now we have the previous term , we get the term amount (previous)
				//from closing balance, we add the amount, negative balance means dues, positive balance means over pay

				double prevtermbalance = 0;
				ClosingBalance closingBalance = new ClosingBalance();
				if(closingBalanceDAO.getClosingBalanceByStudentId(schooluuid, studentuuid, previoustermStr, previuosyear) !=null){
					closingBalance = closingBalanceDAO.getClosingBalanceByStudentId(schooluuid, studentuuid, previoustermStr, previuosyear);

				}

				prevtermbalance = closingBalance.getClosingAmount();
				
				
				//end
				TermFee termfee = new TermFee();
				if(termFeeDAO.getTermFee(schooluuid,examConfig.getTerm()) !=null){
					
					double other_m_amount = 0;
					double other_m_totals = 0;
					
					termfee = termFeeDAO.getTermFee(schooluuid,examConfig.getTerm());
					
					List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
					stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(studentuuid,examConfig.getTerm(),examConfig.getYear());
					if(stuOthermoniList !=null){
						
						for(StudentOtherMonies som  : stuOthermoniList){
							other_m_amount = som.getAmountPiad();
							other_m_totals +=other_m_amount;
						}
					}
					
					
				feebalance =	nf.format(termfee.getTermAmount() -prevtermbalance -totalpaid + other_m_totals);
					
					
					
				}
			   
			   
			   //send message
			   AfricasTalking africasTalking = new AfricasTalking();
		       // Specify your login credentials
		       String username = africasTalking.getUsername();
		       String apiKey   = africasTalking.getApiKey();
		       String message = "";
		       message = "HI " + parentname + ", your " + genderfinderHash.get(studentuuid)+ " " + studNameHash.get(studentuuid) + " Adm.No " + studentAdmNoHash.get(studentuuid) + "  has paid fee of amount " + nf.format(amountTopay) + " Term " + examConfig.getTerm() + " Year " + examConfig.getYear() + "  , Fee balance is " + feebalance;
		       
		       
		       africasTalking.setMessage(message); 
		       africasTalking.setRecipients(realphone); 
		       // Create a new instance of our awesome gateway class
		      AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
		       //	System.out.println("message ="+message);
		       // Thats it, hit send and we'll take care of the rest. Any errors will
		       // be captured in the Exception class below
		       
		       //save to database
               String thestatus ="";
               String thenumber ="";
               String themessage ="";
               String thecost ="";
              
               //System.out.println(message.replaceAll("[\r\n]+", " "));  
               
               SmsSend smsSend = new SmsSend();
               smsSend.setStatus("failed");
               smsSend.setPhoneNo(realphone);
               smsSend.setMessageId(message.replaceAll("[\r\n]+", " "));
               smsSend.setCost("1");
               smsSendDAO.putSmsSend(smsSend);
            	
		       
		      try {
		          JSONArray results = gateway.sendMessage(africasTalking.getRecipients(), africasTalking.getMessage());
		          for( int i = 0; i < results.length(); ++i ) {
		                JSONObject result = results.getJSONObject(i);
		               
		                thestatus = result.getString("status");
		                thenumber = result.getString("number");
		                themessage = message;
		                thecost = result.getString("cost");
		               
		                SmsSend smsSend2 = smsSendDAO.getSmsSend(smsSend.getUuid());
		                smsSend2.setStatus(thestatus);
		                smsSend2.setPhoneNo(thenumber);
		                smsSend2.setMessageId(themessage.replaceAll("[\r\n]+", " "));
		                smsSend2.setCost(thecost);
		                smsSendDAO.updateSmsSend(smsSend2); 
		                
		         }
		         
		     }
		     
		     catch (Exception e) {
		    	e.printStackTrace(); 
		      }
		    
		   }
       
       session.setAttribute(SessionConstants.STUENT_FEE_ADD_PARAM, addparamHash); 
       response.sendRedirect("addFee.jsp");  
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
 * @param mystring
 * @return
 */
private static boolean lengthValid(String mystring) {
	boolean isvalid = true;
	int length = 0;
	length = mystring.length();
	//System.out.println("lenght = " + length);
	if(length<3 ||length>6){
		isvalid = false;
	}
	return isvalid;
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
