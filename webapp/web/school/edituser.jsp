


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




<body>
 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   <div id="tooplate_middle">

 <div class="box-content">
 <div class="row-fluid sortable">	

    <div class="box span12">

            <form class="form-horizontal" action="../editUser" method="POST"  >
                <fieldset>

                    <div class="control-group">
                        <label class="control-label" for="schoolname">Username</label>
                        <div class="controls">
                            <input class="input-xlarge"   name="username" type="text" value="<%=request.getParameter("username")%>">
                        </div>
                    </div>
                        
                         <div class="control-group">
                        <label class="control-label" for="position">Position</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="position" type="text" value="<%=request.getParameter("position")%>">
                        </div>
                    </div>
                    
                    

                   
                    <div class="form-actions">
                        <input type="hidden" name="uuid" value="<%=request.getParameter("uuid")%>">
                         <input type="hidden" name="accountuuid" value="<%=request.getParameter("accountuuid")%>">
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                        <a href="home.jsp"><button class="btn">Cancel</button></a>
                    </div>

                </fieldset>
            </form>
             

           </div>



         <div class="box span12">



            <form class="form-horizontal" action="../editUserPassword" method="POST"  >
                <fieldset>

                    <div class="control-group">
                        <label class="control-label" for="Password">New PassWord</label>
                        <div class="controls">
                            <input class="input-xlarge"   name="password" type="password" value="">
                        </div>
                    </div>
                        
                         <div class="control-group">
                        <label class="control-label" for="Password">Confirm PassWord</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="password2" type="password" value="">
                        </div>
                    </div>
                    
                    

                   
                    <div class="form-actions">
                        <input type="hidden" name="uuid" value="<%=request.getParameter("uuid")%>">
                         <input type="hidden" name="accountuuid" value="<%=request.getParameter("accountuuid")%>">
                        <button type="submit" class="btn btn-primary">Edit</button>
                        <a href="home.jsp"><button class="btn">Cancel</button></a>
                    </div>

                </fieldset>
            </form>


        </div>

       

      










    </div><!--/span-->

</div><!--/row-->


        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>