/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.money.newtermupdate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.ClosingBalance;
import com.yahoo.petermwenda83.bean.money.StudentAmount;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author peter
 *
 */
public class NewTermUpdate extends HttpServlet{

	/** 
	 * 
	 */
	private static final long serialVersionUID = -6488088979845943829L;
	final String COMMITT_SUCCESS = "The Transaction was committed Successfully";
	final String COMMITT_ERROR = "Transaction failed";

	public final static String STATUS_NOT_DEDUCTED = "NOTDEDUCTED";
	public final static String STATUS_DEDUCTED = "DEDUCTED";
	private Cache schoolaccountCache;

	private static StudentAmountDAO studentAmountDAO;
	private static ExamConfigDAO examConfigDAO;
	private static StudentDAO studentDAO;
	private static TermFeeDAO termFeeDAO;
	private static ClosingBalanceDAO closingBalanceDAO;
	
	
	
	TermFee termFee;
	ExamConfig examConfig;
    
	
	 HashMap<String, String> typetHash = new HashMap<String, String>();
	/**  
	 *
	 * @param config
	 * @throws ServletException
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		studentAmountDAO = StudentAmountDAO.getInstance();
		CacheManager mgr = CacheManager.getInstance();
		schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
		examConfigDAO = ExamConfigDAO.getInstance();
		termFeeDAO = TermFeeDAO.getInstance();
		studentDAO = StudentDAO.getInstance();
		closingBalanceDAO = ClosingBalanceDAO.getInstance();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		//response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json"); 

		//PrintWriter out = response.getWriter();

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
        
		String currentTerm = examConfig.getTerm();// 2 -> 1, 3 -> 2 , 1 -> 3
		int currentTernint = Integer.parseInt(currentTerm);
		int previoustermint = currentTernint - 1;
		
		String currentYear = examConfig.getYear();
		int currentYearint = Integer.parseInt(currentYear);
		int prevyearint = 0;
		String previousyear = "";
		
		int realTermint = 0;
		String previousTerm = "";
		if(previoustermint == 0){   //MEANS OUR CURREMT TERM IS 1, so prev term is 3
			realTermint = 3;
			previousTerm = Integer.toString(realTermint);
			prevyearint = currentYearint - 1;
			previousyear = Integer.toString(prevyearint);
		}else{
			realTermint = previoustermint;
			previousTerm = Integer.toString(realTermint);
			previousyear = currentYear;
		}

		termFee = new TermFee();
		if(termFeeDAO.getTermFee(school.getUuid(),currentTerm) !=null){
			termFee = termFeeDAO.getTermFee(school.getUuid(),currentTerm);
		}

		List<Student> studentList = new ArrayList<>();
		studentList = studentDAO.getAllStudentList(school.getUuid());



		//String json = "";
		double previousFee = 0;
		
		 
		int noOfStudent = 0;
		int updatedStudent = 0;
		String message = "";
		
		if(studentList !=null){
			for(Student student : studentList){
				
				StudentAmount studentAmount = new StudentAmount();

				//get student status
				if(studentAmountDAO.getStudentAmount(school.getUuid(), student.getUuid()) !=null){
					studentAmount = studentAmountDAO.getStudentAmount(school.getUuid(), student.getUuid());
				}
				
			

				//check whether student has been deducted term amount
				// if not so, deduct , else skip
				if(StringUtils.equals(studentAmount.getStatus(), STATUS_NOT_DEDUCTED) ){
					
					//we find previous term fee					
					TermFee	termFeePrev = new TermFee();
					
					if(termFeeDAO.getTermFee(school.getUuid(),previousTerm) !=null){
						termFeePrev = termFeeDAO.getTermFee(school.getUuid(),previousTerm);
					}
					
					//this is prev term fee
					previousFee = termFeePrev.getTermAmount();
					// assume prev term fee was 18,000 and the student over paid ( paid 20,000 )					
					// bal overpay = 2000
					//if paid 15000, 
					//bal dues 3000
					
					
					if(studentAmountDAO.deductAmount(school.getUuid(), student.getUuid(), previousFee) &&
							studentAmountDAO.changeStatus(STATUS_DEDUCTED, school.getUuid(), student.getUuid())
							){
						//SELECT THE CURRENT AMOUNT, WHICH DENOTES EITHER OVER PAYMENT OR DUES
						StudentAmount studentAmountObj = new StudentAmount();
						double closingamount = 0;
						studentAmountObj = studentAmountDAO.getStudentAmount(school.getUuid(), student.getUuid());
						closingamount = studentAmountObj.getAmount();
						//WE SAVE CLOSING AMOUNT
						
						ClosingBalance closingBalance = new ClosingBalance();
						closingBalance.setSchoolAccountUuid(school.getUuid());
						closingBalance.setStudentUuid(student.getUuid());
						closingBalance.setTerm(previousTerm);
						closingBalance.setYear(previousyear);
						closingBalance.setClosingAmount(closingamount);
					
						
						if(closingBalanceDAO.putClosingBalance(closingBalance)){
							message = ", ";
						}
						
					    
						updatedStudent++;
						
					}else{
						message = "***, ";
							
						} 
					
					
					
					//json = new Gson().toJson("Deducted");
				} 


				noOfStudent++;
			}
			
		}//if(studentList !=null){

		//out.flush();
		//out.close();	
		
		//response 
		session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS, COMMITT_SUCCESS +" "+message+"("+updatedStudent + " students of " + noOfStudent+")"); 
		
		
		response.sendRedirect("feedback.jsp");  
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


}
