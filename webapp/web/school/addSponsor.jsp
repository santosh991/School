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




<div class="row-fluid sortable">



               



    <div class="box span12">
        <div class="box-header well" data-original-title>
   <p>[<a href="schoolIndex.jsp">Back</a>]  <%=schoolname%> :Student Registration Panel: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> </p>
        </div>
        <div class="box-content">
          
               <%
                    HashMap<String, String> paramHash = (HashMap<String, String>) session.getAttribute(SessionConstants.SPONSOR_PARAM);

                        if (paramHash == null) {
                             paramHash = new HashMap<String, String>();
                            }

                      HashMap<String, String> sponsorParamHash = (HashMap<String, String>) session.getAttribute(SessionConstants.STUDENT_SPONSOR_PARAM);

                        if (sponsorParamHash == null) {
                             sponsorParamHash = new HashMap<String, String>();
                            }
                             
                             

                                String addErrStr = "";
                                String addsuccessStr = "";
                                String addError = "";
                                String addsuccess = "";
                                session = request.getSession(false);
                                     addErrStr = (String) session.getAttribute(SessionConstants.STUDENT_FIND_ERROR);
                                     addsuccessStr = (String) session.getAttribute(SessionConstants.STUDENT_FIND_SUCCESS); 
                                     addError = (String) session.getAttribute(SessionConstants.SPONSOR_ADD_ERROR);
                                     addsuccess = (String) session.getAttribute(SessionConstants.SPONSOR_ADD_SUCCESS); 
                    

                                if (StringUtils.isNotEmpty(addErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, null);
                                  } 
                                  if (StringUtils.isNotEmpty(addError)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addError);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.SPONSOR_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccess)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccess);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.SPONSOR_ADD_SUCCESS, null);
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



               
              <form  class="form-horizontal"   action="findStudentS" method="POST" >
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
                                        value="<%=surname%>"readonly >
                                  </div>
                              </div> 
              </fieldset>
              </form>      

                               
                                <h3><i class="icon-edit"></i> Student Sponsor Details:</h3> 
                                <p>Fields marked with a * are compulsory.</p>

                  <form  class="form-horizontal"   action="addStudentSponsor" method="POST" >
                  <fieldset>
                              
                              <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Name*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorName"
                                        value="<%= StringUtils.trimToEmpty(sponsorParamHash.get("SponsorName")) %>"  >
                                  </div>
                              </div> 

                              <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Phone*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorPhone"
                                        value="<%= StringUtils.trimToEmpty(sponsorParamHash.get("SponsorPhone")) %>"  >
                                  </div>
                              </div> 

                               <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Occupation*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorOccupation"
                                        value="<%= StringUtils.trimToEmpty(sponsorParamHash.get("SponsorOccupation")) %>"  >
                                  </div>
                              </div> 


                               <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Country*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorCountry"
                                        value="<%= StringUtils.trimToEmpty(sponsorParamHash.get("SponsorCountry")) %>"  >
                                  </div>
                              </div> 

                               <div class="control-group">
                                  <label class="control-label" for="name">Sponsor County*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorCounty"
                                        value="<%= StringUtils.trimToEmpty(sponsorParamHash.get("SponsorCounty")) %>"  >
                                  </div>
                              </div> 


                             
                              
                              
                              <div class="form-actions">
                                   <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                   <input type="hidden" name="systemuser" value="<%=staffUsername%>">
                                   <input type="hidden" name="studentUuid" value="<%=StringUtils.trimToEmpty(paramHash.get("studentuuid"))%>">
                                  <button type="submit" class="btn btn-primary">Register</button>
                              </div> 

              </fieldset>
              </form>
       


    </div>
     </div>

</div>


<jsp:include page="footer.jsp" />
