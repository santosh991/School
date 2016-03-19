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
import com.yahoo.petermwenda83.bean.othermoney.OtherMonies;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMonies;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMoniesClosingBal;
import com.yahoo.petermwenda83.bean.othermoney.TermOtherMonies;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.othermoney.OtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.othermoney.StudentOtherMoniesClosingBalDAO;
import com.yahoo.petermwenda83.persistence.othermoney.StudentOtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.othermoney.TermOtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/** 
 * @author peter
 *
 */
public class OtherItemsUpdate extends HttpServlet{

	/** 
	 * 
	 */
	private static final long serialVersionUID = -6488088979845943829L;
	final String COMMITT_SUCCESS = "The Transaction was committed Successfully";
	final String COMMITT_ERROR = "Transaction failed";

	public final static String STATUS_NOT_DEDUCTED = "NOTDEDUCTED";
	public final static String STATUS_DEDUCTED = "DEDUCTED";
	private Cache schoolaccountCache;

	private static ExamConfigDAO examConfigDAO;
	private static StudentDAO studentDAO;

	private static OtherMoniesDAO otherMoniesDAO;
	private static StudentOtherMoniesClosingBalDAO studentOtherMoniesClosingBalDAO;
	private static StudentOtherMoniesDAO studentOtherMoniesDAO;
	private static TermOtherMoniesDAO termOtherMoniesDAO;

	ExamConfig examConfig;
	double prevbal = 0;
	String prevstatus = "";

	HashMap<String, String> typetHash = new HashMap<String, String>();
	/**  
	 *
	 * @param config
	 * @throws ServletException
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		CacheManager mgr = CacheManager.getInstance();
		schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
		examConfigDAO = ExamConfigDAO.getInstance();
		studentDAO = StudentDAO.getInstance();

		otherMoniesDAO = OtherMoniesDAO.getInstance();
		studentOtherMoniesClosingBalDAO = StudentOtherMoniesClosingBalDAO.getInstance();
		studentOtherMoniesDAO = StudentOtherMoniesDAO.getInstance();
		termOtherMoniesDAO = TermOtherMoniesDAO.getInstance();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);


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

		List<Student> studentList = new ArrayList<>();
		studentList = studentDAO.getAllStudentList(school.getUuid());


		//double previousFee = 0;

		if(studentList !=null){
			for(Student student : studentList){




				// we find the other payments the student was assigned in the previuos term

				//get all the available term other money type and amount
				List<TermOtherMonies> termOtherMoniesList = new ArrayList<TermOtherMonies>(); 
				termOtherMoniesList = termOtherMoniesDAO.getTermOtherMoniesList(school.getUuid());  
				HashMap<String, Double> tomHash = new HashMap<String, Double>(); 
				for(TermOtherMonies toml: termOtherMoniesList){
					//this is the amount to be paid
					tomHash.put(toml.getOtherstypeUuid(),toml.getAmount());
				}

				double itemcost = 0;
				double amountpaid = 0;
				double amountpaidTotal = 0;
				double mysombalance = 0;
				double mysombalancetotal = 0;
				// double paymentamountTotal = 0;
				List<StudentOtherMonies> stuOthermoniDistinctList = new ArrayList<StudentOtherMonies>(); 
				stuOthermoniDistinctList = studentOtherMoniesDAO.getStudentOtherMoniesDistinct(student.getUuid());
				// System.out.println("stuOthermoniDistinctList="+stuOthermoniDistinctList);
				if(stuOthermoniDistinctList !=null){
					for(StudentOtherMonies somdisticnt : stuOthermoniDistinctList){
						//get all payments for a particular type

						amountpaid = 0;
						amountpaidTotal = 0;
						itemcost = 0;
						mysombalance = 0;
						mysombalancetotal = 0;

						StudentOtherMoniesClosingBal studentOtherMoniesClosingBal = new StudentOtherMoniesClosingBal();

						List<StudentOtherMonies> paylist = new ArrayList<>();
						paylist = studentOtherMoniesDAO.getStudentOtherMoniesList(student.getUuid(), somdisticnt.getOtherstypeUuid());
						// System.out.println("paylist="+paylist);
						for(StudentOtherMonies sotheO : paylist){
							amountpaid = sotheO.getAmountPiad();
							amountpaidTotal+=amountpaid;
							itemcost = tomHash.get(sotheO.getOtherstypeUuid());
							mysombalance = itemcost - amountpaidTotal;
							mysombalancetotal+=mysombalance;



							StudentOtherMoniesClosingBal cbal = new StudentOtherMoniesClosingBal();
							if(studentOtherMoniesClosingBalDAO.getStudentOtherMoniesClosingBal(student.getUuid(),sotheO.getOtherstypeUuid()) !=null){
								cbal = studentOtherMoniesClosingBalDAO.getStudentOtherMoniesClosingBal(student.getUuid(),sotheO.getOtherstypeUuid());
								if(cbal !=null){
									prevbal = cbal.getBalance();

								}

							}

							studentOtherMoniesClosingBal.setStudentUuid(student.getUuid());
							studentOtherMoniesClosingBal.setOtherstypeUuid(sotheO.getOtherstypeUuid());  
							studentOtherMoniesClosingBal.setBalance(mysombalancetotal);   
							System.out.println("mysombalancetotal="+mysombalancetotal);
							System.out.println("prevbal="+prevbal);
						}


						OtherMonies om = new OtherMonies();
						om.setStudentUuid(student.getUuid()); 

						OtherMonies otherMo = new OtherMonies();
						otherMo.setStudentUuid(student.getUuid()); 
						otherMo.setStatus(STATUS_DEDUCTED); 

						OtherMonies otherM = new OtherMonies();
						otherM = otherMoniesDAO.getOtherMonies(student.getUuid());

						if(StringUtils.equals(otherM.getStatus() , STATUS_NOT_DEDUCTED)) {
							otherMoniesDAO.deductOtherMonies(om,itemcost);
							otherMoniesDAO.changeOtherMoniesStatus(otherMo);

							if(studentOtherMoniesClosingBalDAO.putStudentOtherMoniesClosingBal(studentOtherMoniesClosingBal)){
								session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS, COMMITT_SUCCESS +" "); 
							}else{
								session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, COMMITT_ERROR); 
							}



						}else{
							otherMoniesDAO.deductOtherMonies(om,(itemcost - amountpaidTotal));
							if(studentOtherMoniesClosingBalDAO.putStudentOtherMoniesClosingBal(studentOtherMoniesClosingBal)){
								session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS, COMMITT_SUCCESS +" "); 
							}else{
								session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, COMMITT_ERROR); 
							}


						}
					}
				}


			}

		}

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
