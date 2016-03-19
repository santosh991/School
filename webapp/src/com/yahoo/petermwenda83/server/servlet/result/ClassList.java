/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.result;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.exam.GradingSystem;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.ClassTeacher;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.exam.GradingSystemDAO;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;


/**
 * @author peter
 *
 */
public class ClassList extends HttpServlet{

	private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLDITALIC);
	private Font normalText = new Font(Font.FontFamily.COURIER, 8);
	private Cache schoolaccountCache, statisticsCache;
	private Document document;
	private PdfWriter writer;
	private Logger logger;
	ExamConfig examConfig;
	GradingSystem gradingSystem;

	private String PDF_SUBTITLE ="";
	private static ClassTeacherDAO classTeacherDAO;
	private static PerfomanceDAO perfomanceDAO;
	private static StudentDAO studentDAO;
	private static RoomDAO roomDAO;
	private static ExamConfigDAO examConfigDAO;
	private static GradingSystemDAO gradingSystemDAO;

	String classroomuuid = "";
	String schoolusername = "";
	String stffID = "";
	HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	HashMap<String, String> studNameHash = new HashMap<String, String>(); 
	HashMap<String, String> roomHash = new HashMap<String, String>();
	double total = 0; double mean = 0; double score = 0;
	double engscore = 0; String engscorestr = "";
	double kswscore = 0; String kswscorestr = "";
	double matscore = 0; String matscorestr = "";
	double physcore = 0; String physcorestr = "";
	double bioscore = 0; String bioscorestr = "";
	double chemscore = 0; String chemscorestr = "";
	double bsscore = 0; String bsscorestr = "";
	double compscore = 0; String compscorestr = "";
	double hscscore = 0; String hscscorestr = "";
	double agriscore = 0; String agriscorestr = "";
	double geoscore = 0; String geoscorestr = "";
	double crescore = 0; String crescorestr = "";
	double histscore = 0; String histscorestr = "";

	String grade = "",studeadmno = "",studename = "",admno = "";

	double cat1 = 0,cat2  = 0,endterm  = 0,examcattotal  = 0;
	double paper1  = 0,paper2  = 0,paper3  = 0,catTotals  = 0,catmean  = 0;

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
	   statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_SCHOOL_ACCOUNT);
	   perfomanceDAO = PerfomanceDAO.getInstance();
	   classTeacherDAO = ClassTeacherDAO.getInstance();
	   studentDAO = StudentDAO.getInstance();
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
	   response.setHeader("Content-Disposition", "inline; filename= \" results.pdf \" " );



	   SchoolAccount school = new SchoolAccount();
	   HttpSession session = request.getSession(false);

	   if(session !=null){
		   schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
		   stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);

	   }
	   net.sf.ehcache.Element element;

	   element = schoolaccountCache.get(schoolusername);
	   if(element !=null){
		   school = (SchoolAccount) element.getObjectValue();
	   }
	   
	  

	   examConfig = examConfigDAO.getExamConfig(school.getUuid());
	   gradingSystem = gradingSystemDAO.getGradingSystem(school.getUuid());

	   ClassTeacher classTeacher = classTeacherDAO.getClassTeacherByteacherId(stffID);
	   if(classTeacher !=null){
		   classroomuuid = classTeacher.getClassRoomUuid();
	   }

	   SessionStatistics statistics = new SessionStatistics();
	   if ((element = statisticsCache.get(schoolusername)) != null) {
		   statistics = (SessionStatistics) element.getObjectValue();
	   }

	   List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
	   perfomanceList = perfomanceDAO.getPerfomanceList(school.getUuid(), classroomuuid,examConfig.getTerm(),examConfig.getYear());
	   List<Perfomance> pDistinctList = new ArrayList<Perfomance>();
	   pDistinctList = perfomanceDAO.getPerfomanceListDistinct(school.getUuid(), classroomuuid,examConfig.getTerm(),examConfig.getYear());  

	   List<Student> studentList = new ArrayList<Student>(); 
	   studentList = studentDAO.getAllStudents(school.getUuid(),classroomuuid);

	   for(Student stu : studentList){
		   studentAdmNoHash.put(stu.getUuid(),stu.getAdmno()); 
		   String firstNameLowecase = stu.getFirstname().toLowerCase();
		   String lastNameLowecase = stu.getLastname().toLowerCase();
		   String surNameLowecase = stu.getSurname().toLowerCase(); 
		   String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
		   String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
		   String formatedSurname = surNameLowecase.substring(0,1).toUpperCase()+surNameLowecase.substring(1);

		   studNameHash.put(stu.getUuid(),formatedFirstname + " " + formatedLastname); 
	   }

	   List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
	   classroomList = roomDAO.getAllRooms(school.getUuid()); 
	   for(ClassRoom c : classroomList){
		   roomHash.put(c.getUuid() , c.getRoomName());
	   }

	   PDF_SUBTITLE = school.getSchoolName()+"\n"
			   + "P.O BOX "+school.getPostalAddress()+"\n" 
			   + ""+school.getTown() +"- Kenya\n" 
			   + "" + school.getMobile()+"\n"
			   + "" + school.getEmail()+"\n";


	   document = new Document(PageSize.A4, 46, 46, 64, 64);

	   try {
		   writer = PdfWriter.getInstance(document, response.getOutputStream());           
		   PdfUtil event = new PdfUtil();



		   writer.setBoxSize("art", new Rectangle(46, 64, 559, 788));
		   writer.setPageEvent(event);
		   populatePDFDocument(statistics, school,classroomuuid,perfomanceList,pDistinctList,path);


	   } catch (DocumentException e) {
		   logger.error("DocumentException while writing into the document");
		   logger.error(ExceptionUtils.getStackTrace(e));
	   }

	   
	   if(perfomanceList == null || pDistinctList == null){
		   
		   session.setAttribute(SessionConstants.ERROR_REPORT_NO_PAGES, SessionConstants.ERROR_REPORT_NO_PAGES);  
		   
	   }
	   
	   
	   response.sendRedirect("exam.jsp");  
	   return;
	   
        }



   private void populatePDFDocument(SessionStatistics statistics, SchoolAccount school, String classroomuuid, 
		   List<Perfomance> perfomanceList, List<Perfomance> pDistinctList,String realPath) {
	   SimpleDateFormat formatter;
	   String formattedDate;
	   Date date = new Date();

	   double totalclassmark = 0;
	   double classmean = 0;

	   //languages
	   Map<String,Double> kswscoreMap = new LinkedHashMap<String,Double>();
	   Map<String,Double> engscorehash = new LinkedHashMap<String,Double>(); 
	   //sciences
	   Map<String,Double> physcoreMap = new LinkedHashMap<String,Double>();  
	   Map<String,Double> matscorehash = new LinkedHashMap<String,Double>(); 
	   Map<String,Double> bioscoreMap = new LinkedHashMap<String,Double>();
	   Map<String,Double> chemscorehash = new LinkedHashMap<String,Double>(); 
	   //techinicals
	   Map<String,Double> bsscoreMap = new LinkedHashMap<String,Double>();
	   Map<String,Double> agriscorehash = new LinkedHashMap<String,Double>(); 
	   Map<String,Double> compscoreMap = new LinkedHashMap<String,Double>();
	   Map<String,Double> hscscoreMap = new LinkedHashMap<String,Double>();
	   //humanities 
	   Map<String,Double> crescorehash = new LinkedHashMap<String,Double>(); 
	   Map<String,Double> histscoreMap = new LinkedHashMap<String,Double>();
	   Map<String,Double> geoscoreMap = new LinkedHashMap<String,Double>();

	   String totalz = "";
	   try {
		   document.open();

		   BaseColor baseColor = new BaseColor(32,178,170);//maroon
		   BaseColor Colormagenta = new BaseColor(176,196,222);//magenta
		   BaseColor Colorgrey = new BaseColor(128,128,128);//gray,grey

		   Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLDITALIC);

		   Paragraph emptyline = new Paragraph(("                              "));

		   Paragraph content = new Paragraph();
		   content.add(new Paragraph((PDF_SUBTITLE +"\n\n\n\n\n\n\n") , boldFont));

		   // Paragraph classname = new Paragraph();
		   // classname.add(new Paragraph("CLASS PERFORMANCE LIST FOR: "+roomHash.get(classroomuuid)+"\n",smallBold));

		   PdfPTable prefaceTable = new PdfPTable(2);  
		   prefaceTable.setWidthPercentage(100); 
		   prefaceTable.setWidths(new int[]{100,100}); 



		   PdfPCell contentcell = new PdfPCell(content);
		   contentcell.setBorder(Rectangle.NO_BORDER); 
		   contentcell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		   Paragraph preface = new Paragraph();
		   preface.add(createImage(realPath));

		   Image imgLogo = null;
		   try {
			   imgLogo = Image.getInstance(realPath);
		   } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }

		   imgLogo.scalePercent(10); 
		   imgLogo.setAlignment(Element.ALIGN_LEFT);

		   PdfPCell logo = new PdfPCell();
		   logo.addElement(new Chunk(imgLogo,30,-100)); // margin left  ,  margin top
		   logo.setBorder(Rectangle.NO_BORDER); 
		   logo.setHorizontalAlignment(Element.ALIGN_LEFT);

		   prefaceTable.addCell(logo); 
		   prefaceTable.addCell(contentcell);

		   formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm z");
		   formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
		   formattedDate = formatter.format(date);
		   DecimalFormat df = new DecimalFormat("0.00"); 
		   df.setRoundingMode(RoundingMode.DOWN);
		   DecimalFormat rf = new DecimalFormat("0.0"); 
		   rf.setRoundingMode(RoundingMode.HALF_UP);

		   DecimalFormat rf2 = new DecimalFormat("0"); 
		   rf2.setRoundingMode(RoundingMode.UP);
		   
		   DecimalFormat df2 = new DecimalFormat("0.00"); 
		   df2.setRoundingMode(RoundingMode.UP); 

		   // step 4

		   //table here
		   PdfPCell CountHeader = new PdfPCell(new Paragraph("*",boldFont));
		   CountHeader.setBackgroundColor(baseColor);
		   CountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		   PdfPCell admNoHeader = new PdfPCell(new Paragraph("ADM NO",boldFont));
		   admNoHeader.setBackgroundColor(baseColor);

		   PdfPCell nameHeader = new PdfPCell(new Paragraph("NAME",boldFont));
		   nameHeader.setBackgroundColor(baseColor);
		   nameHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell engHeader = new PdfPCell(new Paragraph("ENG",boldFont));
		   engHeader.setBackgroundColor(baseColor);
		   engHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell kisHeader = new PdfPCell(new Paragraph("KIS",boldFont));
		   kisHeader.setBackgroundColor(baseColor);
		   kisHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell matHeader = new PdfPCell(new Paragraph("MAT",boldFont));
		   matHeader.setBackgroundColor(baseColor);
		   matHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell phyHeader = new PdfPCell(new Paragraph("PHY",boldFont));
		   phyHeader.setBackgroundColor(baseColor);
		   phyHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell cheHeader = new PdfPCell(new Paragraph("CHE",boldFont));
		   cheHeader.setBackgroundColor(baseColor);
		   cheHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell bioHeader = new PdfPCell(new Paragraph("BIO",boldFont));
		   bioHeader.setBackgroundColor(baseColor);
		   bioHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell hisHeader = new PdfPCell(new Paragraph("HIS",boldFont));
		   hisHeader.setBackgroundColor(baseColor);
		   hisHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell creHeader = new PdfPCell(new Paragraph("CRE",boldFont));
		   creHeader.setBackgroundColor(baseColor);
		   creHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell geoHeader = new PdfPCell(new Paragraph("GEO",boldFont));
		   geoHeader.setBackgroundColor(baseColor);roomHash.get(classroomuuid);
		   geoHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell bsHeader = new PdfPCell(new Paragraph("B/S",boldFont));
		   bsHeader.setBackgroundColor(baseColor);
		   bsHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell agrHeader = new PdfPCell(new Paragraph("AGR",boldFont));
		   agrHeader.setBackgroundColor(baseColor);
		   agrHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell hscHeader = new PdfPCell(new Paragraph("HSC",boldFont));
		   hscHeader.setBackgroundColor(baseColor);
		   hscHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell comHeader = new PdfPCell(new Paragraph("COM",boldFont));
		   comHeader.setBackgroundColor(baseColor);
		   comHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell totalsHeader = new PdfPCell(new Paragraph("TOTAL",boldFont));
		   totalsHeader.setBackgroundColor(baseColor);
		   totalsHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell meanHeader = new PdfPCell(new Paragraph("MN",boldFont));
		   meanHeader.setBackgroundColor(baseColor);
		   meanHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

		   PdfPCell gradeHeader = new PdfPCell(new Paragraph("GRD",boldFont));
		   gradeHeader.setBackgroundColor(baseColor);
		   gradeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);


		   PdfPTable myTable = new PdfPTable(19); 

		   myTable.addCell(CountHeader);
		   myTable.addCell(admNoHeader);
		   myTable.addCell(nameHeader);
		   myTable.addCell(engHeader);
		   myTable.addCell(kisHeader);
		   myTable.addCell(matHeader);
		   myTable.addCell(phyHeader);
		   myTable.addCell(cheHeader);
		   myTable.addCell(bioHeader);
		   myTable.addCell(hisHeader);
		   myTable.addCell(creHeader);
		   myTable.addCell(geoHeader);
		   myTable.addCell(bsHeader);
		   myTable.addCell(agrHeader);
		   myTable.addCell(hscHeader);
		   myTable.addCell(comHeader);
		   myTable.addCell(totalsHeader);
		   myTable.addCell(meanHeader);
		   myTable.addCell(gradeHeader);       
		   myTable.setWidthPercentage(103); 
		   myTable.setWidths(new int[]{15,30,54,15,15,15,15,15,15,15,15,15,15,15,15,17,25,20,15});   
		   myTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		   String studeadmno = "",studename = "";double number = 0.0;
		   List<Perfomance> list = new ArrayList<>();
		   Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
		   double grandscore = 0;

		   if(pDistinctList !=null){
			   for(Perfomance s : pDistinctList){ 
				   list = perfomanceDAO.getPerformance(school.getUuid(), classroomuuid, s.getStudentUuid(),examConfig.getTerm(),examConfig.getYear());    
				   for(Perfomance pp : list){
					   cat1 = pp.getCatOne();
					   cat2 = pp.getCatTwo();
					   endterm = pp.getEndTerm();
					   total = (cat1+cat2)/2 +endterm;
					   total = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(total)))));
					   grandscore +=total;              
					   if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
						   engscore = total;
						   engscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(engscore)))));
						   engscorehash.put(s.getStudentUuid(),engscore);
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
						   kswscore = total;
						   kswscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(kswscore)))));
						   kswscoreMap.put(s.getStudentUuid(),kswscore);                 
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
						   physcore = total;
						   physcore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(physcore)))));
						   physcoreMap.put(s.getStudentUuid(),physcore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
						   bioscore = total;
						   bioscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bioscore)))));
						   bioscoreMap.put(s.getStudentUuid(),bioscore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
						   chemscore = total;
						   chemscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(chemscore)))));
						   chemscorehash.put(s.getStudentUuid(),chemscore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
						   matscore = total;
						   matscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(matscore)))));
						   matscorehash.put(s.getStudentUuid(),matscore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
						   bsscore = total;
						   bsscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bsscore)))));
						   bsscoreMap.put(s.getStudentUuid(),bsscore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
						   agriscore = total;
						   agriscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(agriscore)))));
						   agriscorehash.put(s.getStudentUuid(),agriscore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
						   hscscore = total;
						   hscscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(hscscore)))));
						   hscscoreMap.put(s.getStudentUuid(),hscscore);                
					   }if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
						   compscore = total;
						   compscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(compscore)))));
						   compscoreMap.put(s.getStudentUuid(),compscore);                
					   } 
					   if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
						   geoscore = total;
						   geoscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(geoscore)))));
						   geoscoreMap.put(s.getStudentUuid(),geoscore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
						   crescore = total;
						   crescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(crescore)))));
						   crescorehash.put(s.getStudentUuid(),crescore);                
					   }
					   if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
						   histscore = total;
						   histscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(histscore)))));
						   histscoreMap.put(s.getStudentUuid(),histscore);                
					   }   		 
				   }           
				   grandscoremap.put(s.getStudentUuid(), grandscore);
				   grandscore = 0;    	   
			   }

			   ArrayList<?> as = new ArrayList(grandscoremap.entrySet());
			   Collections.sort(as,new Comparator(){
				   public int compare(Object o1,Object o2){
					   Map.Entry e1 = (Map.Entry)o1;
					   Map.Entry e2 = (Map.Entry)o2;
					   Double f = (Double)e1.getValue();
					   Double s = (Double)e2.getValue();
					   return s.compareTo(f);
				   }
			   });


			   int count = 1;
			   int counttwo = 1;	
			   int studentcount = 0;
			   for(Object o : as){
				   String items = String.valueOf(o);
				   String [] item = items.split("=");
				   String uuid = item[0];
				   totalz = item[1];
				   
				   
				   mean = Double.parseDouble(totalz)/11;	
				   
				   totalclassmark +=mean;
				   
				 
				   studeadmno = studentAdmNoHash.get(uuid);
				   studename = studNameHash.get(uuid);     

				   if(engscorehash.get(uuid)!=null){
					   engscore = engscorehash.get(uuid);                                                     
					   engscorestr =  rf2.format(engscore);
				   }else{
					   engscorestr = "";
				   }
				   if(kswscoreMap.get(uuid)!=null){
					   kswscore = kswscoreMap.get(uuid);
					   kswscorestr = rf2.format(kswscore);
				   }else{
					   kswscorestr = "";
				   }
				   if(physcoreMap.get(uuid)!=null){
					   physcore = physcoreMap.get(uuid);
					   physcorestr = rf2.format(physcore);
				   }else{
					   physcorestr = "";
				   }

				   if(bioscoreMap.get(uuid)!=null){
					   bioscore = bioscoreMap.get(uuid);
					   bioscorestr = rf2.format(bioscore);
				   }else{
					   bioscorestr = "";
				   }

				   if(chemscorehash.get(uuid)!=null){
					   chemscore = chemscorehash.get(uuid);
					   chemscorestr = rf2.format(chemscore);
				   }else{
					   chemscorestr = "";
				   }

				   if(matscorehash.get(uuid)!=null){
					   matscore = matscorehash.get(uuid);
					   matscorestr = rf2.format(matscore);
				   }else{
					   matscorestr = "";
				   }

				   if(histscoreMap.get(uuid)!=null){
					   histscore = histscoreMap.get(uuid);
					   histscorestr = rf2.format(histscore);
				   }else{
					   histscorestr = "";
				   }

				   if(crescorehash.get(uuid)!=null){
					   crescore = crescorehash.get(uuid);
					   crescorestr = rf2.format(crescore);
				   }else{
					   crescorestr = "";
				   }


				   if(geoscoreMap.get(uuid)!=null){
					   geoscore = geoscoreMap.get(uuid);
					   geoscorestr = rf2.format(geoscore);
				   }else{
					   geoscorestr = "";
				   }

				   if(bsscoreMap.get(uuid)!=null){
					   bsscore = bsscoreMap.get(uuid);
					   bsscorestr = rf2.format(bsscore);
				   }else{
					   bsscorestr = "";
				   }


				   if(agriscorehash.get(uuid)!=null){
					   agriscore = agriscorehash.get(uuid);
					   agriscorestr = rf2.format(agriscore);
				   }else{
					   agriscorestr = "";
				   }

				   if(hscscoreMap.get(uuid)!=null){
					   hscscore = hscscoreMap.get(uuid);
					   hscscorestr = rf2.format(hscscore);
				   }else{
					   hscscorestr = "";
				   }

				   if(compscoreMap.get(uuid)!=null){
					   compscore = compscoreMap.get(uuid);
					   compscorestr = rf2.format(compscore);
				   }else{
					   compscorestr = "";
				   }   



          
				   if(Double.parseDouble(totalz)==number){
					   myTable.addCell(new Paragraph(" "+(count-counttwo++),boldFont));
					   
				   }
				   else{
					   counttwo=1;
					   myTable.addCell(new Paragraph(" "+count,boldFont));
					   
				   }
				   
				   
				   
				   myTable.addCell(new Paragraph(studeadmno,boldFont));
				   myTable.addCell(new Paragraph(studename,boldFont));				   
				   myTable.addCell(new Paragraph(engscorestr,boldFont));
				   myTable.addCell(new Paragraph(kswscorestr,boldFont));
				   myTable.addCell(new Paragraph(matscorestr,boldFont));
				   myTable.addCell(new Paragraph(physcorestr,boldFont));
				   myTable.addCell(new Paragraph(chemscorestr,boldFont));
				   myTable.addCell(new Paragraph(bioscorestr,boldFont));
				   myTable.addCell(new Paragraph(histscorestr,boldFont));
				   myTable.addCell(new Paragraph(crescorestr,boldFont));				   
				   myTable.addCell(new Paragraph(geoscorestr,boldFont));
				   myTable.addCell(new Paragraph(bsscorestr,boldFont));
				   myTable.addCell(new Paragraph(agriscorestr,boldFont));
				   myTable.addCell(new Paragraph(hscscorestr,boldFont));
				   myTable.addCell(new Paragraph(compscorestr,boldFont));				   
				   myTable.addCell(new Paragraph(df.format(Double.parseDouble(totalz)),boldFont));				   
				   myTable.addCell(new Paragraph(df.format(mean),boldFont));
				   myTable.addCell(new Paragraph(computeGrade(mean),boldFont));
				  
				   count++;
				   studentcount++;
				   number=Double.parseDouble(totalz);
				   
				   classmean = totalclassmark/studentcount;
			   }
		   }

		   Paragraph classname = new Paragraph();
		   classname.add(new Paragraph("CLASS PERFORMANCE LIST FOR: "+roomHash.get(classroomuuid) +" (CLASS MEAN :"+df2.format(classmean) +")\n",smallBold));


		   document.add(prefaceTable);
		   document.add(emptyline);
		   document.add(classname);
		   document.add(emptyline);
		   document.add(myTable);  
		   // step 5
		   document.close();
	   }
	   catch(DocumentException e) {
		   logger.error("DocumentException while writing into the document");
		   logger.error(ExceptionUtils.getStackTrace(e));
	   }  
   }




   /**
    * @param score
    * @return
    */
   private String computeGrade(double score) {
	   double mean = score;
	   if(mean >= gradingSystem.getGradeAplain()){
		   grade = "A";
	   }else if(mean >= gradingSystem.getGradeAminus()){
		   grade = "A-";
	   }else if(mean >= gradingSystem.getGradeBplus()){
		   grade = "B+";
	   }else if(mean >= gradingSystem.getGradeBplain()){
		   grade = "B";
	   }else if(mean >= gradingSystem.getGradeBminus()){
		   grade = "B-";
	   }else if(mean >= gradingSystem.getGradeCplus()){
		   grade = "C+";
	   }else if(mean >= gradingSystem.getGradeCplain()){
		   grade = "C";
	   }else if(mean >= gradingSystem.getGradeCminus()){
		   grade = "C-";
	   }else if(mean >= gradingSystem.getGradeDplus()){
		   grade = "D+";
	   }else if(mean >= gradingSystem.getGradeDplain()){
		   grade = "D";
	   }else if(mean >= gradingSystem.getGradeDminus()){
		   grade = "D-";
	   }else{
		   grade = "E";
	   }

	   if(mean ==0){
		   grade = " ";
	   }

	   return grade;
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
    * @param paragraph
    * @param number
    * @return
    */
   private Paragraph addEmptyLine(Paragraph paragraph, int number) {
	   for (int i = 0; i < number; i++) {
		   paragraph.add(new Paragraph(" "));
	   }
	   return paragraph;
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
