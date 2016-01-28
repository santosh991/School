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
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.ClassTeacher;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author peter
 * 
 */
public class ReportForm12 extends HttpServlet{


    private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
   // private Font normalText = new Font(Font.FontFamily.COURIER, 12);
    private Document document;
    private PdfWriter writer;
    private Cache schoolaccountCache, statisticsCache;

    private Logger logger;
    
    
    final String PDF_TITLE = "pdf title here";
    final String PDF_SUBTITLE = "Report Generated For: ";
    final String PDF_BOTTOM_TEXT = "pdf bottom text here";
    
    private static PerfomanceDAO perfomanceDAO;
    private static SubjectDAO subjectDAO;
    private static ClassTeacherDAO classTeacherDAO;
    private static StudentDAO studentDAO;
    
    String classroomuuid = "";
    String schoolusername = "";
    String stffID = "";
    
    HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
    HashMap<String, String> studNameHash = new HashMap<String, String>();
    
    
  
   
    double score = 0;

    double engscore = 0;
    String engscorestr = "";

    double kswscore = 0;
    String kswscorestr = "";

    double matscore = 0;
    String matscorestr = "";

    double physcore = 0;
    String physcorestr = "";  

     double bioscore = 0;
     String bioscorestr = "";

     double chemscore = 0;
     String chemscorestr = "";

     double bsscore = 0;
     String bsscorestr = "";

      double comscore = 0;
      String comscorestr = "";

      double hscscore = 0;
      String hscscorestr = "";

      double agriscore = 0;
      String agriscorestr = "";

      double geoscore = 0;
      String geoscorestr = "";

      double crescore = 0;
      String crescorestr = "";

      double histscore = 0;
      String histscorestr = "";


                                       
    String grade = "";
    String studeadmno = "";
    String studename = "";
    String admno = "";

    
    double cat1 = 0;  
    double cat2  = 0;
    double endterm  = 0;
    double examcattotal  = 0;
    double total  = 0;
    double pmean  = 0;
  //languages
    final String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
    final String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
    //sciences
    final String MATH_UUID = "4f59580d-1a16-4669-9ed5-4b89615d6903";
    final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";
    final String BIO_UUID = "de0c86be-9bcb-4d3b-8098-b06687536c1f";
    final String CHEM_UUID = "552c0a24-6038-440f-add5-2dadfb9a23bd";
    //techinicals
    final String BS_UUID = "e1729cc2-524a-4069-b4a4-be5aec8473fe";
    final String COMP_UUID = "F1972BF2-C788-4F41-94FE-FBA1869C92BC";
    final String H_S = "C1F28FF4-1A18-4552-822A-7A4767643643";
    final String AGR_UUID = "b9bbd718-b32f-4466-ab34-42f544ff900e";
    //humanities 
    final String GEO_UUID = "0e5dc1c6-f62f-4a36-a1ec-064173332694";
    final String CRE_UUID = "f098e943-26fd-4dc0-b6a0-2d02477004a4";
    final String HIST_UUID = "c9caf109-c27d-4062-9b9f-ac4268629e27";
    
    //MATH_UUID,PHY_UUID,BIO_UUID,CHEM_UUID,BS_UUID,COMP_UUID,H_S,AGR_UUID,GEO_UUID,CRE_UUID,HIST_UUID
    
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
   // We add one empty line
  // addEmptyLine(preface, 1);
   // Lets write a big header
   preface.add(new Paragraph(PDF_TITLE, bigFont));

  // addEmptyLine(preface, 1);

   formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm z");
  // formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
   formattedDate = formatter.format(date);
   
   DecimalFormat df = new DecimalFormat("0.00"); 
   df.setRoundingMode(RoundingMode.DOWN);
   
   DecimalFormat rf = new DecimalFormat("0"); 
   rf.setRoundingMode(RoundingMode.UP);

   // Will create: Report generated by: _name, _date
   preface.add(new Paragraph(PDF_SUBTITLE + school.getSchoolName(), smallBold));

   preface.add(new Paragraph(formattedDate, smallBold));

   //addEmptyLine(preface, 1);

  

  // preface.add(new Paragraph(PDF_BOTTOM_TEXT));

   preface.setAlignment(Element.ALIGN_RIGHT);

   //document.add(preface);

   // Start a new page
  // document.newPage();

   // step 4
  
   
   BaseColor baseColor=new BaseColor(202,225,255);
   
   Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
  
   
   List<Perfomance> list = new ArrayList<>();
   Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
   
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
       
        for(Perfomance pp : list){
        	
        	 cat1 = pp.getCatOne();
             cat2 = pp.getCatTwo();
             endterm = pp.getEndTerm();
             total = (cat1+cat2)/2 +endterm;
             grandscore +=total;
             if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
                 engscore = total;
                 engscorehash.put(s.getStudentUuid(),engscore);
                }
             
               if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
                kswscore = total;
                kswscoreMap.put(s.getStudentUuid(),kswscore);
               
                   }

                   if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
                  physcore = total;
                  physcoreMap.put(s.getStudentUuid(),physcore);
              
                 }


                    if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
               bioscore = total;
               bioscoreMap.put(s.getStudentUuid(),bioscore);
              
                 }
                  if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
               chemscore = total;
               chemscorehash.put(s.getStudentUuid(),chemscore);
              
                 }
                  if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
               matscore = total;
               matscorehash.put(s.getStudentUuid(),matscore);
              
                 }
                  if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
               bsscore = total;
               bsscoreMap.put(s.getStudentUuid(),bsscore);
              
                 }
                if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
               agriscore = total;
               agriscorehash.put(s.getStudentUuid(),agriscore);
              
                 }


                 if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
                 hscscore = total;
                 hscscoreMap.put(s.getStudentUuid(),hscscore);
              
                 }if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
                 comscore = total;
                 comscoreMap.put(s.getStudentUuid(),comscore);
              
                 } 



               if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
               geoscore = total;
               geoscoreMap.put(s.getStudentUuid(),geoscore);
              
                 }
                  if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
               crescore = total;
               crescorehash.put(s.getStudentUuid(),crescore);
              
                 }
                  if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
               histscore = total;
               histscoreMap.put(s.getStudentUuid(),histscore);
              
                 }

  		   
  		   
  	   }
         
  	    grandscoremap.put(s.getStudentUuid(), grandscore);
         grandscore = 0;


        Finalposition = mycount++;

      }




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
	       double mean = 0;
	       int counttwo = 1;
	       
           for(Object o : as){
              String items = String.valueOf(o);
              String [] item = items.split("=");
              String uuid = item[0];
              totalz = item[1];
              mean = Double.parseDouble(totalz)/11;

              studeadmno = studentAdmNoHash.get(uuid);
              studename = studNameHash.get(uuid);         
                 
             

              

              if(engscorehash.get(uuid)!=null){
                engscore = engscorehash.get(uuid);  
                //engGrade = computeGrade(engscore);
                engscorestr =  rf.format(engscore);

              }else{
                engscorestr = "";
              }


              if(kswscoreMap.get(uuid)!=null){
                kswscore = kswscoreMap.get(uuid);
                //kisGrade = computeGrade(kswscore);
                kswscorestr = rf.format(kswscore);
              }else{
                kswscorestr = "";
              }


              if(physcoreMap.get(uuid)!=null){
                physcore = physcoreMap.get(uuid);
               // phyGrade = computeGrade(physcore);
                physcorestr = rf.format(physcore);
              }else{
                physcorestr = "";
              }

              if(bioscoreMap.get(uuid)!=null){
                bioscore = bioscoreMap.get(uuid);
               // bioGrade = computeGrade(bioscore);
                bioscorestr = rf.format(bioscore);
              }else{
                bioscorestr = "";
              }


              if(chemscorehash.get(uuid)!=null){
                chemscore = chemscorehash.get(uuid);
               // chemGrade = computeGrade(chemscore);
                chemscorestr = rf.format(chemscore);
              }else{
                chemscorestr = "";
              }

              //,,,,,,,,,,

              if(matscorehash.get(uuid)!=null){
                matscore = matscorehash.get(uuid);
               // matGrade = computeGrade(matscore);
                matscorestr = rf.format(matscore);
              }else{
                matscorestr = "";
              }

               if(histscoreMap.get(uuid)!=null){
                histscore = histscoreMap.get(uuid);
               // hisGrade = computeGrade(histscore);
                histscorestr = rf.format(histscore);
              }else{
                histscorestr = "";
              }
              

              if(crescorehash.get(uuid)!=null){
                crescore = crescorehash.get(uuid);
               // creGrade = computeGrade(crescore);
                crescorestr = rf.format(crescore);
              }else{
                crescorestr = "";
              }


              if(geoscoreMap.get(uuid)!=null){
                geoscore = geoscoreMap.get(uuid);
                //geoGrade = computeGrade(geoscore);
                geoscorestr = rf.format(geoscore);
              }else{
                geoscorestr = "";
              }


              if(bsscoreMap.get(uuid)!=null){
                bsscore = bsscoreMap.get(uuid);
               // bsGrade = computeGrade(bsscore);
                bsscorestr = rf.format(bsscore);
              }else{
                bsscorestr = "";
              }

             
              if(agriscorehash.get(uuid)!=null){
                agriscore = agriscorehash.get(uuid);
               // agrGrade = computeGrade(agriscore);
                agriscorestr = rf.format(agriscore);
              }else{
                agriscorestr = "";
              }

              if(hscscoreMap.get(uuid)!=null){
                hscscore = hscscoreMap.get(uuid);
               // hscGrade = computeGrade(hscscore);
                hscscorestr = rf.format(hscscore);
              }else{
                hscscorestr = "";
              }

              if(comscoreMap.get(uuid)!=null){
                comscore = comscoreMap.get(uuid);
               // comGrade = computeGrade(comscore);
                comscorestr = rf.format(comscore);
              }else{
                comscorestr = "";
              }   
              
          
   	      

              //add to document
       	  // Paragraph school_name = new Paragraph(("SCHOOL NAME: "+school.getSchoolName()));
       	   Paragraph class_name = new Paragraph(("CLASS : FORM 1 S")); 
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
       	  
       	   PdfPCell scoreHeader = new PdfPCell(new Paragraph("SCORE",boldFont));
       	   scoreHeader.setBackgroundColor(baseColor);
       	   scoreHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
       	   
       	   PdfPCell gradeHeader = new PdfPCell(new Paragraph("GRADE",boldFont));
       	   gradeHeader.setBackgroundColor(baseColor);
       	   gradeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
       	   
           PdfPCell remarkHeader = new PdfPCell(new Paragraph("REMARKS",boldFont));
           remarkHeader.setBackgroundColor(baseColor);
           remarkHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
           
           PdfPTable myTable = new PdfPTable(5); 
           myTable.addCell(CountHeader);
           myTable.addCell(subjectHeader);
           myTable.addCell(scoreHeader);
           myTable.addCell(gradeHeader);
           myTable.addCell(remarkHeader);
           myTable.setWidthPercentage(80); 
           myTable.setWidths(new int[]{15,60,25,25,80});   
           myTable.setHorizontalAlignment(Element.ALIGN_LEFT);
           
           List<Subject> subList = subjectDAO.getAllSubjects();
           
           int count = 1;
           for(Subject sub : subList){
        	   
        	   
        	   
        	   myTable.addCell(" "+count);
        	  
        	   if(StringUtils.equals(sub.getUuid(), ENG_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+engscorestr);
        		   myTable.addCell(" "+computeGrade(engscore));
        		   myTable.addCell(" "+computeRemarks(engscore));
        		   engscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), KISWA_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+kswscorestr);
        		   myTable.addCell(" "+computeGrade(kswscore));
        		   myTable.addCell(" "+computeRemarks(kswscore));	  
        		   kswscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), MATH_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+matscorestr);        		 
        		   myTable.addCell(" "+computeGrade(matscore));
        		   myTable.addCell(" "+computeRemarks(matscore));
        		   matscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), PHY_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+physcorestr);        		  
        		   myTable.addCell(" "+computeGrade(physcore));
        		   myTable.addCell(" "+computeRemarks(physcore));  
        		   physcore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), BIO_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+bioscorestr);        		
        		   myTable.addCell(" "+computeGrade(bioscore));
        		   myTable.addCell(" "+computeRemarks(bioscore));
        		   bioscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), CHEM_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+chemscorestr);        		  
        		   myTable.addCell(" "+computeGrade(chemscore));
        		   myTable.addCell(" "+computeRemarks(chemscore));
        		   chemscore = 0;
        		 
        	   }if(StringUtils.equals(sub.getUuid(), BS_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+bsscorestr);        		  
        		   myTable.addCell(" "+computeGrade(bsscore));
        		   myTable.addCell(" "+computeRemarks(bsscore));
        		   bsscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), COMP_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+comscorestr);        		 
        		   myTable.addCell(" "+computeGrade(comscore));
        		   myTable.addCell(" "+computeRemarks(comscore));
        		   comscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), H_S)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+hscscorestr);        		 
        		   myTable.addCell(" "+computeGrade(hscscore));
        		   myTable.addCell(" "+computeRemarks(hscscore));
        		   hscscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), AGR_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+agriscorestr);        		  
        		   myTable.addCell(" "+computeGrade(agriscore));
        		   myTable.addCell(" "+computeRemarks(agriscore));
        		   agriscore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), GEO_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+geoscorestr);        		  
        		   myTable.addCell(" "+computeGrade(geoscore));
        		   myTable.addCell(" "+computeRemarks(geoscore));
        		   geoscore = 0;
        		
        	   }if(StringUtils.equals(sub.getUuid(), CRE_UUID)){
        		   myTable.addCell(sub.getSubjectName());
        		   myTable.addCell(" "+crescorestr);        		  
        		   myTable.addCell(" "+computeGrade(crescore));
        		   myTable.addCell(" "+computeRemarks(crescore));
        		   crescore = 0;
        		   
        	   }if(StringUtils.equals(sub.getUuid(), HIST_UUID)){
        		   myTable.addCell(sub.getSubjectName());
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
