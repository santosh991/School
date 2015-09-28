

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
<%@page import="java.net.URLEncoder"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page import="org.joda.time.MutableDateTime"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<body>

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

    SchoolAccount school = new SchoolAccount();
    Element element;

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    String accountuuid = school.getUuid();

    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }

%>




 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   
    <div id="tooplate_middle">

                   


       <!-- <p>Students Primary School Details</p>
        
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>School Name</th>
                        <th>Index</th>                
                        <th>K.C.P.E Year</th>
                        <th>K.C.P.E Mark</th>
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      /*  int count = 1;
                       for (Primary p : pList) {
                       
                    %>
                    <tr>
                        <td width="10%"><%=count%></td>
                         <td class="center"><%=p.getSchoolname()%></td> 
                         <td class="center"><%=p.getIndex()%></td>
                         <td class="center"><%=p.getKcpeyear()%></td>
                        <td class="center"><%=p.getKcpemarks()%></td>                       
                    </tr>

                    <%
                           count++;
                            } */
                    %>
                </tbody>
            </table>  -->







     
        <div id="middle_left">
            <h3>School Panel</h3>
           
        </div>
      






       

	</div>
	
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>