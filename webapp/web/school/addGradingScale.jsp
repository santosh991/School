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
    
      
      int sessiontime = SessionConstants.SESSION_TIMEOUT;
      //out.println(sessiontime);

     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);

    Calendar calendar = Calendar.getInstance();
    final int DAYS_IN_MONTH = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
    final int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);
    final int MONTH = calendar.get(Calendar.MONTH) + 1;
    final int YEAR = calendar.get(Calendar.YEAR)-1;
    final int YEAR_COUNT = YEAR + 2;
    
 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-header well" data-original-title>
         <p>    Welcome to <%=schoolname%> :Exam Grading scale Panel: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> </p>
        </div>
        <div class="box-content">

        <%                 
                        HashMap<String, String> paramHash = (HashMap<String, String>) session.getAttribute(SessionConstants.GRADE_PARAM);

                        if (paramHash == null) {
                             paramHash = new HashMap<String, String>();
                            }

                                String updateErrStr = "";
                                String updatesuccessStr = "";
                                session = request.getSession(false);
                                     updateErrStr = (String) session.getAttribute(SessionConstants.GRADE_ADD_ERROR);
                                     updatesuccessStr = (String) session.getAttribute(SessionConstants.GRADE_ADD_SUCCESS); 

                                if(session != null) {
                                    updateErrStr = (String) session.getAttribute(SessionConstants.GRADE_ADD_ERROR);
                                    updatesuccessStr = (String) session.getAttribute(SessionConstants.GRADE_ADD_SUCCESS);
                                }                        

                                if (StringUtils.isNotEmpty(updateErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + updateErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.GRADE_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(updatesuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + updatesuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.GRADE_ADD_SUCCESS,null);
                                  } 



        %>
            
   

   
        <form  class="form-horizontal"   action="addGradeScale" method="POST" >
                 <fieldset>
                    
                                     
                             <div class="control-group">
                                <label class="control-label" for="FatherName">A*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="A"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("A")) %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="FatherName">A-*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="A-"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Am")) %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="FatherName">B+*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="B+"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Bp")) %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="FatherName">B*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="B"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("B")) %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="FatherName">B-*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="B-"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Bm")) %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="FatherName">C+*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="C+"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Cp")) %>"  >
                                </div>
                             </div> 

                             <div class="control-group">
                                <label class="control-label" for="FatherName">C*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="C"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("C")) %>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="FatherName">C-*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="C-"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Cm")) %>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="FatherName">D+*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="D+"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Dp")) %>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="FatherName">D*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="D"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("D")) %>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="FatherName">D-*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="D-"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("Dm")) %>"  >
                                </div>
                             </div> 
                             <div class="control-group">
                                <label class="control-label" for="FatherName">E*:</label>
                                <div class="controls">
                                <input class="input-xlarge focused" id="receiver" type="text" name="E"
                                      value="<%= StringUtils.trimToEmpty(paramHash.get("E")) %>"  >
                                </div>
                             </div> 

                                    
                                    
                            <div class="form-actions">
                                  <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                  <button type="submit" class="btn btn-primary">Save</button>
                            </div> 

              </fieldset>
              </form>
       











    </div>

</div>


<jsp:include page="footer.jsp" />
