
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
      
      //languages
      final String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
      final String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
      //sciences
      final String MATH_UUID = "4f59580d-1a16-4669-9ed5-4b89615d6903";
      final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";
      final String BIO_UUID = "de0c86be-9bcb-4d3b-8098-b06687536c1f";
      final String CHEM_UUID = "552c0a24-6038-440f-add5-2dadfb9a23bd";
      //techinicals
      final String BS_UUID = "e1729cc2-524a-4069-b4a4-be5aec8473fe";
      final String COMP_UUID = "F1972BF2-C788-4F41-94FE-FBA1869C92BC";
      final String H_S = "C1F28FF4-1A18-4552-822A-7A4767643643";
      final String AGR_UUID = "b9bbd718-b32f-4466-ab34-42f544ff900e";
      //humanities 
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
           studNameHash.put(stu.getUuid(),stu.getFirstname()+" "+stu.getLastname()); 
            }

     Perfomance perfor;
     HashMap<String, Perfomance> perfomanceHash = new HashMap<String, Perfomance>();


     PerfomanceDAO perfomanceDAO = PerfomanceDAO.getInstance();
     List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
     List<Perfomance> pDistinctList = new ArrayList<Perfomance>();

     perfomanceList = perfomanceDAO.getPerfomanceList(accountuuid,classroomuuid);
     pDistinctList = perfomanceDAO.getPerfomanceListDistinct(accountuuid, classroomuuid);
     
     Collections.sort(perfomanceList, new PerformanceScoreComparator().reversed()); 
     
     StudentExamDAO studentExamDAO = StudentExamDAO.getInstance();
     List<StudentExam> sutexamList = new ArrayList<StudentExam>(); 
     sutexamList = studentExamDAO.getStudentExamList(accountuuid,classroomuuid); 
     


   
    //nanguages
     double engscore = 0;
     String engscorestr = "";

     double kswscore = 0;
     String kswscorestr = "";

    //sciences
     double matscore = 0;
     String matscorestr = "";

     double physcore = 0;
     String physcorestr = "";  

     double bioscore = 0;
     String bioscorestr = "";

     double chemscore = 0;
     String chemscorestr = "";

     //techinicals
     double bsscore = 0;
     String bsscorestr = "";

     double compscore = 0;
     String compscorestr = "";

     double hscscore = 0;
     String hscscorestr = "";

     double agriscore = 0;
     String agriscorestr = "";

     double geoscore = 0;
     String geoscorestr = "";

     //humanities   
     double crescore = 0;
     String crescorestr = "";

     double histscore = 0;
     String histscorestr = "";


                                       
    String grade = "";
    String studeadmno = "";
    String studename = "";
    String admno = "";

    double total = 0;
    double mean = 0;
    double score = 0;

   
    double paper1  = 0;
    double paper2  = 0;
    double paper3  = 0;
    double examcattotal  = 0;
    
    

    DecimalFormat df = new DecimalFormat("0.00"); 
    df.setRoundingMode(RoundingMode.DOWN);

    DecimalFormat rf = new DecimalFormat("0"); 
    rf.setRoundingMode(RoundingMode.UP);
                                        
    String notNull=null;
    
    HashMap<String, String> stuSubHash = new HashMap<String, String>();
   
   
    
    //languages
    HashMap<String, Double> kiswscoreHash = new HashMap<String, Double>();
    Map<String,Double> kswscoreMap = new LinkedHashMap<String,Double>();
    HashMap<String, Double> engscoreHash = new HashMap<String, Double>();
    Map<String,Double> engscorehash = new LinkedHashMap<String,Double>(); 
    
    //sciences
    Map<String,Double> physcoreMap = new LinkedHashMap<String,Double>();
    HashMap<String, Double> physcoreHash = new HashMap<String, Double>();  
    Map<String,Double> matscorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> bioscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> chemscorehash = new LinkedHashMap<String,Double>(); 

    //techinicals
    Map<String,Double> bsscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> agriscorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> compscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> hscscoreMap = new LinkedHashMap<String,Double>();

    //humanities 
    Map<String,Double> crescorehash = new LinkedHashMap<String,Double>(); 
    Map<String,Double> histscoreMap = new LinkedHashMap<String,Double>();
    Map<String,Double> geoscoreMap = new LinkedHashMap<String,Double>();
  
    String student = "";
    String kis = "";
    String totalz = "";
   
   
 %>






<jsp:include page="header.jsp" />



<div class="row-fluid sortable">




        <div class="box span12">
        <div class="box-header well" data-original-title>
          <p> <%=schoolname%> :FORM 3&4 EXAM MANAGEMENT PANEL</p>
         <!-- <a href="exam.jsp" ><span>Back</span></a>  -->

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
                 <p>Class Performance List</p>
                 <form  class=""   action="form34List" method="POST" target="_blank">
                    <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="name">Exam Type:</label>
                         <div class="controls">
                            <select name="examID" >
                                <option value="">Please select one</option> 
                                <option value="4BE8AD46-EAE8-4151-BD18-CB23CF904DDB">Full Exam</option>
                                <option value="1678664C-D955-4FA7-88C2-9461D3F1E782">Partial Exam</option>
                            </select>                           
                          
                        </div>
                    </div> 

                    <div class="">       
                    <button type="submit" name="Report" value="Report"   class="btn btn-primary">View</button> 
                    </div>
                    </fieldset>
                 </form>
                 <p>Report Forms</p>
                 <form  class=""   action="reportForm" method="POST" target="_blank">
                    <fieldset>
                     <div class="control-group">
                        <label class="control-label" for="name">Exam Type:</label>
                         <div class="controls">
                            <select name="examID" >
                                <option value="">Please select one</option> 
                                <option value="4BE8AD46-EAE8-4151-BD18-CB23CF904DDB">Full Exam</option>
                                <option value="1678664C-D955-4FA7-88C2-9461D3F1E782">Partial Exam</option>
                            </select>                           
                          
                        </div>
                    </div> 
                     <div class="">      
                    <button type="submit" name="Report" value="Report"   class="btn btn-primary">View</button> 
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
                        <th>HSC</th>
                        <th>COM</th> 
                        <th>TOTAL </th>
                        <th>MEAN </th>
                        <th>GRADE </th>         
                        
                    </tr>
                </thead>   
                <tbody>

                    <% 
                                            
                                           
                                            List<Perfomance> list = new ArrayList<>();
                                            Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
                                            double languageScore = 0;
                                            double scienceScore = 0;
                                            double humanityScore = 0;
                                            double techinicalScore = 0;
                                            double grandscore = 0;
                                            double number = 0.0;
                                            
                                             for(Perfomance s : pDistinctList){                              
                                                 list = perfomanceDAO.getPerformance(accountuuid, classroomuuid, s.getStudentUuid());
                                                       
                                                    engscore = 0;
                                                    kswscore = 0;
                                                    matscore = 0;
                                                    physcore = 0;
                                                    bioscore = 0;
                                                    chemscore = 0;
                                                    bsscore = 0;
                                                    compscore = 0;
                                                    hscscore = 0;
                                                    agriscore = 0;
                                                    geoscore = 0;
                                                    crescore = 0;
                                                    histscore = 0;
                                                
                                                 for(Perfomance pp : list){
                                                   
                                                   //Languages
                                                   //Include all the languages
                                                   if(true){
                                                           if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
                                                              paper1 = pp.getPaperOne(); //out of 60
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 60
                                                              total = (paper1 + paper2 + paper3)/2; 
                                                              engscore = total; 
                                                              engscorehash.put(s.getStudentUuid(),engscore);
                                                             }
                                                            

                                                            if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 60
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 60
                                                              total = (paper1 + paper2 + paper3)/2; 
                                                              kswscore = total;
                                                              kswscoreMap.put(s.getStudentUuid(),kswscore);
                                                            
                                                                }

                                                             
                                                            languageScore = (engscore+kswscore); 
                                                           

                                                         }       
                                                    //Sciences
                                                    //Pick best two if the student take the three
                                                    if(true){
                                                    double subjectBig = 0;
                                                    double subjectSmall = 0;
                                                   
                                                            if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 80
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 40
                                                              total = ((paper1 + paper2)/160)*60 + paper3;

                                                              physcore = total;
                                                              physcoreMap.put(s.getStudentUuid(),physcore);
                                                           
                                                              }
                                                            if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 80
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 40
                                                              total = ((paper1 + paper2)/160)*60 + paper3;
                                                              bioscore = total;
                                                              bioscoreMap.put(s.getStudentUuid(),bioscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 80
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 40
                                                              total = ((paper1 + paper2)/160)*60 + paper3;
                                                              chemscore = total;
                                                              chemscorehash.put(s.getStudentUuid(),chemscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 100
                                                              paper2 = pp.getPaperTwo(); //out of 100
                                                              total = (paper1 + paper2)/2;
                                                              matscore = total;
                                                              matscorehash.put(s.getStudentUuid(),matscore);
                                                           
                                                              }


                                                              if(physcore >= bioscore && physcore >= chemscore){
																	subjectBig = physcore;
																	
																	if(subjectBig>bioscore && bioscore > chemscore){
																		subjectSmall = bioscore;
																	}else{
																		subjectSmall = chemscore;
																	}
																	

																}else if(bioscore >= physcore && bioscore >= chemscore){
																	subjectBig = bioscore;
																	
																	if(subjectBig>physcore && physcore > chemscore){
																		subjectSmall = physcore;
																	}else{
																		subjectSmall = chemscore;
																	}
																	
																}else if(chemscore >= physcore && chemscore >= bioscore){
																	subjectBig = chemscore;
																	
																	if(subjectBig>physcore && physcore > bioscore){
																		subjectSmall = physcore;
																	}else{
																		subjectSmall = bioscore;
																	}
																}

                                                              scienceScore = (subjectBig+subjectSmall+matscore);
                                                             
                                                        }
                                                    //Techinicals  
                                                    //Here we pick one subject, the one he/she has performed best, but this subject can be replaced by a science, if the student takes 3 sciences and he/she performed better in the science than in all  the techinicals . 
                                                    if(true){
                                                    double bestTechinical = 0;
                                                            if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 100
                                                              paper2 = pp.getPaperTwo(); //out of 100
                                                              total = (paper1 + paper2)/2;
                                                              bsscore = total;
                                                              bsscoreMap.put(s.getStudentUuid(),bsscore);
                                                           
                                                              }
                                                               if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 80
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 40
                                                              total = (paper1 + paper2)/2 + paper3;
                                                              agriscore = total;
                                                              agriscorehash.put(s.getStudentUuid(),agriscore);
                                                           
                                                              }     
                                                              if(StringUtils.equals(pp.getSubjectUuid(), H_S)){
                                                              paper1 = pp.getPaperOne(); //out of 80
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 40
                                                              total = (paper1 + paper2)/2 + paper3;
                                                              hscscore = total;
                                                              hscscoreMap.put(s.getStudentUuid(),hscscore);
                                                           
                                                              }if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 80
                                                              paper2 = pp.getPaperTwo(); //out of 80
                                                              paper3 = pp.getPaperThree();//out of 40
                                                              total = (paper1 + paper2)/2 + paper3;
                                                              compscore = total;
                                                              compscoreMap.put(s.getStudentUuid(),compscore);
                                                           
                                                              } 
                                                             

														       if(bsscore >= agriscore){
																	bestTechinical= bsscore;
																}else{
																	bestTechinical = agriscore;
																}
																
																
																if(bestTechinical <= hscscore){
																	bestTechinical = hscscore;
																}
																if(bestTechinical <= compscore){
																	bestTechinical = compscore;
																}

       
                                                               techinicalScore = bestTechinical;
                                                              
                                                         }     

                                                    //Humanities
                                                    //Here we pick only one subject, the one the student has performed best . 
                                                    if(true){  
                                                      double bestHumanity = 0;     
                                                            if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 100
                                                              paper2 = pp.getPaperTwo(); //out of 100
                                                              total = (paper1 + paper2)/2;
                                                              geoscore = total;                                                 
                                                              geoscoreMap.put(s.getStudentUuid(),geoscore);
                                                           
                                                              }
                                                           if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 100
                                                              paper2 = pp.getPaperTwo(); //out of 100
                                                              total = (paper1 + paper2)/2;
                                                              crescore = total;
                                                              total=0;
                                                              crescorehash.put(s.getStudentUuid(),crescore);
                                                           
                                                              }
                                                           if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){
                                                              paper1 = pp.getPaperOne(); //out of 100
                                                              paper2 = pp.getPaperTwo(); //out of 100
                                                              total = (paper1 + paper2)/2;
                                                              histscore = total;                                              
                                                              histscoreMap.put(s.getStudentUuid(),histscore);
                                                           
                                                              }


                                                              if(geoscore >= crescore){
																	bestHumanity= geoscore;
																}else{
																	bestHumanity = crescore;
																}
																
																
																if(bestHumanity <= histscore){
																	bestHumanity = histscore;
																}



                                                            humanityScore = bestHumanity;

                                                        } 
                                             

                                                 }
                                                 
                                                 //out.println("scienceScore="+scienceScore);
                                                 grandscore = languageScore+scienceScore+humanityScore+techinicalScore;
                                                 //out.println("grandscore ="+grandscore+"="+languageScore+"+"+scienceScore+"+"+humanityScore+"+"+techinicalScore+"<br>");
                                                 languageScore = 0; scienceScore = 0; humanityScore = 0;techinicalScore = 0;  
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
                                                       mean = Double.parseDouble(totalz)/7;

                                                       studeadmno = studentAdmNoHash.get(uuid);
                                                       studename = studNameHash.get(uuid);         
                                                      

                                                       

                                                       if(engscorehash.get(uuid)!=null){
                                                         engscore = engscorehash.get(uuid);                                                     
                                                         engscorestr =  rf.format(engscore);

                                                       }else{
                                                         engscorestr = "";
                                                       }


                                                       if(kswscoreMap.get(uuid)!=null){
                                                         kswscore = kswscoreMap.get(uuid);
                                                         kswscorestr = rf.format(kswscore);
                                                       }else{
                                                         kswscorestr = "";
                                                       }


                                                       if(physcoreMap.get(uuid)!=null){
                                                         physcore = physcoreMap.get(uuid);
                                                         physcorestr = rf.format(physcore);
                                                       }else{
                                                         physcorestr = "";
                                                       }

                                                       if(bioscoreMap.get(uuid)!=null){
                                                         bioscore = bioscoreMap.get(uuid);
                                                         bioscorestr = rf.format(bioscore);
                                                       }else{
                                                         bioscorestr = "";
                                                       }


                                                       if(chemscorehash.get(uuid)!=null){
                                                         chemscore = chemscorehash.get(uuid);
                                                         chemscorestr = rf.format(chemscore);
                                                       }else{
                                                         chemscorestr = "";
                                                       }



                                                       if(matscorehash.get(uuid)!=null){
                                                         matscore = matscorehash.get(uuid);
                                                         matscorestr = rf.format(matscore);
                                                       }else{
                                                         matscorestr = "";
                                                       }

                                                        if(histscoreMap.get(uuid)!=null){
                                                         histscore = histscoreMap.get(uuid);
                                                         histscorestr = rf.format(histscore);
                                                       }else{
                                                         histscorestr = "";
                                                       }


                                                       if(crescorehash.get(uuid)!=null){
                                                         crescore = crescorehash.get(uuid);
                                                         crescorestr = rf.format(crescore);
                                                       }else{
                                                         crescorestr = "";
                                                       }


                                                       if(geoscoreMap.get(uuid)!=null){
                                                         geoscore = geoscoreMap.get(uuid);
                                                         geoscorestr = rf.format(geoscore);
                                                       }else{
                                                         geoscorestr = "";
                                                       }


                                                       if(bsscoreMap.get(uuid)!=null){
                                                         bsscore = bsscoreMap.get(uuid);
                                                         bsscorestr = rf.format(bsscore);
                                                       }else{
                                                         bsscorestr = "";
                                                       }


                                                       if(agriscorehash.get(uuid)!=null){
                                                         agriscore = agriscorehash.get(uuid);
                                                         agriscorestr = rf.format(agriscore);
                                                       }else{
                                                         agriscorestr = "";
                                                       }

                                                       if(hscscoreMap.get(uuid)!=null){
                                                         hscscore = hscscoreMap.get(uuid);
                                                         hscscorestr = rf.format(hscscore);
                                                       }else{
                                                         hscscorestr = "";
                                                       }

                                                       if(compscoreMap.get(uuid)!=null){
                                                         compscore = compscoreMap.get(uuid);
                                                         compscorestr = rf.format(compscore);
                                                       }else{
                                                         compscorestr = "";
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
                                        out.println("<td class=\"center\">" + hscscorestr + "</td>");
                                        out.println("<td class=\"center\">" + compscorestr + "</td>");
              
                                        out.println("<td class=\"center\">" +df.format(Double.parseDouble(totalz)) + "</td>");
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
