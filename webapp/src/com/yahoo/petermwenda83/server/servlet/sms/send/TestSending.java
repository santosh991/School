/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.sms.send;

/* Make sure the downloaded jar file is in the classpath or has been added to 
referenced libraries if you are using an SDK like eclipse or netbeans*/
import org.json.JSONArray;
import org.json.JSONObject;

import com.yahoo.petermwenda83.bean.smsapi.AfricasTalking;
public class TestSending

{
 public static void main(String[] args_)
 {
	 
	 
	 
	 AfricasTalking africasTalking = new AfricasTalking();
	 
      // Specify your login credentials
      String username = africasTalking.getUsername();
      String apiKey   = africasTalking.getApiKey();
 
      // Specify the numbers that you want to send to in a comma-separated list
      // Please ensure you include the country code (+254 for Kenya in this case)
      String recipients = "+254718953974,+254702249230";
      africasTalking.setRecipients(recipients); 
      // And of course we want our recipients to know what we really do
      String message = "Hi there, This is Peter Mwenda SMS API test";
      africasTalking.setMessage(message);
 
     // Create a new instance of our awesome gateway class
      AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
 
      // Thats it, hit send and we'll take care of the rest. Any errors will
      // be captured in the Exception class below
     try {
         JSONArray results = gateway.sendMessage(africasTalking.getRecipients(), africasTalking.getMessage());
         
         for( int i = 0; i < results.length(); ++i ) {
               JSONObject result = results.getJSONObject(i);
               System.out.print(result.getString("status") + ","); // status is either "Success" or "error message"
               System.out.print(result.getString("number") + ",");
               System.out.print(result.getString("messageId") + ",");
               System.out.println(result.getString("cost"));
     }
    }
    
    catch (Exception e) {
     System.out.println("Encountered an error while sending " + e.getMessage());
     }
 
}
}