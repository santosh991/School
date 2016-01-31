package com.yahoo.petermwenda83.server.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLogout  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8126780564466352141L;

	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);

   }
   
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   
	   response.sendRedirect("index.jsp");
		
		// get current session, and don't create one if it doesn't exist
	    HttpSession session = request.getSession(false);
	    if(session != null) {
	    	session.invalidate();
	    }  	
	   
	}

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       processRequest(request, response);
   }

}
