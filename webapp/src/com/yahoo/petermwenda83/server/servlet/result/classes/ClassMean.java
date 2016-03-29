package com.yahoo.petermwenda83.server.servlet.result.classes;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import com.yahoo.petermwenda83.bean.exam.GradingSystem;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.exam.GradingSystemDAO;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.servlet.result.PdfUtil;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.util.magic.MiddleNumberFor3;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/** 
 * @author peter
 *
 */
public class ClassMean extends HttpServlet{

	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLDITALIC);
	private Document document;
	private PdfWriter writer;
	private Cache schoolaccountCache;

	private Logger logger;
	ExamConfig examConfig;
	GradingSystem gradingSystem;

	private String PDF_SUBTITLE ="";

	private static PerfomanceDAO perfomanceDAO;
	private static RoomDAO roomDAO;
	private static ExamConfigDAO examConfigDAO;
	private static GradingSystemDAO gradingSystemDAO;

	String schoolusername = "";
	String stffID = "";

	private final String EXAM_FULL_ID = "4BE8AD46-EAE8-4151-BD18-CB23CF904DDB";
	String examID = "";

	HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	HashMap<String, String> studNameHash = new HashMap<String, String>();
	HashMap<String, String> roomHash = new HashMap<String, String>();



	double mean = 0;double score = 0;
	double engscore = 0;String engscorestr = "";
	double kswscore = 0;String kswscorestr = "";
	double matscore = 0;String matscorestr = "";
	double physcore = 0;String physcorestr = "";  
	double bioscore = 0;String bioscorestr = "";
	double chemscore = 0;String chemscorestr = "";
	double bsscore = 0;String bsscorestr = "";
	double comscore = 0;String comscorestr = "";
	double hscscore = 0;String hscscorestr = "";
	double agriscore = 0;String agriscorestr = "";
	double geoscore = 0;String geoscorestr = "";
	double crescore = 0;String crescorestr = "";
	double histscore = 0;String histscorestr = "";



	String grade = "";String studeadmno = "";String studename = "";String admno = "";
	double paper1  = 0;double paper2  = 0;double paper3  = 0;
	double cat1 = 0;  double cat2  = 0;double endterm  = 0;
	double catTotals  = 0;double catmean  = 0;double examcattotal  = 0;
	double total  = 0;double pmean  = 0;
	//Languages
	final String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
	final String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
	//Sciences
	final String MATH_UUID = "4f59580d-1a16-4669-9ed5-4b89615d6903";
	final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";
	final String BIO_UUID = "de0c86be-9bcb-4d3b-8098-b06687536c1f";
	final String CHEM_UUID = "552c0a24-6038-440f-add5-2dadfb9a23bd";
	//Technical
	final String BS_UUID = "e1729cc2-524a-4069-b4a4-be5aec8473fe";
	final String COMP_UUID = "F1972BF2-C788-4F41-94FE-FBA1869C92BC";
	final String H_S = "C1F28FF4-1A18-4552-822A-7A4767643643";
	final String AGR_UUID = "b9bbd718-b32f-4466-ab34-42f544ff900e";
	//Humanities 
	final String GEO_UUID = "0e5dc1c6-f62f-4a36-a1ec-064173332694";
	final String CRE_UUID = "f098e943-26fd-4dc0-b6a0-2d02477004a4";
	final String HIST_UUID = "c9caf109-c27d-4062-9b9f-ac4268629e27";

	String USER= "";
	String path ="";
	
	

	/**
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger = Logger.getLogger(this.getClass());
		CacheManager mgr = CacheManager.getInstance();
		schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
		perfomanceDAO = PerfomanceDAO.getInstance();
		roomDAO = RoomDAO.getInstance();
		examConfigDAO = ExamConfigDAO.getInstance();
		gradingSystemDAO = GradingSystemDAO.getInstance();

		USER = System.getProperty("user.name");
		path = "/home/"+USER+"/school/logo/logo.png";	
		
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ServletContext context = getServletContext();
		response.setContentType("application/pdf");



		SchoolAccount school = new SchoolAccount();
		HttpSession session = request.getSession(false); 
		schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
		stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);

		examID = StringUtils.trimToEmpty(request.getParameter("examID"));
		examID = "4BE8AD46-EAE8-4151-BD18-CB23CF904DDB";

		String pdf = schoolusername+"results.pdf";
		response.setHeader("Content-Disposition", "inline; filename= "+pdf );

		net.sf.ehcache.Element element;

		element = schoolaccountCache.get(schoolusername);
		if(element !=null){
			school = (SchoolAccount) element.getObjectValue();
		}



		examConfig = examConfigDAO.getExamConfig(school.getUuid());
		gradingSystem = gradingSystemDAO.getGradingSystem(school.getUuid());

		PDF_SUBTITLE = school.getSchoolName()+"\n"
				+ "P.O BOX "+school.getPostalAddress()+"\n" 
				+ ""+school.getTown().toUpperCase() +" KENYA\n" 
				+ "" + school.getMobile()+"\n"
				+ "" + school.getEmail()+"\n";
	
		document = new Document(PageSize.A4, 46, 46, 64, 64);

		try {
			writer = PdfWriter.getInstance(document, response.getOutputStream());

			PdfUtil event = new PdfUtil();
			writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
			writer.setPageEvent(event);

			populatePDFDocument(school,path);


		} catch (DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}


	}



	private void populatePDFDocument(SchoolAccount school, String realPath) {

		try {
			document.open();



			DecimalFormat df = new DecimalFormat("0.00"); 
			df.setRoundingMode(RoundingMode.DOWN);

			DecimalFormat rf = new DecimalFormat("0.0"); 
			rf.setRoundingMode(RoundingMode.HALF_UP);

			DecimalFormat rf2 = new DecimalFormat("0"); 
			rf2.setRoundingMode(RoundingMode.UP);

			DecimalFormat df2 = new DecimalFormat("0.00"); 
			df2.setRoundingMode(RoundingMode.UP); 


			BaseColor baseColor = new BaseColor(32,178,170);//maroon

			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);

			Paragraph emptyline = new Paragraph(("                              "));
			PdfPTable prefaceTable = new PdfPTable(2);  
			prefaceTable.setWidthPercentage(100); 
			prefaceTable.setWidths(new int[]{100,100}); 

			Paragraph content = new Paragraph();
			content.add(new Paragraph((PDF_SUBTITLE +"\n\n\n\n\n\n") , smallBold));



			PdfPCell contentcell = new PdfPCell(content);
			contentcell.setBorder(Rectangle.NO_BORDER); 
			contentcell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Paragraph preface = new Paragraph();
			preface.add(createImage(realPath));

			Image imgLogo = null;
			try {
				imgLogo = Image.getInstance(realPath);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			imgLogo.scalePercent(8); 
			imgLogo.setAlignment(Element.ALIGN_LEFT);

			PdfPCell logo = new PdfPCell();
			logo.addElement(new Chunk(imgLogo,30,-80)); // margin left  ,  margin top
			logo.setBorder(Rectangle.NO_BORDER); 
			logo.setHorizontalAlignment(Element.ALIGN_LEFT);

			prefaceTable.addCell(logo); 
			prefaceTable.addCell(contentcell);


			//table here
			PdfPCell CountHeader = new PdfPCell(new Paragraph("*",boldFont));
			CountHeader.setBackgroundColor(baseColor);
			CountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell classHeader = new PdfPCell(new Paragraph("CLASS",boldFont));
			classHeader.setBackgroundColor(baseColor);
			classHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell meanHeader = new PdfPCell(new Paragraph("MEAN",boldFont));
			meanHeader.setBackgroundColor(baseColor);
			meanHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

			


			PdfPTable myTable1 = new PdfPTable(2); 
			//myTable1.addCell(CountHeader);
			myTable1.addCell(classHeader);
			myTable1.addCell(meanHeader);
			
			myTable1.setWidthPercentage(100); 
			myTable1.setWidths(new int[]{60,60});   
			myTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable myTable2 = new PdfPTable(2); 
			//myTable2.addCell(CountHeader);
			myTable2.addCell(classHeader);
			myTable2.addCell(meanHeader);
			
			myTable2.setWidthPercentage(100); 
			myTable2.setWidths(new int[]{60,60});   
			myTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable myTable3 = new PdfPTable(2); 
			//myTable3.addCell(CountHeader);
			myTable3.addCell(classHeader);
			myTable3.addCell(meanHeader);
			
			myTable3.setWidthPercentage(100); 
			myTable3.setWidths(new int[]{60,60});   
			myTable3.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPTable myTable4 = new PdfPTable(2); 
			//myTable4.addCell(CountHeader);
			myTable4.addCell(classHeader);
			myTable4.addCell(meanHeader);
			
			myTable4.setWidthPercentage(100); 
			myTable4.setWidths(new int[]{60,60});   
			myTable4.setHorizontalAlignment(Element.ALIGN_LEFT);
			
		
			int formonecount = 0;
			int formtwocount = 0;
			int formthreecount = 0;
			int formfourcount = 0;
			

			final String FORM1 = "C143978A-E021-4015-BC67-5A00D6C910D1";
			final String FORM2 = "3E22E428-3155-42F5-B73E-66553ED501C9";
			final String FORM3 = "A4BFC2BD-262F-4207-99C8-057D6ADF80C7";
			final String FORM4 = "14E56350-08DA-45CC-97D9-C225AF74A7AD";
			
			 //firm 0ne
			  List<Perfomance> pDistinctList1 = new ArrayList<Perfomance>();
		      pDistinctList1 = perfomanceDAO.getPerfomanceListDistinctGeneral(school.getUuid(), FORM1,examConfig.getTerm(),examConfig.getYear());
		        
		      for(Perfomance p1: pDistinctList1){
					  formonecount++;
		        }
		      
		       double classmean = 0;
			   double grandscore = 0;
			   double classmeantotals = 0;
				
		      
		      List<Perfomance> list1 = new ArrayList<>();
		      list1 = perfomanceDAO.getClassPerfomanceListGeneral(school.getUuid(), FORM1,examConfig.getTerm(),examConfig.getYear());
		      
		      
		       for(Perfomance pp : list1){
           	   
				   cat1 = pp.getCatOne();
				   cat2 = pp.getCatTwo();
				   endterm = pp.getEndTerm();
				   total = (cat1+cat2)/2 +endterm;
				   total = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(total)))));
				   grandscore = total/11;
				   classmean+=grandscore;
				   classmeantotals = classmean/formonecount;   
				   
	               }
		      
		        myTable1.addCell(new Paragraph("FORM 1",boldFont));
				myTable1.addCell(new Paragraph(df2.format(classmeantotals),boldFont));
		      
		      
		      
		      
		      
		      
		      
		      //firm Two
		      List<Perfomance> pDistinctList2 = new ArrayList<Perfomance>();
		      pDistinctList2 = perfomanceDAO.getPerfomanceListDistinctGeneral(school.getUuid(), FORM2,examConfig.getTerm(),examConfig.getYear());
		        
		      for(Perfomance p2: pDistinctList2){
		    	  formtwocount++;
		        }
		      
		       classmean = 0;
			   grandscore = 0;
			   classmeantotals = 0;
				
		      List<Perfomance> list2 = new ArrayList<>();
		      list2 = perfomanceDAO.getClassPerfomanceListGeneral(school.getUuid(), FORM2,examConfig.getTerm(),examConfig.getYear());
				
               for(Perfomance pp : list2){
            	   
			   cat1 = pp.getCatOne();
			   cat2 = pp.getCatTwo();
			   endterm = pp.getEndTerm();
			   total = (cat1+cat2)/2 +endterm;
			   total = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(total)))));
			   grandscore = total/11;
			   classmean+=grandscore;
			   classmeantotals = classmean/formtwocount;   
			   
               }
		      
               myTable2.addCell(new Paragraph("FORM 2",boldFont));
			   myTable2.addCell(new Paragraph(df2.format(classmeantotals),boldFont));
		      
		      
		      
		      //firm Three
		      List<Perfomance> pDistinctList3 = new ArrayList<Perfomance>();
		      pDistinctList3 = perfomanceDAO.getPerfomanceListDistinctGeneral(school.getUuid(), FORM3,examConfig.getTerm(),examConfig.getYear());
		        
		      for(Perfomance p3: pDistinctList3){
		    	  formthreecount++;
		        }
		      
		      List<Perfomance> list3 = new ArrayList<>();
		      double languageScore = 0;double scienceScore = 0;double humanityScore = 0;
			  double techinicalScore = 0;grandscore = 0;
			  MiddleNumberFor3 middle = new MiddleNumberFor3();
			  engscore = 0;kswscore = 0;matscore = 0;physcore = 0;bioscore = 0;chemscore = 0;
			  bsscore = 0;comscore = 0;hscscore = 0;agriscore = 0;geoscore = 0;crescore = 0;
			  histscore = 0;
			  
			  double totalclassmark = 0;
			  classmean = 0;
		      
		      
		      list3 = perfomanceDAO.getClassPerfomanceListGeneral(school.getUuid(), FORM3,examConfig.getTerm(),examConfig.getYear());
		      for(Perfomance pp : list3){


					//Languages
					//Include all the languages
					if(true){
						if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 60
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 60                   
								total = (paper1 + paper2 + paper3)/2; 
								engscore = total; 
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								engscore = examcattotal; 
								

							}
						}
						if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 60
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 60
								total = (paper1 + paper2 + paper3)/2; 
								kswscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								kswscore = examcattotal; 
								

							}

						}

						engscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(engscore)))));
						kswscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(kswscore)))));
						languageScore = (engscore+kswscore); 
						//System.out.println("student="+studNameHash.get(s.getStudentUuid())+",engscore="+engscore+",kswscore="+kswscore+",languageScore="+languageScore);

					}       
					//Sciences
					//Pick best two if the student take the three
					if(true){
						double subjectBig = 0;
						double subjectSmall = 0;
						if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								physcore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								physcore = examcattotal; 
								

							}
						}
						if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								bioscore = total;
								
							}
							else{
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								bioscore = examcattotal; 
								

							}
						}
						if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								chemscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								chemscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								matscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								matscore = examcattotal; 
								

							}

						}

						physcore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(physcore)))));
						bioscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bioscore)))));
						chemscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(chemscore)))));
						matscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(matscore)))));

						subjectBig = Math.max( (Math.max(physcore, bioscore)), Math.max(Math.max(physcore, bioscore), chemscore));
						subjectSmall = middle.ComputeMiddle(physcore, bioscore, chemscore);
						scienceScore = (subjectBig+subjectSmall+matscore);

					}
					//Technical
					//Here we pick one subject, the one he/she has performed best, but this subject can be replaced by a science, if the student takes 3 sciences and he/she performed better in the science than in all  the techinicals . 
					if(true){
						double bestTechinical = 0;
						if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								bsscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								bsscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								agriscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								agriscore = examcattotal; 
								

							}

						}     
						if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								hscscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								hscscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								comscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								comscore = examcattotal; 
								

							}

						} 
						bsscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bsscore)))));
						agriscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(agriscore)))));
						hscscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(hscscore)))));
						comscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(comscore)))));
						bestTechinical = Math.max( (Math.max(bsscore, agriscore)), Math.max(hscscore, comscore));
						techinicalScore = bestTechinical; 
						
					}    

					//Humanities
					//Here we pick only one subject, the one the student has performed best . 
					if(true){  
						double bestHumanity = 0;     
						if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								geoscore = total;                                                 
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								geoscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								crescore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								crescore = examcattotal; 
								
							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								histscore = total;                                              
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								histscore = examcattotal; 
							}
						}

						geoscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(geoscore)))));
						crescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(crescore)))));
						histscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(histscore)))));
						bestHumanity = Math.max( (Math.max(geoscore, crescore)), Math.max(Math.max(geoscore, crescore), histscore));
						humanityScore = bestHumanity; 
						
					} 
				
		      
		    	  
		      }

		        grandscore = 0;
				grandscore = languageScore+scienceScore+humanityScore+techinicalScore;
				languageScore = 0; scienceScore = 0; humanityScore = 0;techinicalScore = 0;  				         
				grandscore = grandscore/7;
				totalclassmark+=grandscore;
				classmean = totalclassmark/formthreecount;
				myTable3.addCell(new Paragraph("FORM 3",boldFont));
				myTable3.addCell(new Paragraph(df2.format(classmean),boldFont));
		      
		      
		      
		      
		      
		      
		      
		      //firm Four
		      List<Perfomance> pDistinctList4 = new ArrayList<Perfomance>();
		      pDistinctList4 = perfomanceDAO.getPerfomanceListDistinctGeneral(school.getUuid(), FORM4,examConfig.getTerm(),examConfig.getYear());

		      for(Perfomance p4: pDistinctList4){
		    	  formfourcount++;
		      }

		      List<Perfomance> list4 = new ArrayList<>();
		      languageScore = 0;scienceScore = 0;humanityScore = 0;
			  techinicalScore = 0;grandscore = 0;
			  engscore = 0;kswscore = 0;matscore = 0;physcore = 0;bioscore = 0;chemscore = 0;
			  bsscore = 0;comscore = 0;hscscore = 0;agriscore = 0;geoscore = 0;crescore = 0;
			  histscore = 0;
			  
			  totalclassmark = 0;
			  classmean = 0;
			  
		      list4 = perfomanceDAO.getClassPerfomanceListGeneral(school.getUuid(), FORM4,examConfig.getTerm(),examConfig.getYear());
		      for(Perfomance pp : list4){

					//Languages
					//Include all the languages
					if(true){
						if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 60
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 60                   
								total = (paper1 + paper2 + paper3)/2; 
								engscore = total; 
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								engscore = examcattotal; 
								

							}
						}
						if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 60
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 60
								total = (paper1 + paper2 + paper3)/2; 
								kswscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								kswscore = examcattotal; 
								

							}

						}

						engscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(engscore)))));
						kswscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(kswscore)))));
						languageScore = (engscore+kswscore); 
						//System.out.println("student="+studNameHash.get(s.getStudentUuid())+",engscore="+engscore+",kswscore="+kswscore+",languageScore="+languageScore);

					}       
					//Sciences
					//Pick best two if the student take the three
					if(true){
						double subjectBig = 0;
						double subjectSmall = 0;
						if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								physcore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								physcore = examcattotal; 
								

							}
						}
						if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								bioscore = total;
								
							}
							else{
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								bioscore = examcattotal; 
								

							}
						}
						if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								chemscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								chemscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								matscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								matscore = examcattotal; 
								

							}

						}

						physcore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(physcore)))));
						bioscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bioscore)))));
						chemscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(chemscore)))));
						matscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(matscore)))));

						subjectBig = Math.max( (Math.max(physcore, bioscore)), Math.max(Math.max(physcore, bioscore), chemscore));
						subjectSmall = middle.ComputeMiddle(physcore, bioscore, chemscore);
						scienceScore = (subjectBig+subjectSmall+matscore);

					}
					//Technical
					//Here we pick one subject, the one he/she has performed best, but this subject can be replaced by a science, if the student takes 3 sciences and he/she performed better in the science than in all  the techinicals . 
					if(true){
						double bestTechinical = 0;
						if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								bsscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								bsscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								agriscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								agriscore = examcattotal; 
								

							}

						}     
						if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								hscscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								hscscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								comscore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								comscore = examcattotal; 
								

							}

						} 
						bsscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bsscore)))));
						agriscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(agriscore)))));
						hscscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(hscscore)))));
						comscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(comscore)))));
						bestTechinical = Math.max( (Math.max(bsscore, agriscore)), Math.max(hscscore, comscore));
						techinicalScore = bestTechinical; 
						
					}    

					//Humanities
					//Here we pick only one subject, the one the student has performed best . 
					if(true){  
						double bestHumanity = 0;     
						if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								geoscore = total;                                                 
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								geoscore = examcattotal; 
								

							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								crescore = total;
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								crescore = examcattotal; 
								
							}

						}
						if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
							if(StringUtils.equals(examID, EXAM_FULL_ID)){
								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								histscore = total;                                              
								
							}
							else {
								cat1 = pp.getCatOne();
								cat2 = pp.getCatTwo();
								endterm = pp.getEndTerm();
								catTotals = cat1 + cat2;
								catmean = catTotals/2;
								examcattotal = catmean + endterm;
								histscore = examcattotal; 
							}
						}

						geoscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(geoscore)))));
						crescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(crescore)))));
						histscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(histscore)))));
						bestHumanity = Math.max( (Math.max(geoscore, crescore)), Math.max(Math.max(geoscore, crescore), histscore));
						humanityScore = bestHumanity; 
						
					} 
				
		      }
		      
		        grandscore = 0;
				grandscore = languageScore+scienceScore+humanityScore+techinicalScore;
				languageScore = 0; scienceScore = 0; humanityScore = 0;techinicalScore = 0;  				         
				grandscore = grandscore/7;
				totalclassmark+=grandscore;
				classmean = totalclassmark/formfourcount;
				myTable4.addCell(new Paragraph("FORM 4 ",boldFont));
				myTable4.addCell(new Paragraph(df2.format(classmean),boldFont));  
		      
		      
		      

		      document.add(prefaceTable);
		      document.add(emptyline);
		      document.add(myTable1);  
		      document.add(myTable2);  
		      document.add(myTable3);  
		      document.add(myTable4);  
			
			
			
			
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
			imgLogo.scaleToFit(200, 200);
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
	 *
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3513371438433721109L;



}
