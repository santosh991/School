
<%@page import="com.yahoo.petermwenda83.server.session.AdminSessionConstants"%>

<%@page import="com.yahoo.petermwenda83.persistence.staff.PositionDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.staff.Position"%>

<%@page import="com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

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
     response.setHeader("Refresh", AdminSessionConstants.SESSION_TIMEOUT + "; url=Logout");

     PositionDAO positionDAO = PositionDAO.getInstance();
     List<Position> positionList = new ArrayList<Position>(); 
     positionList = positionDAO.getPositionList();


     AccountDAO accountDAO = AccountDAO.getInstance();
     List<SchoolAccount> schoolList = new ArrayList(); 
     schoolList = accountDAO.getAllSchools();
%>

 <jsp:include page="header.jsp" />

<div class="row-fluid sortable">    
    <div class="box span12">
    <div class="box-header well" data-original-title>
          <p> <a href="adminIndex.jsp">Back</a> </p>
        </div>
      

        <div class="box-content">

        
                     <%
                    HashMap<String, String> paramHash = (HashMap<String, String>) session.getAttribute(AdminSessionConstants.SCHOOL_ACCOUNT_PARAM);

                        if (paramHash == null) {
                             paramHash = new HashMap<String, String>();
                            }
                             

                             String addErrStr = "";
                                String addsuccessStr = "";
                                session = request.getSession(false);
                                     addErrStr = (String) session.getAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR);
                                     addsuccessStr = (String) session.getAttribute(AdminSessionConstants.PRINCIPAL_ADD_SUCCESS); 

                                if(session != null) {
                                    addErrStr = (String) session.getAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR);
                                    addsuccessStr = (String) session.getAttribute(AdminSessionConstants.PRINCIPAL_ADD_SUCCESS);
                                }                        

                                if (StringUtils.isNotEmpty(addErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(AdminSessionConstants.PRINCIPAL_ADD_SUCCESS, null);
                                  } 


                     %>




            <form  class="form-horizontal"   action="addRootUser" method="POST" >
                <fieldset>

                 <div class="control-group">
                        <label class="control-label" for="SchoolAccount">School Account:</label>
                         <div class="controls">
                            <select name="accountUuid" >
                                <option value="">Please select one</option> 
                               <%
                                    int acount = 1;
                                    if (schoolList != null) {
                                        for (SchoolAccount ac : schoolList) {
                                %>
                                <option value="<%= ac.getUuid()%>"><%=ac.getSchoolName()%></option>
                                <%
                                            acount++;
                                        }
                                    }
                                    %>
                                
                            </select>                           
                          
                        </div>
                    </div> 


                     <div class="control-group">
                        <label class="control-label" for="Category">Category*:</label>
                         <div class="controls">
                            <select name="Category" >
                                <option value="">Please select one</option> 
                                <option value="Teaching">Teaching</option>
                                <option value="Non-Teaching">Non-Teaching</option>
                               
                            </select>                           
                          
                        </div>
                    </div> 


                     <div class="control-group">
                        <label class="control-label" for="Position">Position*:</label>
                         <div class="controls">
                            <select name="PositionUuid" >
                                <option value="">Please select one</option> 
                               
                                     <%
                                    int count = 1;
                                    if (positionList != null) {
                                        for (Position p : positionList) {
                                %>
                                <option value="<%= p.getUuid()%>"><%=p.getPosition()%></option>
                                <%
                                            count++;
                                        }
                                    }
                                    %>
                            </select>                           
                          
                        </div>
                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Principal Username*:</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="principalusername" 
                             value="<%= StringUtils.trimToEmpty(paramHash.get("principalusername")) %>"  >                                    
                        </div>
                    </div> 


                     <div class="control-group">
                        <label class="control-label" for="name">Principal Password*:</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="password" name="principalpassword" 
                             value="<%= StringUtils.trimToEmpty(paramHash.get("principalpasswordprincipalpassword")) %>"  >                                    
                        </div>
                    </div> 


                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>

              </fieldset>
              </form>


 </div>      


  </div>
    </div>
    
  <jsp:include page="footer.jsp" />