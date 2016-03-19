package com.yahoo.petermwenda83.server.servlet.sms.send;


	import java.io.IOException;
	import javax.servlet.ServletConfig;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	/**
	* This Java program is a servlet that receives messages sent from the SMS Gateway
	* callback mechanism.
	* <p>
	* This has been tested on JDK 1.7. and Oracle Glassfish 3.1.
	* <p>
	* Copyright (c) Tawi Commercial Services Ltd., June 2012
	*
	*/
	public class CallbackListener extends HttpServlet {
	/**
		 * 
		 */
		private static final long serialVersionUID = -6227402318441771698L;


	/**
	*
	* @param config
	* @throws ServletException
	*/
	public void init(ServletConfig config) throws ServletException {
	super.init(config);
	}
	/**
	*
	* @param servletRequest
	* @param servletResponse
	* @throws ServletException, IOException
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	System.out.println("Have received callback.");
	String callbackType = request.getParameter("callbackType");
	
	if(callbackType.equals("incomingSms")) {
	System.out.println("source is: " + request.getParameter("source"));
	System.out.println("destination is: " + request.getParameter("destination"));
	System.out.println("message is: " + request.getParameter("message"));
	System.out.println("messageId is: " + request.getParameter("messageId"));
	System.out.println("network is: " + request.getParameter("network"));
	}
	
	}
	
	
	/**
	* @param servletRequest
	* @param servletResponse
	* @throws ServletException, IOException
	*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
			}
	
}