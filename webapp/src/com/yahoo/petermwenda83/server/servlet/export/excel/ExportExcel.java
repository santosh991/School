package com.yahoo.petermwenda83.server.servlet.export.excel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.guardian.StudentParent;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.guardian.ParentsDAO;
import com.yahoo.petermwenda83.persistence.student.HouseDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class ExportExcel extends HttpServlet{

    private final String SPREADSHEET_NAME = "Export.xlsx";
    private static final long serialVersionUID = 3896751907947782599L;
    private static int pageno;

    private Cache schoolaccountCache;

    private static StudentDAO studentDAO;
    private static HouseDAO houseDAO;
    private static ParentsDAO parentsDAO;
    private static RoomDAO roomDAO;
    
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timezoneFormatter;
    
    private String classroomuuid = "";
    String schoolusername = "";
    private Logger logger;	
    private ServletOutputStream out;
    
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);       
        
        dateFormatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        timezoneFormatter = new SimpleDateFormat("z");
	    logger = Logger.getLogger(this.getClass());
	    CacheManager mgr = CacheManager.getInstance();
	    schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
	    studentDAO = StudentDAO.getInstance();
	    roomDAO = RoomDAO.getInstance();
	    
    }

    /**    
     *
     * @param request
     * @param response
     * @throws ServletException, IOException
     * @throws java.io.IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

        out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Cache-Control", "cache, must-revalidate");
        response.setHeader("Pragma", "public");

        HttpSession session = request.getSession(false); 
        
        classroomuuid = StringUtils.trimToEmpty(request.getParameter("classroomuuid"));
       
        SchoolAccount school = new SchoolAccount();
	
		   if(session !=null){
		   schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
		  
		      }
		   net.sf.ehcache.Element element;
		   
		   element = schoolaccountCache.get(schoolusername);
		   if(element !=null){
		   school = (SchoolAccount) element.getObjectValue();
	 
		   }
               
		    List<Student> studentList = new ArrayList<>();
	        studentList = studentDAO.getAllStudents(school.getUuid(), classroomuuid);
	        
	        
            String fileName = new StringBuffer(school.getUsername()).append(" ")
                .append(StringUtils.trimToEmpty(school.getUsername()))
                .append(" ")
                .append(SPREADSHEET_NAME)
                .toString();

        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");     
        createExcelSheets(studentList);
       }
    
    
    
    /**
     * Returns MS Excel file of the data specified for exporting.
     * @param List<IncomingLog>
     * Method create excelSheets and sends them
     ****/    
    public void createExcelSheets(List<Student>studentList) throws IOException{    	
    	 List<StudentParent> parentList;
    	 //String cont = null;
    	
        XSSFWorkbook xf = new XSSFWorkbook();
        XSSFCreationHelper ch =xf.getCreationHelper();
      
        XSSFSheet s =xf.createSheet();
        //create the first row
        XSSFRow r1 = s.createRow(0);
  	            XSSFCell c11 = r1.createCell(0);
  	                  c11.setCellValue(ch.createRichTextString("*")); 
	            XSSFCell c12 = r1.createCell(1);
	                  c12.setCellValue(ch.createRichTextString("Admission Number"));
	            XSSFCell c13 = r1.createCell(2);
	                  c13.setCellValue(ch.createRichTextString("First Name"));
	            XSSFCell c14 = r1.createCell(3);
	                  c14.setCellValue(ch.createRichTextString("Last Name"));
	            XSSFCell c15 = r1.createCell(4);
	                  c15.setCellValue(ch.createRichTextString("Surname"));
	            XSSFCell c16 = r1.createCell(5);
	                  c16.setCellValue(ch.createRichTextString("Gender"));
	            XSSFCell c17 = r1.createCell(6);
	                  c17.setCellValue(ch.createRichTextString("Class"));
        
        
        int i=1;
        //create other rows
          for(Student stu :studentList){ 
        	 parentList = parentsDAO.getParent(stu.getUuid());
        	          	  
        	  XSSFRow r = s.createRow(i);
        	  //row number
        	  XSSFCell c1 = r.createCell(0);
        	      c1.setCellValue(i+pageno);
        	      
        	    //get message  
        	  XSSFCell c2 = r.createCell(1);        	
        	      c2.setCellValue(ch.createRichTextString(stu.getAdmno()));
        	      
        	 //get phone numbers
        	      XSSFCell c3 = r.createCell(2);
        	      if(parentList.size()>0){
        	         for(StudentParent p : parentList){ 
        		     //Contact contacts = ctDAO.getContact(phone.getContactUuid());      	                     
        			 // c3.setCellValue(ch.createRichTextString(contacts.getName())); 
        	         }   
        			       }
        		  //else{ 
        			  //c3.setCellValue(ch.createRichTextString(stu.getFirstname()));     }        		   	     
        	     
        	      
        	   //get destination   
        	  XSSFCell c4 = r.createCell(3);
        	      c4.setCellValue(ch.createRichTextString(stu.getLastname()));
        	      
        	  //get network name    
        	   XSSFCell c5 = r.createCell(4);
        	      c5.setCellValue(ch.createRichTextString(stu.getSurname())); 
        	   
        	      //get date 
        	  XSSFCell c6 = r.createCell(5);         	  
        	     c6.setCellValue(ch.createRichTextString(stu.getGender()));
        	              	      
        	      //get message id
        	  XSSFCell c7 = r.createCell(6); 
        	       //c7.setCellValue(ch.createRichTextString(log.getUuid())); 
        	  i++;
        	         	  
          }
          xf.write(out);
          out.flush();          
          out.close();
    }
    
    
    
   
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException, IOException
     * @throws java.io.IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
   
}
