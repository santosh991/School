/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.exam;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.server.session.AdminSessionConstants;
import com.yahoo.petermwenda83.server.session.SessionConstants;
/**
 * @author peter
 *
 */
public class UpdateExamConfig extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7880606806285167190L;
	
	
	private static ExamConfigDAO examConfigDAO;
	final String ERROR_EMPTY_FIELD = "Empty fields not allowed";

	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       examConfigDAO = ExamConfigDAO.getInstance();
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String schoolAccountUuid = StringUtils.trimToEmpty(request.getParameter("schoolUuid"));
       String term = StringUtils.trimToEmpty(request.getParameter("term"));
       String year = StringUtils.trimToEmpty(request.getParameter("year"));
       String exam = StringUtils.trimToEmpty(request.getParameter("exam"));
       String exammode = StringUtils.trimToEmpty(request.getParameter("exammode"));
       
       
       if(StringUtils.isEmpty(schoolAccountUuid)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_FIELD); 
    	   
       }else if(StringUtils.isEmpty(term)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_FIELD); 
    	   
       }else if(StringUtils.isEmpty(year)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_FIELD); 
    	   
       }else if(StringUtils.isEmpty(exam)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_FIELD); 
    	   
       }else if(StringUtils.isEmpty(exammode)){
    	   session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, ERROR_EMPTY_FIELD); 
    	   
       }else{
       
       
       ExamConfig examConfig = new ExamConfig();
       examConfig.setSchoolAccountUuid(schoolAccountUuid);
       examConfig.setTerm(term);
       examConfig.setYear(year);
       examConfig.setExam(exam);
       examConfig.setExamMode(exammode); 
       
       if(examConfigDAO.updateExamConfig(examConfig)){
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS, SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS);  
       }else{
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, SessionConstants.EXAM_CONFIG_UPDATE_ERROR);  
       }
       response.sendRedirect("examConfig.jsp");  
	   return;
       }
   }
   
   

   @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          doPost(request, response);
      }
}
