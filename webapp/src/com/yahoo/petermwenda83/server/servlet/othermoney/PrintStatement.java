/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.othermoney;

import java.io.IOException;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.money.ClosingBalance;
import com.yahoo.petermwenda83.bean.money.StudentAmount;
import com.yahoo.petermwenda83.bean.money.StudentFee;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.othermoney.Otherstype;
import com.yahoo.petermwenda83.bean.othermoney.RevertedMoney;
import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMonies;
import com.yahoo.petermwenda83.bean.othermoney.TermOtherMonies;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.money.StudentFeeDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.OtherstypeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.RevertedMoneyDAO;
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
public class PrintStatement extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3176338567192522049L;


	SimpleDateFormat formatter = new SimpleDateFormat("dd, MMM yyyy");
	SimpleDateFormat yearformatter = new SimpleDateFormat("yyyy");
	BaseColor baseColor = new BaseColor(32,178,170);//maroon
	BaseColor Colormagenta = new BaseColor(176,196,222);//magenta
	Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);

	private Cache schoolaccountCache;

	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLDITALIC);
	private Font normalText = new Font(Font.FontFamily.COURIER, 8,Font.UNDERLINE);
	private Document document;
	private PdfWriter writer;

	private Logger logger;

	private String PDF_TITLE ="";
	private String PDF_SUBTITLE ="";

	Locale locale = new Locale("en","KE"); 
	NumberFormat nf = NumberFormat.getCurrencyInstance(locale);


	private static StudentFeeDAO studentFeeDAO;
	private static StudentDAO studentDAO;
	private static ExamConfigDAO examConfigDAO;
	private static ClosingBalanceDAO closingBalanceDAO;
	private static TermFeeDAO termFeeDAO;
	private static RoomDAO roomDAO;
	private static StudentAmountDAO studentAmountDAO;
	private static RevertedMoneyDAO revertedMoneyDAO;

	private static StudentOtherMoniesDAO studentOtherMoniesDAO;


	TermOtherMonies termOtherMonies;
	StudentOtherMonies studentOtherMonies;
	Otherstype otherstype;

	String USER= "";
	String path ="";
	String pdfname = "";


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
		roomDAO = RoomDAO.getInstance();
		studentAmountDAO = StudentAmountDAO.getInstance();
		studentOtherMoniesDAO = StudentOtherMoniesDAO.getInstance();
		revertedMoneyDAO = RevertedMoneyDAO.getInstance();


		USER = System.getProperty("user.name");
		path = "/home/"+USER+"/school/logo/logo.png";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		response.setContentType("application/pdf");

		String studentuuid = StringUtils.trimToEmpty(request.getParameter("studentuuid"));

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

		Student stuudent = new Student();
		stuudent = studentDAO.getStudentByuuid(school.getUuid(), studentuuid);

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



		ExamConfig examConfig = new ExamConfig();
		if(examConfigDAO.getExamConfig(school.getUuid()) !=null){
			examConfig = examConfigDAO.getExamConfig(school.getUuid());
		}

		List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
		classroomList = roomDAO.getAllRooms(school.getUuid()); 
		for(ClassRoom c : classroomList){
			roomHash.put(c.getUuid() , c.getRoomName());
		}






		PDF_TITLE = "STUDENT FEES PAYMENT REPORT \n";

		PDF_SUBTITLE =     school.getSchoolName()+"\n"
				+ "P.O BOX "+school.getPostalAddress()+"\n" 
				+ ""+school.getTown().toUpperCase()+ " - Kenya\n" 
				+ "" + school.getMobile()+"\n"
				+ "" + school.getEmail()+"\n"; 

		document = new Document(PageSize.A4, 40, 40, 60, 60);

		try {
			writer = PdfWriter.getInstance(document, response.getOutputStream());


			PdfUtil event = new PdfUtil();
			writer.setBoxSize("art", new Rectangle(46, 46, 400, 400));
			writer.setPageEvent(event);

			if(school !=null && examConfig !=null && stuudent !=null){
				populatePDFDocument(school,examConfig,stuudent,path);
			}



		} catch (DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}









	}

	private void populatePDFDocument(SchoolAccount school, ExamConfig examConfig2, Student stuudent,
			String path2) {

		try {
			document.open();
			
			Paragraph preface = new Paragraph();
			preface.add(new Paragraph(PDF_TITLE, normalText));
			preface.add(new Paragraph(PDF_SUBTITLE, normalText));
			preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()))),normalText));
			preface.add(new Paragraph("Printed on: " + formatter.format(new Date()) + " Term: " + examConfig2.getTerm()+" Year: " + examConfig2.getYear()+"\n\n", normalText));
			document.add(preface);
			
			Date admissionDate = stuudent.getAdmissionDate();
			String regterm = stuudent.getRegTerm();

			String admYear = yearformatter.format(admissionDate);

			compute(admYear,regterm,school,stuudent,examConfig2);
			
			//Reverted money
			PdfPCell mycountHeader = new PdfPCell(new Paragraph("S.N",boldFont));
			mycountHeader.setBackgroundColor(baseColor);
			mycountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell ptypeHeader = new PdfPCell(new Paragraph("Payment Type",boldFont));
			ptypeHeader.setBackgroundColor(baseColor);
			ptypeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell amountrevertedHeader = new PdfPCell(new Paragraph("Amount Reverted",boldFont));
			amountrevertedHeader.setBackgroundColor(baseColor);
			amountrevertedHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell oTernHeader = new PdfPCell(new Paragraph("Term",boldFont));
			oTernHeader.setBackgroundColor(baseColor);
			oTernHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell pYearHeader = new PdfPCell(new Paragraph("Year",boldFont));
			pYearHeader.setBackgroundColor(baseColor);
			pYearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable reveertTable;

			reveertTable = new PdfPTable(5); 
			reveertTable.addCell(mycountHeader);
			reveertTable.addCell(ptypeHeader);
			reveertTable.addCell(amountrevertedHeader);
			reveertTable.addCell(oTernHeader);
			reveertTable.addCell(pYearHeader);
			reveertTable.setWidthPercentage(100); 
			reveertTable.setWidths(new int[]{8,35,40,35,35});   
			reveertTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			OtherstypeDAO otherstypeDAO = OtherstypeDAO.getInstance();
			List<Otherstype> othertypeList = new ArrayList<Otherstype>(); 
			othertypeList = otherstypeDAO.gettypeList(school.getUuid());  
			HashMap<String, String> moneytypeHash = new HashMap<String, String>(); 

			if(othertypeList !=null){
				for(Otherstype om : othertypeList){
					moneytypeHash.put(om.getUuid(),om.getType());
				}
			}
			
			List<RevertedMoney> revertedList = new ArrayList<RevertedMoney>(); 
			revertedList = revertedMoneyDAO.getRevertedMoneyList(stuudent.getUuid());
			
			int rcount = 1;
			for(RevertedMoney rmoney : revertedList){
				reveertTable.addCell(new Paragraph(rcount+"",boldFont));
				reveertTable.addCell(new Paragraph(moneytypeHash.get(rmoney.getOtherstypeUuid())+"",boldFont));
				reveertTable.addCell(new Paragraph(nf.format(rmoney.getAmount())+"",boldFont));
				reveertTable.addCell(new Paragraph(rmoney.getTerm()+"",boldFont));
				reveertTable.addCell(new Paragraph(rmoney.getYear()+"",boldFont));
				rcount++;
			}
			
			Paragraph emptyline = new Paragraph(("                              "));
			document.add(emptyline); 
			document.add(reveertTable); 

			document.close();
		}
		catch(DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}


	}





	private void compute(String admYear, String regterm, SchoolAccount school, Student stuudent, ExamConfig examConfig2) throws DocumentException {

		int nextterm = 0;
		int nextyear =0;

		//check what  term , the initial term is,,,, either ... 1,2 or 3

		//if initial term is 1, compute and move to term 2
		if(Integer.parseInt(regterm) == 1){

			/*Paragraph preface = new Paragraph();
			preface.add(new Paragraph(PDF_TITLE, normalText));
			preface.add(new Paragraph(PDF_SUBTITLE, normalText));
			preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()) +"\n\n")),normalText));
*/

			//table here
			PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
			countHeader.setBackgroundColor(baseColor);
			countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell amountHeader = new PdfPCell(new Paragraph("Amnt Paid",boldFont));
			amountHeader.setBackgroundColor(baseColor);
			amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction Number",boldFont));
			transactionHeader.setBackgroundColor(baseColor);
			transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell paydateHeader = new PdfPCell(new Paragraph("Date paid",boldFont));
			paydateHeader.setBackgroundColor(baseColor);
			paydateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell termHeader = new PdfPCell(new Paragraph("Term " + "1",boldFont));
			termHeader.setBackgroundColor(baseColor);
			termHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell yearHeader = new PdfPCell(new Paragraph("Year " + admYear,boldFont));
			yearHeader.setBackgroundColor(baseColor);
			yearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPTable myTable = new PdfPTable(6); 
			myTable.addCell(countHeader);
			myTable.addCell(amountHeader);
			myTable.addCell(transactionHeader);
			myTable.addCell(paydateHeader);
			myTable.addCell(termHeader);
			myTable.addCell(yearHeader);//


			myTable.setWidthPercentage(100); 
			myTable.setWidths(new int[]{8,40,80,40,12,15});   
			myTable.setHorizontalAlignment(Element.ALIGN_LEFT);


			//Get payments for the 'start year' and 'start term' 
			List<StudentFee> list = new ArrayList<>();               
			list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),regterm,admYear);

			int mycount = 1;
			double totalpaid = 0;
			if(list !=null){
				for(StudentFee fee : list){

					myTable.addCell(new Paragraph(mycount+"",boldFont));
					myTable.addCell(new Paragraph(nf.format(fee.getAmountPaid())+"",boldFont));
					myTable.addCell(new Paragraph(fee.getTransactionID()+"",boldFont));
					myTable.addCell(new Paragraph(formatter.format(fee.getDatePaid())+"",boldFont));
					myTable.addCell(new Paragraph(regterm,boldFont));
					myTable.addCell(new Paragraph(admYear,boldFont));

					totalpaid +=fee.getAmountPaid();
					mycount++;
				}




				TermFee termfee = new TermFee();
				if(termFeeDAO.getTermFee(school.getUuid(),regterm) !=null){
					
					double other_m_amount = 0;
					double other_m_totals = 0;
					
					List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
					stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
					if(stuOthermoniList !=null){
						
						for(StudentOtherMonies som  : stuOthermoniList){
							other_m_amount = som.getAmountPiad();
							other_m_totals +=other_m_amount;
						}
					}
					
					
					termfee = termFeeDAO.getTermFee(school.getUuid(),regterm);

					PdfPCell closeHeader = new PdfPCell(new Paragraph("Total Paid =" + nf.format(totalpaid) +""
							+ "               "+"Fee Balance ="+nf.format(    ((termfee.getTermAmount() + other_m_totals) - totalpaid)   )    +""
							+ "               "+"Term Fee ="+nf.format(termfee.getTermAmount()), smallBold));
					//closeHeader.setBackgroundColor(baseColor);
					closeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					closeHeader.setColspan(6); 		

					myTable.addCell(closeHeader);

				}
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
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);


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

			//END

			//document.add(preface);
			Paragraph emptyline = new Paragraph(("                              "));
			document.add(OtherPayTable); 
			document.add(myTable); 
			document.add(emptyline);

			nextyear = Integer.parseInt(admYear);
			nextterm = 2;

			cmputeMoveTerm2(nextterm,nextyear,school,stuudent,examConfig2); // 2 , 2016



		}


		//if initial term is 2 , compute and move to term 3
		if(Integer.parseInt(regterm) == 2){


			/*Paragraph preface = new Paragraph();
			preface.add(new Paragraph(PDF_TITLE, normalText));
			preface.add(new Paragraph(PDF_SUBTITLE, normalText));
			preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()) +"\n\n")),normalText));
*/
			//table here
			PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
			countHeader.setBackgroundColor(baseColor);
			countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell amountHeader = new PdfPCell(new Paragraph("Amnt Paid",boldFont));
			amountHeader.setBackgroundColor(baseColor);
			amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction Number",boldFont));
			transactionHeader.setBackgroundColor(baseColor);
			transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell paydateHeader = new PdfPCell(new Paragraph("Date paid",boldFont));
			paydateHeader.setBackgroundColor(baseColor);
			paydateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell termHeader = new PdfPCell(new Paragraph("Term " + "2",boldFont));
			termHeader.setBackgroundColor(baseColor);
			termHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell yearHeader = new PdfPCell(new Paragraph("Year " + admYear,boldFont));
			yearHeader.setBackgroundColor(baseColor);
			yearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPTable myTable = new PdfPTable(6); 
			myTable.addCell(countHeader);
			myTable.addCell(amountHeader);
			myTable.addCell(transactionHeader);
			myTable.addCell(paydateHeader);
			myTable.addCell(termHeader);
			myTable.addCell(yearHeader);//


			myTable.setWidthPercentage(100); 
			myTable.setWidths(new int[]{8,40,80,40,12,15});   
			myTable.setHorizontalAlignment(Element.ALIGN_LEFT);


			//Get payments for the 'start year' and 'start term' 
			List<StudentFee> list = new ArrayList<>();               
			list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),regterm,admYear);

			int mycount = 1;
			double totalpaid = 0;
			if(list !=null){
				for(StudentFee fee : list){

					myTable.addCell(new Paragraph(mycount+"",boldFont));
					myTable.addCell(new Paragraph(nf.format(fee.getAmountPaid())+"",boldFont));
					myTable.addCell(new Paragraph(fee.getTransactionID()+"",boldFont));
					myTable.addCell(new Paragraph(formatter.format(fee.getDatePaid())+"",boldFont));
					myTable.addCell(new Paragraph(regterm,boldFont));
					myTable.addCell(new Paragraph(admYear,boldFont));


					totalpaid +=fee.getAmountPaid();
					mycount++;
				}


				TermFee termfee = new TermFee();
				if(termFeeDAO.getTermFee(school.getUuid(),regterm) !=null){
					
					double other_m_amount = 0;
					double other_m_totals = 0;
					
					List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
					stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
					if(stuOthermoniList !=null){
						
						for(StudentOtherMonies som  : stuOthermoniList){
							other_m_amount = som.getAmountPiad();
							other_m_totals +=other_m_amount;
						}
					}
					
					
					termfee = termFeeDAO.getTermFee(school.getUuid(),regterm);

					PdfPCell closeHeader = new PdfPCell(new Paragraph("Total Paid =" + nf.format(totalpaid) +""
							+ "               "+"Fee Balance ="+nf.format(    ((termfee.getTermAmount() + other_m_totals) - totalpaid)   )    +""
							+ "               "+"Term Fee ="+nf.format(termfee.getTermAmount()), smallBold));
					//closeHeader.setBackgroundColor(baseColor);
					closeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					closeHeader.setColspan(6); 		

					myTable.addCell(closeHeader);


				}
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
			if(studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear) !=null){
				stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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

			//END
			//document.add(preface);
			Paragraph emptyline = new Paragraph(("                              "));
			document.add(OtherPayTable); 
			document.add(myTable); 
			document.add(emptyline);


			nextyear = Integer.parseInt(admYear);
			nextterm = 3;

			cmputeMoveTerm3(nextterm,nextyear,school,stuudent,examConfig2);  



		}


		//if initial term is 3, compute and move to term 1 next year , term 1 , if possible
		if(Integer.parseInt(regterm) == 3){



			/*Paragraph preface = new Paragraph();
			preface.add(new Paragraph(PDF_TITLE, normalText));
			preface.add(new Paragraph(PDF_SUBTITLE, normalText));
			preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()) +"\n\n")),normalText));
*/

			//table here
			PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
			countHeader.setBackgroundColor(baseColor);
			countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell amountHeader = new PdfPCell(new Paragraph("Amnt Paid",boldFont));
			amountHeader.setBackgroundColor(baseColor);
			amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction Number",boldFont));
			transactionHeader.setBackgroundColor(baseColor);
			transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell paydateHeader = new PdfPCell(new Paragraph("Date paid",boldFont));
			paydateHeader.setBackgroundColor(baseColor);
			paydateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell termHeader = new PdfPCell(new Paragraph("Term " + "3",boldFont));
			termHeader.setBackgroundColor(baseColor);
			termHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell yearHeader = new PdfPCell(new Paragraph("Year " + admYear,boldFont));
			yearHeader.setBackgroundColor(baseColor);
			yearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPTable myTable = new PdfPTable(6); 
			myTable.addCell(countHeader);
			myTable.addCell(amountHeader);
			myTable.addCell(transactionHeader);
			myTable.addCell(paydateHeader);
			myTable.addCell(termHeader);
			myTable.addCell(yearHeader);//


			myTable.setWidthPercentage(100); 
			myTable.setWidths(new int[]{8,40,80,40,12,15});   
			myTable.setHorizontalAlignment(Element.ALIGN_LEFT);


			//Get payments for the 'start year' and 'start term' 
			List<StudentFee> list = new ArrayList<>();               
			list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),regterm,admYear);

			int mycount = 1;
			double totalpaid = 0;
			if(list !=null){
				for(StudentFee fee : list){

					myTable.addCell(new Paragraph(mycount+"",boldFont));
					myTable.addCell(new Paragraph(nf.format(fee.getAmountPaid())+"",boldFont));
					myTable.addCell(new Paragraph(fee.getTransactionID()+"",boldFont));
					myTable.addCell(new Paragraph(formatter.format(fee.getDatePaid())+"",boldFont));
					myTable.addCell(new Paragraph(regterm,boldFont));
					myTable.addCell(new Paragraph(admYear,boldFont));


					totalpaid +=fee.getAmountPaid();
					mycount++;
				}


				TermFee termfee = new TermFee();
				if(termFeeDAO.getTermFee(school.getUuid(),regterm) !=null){
					
					double other_m_amount = 0;
					double other_m_totals = 0;
					
					List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
					stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
					if(stuOthermoniList !=null){
						
						for(StudentOtherMonies som  : stuOthermoniList){
							other_m_amount = som.getAmountPiad();
							other_m_totals +=other_m_amount;
						}
					}
					
					
					termfee = termFeeDAO.getTermFee(school.getUuid(),regterm);

					PdfPCell closeHeader = new PdfPCell(new Paragraph("Total Paid =" + nf.format(totalpaid) +""
							+ "               "+"Fee Balance ="+nf.format(    ((termfee.getTermAmount() + other_m_totals) - totalpaid)   )    +""
							+ "               "+"Term Fee ="+nf.format(termfee.getTermAmount()), smallBold));
					//closeHeader.setBackgroundColor(baseColor);
					closeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					closeHeader.setColspan(6); 		

					myTable.addCell(closeHeader);


				}
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
			if(studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear) !=null){
				stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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

			//END
			//document.add(preface);
			Paragraph emptyline = new Paragraph(("                              "));
			document.add(OtherPayTable); 
			document.add(myTable); 
			document.add(emptyline);


			nextterm = 1;
			nextyear = Integer.parseInt(admYear) + 1;
			computeNext(nextterm,nextyear,school,stuudent,examConfig2);

		}










	}

	/**
	 * @param nextterm
	 * @param nextyear
	 * @param school
	 * @param stuudent
	 * @param examConfig2
	 * @throws DocumentException
	 */
	private void computeNext(int nextterm, int nextyear, SchoolAccount school, Student stuudent, ExamConfig examConfig2) throws DocumentException {

		if(nextyear <= Integer.parseInt(examConfig2.getYear())){ 
			if(nextterm <= Integer.parseInt(examConfig2.getTerm())){ 
				computeTerm1(nextterm,nextyear,school,stuudent,examConfig2); 
			}
		}
	}



	/**
	 * @param nextterm
	 * @param nextyear
	 * @param school
	 * @param stuudent
	 * @param examConfig2
	 * @throws DocumentException
	 */
	private void computeTerm1(int nextterm, int nextyear, SchoolAccount school, Student stuudent, ExamConfig examConfig2) throws DocumentException {

		String regterm = Integer.toString(nextterm);
		String admYear = Integer.toString(nextyear);

/*
		Paragraph preface = new Paragraph();
		preface.add(new Paragraph(PDF_TITLE, normalText));
		preface.add(new Paragraph(PDF_SUBTITLE, normalText));
		preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()) +"\n\n")),normalText));
*/

		//table here
		PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
		countHeader.setBackgroundColor(baseColor);
		countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell amountHeader = new PdfPCell(new Paragraph("Amnt Paid",boldFont));
		amountHeader.setBackgroundColor(baseColor);
		amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction Number",boldFont));
		transactionHeader.setBackgroundColor(baseColor);
		transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell paydateHeader = new PdfPCell(new Paragraph("Date paid",boldFont));
		paydateHeader.setBackgroundColor(baseColor);
		paydateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell termHeader = new PdfPCell(new Paragraph("Term " + regterm,boldFont));
		termHeader.setBackgroundColor(baseColor);
		termHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell yearHeader = new PdfPCell(new Paragraph("Year " + admYear,boldFont));
		yearHeader.setBackgroundColor(baseColor);
		yearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPTable myTable = new PdfPTable(6); 
		myTable.addCell(countHeader);
		myTable.addCell(amountHeader);
		myTable.addCell(transactionHeader);
		myTable.addCell(paydateHeader);
		myTable.addCell(termHeader);
		myTable.addCell(yearHeader);//


		myTable.setWidthPercentage(100); 
		myTable.setWidths(new int[]{8,40,80,40,12,15});   
		myTable.setHorizontalAlignment(Element.ALIGN_LEFT);


		//Get payments for the 'start year' and 'start term' 
		List<StudentFee> list = new ArrayList<>();               
		list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),regterm,admYear);

		int mycount = 1;
		double totalpaid = 0;
		if(list !=null){
			for(StudentFee fee : list){

				myTable.addCell(new Paragraph(mycount+"",boldFont));
				myTable.addCell(new Paragraph(nf.format(fee.getAmountPaid())+"",boldFont));
				myTable.addCell(new Paragraph(fee.getTransactionID()+"",boldFont));
				myTable.addCell(new Paragraph(formatter.format(fee.getDatePaid())+"",boldFont));
				myTable.addCell(new Paragraph(regterm,boldFont));
				myTable.addCell(new Paragraph(admYear,boldFont));


				totalpaid +=fee.getAmountPaid();
				mycount++;
			}

			String lastYear = Integer.toString(nextyear - 1);

			double prevtermbalance = 0;
			ClosingBalance closingBalance = new ClosingBalance();
			if(closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), "3", lastYear) !=null){
				closingBalance = closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), "3", lastYear);

			}


			TermFee termFeenew = new TermFee();
			if(termFeeDAO.getTermFee(school.getUuid(),"1") !=null){
				termFeenew = termFeeDAO.getTermFee(school.getUuid(),"1");
			}


			double other_m_amount = 0;
			double other_m_totals = 0;
			
			List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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

			myTable.addCell(closeHeader);




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
		if(studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear) !=null){
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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
		//document.add(preface);
		Paragraph emptyline = new Paragraph(("                              "));
		document.add(OtherPayTable); 
		document.add(myTable); 
		document.add(emptyline);


		nextterm = 2;
		int thisyear = nextyear;
		// this term = 1  real term = 1

		cmputeMoveTerm2(nextterm,thisyear,school,stuudent, examConfig2);


	}

	/**
	 * @param nextterm
	 * @param nextyear
	 * @param school
	 * @param stuudent
	 * @param examConfig2
	 * @throws DocumentException
	 */
	private void cmputeMoveTerm2(int nextterm, int nextyear, SchoolAccount school, Student stuudent, ExamConfig examConfig2) throws DocumentException {

		String regterm = Integer.toString(nextterm);
		String admYear = Integer.toString(nextyear);


		/*Paragraph preface = new Paragraph();
		preface.add(new Paragraph(PDF_TITLE, normalText));
		preface.add(new Paragraph(PDF_SUBTITLE, normalText));
		preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()) +"\n\n")),normalText));
*/
		//table here
		PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
		countHeader.setBackgroundColor(baseColor);
		countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell amountHeader = new PdfPCell(new Paragraph("Amnt Paid",boldFont));
		amountHeader.setBackgroundColor(baseColor);
		amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction Number",boldFont));
		transactionHeader.setBackgroundColor(baseColor);
		transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell paydateHeader = new PdfPCell(new Paragraph("Date paid",boldFont));
		paydateHeader.setBackgroundColor(baseColor);
		paydateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);


		PdfPCell termHeader = new PdfPCell(new Paragraph("Term " + regterm,boldFont));
		termHeader.setBackgroundColor(baseColor);
		termHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell yearHeader = new PdfPCell(new Paragraph("Year " + admYear,boldFont));
		yearHeader.setBackgroundColor(baseColor);
		yearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPTable myTable = new PdfPTable(6); 
		myTable.addCell(countHeader);
		myTable.addCell(amountHeader);
		myTable.addCell(transactionHeader);
		myTable.addCell(paydateHeader);
		myTable.addCell(termHeader);
		myTable.addCell(yearHeader);//


		myTable.setWidthPercentage(100); 
		myTable.setWidths(new int[]{8,40,80,40,12,15});   
		myTable.setHorizontalAlignment(Element.ALIGN_LEFT);


		//Get payments for the 'start year' and 'start term' 
		List<StudentFee> list = new ArrayList<>();               
		list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),regterm,admYear);

		int mycount = 1;
		double totalpaid = 0;
		if(list !=null){
			for(StudentFee fee : list){

				myTable.addCell(new Paragraph(mycount+"",boldFont));
				myTable.addCell(new Paragraph(nf.format(fee.getAmountPaid())+"",boldFont));
				myTable.addCell(new Paragraph(fee.getTransactionID()+"",boldFont));
				myTable.addCell(new Paragraph(formatter.format(fee.getDatePaid())+"",boldFont));
				myTable.addCell(new Paragraph(regterm,boldFont));
				myTable.addCell(new Paragraph(admYear,boldFont));


				totalpaid +=fee.getAmountPaid();
				mycount++;
			}



			double prevtermbalance = 0;
			ClosingBalance closingBalance = new ClosingBalance();
			if(closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), "1", admYear) !=null){
				closingBalance = closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), "1", admYear);

			}


			TermFee termFeenew = new TermFee();
			if(termFeeDAO.getTermFee(school.getUuid(),"2") !=null){
				termFeenew = termFeeDAO.getTermFee(school.getUuid(),"2");
			}
			
			double other_m_amount = 0;
			double other_m_totals = 0;
			
			List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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

			myTable.addCell(closeHeader);

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
		if(studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear) !=null){
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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
		//document.add(preface);
		Paragraph emptyline = new Paragraph(("                              "));
		document.add(OtherPayTable); 
		document.add(myTable); 
		document.add(emptyline);



		nextterm = 3;

		cmputeMoveTerm3(nextterm,nextyear,school,stuudent, examConfig2);


	}


	
	
	
	

	/**
	 * @param nextterm
	 * @param nextyear
	 * @param school
	 * @param stuudent
	 * @param examConfig2
	 * @throws DocumentException
	 */
	private void cmputeMoveTerm3(int nextterm, int nextyear, SchoolAccount school, Student stuudent, ExamConfig examConfig2) throws DocumentException {

		String regterm = Integer.toString(nextterm);
		String admYear = Integer.toString(nextyear);



		/*Paragraph preface = new Paragraph();
		preface.add(new Paragraph(PDF_TITLE, normalText));
		preface.add(new Paragraph(PDF_SUBTITLE, normalText));
		preface.add(new Paragraph(("ADM N0 : " + studentAdmNoHash.get(stuudent.getUuid()) +"\n" +("STUDENT : " + studNameHash.get(stuudent.getUuid()) +"\n\n")),normalText));

*/

		//table here
		PdfPCell countHeader = new PdfPCell(new Paragraph("S.N",boldFont));
		countHeader.setBackgroundColor(baseColor);
		countHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell amountHeader = new PdfPCell(new Paragraph("Amnt Paid",boldFont));
		amountHeader.setBackgroundColor(baseColor);
		amountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell transactionHeader = new PdfPCell(new Paragraph("Transaction Number",boldFont));
		transactionHeader.setBackgroundColor(baseColor);
		transactionHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell paydateHeader = new PdfPCell(new Paragraph("Date paid",boldFont));
		paydateHeader.setBackgroundColor(baseColor);
		paydateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);


		PdfPCell termHeader = new PdfPCell(new Paragraph("Term " + regterm,boldFont));
		termHeader.setBackgroundColor(baseColor);
		termHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell yearHeader = new PdfPCell(new Paragraph("Year " + admYear,boldFont));
		yearHeader.setBackgroundColor(baseColor);
		yearHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPTable myTable = new PdfPTable(6); 
		myTable.addCell(countHeader);
		myTable.addCell(amountHeader);
		myTable.addCell(transactionHeader);
		myTable.addCell(paydateHeader);
		myTable.addCell(termHeader);
		myTable.addCell(yearHeader);//


		myTable.setWidthPercentage(100); 
		myTable.setWidths(new int[]{8,40,80,40,12,15});   
		myTable.setHorizontalAlignment(Element.ALIGN_LEFT);


		//Get payments for the 'start year' and 'start term' 
		List<StudentFee> list = new ArrayList<>();               
		list = studentFeeDAO.getStudentFeeByStudentUuidList(school.getUuid(),stuudent.getUuid(),regterm,admYear);

		int mycount = 1;
		double totalpaid = 0;
		if(list !=null){
			for(StudentFee fee : list){

				myTable.addCell(new Paragraph(mycount+"",boldFont));
				myTable.addCell(new Paragraph(nf.format(fee.getAmountPaid())+"",boldFont));
				myTable.addCell(new Paragraph(fee.getTransactionID()+"",boldFont));
				myTable.addCell(new Paragraph(formatter.format(fee.getDatePaid())+"",boldFont));
				myTable.addCell(new Paragraph(regterm,boldFont));
				myTable.addCell(new Paragraph(admYear,boldFont));


				totalpaid +=fee.getAmountPaid();
				mycount++;
			}

			//String prevYear = Integer.toString(nextyear - 1);

			double prevtermbalance = 0;
			ClosingBalance closingBalance = new ClosingBalance();
			if(closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), "2", admYear) !=null){
				closingBalance = closingBalanceDAO.getClosingBalanceByStudentId(school.getUuid(), stuudent.getUuid(), "2", admYear);

			}


			TermFee termFeenew = new TermFee();
			if(termFeeDAO.getTermFee(school.getUuid(),"3") !=null){
				termFeenew = termFeeDAO.getTermFee(school.getUuid(),"3");
			}

			double other_m_amount = 0;
			double other_m_totals = 0;
			
			List<StudentOtherMonies>  stuOthermoniList = new ArrayList<>(); 
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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

			myTable.addCell(closeHeader);



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
		if(studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear) !=null){
			stuOthermoniList = studentOtherMoniesDAO.getStudentOtherList(stuudent.getUuid(),regterm,admYear);
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
		//document.add(preface);
		Paragraph emptyline = new Paragraph(("                              "));
		document.add(OtherPayTable); 
		document.add(myTable); 
		document.add(emptyline);

		nextterm = 1;
		int nextyr = nextyear + 1;

		computeNext(nextterm,nextyr,school,stuudent, examConfig2);


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
