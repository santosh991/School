<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Collectors"%>

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
    
 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-header well" data-original-title>
         <p>Welcome to <%=schoolname%> :Exam Configuration Panel: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> </p>
        </div>
        <div class="box-content">

        <%

                                String updateErrStr = "";
                                String updatesuccessStr = "";
                                session = request.getSession(false);
                                     updateErrStr = (String) session.getAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR);
                                     updatesuccessStr = (String) session.getAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS); 

                                if(session != null) {
                                    updateErrStr = (String) session.getAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR);
                                    updatesuccessStr = (String) session.getAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS);
                                }                        

                                if (StringUtils.isNotEmpty(updateErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + updateErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(updatesuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + updatesuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.EXAM_CONFIG_UPDATE_SUCCESS,null);
                                  } 



        %>
            
<div>     
<table class="table table-striped table-bordered bootstrap-datatable ">
                <thead>
                    <tr>
                       
                        <th>Term</th>
                        <th>Year</th> 
                        <th>Exam</th>                
                        <th>Exam Mode</th>
                    </tr>
                </thead>   
                <tbody>
                    
                    <tr>
                         <td class="center"><%=examConfig.getTerm() %></td>
                         <td class="center"><%=examConfig.getYear() %></td>
                         <td class="center"><%=examConfig.getExam() %></td>
                         <td class="center"><%=examConfig.getExamMode() %></td>  
                         <td class="center">
                                <form name="edit" method="POST" action="updateExamConfig.jsp" > 
                                <input type="hidden" name="schoolUuid" value="<%=examConfig.getSchoolAccountUuid()%>">
                                <input type="hidden" name="term" value="<%=examConfig.getTerm()%>">
                                <input type="hidden" name="year" value="<%=examConfig.getYear()%>">
                                <input type="hidden" name="exam" value="<%=examConfig.getExam()%>">
                                <input type="hidden" name="exammode" value="<%=examConfig.getExamMode()%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Update" /> 
                                </form>                          
                         </td>  

                    </tr>

                </tbody>
            </table>  

       </div>   


    </div>

</div>


<jsp:include page="footer.jsp" />