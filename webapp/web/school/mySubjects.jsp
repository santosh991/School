
<%@page import="com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.staff.TeacherSubClass"%>
<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.persistence.classroom.RoomDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.classroom.ClassRoom"%>
<%@page import="com.yahoo.petermwenda83.persistence.subject.SubjectDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.subject.Subject"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>


<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>


<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>


<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Collectors"%>


<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>

<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>

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
   
     String stffID = "";
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     stffID = request.getParameter("staffuuid");

     
     TeacherSubClassDAO teacherSubClassDAO = TeacherSubClassDAO.getInstance();
     List<TeacherSubClass> teachersubclassList = new ArrayList<TeacherSubClass>(); 
     if(teacherSubClassDAO.getSubjectsANDClassesList(stffID) !=null){
      teachersubclassList = teacherSubClassDAO.getSubjectsANDClassesList(stffID);
      }
      
     
     HashMap<String, String> subjectHash = new HashMap<String, String>();
     HashMap<String, String> subjectCodeHash = new HashMap<String, String>();
     
     SubjectDAO subjectDAO = SubjectDAO.getInstance();
     List<Subject> subjectList = new ArrayList<Subject>(); 
     subjectList = subjectDAO.getAllSubjects(); 
      for(Subject s : subjectList){
           subjectHash.put(s.getUuid() , s.getSubjectName());  
           subjectCodeHash.put(s.getUuid() , s.getSubjectCode());
            }



     HashMap<String, String> roomHash = new HashMap<String, String>();
     RoomDAO roomDAO = RoomDAO.getInstance();
     List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
     classroomList = roomDAO.getAllRooms(accountuuid); 
      for(ClassRoom c : classroomList){
           roomHash.put(c.getUuid() , c.getRoomName());

            }

    
    

    DecimalFormat df = new DecimalFormat("0.00"); 
    df.setRoundingMode(RoundingMode.DOWN);
                                        

 %>






<jsp:include page="header.jsp" />

<div>
    <ul class="breadcrumb">

    <li> <b> <%=schoolname%> :MY SUBJECTS TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> <b> </li> <br>

        <li>
            <a href="staff.jsp">Back</a> <span class="divider">/</span>
        </li>

         <li>
            <a href="examUpload.jsp">Upload Exam</a> <span class="divider">/</span>
        </li>
         
    </ul>
</div>



<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-content">
            
            


            <div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>ClassRoom</th>
                        <th>Subject </th>
                        <th>Code </th>
                        <th>Scores </th>
                      <!--  <th>Download</th>
                        <th>Download</th> -->
                        <th>Download</th>
                        <th>Download</th>
                        
                    </tr>
                </thead>   
                <tbody>
          
                    <%                 
                             
                       int count = 1;
                       for(TeacherSubClass cs : teachersubclassList) {
                             out.println("<tr>"); 
                             out.println("<td width=\"3%\" >" + count + "</td>"); 
                             out.println("<td class=\"center\">" + roomHash.get(cs.getClassRoomUuid()) + "</td>"); 
                             out.println("<td class=\"center\">" + subjectHash.get(cs.getSubjectUuid()) + "</td>");  
                             out.println("<td class=\"center\">" + subjectCodeHash.get(cs.getSubjectUuid()) + "</td>");  
                             
                             %> 
                                <td class="center">
                                <form name="edit" method="POST" action="viewScores.jsp"> 
                                <input type="hidden" name="subjectUuid" value="<%=cs.getSubjectUuid()%>">
                                <input type="hidden" name="classroomUuid" value="<%=cs.getClassRoomUuid()%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="View" /> 
                                </form>                          
                               </td>  
                               <!--
                               <td class="center">
                                <form name="edit" method="POST" action="exportText" target="_blank"> 
                                <input type="hidden" name="subjectuuidToken" value="<%=cs.getSubjectUuid()%>">
                                <input type="hidden" name="classroomuuidToken" value="<%=cs.getClassRoomUuid()%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Text" /> 
                                </form>                          
                               </td>  

                               <td class="center">
                                <form name="edit" method="POST" action="exportCSV" target="_blank"> 
                                <input type="hidden" name="subjectuuidToken" value="<%=cs.getSubjectUuid()%>">
                                <input type="hidden" name="classroomuuidToken" value="<%=cs.getClassRoomUuid()%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="CSV" /> 
                                </form>                          
                               </td>  
                                  -->
                                <td class="center">
                                <form name="edit" method="POST" action="exportExcelxlsx" target="_blank"> 
                                <input type="hidden" name="subjectuuidToken" value="<%=cs.getSubjectUuid()%>">
                                <input type="hidden" name="classroomuuidToken" value="<%=cs.getClassRoomUuid()%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Excel xlsx" /> 
                                </form>                          
                               </td>  

                               <td class="center">
                                <form name="edit" method="POST" action="exportExcelxls" target="_blank"> 
                                <input type="hidden" name="subjectuuidToken" value="<%=cs.getSubjectUuid()%>">
                                <input type="hidden" name="classroomuuidToken" value="<%=cs.getClassRoomUuid()%>">
                                <input class="btn btn-success" type="submit" name="edit" id="submit" value="Excel xls" /> 
                                </form>                          
                               </td>  

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
