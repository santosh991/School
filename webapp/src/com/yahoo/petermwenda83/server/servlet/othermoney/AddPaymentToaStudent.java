package com.yahoo.petermwenda83.server.servlet.othermoney;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.ClosingBalance;
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.othermoney.Otherstype;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMonies;
import com.yahoo.petermwenda83.bean.othermoney.TermOtherMonies;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.schoolaccount.SmsSend;
import com.yahoo.petermwenda83.bean.smsapi.AfricasTalking;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.guardian.StudentParent;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.guardian.ParentsDAO;
import com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.money.StudentFeeDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.OtherstypeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.StudentOtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.othermoney.TermOtherMoniesDAO;
import com.yahoo.petermwenda83.persistence.schoolaccount.SmsSendDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.servlet.sms.send.AfricasTalkingGateway;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class AddPaymentToaStudent extends HttpServlet{

	public final static String STATUS_NOT_DEDUCTED = "NOTDEDUCTED";

	final String MONEY_ASSIGNED_SUCCESS = "The money has been assigned successfully";
	final String MONEY_ASSIGNED_ERROR = "Something went wrong while assigning the money";
	final String ERROR_AMOUNT_INVALID = "Invalid amount, amount range from KSH 100 - KSH 100,000";
	final String ERROR_AMOUNT_NUMERIC = "Amount can only be numeric";
	final String EMPTY_FIELD = "Empty Fields not allowed";
	final String ERROR_MONEY_ALREADY_ASSIGNED = "Payment type already added";
	final String ERROR_DID_NOT_SEARCH_STUDENT = "It looks like you didn't search for any student";
	
	final String ERROR_STUDENT_INACTIVE = "This student is Inactive.";
	final String statusUuid = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";


	private static StudentOtherMoniesDAO studentOtherMoniesDAO;
	private static ExamConfigDAO examConfigDAO;
	private static StudentAmountDAO studentAmountDAO;
	private static TermOtherMoniesDAO termOtherMoniesDAO;

	private static StudentFeeDAO studentFeeDAO;
	private static TermFeeDAO termFeeDAO;
	private static StudentDAO studentDAO;
	private static ParentsDAO parentsDAO;
	private static SmsSendDAO smsSendDAO;
	private static ClosingBalanceDAO closingBalanceDAO;
	private static OtherstypeDAO otherstypeDAO;

	Locale locale = new Locale("en","KE"); 
	NumberFormat nf = NumberFormat.getCurrencyInstance(locale);



	StudentOtherMonies studentOtherMonies;
	ExamConfig examConfig;

	HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	HashMap<String, String>genderfinderHash = new HashMap<String, String>();
	HashMap<String, String> studNameHash = new HashMap<String, String>();
	HashMap<String, String> roomHash = new HashMap<String, String>();

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

		studentFeeDAO = StudentFeeDAO.getInstance();
		termFeeDAO = TermFeeDAO.getInstance();
		studentDAO = StudentDAO.getInstance();
		parentsDAO = ParentsDAO.getInstance();
		smsSendDAO = SmsSendDAO.getInstance();
		closingBalanceDAO = ClosingBalanceDAO.getInstance();
		otherstypeDAO = OtherstypeDAO.getInstance();

		CacheManager mgr = CacheManager.getInstance();
		schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		String OtherstypeUuid = StringUtils.trimToEmpty(request.getParameter("OtherstypeUuid"));
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
			

			//get student name
			Student stuudent = new Student();
			if(studentDAO.getStudentByuuid(school.getUuid(), StudentUuid) !=null){
				stuudent = studentDAO.getStudentByuuid(school.getUuid(), StudentUuid);
			}
			
			 if(StringUtils.equals(stuudent.getStatusUuid(),statusUuid)){
			
			 List<Otherstype> othertypeList = new ArrayList<Otherstype>(); 
		     othertypeList = otherstypeDAO.gettypeList(school.getUuid());  
		      HashMap<String, String> moneytypeHash = new HashMap<String, String>(); 
		     
		     if(othertypeList !=null){
		     for(Otherstype om : othertypeList){
		         moneytypeHash.put(om.getUuid(),om.getType());
		         }
		       }
		             

			double typeAmount = 0;
			String type = "";
			if(termOtherMoniesDAO.getTermOtherMoney(school.getUuid(), OtherstypeUuid) !=null){
				TermOtherMonies termOtherMonies =  termOtherMoniesDAO.getTermOtherMoney(school.getUuid(), OtherstypeUuid);
				typeAmount = termOtherMonies.getAmount();
				type = moneytypeHash.get(termOtherMonies.getOtherstypeUuid());
			}

			studentOtherMonies = new StudentOtherMonies();
			studentOtherMonies.setStudentUuid(StudentUuid);
			studentOtherMonies.setOtherstypeUuid(OtherstypeUuid);
			studentOtherMonies.setAmountPiad(typeAmount); 
			studentOtherMonies.setTerm(examConfig.getTerm());
			studentOtherMonies.setYear(examConfig.getYear()); 
			
			if(studentAmountDAO.deductAmount(school.getUuid(), StudentUuid, typeAmount) && 
					studentOtherMoniesDAO.putStudentOtherMonies(studentOtherMonies)){
				session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_SUCCESS, MONEY_ASSIGNED_SUCCESS); 
			}else{
				session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_ERROR, MONEY_ASSIGNED_ERROR); 
			}
			
			String feebalance = "";
			
			//get parent contact and name
			String phone = "";
			String formatedphone = "";
			String realphone = "";
			String parentname = "";
			StudentParent studentParent = new StudentParent();
			if(parentsDAO.getParent(StudentUuid) !=null){
				studentParent = parentsDAO.getParent(StudentUuid);
				parentname = studentParent.getFathername();
				phone = studentParent.getFatherphone();
				formatedphone = phone.replaceFirst("^0+(?!$)", "");
				realphone = "+254"+formatedphone;
			}



			String genderfinder = "";
			String gender = "";

			if(stuudent != null){
				studentAdmNoHash.put(stuudent.getUuid(),stuudent.getAdmno()); 
				String firstNameLowecase = stuudent.getFirstname().toLowerCase();
				String lastNameLowecase = stuudent.getLastname().toLowerCase();
				//String surnameLowecase = stuudent.getSurname().toLowerCase();
				String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
				String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
				//String formatedsurname = surnameLowecase.substring(0,1).toUpperCase()+surnameLowecase.substring(1);
				studNameHash.put(stuudent.getUuid(),formatedFirstname + " " + formatedLastname +"\n"); 
				//firstnameHash.put(stuudent.getUuid(), formatedFirstname);
				gender = stuudent.getGender();
				if(StringUtils.equalsIgnoreCase(gender, "Male")) {
					genderfinder = "son";
				}else{
					genderfinder = "daughter";
				}
				genderfinderHash.put(stuudent.getUuid(), genderfinder);
			}



				//get new fee balance
				//Get payments for the 'current year' and 'current term' 
				double totalpaid = 0;
				List<StudentFee> feelist = new ArrayList<>();               
				feelist = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),StudentUuid,examConfig.getTerm(),examConfig.getYear());
				if(feelist !=null){
					for(StudentFee fee : feelist){
						totalpaid +=fee.getAmountPaid();
					}
				}


				// we should find previous term balance or over payments
				String previuosyear = "";
				String currentyear = examConfig.getYear();
				int currentyearint = Integer.parseInt(currentyear);
				int previousyearint = 0;

				String currenttermStr = examConfig.getTerm();
				int currenttermint = Integer.parseInt(currenttermStr);// can either be 1, 2, or 3
				int previousterm = currenttermint - 1;// if c = 3 , p = 2 // if c = 2 , p = 1 // if c = 1 p = 3
				if(previousterm == 0){
					previousterm = 3;
					previousyearint = currentyearint - 1;
					previuosyear = Integer.toString(previousyearint);
				}else{
					previuosyear = examConfig.getYear();
				}
				String previoustermStr = Integer.toString(previousterm);
				//now we have the previous term , we get the term amount (previous)
				//from closing balance, we add the amount, negative balance means dues, positive balance means over pay

				double prevtermbalance = 0;
				ClosingBalance closingBalance = new ClosingBalance();
				if(closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), StudentUuid, previoustermStr, previuosyear) !=null){
					closingBalance = closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), StudentUuid, previoustermStr, previuosyear);

				}

				prevtermbalance = closingBalance.getClosingAmount();


				//end
				TermFee termfee = new TermFee();
				if(termFeeDAO.getFee(school.getUuid(),examConfig.getTerm(),examConfig.getYear()) !=null){

					double other_m_amount = 0;
					double other_m_totals = 0;

					termfee = termFeeDAO.getFee(school.getUuid(),examConfig.getTerm(),examConfig.getYear());

					List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
					stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(StudentUuid,examConfig.getTerm(),examConfig.getYear());
					if(stuOthermoniList !=null){

						for(StudentOtherMonies som  : stuOthermoniList){
							other_m_amount = som.getAmountPiad();
							other_m_totals +=other_m_amount;
						}
					}


					feebalance =	nf.format(termfee.getTermAmount() -prevtermbalance -totalpaid + other_m_totals);



				}


				//send message
				AfricasTalking africasTalking = new AfricasTalking();
				// Specify your login credentials
				String username = africasTalking.getUsername();
				String apiKey   = africasTalking.getApiKey();
				String message = "";
				message = "HI " + parentname + ", your " + genderfinderHash.get(StudentUuid)+ " " + studNameHash.get(StudentUuid) + " Adm.No " + studentAdmNoHash.get(StudentUuid) + " has been added additional charges  of type " + type + " ,amount " + nf.format(typeAmount) + " Term " + examConfig.getTerm() + " Year " + examConfig.getYear() +", Fee balance is " + feebalance;


				africasTalking.setMessage(message); 
				africasTalking.setRecipients(realphone); 
				// Create a new instance of our awesome gateway class
				AfricasTalkingGateway gateway  = new AfricasTalkingGateway(username, apiKey);
				
				// Thats it, hit send and we'll take care of the rest. Any errors will
				// be captured in the Exception class below

				//save to database
				String thestatus ="";
				String thenumber ="";
				String themessage ="";
				String thecost ="";

				//System.out.println(message.replaceAll("[\r\n]+", " "));  
				SmsSend smsSend = new SmsSend();
				if(realphone !=null && message.replaceAll("[\r\n]+", " ") !=null){
				smsSend.setStatus("failed");
				smsSend.setPhoneNo(realphone);
				smsSend.setMessageId(message.replaceAll("[\r\n]+", " "));
				smsSend.setCost("1");
				smsSendDAO.putSmsSend(smsSend);
				}

				try {
					JSONArray results = gateway.sendMessage(africasTalking.getRecipients(), africasTalking.getMessage());
					for( int i = 0; i < results.length(); ++i ) {
						JSONObject result = results.getJSONObject(i);
					
						thestatus = result.getString("status");
						thenumber = result.getString("number");
						themessage = message;
						thecost = result.getString("cost");
                        
						if(smsSend !=null){
						SmsSend smsSend2 = smsSendDAO.getSmsSend(smsSend.getUuid());
						smsSend2.setStatus(thestatus);
						smsSend2.setPhoneNo(thenumber);
						smsSend2.setMessageId(themessage.replaceAll("[\r\n]+", " "));
						smsSend2.setCost(thecost);
						smsSendDAO.updateSmsSend(smsSend2); 
						}

					}

				}

				catch (Exception e) {
					e.printStackTrace(); 
					
				}
				
			 }else{
	 			  session.setAttribute(SessionConstants.STUDENT_ADD_OTHER_MONIES_ADD_ERROR,ERROR_STUDENT_INACTIVE ); 
	 		  }
		
		}

		response.sendRedirect("studentAddOM.jsp");  
		return; 

	}


	/**
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	    
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
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
