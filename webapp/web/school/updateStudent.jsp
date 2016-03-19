<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>


<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page import="java.util.Calendar" %>
<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>

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
    
    
    
 %>






<jsp:include page="header.jsp" />


<div>
    <ul class="breadcrumb">
     <li> <b> <%=schoolname%> :STUDENT MANAGEMENT PANEL(UPDATE STUDENT INFORMATION): TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> <b> </li> <br>


        <li>
            <a href="studentIndex.jsp">Back</a> <span class="divider">/</span>
        </li>

        
    </ul>
</div>


<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-content">

       
            
   

   
        <form  class="form-horizontal"   action="updateStudentBasic" method="POST" >
                 <fieldset>
                    
                                     
                             <div class="control-group">
                                <label class="control-label" for="AdmissionNumber">Admission Number:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="admNo"
                                      value="<%=request.getParameter("admNo")%>"  readonly>
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="firstname">Firstname:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="firstname"
                                      value="<%=request.getParameter("firstname") %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="lastname">Lastname:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="lastname"
                                      value="<%=request.getParameter("lastname") %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="surname">Surname:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="surname"
                                      value="<%=request.getParameter("surname")%>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="gender">Gender:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="gender"
                                      value="<%=request.getParameter("gender")%>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="dob">Date of Birth:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="dob"
                                      value="<%=request.getParameter("dob")%>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="BcertNo">Birth Cert No:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="BcertNo"
                                      value="<%=request.getParameter("BcertNo")%>"  >
                                </div>
                             </div> 
                            
                             <div class="control-group">
                                <label class="control-label" for="County">County:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="county"
                                      value="<%=request.getParameter("county")%>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="Primary">Primary School:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="primary"
                                      value="<%=request.getParameter("primary")%>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="kcpeindex">KCPE Index:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="kcpeindex"
                                      value="<%=request.getParameter("kcpeindex")%>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="kcpemark">KCPE Marks:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="kcpemark"
                                      value="<%=request.getParameter("kcpemark")%>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="kcpeyear">KCPE Year:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="kcpeyear"
                                      value="<%=request.getParameter("kcpeyear")%>"  >
                                </div>
                             </div> 

                                    
                                    
                            <div class="form-actions">
                                <input type="hidden" name="studentUuid" value="<%=request.getParameter("studentUuid")%>">
                                <input type="hidden" name="schoolUuid" value="<%=request.getParameter("schoolUuid")%>">
                                  <button type="submit" class="btn btn-primary">Update</button>
                            </div> 

              </fieldset>
              </form>
       











    </div>

</div>


<jsp:include page="footer.jsp" />
