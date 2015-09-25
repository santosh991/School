package com.yahoo.petermwenda83.server.servlet.school;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4366123889354229389L;





	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(false);

       if (session != null) {
           session.invalidate();  
       }
       
       session = request.getSession(true);
       String username = StringUtils.trimToEmpty(request.getParameter("username"));
       String password = StringUtils.trimToEmpty(request.getParameter("password"));
       
       System.out.println(username);
       System.out.println(password);
       String pass ="demo";
       String user ="demo";
       
       if (!StringUtils.equals(password,pass)){
    	   response.sendRedirect("index.jsp");
       }else if (!StringUtils.equals(username,user)){
    	   response.sendRedirect("index.jsp");
       }else{
    	   
       }
       response.sendRedirect("home.jsp");
       
       
   }
   
   
   
   
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }
}
