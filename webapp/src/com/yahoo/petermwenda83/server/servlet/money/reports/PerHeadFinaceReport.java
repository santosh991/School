/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.money.reports;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
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
import com.itextpdf.text.pdf.PdfWriter;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
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
public class PerHeadFinaceReport extends HttpServlet{
	
	    final String STATUS_ACTIVE = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
		
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
    studentFeeDAO = StudentFeeDAO.getInstance();
    CacheManager mgr = CacheManager.getInstance();
    schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
    studentDAO = StudentDAO.getInstance();

    examConfigDAO = ExamConfigDAO.getInstance();
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

			populatePDFDocument(school,examConfig,termFee,path);


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
	private void populatePDFDocument(SchoolAccount school, ExamConfig examConfig2, TermFee termFee,String path2) throws DocumentException {



		document.open();

		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("dd, MMM yyyy");



		BaseColor baseColor = new BaseColor(32,178,170);//maroon

		String formattedDate;
		Date date = new Date();
		formattedDate = formatter.format(date);

		Paragraph emptyline = new Paragraph(("                              "));

		Paragraph preface = new Paragraph();
		preface.add(createImage(path2));
		preface.add(new Paragraph(PDF_TITLE, smallBold));
		preface.add(new Paragraph(PDF_SUBTITLE, normalText));
		preface.add(new Paragraph("PRINTED ON :"+formattedDate + " FOR YEAR :"+examConfig.getYear()+"\n\n", smallBold));


		
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
		

		
		if(studentList !=null){
			for(Student students : studentList){
				
				List<StudentFee> feehistoryList = new ArrayList<>();
				if(studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(), students.getUuid(), examConfig.getTerm(), examConfig.getYear()) !=null){
					feehistoryList =studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(), students.getUuid(), examConfig.getTerm(), examConfig.getYear());
				}
				
				
				
				
				
			}
		 
		}
		
		
		
		
		
		
		

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
