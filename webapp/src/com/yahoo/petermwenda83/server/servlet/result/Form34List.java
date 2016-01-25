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
public class Form34List extends HttpServlet{


    private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font normalText = new Font(Font.FontFamily.COURIER, 12);
    private Document document;
    private PdfWriter writer;
    private Cache schoolaccountCache, statisticsCache;

    private Logger logger;
    
    
    final String PDF_TITLE = "pdf title here";
    final String PDF_SUBTITLE = "Report Generated For: ";
    final String PDF_BOTTOM_TEXT = "pdf bottom text here";
    
    private static PerfomanceDAO perfomanceDAO;
    //private static StudentExamDAO studentExamDAO;
    private static ClassTeacherDAO classTeacherDAO;
    private static StudentDAO studentDAO;
    
    String classroomuuid = "";
    String schoolusername = "";
    String stffID = "";
    
    HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
    HashMap<String, String> studNameHash = new HashMap<String, String>();
    
    
  
    double mean = 0;
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

    
    double paper1  = 0;
    double paper2  = 0;
    double paper3  = 0;
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
   //studentExamDAO = StudentExamDAO.getInstance();
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
   		   String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
   		   String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
   		   
           studNameHash.put(stu.getUuid(),formatedFirstname + " " + formatedLastname); 
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
   formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
   formattedDate = formatter.format(date);
   
   DecimalFormat df = new DecimalFormat("0.00"); 
   df.setRoundingMode(RoundingMode.DOWN);
   
   DecimalFormat rf = new DecimalFormat("0"); 
   rf.setRoundingMode(RoundingMode.UP);

   // Will create: Report generated by: _name, _date
   preface.add(new Paragraph(PDF_SUBTITLE + school.getSchoolName(), smallBold));

   preface.add(new Paragraph(formattedDate, smallBold));

   addEmptyLine(preface, 1);

  

  // preface.add(new Paragraph(PDF_BOTTOM_TEXT));

   preface.setAlignment(Element.ALIGN_RIGHT);

   document.add(preface);

   // Start a new page
  // document.newPage();

   // step 4
  
   
   BaseColor baseColor=new BaseColor(202,225,255);
   
   Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
   
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
   geoHeader.setBackgroundColor(baseColor);
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
   
   
   myTable.setWidths(new int[]{15,30,56,15,15,15,15,15,15,15,15,15,15,15,15,15,25,20,15});   
   
   
   myTable.setHorizontalAlignment(Element.ALIGN_LEFT);
   
   String studeadmno = "";
   String studename = "";
   
   
   List<Perfomance> list = new ArrayList<>();
   Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
   double languageScore = 0;
   double scienceScore = 0;
   double humanityScore = 0;
   double techinicalScore = 0;
   double grandscore = 0;
   double number = 0.0;
   
   if(pDistinctList !=null){
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
          
          //Languages
          //Include all the languages
          if(true){
                  if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
                     paper1 = pp.getPaperOne(); //out of 60
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 60
                     total = (paper1 + paper2 + paper3)/2; 
                     engscore = total; 
                     engscorehash.put(s.getStudentUuid(),engscore);
                    }
                   

                   if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
                     paper1 = pp.getPaperOne(); //out of 60
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 60
                     total = (paper1 + paper2 + paper3)/2; 
                     kswscore = total;
                     kswscoreMap.put(s.getStudentUuid(),kswscore);
                   
                       }

                    
                   languageScore = (engscore+kswscore); 
                  

                }       
           //Sciences
           //Pick best two if the student take the three
           if(true){
           double subjectBig = 0;
           double subjectSmall = 0;
          
                   if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
                     paper1 = pp.getPaperOne(); //out of 80
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 40
                     total = ((paper1 + paper2)/160)*60 + paper3;

                     physcore = total;
                     physcoreMap.put(s.getStudentUuid(),physcore);
                  
                     }
                   if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
                     paper1 = pp.getPaperOne(); //out of 80
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 40
                     total = ((paper1 + paper2)/160)*60 + paper3;
                     bioscore = total;
                     bioscoreMap.put(s.getStudentUuid(),bioscore);
                  
                     }
                      if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
                     paper1 = pp.getPaperOne(); //out of 80
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 40
                     total = ((paper1 + paper2)/160)*60 + paper3;
                     chemscore = total;
                     chemscorehash.put(s.getStudentUuid(),chemscore);
                  
                     }
                      if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
                     paper1 = pp.getPaperOne(); //out of 100
                     paper2 = pp.getPaperTwo(); //out of 100
                     total = (paper1 + paper2)/2;
                     matscore = total;
                     matscorehash.put(s.getStudentUuid(),matscore);
                  
                     }


                     if(physcore >= bioscore && physcore >= chemscore){
							subjectBig = physcore;
							
							if(subjectBig>bioscore && bioscore > chemscore){
								subjectSmall = bioscore;
							}else{
								subjectSmall = chemscore;
							}
							

						}else if(bioscore >= physcore && bioscore >= chemscore){
							subjectBig = bioscore;
							
							if(subjectBig>physcore && physcore > chemscore){
								subjectSmall = physcore;
							}else{
								subjectSmall = chemscore;
							}
							
						}else if(chemscore >= physcore && chemscore >= bioscore){
							subjectBig = chemscore;
							
							if(subjectBig>physcore && physcore > bioscore){
								subjectSmall = physcore;
							}else{
								subjectSmall = bioscore;
							}
						}

                     scienceScore = (subjectBig+subjectSmall+matscore);
                    
               }
           //Techinicals  
           //Here we pick one subject, the one he/she has performed best, but this subject can be replaced by a science, if the student takes 3 sciences and he/she performed better in the science than in all  the techinicals . 
           if(true){
           double bestTechinical = 0;
                   if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
                     paper1 = pp.getPaperOne(); //out of 100
                     paper2 = pp.getPaperTwo(); //out of 100
                     total = (paper1 + paper2)/2;
                     bsscore = total;
                     bsscoreMap.put(s.getStudentUuid(),bsscore);
                  
                     }
                      if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
                     paper1 = pp.getPaperOne(); //out of 80
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 40
                     total = (paper1 + paper2)/2 + paper3;
                     agriscore = total;
                     agriscorehash.put(s.getStudentUuid(),agriscore);
                  
                     }     
                     if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
                     paper1 = pp.getPaperOne(); //out of 80
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 40
                     total = (paper1 + paper2)/2 + paper3;
                     hscscore = total;
                     hscscoreMap.put(s.getStudentUuid(),hscscore);
                  
                     }if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
                     paper1 = pp.getPaperOne(); //out of 80
                     paper2 = pp.getPaperTwo(); //out of 80
                     paper3 = pp.getPaperThree();//out of 40
                     total = (paper1 + paper2)/2 + paper3;
                     comscore = total;
                     comscoreMap.put(s.getStudentUuid(),comscore);
                  
                     } 
                    

				       if(bsscore >= agriscore){
							bestTechinical= bsscore;
						}else{
							bestTechinical = agriscore;
						}
						
						
						if(bestTechinical <= hscscore){
							bestTechinical = hscscore;
						}
						if(bestTechinical <= comscore){
							bestTechinical = comscore;
						}


                      techinicalScore = bestTechinical;
                     
                }     

           //Humanities
           //Here we pick only one subject, the one the student has performed best . 
           if(true){  
             double bestHumanity = 0;     
                   if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
                     paper1 = pp.getPaperOne(); //out of 100
                     paper2 = pp.getPaperTwo(); //out of 100
                     total = (paper1 + paper2)/2;
                     geoscore = total;                                                 
                     geoscoreMap.put(s.getStudentUuid(),geoscore);
                  
                     }
                  if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
                     paper1 = pp.getPaperOne(); //out of 100
                     paper2 = pp.getPaperTwo(); //out of 100
                     total = (paper1 + paper2)/2;
                     crescore = total;
                     total=0;
                     crescorehash.put(s.getStudentUuid(),crescore);
                  
                     }
                  if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
                     paper1 = pp.getPaperOne(); //out of 100
                     paper2 = pp.getPaperTwo(); //out of 100
                     total = (paper1 + paper2)/2;
                     histscore = total;                                              
                     histscoreMap.put(s.getStudentUuid(),histscore);
                  
                     }


                     if(geoscore >= crescore){
							bestHumanity= geoscore;
						}else{
							bestHumanity = crescore;
						}
						
						
						if(bestHumanity <= histscore){
							bestHumanity = histscore;
						}



                   humanityScore = bestHumanity;

               } 
    

        }
    
        grandscore = languageScore+scienceScore+humanityScore+techinicalScore;
        languageScore = 0; scienceScore = 0; humanityScore = 0;techinicalScore = 0;  
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
         
           for(Object o : as){
              String items = String.valueOf(o);
              String [] item = items.split("=");
              String uuid = item[0];
              totalz = item[1];
              mean = Double.parseDouble(totalz)/7;

              studeadmno = studentAdmNoHash.get(uuid);
              studename = studNameHash.get(uuid);         
             

              

              if(engscorehash.get(uuid)!=null){
                engscore = engscorehash.get(uuid);                                                     
                engscorestr =  rf.format(engscore);

              }else{
                engscorestr = "";
              }


              if(kswscoreMap.get(uuid)!=null){
                kswscore = kswscoreMap.get(uuid);
                kswscorestr = rf.format(kswscore);
              }else{
                kswscorestr = "";
              }


              if(physcoreMap.get(uuid)!=null){
                physcore = physcoreMap.get(uuid);
                physcorestr = rf.format(physcore);
              }else{
                physcorestr = "";
              }

              if(bioscoreMap.get(uuid)!=null){
                bioscore = bioscoreMap.get(uuid);
                bioscorestr = rf.format(bioscore);
              }else{
                bioscorestr = "";
              }


              if(chemscorehash.get(uuid)!=null){
                chemscore = chemscorehash.get(uuid);
                chemscorestr = rf.format(chemscore);
              }else{
                chemscorestr = "";
              }



              if(matscorehash.get(uuid)!=null){
                matscore = matscorehash.get(uuid);
                matscorestr = rf.format(matscore);
              }else{
                matscorestr = "";
              }

               if(histscoreMap.get(uuid)!=null){
                histscore = histscoreMap.get(uuid);
                histscorestr = rf.format(histscore);
              }else{
                histscorestr = "";
              }


              if(crescorehash.get(uuid)!=null){
                crescore = crescorehash.get(uuid);
                crescorestr = rf.format(crescore);
              }else{
                crescorestr = "";
              }


              if(geoscoreMap.get(uuid)!=null){
                geoscore = geoscoreMap.get(uuid);
                geoscorestr = rf.format(geoscore);
              }else{
                geoscorestr = "";
              }


              if(bsscoreMap.get(uuid)!=null){
                bsscore = bsscoreMap.get(uuid);
                bsscorestr = rf.format(bsscore);
              }else{
                bsscorestr = "";
              }


              if(agriscorehash.get(uuid)!=null){
                agriscore = agriscorehash.get(uuid);
                agriscorestr = rf.format(agriscore);
              }else{
                agriscorestr = "";
              }

              if(hscscoreMap.get(uuid)!=null){
                hscscore = hscscoreMap.get(uuid);
                hscscorestr = rf.format(hscscore);
              }else{
                hscscorestr = "";
              }

              if(comscoreMap.get(uuid)!=null){
                comscore = comscoreMap.get(uuid);
                comscorestr = rf.format(comscore);
              }else{
                comscorestr = "";
              }   







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
    

      
    
       

      if(Double.parseDouble(totalz)==number){
    	  myTable.addCell(" "+(count-counttwo++)); 
        }
        else{
         counttwo=1;
         myTable.addCell(" "+count); 
        }
      
      myTable.addCell(studeadmno);
      myTable.addCell(studename);  
      myTable.addCell(engscorestr);  
      myTable.addCell(kswscorestr);  
      myTable.addCell(matscorestr);  
      myTable.addCell(physcorestr);  
      myTable.addCell(chemscorestr);  
      myTable.addCell(bioscorestr);  
      myTable.addCell(histscorestr);  
      myTable.addCell(crescorestr);  
      myTable.addCell(geoscorestr);  
      myTable.addCell(bsscorestr);  
      myTable.addCell(agriscorestr);
      myTable.addCell(hscscorestr);  
      myTable.addCell(comscorestr);
      myTable.addCell(df.format(Double.parseDouble(totalz)));  
      myTable.addCell(df.format(mean));  
      myTable.addCell(grade);  
     
    
      count++;
      number=Double.parseDouble(totalz);
       
     
       

   }
   
      
      

 }

	   
                
      
   
   
   document.add(myTable);  
 
   
   

 
   // step 5
   document.close();
}
catch(DocumentException e) {
   logger.error("DocumentException while writing into the document");
   logger.error(ExceptionUtils.getStackTrace(e));
}

}

private Element createImage(String realPath) {
 Image imgLogo = null;

 try {
     imgLogo = Image.getInstance(realPath);
     imgLogo.scaleToFit(100, 100);
     imgLogo.setAlignment(Element.ALIGN_RIGHT);

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
