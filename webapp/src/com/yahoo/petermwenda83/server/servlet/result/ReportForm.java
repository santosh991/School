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
import java.util.Date;
import java.util.List;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.ClassTeacher;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author peter
 *
 */
public class ReportForm extends HttpServlet{
	
	    private static PerfomanceDAO perfomanceDAO;
	    private static StudentDAO studentDAO;
	    private static ClassTeacherDAO classTeacherDAO;
	    private Logger logger;
	    private Cache schoolaccountCache, statisticsCache;
	    
	    private Document document;
	    private PdfWriter writer;
	    
	    private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	    private Font normalText = new Font(Font.FontFamily.COURIER, 12);
	    
	    
	    final String PDF_TITLE = "pdf title here";
	    final String PDF_SUBTITLE = "Report Generated For: ";
	    final String PDF_BOTTOM_TEXT = "pdf bottom text here";

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
	       studentDAO = StudentDAO.getInstance();
	       classTeacherDAO = ClassTeacherDAO.getInstance();
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
		  
		  String studentuuid = StringUtils.trimToEmpty(request.getParameter("studentuuid"));
		  System.out.println(studentuuid);
		   
		   SchoolAccount school = new SchoolAccount();
		   HttpSession session = request.getSession(false);
		   String schoolusername = "";
		   String stffID = "";
		   String classroomuuid = "";
		   
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
		   List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
		   perfomanceList = perfomanceDAO.getPerformance(school.getUuid(), classroomuuid,studentuuid);
		   
		   
		   document = new Document(PageSize.A4, 36, 36, 54, 54);
		   
		   try {
	           writer = PdfWriter.getInstance(document, response.getOutputStream());
	           

	           PdfUtil event = new PdfUtil();
	           writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
	           writer.setPageEvent(event);

	           populatePDFDocument(school,classroomuuid,perfomanceList, context.getRealPath("/images/fastech.png"));

	       } catch (DocumentException e) {
	           logger.error("DocumentException while writing into the document");
	           logger.error(ExceptionUtils.getStackTrace(e));
	       }
		   
		   
		   
		
		   
	   }


	private void populatePDFDocument(SchoolAccount school, String classroomuuid, List<Perfomance> perfomanceList,
			String realPath) {
		
		   SimpleDateFormat formatter;
	       String formattedDate;
	       Date date = new Date();
	       
	       try{
	       document.open();
	       
	       Paragraph preface = new Paragraph();
	       preface.add(createImage(realPath));
	     
	       preface.add(new Paragraph(PDF_TITLE, bigFont));

	       formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm z");
	       formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
	       formattedDate = formatter.format(date);
	       
	       DecimalFormat df = new DecimalFormat("0.00"); 
	       df.setRoundingMode(RoundingMode.DOWN);

	       // Will create: Report generated by: _name, _date
	       preface.add(new Paragraph(PDF_SUBTITLE + school.getSchoolName(), smallBold));

	       preface.add(new Paragraph(formattedDate, smallBold));

	       addEmptyLine(preface, 1);

	      

	      // preface.add(new Paragraph(PDF_BOTTOM_TEXT));

	       preface.setAlignment(Element.ALIGN_RIGHT);

	       document.add(preface);
	       
	       for(Perfomance p : perfomanceList){
	    	   System.out.println(p);
	    	   
	       }
	       document.newPage();
	       
	       
	       
	       
		   
		   
		   document.close();
	       }catch(DocumentException e) {
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
	   
	   
}
