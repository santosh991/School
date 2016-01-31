

<%@page import="com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.AdminSessionConstants"%>


<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.net.URLEncoder"%>

<%@ page import="net.sf.ehcache.Cache" %>
<%@ page import="net.sf.ehcache.CacheManager" %>
<%@ page import="net.sf.ehcache.Element" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<body>

<%
if (session == null) {
        response.sendRedirect("index.jsp");
    }

    String username = (String) session.getAttribute(AdminSessionConstants.ADMIN_SESSION_KEY);
    if (StringUtils.isEmpty(username)) {
        response.sendRedirect("index.jsp");
    }

     session.setMaxInactiveInterval(AdminSessionConstants.SESSION_TIMEOUT);
     response.setHeader("Refresh", AdminSessionConstants.SESSION_TIMEOUT + "; url=adminLogout");




 AccountDAO accountDAO = AccountDAO.getInstance();
 List<SchoolAccount> schoolList = new ArrayList(); 
 schoolList = accountDAO.getAllSchools();


%>




 <jsp:include page="header.jsp" />

<div class="row-fluid sortable">    
    <div class="box span12">
    <div class="box-header well" data-original-title>
          <p> <a href="addSchool.jsp">Add</a> </p>
        </div>
       
        <div class="box-content">
                    <%

                        

                                String updateErrStr = "";
                                String updatesuccessStr = "";
                                session = request.getSession(false);
                                     updateErrStr = (String) session.getAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR);
                                     updatesuccessStr = (String) session.getAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_SUCCESS); 

                                if(session != null) {
                                    updateErrStr = (String) session.getAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR);
                                    updatesuccessStr = (String) session.getAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_SUCCESS);
                                }                        

                                if (StringUtils.isNotEmpty(updateErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + updateErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(updatesuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + updatesuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_UPDATE_SUCCESS, null);
                                  } 

                                 

                            %>

        
                <table class="table table-striped table-bordered bootstrap-datatable ">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>School Name</th>
                        <th>UserName</th>                
                        <th>Mobile</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>actions</th>
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      int count = 1;
                       for (SchoolAccount s : schoolList) {
                         String status = "Active";
                    %>
                    <tr>
                        <td width="10%"><%=count%></td>
                         <td class="center"><%=s.getSchoolName()%></td> 
                         <td class="center"><%=s.getUsername()%></td>
                         <td class="center"><%=s.getMobile()%></td>
                         <td class="center"><%=s.getEmail()%></td>  
                          <td class="center"><%=status%></td>  
                         <td class="center">
                                <form name="edit" method="POST" action="editSchool.jsp"> 
                                <input type="hidden" name="schoolname" value="<%=s.getSchoolName()%>">
                                <input type="hidden" name="username" value="<%=s.getUsername()%>">
                                 <input type="hidden" name="password" value="<%=s.getPassword()%>">
                                <input type="hidden" name="mobile" value="<%=s.getMobile()%>">
                                <input type="hidden" name="email" value="<%=s.getEmail()%>">
                                <input type="hidden" name="schooluuid" value="<%=s.getUuid()%>">
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
    </div>
    
  <jsp:include page="footer.jsp" />

