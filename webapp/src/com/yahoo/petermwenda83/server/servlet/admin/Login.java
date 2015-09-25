/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import com.yahoo.petermwenda83.server.servlet.util.PropertiesConfig;


/**
 * @author peter
 *
 */
public class Login extends HttpServlet{
	
	
	
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -5143599320944244017L;

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
         HttpSession session = request.getSession(true);
         

         String username = StringUtils.trimToEmpty(request.getParameter("username"));
         String password = StringUtils.trimToEmpty(request.getParameter("password"));
         
         
         System.out.println(username);
         System.out.println(password);
        
         if (!StringUtils.equals(password, PropertiesConfig.getConfigValue("ADMIN_PASSWORD"))) {
             //session.setAttribute(SessionConstants.ADMIN_SIGN_IN_ERROR_KEY, SessionConstants.ADMIN_SIGN_IN_ERROR_VALUE);
             response.sendRedirect("index.jsp");

             
        	
         } else if (!StringUtils.equals(username, PropertiesConfig.getConfigValue("ADMIN_USERNAME"))) {
            // session.setAttribute(SessionConstants.ADMIN_SIGN_IN_ERROR_KEY, SessionConstants.ADMIN_SIGN_IN_ERROR_KEY);
            response.sendRedirect("index.jsp");
         
         } else {
             //session.setAttribute(SessionConstants.ADMIN_SESSION_KEY, "admin");
             //session.setAttribute(SessionConstants.ADMIN_LOGIN_TIME_KEY, new Date());
             
            response.sendRedirect("home.jsp");
         }
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
