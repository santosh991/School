
<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.Perfomance"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.StudentExamDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.StudentExam"%>

<%@page import="com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.staff.ClassTeacher"%>
<%@page import="com.yahoo.petermwenda83.server.servlet.upload.UploadFrom34"%>

<%@page import="com.yahoo.petermwenda83.util.performance.comparator.PerformanceScoreComparator"%>

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

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>


<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

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


    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
   
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     String classroomuuid = "";

     ClassTeacherDAO classTeacherDAO = ClassTeacherDAO.getInstance();
     ClassTeacher classTeacher = classTeacherDAO.getClassTeacher(stffID);
     if(classTeacher !=null){
           classroomuuid = classTeacher.getClassRoomUuid();
         }

      final String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
      final String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
      final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";


     HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
     HashMap<String, String> studNameHash = new HashMap<String, String>();

     StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList<Student>(); 
     studentList = studentDAO.getAllStudents(accountuuid,classroomuuid);
    
     for(Student stu : studentList){
           studentAdmNoHash.put(stu.getUuid(),stu.getAdmno());  
           studNameHash.put(stu.getUuid(),stu.getFirstname()+" "+stu.getLastname()+" "+stu.getSurname()); 
            }

     Perfomance perfor;
     HashMap<String, Perfomance> perfomanceHash = new HashMap<String, Perfomance>();

     HashMap<String, String> stuSubHash = new HashMap<String, String>();
     HashMap<String, Double> engscoreHash = new HashMap<String, Double>();
     HashMap<String, Double> kiswscoreHash = new HashMap<String, Double>();
     HashMap<String, Double> physcoreHash = new HashMap<String, Double>();  

     PerfomanceDAO perfomanceDAO = PerfomanceDAO.getInstance();
     List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 

     perfomanceList = perfomanceDAO.getPerfomanceList(accountuuid,classroomuuid);

    // Collections.sort(perfomanceList, new PerformanceScoreComparator()); 
     Collections.sort(perfomanceList, new PerformanceScoreComparator().reversed()); 
     
     StudentExamDAO studentExamDAO = StudentExamDAO.getInstance();
     List<StudentExam> sutexamList = new ArrayList<StudentExam>(); 
     sutexamList = studentExamDAO.getStudentExamList(accountuuid,classroomuuid); 
     


    double total = 0;
    double mean = 0;
    double score = 0;
    double engscore = 0;
    double kswscore = 0;
    double physcore = 0;
                                       
    String grade = "";
    String studeadmno = "";
    String studename = "";
    String admno = "";

    double cat1 = 0;  
    double cat2  = 0;
    double endterm  = 0;
    double examcattotal  = 0;
    double paper1  = 0;
    double paper2  = 0;
    double paper3  = 0;
    double catTotals  = 0;
    double catmean  = 0;

    

    DecimalFormat df = new DecimalFormat("0.00"); 
    df.setRoundingMode(RoundingMode.DOWN);
                                        
    String notNull=null;

   
 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-header well" data-original-title>
          <p> <%=schoolname%> :EXAM MANAGEMENT PANEL</p>
        </div>
        <div class="box-content">
            
            <%
                                String uploadErr = "";
                                String uploadsuccess = "";

                                     session = request.getSession(false);
                                     uploadErr = (String) session.getAttribute(SessionConstants.FILE_UPLOAD_ERROR);
                                     uploadsuccess = (String) session.getAttribute(SessionConstants.FILE_UPLOAD_SUCCESS); 

                                     if(session != null) {
                                      uploadErr = (String) session.getAttribute(SessionConstants.FILE_UPLOAD_ERROR);
                                      uploadsuccess = (String) session.getAttribute(SessionConstants.FILE_UPLOAD_SUCCESS); 
                                       }                     

                                if (StringUtils.isNotEmpty(uploadErr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + uploadErr);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.FILE_UPLOAD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(uploadsuccess)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + uploadsuccess);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.FILE_UPLOAD_SUCCESS, null);
                                  } 




                 %>
      
              
               <p> <h3>Download Report </h3>  </p>
          
                 <form  class="form-horizontal"   action="classList" method="POST" target="_blank">
                    <fieldset>
                   
                    <div class="form-actions">        
                    <button type="submit" name="Report" value="Report"   class="btn btn-primary">Download</button> 
                    </div>
                   
                    </fieldset>
                 </form>
             

            <div>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>ADMNO </th>
                        <th>NAME </th>
                        <th>ENG</th>                
                        <th>KIS</th>
                        <th>FRE</th>
                        <th>MAT</th>
                        <th>PHY</th>
                        <th>CHE</th>
                        <th>BIO</th>
                        <th>HIS</th>
                        <th>CRE</th>
                        <th>GEO</th>
                        <th>B/S</th>
                        <th>AGR</th>
                        <th>COM</th>
                        <th>H/S</th>
                        <th sortable ="true">TOTAL </th>
                        <th>MEAN </th>
                        <th>GRADE </th>         
                        
                    </tr>
                </thead>   
                <tbody>

                    <%                 
                         String engscorestr = ""; 
                         String kiswscorestr = ""; 
                         String physcorestr = "";      
                             
                                        
                         int count = 1;
                         for(StudentExam student : sutexamList){ 
                                       studeadmno = studentAdmNoHash.get(student.getStudentUuid());
                                       studename = studNameHash.get(student.getStudentUuid());                             
                          
                                       for(Perfomance p : perfomanceList){
                                         
                                    if(StringUtils.equals(student.getStudentUuid(),p.getStudentUuid())){
                                      
                                                       cat1 = p.getCatOne();
                                                       cat2 = p.getCatTwo();
                                                       endterm = p.getEndTerm();
                                                       catTotals = cat1 + cat2;
                                                       catmean = catTotals/2;
                                                       examcattotal = endterm + catmean;
                                                       
                                                                                                                                        
                                                  if(StringUtils.equals(ENG_UUID,p.getSubjectUuid())){
                                                       

                                                       engscore = examcattotal;
                                                       engscoreHash.put(p.getStudentUuid(),engscore); 
                                                       

                                                       
                                                       
                                                  }

                                                  if(StringUtils.equals(KISWA_UUID,p.getSubjectUuid())){
                                                       
                                                       kswscore = examcattotal;
                                                       kiswscoreHash.put(p.getStudentUuid(),kswscore); 
                                                       
                                                  }

                                                  if(StringUtils.equals(PHY_UUID,p.getSubjectUuid())){
                                                         physcore = examcattotal;
                                                         physcoreHash.put(p.getStudentUuid(),physcore); 
                                                             
                                                       
                                                       }
  
                                                      
                                              }
                                                  
                                       }   
                                            
                                             
                                              if(engscoreHash.get(student.getStudentUuid()) !=null){                                           
                                                engscore = engscoreHash.get(student.getStudentUuid());
                                                engscorestr = new Double(engscore).toString();
                                              }else{
                                                  engscorestr = " ";
                                                  engscore = 0;
                                              }

                                              if(kiswscoreHash.get(student.getStudentUuid()) !=null){                                           
                                                kswscore = kiswscoreHash.get(student.getStudentUuid());
                                                kiswscorestr = new Double(kswscore).toString();
                                              }else{
                                                  kiswscorestr = " ";
                                                  kswscore = 0;
                                              }

                                              if(physcoreHash.get(student.getStudentUuid()) !=null){                                           
                                                physcore = physcoreHash.get(student.getStudentUuid());
                                                physcorestr = new Double(physcore).toString();
                                              }else{
                                                  physcorestr = " ";
                                                  physcore = 0;
                                              }
                                                

                                              
                                                         total = engscore + kswscore + physcore;
                                                        
                                                         mean = total/3; 
                                                          

                                                         if(mean >= 83){
                                                             grade = "A";
                                                         }else if(mean >= 71){
                                                              grade = "A-";
                                                         }else if(mean >= 67){
                                                              grade = "B+";
                                                         }else if(mean >= 61){
                                                              grade = "B";
                                                         }else if(mean >= 55){
                                                              grade = "B-";
                                                         }else if(mean >= 47){
                                                              grade = "C+";
                                                         }else if(mean >= 42){
                                                              grade = "C";
                                                         }else if(mean >= 38){
                                                              grade = "C-";
                                                         }else if(mean >= 35){
                                                              grade = "D+";
                                                         }else if(mean >= 30){
                                                              grade = "D";
                                                         }else if(mean >= 25){
                                                              grade = "D-";
                                                         }else{
                                                              grade = "E";
                                                         }
                                             
                                             

                                        out.println("<tr>");
                                        out.println("<td width=\"3%\" >" + count + "</td>");
                                        out.println("<td class=\"center\" >" + studeadmno + "</td>");
                                        out.println("<td class=\"center\" >" + studename + "</td>");
                                        out.println("<td class=\"center\">" + engscorestr + "</td>");
                                        out.println("<td class=\"center\">" + kiswscorestr + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + physcorestr + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + " " + "</td>");
                                        out.println("<td class=\"center\">" + total + "</td>");
                                        out.println("<td class=\"center\">" + df.format(mean) + "</td>");
                                        out.println("<td class=\"center\">" + grade + "</td>"); 


                   
  
                          count++;
                          
                        } 
                           
                    %>
                </tbody>
            </table> 


                
                 <h3>
                <%
                    if(StringUtils.isNotBlank((String)session.getAttribute( UploadFrom34.UPLOAD_FEEDBACK ))) {
                    String servletResponse =(String)session.getAttribute( UploadFrom34.UPLOAD_FEEDBACK );
                        out.println(servletResponse);
                        //used by javascript 
                        if(servletResponse!=null){notNull=servletResponse.substring(0,10);
                        }                    
                        session.setAttribute(UploadFrom34.UPLOAD_FEEDBACK, null);
                    }
                %>  
                </h3>
                
                
                <p>Upload CSV file with format <code>admission number,score</code></p>
                <p>The results file must be saved as<code>subjectcode.classcode.examcode.csv</code><p> 
                <form method="POST" action="resultUpload" enctype="multipart/form-data">
                <fieldset>
                    <div class="control-group" id="javascript" javaScriptCheck="<%=notNull%>">
                                              
                         
                    <input type="file" name="file"/>

                   <div class="form-actions">                        
                        <button type="submit" class="btn btn-primary">Upload File</button>           
                    </div>
                    </div>
                </fieldset>
                </form> 
            </div>
                     
       


    </div>

</div>
<!--scroll to the bottom the page if file upload is done -->
<script type="text/javascript">


$("document").ready(function() {   

        var check1 = $("#javascript").attr("javaScriptCheck");
        if(check1.length>4){
           $("html, body").animate({ scrollTop: $(document).height() });
        }   


 $('#performance').dataTable({

    "aoColumnDefs":[
    {
     'bSortable':false,
     'aTargets':[0] 
     }]

  });



    });
</script>


<jsp:include page="footer.jsp" />
