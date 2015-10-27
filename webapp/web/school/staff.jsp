



<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>


<%@page import="com.yahoo.petermwenda83.persistence.user.UsresDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.systemuser.User"%>


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
     

    User user;
    UsresDAO userDAO = UsresDAO.getInstance();
    List <User> userList = new ArrayList<User>();


    SchoolAccount school = new SchoolAccount();
    Element element;
    

   

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    String accountuuid = school.getUuid();

    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }

   
    userList = userDAO.getAllUsers(accountuuid);
     


       
%>






<body>

<div id="tooplate_wrapper">
	

	<jsp:include page="header.jsp" />

    
    <!--<div id="tooplate_middle_subpage">
       
        
    </div>   -->
	
    <div id="tooplate_main_top"></div>        
    <div id="tooplate_main">
               

             
                         <%
                                String useraddErrStr = "";
                                String useraddsuccess = "";
                                session = request.getSession(false);

                                if(session != null) {
                                    useraddErrStr = (String) session.getAttribute(SessionConstants.SCHOOL_USER_EDIT_ERROR);
                                    useraddsuccess = (String) session.getAttribute(SessionConstants.SCHOOL_USER_EDIT_SUCCESS);
                                }                        

                                if (StringUtils.isNotEmpty(useraddErrStr)) {
                                    out.println("<p style='color:red;'>");
                                    out.println("<p class=\"error\">");
                                    out.println("Login error: " + useraddErrStr);
                                    out.println("</p>");
                                    out.println("</p>");
                                    session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_ERROR, null);
                                  } 

                                   if (StringUtils.isNotEmpty(useraddsuccess)) {
                                    out.println("<p style='color:red;'>");
                                    out.println("<p class=\"error\">");
                                    out.println("Login error: " + useraddsuccess);
                                    out.println("</p>");
                                    out.println("</p>");
                                    session.setAttribute(SessionConstants.SCHOOL_USER_EDIT_SUCCESS, null);
                                  } 
                            %>


        <div class="col_w900 col_w900_last">
           	  <div class="news_box">
              	 <h2>School Users</h2>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>Username</th>
                        <th>Position</th>
                         <th>actions</th>  
                       
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      int count =1;
                       for (User u : userList) {
                                      
                          %>
                          <tr>
                         <td width="10%"><%=count%></td>
                         <td class="center"><%=u.getUsername()%></td> 
                         <td class="center"><%=u.getUserType()%></td> 
                         <td class="center">
                            <form name="edit" method="post" action="edituser.jsp"> 
                                <input type="hidden" name="username" value="<%=u.getUsername()%>">
                                <input type="hidden" name="position" value="<%=u.getUserType()%>">
                                <input type="hidden" name="uuid" value="<%=u.getUuid()%>">
                                 <input type="hidden" name="accountuuid" value="<%=accountuuid%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Edit" /> 
                                </form>                          
                        </td>   
                           
                                          
                    </tr>

                    <%       
                           count++;
                            } 
                    %>
                </tbody>
            </table>  




            </div>
           
          
		</div>
        
        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
    <jsp:include page="footer.jsp" />
        
</div> <!-- end of wrapper -->
</body>
</html>