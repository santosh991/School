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
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.ClassTeacher;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;
import com.yahoo.petermwenda83.server.util.magic.MiddleNumberFor3;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author peter
 * 
 */
public class ReportForm2 extends HttpServlet{


    private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    //private Font normalText = new Font(Font.FontFamily.COURIER, 12);
    private Document document;
    private PdfWriter writer;
    private Cache schoolaccountCache, statisticsCache;

    private Logger logger;
    
    final String PDF_TITLE = "Student Report Form";
    final String PDF_SUBTITLE = "Report Generated For: ";
    final String PDF_BOTTOM_TEXT = "pdf bottom text here";
    
     private static PerfomanceDAO perfomanceDAO;
     private static SubjectDAO subjectDAO;
     private static ClassTeacherDAO classTeacherDAO;
     private static StudentDAO studentDAO;
     private static RoomDAO roomDAO;
    
      String classroomuuid = "";String schoolusername = "";String stffID = "";
    
      HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
      HashMap<String, String> studNameHash = new HashMap<String, String>();
      HashMap<String, String> roomHash = new HashMap<String, String>();
    
      double score = 0;
      double engscore = 0;String engscorestr = "";
      double kswscore = 0;String kswscorestr = "";
      double matscore = 0;String matscorestr = "";
      double physcore = 0;String physcorestr = "";  
      double bioscore = 0;String bioscorestr = "";
      double chemscore = 0; String chemscorestr = "";
      double bsscore = 0;String bsscorestr = "";
      double comscore = 0;String comscorestr = "";
      double hscscore = 0;String hscscorestr = "";
      double agriscore = 0;String agriscorestr = "";
      double geoscore = 0;String geoscorestr = "";
      double crescore = 0;String crescorestr = "";
      double histscore = 0; String histscorestr = "";


                                       
    String grade = "";String studeadmno = "";String studename = "";String admno = "";

    double cat1 = 0;  double cat2  = 0;double endterm  = 0;
    double catTotals  = 0;double catmean  = 0;double examcattotal  = 0;
    
    
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
   
    int position = 1;
    static int Finalposition = 0;
   
    
   
    
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
   subjectDAO = SubjectDAO.getInstance();
   classTeacherDAO = ClassTeacherDAO.getInstance();
   studentDAO = StudentDAO.getInstance();
   roomDAO = RoomDAO.getInstance();
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
   ServletContext context = getServletContext();
   response.setContentType("application/pdf");
  
   SchoolAccount school = new SchoolAccount();
   HttpSession session = request.getSession(false);
  
   if(session !=null){
   schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
   stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
   
      }
   
   String pdf = schoolusername+"results.pdf";
   response.setHeader("Content-Disposition", "inline; filename= "+pdf );
   
   
   net.sf.ehcache.Element element;
   
   element = schoolaccountCache.get(schoolusername);
   if(element !=null){
   school = (SchoolAccount) element.getObjectValue();
      }
   ClassTeacher classTeacher = classTeacherDAO.getClassTeacher(stffID);
     if(classTeacher !=null){
           classroomuuid = classTeacher.getClassRoomUuid();
         }
   
   SessionStatistics statistics = new SessionStatistics();
   if ((element = statisticsCache.get(schoolusername)) != null) {
       statistics = (SessionStatistics) element.getObjectValue();
   }
   
   List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
   perfomanceList = perfomanceDAO.getPerfomanceList(school.getUuid(), classroomuuid);
   List<Perfomance> pDistinctList = new ArrayList<Perfomance>();
   pDistinctList = perfomanceDAO.getPerfomanceListDistinct(school.getUuid(), classroomuuid);
    
   
   List<Student> studentList = new ArrayList<Student>(); 
   studentList = studentDAO.getAllStudents(school.getUuid(),classroomuuid);
   
          for(Student stu : studentList){
           studentAdmNoHash.put(stu.getUuid(),stu.getAdmno()); 
           String firstNameLowecase = stu.getFirstname().toLowerCase();
           String lastNameLowecase = stu.getLastname().toLowerCase();
           String surnameLowecase = stu.getSurname().toLowerCase();
   		   String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
   		   String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
   		   String formatedsurname = surnameLowecase.substring(0,1).toUpperCase()+surnameLowecase.substring(1);
   		   
           studNameHash.put(stu.getUuid(),formatedFirstname + " " + formatedLastname + " " + formatedsurname +"\n"); 
            }
          
          List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
          classroomList = roomDAO.getAllRooms(school.getUuid()); 
           for(ClassRoom c : classroomList){
                roomHash.put(c.getUuid() , c.getRoomName());
                 }
           
          
          
          
          

   document = new Document(PageSize.A3, 46, 46, 64, 64);

   try {
       writer = PdfWriter.getInstance(document, response.getOutputStream());
       

       PdfUtil event = new PdfUtil();
       writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
       writer.setPageEvent(event);

       populatePDFDocument(statistics, school,classroomuuid,perfomanceList,pDistinctList, context.getRealPath("/images/fastech.png"));

   } catch (DocumentException e) {
       logger.error("DocumentException while writing into the document");
       logger.error(ExceptionUtils.getStackTrace(e));
   }
   
   
    }



/**
 * @param statistics
 * @param school
 * @param classroomuuid
 * @param perfomanceList
 * @param pDistinctList
 * @param realPath
 */
private void populatePDFDocument(SessionStatistics statistics, SchoolAccount school, String classroomuuid, 
	  List<Perfomance> perfomanceList, List<Perfomance> pDistinctList,String realPath) {
	
   SimpleDateFormat formatter;
   String formattedDate;
   Date date = new Date();
   
   Map<String,Double> kswscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> engscorehash = new LinkedHashMap<String,Double>(); 
   
   Map<String,Double> physcoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> matscorehash = new LinkedHashMap<String,Double>(); 
   Map<String,Double> bioscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> chemscorehash = new LinkedHashMap<String,Double>(); 
   
   Map<String,Double> bsscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> agriscorehash = new LinkedHashMap<String,Double>(); 
   Map<String,Double> hscscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> comscoreMap = new LinkedHashMap<String,Double>();
   
   Map<String,Double> geoscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> crescorehash = new LinkedHashMap<String,Double>(); 
   Map<String,Double> histscoreMap = new LinkedHashMap<String,Double>();
   
   String totalz = "";
   try {
   document.open();
  
  
   
   Paragraph preface = new Paragraph();
   preface.add(createImage(realPath));
  
   // Lets write a big header
   preface.add(new Paragraph(PDF_TITLE, bigFont));

  // addEmptyLine(preface, 1);

   formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm z");
  // formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
   formattedDate = formatter.format(date);
   
   DecimalFormat df = new DecimalFormat("0.00"); 
   df.setRoundingMode(RoundingMode.DOWN);
   
   DecimalFormat rf = new DecimalFormat("0.0"); 
   rf.setRoundingMode(RoundingMode.HALF_UP);
		
   DecimalFormat rf2 = new DecimalFormat("0"); 
   rf2.setRoundingMode(RoundingMode.UP);

   // Will create: Report generated by: _name, _date
   preface.add(new Paragraph(PDF_SUBTITLE + school.getSchoolName(), smallBold));

   preface.add(new Paragraph(formattedDate, smallBold));

   preface.setAlignment(Element.ALIGN_RIGHT);


   // step 4
  
   
   BaseColor baseColor=new BaseColor(202,225,255);
   
   Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
   
   Map<String,Double> engcat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> kiscat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> matcat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> phycat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> biocat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> chemcat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> bscat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> agrcat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> comcat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> hsccat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> geocat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> hiscat1scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> crecat1scoreMap = new LinkedHashMap<String,Double>();
   
   Map<String,Double> engcat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> kiscat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> matcat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> phycat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> biocat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> chemcat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> bscat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> agrcat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> comcat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> hsccat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> geocat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> hiscat2scoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> crecat2scoreMap = new LinkedHashMap<String,Double>();
   
   Map<String,Double> engendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> kisendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> matendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> phyendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> bioendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> chemendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> bsendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> agrendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> comendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> hscendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> geoendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> hisendtscoreMap = new LinkedHashMap<String,Double>();
   Map<String,Double> creendtscoreMap = new LinkedHashMap<String,Double>();
   
   MiddleNumberFor3 middle = new MiddleNumberFor3();
   List<Perfomance> list = new ArrayList<>();
   Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
   double languageScore = 0;
   double scienceScore = 0;
   double humanityScore = 0;
   double techinicalScore = 0;
   double grandscore = 0;
   double number = 0.0;
   
   if(pDistinctList !=null){
	   int mycount =1;
    for(Perfomance s : pDistinctList){                              
        list = perfomanceDAO.getPerformance(school.getUuid(), classroomuuid, s.getStudentUuid());
              
           engscore = 0;
           kswscore = 0;
           matscore = 0;
           physcore = 0;
           bioscore = 0;
           chemscore = 0;
           bsscore = 0;
           comscore = 0;
           hscscore = 0;
           agriscore = 0;
           geoscore = 0;
           crescore = 0;
           histscore = 0;
           
           examcattotal = 0; cat1  = 0; cat2  = 0; endterm = 0; catTotals = 0; catmean = 0;
       
        for(Perfomance pp : list){
          
          //Languages
          //Include all the languages
        	if(true){
                if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
              	  
              		    cat1 = pp.getCatOne();
                        cat2 = pp.getCatTwo();
                        endterm = pp.getEndTerm();
                        catTotals = cat1 + cat2;
                        catmean = catTotals/2;
                        examcattotal = catmean + endterm;
                        engscore = examcattotal; 
                        
                        examcattotal = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(examcattotal)))));
                        cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                        cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                        endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                        engscorehash.put(s.getStudentUuid(),examcattotal);
                        engcat1scoreMap.put(s.getStudentUuid(), cat1);
                        engcat2scoreMap.put(s.getStudentUuid(), cat2);
                        engendtscoreMap.put(s.getStudentUuid(), endterm);
                     
                 
                }

                 if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
              	  
               		     cat1 = pp.getCatOne();
                         cat2 = pp.getCatTwo();
                         endterm = pp.getEndTerm();
                         catTotals = cat1 + cat2;
                         catmean = catTotals/2;
                         examcattotal = catmean + endterm;
                         kswscore = examcattotal; 
                         kswscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(kswscore)))));
                         cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                         cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                         endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                         kswscoreMap.put(s.getStudentUuid(),kswscore);
                         kiscat1scoreMap.put(s.getStudentUuid(), cat1);                        
                         kiscat2scoreMap.put(s.getStudentUuid(), cat2);
                         kisendtscoreMap.put(s.getStudentUuid(), endterm);
                     
              	   
                     }

                  
                 languageScore = (engscore+kswscore); 
                
              }       
         //Sciences
         //Pick best two if the student take the three
         if(true){
         double subjectBig = 0;
         double subjectSmall = 0;
        
                 if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
              	   
               		     cat1 = pp.getCatOne();
                         cat2 = pp.getCatTwo();
                         endterm = pp.getEndTerm();
                         catTotals = cat1 + cat2;
                         catmean = catTotals/2;
                         examcattotal = catmean + endterm;
                         physcore = examcattotal; 
                         physcore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(physcore)))));
                         cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                         cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                         endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                         physcoreMap.put(s.getStudentUuid(),physcore);
                         phycat1scoreMap.put(s.getStudentUuid(), cat1);                  
                         phycat2scoreMap.put(s.getStudentUuid(), cat2);
                         phyendtscoreMap.put(s.getStudentUuid(), endterm);
              	   
                   }
                 if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
              	   
               		     cat1 = pp.getCatOne();
                         cat2 = pp.getCatTwo();
                         endterm = pp.getEndTerm();
                         catTotals = cat1 + cat2;
                         catmean = catTotals/2;
                         examcattotal = catmean + endterm;
                         bioscore = examcattotal; 
                         bioscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bioscore)))));
                         cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                         cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                         endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                         bioscoreMap.put(s.getStudentUuid(),bioscore);
                         biocat1scoreMap.put(s.getStudentUuid(), cat1);              
                         biocat2scoreMap.put(s.getStudentUuid(), cat2);
                         bioendtscoreMap.put(s.getStudentUuid(), endterm);
              	     
                   }
                    if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
                  	  
                  		    cat1 = pp.getCatOne();
                            cat2 = pp.getCatTwo();
                            endterm = pp.getEndTerm();
                            catTotals = cat1 + cat2;
                            catmean = catTotals/2;
                            examcattotal = catmean + endterm;
                            chemscore = examcattotal; 
                            chemscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(chemscore)))));
                            cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                            cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                            endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                            chemscorehash.put(s.getStudentUuid(),chemscore);
                            chemcat1scoreMap.put(s.getStudentUuid(), cat1);                           
                            chemcat2scoreMap.put(s.getStudentUuid(), cat2);
                            chemendtscoreMap.put(s.getStudentUuid(), endterm);
                  	  
                   }
                    if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
                  	  
                  		    cat1 = pp.getCatOne();
                            cat2 = pp.getCatTwo();
                            endterm = pp.getEndTerm();
                            catTotals = cat1 + cat2;
                            catmean = catTotals/2;
                            examcattotal = catmean + endterm;
                            matscore = examcattotal; 
                            matscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(matscore)))));
                            cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                            cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                            endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                            matscorehash.put(s.getStudentUuid(),matscore);
                            matcat1scoreMap.put(s.getStudentUuid(), cat1);                          
                            matcat2scoreMap.put(s.getStudentUuid(), cat2);
                            matendtscoreMap.put(s.getStudentUuid(), endterm);
                  	  
                   }


                    subjectBig = Math.max( (Math.max(physcore, bioscore)), Math.max(Math.max(physcore, bioscore), chemscore));
                    subjectSmall = middle.ComputeMiddle(physcore, bioscore, chemscore);
					scienceScore = (subjectBig+subjectSmall+matscore);
                    }
             
         //Technical 
         //Here we pick one subject, the one he/she has performed best, but this subject can be replaced by a science,
         //if the student takes 3 sciences and he/she performed better in the science than in all  the techinicals . 
         if(true){
         double bestTechinical = 0;
                 if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
              	   
               		     cat1 = pp.getCatOne();
                         cat2 = pp.getCatTwo();
                         endterm = pp.getEndTerm();
                         catTotals = cat1 + cat2;
                         catmean = catTotals/2;
                         examcattotal = catmean + endterm;
                         bsscore = examcattotal; 
                         bsscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bsscore)))));
                         cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                         cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                         endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                         bsscoreMap.put(s.getStudentUuid(),bsscore);
                         bscat1scoreMap.put(s.getStudentUuid(), cat1);                        
                         bscat2scoreMap.put(s.getStudentUuid(), cat2);
                         bsendtscoreMap.put(s.getStudentUuid(), endterm);
                     
                   }
                    if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
                  	  
                  		    cat1 = pp.getCatOne();
                            cat2 = pp.getCatTwo();
                            endterm = pp.getEndTerm();
                            catTotals = cat1 + cat2;
                            catmean = catTotals/2;
                            examcattotal = catmean + endterm;
                            agriscore = examcattotal; 
                            agriscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(agriscore)))));
                            cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                            cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                            endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                            agriscorehash.put(s.getStudentUuid(),agriscore);
                            agrcat1scoreMap.put(s.getStudentUuid(), cat1);                         
                            agrcat2scoreMap.put(s.getStudentUuid(), cat2);
                            agrendtscoreMap.put(s.getStudentUuid(), endterm);
                  	  
                  	    
                   }     
                   if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
                  	 
                 		   cat1 = pp.getCatOne();
                           cat2 = pp.getCatTwo();
                           endterm = pp.getEndTerm();
                           catTotals = cat1 + cat2;
                           catmean = catTotals/2;
                           examcattotal = catmean + endterm;
                           hscscore = examcattotal; 
                           hscscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(hscscore)))));
                           cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                           cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                           endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                           hscscoreMap.put(s.getStudentUuid(),hscscore);
                           hsccat1scoreMap.put(s.getStudentUuid(), cat1);                          
                           hsccat2scoreMap.put(s.getStudentUuid(), cat2);
                           hscendtscoreMap.put(s.getStudentUuid(), endterm);
                         
                  	 
                   }
                   if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
                  	
                 		   cat1 = pp.getCatOne();
                           cat2 = pp.getCatTwo();
                           endterm = pp.getEndTerm();
                           catTotals = cat1 + cat2;
                           catmean = catTotals/2;
                           examcattotal = catmean + endterm;
                           comscore = examcattotal; 
                           comscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(comscore)))));
                           cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                           cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                           endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                           comscoreMap.put(s.getStudentUuid(),comscore);
                           comcat1scoreMap.put(s.getStudentUuid(), cat1);                           
                           comcat2scoreMap.put(s.getStudentUuid(), cat2);
                           comendtscoreMap.put(s.getStudentUuid(), endterm);
                  
                          } 
                  

                   bestTechinical = Math.max( (Math.max(bsscore, agriscore)), Math.max(hscscore, comscore));
			       techinicalScore = bestTechinical; 
                   
              }     

         //Humanities
         //Here we pick only one subject, the one the student has performed best . 
         if(true){  
           double bestHumanity = 0;     
                 if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
              	   
               		     cat1 = pp.getCatOne();
                         cat2 = pp.getCatTwo();
                         endterm = pp.getEndTerm();
                         catTotals = cat1 + cat2;
                         catmean = catTotals/2;
                         examcattotal = catmean + endterm;
                         geoscore = examcattotal; 
                         geoscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(geoscore)))));
                         cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                         cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                         endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                         geoscoreMap.put(s.getStudentUuid(),geoscore);
                         geocat1scoreMap.put(s.getStudentUuid(), cat1);                        
                         geocat2scoreMap.put(s.getStudentUuid(), cat2);
                         geoendtscoreMap.put(s.getStudentUuid(), endterm);
              	        
                   }
                if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
              	 
              		    cat1 = pp.getCatOne();
                        cat2 = pp.getCatTwo();
                        endterm = pp.getEndTerm();
                        catTotals = cat1 + cat2;
                        catmean = catTotals/2;
                        examcattotal = catmean + endterm;
                        crescore = examcattotal; 
                        crescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(crescore)))));
                        cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                        cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                        endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                        crescorehash.put(s.getStudentUuid(),crescore);
                        crecat1scoreMap.put(s.getStudentUuid(), cat1);                        
                        crecat2scoreMap.put(s.getStudentUuid(), cat2);
                        creendtscoreMap.put(s.getStudentUuid(), endterm);
                       
                
                   }
                if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
                
                   
              		    cat1 = pp.getCatOne();
                        cat2 = pp.getCatTwo();
                        endterm = pp.getEndTerm();
                        catTotals = cat1 + cat2;
                        catmean = catTotals/2;
                        examcattotal = catmean + endterm;
                        histscore = examcattotal; 
                        histscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(histscore)))));
                        cat1 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat1)))));
                        cat2 = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(cat2)))));
                        endterm = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(endterm)))));
                        histscoreMap.put(s.getStudentUuid(),histscore);
                        hiscat1scoreMap.put(s.getStudentUuid(), cat1);                       
                        hiscat2scoreMap.put(s.getStudentUuid(), cat2);
                        hisendtscoreMap.put(s.getStudentUuid(), endterm);
                    
              	    
                   }

                bestHumanity = Math.max( (Math.max(geoscore, crescore)), Math.max(Math.max(geoscore, crescore), histscore));
				humanityScore = bestHumanity; 

             } 
  

      }
       
        grandscore = languageScore+scienceScore+humanityScore+techinicalScore;
        languageScore = 0; scienceScore = 0; humanityScore = 0;techinicalScore = 0;  
        grandscoremap.put(s.getStudentUuid(), grandscore);   
        grandscore = 0;
        Finalposition = mycount++;

      }

       double engc1 = 0,kisc1 = 0,matc1 = 0,phyc1 = 0,chemc1 = 0,bioc1 = 0;
       double bsc1 = 0,agrc1 = 0,hscc1 = 0,comc1 = 0,crec1 = 0,hisc1 = 0,geoc1 = 0;
       
       double engc2 = 0,kisc2 = 0,matc2 = 0,phyc2 = 0,chemc2 = 0,bioc2 = 0;
       double bsc2 = 0,agrc2 = 0,hscc2 = 0,comc2 = 0,crec2 = 0,hisc2 = 0,geoc2 = 0;
       
       double enget = 0,kiset = 0,matet = 0,phyet = 0,chemet = 0,bioet = 0;
       double bset = 0,agret = 0,hscet = 0,comet = 0,creet = 0,hiset = 0,geoet = 0;
       
       String engc1str = "",kisc1str = "",matc1str = "",phyc1str = "",chemc1str = "",bioc1str = "";
       String bsc1str = "",agrc1str = "",hscc1str = "",comc1str = "",crec1str = "",hisc1str = "",geoc1str = "";
       
       String engc2str = "",kisc2str = "",matc2str = "",phyc2str = "",chemc2str = "",bioc2str = "";
       String bsc2str = "",agrc2str = "",hscc2str = "",comc2str = "",crec2str = "",hisc2str = "",geoc2str = "";
       
       String engetstr = "",kisetstr = "",matetstr = "",phyetstr = "",chemetstr = "",bioetstr = "";
       String bsetstr = "",agretstr = "",hscetstr = "",cometstr = "",creetstr = "",hisetstr = "",geoetstr = "";
      


         @SuppressWarnings("unchecked")
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


          
	       position = 1;
	       int counttwo = 1;
	       
           for(Object o : as){
              String items = String.valueOf(o);
              String [] item = items.split("=");
              String uuid = item[0];
              totalz = item[1];
              double mean = 0;
              mean = Double.parseDouble(totalz)/7;  
             
              studeadmno = studentAdmNoHash.get(uuid);
              studename = studNameHash.get(uuid);   

              engc1 = 0;kisc1 = 0;matc1 = 0;phyc1 = 0;chemc1 = 0;bioc1 = 0;
              bsc1 = 0;agrc1 = 0;hscc1 = 0;comc1 = 0;crec1 = 0;hisc1 = 0;geoc1 = 0;
              
              engc2 = 0;kisc2 = 0;matc2 = 0;phyc2 = 0;chemc2 = 0;bioc2 = 0;
              bsc2 = 0;agrc2 = 0;hscc2 = 0;comc2 = 0;crec2 = 0;hisc2 = 0;geoc2 = 0;
              
              enget = 0;kiset = 0;matet = 0;phyet = 0;chemet = 0;bioet = 0;
              bset = 0;agret = 0;hscet = 0;comet = 0;creet = 0;hiset = 0;geoet = 0;

              if(engscorehash.get(uuid)!=null){
                engscore = engscorehash.get(uuid);  
                engscorestr =  rf2.format(engscore);
	                
              }else{
                engscorestr = "";
              }
              if(engcat1scoreMap.get(uuid)!=null){
              	engc1 = engcat1scoreMap.get(uuid); 
              	engc1str =  rf2.format(engc1);
              }else{
              	engc1str = " ";
              }
              if(engcat2scoreMap.get(uuid)!=null){
              	engc2 = engcat2scoreMap.get(uuid); 
              	engc2str =  rf2.format(engc2);
              }else{
              	engc2str = " ";
              }
              if(engendtscoreMap.get(uuid)!=null){
              	enget = engendtscoreMap.get(uuid);
              	engetstr = rf2.format(enget);
              }else{
              	engetstr = " ";
              }



              if(kswscoreMap.get(uuid)!=null){
                kswscore = kswscoreMap.get(uuid);               
                kswscorestr = rf2.format(kswscore);
                
              }else{
                kswscorestr = "";
              }
              if(kiscat1scoreMap.get(uuid)!=null){
              	kisc1 = kiscat1scoreMap.get(uuid); 
              	kisc1str = rf2.format(kisc1);
              }else{
              	kisc1str = " ";
              }
              if(kiscat2scoreMap.get(uuid)!=null){
              	kisc2 = kiscat2scoreMap.get(uuid);   
              	kisc2str = rf2.format(kisc2);
              }else{
              	kisc2str = " ";
              }
              if(kisendtscoreMap.get(uuid)!=null){
              	kiset = kisendtscoreMap.get(uuid);  
              	kisetstr = rf2.format(kiset);
              }else{
              	kisetstr = " ";
              }


              if(physcoreMap.get(uuid)!=null){
                physcore = physcoreMap.get(uuid);              
                physcorestr = rf2.format(physcore);
	                
              }else{
                physcorestr = "";
              }
              if(phycat1scoreMap.get(uuid)!=null){
              	phyc1 = phycat1scoreMap.get(uuid);   
              	phyc1str = rf2.format(phyc1);
              }else{
              	phyc1str = " ";
              }
              if(phycat2scoreMap.get(uuid)!=null){
              	phyc2 = phycat2scoreMap.get(uuid); 
              	phyc2str = rf2.format(phyc2);
              }else{
              	phyc2str = " ";
              }if(phyendtscoreMap.get(uuid)!=null){
              	phyet = phyendtscoreMap.get(uuid); 
              	phyetstr = rf2.format(phyet);
              }else{
              	phyetstr = " ";
              }

              if(bioscoreMap.get(uuid)!=null){
                bioscore = bioscoreMap.get(uuid);              
                bioscorestr = rf2.format(bioscore);
	                
              }else{
                bioscorestr = "";
              }
              if(biocat1scoreMap.get(uuid)!=null){
              	bioc1 = biocat1scoreMap.get(uuid); 
              	bioc1str = rf2.format(bioc1);
              }else{
              	bioc1str = " ";
              }
              if(biocat2scoreMap.get(uuid)!=null){
              	bioc2 = biocat2scoreMap.get(uuid); 
              	bioc2str = rf2.format(bioc2);
              }else{
              	bioc2str = " ";
              }
              if(bioendtscoreMap.get(uuid)!=null){
              	bioet = bioendtscoreMap.get(uuid); 
              	bioetstr = rf2.format(bioet);
              }else{
              	bioetstr = " ";
              }


              if(chemscorehash.get(uuid)!=null){
                chemscore = chemscorehash.get(uuid);              
                chemscorestr = rf2.format(chemscore);
	               
              }else{
                chemscorestr = "";
              } 
              if(chemcat1scoreMap.get(uuid)!=null){
              	chemc1 = chemcat1scoreMap.get(uuid); 
              	chemc1str = rf2.format(chemc1);
              }else{
              	chemc1str = " ";
              }
              if(chemcat2scoreMap.get(uuid)!=null){
              	chemc2 = chemcat2scoreMap.get(uuid); 
              	chemc2str = rf2.format(chemc2);
              }else{
              	chemc2str = " ";
              }
              if(chemendtscoreMap.get(uuid)!=null){
              	chemet = chemendtscoreMap.get(uuid);  
              	chemetstr = rf2.format(chemet);
              }else{
              	chemetstr = " ";
              }

           

              if(matscorehash.get(uuid)!=null){
                matscore = matscorehash.get(uuid);              
                matscorestr = rf2.format(matscore);
                
              }else{
                matscorestr = "";
              }
              if(matcat1scoreMap.get(uuid)!=null){
              	matc1 = matcat1scoreMap.get(uuid);   
              	matc1str = rf2.format(matc1);
              }else{
              	matc1str = " ";
              }
              if(matcat2scoreMap.get(uuid)!=null){
              	matc2 = matcat2scoreMap.get(uuid);  
              	matc2str = rf2.format(matc2);
              }else{
              	matc2str = " ";
              }
              if(matendtscoreMap.get(uuid)!=null){
              	matet = matendtscoreMap.get(uuid); 
              	matetstr = rf2.format(matet);
              }else{
              	matetstr = " ";
              }
              
              

               if(histscoreMap.get(uuid)!=null){
                histscore = histscoreMap.get(uuid);              
                histscorestr = rf2.format(histscore);
                
              }else{
                histscorestr = "";
              }
               if(hiscat1scoreMap.get(uuid)!=null){
               	hisc1 = hiscat1scoreMap.get(uuid); 
               	hisc1str = rf2.format(hisc1);
               }else{
               	hisc1str = " ";
               }
               if(hiscat2scoreMap.get(uuid)!=null){
               	hisc2 = hiscat2scoreMap.get(uuid);
               	hisc2str = rf2.format(hisc2);
               }else{
               	hisc2str = " ";
               }
               if(hisendtscoreMap.get(uuid)!=null){
               	hiset = hisendtscoreMap.get(uuid);  
               	hisetstr = rf2.format(hiset);
               }else{
               	hisetstr = " ";
               }
               
               

              if(crescorehash.get(uuid)!=null){
                crescore = crescorehash.get(uuid);              
                crescorestr = rf2.format(crescore);
	                
              }else{
                crescorestr = "";
              }
              if(crecat1scoreMap.get(uuid)!=null){
              	crec1 = crecat1scoreMap.get(uuid);  
              	crec1str = rf2.format(crec1);
              }else{
              	crec1str = " ";
              }
              if(crecat2scoreMap.get(uuid)!=null){
              	crec2 = crecat2scoreMap.get(uuid);  
              	crec2str = rf2.format(crec2);
              }else{
              	crec2str = " ";
              }
              if(creendtscoreMap.get(uuid)!=null){
              	creet = creendtscoreMap.get(uuid);
              	creetstr = rf2.format(creet);
              }else{
              	creetstr = " ";
              }
              
              


              if(geoscoreMap.get(uuid)!=null){
                geoscore = geoscoreMap.get(uuid);               
                geoscorestr = rf2.format(geoscore);
                
              }else{
                geoscorestr = "";
              }
              if(geocat1scoreMap.get(uuid)!=null){
              	geoc1 = geocat1scoreMap.get(uuid); 
              	geoc1str = rf2.format(geoc1);
              }else{
              	geoc1str = " ";
              }
              if(geocat2scoreMap.get(uuid)!=null){
              	geoc2 = geocat2scoreMap.get(uuid);  
              	geoc2str = rf2.format(geoc2);
              }else{
              	geoc2str = " ";
              }
              if(geoendtscoreMap.get(uuid)!=null){
              	geoet = geoendtscoreMap.get(uuid);  
              	geoetstr = rf2.format(geoet);
              }else{
              	geoetstr = " ";
              }
              
              


              if(bsscoreMap.get(uuid)!=null){
                bsscore = bsscoreMap.get(uuid);             
                bsscorestr = rf2.format(bsscore);
                
              }else{
                bsscorestr = "";
              }
              if(bscat1scoreMap.get(uuid)!=null){
              	bsc1 = bscat1scoreMap.get(uuid);
              	bsc1str = rf2.format(bsc1);
              }else{
              	bsc1str = " ";
              }
              if(bscat2scoreMap.get(uuid)!=null){
              	bsc2 = bscat2scoreMap.get(uuid); 
              	bsc2str = rf2.format(bsc2);
              }else{
              	bsc2str = " ";
              }
              if(bsendtscoreMap.get(uuid)!=null){
              	bset = bsendtscoreMap.get(uuid);  
              	bsetstr = rf2.format(bset);
              }else{
              	bsetstr = " ";
              }
              
              
             
              if(agriscorehash.get(uuid)!=null){
                agriscore = agriscorehash.get(uuid);              
                agriscorestr = rf2.format(agriscore);
                
              }else{
                agriscorestr = "";
              }
              if(agrcat1scoreMap.get(uuid)!=null){
              	agrc1 = agrcat1scoreMap.get(uuid); 
              	agrc1str = rf2.format(agrc1);
              }else{
              	agrc1str = " ";
              }
              if(agrcat2scoreMap.get(uuid)!=null){
              	agrc2 = agrcat2scoreMap.get(uuid); 
              	agrc2str = rf2.format(agrc2);
              }else{
              	agrc2str = " ";
              }
              if(agrendtscoreMap.get(uuid)!=null){
              	agret = agrendtscoreMap.get(uuid); 
              	agretstr = rf2.format(agret);
              }else{
              	agretstr = " ";
              }
              
              
              if(hscscoreMap.get(uuid)!=null){
                hscscore = hscscoreMap.get(uuid);              
                hscscorestr = rf2.format(hscscore);
                
              }else{
                hscscorestr = "";
              }
              if(hsccat1scoreMap.get(uuid)!=null){
              	hscc1 = hsccat1scoreMap.get(uuid); 
              	hscc1str = rf2.format(hscc1);
              }else{
              	hscc1str = " ";
              }
              if(hsccat2scoreMap.get(uuid)!=null){
              	hscc2 = hsccat2scoreMap.get(uuid);  
              	hscc2str = rf2.format(hscc2);
              }else{
              	hscc2str = " ";
              }
              if(hscendtscoreMap.get(uuid)!=null){
              	hscet = hscendtscoreMap.get(uuid); 
              	hscetstr = rf2.format(hscet);              	
              }else{
              	hscetstr = " ";
              }
              
              

              if(comscoreMap.get(uuid)!=null){
                comscore = comscoreMap.get(uuid);               
                comscorestr = rf2.format(comscore);
	                
                
              }else{
                comscorestr = "";
              }   

              if(comcat1scoreMap.get(uuid)!=null){
              	comc1 = comcat1scoreMap.get(uuid);   
              	comc1str = rf2.format(comc1);
              }else{
              	comc1str = "";
              }
              
              if(comcat2scoreMap.get(uuid)!=null){
              	comc2 = comcat2scoreMap.get(uuid);  
              	comc2str = rf2.format(comc2);
              }else{
              	comc2str = "";
              }
              
              if(comendtscoreMap.get(uuid)!=null){
              	comet = comendtscoreMap.get(uuid);  
              	cometstr = rf2.format(comet);
              }else{
              	cometstr = "";
              }
              
            
            	   //add to document
               	  // Paragraph school_name = new Paragraph(("SCHOOL NAME: "+school.getSchoolName()));
               	   Paragraph class_name = new Paragraph(("CLASS :"+roomHash.get(classroomuuid))); 
               	   Paragraph year = new Paragraph(("YEAR:  "  + 2016));
               	   Paragraph term = new Paragraph(("TERM: "  + 1));
               	   Paragraph admno = new Paragraph(("ADM NO: "+studentAdmNoHash.get(uuid))); 
               	   Paragraph fullname = new Paragraph(("NAME: "+studNameHash.get(uuid)));
               	   Paragraph emptyline = new Paragraph(("                              "));
               	   
                	//table here
               	   PdfPCell CountHeader = new PdfPCell(new Paragraph("*",boldFont));
               	   CountHeader.setBackgroundColor(baseColor);
               	   CountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
               	   
               	   PdfPCell subjectHeader = new PdfPCell(new Paragraph("SUBJECT",boldFont));
                   subjectHeader.setBackgroundColor(baseColor);
                   subjectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
               	  
                   PdfPCell cat1Header = new PdfPCell(new Paragraph("CAT1",boldFont));
                   cat1Header.setBackgroundColor(baseColor);
                   cat1Header.setHorizontalAlignment(Element.ALIGN_LEFT);
            	   
            	   PdfPCell cat2Header = new PdfPCell(new Paragraph("CAT2",boldFont));
            	   cat2Header.setBackgroundColor(baseColor);
            	   cat2Header.setHorizontalAlignment(Element.ALIGN_LEFT);
               	   
                   PdfPCell endtermHeader = new PdfPCell(new Paragraph("E.TERM",boldFont)); 
                   endtermHeader.setBackgroundColor(baseColor);
                   endtermHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            	   
            	   PdfPCell scoreHeader = new PdfPCell(new Paragraph("SCORE",boldFont));
               	   scoreHeader.setBackgroundColor(baseColor);
               	   scoreHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
               	   
               	   PdfPCell gradeHeader = new PdfPCell(new Paragraph("GRADE",boldFont));
               	   gradeHeader.setBackgroundColor(baseColor);
               	   gradeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
               	   
                   PdfPCell remarkHeader = new PdfPCell(new Paragraph("REMARKS",boldFont));
                   remarkHeader.setBackgroundColor(baseColor);
                   remarkHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                   PdfPTable myTable;
                  
                	   myTable = new PdfPTable(8); 
                       myTable.addCell(CountHeader);
                       myTable.addCell(subjectHeader);
                       myTable.addCell(cat1Header);
                       myTable.addCell(cat2Header);
                       myTable.addCell(endtermHeader);
                       myTable.addCell(scoreHeader);
                       myTable.addCell(gradeHeader);
                       myTable.addCell(remarkHeader);
                       myTable.setWidthPercentage(100); 
                       myTable.setWidths(new int[]{15,60,20,20,20,25,25,80});   
                       myTable.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                   
                   List<Subject> subList = subjectDAO.getAllSubjects();
                   
                   int count = 1;
                   for(Subject sub : subList){
                	   
                	   myTable.addCell(" "+count);
                	  
                	   if(StringUtils.equals(sub.getUuid(), ENG_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+engc1str);
                		   myTable.addCell(" "+engc2str);
                		   myTable.addCell(" "+engetstr);
                		   myTable.addCell(" "+engscorestr);
                		   myTable.addCell(" "+computeGrade(engscore));
                		   myTable.addCell(" "+computeRemarks(engscore));
                		   engscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), KISWA_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+kisc1str);
                		   myTable.addCell(" "+kisc2str);
                		   myTable.addCell(" "+kisetstr);
                		   myTable.addCell(" "+kswscorestr);
                		   myTable.addCell(" "+computeGrade(kswscore));
                		   myTable.addCell(" "+computeRemarks(kswscore));	  
                		   kswscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), MATH_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+matc1str);  
                		   myTable.addCell(" "+matc2str);  
                		   myTable.addCell(" "+matetstr);  
                		   myTable.addCell(" "+matscorestr);  
                		   myTable.addCell(" "+computeGrade(matscore));
                		   myTable.addCell(" "+computeRemarks(matscore));
                		   matscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), PHY_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+phyc1str);  
                		   myTable.addCell(" "+phyc2str); 
                		   myTable.addCell(" "+phyetstr); 
                		   myTable.addCell(" "+physcorestr); 
                		   myTable.addCell(" "+computeGrade(physcore));
                		   myTable.addCell(" "+computeRemarks(physcore));  
                		   physcore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), BIO_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+bioc1str);      
                		   myTable.addCell(" "+bioc2str);      
                		   myTable.addCell(" "+bioetstr);      
                		   myTable.addCell(" "+bioscorestr);      
                		   myTable.addCell(" "+computeGrade(bioscore));
                		   myTable.addCell(" "+computeRemarks(bioscore));
                		   bioscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), CHEM_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+chemc1str); 
                		   myTable.addCell(" "+chemc2str); 
                		   myTable.addCell(" "+chemetstr); 
                		   myTable.addCell(" "+chemscorestr); 
                		   myTable.addCell(" "+computeGrade(chemscore));
                		   myTable.addCell(" "+computeRemarks(chemscore));
                		   chemscore = 0;
                		 
                	   }if(StringUtils.equals(sub.getUuid(), BS_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+bsc1str);     
                		   myTable.addCell(" "+bsc2str);     
                		   myTable.addCell(" "+bsetstr);     
                		   myTable.addCell(" "+bsscorestr);     
                		   myTable.addCell(" "+computeGrade(bsscore));
                		   myTable.addCell(" "+computeRemarks(bsscore));
                		   bsscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), COMP_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+comc1str);  
                		   myTable.addCell(" "+comc2str);  
                		   myTable.addCell(" "+cometstr);  
                		   myTable.addCell(" "+comscorestr);  
                		   myTable.addCell(" "+computeGrade(comscore));
                		   myTable.addCell(" "+computeRemarks(comscore));
                		   comscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), H_S)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+hscc1str);
                		   myTable.addCell(" "+hscc2str);
                		   myTable.addCell(" "+hscetstr);
                		   myTable.addCell(" "+hscscorestr);
                		   myTable.addCell(" "+computeGrade(hscscore));
                		   myTable.addCell(" "+computeRemarks(hscscore));
                		   hscscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), AGR_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+agrc1str);      
                		   myTable.addCell(" "+agrc2str);      
                		   myTable.addCell(" "+agretstr);      
                		   myTable.addCell(" "+agriscorestr);      
                		   myTable.addCell(" "+computeGrade(agriscore));
                		   myTable.addCell(" "+computeRemarks(agriscore));
                		   agriscore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), GEO_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+geoc1str);     
                		   myTable.addCell(" "+geoc2str);     
                		   myTable.addCell(" "+geoetstr);     
                		   myTable.addCell(" "+geoscorestr);     
                		   myTable.addCell(" "+computeGrade(geoscore));
                		   myTable.addCell(" "+computeRemarks(geoscore));
                		   geoscore = 0;
                		
                	   }if(StringUtils.equals(sub.getUuid(), CRE_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+crec1str);   
                		   myTable.addCell(" "+crec2str);   
                		   myTable.addCell(" "+creetstr);   
                		   myTable.addCell(" "+crescorestr);   
                		   myTable.addCell(" "+computeGrade(crescore));
                		   myTable.addCell(" "+computeRemarks(crescore));
                		   crescore = 0;
                		   
                	   }if(StringUtils.equals(sub.getUuid(), HIST_UUID)){
                		   myTable.addCell(sub.getSubjectName());
                		   myTable.addCell(" "+hisc1str);    
                		   myTable.addCell(" "+hisc2str);    
                		   myTable.addCell(" "+hisetstr);    
                		   myTable.addCell(" "+histscorestr);    
                		   myTable.addCell(" "+computeGrade(histscore));
                		   myTable.addCell(" "+computeRemarks(histscore));
                		   histscore = 0;
                	   }
                	  
                	 
                	   count++;
                   }
                  
                    Paragraph myposition;
                   
                    if(mean==number){
                      myposition = new Paragraph(("POSITION " +(position-counttwo++)+ " OUT OF " +Finalposition));
                     }
                     else{
                      counttwo=1;
                      myposition = new Paragraph(("POSITION " +position+ " OUT OF " +Finalposition));
                     }
               
               	   Paragraph total = new Paragraph(("MEAN SCORE " + df.format(mean) + " GRADE " +computeGrade(mean)));
               	   
               	   Paragraph class_teacher_remarks = new Paragraph(("Class Teacher Remarks:  "+classteacherRemarks(mean)));
               	   
                   Paragraph class_teacher_sign = new Paragraph(("Class Teacher Signature :  ________________ "));
                   
               	   Paragraph principal = new Paragraph(("PRINCIPAL  Mr Karani          : SIGNATURE ________________ "));
               	   
                   Paragraph clossDate = new Paragraph(("Opening date :_________________ "));
            	   Paragraph opendate = new Paragraph(("Clossing date : " +formatter.format(new Date())));
            	   
               	   Paragraph stamp = new Paragraph(("[ STAMP  _______________ ]      : DATE " +formatter.format(new Date())));
               	   
               	   
               	   document.add(preface);
               	   document.add(class_name);
               	   document.add(year);
               	   document.add(term);
               	   document.add(admno);
               	   document.add(fullname);
               	   document.add(emptyline);
                   document.add(myTable);  
                   document.add(emptyline);
               	   document.add(myposition);
               	   document.add(total);
                   document.add(emptyline);
               	   document.add(class_teacher_remarks);
                   document.add(class_teacher_sign);
                   document.add(emptyline);
               	   document.add(principal);
               	   document.add(stamp);
               	   document.add(emptyline);
                   document.add(clossDate);
            	   document.add(opendate);
               	   
               	 
           	       document.newPage();
                      
           	    
           	    position++;
            	number=mean;
            
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

private String classteacherRemarks(double score) {
	String remarks = "";
	double mean = score;
	
	if(mean >= 83){
		remarks = "Execellent,let sky be your steping stone.";
    }else if(mean >= 71){
    	remarks = "Good work, sky might not be the limit.";
    }else if(mean >= 67){
    	remarks = "Good work, a little more effort please.";
    }else if(mean >= 61){
    	remarks = "Good work but, this is not your best.";
    }else if(mean >= 55){
    	remarks = "An average student, you have the potential.";
    }else if(mean >= 47){
    	remarks = "Below average, this is not your best.";
    }else if(mean >= 42){
    	remarks = "Below average, you can do beter than this.";
    }else if(mean >= 38){
    	remarks = "Below average, you can do beter.";
    }else if(mean >= 35){
    	remarks = "Below average, put more effort.";
    }else if(mean >= 30){
    	remarks = "Far below average, please you deserve better.";
    }else if(mean >= 25){
    	remarks = "Far much below average...but you deserve beter than this.";
    }else{
    	remarks = "This is extremly poor but still....can do beter.";
    }
	
	if(mean ==0){
		remarks = " ";
	}
	
	return remarks;
}

private String computeRemarks(double score) {
	String remarks = "";
	double mean = score;
	
	if(mean >= 83){
		remarks = "Execellent, keep it up.";
    }else if(mean >= 71){
    	remarks = "Good work, aim higher.";
    }else if(mean >= 67){
    	remarks = "Good job, aim high.";
    }else if(mean >= 61){
    	remarks = "well done, aim higher.";
    }else if(mean >= 55){
    	remarks = "well done, aim high.";
    }else if(mean >= 47){
    	remarks = "Fair, you can do better.";
    }else if(mean >= 42){
    	remarks = "Fair, put more effort.";
    }else if(mean >= 38){
    	remarks = "Fair, you can improve.";
    }else if(mean >= 35){
    	remarks = "Fair, pull up your socks.";
    }else if(mean >= 30){
    	remarks = "You can do better than this.";
    }else if(mean >= 25){
    	remarks = "You can do better than this.";
    }else{
    	remarks = "You can do better than this.";
    }
	
	if(mean ==0){
		remarks = " ";
	}
	
	return remarks;
}

/**
 * @param engscore2
 * @return
 */
private String computeGrade(double score) {
	double mean = score;
	if(mean >= 83){
        grade = "A";
    }else if(mean >= 71){
         grade = "A-";
    }else if(mean >= 67){
         grade = "B+";
    }else if(mean >= 61){
         grade = "B";
    }else if(mean >= 55){
         grade = "B-";
    }else if(mean >= 47){
         grade = "C+";
    }else if(mean >= 42){
         grade = "C";
    }else if(mean >= 38){
         grade = "C-";
    }else if(mean >= 35){
         grade = "D+";
    }else if(mean >= 30){
         grade = "D";
    }else if(mean >= 25){
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
