package com.yahoo.petermwenda83.server.servlet.sms.send;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import com.yahoo.petermwenda83.bean.smsapi.AfricasTalking;
import com.yahoo.petermwenda83.server.session.SessionConstants;

public class SendSMS extends HttpServlet{/*
	
	
	final String SMS_SEND_SUCCESS = "SMS was sent Successfully.";
	final String SMS_SEND_ERROR = "Something went wrong while sendig the SMS.";
	*//**  
    *
    * @param config
    * @throws ServletException
    *//*
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String destination = StringUtils.trimToEmpty(request.getParameter("destination"));
       String message = StringUtils.trimToEmpty(request.getParameter("smsText"));
       
       AfricasTalking africasTalking = new AfricasTalking();
  	 
       // Specify your login credentials
       String username = africasTalking.getUsername();
       String apiKey   = africasTalking.getApiKey();
       
       //String recipients = "+254718953974,+254702249230";
       africasTalking.setRecipients(destination); 
       // And of course we want our recipients to know what we really do
       //String message = "Hi there, This is Peter Mwenda SMS API test";
       africasTalking.setMessage(message);
       
       // Create a new instance of our awesome gateway class
       AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
       

       // Thats it, hit send and we'll take care of the rest. Any errors will
       // be captured in the Exception class below
      try {
          JSONArray results = gateway.sendMessage(africasTalking.getRecipients(), africasTalking.getMessage());
          int count = 0;
          for( int i = 0; i < results.length(); ++i ) {
                JSONObject result = results.getJSONObject(i);
                System.out.print(result.getString("status") + ","); 
                System.out.print(result.getString("number") + ",");
                System.out.print(result.getString("messageId") + ",");
                System.out.println(result.getString("cost"));
                count++;
         }
          session.setAttribute(SessionConstants.SMS_SEND_SUCCESS, SMS_SEND_SUCCESS  + " SMS sent to " + count +" Contacts"); 
     }
     
     catch (Exception e) {
    	 session.setAttribute(SessionConstants.SMS_SEND_ERROR, SMS_SEND_ERROR); 
      }
  
  
      response.sendRedirect("sendsms.jsp");  
	  return; 
       
   }
   
   
   

   @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doPost(request, response);
     }
   
   *//**
	 * 
	 *//*
	private static final long serialVersionUID = 5115859501386056513L;

*/}
