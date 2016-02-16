

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>


<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%


     if (session == null) {
       response.sendRedirect("../index.jsp");
       //return;
    }

    String username = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
    if (StringUtils.isEmpty(username)) {
        response.sendRedirect("../index.jsp");
        //return;
    }

    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
    //return;


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


    String admno = request.getParameter("admissNo");
     
    
    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }

    
     StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList(); 
     studentList = studentDAO.getStudentByAdmNo(accountuuid, admno);
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-dd-MM");
        
%>       
                    <%  
                    int count =1;                                                        
                       String fullname = "";
                       for (Student st : studentList) {
                           fullname = st.getFirstname()+" "+st.getSurname()+" "+st.getLastname();
                      
                    %>
                    <tr class="tabledit">
                         <td width="3%"><%=count%></td>
                         <td class="center"><%=st.getAdmno()%></td> 
                         <td class="center"><%=fullname%></td>
                         <td class="center"><%=st.getGender()%></td>
                         <td class="center"><%=st.getdOB()%></td>
                         <td class="center"><%=st.getBcertno()%></td>
                         <td class="center"><%%></td>
                         <td class="center"><%=st.getCounty()%></td>
                         <td class="center"><%%></td>
                         <td class="center"><%%></td>
                         <td class="center"><%%></td>
                         <td class="center"><%%></td>
                         <td class="center"><%=dateFormatter.format(st.getAdmissionDate())%></td>    
                         <td class="center"><%%></td>                   
                         <td class="center">
                                          
                    </tr>

                    <%       
                           count++;
                            } 
                    %>
               


       
          