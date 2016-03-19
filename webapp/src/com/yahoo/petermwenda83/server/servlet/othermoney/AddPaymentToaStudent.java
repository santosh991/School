package com.yahoo.petermwenda83.server.servlet.othermoney;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMonies;
import com.yahoo.petermwenda83.bean.othermoney.TermOtherMonies;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.othermoney.StudentOtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.othermoney.TermOtherMoniesDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class AddPaymentToaStudent extends HttpServlet{
	
	public final static String STATUS_NOT_DEDUCTED = "NOTDEDUCTED";
	
	final String MONEY_ASSIGNED_SUCCESS = "The money has been assigned successfully";
	final String MONEY_ASSIGNED_ERROR = "Something went wrong while assigning the money";
	
	final String EMPTY_FIELD = "Empty Fields not allowed";
	final String ERROR_MONEY_ALREADY_ASSIGNED = "Payment type already added";
	final String ERROR_DID_NOT_SEARCH_STUDENT = "It looks like you didn't search for any student";
	
	
	private static StudentOtherMoniesDAO studentOtherMoniesDAO;
	private static ExamConfigDAO examConfigDAO;
	private static StudentAmountDAO studentAmountDAO;
	private static TermOtherMoniesDAO termOtherMoniesDAO;
	
	
	StudentOtherMonies studentOtherMonies;
	ExamConfig examConfig;

	
	private Cache schoolaccountCache;
	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentOtherMoniesDAO = StudentOtherMoniesDAO.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
       studentAmountDAO = StudentAmountDAO.getInstance();
       termOtherMoniesDAO = TermOtherMoniesDAO.getInstance();
       
       CacheManager mgr = CacheManager.getInstance();
	   schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
       
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
   
       String OtherstypeUuid = StringUtils.trimToEmpty(request.getParameter("OtherstypeUuid"));
       //String AmountPiad = StringUtils.trimToEmpty(request.getParameter("AmountPiad"));
       String StudentUuid = StringUtils.trimToEmpty(request.getParameter("StudentUuid"));
       
   
       
       SchoolAccount school = new SchoolAccount();
		String  schoolusername = "";
		if(session !=null){
			schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);

		}
		net.sf.ehcache.Element element;

		element = schoolaccountCache.get(schoolusername);
		if(element !=null){
			school = (SchoolAccount) element.getObjectValue();
		}



		examConfig = new ExamConfig();
		if(examConfigDAO.getExamConfig(school.getUuid()) !=null){
			examConfig = examConfigDAO.getExamConfig(school.getUuid());
		}
       
	    if(StringUtils.isBlank(OtherstypeUuid)){
	    	session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_ERROR, EMPTY_FIELD); 
	    	
	    }else if (StringUtils.isBlank(StudentUuid)){
	    	session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_ERROR, ERROR_DID_NOT_SEARCH_STUDENT); 
	    	
	    }else if (studentOtherMoniesDAO.getStudentOtherMTY(StudentUuid, OtherstypeUuid, examConfig.getTerm(), examConfig.getYear()) !=null){
	    	session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_ERROR, ERROR_MONEY_ALREADY_ASSIGNED); 
	    	
	    }else{
	    	
	    	double typeAmount = 0;
	    	if(termOtherMoniesDAO.getTermOtherMoney(school.getUuid(), OtherstypeUuid) !=null){
	        TermOtherMonies termOtherMonies =  termOtherMoniesDAO.getTermOtherMoney(school.getUuid(), OtherstypeUuid);
	        typeAmount = termOtherMonies.getAmount();
	    	}
	
       studentOtherMonies = new StudentOtherMonies();
       studentOtherMonies.setStudentUuid(StudentUuid);
       studentOtherMonies.setOtherstypeUuid(OtherstypeUuid);
       studentOtherMonies.setAmountPiad(typeAmount); 
       studentOtherMonies.setTerm(examConfig.getTerm());
       studentOtherMonies.setYear(examConfig.getYear()); 
       
       if(studentOtherMoniesDAO.putStudentOtherMonies(studentOtherMonies) &&  studentAmountDAO.deductAmount(school.getUuid(), StudentUuid, typeAmount)){
    	   session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_SUCCESS, MONEY_ASSIGNED_SUCCESS); 
    	   
       }else{
    	   session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_ERROR, MONEY_ASSIGNED_ERROR); 
       }
	 }
       response.sendRedirect("studentAddOM.jsp");  
	   return; 
       
   }

/**
 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doPost(request, response);
     }
/**
 * 
 */
private static final long serialVersionUID = -2612273960810030426L;
}
