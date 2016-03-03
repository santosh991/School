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
import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
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
public class AllStudentsFinaceReport extends HttpServlet{
	

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
		private static RoomDAO roomDAO;

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

       examConfigDAO = ExamConfigDAO.getInstance();
       termFeeDAO = TermFeeDAO.getInstance();
       roomDAO = RoomDAO.getInstance();

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
		
		
		List<Student> studentList = new ArrayList<Student>(); 
		studentList = studentDAO.getAllStudentList(school.getUuid());

		for(Student stu : studentList){
			studentAdmNoHash.put(stu.getUuid(),stu.getAdmno()); 
			String firstNameLowecase = stu.getFirstname().toLowerCase();
			String lastNameLowecase = stu.getLastname().toLowerCase();
			String surnameLowecase = stu.getSurname().toLowerCase();
			String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
			String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
			String formatedsurname = surnameLowecase.substring(0,1).toUpperCase()+surnameLowecase.substring(1);
			studNameHash.put(stu.getUuid(),formatedFirstname + " " + formatedLastname + " " + formatedsurname +"\n"); 
			firstnameHash.put(stu.getUuid(), formatedFirstname);
		}

		String pdfname =school.getUsername()+"Report.pdf";

		response.setHeader("Content-Disposition", "inline; filename= \"" +pdfname);

		examConfig = new ExamConfig();
		if(examConfigDAO.getExamConfig(school.getUuid()) !=null){
			examConfig = examConfigDAO.getExamConfig(school.getUuid());
		}

		List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
		classroomList = roomDAO.getAllRooms(school.getUuid()); 
		for(ClassRoom c : classroomList){
			roomHash.put(c.getUuid() , c.getRoomName());
		}
			

       
		TermFee termFee = new TermFee();
		if(termFeeDAO.getTermFee(school.getUuid(),examConfig.getTerm()) !=null){
			termFee = termFeeDAO.getTermFee(school.getUuid(),examConfig.getTerm());
		}
		



		PDF_TITLE = "STUDENT FEES PAYMENT REPORT \n";
		
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

			populatePDFDocument(school,examConfig,termFee,studentList,path);


		} catch (DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}




	}


	/**
	 * @param school
	 * @param examConfig2
	 * @param studentList 
	 * @param termFee
	 * @param feeList
	 * @param path2
	 * @throws DocumentException
	 */
	private void populatePDFDocument(SchoolAccount school, ExamConfig examConfig2, TermFee termFees,List<Student> studentList, String path2) throws DocumentException {
     
		
		
		try {
			document.open();

			
			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("dd, MMM yyyy");
			
			String formattedDate;
			Date date = new Date();
			formattedDate = formatter.format(date);

			
          
            
			List<StudentFee> list = new ArrayList<>();
		
			if(studentList !=null){
			      int count = 1;
				for(Student s : studentList){                              
					list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),s.getUuid(),examConfig.getTerm(),examConfig.getYear());
					
					BaseColor baseColor = new BaseColor(32,178,170);//maroon
					BaseColor Colormagenta = new BaseColor(176,196,222);//magenta
					
					Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

					Paragraph emptyline = new Paragraph(("                              "));

					//table here
					PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
					countHeader.setBackgroundColor(baseColor);
					countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					
					PdfPCell amountHeader = new PdfPCell(new Paragraph("Amount Paid",boldFont));
					amountHeader.setBackgroundColor(baseColor);
					amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction No",boldFont));
					transactionHeader.setBackgroundColor(baseColor);
					transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPTable myTable;

					myTable = new PdfPTable(3); 
					myTable.addCell(countHeader);
					myTable.addCell(amountHeader);
					myTable.addCell(transactionHeader);
					myTable.setWidthPercentage(100); 
					myTable.setWidths(new int[]{15,60,60});   
					myTable.setHorizontalAlignment(Element.ALIGN_LEFT);
					
					
					PdfPTable containerTable = new PdfPTable(2);  
					containerTable.setWidthPercentage(100); 
					containerTable.setWidths(new int[]{100,100}); 
					containerTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					

					Paragraph preface = new Paragraph();
					preface.add(createImage(path2));
					preface.add(new Paragraph(PDF_TITLE, smallBold));
					preface.add(new Paragraph(PDF_SUBTITLE, normalText));
					preface.add(new Paragraph("PRINTED ON :"+formattedDate + " FOR YEAR :"+examConfig.getYear()+"\n\n", smallBold));

					PdfPCell contheader = new PdfPCell(new Paragraph(("TERM " +examConfig.getTerm() + ": YEAR " + examConfig.getYear() +"\n\n" +("CLASS : " + roomHash.get(s.getClassRoomUuid()) +"\n")) +"",boldFont));
					contheader.setBackgroundColor(Colormagenta);
					contheader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell bodyheader = new PdfPCell(new Paragraph(("ADMISSION NUMBER : " + studentAdmNoHash.get(s.getUuid()) +"\n\n" +("STUDENT NAME : " + studNameHash.get(s.getUuid()) +"\n")),boldFont));
					bodyheader.setBackgroundColor(Colormagenta);
					bodyheader.setHorizontalAlignment(Element.ALIGN_LEFT);


					containerTable.addCell(bodyheader);
					containerTable.addCell(contheader);
					
					int mycount = 1;
					for(StudentFee fee : list){
					
                    
					myTable.addCell(mycount+"");
					myTable.addCell(nf.format(fee.getAmountPaid()));
					myTable.addCell(fee.getTransactionID());
					mycount++;
					}

					document.add(preface);
					document.add(emptyline);
					document.add(containerTable);      	  
					document.add(emptyline);
					document.add(myTable); 
					document.add(emptyline);
					document.newPage();
					
					count++;
					}
				
				}

			// step 5
			document.close();
		   }
		catch(DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	
	
		
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
	private static final long serialVersionUID = -9116260712424015666L;

}









// PDF FORMAT
		/*******************************************************************
		 * 
		 *            SCHOOL LOGO
		 *            
		 *            SCHOOL CONTACT DETAILS
		 *            
		 *            
		 *            STUDENT NAME : CLASS : TERM : YEAR
		 *            
		 *            
		 *            FEE PAYMWNT HISTORY
		 *            
		 *            AMOUNT PAID     TRANSACTIONID DATE   TERM FEE = 18,000
		 *            
		 *            1) 10,000         GHHSHSHHSJSJSJJS          
		 *            2) 2,000          JXJJJKJJJJJJSHDD
		 *               TOTALS                                    BALANCE
		 *               16,000                                     2,000
		 * 
		 * 
		 * 
		 * 
		 * 
		 *********************************************************************/
