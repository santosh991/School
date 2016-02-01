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
       HttpSession session = request.getSession(false);
       
        if (session != null) {
                 
          //destroy the session
          session.invalidate();
        }
	   //return;
	}
   /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }


}
