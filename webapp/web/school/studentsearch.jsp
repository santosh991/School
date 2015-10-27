

<%@page import="com.yahoo.petermwenda83.persistence.user.UsresDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.systemuser.User"%>

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
    StudentDAO studentDAO = StudentDAO.getInstance();
    RoomDAO roomDAO = RoomDAO.getInstance();
    UsresDAO userDAO = UsresDAO.getInstance();

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

    //int incount = 0;  // Generic counter

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    String accountuuid = school.getUuid();
    String admno = request.getParameter("admissNo");

    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }

     List <User> usersList = new ArrayList<User>();
     usersList = userDAO.getAllUsers(accountuuid); 

     
     List<Student> studentList = new ArrayList(); 
     studentList = studentDAO.getStudentAdmNo(accountuuid, admno);

        //studentList = studentpage.getContents();
       

        for(StudentSubject sb : subList){
            subHash.put(sb.getStudentUuid(), sb.getClassRoomUuid() );
        
          }
          for(ClassRoom c : roomList){
          roomHash.put(c.getUuid(), c.getRoom()+"("+c.getRoomName()+")"  );

          }
       
%>       
                    <%  
                    int count =1;                                                        
                      
                       for (Student st : studentList) {
                       if(StringUtils.isBlank(roomHash.get(subHash.get(st.getUuid()) ))){
                           ClassRoom ="";
                            }else{
                            ClassRoom = roomHash.get(subHash.get(st.getUuid()));
                            }
                      
                    %>
                    <tr class="tabledit">
                        <td width="10%"><%=count%></td>
                         <td class="center"><%=st.getFirstname()%></td> 
                          <td class="center"><%=st.getLastname()%></td> 
                          <td class="center"><%=st.getSurname()%></td> 
                           <td class="center"><%=st.getAdmno()%></td> 
                            <td class="center"><%=st.getGender()%></td> 
                            <td class="center"><%=ClassRoom%></td> 
                                          
                    </tr>

                    <%       
                           count++;
                            } 
                    %>
               


       
          