
<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.persistence.money.StudentFeeDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.money.StudentFee"%>

<%@page import="com.yahoo.petermwenda83.persistence.money.TermFeeDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.money.TermFee"%>

<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.bean.money.StudentAmount"%>


<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>


<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%

   
  if (session == null) {
       response.sendRedirect("../index.jsp");
      
    }

    String username = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
    if (StringUtils.isEmpty(username)) {
        response.sendRedirect("../index.jsp");
       
    }
     
    CacheManager mgr = CacheManager.getInstance();
    Cache accountsCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
    

    SchoolAccount school = new SchoolAccount();
    Element element;
   

    int incount = 0;  // Generic counter
    String accountuuid = "";
    String schoolname = "";
   

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }
     
     if(school !=null){
      accountuuid = school.getUuid();
      schoolname = school.getSchoolName();
      }

    ExamConfigDAO examConfigDAO = ExamConfigDAO.getInstance();
    ExamConfig examConfig = new ExamConfig();
    if(examConfigDAO.getExamConfig(accountuuid) !=null){
        examConfig = examConfigDAO.getExamConfig(accountuuid);
       }
    

    TermFeeDAO termFeeDAO = TermFeeDAO.getInstance();

    TermFee termFee  = new TermFee();
    if(termFeeDAO.getTermFee(accountuuid,examConfig.getTerm()) !=null){
           termFee = termFeeDAO.getTermFee(accountuuid,examConfig.getTerm());
       }
   
    

    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
   
   
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
   

     
   
   
 %>






<jsp:include page="header.jsp" />



<div>
    <ul class="breadcrumb">
     <li> <b> <%=schoolname%> :FEE MANAGEMENT PANEL FOR: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear() %>  TERM FEE : <%=termFee.getTermAmount()%><b> </li> <br>

        <li>
            <a href="addFee.jsp">New Payment Info</a> <span class="divider">/</span>
        </li>

         <li>
            <a href="feeIndex.jsp">Back</a> <span class="divider">/</span>
        </li>

        <li>
            <a href="studentsFinaceReport" target="_blank">Print</a> <span class="divider">/</span>
        </li>

         
        
    </ul>
</div>



<div class="row-fluid sortable">




        <div class="box span12">
        <div class="box-content">

         <%
                    HashMap<String, StudentAmount> studentAmountHash = (HashMap<String, StudentAmount>) session.getAttribute(SessionConstants.STUENT_BALANCE_PARAM);
                    HashMap<String, Student> studentParamHash = (HashMap<String, Student>) session.getAttribute(SessionConstants.STUENT_PARAM_F);
                    HashMap<String, List<StudentFee> > feeParamHash = (HashMap<String, List<StudentFee> >) session.getAttribute(SessionConstants.FEE_PARAM);

                        if (studentAmountHash == null) {
                             studentAmountHash = new HashMap<String, StudentAmount>();
                            }

                            if (studentParamHash == null) {
                             studentParamHash = new HashMap<String, Student>();
                            }

                            if (feeParamHash == null) {
                             feeParamHash = new HashMap<String, List<StudentFee> >();
                            }


                            StudentAmount studentAmount = new StudentAmount();
                            studentAmount = studentAmountHash.get("studentamountObj");


                            Student student = new Student();
                            student = studentParamHash.get("studentObj");
                              

                            List<StudentFee> studentfeeList = new ArrayList<StudentFee>();
                            studentfeeList = feeParamHash.get("studentfeeList");
                            

                               
                         
                         
                     String fullname = "";

                     String formatedFirstname = "";
					           String formatedLastname = "";
					           String formatedSurname = "";

					           String firstNameLowecase  = "";
					           String lastNameLowecase  = "";
					           String surNameLowecase  = "";

					           String admNumber =""; 
                     String studentuuid =""; 	                          

					           if(student !=null){

					               admNumber = student.getAdmno();
                         studentuuid = student.getUuid();
                         firstNameLowecase = student.getFirstname().toLowerCase();
					               lastNameLowecase =student.getLastname().toLowerCase();
					               surNameLowecase = student.getSurname().toLowerCase();

					               formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
					               formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
					               formatedSurname = surNameLowecase.substring(0,1).toUpperCase()+surNameLowecase.substring(1); 

					               fullname = formatedFirstname +" "+formatedLastname+" "+formatedSurname;
 
					             }
					           
					            

                                String addError = "";
                                String addsuccess = "";
                                session = request.getSession(false);
                                addError = (String) session.getAttribute(SessionConstants.STUDENT_FEE_ADD_ERROR);
                                addsuccess = (String) session.getAttribute(SessionConstants.STUDENT_FEE_ADD_SUCCESS); 

                                                    


                                if (StringUtils.isNotEmpty(addError)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addError);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.STUDENT_FEE_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccess)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccess);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.STUDENT_FEE_ADD_SUCCESS, null);
                                  } 

                          

                         
                       
                           
                     %>












            <table class="table table-striped  ">

                <thead>
                    <tr >             
                        <th></th>
                        <th></th>
                        <th>Search</th>
                    </tr>
                </thead>   

                <tbody >

                              <form name="view" method="POST" action="findStudentFee"> 

                               <td width="8%" class="center">                              
		                          <p><b>Student Admission Number:</b><p>                                                    
                               </td> 

                                <td width="10%" class="center">                              
		                               <input class="input-xlarge focused" id="receiver" type="text" name="AdmNo" 
		                                value=""  >                                                    
                               </td> 

                               <td width="10%" class="center">                                
		                        <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                <input class="btn btn-success" type="submit" name="view" id="submit" value="Find" />                                                         
                               </td> 
                               </form> 


                </tbody>                  
            </table>  














             <table class="table ">
                <thead>
                    <tr >             
                        <th>Student AdmNo</th>
                        <th>Student name</th>
                    </tr>
                </thead>   
                <tbody >
                    <%  
                               out.println("<tr>"); 
                               out.println("<td width=\"10%\" class=\"center\">" + admNumber + "</td>");  
                               out.println("<td width=\"10%\" class=\"center\">" + fullname + "</td>");    
                             
                    %> 

                </tbody>                  
            </table>  

       




             <table class="table table-striped table-bordered bootstrap-datatable ">
                <thead>
                    <tr >             
                        <th>*</th>
                        <th>Ammount Paid</th>
                        <th>Receipt Number</th>
                        <th>Date Received</th>
                        <th>Update</th>
                    </tr>
                </thead>   
                <tbody >
                    <%     
                              double total = 0;
                              double totalpaid = 0;
                              Locale locale = new Locale("en","KE"); 
					   		  NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

                               int count = 1;
		                     if(studentfeeList !=null){
		                   for(StudentFee sfee : studentfeeList){
                               total = sfee.getAmountPaid();
                               totalpaid = totalpaid + total;
                               out.println("<tr>"); 
                               out.println("<td width=\"3%\" >" + count + "</td>"); 
                               out.println("<td class=\"center\">" +  nf.format(total) + "</td>"); 
                               out.println("<td class=\"center\">" +  sfee.getTransactionID() + "</td>");
                               out.println("<td class=\"center\">" +  sfee.getDatePaid() + "</td>"); 
                               %>
                               <td class="center">
                                <form name="view" method="POST" action="updatefee.jsp"> 
                                <input type="hidden" name="transactionid" value="<%=sfee.getTransactionID()%>">
                                <input type="hidden" name="amountpaid" value="<%=sfee.getAmountPaid()%>">
                                <input type="hidden" name="studentuuid" value="<%=sfee.getStudentUuid()%>">
                                <input type="hidden" name="fullname" value="<%=fullname%>">
                                 <input type="hidden" name="admNumber" value="<%=admNumber%>">
                                <input class="btn btn-success" type="submit" name="view" id="submit" value="Deduct" /> 
                                </form>                          
                               </td>    



          
                                <%
                               count++;
                            
		                       }		                       
                           }                   
                  
                             %> 

                               

                </tbody>                                 
            </table>  
 


            
            <table class="table  ">
                <thead>
                    <tr >             
                        <th>Totals</th>
                    </tr>
                </thead>   
                <tbody >
                    <%  
                              
                               out.println("<tr>"); 
                               out.println("<td class=\"center\">" +  nf.format(totalpaid) + "</td>"); 
                             
                    %> 

                </tbody>


                                   
            </table>  




            <table class="table  ">
                <thead>
                    <tr >             
                        <th>Total paid</th>
                        <th>Balance</th>
                    </tr>
                </thead>   
                <tbody >
                    <%  
                               double mybalance = 0.0;   

                               if(studentAmount !=null){
                                    mybalance =  studentAmount.getAmount();
                                  }

                               double termfee = termFee.getTermAmount();
					                     double balance =  termfee - mybalance;
                               out.println("<tr>"); 
                               out.println("<td width=\"10%\" class=\"center\">" + nf.format(mybalance) + "</td>");  
                               out.println("<td width=\"10%\" class=\"center\">" + nf.format(balance) + "</td>");    
                             
                    %> 

                </tbody>


                                   
            </table>  
               

                                      
         <%
            feeParamHash.clear();
         %>
            
       


    </div>

</div>



<jsp:include page="footer.jsp" />
