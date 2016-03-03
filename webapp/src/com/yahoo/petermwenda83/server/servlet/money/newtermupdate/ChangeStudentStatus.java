/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.money.newtermupdate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author peter
 *
 */
public class ChangeStudentStatus extends HttpServlet{
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -6488088979845943829L;
	final String COMMITT_SUCCESS = "Status changed Successfully";
	final String COMMITT_ERROR = "Status change failed";
	
	public final static String STATUS_NOT_DEDUCTED = "NOTDEDUCTED";
	public final static String STATUS_DEDUCTED = "DEDUCTED";
	private Cache schoolaccountCache;
	
	private static StudentAmountDAO studentAmountDAO;
	private static StudentDAO studentDAO;

	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentAmountDAO = StudentAmountDAO.getInstance();
       CacheManager mgr = CacheManager.getInstance();
       schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
       studentDAO = StudentDAO.getInstance();
    
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       response.setCharacterEncoding("UTF-8");
       response.setContentType("application/json"); 
       
       PrintWriter out = response.getWriter();
       
       SchoolAccount school = new SchoolAccount();
       String  schoolusername = "";
       if(session !=null){
    	 schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
    	 
    	      }
    	   net.sf.ehcache.Element element;
    	   
    	   element = schoolaccountCache.get(schoolusername);
    	   if(element !=null){
    	   school = (SchoolAccount) element.getObjectValue();
    	      }
       
    	  
    	   List<Student> studentList = new ArrayList<>();
    	   studentList = studentDAO.getAllStudentList(school.getUuid());
    			   
    			  
    	  
    	   String json = "";
    	   int count = 0;
    	   if(studentList !=null){
    		   for(Student student : studentList){
    			   
    			   if(studentAmountDAO.changeStatus(STATUS_NOT_DEDUCTED, school.getUuid(), student.getUuid())){
    				   json = new Gson().toJson(" Student(s) Updated ");
    				   
    			   }else{
    				   json = new Gson().toJson(" SOMETHING WENT WRONG , START THE PROCESS ONCE MORE UNTILL EVERYTHINH IS FINE ");
    			   }
    			
    			  count ++;
    			 
    		   }
    		   out.println( json + " Total Number of Students = "+count);
    	   }
    	   
    	   out.flush();
   		   out.close();	
           //response.sendRedirect("gson.jsp");  
           return; 
       
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