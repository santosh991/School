<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>



<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>


<%@page import="org.apache.commons.lang3.StringUtils"%>


<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%
     String accountuuid = "";

     if (session == null) {
       response.sendRedirect("../index.jsp");
      
    }

    String username = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
    if (StringUtils.isEmpty(username)) {
        response.sendRedirect("../index.jsp");
       
    }
     
    CacheManager mgr = CacheManager.getInstance();
    Cache accountsCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
    Cache statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_SCHOOL_ACCOUNT);
    SessionStatistics statistics = new SessionStatistics();
    

    SchoolAccount school = new SchoolAccount();
    Element element;
   

    int incount = 0;  // Generic counter

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    accountuuid = school.getUuid();
    String schoolname = school.getSchoolName();

    ExamConfigDAO examConfigDAO = ExamConfigDAO.getInstance();
    ExamConfig examConfig = examConfigDAO.getExamConfig(accountuuid);

   
    

    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
   
   
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     

 %>






<jsp:include page="header.jsp" />

<script>
	
	function hideDiv(){
		$('#motherDiv').hide();
	}

	function showDiv(){
    $('#motherDiv').show();

	 }

</script>  


<div class="row-fluid sortable">



               



    <div class="box span12">
        <div class="box-header well" data-original-title>
   <p>[<a href="schoolIndex.jsp">Back</a>]  <%=schoolname%> : Parent/Guardian Registration Panel : TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> </p>
        </div>
        <div class="box-content">
          
               <%
                    HashMap<String, String> paramHash = (HashMap<String, String>) session.getAttribute(SessionConstants.PARENT_PARAM);

                        if (paramHash == null) {
                             paramHash = new HashMap<String, String>();
                            }

                    HashMap<String, String> fatherMotherParamHash = (HashMap<String, String>) session.getAttribute(SessionConstants.FATHER_MOTHER_PARAM);

                        if (fatherMotherParamHash == null) {
                             fatherMotherParamHash = new HashMap<String, String>();
                            }
                             
                       

                                String findError = "";
                                String findsuccess = "";
                                String addError = "";
                                String addsuccess = "";
                                session = request.getSession(false);
                                findError = (String) session.getAttribute(SessionConstants.STUDENT_FIND_ERROR);
                                findsuccess = (String) session.getAttribute(SessionConstants.STUDENT_FIND_SUCCESS); 
                                addError = (String) session.getAttribute(SessionConstants.FATHER_MOTHER_ADD_ERROR);
                                addsuccess = (String) session.getAttribute(SessionConstants.FATHER_MOTHER_ADD_SUCCESS); 

                                                    

                                if (StringUtils.isNotEmpty(findError)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + findError);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(findsuccess)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + findsuccess);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, null);
                                  } 


                                if (StringUtils.isNotEmpty(addError)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addError);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.FATHER_MOTHER_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccess)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccess);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.FATHER_MOTHER_ADD_SUCCESS, null);
                                  } 

                           String admNumber =""; 
                           String firstname ="";
                           String lastname =""; 
                           String surname ="";  
                          if(StringUtils.isEmpty(paramHash.get("admNumber"))){
                           admNumber = " ";
                          }else{
                           admNumber = paramHash.get("admNumber"); 
                           }

                           if(StringUtils.isEmpty(paramHash.get("firstname"))){
                           firstname = " ";
                          }else{
                           firstname = paramHash.get("firstname"); 
                           }


                           if(StringUtils.isEmpty(paramHash.get("lastname"))){
                           lastname = " ";
                          }else{
                           lastname = paramHash.get("lastname"); 
                           }


                           if(StringUtils.isEmpty(paramHash.get("surname"))){
                           surname = " ";
                          }else{
                           surname = paramHash.get("surname"); 
                           }


                     %>
              
               <form  class="form-horizontal"   action="findStudentP" method="POST" >
               <fieldset>
		                     <div class="control-group">
		                         <label class="control-label" for="name">Admission Number:</label>
		                             <div class="controls">
		                               <input class="input-xlarge focused" id="receiver" type="text" name="AdmNo" 
		                                value=""  >
		                           </div>
		                    </div> <!--end of form find-->
		                 
		                    <!--submit form -->  
		                    <div class="form-actions">
		                          <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
		                          <button type="submit" name="Find" value="Find"   class="btn btn-primary">Find</button> 
		                    </div>

                </fieldset>
                </form>
 
               <form  class="form-horizontal"   action="" method="POST" >
               <fieldset>
			                    <div class="control-group">
			                        <label class="control-label" for="name">Admission Number:</label>
			                        <div class="controls">
			                        <input class="input-xlarge focused" id="receiver" type="text" name="admnumber" 
			                            value="<%=admNumber%>" readonly >
			                        </div>
			                    </div>  


			                     <div class="control-group">
			                        <label class="control-label" for="name">FirstName:</label>
			                        <div class="controls">
			                        <input class="input-xlarge focused" id="receiver" type="text" name="firstname" 
			                             value="<%=firstname%>" readonly >                                    
			                        </div>
			                    </div> 


			                    <div class="control-group">
			                        <label class="control-label" for="name">LastName:</label>
			                        <div class="controls">
			                         <input class="input-xlarge focused" id="receiver" type="text" name="lastname"
			                              value="<%=lastname%>" readonly >
			                        </div>
			                    </div> 


			                     <div class="control-group">
			                        <label class="control-label" for="name">SurName:</label>
			                        <div class="controls">
			                        <input class="input-xlarge focused" id="receiver" type="text" name="surname"
			                              value="<%=surname%>" readonly >
			                        </div>
			                    </div> 
                 </fieldset>
                 </form>


                <h3><i class="icon-edit"></i> Student Parent/Guardian Details:</h3>  
                <p>Fields marked with a * are compulsory. (If the student is an orphan,Guardian's details can be entered in the place of father or mother)</p>

                <form  class="form-horizontal"   action="addStudentParent" method="POST" >
                <fieldset>           
 

				                     <div class="control-group">
				                        <label class="control-label" for="FatherName">Father Name*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="FatherName"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("FatherName")) %>"  >
				                        </div>
				                    </div> 

				                    <div class="control-group">
				                        <label class="control-label" for="FatherPhone">Father Phone*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="FatherPhone"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("FatherPhone")) %>"  >
				                        </div>
				                    </div> 

				                     <div class="control-group">
				                        <label class="control-label" for="FatherOccupation">Father Occupation*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="FatherOccupation"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("FatherOccupation")) %>"  >
				                        </div>
				                    </div> 


				                    <div class="control-group">
				                        <label class="control-label" for="FatherID">Father ID No*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="FatherID"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("FatherID")) %>"  >
				                        </div>
				                    </div> 

				                    <div class="control-group">
				                        <label class="control-label" for="FatherEmail">Father Email*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="FatherEmail"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("FatherEmail")) %>"  >
				                        </div>
				                    </div> 


                      

				                    <div class="control-group">
				                        <label class="control-label" for="MotherName">Mother Name*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="MotherName"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("MotherName")) %>"  >
				                        </div>
				                    </div> 

				                     <div class="control-group">
				                        <label class="control-label" for="MotherPhone">Mother Phone*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="MotherPhone"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("MotherPhone")) %>"  >
				                        </div>
				                    </div> 

				                     <div class="control-group">
				                        <label class="control-label" for="MotherOccupation">Mother Occupation*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="MotherOccupation"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("MotherOccupation")) %>"  >
				                        </div>
				                    </div> 

				                     <div class="control-group">
				                        <label class="control-label" for="MotherEmail">Mother Email*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="MotherEmail"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("MotherEmail")) %>"  >
				                        </div>
				                    </div> 

				                     <div class="control-group">
				                        <label class="control-label" for="MotherID">Mother ID No*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="MotherID"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("MotherID")) %>"  >
				                        </div>
				                    </div> 

				        

				                     <div class="control-group">
				                        <label class="control-label" for="RelativeName">Relative Name*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="RelativeName"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("RelativeName")) %>"  >
				                        </div>
				                    </div> 

				                     <div class="control-group">
				                        <label class="control-label" for="RelativePhone">Relative Phone*:</label>
				                        <div class="controls">
				                        <input class="input-xlarge focused" id="receiver" type="text" name="RelativePhone"
				                              value="<%= StringUtils.trimToEmpty(fatherMotherParamHash.get("RelativePhone")) %>"  >
				                        </div>
				                    </div> 

				                    
				                    <div class="form-actions">
				                        <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
				                         <input type="hidden" name="studentUuid" value="<%= StringUtils.trimToEmpty(paramHash.get("studentuuid")) %>">
				                        <button type="submit" class="btn btn-primary">Register</button>
				                    </div> 

              </fieldset>
              </form>
       


    </div>
     </div>

</div>


<jsp:include page="footer.jsp" />
