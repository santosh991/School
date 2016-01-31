/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.jasypt.util.text.BasicTextEncryptor;

import com.yahoo.petermwenda83.server.servlet.util.PropertiesConfig;
import com.yahoo.petermwenda83.server.session.AdminSessionConstants;
/**
 * @author peter
 *
 */
public class AdminLogin extends HttpServlet{
	
	
	
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -5143599320944244017L;
	 // Error message provided when incorrect captcha is submitted
    final String ACCOUNT_SIGN_IN_BAD_CAPTCHA = "Sorry, the characters you entered did not "
            + "match those provided in the image. Please try again.";
	 private BasicTextEncryptor textEncryptor;
	 private String hiddenCaptchaStr = "";
	 private Logger logger;

	/**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(PropertiesConfig.getConfigValue("ENCRYPT_PASSWORD"));
        logger = Logger.getLogger(this.getClass());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(true);
         

         String username = StringUtils.trimToEmpty(request.getParameter("username"));
         String password = StringUtils.trimToEmpty(request.getParameter("password"));
         hiddenCaptchaStr = request.getParameter("captchaHidden");
         String captchaAnswer = request.getParameter("captchaAnswer").trim();
         
        // System.out.println(username);
        // System.out.println(password);
         
         if (!validateCaptcha(hiddenCaptchaStr, captchaAnswer)) {
             session.setAttribute(AdminSessionConstants.ADMIN_SIGN_IN_ERROR_KEY, ACCOUNT_SIGN_IN_BAD_CAPTCHA);
             response.sendRedirect("index.jsp");
         }
         else if (!StringUtils.equals(password, PropertiesConfig.getConfigValue("ADMIN_PASSWORD"))) {
             session.setAttribute(AdminSessionConstants.ADMIN_SIGN_IN_ERROR_KEY, AdminSessionConstants.ADMIN_SIGN_IN_ERROR_VALUE);
             response.sendRedirect("index.jsp");

         } else if (!StringUtils.equals(username, PropertiesConfig.getConfigValue("ADMIN_USERNAME"))) {
            session.setAttribute(AdminSessionConstants.ADMIN_SIGN_IN_ERROR_KEY, AdminSessionConstants.ADMIN_SIGN_IN_ERROR_KEY);
            response.sendRedirect("index.jsp");
         
         } else {
             session.setAttribute(AdminSessionConstants.ADMIN_SESSION_KEY, "admin");
             session.setAttribute(AdminSessionConstants.ADMIN_SIGN_IN_TIME, new Date());
             
            response.sendRedirect("adminIndex.jsp");
         }
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    

    /**
     * Checks to see that the captcha generated by the person and the captcha
     * submitted are equal. Case is ignored.
     *
     * @param systemCaptcha
     * @param userCaptcha
     * @return boolean
     */
    private boolean validateCaptcha(String encodedSystemCaptcha, String userCaptcha) {
        boolean valid = false;
        String decodedHiddenCaptcha = "";

        try {
            decodedHiddenCaptcha = textEncryptor.decrypt(URLDecoder.decode(encodedSystemCaptcha, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException while validating administrator captcha");
            logger.error(ExceptionUtils.getStackTrace(e));
        }

        if (StringUtils.equalsIgnoreCase(decodedHiddenCaptcha, userCaptcha)) {
            valid = true;
        }

        return valid;
    }
}
