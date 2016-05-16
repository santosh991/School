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


<div>
    <ul class="breadcrumb">
     <li> <b> <%=schoolname%> :PREPARATION FOR TERM UPDATE: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> <b> </li> 
       
    </ul>
</div>

<div class="row-fluid sortable">
    <div class="box span12">
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
            
   

                                  <p>
                                  
                                 <b style=color:red;>Term Update:<br>
                                 NOTE: This command should only be committed after updating the system to a new term.<br>
                                 If you have not done so, EVERYTHING will be in a mess.<br>

                                 The System is not responsible for user's careless mistakes. <br>

                                 If you are not sure of what you are about to do please quit.</b> 

                   
                                </p>


                                <p>
                                  
                                <form name="edit" method="POST" action="prepare" > 
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Initiate Fee  Committ" /> 
                                </form>  

                                <br>

                                </p>

                         <!--
                                 <p>
                                 <b style=color:red;> Other Payments Committ:<br>



                                </b> 
                                  
                                <form name="edit" method="POST" action="otherItemsUpdate" > 
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Committ Other Payments" /> 
                                </form>  

                                </p>


                            -->










                                <p>
                                 <b style=color:red;>Previous Fee Committ failed??? Click below:<br>



                                </b> 
                                  
                                <form name="edit" method="POST" action="committ" > 
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Committ Fee" /> 
                                </form>  

                                </p>






    </div>

</div>


<jsp:include page="footer.jsp" />
