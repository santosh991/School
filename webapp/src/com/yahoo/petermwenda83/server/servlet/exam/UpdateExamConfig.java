/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.exam;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.TermFee;
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

	
	TermFee termFee;
	ExamConfig examConfig;
	
	private String[] examcodeArray;
	private List<String> examcodeList;
	
	private String[] exammodeArray;
	private List<String> exammodeList;
	
	
	final String ERROR_EMPTY_FIELD = "Empty fields not allowed";
	final String ERROR_YEAR_OUTSIDE_RANGE = "Check the year and try again";
	final String ERROR_TERM_NOT_ALLOWED = "Term can't be greater that three";
	final String ERROR_TERM_NUMERIC = "Term can only be numeric";
	final String ERROR_EXAM_MODE_NOT_ALLOWED = "Exam Mode can only be  ON or OFF";
	final String ERROR_EXAM_NOT_FOUND = "Exam code not found";
	
	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
        examConfigDAO = ExamConfigDAO.getInstance();
        examcodeArray = new String[] {"C1", "C2", "ET", "P1","P2","P3"};
		examcodeList = Arrays.asList(examcodeArray);
		
		exammodeArray = new String[] {"ON","OFF"};
		exammodeList = Arrays.asList(exammodeArray);
	
		
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String schoolAccountUuid = StringUtils.trimToEmpty(request.getParameter("schoolUuid"));
       String term = StringUtils.trimToEmpty(request.getParameter("term"));
       String year = StringUtils.trimToEmpty(request.getParameter("year"));
       String exam = StringUtils.trimToEmpty(request.getParameter("exam"));
       String exammode = StringUtils.trimToEmpty(request.getParameter("exammode"));

       Calendar calendar = Calendar.getInstance();
       final int YEAR = calendar.get(Calendar.YEAR);
       int currentYearplusone = YEAR+1;
       int theyear = Integer.parseInt(year);
       int theterm = Integer.parseInt(term);
       
       
        if(StringUtils.isEmpty(year)){
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_EMPTY_FIELD); 
    	   
        }else if(!isNumericRange(term)){
		     session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, ERROR_TERM_NUMERIC); 
			   
	   }else if(theterm >3){
    	  session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_TERM_NOT_ALLOWED); 
    	   
        }
        else if(theyear>currentYearplusone){
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_YEAR_OUTSIDE_RANGE); 
    	   
        }else if(StringUtils.isEmpty(exam)){
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_EMPTY_FIELD); 
    	   
        }else if(!examcodeList.contains(exam)){ 
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_EXAM_NOT_FOUND); 
    	   
       }else if(StringUtils.isEmpty(exammode)){
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_EMPTY_FIELD); 
    	   
       }else if(!exammodeList.contains(exammode)){  
    	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_EXAM_MODE_NOT_ALLOWED); 
    	   
       }else if(StringUtils.isEmpty(schoolAccountUuid)){
   	   session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, ERROR_EMPTY_FIELD); 
   	   
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
       
       }
       
       response.sendRedirect("prepareCommitt.jsp");  
	   return;
   }
   
   /**
    * @param amount
    * @return
    */
   private boolean isNumericRange(String amount) {
   	boolean valid = true;
   	String regex = "[0-9]+";
   	if(amount.matches(regex)){ 
   		valid = true;
   	}else{
   		valid = false;
   	}
   	
   	return valid;
   }
      
   

   @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          doPost(request, response);
      }
}
