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
import java.util.HashMap;
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
import com.yahoo.petermwenda83.bean.money.ClosingBalance;
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.othermoney.Otherstype;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMonies;
import com.yahoo.petermwenda83.bean.othermoney.TermOtherMonies;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.money.StudentFeeDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.OtherstypeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.StudentOtherMoniesDAO;
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
public class PerClassClearance extends HttpServlet{
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd, MMM yyyy");
	SimpleDateFormat yearformatter = new SimpleDateFormat("yyyy");
	BaseColor baseColor = new BaseColor(32,178,170);//maroon
	BaseColor Colormagenta = new BaseColor(176,196,222);//magenta
	Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLDITALIC);
	private Font normalText = new Font(Font.FontFamily.COURIER, 8,Font.UNDERLINE);
	
	private Document document;
	private PdfWriter writer;

	private Logger logger;
	ExamConfig examConfig;

	private String PDF_TITLE ="";
	private String PDF_SUBTITLE ="";

	Locale locale = new Locale("en","KE"); 
	NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

	private static StudentFeeDAO studentFeeDAO;
	private static StudentDAO studentDAO;
	private static ExamConfigDAO examConfigDAO;
	private static ClosingBalanceDAO closingBalanceDAO;
	private static TermFeeDAO termFeeDAO;
	private static StudentAmountDAO studentAmountDAO;

	private static StudentOtherMoniesDAO studentOtherMoniesDAO;
	
	private Cache schoolaccountCache;


	TermOtherMonies termOtherMonies;
	StudentOtherMonies studentOtherMonies;

	String USER= "";
	String path ="";
	
	HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	HashMap<String, String> firstnameHash = new HashMap<String, String>();
	HashMap<String, String> studNameHash = new HashMap<String, String>();
	HashMap<String, String> roomHash = new HashMap<String, String>();

	/**  
 *
 * @param config
 * @throws ServletException
 */
@Override
public void init(ServletConfig config) throws ServletException {
    super.init(config);

    logger = Logger.getLogger(this.getClass());
	studentFeeDAO = StudentFeeDAO.getInstance();
	CacheManager mgr = CacheManager.getInstance();
	schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
	studentDAO = StudentDAO.getInstance();
	closingBalanceDAO = ClosingBalanceDAO.getInstance();
	examConfigDAO = ExamConfigDAO.getInstance();
	termFeeDAO = TermFeeDAO.getInstance();
	studentAmountDAO = StudentAmountDAO.getInstance();
	studentOtherMoniesDAO = StudentOtherMoniesDAO.getInstance();
	
	USER = System.getProperty("user.name");
	path = "/home/"+USER+"/school/logo/logo.png";
 
   
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

	HttpSession session = request.getSession(true);

	response.setContentType("application/pdf");

	String classUuid = StringUtils.trimToEmpty(request.getParameter("classUuid"));
	
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

	String pdfname =school.getUsername()+"Report.pdf";

	response.setHeader("Content-Disposition", "inline; filename= \"" +pdfname);

	examConfig = new ExamConfig();
	if(examConfigDAO.getExamConfig(school.getUuid()) !=null){
		examConfig = examConfigDAO.getExamConfig(school.getUuid());
	}



	TermFee termFee = new TermFee();
	if(termFeeDAO.getFee(school.getUuid(),examConfig.getTerm(),examConfig.getYear()) !=null){
		termFee = termFeeDAO.getFee(school.getUuid(),examConfig.getTerm(),examConfig.getYear());
	}

	Student stuudent = new Student();
	stuudent = studentDAO.getStudentByuuid(school.getUuid(), classUuid);

	if(stuudent != null){
		studentAdmNoHash.put(stuudent.getUuid(),stuudent.getAdmno()); 
		String firstNameLowecase = stuudent.getFirstname().toLowerCase();
		String lastNameLowecase = stuudent.getLastname().toLowerCase();
		String surnameLowecase = stuudent.getSurname().toLowerCase();
		String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
		String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
		String formatedsurname = surnameLowecase.substring(0,1).toUpperCase()+surnameLowecase.substring(1);
		studNameHash.put(stuudent.getUuid(),formatedFirstname + " " + formatedLastname + " " + formatedsurname +"\n"); 
		firstnameHash.put(stuudent.getUuid(), formatedFirstname);

		pdfname =new Date()+stuudent.getAdmno()+"_Fee_Statement.pdf"; 
		response.setHeader("Content-Disposition", "inline; filename= \"" +pdfname);

	}



	PDF_TITLE = "FINANCIAL ANALYSIS REPORT \n";

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

		populatePDFDocument(school,examConfig,termFee,stuudent,path);


	} catch (DocumentException e) {
		logger.error("DocumentException while writing into the document");
		logger.error(ExceptionUtils.getStackTrace(e));
	}




	}


	/**
	 * @param school
	 * @param examConfig2
	 * @param termFee
	 * @param stuudent 
	 * @param feeList
	 * @param path2
	 * @throws DocumentException
	 */
	private void populatePDFDocument(SchoolAccount school, ExamConfig examConfig2, TermFee termFee,Student stuudent, String path2) throws DocumentException {
     
		/**  Algorithm to clear students
		 *   1)if student has fee balance, they must clear the balance first
		 *     else if student has over payment 
		 *          1)move the over payment to pocket money account so that he/she can be able to withdraw 
		 *          
		 *          otherwise clear the student, print clearance form
		 *          
		 *               ***********************************************************
		 *          
		 *                           
		 *                    (THIS WILL ALSO BE THE LEAVING CERTIFICATE)
		 *                           
		 *               ***********************************************************
		 *                                * COAT OF ARM *
		 *                                
		 *                                ED/B 100(Rev)
		 *                                
		 *                                REPUBLIC OF KENYA
		 *                                MINISTRY OF EDUCATION
		 *                    KENYA SECONDARY SCHOOL LEAVING CERTIFICATE 
		 *                                
		 *               SCHOOL DETAILS
		 *               
		 *               STUDENT *ADM NO*
		 *               
		 *               THIS IS TO CERTIFY THAT ______________NAME__________________________
		 *               Entered this school on __________year______________  and was enrolled 
		 *               in Form ____FOUR____and left on _____NOV,2010 ______from Form __4____
		 *               having satisfactorily completed the approved Course from Form __4____
		 *               
		 *               Date of Birth (in admission Register) ________ 19*** ________________
		 *               
		 *               *Headteacher's report on the pupil's ability,industry and conduct*
		 *               
		 *               __Above average student, A very disciplined and hard working boy, A Memeber 
		 *               of __clubs__
		 *               
		 *               
		 *               Pupil's Signature.___________
		 *               Date of Issue._______________
		 *               
		 *               Head teacher Signature.___________
		 *               
		 *               This certificate was issued without any erasure or alteration whatsoever.
		 *               
		 *               GPK(L)
		 *               
		 *               
		 *               
		 *                           
		 *                           
		 *           ********************************************************************************
		 *                          The Clearance Report 
		 *           ******************************************************************************
		 *          
		 * 
		 */


		document.open();


		Paragraph preface = new Paragraph();
		preface.add(new Paragraph(PDF_TITLE, normalText));
		preface.add(new Paragraph(PDF_SUBTITLE, normalText));
		preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()))),normalText));
		preface.add(new Paragraph("Printed on: " + formatter.format(new Date()) + " Term: " + examConfig2.getTerm()+" Year: " + examConfig2.getYear()+"\n\n", normalText));
		document.add(preface);
		

		//Get payments for the 'start year' and 'start term' 
		List<StudentFee> list = new ArrayList<>();               
		list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),examConfig2.getTerm(),examConfig2.getYear());

		int mycount = 1;
		double totalpaid = 0;
		if(list !=null){
			for(StudentFee fee : list){
				totalpaid +=fee.getAmountPaid();
				mycount++;
			}



			double prevtermbalance = 0;
			ClosingBalance closingBalance = new ClosingBalance();
			if(closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), examConfig2.getTerm(), examConfig2.getYear()) !=null){
				closingBalance = closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), examConfig2.getTerm(), examConfig2.getYear());

			}


			TermFee termFeenew = new TermFee();
			if(termFeeDAO.getFee(school.getUuid(),examConfig2.getTerm(),examConfig.getYear()) !=null){
				termFeenew = termFeeDAO.getFee(school.getUuid(),examConfig2.getTerm(),examConfig.getYear());
			}
			
			double other_m_amount = 0;
			double other_m_totals = 0;
			
			List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),examConfig2.getTerm(), examConfig2.getYear());
			if(stuOthermoniList !=null){
				
				for(StudentOtherMonies som  : stuOthermoniList){
					other_m_amount = som.getAmountPiad();
					other_m_totals +=other_m_amount;
				}
			}

			prevtermbalance = closingBalance.getClosingAmount();
			PdfPCell closeHeader = new PdfPCell(new Paragraph("Total Paid =" + nf.format(totalpaid) +""
					+ "                "+"Fee Balance ="+nf.format(termFeenew.getTermAmount() - prevtermbalance - totalpaid + other_m_totals) +" "
					+ "                "+"Term Fee ="+nf.format(termFeenew.getTermAmount()), smallBold));
			//closeHeader.setBackgroundColor(baseColor);
			closeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			closeHeader.setColspan(6); 


		}



		//START
		PdfPCell mycountHeader = new PdfPCell(new Paragraph("S.N",boldFont));
		mycountHeader.setBackgroundColor(baseColor);
		mycountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell ptypeHeader = new PdfPCell(new Paragraph("Payment Type",boldFont));
		ptypeHeader.setBackgroundColor(baseColor);
		ptypeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell amountpaidHeader = new PdfPCell(new Paragraph("Amount",boldFont));
		amountpaidHeader.setBackgroundColor(baseColor);
		amountpaidHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell oTernHeader = new PdfPCell(new Paragraph("Term",boldFont));
		oTernHeader.setBackgroundColor(baseColor);
		oTernHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell pYearHeader = new PdfPCell(new Paragraph("Year",boldFont));
		pYearHeader.setBackgroundColor(baseColor);
		pYearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPTable OtherPayTable;

		OtherPayTable = new PdfPTable(5); 
		OtherPayTable.addCell(mycountHeader);
		OtherPayTable.addCell(ptypeHeader);
		OtherPayTable.addCell(amountpaidHeader);
		OtherPayTable.addCell(oTernHeader);
		OtherPayTable.addCell(pYearHeader);
		OtherPayTable.setWidthPercentage(100); 
		OtherPayTable.setWidths(new int[]{8,35,40,35,35});   
		OtherPayTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		OtherstypeDAO otherstypeDAO = OtherstypeDAO.getInstance();
		List<Otherstype> othertypeList = new ArrayList<Otherstype>(); 
		othertypeList = otherstypeDAO.gettypeList(school.getUuid());  
		HashMap<String, String> moneytypeHash = new HashMap<String, String>(); 
		if(othertypeList !=null){
			for(Otherstype om : othertypeList){
				moneytypeHash.put(om.getUuid(),om.getType());
			}
		}
		List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
		if(studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),examConfig2.getTerm(), examConfig2.getYear()) !=null){
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),examConfig2.getTerm(), examConfig2.getYear());
		}  


		String somtype = "";
		int countOther = 1;
		if(stuOthermoniList !=null){
			for(StudentOtherMonies som  : stuOthermoniList){

				somtype = moneytypeHash.get(som.getOtherstypeUuid()); 

				OtherPayTable.addCell(new Paragraph(countOther+"",boldFont));
				OtherPayTable.addCell(new Paragraph(somtype+"",boldFont));
				OtherPayTable.addCell(new Paragraph(nf.format(som.getAmountPiad())+"",boldFont));
				OtherPayTable.addCell(new Paragraph(som.getTerm()+"",boldFont));
				OtherPayTable.addCell(new Paragraph(som.getYear()+"",boldFont));

				countOther++;
			}
		}

		//END document.add(OtherPayTable); 
		
		
		
		
		
		
		

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



}
