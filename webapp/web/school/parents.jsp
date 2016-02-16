<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.persistence.guardian.ParentsDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.guardian.StudentParent"%>

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.pagination.student.StudentPaginator"%>
<%@page import="com.yahoo.petermwenda83.pagination.student.StudentPage"%>

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

<%@page import="java.text.SimpleDateFormat"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%
     String accountuuid = "";

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

    accountuuid = school.getUuid();
    String schoolname = school.getSchoolName();

    ExamConfigDAO examConfigDAO = ExamConfigDAO.getInstance();
    ExamConfig examConfig = examConfigDAO.getExamConfig(accountuuid);


    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
   
   
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     
     StudentParent studentParent = new StudentParent();
     HashMap<String, StudentParent> studentParentHash = new HashMap<String, StudentParent>();
     ParentsDAO parentsDAO = ParentsDAO.getInstance();
     List<StudentParent> parentList = new ArrayList<StudentParent>(); 

     parentList = parentsDAO.getParentList();
     for(StudentParent stuparent : parentList){
        studentParentHash.put(stuparent.getStudentUuid(), stuparent);
        }
    

     StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList(); 
     studentList = studentDAO.getStudentList(school , 0 , 15); 

     int ussdCount = 0;
     StudentPaginator paginator = new StudentPaginator(accountuuid);
     StudentPage studentpage;



     studentpage = (StudentPage) session.getAttribute("currentPage");
        String referrer = request.getHeader("referer");
        String pageParam = (String) request.getParameter("page");

        // We are to give the first page
        if (studentpage == null
                || !StringUtils.endsWith(referrer, "parents.jsp")
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

     
                             

 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">

               




    <div class="box span12">
        <div class="box-header well" data-original-title>
         <p>Welcome to <%=schoolname%> :Parents Management Panel: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> </p>
        </div>
        <div class="box-content">
            <p> <b>S</b> stands for Student, <b>F</b> stands for Father and <b>M</b> stands for Mother</p>
            
                <div id="search_box">
                <form action="#" method="get">
                 <input type="text" placeholder="Search By AdmNo" name="q" size="10" id="searchfield" title="searchfield" onkeyup="searchstudents(this.value)" />
                </form>
                </div>


            <div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>S AdmNo</th>
                        <th>F Name</th>
                        <th>F Phone</th>
                        <th>F Email</th>
                        <th>F ID</th>
                        <th>F Occu</th>
                        <th>M Name</th>
                        <th>M Phone</th>
                        <th>M Email</th>
                        <th>M ID</th>
                        <th>M Occu</th>
                        
                            
                    </tr>
                </thead>   
                <tbody>
          
                    <%                 
                             
                   
                      
                        if(studentList !=null){
                       for(Student ss : studentList) { 

                            studentParent = studentParentHash.get(ss.getUuid());
                             

                             out.println("<tr>"); 
                             out.println("<td width=\"3%\" >" + ussdCount + "</td>"); 
                             out.println("<td width=\"10%\" class=\"center\">" + ss.getAdmno() + "</td>"); 
                            
                             if(studentParent !=null){

                             out.println("<td width=\"13%\" class=\"center\">" + studentParent.getFathername() + "</td>");
                             out.println("<td width=\"10%\" class=\"center\">" + studentParent.getFatherphone() + "</td>"); 
                             out.println("<td width=\"12%\" class=\"center\">" + studentParent.getFatherEmail() + "</td>");
                             out.println("<td width=\"8%\" class=\"center\">" + studentParent.getFatherID() + "</td>");
                             out.println("<td width=\"15%\" class=\"center\">" + studentParent.getFatheroccupation() + "</td>"); 

                             out.println("<td width=\"13%\" class=\"center\">" + studentParent.getMothername() + "</td>"); 
                             out.println("<td width=\"10%\" class=\"center\">" + studentParent.getMotherphone() + "</td>"); 
                             out.println("<td width=\"12%\" class=\"center\">" + studentParent.getMotherEmail() + "</td>");
                             out.println("<td width=\"8%\" class=\"center\">" + studentParent.getMotherID() + "</td>"); 
                             out.println("<td width=\"15%\" class=\"center\">" + studentParent.getMotheroccupation() + "</td>");

                                 
                                       }


                          ussdCount++;
                          } 
                     }
                    %>
                    
                    </tbody>
            </table> 


            <div id="pagination">
                <form name="pageForm" method="post" action="parents.jsp">                                
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

            
            </div>
       


    </div>

</div>


<jsp:include page="footer.jsp" />
