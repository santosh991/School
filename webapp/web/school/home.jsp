

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.StudentSubject"%>

<%@page import="com.yahoo.petermwenda83.persistence.room.RoomDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.room.ClassRoom"%>


<%@page import="com.yahoo.petermwenda83.pagination.student.StudentPaginator"%>
<%@page import="com.yahoo.petermwenda83.pagination.student.StudentPage"%>

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

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
     
    StudentSubjectDAO subjectDAO = StudentSubjectDAO.getInstance();
    RoomDAO roomDAO = RoomDAO.getInstance();

    HashMap<String, String> roomHash = new HashMap<String, String>();
    HashMap<String, String> subHash = new HashMap<String, String>();
    
    List <StudentSubject> subList = new ArrayList<StudentSubject>();
    subList = subjectDAO.getAllStudentSubject();
    List <ClassRoom> roomList = new ArrayList<ClassRoom>();
    roomList = roomDAO.getAllRooms();

    SchoolAccount school = new SchoolAccount();
    Element element;
    StudentSubject subject;
    ClassRoom room;
    String ClassRoom ="";

    int incount = 0;  // Generic counter

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    String accountuuid = school.getUuid();

    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }

     //StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList(); 
     //studentList = studentDAO.getStudentList(school , 0 , 15); 


    //incount = statistics.getAllIncomingCount();


     int ussdCount = 0;
     StudentPaginator paginator = new StudentPaginator(accountuuid);
     StudentPage studentpage;

      /*if (incount == 0) {   // This user has no Incoming USSD in the account
        studentpage = new StudentPage();
        studentList = new ArrayList<Student>();
        ussdCount = 0;

    } else { */
     studentpage = (StudentPage) session.getAttribute("currentPage");
        String referrer = request.getHeader("referer");
        String pageParam = (String) request.getParameter("page");

        // We are to give the first page
        if (studentpage == null
                || !StringUtils.endsWith(referrer, "home.jsp")
                || StringUtils.equalsIgnoreCase(pageParam, "first")) {
              studentpage = paginator.getFirstPage();

            //We are to give the last page
        } else if (StringUtils.equalsIgnoreCase(pageParam, "last")) {
             studentpage = paginator.getLastPage();

            // We are to give the previous page
        } else if (StringUtils.equalsIgnoreCase(pageParam, "previous")) {
            studentpage = paginator.getPrevPage(studentpage);

            // We are to give the next page 
        } else if (StringUtils.equalsIgnoreCase(pageParam, "next"))  {
           studentpage = paginator.getNextPage(studentpage);
        }

        session.setAttribute("currentPage", studentpage);
        studentList = studentpage.getContents();
        ussdCount = (studentpage.getPageNum() - 1) * studentpage.getPagesize() + 1;
      // }


    

        for(StudentSubject sb : subList){
            subHash.put(sb.getStudentUuid(), sb.getClassRoomUuid() );
        
          }
          for(ClassRoom c : roomList){
          roomHash.put(c.getUuid(), c.getRoom()+"("+c.getRoomName()+")"  );

          }
      
%>
<html>
<body>
 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />

          
     <div id="search_box">
            <form action="#" method="get">
                <input type="text" value="Search" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="" id="searchbutton" title="Search" />
            </form>
        </div>
  
         <div id="tooplate_main">        
         <div id="middle_left" >               
            <a class="btn" href="addstudent.jsp" title="add student" data-rel="tooltip">Add</a>  
         </div>

          <p>Students Details</p>
        
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                         <th>SurName</th>
                          <th>AdmNo</th>
                           <th>Gender</th>
                          <th>Class</th>
                       
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      
                       for (Student st : studentList) {
                       if(StringUtils.isBlank(roomHash.get(subHash.get(st.getUuid()) ))){
                           ClassRoom ="";
                            }else{
                            ClassRoom = roomHash.get(subHash.get(st.getUuid()));
                            }
                      
                    %>
                    <tr>
                        <td width="10%"><%=ussdCount%></td>
                         <td class="center"><%=st.getFirstname()%></td> 
                          <td class="center"><%=st.getLastname()%></td> 
                          <td class="center"><%=st.getSurname()%></td> 
                           <td class="center"><%=st.getAdmno()%></td> 
                            <td class="center"><%=st.getGender()%></td> 
                            <td class="center"><%=ClassRoom%></td> 
                                          
                    </tr>

                    <%       
                           ussdCount++;
                            } 
                    %>
                </tbody>
            </table>  


       
           <div id="pagination">
                <form name="pageForm" method="post" action="home.jsp">                                
                    <%                                            
                        if (!studentpage.isFirstPage()) {
                    %>
                        <input class="toolbarBtn" type="submit" name="page" value="First" />
                        <input class="toolbarBtn" type="submit" name="page" value="Previous" />
                    <%
                        }
                    %>
                    <span class="pageInfo">Page 
                        <span class="pagePosition currentPage"><%= studentpage.getPageNum()%></span> of 
                        <span class="pagePosition"><%= studentpage.getTotalPage()%></span>
                    </span>   
                    <%
                        if (!studentpage.isLastPage()) {                        
                    %>
                        <input class="toolbarBtn" type="submit" name="page" value="Next">  
                        <input class="toolbarBtn" type="submit" name="page" value="Last">
                    <%
                       }
                    %>                                
                </form>
            </div>
	
	
    <div class="col_w900 col_w900_last">
        <div class="col_w580 float_l">
                    <div class="con_tit_01">Welcome <span>to ....... </span></div>
                    
                    <p><em>uyfyuiiuyfghoiuyfghjgvhbjuyghjughjuyghjughjuyghj.</em></p>
                    <p align="justify">jhdhdhfhjierjeioirioriotioioriiurioioriorioruiiuoiro .</p>  

                    <div class="cleaner"></div>
           </div>
          
                <div class="col_w280 float_r">
                  
                    <h2>Latest Updates</h2>
                    <div class="lbe_box">
                    <p class="date">Nov 17, 2015</p>
                    <h3><a href="#">Exam Start</a></h3>
                    <p>ghjkjhghjkliuyfguiuyuioiuyghjitghjiuyghitgyu.</p>
                    
                    </div>
                    <div class="lbe_box">
                      <p class="date">Nov 27, 2015</p>
                        <h3><a href="#">Closing day</a></h3>
                        <p>ghjkkuyiooiuytrtghiutrghjkoiuytfghjuytfgvuytfghjgf.</p>
                        
                        
                    </div>
                    <div class="lbe_box">
                      <p class="date">Dec 10, 2015</p>
                        <h3><a href="#">School Opens</a></h3>
                        <p>hgfdfghjkkgfghlkoiuyfghjkiuhgvbnmlugvhnliughjn.</p>
                    </div>                 
                  <div class="cleaner"></div>
              </div>
      </div>
    <div class="cleaner"></div>
    </div> <!-- end of main -->
 <jsp:include page="footer.jsp" />
        
</div> <!-- end of wrapper -->

</body>
</html>