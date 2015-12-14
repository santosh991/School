<%@page import="com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamDetail"%>
<%@page import="com.yahoo.petermwenda83.persistence.exam.PerformanceDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.result.Perfomance"%>



<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.net.URLEncoder"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page import="org.joda.time.MutableDateTime"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%


  if (session == null) {
        response.sendRedirect("../index.jsp");
    }

    String username = (String) session.getAttribute(SessionConstants.ACCOUNT_SIGN_IN_KEY);
    if (StringUtils.isEmpty(username)) {
        response.sendRedirect("../index.jsp");
    }

    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../logout");

    CacheManager mgr = CacheManager.getInstance();
    Cache accountsCache = mgr.getCache(CacheVariables.CACHE_ACCOUNTS_BY_USERNAME);
    Cache statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_ACCOUNT);
    SessionStatistics statistics = new SessionStatistics();
     
     ExamDetail examDetail;
     Perfomance perfomance;

    
     String Term ="ONE";
     String Year ="2015";
     String ExamDetailUuid ="";
    
   



    SchoolAccount school = new SchoolAccount();
    Element element;
    

   

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    String accountuuid = school.getUuid();

    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }



    ExamDetailDAO examDetailDAO = ExamDetailDAO.getInstance();
    List<ExamDetail> examDetailList = new ArrayList(); 
   

    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();
    List<Perfomance> performanceList = new ArrayList(); 
    performanceList = performanceDAO.getAllPerfomance(ExamDetailUuid);


   
   %>

<div id="tooplate_wrapper">
	
   <jsp:include page="header.jsp" />
    
    <div id="tooplate_middle_subpage">
        <h2>Exam Management</h2>

            
       
    </div>
	
    <div id="tooplate_main_top"></div>        
    <div id="tooplate_main">
        
         <div class="col_w900 col_w900_last">
            <div class="col_w580 float_l">
            
            <div class="control-group">
                        <label class="control-label" for="name">Select Term:</label>
                         <div class="controls">
                            <select name="YearOfStudy" >
                                <option value="">Please select one</option> 
                                <option value="1">ONE</option>
                                <option value="2">TWO</option>
                                <option value="3">THREE</option>
                               
                                
                            </select>                           
                          
                        </div>
                    </div> 
                 

                    <div class="control-group">
                        <label class="control-label" for="name">Select Year:</label>
                         <div class="controls">
                            <select name="YearOfStudy" >
                                <option value="">Please select one</option> 
                                <option value="1">2015</option>
                                <option value="2">2016</option>
                                <option value="3">2017</option>
                                <option value="4">2018</option>
                                
                            </select>                           
                          
                        </div>
                    </div> 

      <%
         examDetailList = examDetailDAO.getAllExamdetails(accountuuid,Term,Year);
        // out.println(examDetailList);
    


      %>





           	  
            </div>
            
            <div class="col_w280 float_r">
                
                <h3>xxxxxx</h3>
                <ul class="tooplate_list">
                	<li><a href="#">xxxxxx</a></li>
                    <li><a href="#">xxxxxx</a></li>
                    <li><a href="#">xxxxxx</a></li>
                    <li><a href="#">xxxxxx</a></li>
                    <li><a href="#">xxxxxx</a></li>
                    <li><a href="#">xxxxxx</a></li>
                
                </ul>
                <div class="cleaner h30"></div>
                <h3>yyyyyyyy</h3>
               
            </div>
            
            <div class="cleaner"></div>
		</div>
        
        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
    <jsp:include page="footer.jsp" />
        
</div> <!-- end of wrapper -->
</body>
</html>