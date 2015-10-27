/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.school.student;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import net.sf.ehcache.CacheManager;

/**
 * @author peter
 *
 */
public class AddStudent extends HttpServlet{
	
	final String ERROR_NO_STUDENT_FIRTSTNAME = "Please provide Student First Name.";

	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       CacheManager mgr = CacheManager.getInstance();
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String studentfirstname = StringUtils.trimToEmpty(request.getParameter("studentfirstname"));
       String studentsurname = StringUtils.trimToEmpty(request.getParameter("studentsurname"));
       String studentlasname = StringUtils.trimToEmpty(request.getParameter("studentlasname"));
       String studentadmno = StringUtils.trimToEmpty(request.getParameter("studentadmno"));
       String studentgender = StringUtils.trimToEmpty(request.getParameter("studentgender"));
       String studentdob = StringUtils.trimToEmpty(request.getParameter("studentdob"));
       String bcertno = StringUtils.trimToEmpty(request.getParameter("bcertno"));
       String studenthouse = StringUtils.trimToEmpty(request.getParameter("studenthouse"));
       String studentcounty = StringUtils.trimToEmpty(request.getParameter("studentcounty"));
       String studentlocation = StringUtils.trimToEmpty(request.getParameter("studentlocation"));
       String studentsubLocation = StringUtils.trimToEmpty(request.getParameter("studentsubLocation"));
       
       String prischoolname = StringUtils.trimToEmpty(request.getParameter("prischoolname"));
       String kcpeindex = StringUtils.trimToEmpty(request.getParameter("kcpeindex"));
       String kcpeyear = StringUtils.trimToEmpty(request.getParameter("kcpeyear"));
       String kcpemark = StringUtils.trimToEmpty(request.getParameter("kcpemark"));
       
       String fathername = StringUtils.trimToEmpty(request.getParameter("fathername"));
       String fatherphone = StringUtils.trimToEmpty(request.getParameter("fatherphone"));
       String fatheroccupation = StringUtils.trimToEmpty(request.getParameter("fatheroccupation"));
       String fatherid = StringUtils.trimToEmpty(request.getParameter("fatherid"));
       String fatheremail = StringUtils.trimToEmpty(request.getParameter("fatheremail"));
       
       String mothername = StringUtils.trimToEmpty(request.getParameter("mothername"));
       String motherphone = StringUtils.trimToEmpty(request.getParameter("motherphone"));
       String motheroccupation = StringUtils.trimToEmpty(request.getParameter("motheroccupation"));
       String motherid = StringUtils.trimToEmpty(request.getParameter("motherid"));
       String motheremail = StringUtils.trimToEmpty(request.getParameter("motheremail"));
       
       String relativename = StringUtils.trimToEmpty(request.getParameter("relativename"));
       String relavivephone = StringUtils.trimToEmpty(request.getParameter("relavivephone"));
       String relaviveid = StringUtils.trimToEmpty(request.getParameter("relaviveid"));
       
       String sponsorname = StringUtils.trimToEmpty(request.getParameter("sponsorname"));
       String sponsorphone = StringUtils.trimToEmpty(request.getParameter("sponsorphone"));
       String sponsorid = StringUtils.trimToEmpty(request.getParameter("sponsorid"));
       String sponsoroccupation = StringUtils.trimToEmpty(request.getParameter("sponsoroccupation"));
       String sponsorcounty = StringUtils.trimToEmpty(request.getParameter("sponsorcounty"));
       String sponsorcountry = StringUtils.trimToEmpty(request.getParameter("sponsorcountry"));
       
        Map<String, String> paramHash = new HashMap<>();    	
     	paramHash.put("studentfirstname", studentfirstname);
       
       
       System.out.println(studentfirstname);
       System.out.println(studentsurname);
       System.out.println(studentlasname);
       System.out.println(studentadmno);
       
       
       response.sendRedirect("school/home.jsp");  
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
   }
   
      
   

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }

}
