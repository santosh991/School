
<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.persistence.classroom.RoomDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.classroom.ClassRoom"%>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>
<%@ page import="java.util.Calendar" %>


<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%

   
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

    String accountuuid = school.getUuid();
    String schoolname = school.getSchoolName();

    ExamConfigDAO examConfigDAO = ExamConfigDAO.getInstance();
    ExamConfig examConfig = examConfigDAO.getExamConfig(accountuuid);


    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
   
   
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
   

      RoomDAO roomDAO = RoomDAO.getInstance();
     List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
     classroomList = roomDAO.getAllRooms(accountuuid); 

      Calendar calendar = Calendar.getInstance();
      final int YEAR = calendar.get(Calendar.YEAR)-4;
      final int YEAR_COUNT = YEAR + 5;
   
   
 %>






<jsp:include page="header.jsp" />

<div>
    <ul class="breadcrumb">
     <li> <b> <%=schoolname%> :FORM 3 AND 4 REPORTS PANEL: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> <b> </li> <br>


        <li>
            <a href="reports.jsp">Back</a> <span class="divider">/</span>
        </li>



        
    </ul>
</div>



<div class="row-fluid sortable">




        <div class="box span12">
        <div class="box-content">



         <form  class="form-horizontal"   action="" method="POST" >
               <fieldset>
                
                <div class="control-group" id="divid">
                        <label class="control-label" for="class">Category</label>
                        <div class="controls">
                            <select name="category" >
                                <option value="">select one</option>
                                <option value="Report Card">Report Card</option>
                                <option value="Class List">Class List</option>
                            </select>                           
                          
                        </div>
                    </div> 



              <div class="control-group" id="divid">
                        <label class="control-label" for="class">Class</label>
                        <div class="controls">
                            <select name="currentclassuuid" >
                               
                                 <%
                        int count = 1;
                if (classroomList != null) {
              for (ClassRoom cc : classroomList) {
           if(StringUtils.contains(cc.getRoomName(), "3") || StringUtils.contains(cc.getRoomName(), "4")){
                                %>
                                <option value="<%= cc.getUuid()%>"><%=cc.getRoomName()%></option>
                                <%
                                            count++;
                                                }
                                        }
                                    }
                                %>
                            </select>                           
                          
                        </div>
                    </div> 


                    <div class="control-group" id="divid">
                        <label class="control-label" for="class">Term</label>
                        <div class="controls">
                            <select name="term" >
                                <option value="">select one</option>
                                 <option value="1">ONE</option>
                                 <option value="2">TWO</option>
                                 <option value="3">THREE</option>
                            </select>                           
                          
                        </div>
                    </div> 


                     <div class="control-group" id="divid">
                        <label class="control-label" for="class">Year</label>
                        <div class="controls">
                            <select name="year" >
                                <%
                                for (int j = YEAR; j < YEAR_COUNT; j++) {
                                    if (j == YEAR) {
                                     out.println("<option selected=\"selected\" value=\"" + j + "\">" + j + "</option>");
                                        } else {
                                    out.println("<option value=\"" + j + "\">" + j + "</option>");
                                        }
                                }
                                 %>
                                 
                            </select>                           
                          
                        </div>
                       </div> 


                       <div class="control-group" id="divid">
                        <label class="control-label" for="class">Exam Type</label>
                        <div class="controls">
                            <select name="examtype" >
                               <option value="">select one</option>
                               <option value="4BE8AD46-EAE8-4151-BD18-CB23CF904DDB">Standard</option>
                               <option value="4BE8AD46-EAE8-4151-BD18-CB23CF904DDB">Non-Standard</option>
                                 
                            </select>                           
                          
                        </div>
                       </div> 










                            <div class="form-actions">
                                  <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                  <button type="submit" name="Find" value="promote"   class="btn btn-primary">Generate</button> 
                            </div>



                 </fieldset>
                 </form>

            
            
             

            
       


    </div>

</div>



<jsp:include page="footer.jsp" />
