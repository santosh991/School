package com.yahoo.petermwenda83.server.servlet.sms.send;

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
import org.json.JSONArray;
import org.json.JSONObject;

import com.yahoo.petermwenda83.bean.schoolaccount.SmsSend;
import com.yahoo.petermwenda83.bean.smsapi.AfricasTalking;
import com.yahoo.petermwenda83.bean.staff.Staff;
import com.yahoo.petermwenda83.bean.staff.StaffDetails;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.guardian.StudentParent;
import com.yahoo.petermwenda83.persistence.guardian.ParentsDAO;
import com.yahoo.petermwenda83.persistence.schoolaccount.SmsSendDAO;
import com.yahoo.petermwenda83.persistence.staff.StaffDAO;
import com.yahoo.petermwenda83.persistence.staff.StaffDetailsDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/**
 * @author peter
 *
 */
public class SendSMS extends HttpServlet{
	
	
	final String SMS_SEND_SUCCESS = "SMS was sent Successfully.";
	final String SMS_SEND_ERROR = "You can't send a blank message.";
	
	final String STATUS_ACTIVE = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
	
	final String DESTINATION_PARENTS = "Parents";
	final String DESTINATION_TEACHING_STAFF = "Teaching Staff";
	final String DESTINATION_NON_TEACHING_STAFF = "Non Teaching Staff";
	
	private static ParentsDAO parentsDAO;
	
	private static StudentDAO studentDAO;
	private static SmsSendDAO smsSendDAO;
	private static StaffDAO staffDAO;
	private static StaffDetailsDAO staffDetailsDAO;
	
	String message = "";
	
	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       parentsDAO = ParentsDAO.getInstance();
       studentDAO = StudentDAO.getInstance();
       staffDAO = StaffDAO.getInstance();
       smsSendDAO = SmsSendDAO.getInstance();
       staffDetailsDAO = StaffDetailsDAO.getInstance();
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {

	   HttpSession session = request.getSession(true);

	   String destination = StringUtils.trimToEmpty(request.getParameter("destination"));//Parents,Teaching Staff,Non Teaching Staff
	   message = StringUtils.trimToEmpty(request.getParameter("smsText"));
	   String schoolaccountUuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));

	   if(!StringUtils.isBlank(message)){
		   //common
		   AfricasTalking africasTalking = new AfricasTalking();
		   String username = africasTalking.getUsername();
		   String apiKey   = africasTalking.getApiKey();
		   //end common

		   if(StringUtils.equals(destination, DESTINATION_PARENTS)){

			   List<StudentParent> parentList = new ArrayList<StudentParent>();
			   List<Student> studentList = new ArrayList<Student>();
			   //get all students for a particular school
			   studentList = studentDAO.getAllStudentList(schoolaccountUuid);//STATUS_ACTIVE
			   String studentuuid = "";
			   for(Student st : studentList){
				   //get student id where status is active
				   if(StringUtils.equals(st.getStatusUuid(), STATUS_ACTIVE)){
				   studentuuid = st.getUuid();
				  
				   String parentphone = "";
				   String formatedparentphone = "";
				   String realParentphone = "";
				   //list of parents for active students 
				   parentList = parentsDAO.getParentListByStudent(studentuuid); 
				   //int pcount = 1;
				   for(StudentParent sp : parentList){
					   //get parents phone and name
					   parentphone = sp.getFatherphone();
					   formatedparentphone = parentphone.replaceFirst("^0+(?!$)", "");
					   realParentphone = "+254"+formatedparentphone;
					   //message
					    africasTalking.setMessage(message); 
						africasTalking.setRecipients(realParentphone); 
						AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
					   //send message to parent now
						//save to database
						SmsSend smsSend = new SmsSend();
						if(realParentphone !=null && message.replaceAll("[\r\n]+", " ") !=null){
						smsSend.setStatus("failed");
						smsSend.setPhoneNo(realParentphone);
						smsSend.setMessageId(message.replaceAll("[\r\n]+", " "));
						smsSend.setCost("1");
						smsSendDAO.putSmsSend(smsSend);
						}
						sendSmS(gateway,africasTalking,smsSend); 
						
					   session.setAttribute(SessionConstants.SMS_SEND_SUCCESS, SMS_SEND_SUCCESS); 
					   
					  // pcount++;
				     }
				   }
				 }
		   }else if(StringUtils.equals(destination, DESTINATION_TEACHING_STAFF)){
			   String category = "";
			   String TstaffPhone = "";
			   String formatedTstaffPhone = "";
			   String realTstaffPhone = "";
			   List<Staff> staffList = new ArrayList<Staff>();
			   staffList = staffDAO.getStaffList(schoolaccountUuid);
			  // int count1 = 1;
			   for(Staff stf : staffList){
				   category = stf.getCategory();
				  //Teaching staff
				   if(StringUtils.equals(category, "Teaching")){
				    //Non-Teaching staff
				    StaffDetails staffDetail = staffDetailsDAO.getStaffDetail(stf.getUuid()); 
				    TstaffPhone = staffDetail.getPhone();
				    formatedTstaffPhone = TstaffPhone.replaceFirst("^0+(?!$)", "");
				    realTstaffPhone = "+254"+formatedTstaffPhone;
				  //message
				    africasTalking.setMessage(message); 
					africasTalking.setRecipients(realTstaffPhone); 
					AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
				   //send message to parent now
					//save to database
					SmsSend smsSend = new SmsSend();
					if(realTstaffPhone !=null && message.replaceAll("[\r\n]+", " ") !=null){
					smsSend.setStatus("failed");
					smsSend.setPhoneNo(realTstaffPhone);
					smsSend.setMessageId(message.replaceAll("[\r\n]+", " "));
					smsSend.setCost("1");
					smsSendDAO.putSmsSend(smsSend);
					}
					sendSmS(gateway,africasTalking,smsSend); 
					
					  
					   
					   session.setAttribute(SessionConstants.SMS_SEND_SUCCESS, SMS_SEND_SUCCESS); 
					   //count1++;
				   }
				  
			   }

			   
		   } else if(StringUtils.equals(destination, DESTINATION_NON_TEACHING_STAFF)){
			   String category = "";
			   String NTstaffPhone = "";
			   String formatedNTstaffPhone = "";
			   String realNTstaffPhone = "";
			   List<Staff> staffList = new ArrayList<Staff>();
			   staffList = staffDAO.getStaffList(schoolaccountUuid);
			  // int count2 = 1;
			   for(Staff stf : staffList){
				   category = stf.getCategory();
				  //Teaching staff
				   if(StringUtils.equals(category, "Non-Teaching")){
				  //Non-Teaching staff
					    StaffDetails staffDetail = staffDetailsDAO.getStaffDetail(stf.getUuid()); 
					    NTstaffPhone = staffDetail.getPhone();
					    formatedNTstaffPhone = NTstaffPhone.replaceFirst("^0+(?!$)", "");
					    realNTstaffPhone = "+254"+formatedNTstaffPhone;
					   //message
					    africasTalking.setMessage(message); 
						africasTalking.setRecipients(realNTstaffPhone); 
						AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
					   //send message to parent now
						//save to database
						SmsSend smsSend = new SmsSend();
						if(realNTstaffPhone !=null && message.replaceAll("[\r\n]+", " ") !=null){
						smsSend.setStatus("failed");
						smsSend.setPhoneNo(realNTstaffPhone);
						smsSend.setMessageId(message.replaceAll("[\r\n]+", " "));
						smsSend.setCost("1");
						smsSendDAO.putSmsSend(smsSend);
						}
						sendSmS(gateway,africasTalking,smsSend); 
						
					   
					   session.setAttribute(SessionConstants.SMS_SEND_SUCCESS, SMS_SEND_SUCCESS); 
					  // count2++;
				   }
				  
			   }

		   }
			   

	   }else{//end if message is null
		   //session - error message
		   session.setAttribute(SessionConstants.SMS_SEND_ERROR, SMS_SEND_ERROR); 
	   }

	   response.sendRedirect("sendsms.jsp");  
	   return; 

   }
   
   
   

   /**
    * @param gateway
 * @param africasTalking 
 * @param smsSend 
    */
   private void sendSmS(AfricasTalkingGateway gateway, AfricasTalking africasTalking, SmsSend smsSend) {
	    String thestatus ="";
		String thenumber ="";
		String themessage ="";
		String thecost ="";
	   try {
			JSONArray results = gateway.sendMessage(africasTalking.getRecipients(), africasTalking.getMessage());
			for( int i = 0; i < results.length(); ++i ) {
				JSONObject result = results.getJSONObject(i);
			
				thestatus = result.getString("status");
				thenumber = result.getString("number");
				themessage = message;
				thecost = result.getString("cost");
               
				if(smsSend !=null){
				SmsSend smsSend2 = smsSendDAO.getSmsSend(smsSend.getUuid());
				smsSend2.setStatus(thestatus);
				smsSend2.setPhoneNo(thenumber);
				smsSend2.setMessageId(themessage.replaceAll("[\r\n]+", " "));
				smsSend2.setCost(thecost);
				smsSendDAO.updateSmsSend(smsSend2); 
				}

			}

		}

		catch (Exception e) {
			e.printStackTrace(); 
			
		}

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
	private static final long serialVersionUID = 5115859501386056513L;

}
