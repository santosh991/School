<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.persistence.money.StudentAmountDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.money.StudentAmount"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Collectors"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>

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

      
     int sessiontime = SessionConstants.SESSION_TIMEOUT;
     
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
   

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

     StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList(); 
     studentList = studentDAO.getAllStudentList(accountuuid); 
      HashMap<String, Student> studentHash = new HashMap<String, Student>();
      if(studentList !=null){
      for(Student stu : studentList){
         studentHash.put(stu.getUuid(), stu); 
         }
     }
    

     StudentAmountDAO studentAmountDAO = StudentAmountDAO.getInstance();
     List<StudentAmount> amountList = new ArrayList<StudentAmount>(); 
     amountList = studentAmountDAO.getAmountList(accountuuid); 
     //amountList  StudentAmount  studentHash  Student
    
 %>






<jsp:include page="header.jsp" />


<div>
    <ul class="breadcrumb">
     <li> <b> <%=schoolname%> :STUDENT FEE ACCOUNT STATUS: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> <b> </li> <br>   

        <li>
            <a href="fee.jsp">Back</a> <span class="divider">/</span>
        </li>

       
    </ul>
</div>

<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-content">




        <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr >
                        <th>*</th>
                        <th>Student Adm No</th>
                        <th>Student Name</th>                
                        <th>Status</th>
                    </tr>
                </thead>   
                <tbody>
                    <%
                    //amountList  StudentAmount  studentHash  Student
                     int count = 1;
                     Student students;
                     if(amountList!=null){
                    for(StudentAmount amnt : amountList){
                    students = new Student();
                    students = studentHash.get(amnt.getStudentUuid());
                    if(students!=null){
                        String statusUuid = "85C6F08E-902C-46C2-8746-8C50E7D11E2E";
                    if(StringUtils.equals(students.getStatusUuid(),statusUuid)){

                     %>
                   

                    <tr>
                         <td width="3%"><%=count%></td>
                         <td class="center"><%=students.getAdmno()%></td> 
                         <td class="center"><%=students.getFirstname()+" "+ students.getLastname()%></td>
                         <td class="center"><%=amnt.getStatus()%></td>
                    </tr>

                    <%
                          count++;
                             }
                           }
                         }
                       }
                            
                    %>
                </tbody>
            </table>  

       
   

                                  



    </div>

</div>


<jsp:include page="footer.jsp" />
