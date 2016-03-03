/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.money.reports;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.StudentAmount;
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.money.StudentFeeDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.servlet.result.PdfUtil;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author peter
 *
 */ 
public class FinancialReport extends HttpServlet{
	
	final String STATUS_ACTIVE = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";


	//private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLDITALIC);
	private Font normalText = new Font(Font.FontFamily.COURIER, 10,Font.UNDERLINE);
	private Document document;
	private PdfWriter writer;

	private Logger logger;
	ExamConfig examConfig;

	private String PDF_TITLE ="";
	private String PDF_SUBTITLE ="";

	Locale locale = new Locale("en","KE"); 
	NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

	private Cache schoolaccountCache;

	private static StudentAmountDAO studentAmountDAO;
	private static StudentFeeDAO studentFeeDAO;
	private static StudentDAO studentDAO;
	private static ExamConfigDAO examConfigDAO;
	private static TermFeeDAO termFeeDAO;

	String USER= "";
	String path ="";

	/**  
	 *
	 * @param config
	 * @throws ServletException
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		logger = Logger.getLogger(this.getClass());
		studentAmountDAO = StudentAmountDAO.getInstance();
		studentFeeDAO = StudentFeeDAO.getInstance();
		CacheManager mgr = CacheManager.getInstance();
		schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
		studentDAO = StudentDAO.getInstance();

		examConfigDAO = ExamConfigDAO.getInstance();
		studentAmountDAO = StudentAmountDAO.getInstance();
		termFeeDAO = TermFeeDAO.getInstance();

		USER = System.getProperty("user.name");
		path = "/home/"+USER+"/school/logo/logo.png";


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		response.setContentType("application/pdf");




		SchoolAccount school = new SchoolAccount();
		String schoolusername = "";

		if(session !=null){
			schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);

		}
		net.sf.ehcache.Element element;

		element = schoolaccountCache.get(schoolusername);
		if(element !=null){
			school = (SchoolAccount) element.getObjectValue();
		}

		String pdfname =school.getUsername()+"financialReport.pdf";

		response.setHeader("Content-Disposition", "inline; filename= \"" +pdfname);

		examConfig = new ExamConfig();
		if(examConfigDAO.getExamConfig(school.getUuid()) !=null){
			examConfig = examConfigDAO.getExamConfig(school.getUuid());
		}


		List<StudentAmount> feeList = new ArrayList<>();
		if(studentAmountDAO.getAmountList(school.getUuid()) !=null){
			feeList = studentAmountDAO.getAmountList(school.getUuid());
		}

		TermFee termFee = new TermFee();
		if(termFeeDAO.getTermFee(school.getUuid(),examConfig.getTerm()) !=null){
			termFee = termFeeDAO.getTermFee(school.getUuid(),examConfig.getTerm());
		}





		PDF_TITLE = "FINANCIAL ANALYSIS REPORT \n";
		// +"TERM :" + examConfig.getTerm() + " YEAR :"+examConfig.getYear()+" TERM FEE "+ nf.format(termFee.getTermAmount())+"\n\n\n";

		PDF_SUBTITLE =     school.getSchoolName()+"\n"
				+ "P.O BOX "+school.getPostalAddress()+"\n" 
				+ ""+school.getTown().toUpperCase()+ " - KENYA\n" 
				+ "" + school.getMobile()+"\n"
				+ "" + school.getEmail()+"\n"; 

		document = new Document(PageSize.A4, 46, 46, 64, 64);

		try {
			writer = PdfWriter.getInstance(document, response.getOutputStream());


			PdfUtil event = new PdfUtil();
			writer.setBoxSize("art", new Rectangle(36, 54, 459, 588));
			writer.setPageEvent(event);

			populatePDFDocument(school,examConfig,termFee,feeList,path);


		} catch (DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}




	}


	/**
	 * @param school
	 * @param examConfig2
	 * @param termFee
	 * @param feeList
	 * @param path2
	 * @throws DocumentException
	 */
	private void populatePDFDocument(SchoolAccount school, ExamConfig examConfig2, TermFee termFee,
			List<StudentAmount> feeList, String path2) throws DocumentException {



		document.open();

		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("dd, MMM yyyy");



		BaseColor baseColor = new BaseColor(32,178,170);//maroon

		String formattedDate;
		Date date = new Date();
		formattedDate = formatter.format(date);

		Paragraph emptyline = new Paragraph(("                              "));


		Paragraph current = new Paragraph(("CURRENT FEE ANALYSIS"),smallBold);
		Paragraph termOne = new Paragraph(("TERM ONE FEE ANALYSIS"),smallBold);
		Paragraph termTwo = new Paragraph(("TERM TWO FEE ANALYSIS"),smallBold);
		Paragraph termThree = new Paragraph(("TERM THREE FEE ANALYSIS"),smallBold);

		Paragraph preface = new Paragraph();
		preface.add(createImage(path2));
		preface.add(new Paragraph(PDF_TITLE, smallBold));
		preface.add(new Paragraph(PDF_SUBTITLE, normalText));
		preface.add(new Paragraph("PRINTED ON :"+formattedDate + " FOR YEAR :"+examConfig.getYear()+"\n\n", smallBold));


		PdfPTable FinanceReportTable = new PdfPTable(2);  

		PdfPCell totalDuesHeader = new PdfPCell(new Paragraph("Total Dues",smallBold));
		totalDuesHeader.setBackgroundColor(baseColor);
		totalDuesHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell totalOverPaysHeader = new PdfPCell(new Paragraph("Total OverPay",smallBold));
		totalOverPaysHeader.setBackgroundColor(baseColor);
		totalOverPaysHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		FinanceReportTable.addCell(totalDuesHeader);
		FinanceReportTable.addCell(totalOverPaysHeader);
		FinanceReportTable.setWidthPercentage(100); 
		FinanceReportTable.setWidths(new int[]{100,100});   
		FinanceReportTable.setHorizontalAlignment(Element.ALIGN_LEFT);



		//TERM 1
		PdfPTable termOneTable = new PdfPTable(2); 
		//table here
		PdfPCell termoneTotalHeader = new PdfPCell(new Paragraph("Total Paid Term 1",smallBold));
		termoneTotalHeader.setBackgroundColor(baseColor);
		termoneTotalHeader.setHorizontalAlignment(Element.ALIGN_LEFT);


		PdfPCell termoneExpectHeader = new PdfPCell(new Paragraph("Total Term 1 Fee",smallBold));
		termoneExpectHeader.setBackgroundColor(baseColor);
		termoneExpectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		termOneTable.addCell(termoneTotalHeader);
		termOneTable.addCell(termoneExpectHeader);
		
		termOneTable.setWidthPercentage(100); 
		termOneTable.setWidths(new int[]{100,100});   
		termOneTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		//TERM 2
		PdfPTable termTwoTable = new PdfPTable(3); 
		//table here
		PdfPCell termtwoTotalHeader = new PdfPCell(new Paragraph("Total Paid Term 2",smallBold));
		termtwoTotalHeader.setBackgroundColor(baseColor);
		termtwoTotalHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell termtwoExpectHeader = new PdfPCell(new Paragraph("Total Term 2 Fee",smallBold));
		termtwoExpectHeader.setBackgroundColor(baseColor);
		termtwoExpectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell realtermtwoExpectHeader = new PdfPCell(new Paragraph("Total Expected Amnt",smallBold));
		realtermtwoExpectHeader.setBackgroundColor(baseColor);
		realtermtwoExpectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		termTwoTable.addCell(termtwoTotalHeader);
		termTwoTable.addCell(termtwoExpectHeader);
		termTwoTable.addCell(realtermtwoExpectHeader);
		
		termTwoTable.setWidthPercentage(100); 
		termTwoTable.setWidths(new int[]{80,80,80});   
		termTwoTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		//TERM 3
		PdfPTable termThreeTable = new PdfPTable(3); 

		//table here
		PdfPCell termthreeTotalHeader = new PdfPCell(new Paragraph("Total Paid Term 3",smallBold));
		termthreeTotalHeader.setBackgroundColor(baseColor);
		termthreeTotalHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell termthreeExpectHeader = new PdfPCell(new Paragraph("Total Term 3 Fee",smallBold));
		termthreeExpectHeader.setBackgroundColor(baseColor);
		termthreeExpectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		PdfPCell realtermtthreeExpectHeader = new PdfPCell(new Paragraph("Total Expected Amnt",smallBold));
		realtermtthreeExpectHeader.setBackgroundColor(baseColor);
		realtermtthreeExpectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		termThreeTable.addCell(termthreeTotalHeader);
		termThreeTable.addCell(termthreeExpectHeader);
		termThreeTable.addCell(realtermtthreeExpectHeader);
		
		termThreeTable.setWidthPercentage(100); 
		termThreeTable.setWidths(new int[]{80,80,80});   
		termThreeTable.setHorizontalAlignment(Element.ALIGN_LEFT);



		List<Student> studentList = new ArrayList<>(); 

		int studentCount = 0;
		if(studentList !=null){
			studentList = studentDAO.getAllStudentList(school.getUuid()); 
			for(Student stu : studentList) {
				if(StringUtils.equals(stu.getStatusUuid(),STATUS_ACTIVE)){
				stu.getUuid();
				studentCount ++;
				}
			}
		}
		
		

		//TERM 1
		double termOneAmount = 0;
		double termOneTotals = 0;
		double termOneFee = 0;
		double termOneTotalExpect = 0;
		

		TermFee termoneFee = new TermFee();
		if(termFeeDAO.getTermFee(school.getUuid(),"1") !=null){
			termoneFee = termFeeDAO.getTermFee(school.getUuid(),"1");
			termOneFee = termoneFee.getTermAmount();
		}

		List<StudentFee> studentfeeListTerm1 = new ArrayList<StudentFee>();
		if(studentFeeDAO.getStudentFeeList(school.getUuid(),"1",examConfig.getYear()) !=null){
			studentfeeListTerm1 = studentFeeDAO.getStudentFeeList(school.getUuid(),"1",examConfig.getYear());      	 

		}
		if(studentfeeListTerm1 !=null){
			for(StudentFee term1 : studentfeeListTerm1){
				termOneAmount = term1.getAmountPaid();
				termOneTotals +=termOneAmount;
				
			}
		}

		termOneTotalExpect = termOneFee * studentCount;

		
		termOneTable.addCell(nf.format(termOneTotals));
		termOneTable.addCell(nf.format(termOneTotalExpect));

		//TERM 2

		double termTwoAmount = 0;
		double termTwoTotals = 0;
		double termTwoFee = 0;
		double termTwoTotalExpect = 0;
		
		


		TermFee termtwoFee = new TermFee();
		if(termFeeDAO.getTermFee(school.getUuid(),"2") !=null){
			termtwoFee = termFeeDAO.getTermFee(school.getUuid(),"2");
			termTwoFee = termtwoFee.getTermAmount();
		}
        
		// TERM 2
		List<StudentFee> studentfeeListTerm2 = new ArrayList<StudentFee>();
		if(studentFeeDAO.getStudentFeeList(school.getUuid(),"2",examConfig.getYear()) !=null){
			studentfeeListTerm2 = studentFeeDAO.getStudentFeeList(school.getUuid(),"2",examConfig.getYear());      	 

		}
		
		
		double TotalprevTermPaid = 0;
		double prevTermbalance = 0;
		double REAL_TOTAL_EXPECTED = 0;

		double REAL_TOTAL_UN_PAID = 0;
		double ALREADYPAIDAMOUNT = 0;
		double absALREADYPAIDAMOUNT = 0;
		double TotalabsALREADYPAIDAMOUNT = 0;
		double TotalabsALREADYPAIDAMOUNT2 = 0;
		 
		if(studentList !=null){
			for(Student students : studentList){
				
				//WE GO BACK TO PREV TERM 1
				List<StudentFee> studentfeeListPrevTerm = new ArrayList<StudentFee>();
				if(studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),students.getUuid(),"1",examConfig.getYear()) !=null){
					studentfeeListPrevTerm = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),students.getUuid(),"1",examConfig.getYear());      	 

				}
				 double prevTermPaid = 0;
				
				
				 for(StudentFee PrevTerm : studentfeeListPrevTerm){
					 prevTermPaid = PrevTerm.getAmountPaid();
					 TotalprevTermPaid = 0;
					 TotalprevTermPaid +=prevTermPaid;
					 prevTermbalance = termOneFee - TotalprevTermPaid ;
	             
	                 if(prevTermbalance >0){ 
	                  REAL_TOTAL_UN_PAID = 0;
	       			  REAL_TOTAL_UN_PAID += (prevTermbalance+termTwoFee);
	       			
	       			
	       		     }else{
	       		      TotalabsALREADYPAIDAMOUNT = 0;
	       			  ALREADYPAIDAMOUNT = prevTermbalance;
	       			  absALREADYPAIDAMOUNT = Math.abs(ALREADYPAIDAMOUNT);
	       			  TotalabsALREADYPAIDAMOUNT +=(termTwoFee-absALREADYPAIDAMOUNT);
	       			  TotalabsALREADYPAIDAMOUNT2 +=TotalabsALREADYPAIDAMOUNT;
	       		  }
	                 
	             
				 }
				  
				 
		  		  REAL_TOTAL_EXPECTED = 0;
		  		  REAL_TOTAL_EXPECTED +=(TotalabsALREADYPAIDAMOUNT2 + REAL_TOTAL_UN_PAID);
		  		 
			  }
			}
		
		 
		
		
		//GO BACK TO TERM 1, GET TERM 1 FEE, 18000, FIND WHAT THE STUDENT PAID , IF AMOUNT PAID IS +VE 
		// UNPAIDAMOUNT = AMOUNT    ,
		// CURRENTLY WE DEMAND THE STUDENT , CURRENT TERM FEE
		//TOTALUNPAIDAMOUNT = UNPAINDAOUNT + CURRENT TERM FEE
		//REAL_TOTAL_UN_PAID +=TOTALUNPAIDAMOUNT;
		
		//ELSE IF BALANCE IS NEGATIVE
		//CONVERT TO POSITVE CALL IT , ALREADYPAIDAMOUNT 
		//CURRENTDEMAND = CURRENT TERM FEE - ALREADYPAIDAMOUNT
		
		//TOTALEXPECTEDAMOUNT =  TOTALUNPAIDAMOUNT+CURRENTDEMAND
		//REAL_TOTAL_EXPECTED += TOTALEXPECTEDAMOUNT; //( Total Expected Amnt)
		
		
		

		if(studentfeeListTerm2 !=null){
			for(StudentFee term2 : studentfeeListTerm2){
				termTwoAmount = term2.getAmountPaid();
				termTwoTotals = termTwoTotals+termTwoAmount;
			}
		}

		termTwoTotalExpect = termTwoFee * studentCount;
		
		termTwoTable.addCell(nf.format(termTwoTotals));
		termTwoTable.addCell(nf.format(termTwoTotalExpect));
		termTwoTable.addCell(nf.format(REAL_TOTAL_EXPECTED));
		
		//TERM 3
		double termThreeAmount = 0;
		double termThreeTotals = 0;
		double termThreeFee = 0;
		double termThreeTotalExpect = 0;
		
		TermFee termthreeFee = new TermFee();
		if(termFeeDAO.getTermFee(school.getUuid(),"3") !=null){
			termthreeFee = termFeeDAO.getTermFee(school.getUuid(),"3");
			termThreeFee = termthreeFee.getTermAmount();
		}

         //TERM 3
		List<StudentFee> studentfeeListTerm3 = new ArrayList<StudentFee>();
		if(studentFeeDAO.getStudentFeeList(school.getUuid(),"3",examConfig.getYear()) !=null){
			studentfeeListTerm3 = studentFeeDAO.getStudentFeeList(school.getUuid(),"3",examConfig.getYear());      	 

		} 
		if(studentfeeListTerm3 !=null){
			for(StudentFee term3 : studentfeeListTerm3){
				termThreeAmount = term3.getAmountPaid();
				termThreeTotals = termThreeTotals+termThreeAmount;
			}
		}

		termThreeTotalExpect = termThreeFee * studentCount;
		
		//GO BACK TO TERM 2
		double TotalprevTerm2Paid = 0;
		double prevTerm2balance = 0;
		double REAL_TOTAL_EXPECTED_T_2 = 0;

		double REAL_TOTAL_UN_PAID_T_2 = 0;
		double ALREADY_PAID_AMOUNT_T_2 = 0;
		double abs_ALREADY_PAID_AMOUNT_T_2 = 0;
		double Total_abs_ALREADY_PAID_AMOUNT_T_2 = 0;
		
		 
		if(studentList !=null){
			for(Student students : studentList){
				
				//WE GO BACK TO PREV TERM 2
				List<StudentFee> studentfeeListPrevTerm = new ArrayList<StudentFee>();
				if(studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),students.getUuid(),"2",examConfig.getYear()) !=null){
					studentfeeListPrevTerm = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),students.getUuid(),"2",examConfig.getYear());      	 

				}
				 double prevTermPaid = 0;
				
				
				 for(StudentFee PrevTerm : studentfeeListPrevTerm){
					 prevTermPaid = PrevTerm.getAmountPaid();
					 TotalprevTerm2Paid = 0;
					 TotalprevTerm2Paid +=prevTermPaid;
					 prevTerm2balance = (termTwoFee - TotalprevTerm2Paid);
	             
	                 if(prevTerm2balance >0){ 
	                	 REAL_TOTAL_UN_PAID_T_2 = 0;
	                	 REAL_TOTAL_UN_PAID_T_2 += (prevTerm2balance+termThreeAmount);
	       			
	       			
	       		     }else{
	       		      Total_abs_ALREADY_PAID_AMOUNT_T_2 = 0;
	       		      ALREADY_PAID_AMOUNT_T_2 = REAL_TOTAL_UN_PAID_T_2;
	       		      abs_ALREADY_PAID_AMOUNT_T_2 = Math.abs(ALREADY_PAID_AMOUNT_T_2);
	       		      Total_abs_ALREADY_PAID_AMOUNT_T_2 +=(termThreeAmount-abs_ALREADY_PAID_AMOUNT_T_2);
	       		      Total_abs_ALREADY_PAID_AMOUNT_T_2 +=Total_abs_ALREADY_PAID_AMOUNT_T_2;
	       		  }
	                 
	             
				 }
				  
				 
				 REAL_TOTAL_EXPECTED_T_2 = 0;
				 REAL_TOTAL_EXPECTED_T_2 +=(Total_abs_ALREADY_PAID_AMOUNT_T_2 + REAL_TOTAL_UN_PAID_T_2);
		  		 
			  }
			}

		
		termThreeTable.addCell(nf.format(termThreeTotals));
		termThreeTable.addCell(nf.format(termThreeTotalExpect));
		termThreeTable.addCell(nf.format(REAL_TOTAL_EXPECTED_T_2));
		
		// CURRENT ANALYSIS 
		double amountpaid = 0;
		double balance = 0;
		double termfee = 0;
		double grandtotal = 0;  
		double theTotal = 0;  
		double totalDue = 0;

 
		double realDues = 0;
		double overPays = 0;
		double aBSoverPays = 0;
		double thisBalance = 0;
		termfee = termFee.getTermAmount();

		if(feeList !=null){
			for(StudentAmount fees : feeList){

				theTotal = fees.getAmount();  
				thisBalance =  termfee - theTotal;

				grandtotal = grandtotal+theTotal;

				balance = termfee - amountpaid;
				totalDue = totalDue + balance;

				if(thisBalance < 0){
					overPays +=thisBalance;
				}else{
					realDues +=thisBalance;
				}



			}
		}

		aBSoverPays = Math.abs(overPays);



		
		FinanceReportTable.addCell(nf.format(realDues));
		FinanceReportTable.addCell(nf.format(aBSoverPays));


		
		document.add(emptyline);
		document.add(preface);
		document.add(emptyline);
		document.add(current);
		document.add(emptyline);
		document.add(FinanceReportTable); 

		//TERM 1
		document.add(emptyline);
		document.add(termOne);
		document.add(emptyline);
		document.add(termOneTable);
		//TERM 2
		document.add(emptyline);
		document.add(termTwo);
		document.add(emptyline);
		document.add(termTwoTable);
		//TERM 3
		document.add(emptyline);       
		document.add(termThree);
		document.add(emptyline);
		document.add(termThreeTable);
		
		




		// step 5
		document.close();


	}





	/**
	 * @param realPath
	 * @return
	 */
	private Element createImage(String realPath) {
		Image imgLogo = null;

		try {
			imgLogo = Image.getInstance(realPath);
			imgLogo.scaleToFit(150, 150);
			imgLogo.setAlignment(Element.ALIGN_CENTER);

		} catch (BadElementException e) {
			logger.error("BadElementException Exception while creating an image");
			logger.error(ExceptionUtils.getStackTrace(e));

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException for the path");
			logger.error(ExceptionUtils.getStackTrace(e));

		} catch (IOException e) {
			logger.error("IOException while creating an image");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return imgLogo;
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
	private static final long serialVersionUID = -6769826759676209553L;

}
