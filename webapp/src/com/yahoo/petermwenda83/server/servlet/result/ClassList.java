/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.result;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.exam.StudentExam;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.ClassTeacher;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.persistence.exam.StudentExamDAO;
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
	    private static StudentExamDAO studentExamDAO;
	    private static ClassTeacherDAO classTeacherDAO;
	    private static StudentDAO studentDAO;
	    
	    String classroomuuid = "";
	    String schoolusername = "";
	    String stffID = "";
	    
	    HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	    HashMap<String, String> studNameHash = new HashMap<String, String>();

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
       studentExamDAO = StudentExamDAO.getInstance();
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
	   
	   List<StudentExam> sutexamList = new ArrayList<StudentExam>(); 
	   sutexamList = studentExamDAO.getStudentExamList(school.getUuid(),classroomuuid); 
	   
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

           populatePDFDocument(statistics, school,classroomuuid,perfomanceList,sutexamList, context.getRealPath("/images/fastech.png"));

       } catch (DocumentException e) {
           logger.error("DocumentException while writing into the document");
           logger.error(ExceptionUtils.getStackTrace(e));
       }
	   
	   
	   
	   
	   
        }



   private void populatePDFDocument(SessionStatistics statistics, SchoolAccount school, String classroomuuid, 
		  List<Perfomance> perfomanceList, List<StudentExam> sutexamList,String realPath) {
	   SimpleDateFormat formatter;
       String formattedDate;
       Date date = new Date();
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
      
       PdfPCell freHeader = new PdfPCell(new Paragraph("FRE",boldFont));
       freHeader.setBackgroundColor(baseColor);
       freHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
       
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
       
       PdfPCell comHeader = new PdfPCell(new Paragraph("COM",boldFont));
       comHeader.setBackgroundColor(baseColor);
       comHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
       
       PdfPCell hsHeader = new PdfPCell(new Paragraph("H/S",boldFont));
       hsHeader.setBackgroundColor(baseColor);
       hsHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
       
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
        myTable.addCell(freHeader);
        myTable.addCell(matHeader);
        myTable.addCell(phyHeader);
        myTable.addCell(cheHeader);
        myTable.addCell(bioHeader);
        myTable.addCell(hisHeader);
        myTable.addCell(creHeader);
        myTable.addCell(geoHeader);
        myTable.addCell(bsHeader);
        myTable.addCell(agrHeader);
        myTable.addCell(comHeader);
        myTable.addCell(hsHeader);
        myTable.addCell(meanHeader);
        myTable.addCell(gradeHeader);
        
      
       //Rectangle rec = new Rectangle(523,770); 
       myTable.setWidthPercentage(100); 
       
       
       myTable.setWidths(new int[]{10,41,50,15,15,15,15,15,15,15,15,15,15,15,15,16,15,20,15});   
       
     //  leadersTable.setLockedWidth(true); 
       
       myTable.setHorizontalAlignment(Element.ALIGN_LEFT);
       
       String studeadmno = "";
       String studename = "";
       int count = 1;
       
       if(sutexamList !=null){
       for(StudentExam student : sutexamList){ 
                     studeadmno = studentAdmNoHash.get(student.getStudentUuid());
                     studename = studNameHash.get(student.getStudentUuid());   
                     
                     
 		            
 		            
 		           myTable.addCell(" "+count++); 
 		           myTable.addCell(studeadmno);
 		           myTable.addCell(studename);  
 		           myTable.addCell("87");  
 		           myTable.addCell("76");  
 		           myTable.addCell("66");  
		           myTable.addCell("65");  
		           myTable.addCell("88");  
 		           myTable.addCell("65");  
 		           myTable.addCell("34");  
		           myTable.addCell("45");  
		           myTable.addCell("78");  
 		           myTable.addCell("55");  
 		           myTable.addCell("56");  
		           myTable.addCell("94");  
		           myTable.addCell("76");  
 		           myTable.addCell("45");  
 		           myTable.addCell("43.68");  
		           myTable.addCell("B"); 
		          
 		           
 		            
 		          
                     
                    
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
