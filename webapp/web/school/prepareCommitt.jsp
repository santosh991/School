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
                                 <b style=color:red;> <em> WARNING!!!! The commands below invoke Batch Processing events. Read carefully.</em></b><br>

                                <b style=color:black;> 
                                 NOTE: This is an irreversible and uninterruptible command and should only be invoked at the <u>beginning of a new Term and/or Year</u>.<br>
                                 Please ensure you have done the following before you proceed:<br>
                                 <ol>
                                 <li>Submited all the previous Term exams.</li>
                                 <li>Completed everything pertaining <u>Students Fee</u> for the previous Term and you are not intending to make any other fee changes for that Term whatsoever.</li>
                                 <li>If that is not the case, go back to previous Term and/or Year  <a href="examConfig.jsp"> Back</a> </li>
                                 </ol>
                                 <!--
                                  After you are done with "committ" command, confirm that all students account have been updated, updated account have the status "DEDUCTED".However, If you see the status "NOTDEDUCTED", you should do the following.
                                  <ol>
                                    <li>Click the control panel</li>
                                    <li>Locate Term Configurations and click "Update". (Don't make any change to the values)</li>
                                    <li>Click "Save changes"</li>
                                    <li>You will be redirected to this page. Scroll to the bottom of the page and click on the button "Committ Fee"</li>
                                    The button is only invoked when some students account fails to update for reasons like unexpected shutdown.<br>
                                  </ol>
                                  </b>

                                <p>
                                <b style=color:magenta;> If you have comprehended and understood the consequences of the command below, you can now initiate fee committ by clicking on the button <u>"Initiate Fee  Committ"</u> below, then proceed to committing the changes.  <br>
                                
                                </b> 
                                </p> -->

                                <p>
                                  <b style=color:red> I don't understand this.</b>  <a href="studentIndex.jsp">Please get me out of here</a> 
                                </p>




                                <p>
                                <form name="edit" method="POST" action="prepare" > 
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Initiate Fee  Committ" /> 
                                </form>  
                                <br>
                                </p>

                      
                                 
                          
                                <p>
                                <b style=color:red;>Previous Fee Committ failed??? Don't panic, Just click  the button below:<br>
                                </b>  
                                <form name="edit" method="POST" action="committ" > 
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Committ Fee" /> 
                                </form>  
                                </p>






    </div>

</div>


<jsp:include page="footer.jsp" />
