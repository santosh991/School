
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
      final String FRE_UUID = "";
      final String MATH_UUID = "4f59580d-1a16-4669-9ed5-4b89615d6903";
      final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";
      final String BIO_UUID = "de0c86be-9bcb-4d3b-8098-b06687536c1f";
      final String CHEM_UUID = "552c0a24-6038-440f-add5-2dadfb9a23bd";
      final String BS_UUID = "e1729cc2-524a-4069-b4a4-be5aec8473fe";
      final String COMP_UUID = "";
      final String AGR_UUID = "b9bbd718-b32f-4466-ab34-42f544ff900e";
      final String GEO_UUID = "0e5dc1c6-f62f-4a36-a1ec-064173332694";
      final String CRE_UUID = "f098e943-26fd-4dc0-b6a0-2d02477004a4";
      final String HIST_UUID = "c9caf109-c27d-4062-9b9f-ac4268629e27";
      


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
     List<Perfomance> pDistinctList = new ArrayList<Perfomance>();

     perfomanceList = perfomanceDAO.getPerfomanceList(accountuuid,classroomuuid);
     pDistinctList = perfomanceDAO.getPerfomanceListDistinct(accountuuid, classroomuuid);
     
     Collections.sort(perfomanceList, new PerformanceScoreComparator().reversed()); 
     
     StudentExamDAO studentExamDAO = StudentExamDAO.getInstance();
     List<StudentExam> sutexamList = new ArrayList<StudentExam>(); 
     sutexamList = studentExamDAO.getStudentExamList(accountuuid,classroomuuid); 
     


    double total = 0;
    double mean = 0;
    double score = 0;

    double engscore = 0;
    String engscorestr = "";

    double kswscore = 0;
    String kswscorestr = "";

    double matscore = 0;
    String matscorestr = "";

    double physcore = 0;
    String physcorestr = "";  

     double bioscore = 0;
     String bioscorestr = "";

     double chemscore = 0;
     String chemscorestr = "";

     double bsscore = 0;
     String bsscorestr = "";

     double compscore = 0;
     String compscorestr = "";

      double agriscore = 0;
      String agriscorestr = "";

      double geoscore = 0;
      String geoscorestr = "";

      double crescore = 0;
      String crescorestr = "";

      double histscore = 0;
      String histscorestr = "";


                                       
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
    
    
    Map<String,Double> kswscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> engscorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> physcoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> matscorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> bioscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> chemscorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> bsscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> agriscorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> geoscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> crescorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> histscoreMap = new LinkedHashMap<String,Double>();
  
    String student = "";


    String kis = "";

    String totalz = "";
   
   
 %>






<jsp:include page="header.jsp" />



<div class="row-fluid sortable">




        <div class="box span12">
        <div class="box-header well" data-original-title>
        <p> <%=schoolname%> :FORM 1&2 EXAM MANAGEMENT PANEL</p>
      <!--   <a href="examIndex.jsp" ><span>Form 3&4 Test</span></a>  -->
         
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

                 <div class="control-group">
                 <form  class=""   action="classList" method="POST" target="_blank">
                    <fieldset>
                   
                    <div class="">        
                    <button type="submit" name="Report" value="Report"   class="btn btn-primary">Results</button> 
                    </div>
                   
                    </fieldset>
                 </form>
                 
                 <form  class=""   action="reportForm" method="POST" target="_blank">
                    <fieldset>
                   
                    <div class="">        
                    <button type="submit" name="Report" value="Report"   class="btn btn-primary">ReportForm</button> 
                    </div>
                   
                    </fieldset>
                 </form>
                 </div>
                
             

            <div>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>ADMNO </th>
                        <th>NAME </th>
                        <th>ENG</th>                
                        <th>KIS</th>
                        <th>MAT</th>
                        <th>PHY</th>
                        <th>CHE</th>
                        <th>BIO</th>
                        <th>HIS</th>
                        <th>CRE</th>
                        <th>GEO</th>
                        <th>B/S</th>
                        <th>AGR</th>
                        <th>TOTAL </th>
                        <th>MEAN </th>
                        <th>GRADE </th>         
                        
                    </tr>
                </thead>   
                <tbody>

                    <% 
                                            
                                           
                                            List<Perfomance> list = new ArrayList<>();
                                            Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
                                            double grandscore = 0;
                                            double number = 0.0;

                                             for(Perfomance s : pDistinctList){                              
                                                 list = perfomanceDAO.getPerformance(accountuuid, classroomuuid, s.getStudentUuid());

                                                
                                                 for(Perfomance pp : list){
                                                    cat1 = pp.getCatOne();
                                                    cat2 = pp.getCatTwo();
                                                    endterm = pp.getEndTerm();
                                                    total = (cat1+cat2)/2 +endterm;
                                                    grandscore +=total;
                                                   
                                                           if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
                                                              engscore = total;
                                                              engscorehash.put(s.getStudentUuid(),engscore);
                                                             }
                                                          
                                                            if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
                                                             kswscore = total;
                                                             kswscoreMap.put(s.getStudentUuid(),kswscore);
                                                            
                                                                }

                                                                if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
                                                               physcore = total;
                                                               physcoreMap.put(s.getStudentUuid(),physcore);
                                                           
                                                              }


                                                                 if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
                                                            bioscore = total;
                                                            bioscoreMap.put(s.getStudentUuid(),bioscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
                                                            chemscore = total;
                                                            chemscorehash.put(s.getStudentUuid(),chemscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
                                                            matscore = total;
                                                            matscorehash.put(s.getStudentUuid(),matscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
                                                            bsscore = total;
                                                            bsscoreMap.put(s.getStudentUuid(),bsscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
                                                            agriscore = total;
                                                            agriscorehash.put(s.getStudentUuid(),agriscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
                                                            geoscore = total;
                                                            geoscoreMap.put(s.getStudentUuid(),geoscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
                                                            crescore = total;
                                                            crescorehash.put(s.getStudentUuid(),crescore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
                                                            histscore = total;
                                                            histscoreMap.put(s.getStudentUuid(),histscore);
                                                           
                                                              }

                                             

                                                 }
                                                 


                                                 grandscoremap.put(s.getStudentUuid(), grandscore);
       
                                                 grandscore = 0;
      





                                               }




                                                  ArrayList<?> as = new ArrayList(grandscoremap.entrySet());
                                                    Collections.sort(as,new Comparator(){
                                                      public int compare(Object o1,Object o2){
                                                        Map.Entry e1 = (Map.Entry)o1;
                                                        Map.Entry e2 = (Map.Entry)o2;
                                                        Double f = (Double)e1.getValue();
                                                        Double s = (Double)e2.getValue();
                                                        return s.compareTo(f);
                                                      }
                                                    });




                                                     int count = 1;
                                                     int counttwo = 1;

                                                    for(Object o : as){
                                                       String items = String.valueOf(o);
                                                       String [] item = items.split("=");
                                                       String uuid = item[0];
                                                       totalz = item[1];
                                                       mean = Double.parseDouble(totalz)/11;

                                                       studeadmno = studentAdmNoHash.get(uuid);
                                                       studename = studNameHash.get(uuid);         
                                                      

                                                       

                                                       if(engscorehash.get(uuid)!=null){
                                                         engscore = engscorehash.get(uuid);
                                                         engscorestr = Double.toString(engscore);
                                                       }else{
                                                         engscorestr = "";
                                                       }


                                                       if(kswscoreMap.get(uuid)!=null){
                                                         kswscore = kswscoreMap.get(uuid);
                                                         kswscorestr = Double.toString(kswscore);
                                                       }else{
                                                         kswscorestr = "";
                                                       }


                                                       if(physcoreMap.get(uuid)!=null){
                                                         physcore = physcoreMap.get(uuid);
                                                         physcorestr = Double.toString(physcore);
                                                       }else{
                                                         physcorestr = "";
                                                       }

                                                       if(bioscoreMap.get(uuid)!=null){
                                                         bioscore = bioscoreMap.get(uuid);
                                                         bioscorestr = Double.toString(bioscore);
                                                       }else{
                                                         bioscorestr = "";
                                                       }


                                                       if(chemscorehash.get(uuid)!=null){
                                                         chemscore = chemscorehash.get(uuid);
                                                         chemscorestr = Double.toString(chemscore);
                                                       }else{
                                                         chemscorestr = "";
                                                       }



                                                       if(matscorehash.get(uuid)!=null){
                                                         matscore = matscorehash.get(uuid);
                                                         matscorestr = Double.toString(matscore);
                                                       }else{
                                                         matscorestr = "";
                                                       }

                                                        if(histscoreMap.get(uuid)!=null){
                                                         histscore = histscoreMap.get(uuid);
                                                         histscorestr = Double.toString(histscore);
                                                       }else{
                                                         histscorestr = "";
                                                       }


                                                       if(crescorehash.get(uuid)!=null){
                                                         crescore = crescorehash.get(uuid);
                                                         crescorestr = Double.toString(crescore);
                                                       }else{
                                                         crescorestr = "";
                                                       }


                                                       if(geoscoreMap.get(uuid)!=null){
                                                         geoscore = geoscoreMap.get(uuid);
                                                         geoscorestr = Double.toString(geoscore);
                                                       }else{
                                                         geoscorestr = "";
                                                       }


                                                       if(bsscoreMap.get(uuid)!=null){
                                                         bsscore = bsscoreMap.get(uuid);
                                                         bsscorestr = Double.toString(bsscore);
                                                       }else{
                                                         bsscorestr = "";
                                                       }


                                                       if(agriscorehash.get(uuid)!=null){
                                                         agriscore = agriscorehash.get(uuid);
                                                         agriscorestr = Double.toString(agriscore);
                                                       }else{
                                                         agriscorestr = "";
                                                       }







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
                                        if(Double.parseDouble(totalz)==number){
                                          out.println("<td width=\"3%\" >" + +(count-counttwo++) + "</td>");
                                          }
                                          else{
                                           counttwo=1;
                                           out.println("<td width=\"3%\" >" + count + "</td>");
                                          }
                                        out.println("<td class=\"center\" >" + studeadmno + "</td>");
                                        out.println("<td class=\"center\" >" + studename + "</td>");

                                        out.println("<td class=\"center\">" + engscorestr + "</td>");
                                        out.println("<td class=\"center\">" + kswscorestr + "</td>");
                                        out.println("<td class=\"center\">" + matscorestr + "</td>");
                                        out.println("<td class=\"center\">" + physcorestr + "</td>");
                                        out.println("<td class=\"center\">" + chemscorestr + "</td>");
                                        out.println("<td class=\"center\">" + bioscorestr + "</td>");
                                        out.println("<td class=\"center\">" + histscorestr + "</td>");
                                        out.println("<td class=\"center\">" + crescorestr + "</td>");
                                        out.println("<td class=\"center\">" + geoscorestr + "</td>");
                                        out.println("<td class=\"center\">" + bsscorestr + "</td>");
                                        out.println("<td class=\"center\">" + agriscorestr + "</td>");
             
                                        out.println("<td class=\"center\">" + totalz + "</td>");
                                        out.println("<td class=\"center\">" +df.format(mean) + "</td>"); 
                                        out.println("<td class=\"center\">" + grade + "</td>"); 


                   
  
                          count++;
                          number=Double.parseDouble(totalz);
                          
                         } 
                           
                    %>
                </tbody>
            </table> 

            </div>
                     
       


    </div>

</div>



<jsp:include page="footer.jsp" />