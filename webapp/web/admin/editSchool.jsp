

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


%>

 <jsp:include page="header.jsp" />

<div class="row-fluid sortable">    
    <div class="box span12">
    <div class="box-header well" data-original-title>
           <p> <a href="adminIndex.jsp">Back</a> </p>
        </div>
       
        <div class="box-content">

         <%
                                


                     %>


            <form class="form-horizontal" action="updateSchool" method="POST"  >
                <fieldset>

                    <div class="control-group">
                        <label class="control-label" for="schoolname">schoolname</label>
                        <div class="controls">
                            <input class="input-xlarge"   name="schoolname" type="text" value="<%=request.getParameter("schoolname")%>">
                        </div>
                    </div>
                        
                    <div class="control-group">
                        <label class="control-label" for="username">username</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="username" type="text" value="<%=request.getParameter("username")%>">
                        </div>
                    </div>

                     <div class="control-group">
                        <label class="control-label" for="password">password</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="password" type="text" value="<%=request.getParameter("password")%>">
                        </div>
                    </div>
                    
                    <div class="control-group">
                        <label class="control-label" for="mobile">mobile</label>
                        <div class="controls">
                            <input class="input-xlarge focused"   name="mobile" type="text" value="<%=request.getParameter("mobile")%>">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">email</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="email" type="text" value="<%=request.getParameter("email")%>">
                        </div>
                    </div>

                   
                    <div class="form-actions">
                        <input type="hidden" name="schooluuid" value="<%=request.getParameter("schooluuid")%>">
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>

                </fieldset>
            </form>
  
       </div>
     </div>
    </div>
    
  <jsp:include page="footer.jsp" />