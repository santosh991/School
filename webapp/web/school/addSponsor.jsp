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
                    HashMap<String, String> paramHash = (HashMap<String, String>) session.getAttribute(SessionConstants.STAFF_ADD_PARAM);

                        if (paramHash == null) {
                             paramHash = new HashMap<String, String>();
                            }
                             

                                String addErrStr = "";
                                String addsuccessStr = "";
                                session = request.getSession(false);
                                     addErrStr = (String) session.getAttribute(SessionConstants.STAFF_ADD_ERROR);
                                     addsuccessStr = (String) session.getAttribute(SessionConstants.STAFF_ADD_SUCCESS); 

                                if(session != null) {
                                    addErrStr = (String) session.getAttribute(SessionConstants.STAFF_ADD_ERROR);
                                    addsuccessStr = (String) session.getAttribute(SessionConstants.STAFF_ADD_SUCCESS);
                                }                        

                                if (StringUtils.isNotEmpty(addErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.STAFF_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.STAFF_ADD_SUCCESS, null);
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
                                      value="" readonly >

                                  </div>
                              </div>  


                               <div class="control-group">
                                  <label class="control-label" for="name">FirstName:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="firstname" 
                                       value="" readonly >                                    
                                  </div>
                              </div> 


                              <div class="control-group">
                                  <label class="control-label" for="name">LastName:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="lastname"
                                        value="" readonly >
                                  </div>
                              </div> 

                               <div class="control-group">
                                  <label class="control-label" for="name">SurName:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="surname"
                                        value=""readonly >
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
                                        value="<%= StringUtils.trimToEmpty(paramHash.get("SponsorName")) %>"  >
                                  </div>
                              </div> 

                              <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Phone*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorPhone"
                                        value="<%= StringUtils.trimToEmpty(paramHash.get("SponsorPhone")) %>"  >
                                  </div>
                              </div> 

                               <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Occupation*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorOccupation"
                                        value="<%= StringUtils.trimToEmpty(paramHash.get("SponsorOccupation")) %>"  >
                                  </div>
                              </div> 


                               <div class="control-group">
                                  <label class="control-label" for="name">Sponsor Country*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorCountry"
                                        value="<%= StringUtils.trimToEmpty(paramHash.get("SponsorCountry")) %>"  >
                                  </div>
                              </div> 

                               <div class="control-group">
                                  <label class="control-label" for="name">Sponsor County*:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="SponsorCounty"
                                        value="<%= StringUtils.trimToEmpty(paramHash.get("SponsorCounty")) %>"  >
                                  </div>
                              </div> 


                             
                              
                              
                              <div class="form-actions">
                                  <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                   <input type="hidden" name="systemuser" value="<%=staffUsername%>">
                                  <button type="submit" class="btn btn-primary">Register</button>
                              </div> 

              </fieldset>
              </form>
       


    </div>
     </div>

</div>


<jsp:include page="footer.jsp" />
