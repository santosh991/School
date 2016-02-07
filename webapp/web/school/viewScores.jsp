<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.Perfomance"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>

<%@page import="com.yahoo.petermwenda83.persistence.staff.StaffDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.staff.Staff"%>

<%@page import="com.yahoo.petermwenda83.persistence.classroom.RoomDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.classroom.ClassRoom"%>

<%@page import="com.yahoo.petermwenda83.persistence.subject.SubjectDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.subject.Subject"%>

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
    
      
      int sessiontime = SessionConstants.SESSION_TIMEOUT;
      //out.println(sessiontime);

     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     String classroomuuid = "";
     String subjectuuid = request.getParameter("subjectUuid");
     String classroomuuid2 = request.getParameter("classroomUuid");



     PerfomanceDAO perfomanceDAO = PerfomanceDAO.getInstance();
     List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
     List<Perfomance> pDistinctList = new ArrayList<Perfomance>();

     perfomanceList = perfomanceDAO.getPerfomanceList(accountuuid,classroomuuid2,examConfig.getTerm(),examConfig.getYear());
     pDistinctList = perfomanceDAO.getPerfomanceListDistinct(accountuuid, classroomuuid2,examConfig.getTerm(),examConfig.getYear());  





     HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
     HashMap<String, String> studNameHash = new HashMap<String, String>();

     StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList<Student>(); 
     studentList = studentDAO.getAllStudents(accountuuid,classroomuuid2);
    
      if(studentList !=null){
     for(Student stu : studentList){
           studentAdmNoHash.put(stu.getUuid(),stu.getAdmno());  
           studNameHash.put(stu.getUuid(),stu.getFirstname()+" "+stu.getLastname()); 
            }
        }

     HashMap<String, String> roomHash = new HashMap<String, String>();
     RoomDAO roomDAO = RoomDAO.getInstance();
     List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 

     classroomList = roomDAO.getAllRooms(accountuuid); 
      if(classroomList !=null){
      for(ClassRoom c : classroomList){
           roomHash.put(c.getUuid() , c.getRoomName());
            } 
       }

     HashMap<String, String> subjectHash = new HashMap<String, String>();
     SubjectDAO subjectDAO = SubjectDAO.getInstance();
     List<Subject> subjectList = new ArrayList<Subject>(); 

     if(subjectList !=null){
     subjectList = subjectDAO.getAllSubjects(); 
      for(Subject s : subjectList){
           subjectHash.put(s.getUuid() , s.getSubjectName());
            }
        }

    

    DecimalFormat df = new DecimalFormat("0.00"); 
    df.setRoundingMode(RoundingMode.DOWN);

     DecimalFormat df2 = new DecimalFormat("0"); 
	 df2.setRoundingMode(RoundingMode.UP);
                                        
    double cat1 = 0; 
    String cat1str = "";

    double cat2  = 0;
    String cat2str = "";

    double endterm  = 0;
    String endtermstr = "";

    double paper1  = 0;
    String paper1str = "";

    double paper2  = 0;
    String paper2str = "";

    double paper3  = 0;
    String paper3str = "";

    //techinicals
    Map<String,Double> paper1Map = new LinkedHashMap<String,Double>();
    Map<String,Double> paper2Map = new LinkedHashMap<String,Double>(); 
    Map<String,Double> paper3Map = new LinkedHashMap<String,Double>();
    Map<String,Double> cat1Map = new LinkedHashMap<String,Double>();
    Map<String,Double> cat2Map = new LinkedHashMap<String,Double>(); 
    Map<String,Double> endtermMap = new LinkedHashMap<String,Double>();

    String studeadmno = "";
    String studename = "";
 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-header well" data-original-title>
           <p>Wellcome to <%=schoolname%> :<%=subjectHash.get(subjectuuid)%> : SCORES FOR : <%=roomHash.get(classroomuuid2)%> : TERM <%=examConfig.getTerm()%>,<%=examConfig.getYear()%> </p>
           
        </div>
        <div class="box-content">
            
<div>     
<table class="table table-striped table-bordered bootstrap-datatable ">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>AdmNo</th>
                        <th>Student Name</th> 
                        <th>Cat1</th> 
                        <th>Cat2</th>                
                        <th>EndTerm</th>
                        <th>Papaer1</th>
                        <th>Paper2</th>
                        <th>Paper3</th>
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
				   int count = 1;
                   
				   List<Perfomance> list = new ArrayList<>();

                  if(studentList !=null){                 
                   for(Student s : studentList){     
                    list = perfomanceDAO.getPerformance(accountuuid, classroomuuid2, s.getUuid(),examConfig.getTerm(),examConfig.getYear());                                

                                paper1 = 0;paper2 = 0;paper3 = 0;
                                cat1 = 0;cat2 = 0;endterm = 0;

                                studeadmno = studentAdmNoHash.get(s.getUuid());
                                studename = studNameHash.get(s.getUuid()); 

                                                    
                     
                     if(list !=null){
                   for(Perfomance pp : list){

                          if(StringUtils.equals(pp.getSubjectUuid(), subjectuuid) ){


                                     paper1 = pp.getPaperOne();
                                     if(paper1==0||paper1==0.0){
                                       paper1str = " "; 
                                     }  else{
                                       paper1str = df2.format(paper1); 
                                     }                                       

                                     paper2 = pp.getPaperTwo();
                                     if(paper2==0||paper2==0.0){
                                       paper2str = " "; 
                                     }  else{
                                       paper2str = df2.format(paper2); 
                                     }    
                                     
                                     paper3 = pp.getPaperThree();
                                     if(paper3==0||paper3==0.0){
                                       paper3str = " "; 
                                     }  else{
                                       paper3str = df2.format(paper3); 
                                     }                                        
                                      
                                     cat1 = pp.getCatOne(); 
                                     if(cat1==0||cat1==0.0){
                                       cat1str = " "; 
                                     }  else{
                                       cat1str = df2.format(cat1); 
                                     }                                
                                     
                                     cat2 = pp.getCatTwo(); 
                                     if(cat2==0||cat2==0.0){
                                       cat2str = " "; 
                                     }  else{
                                       cat2str = df2.format(cat2); 
                                     }                                      

                                     endterm = pp.getEndTerm();
                                     if(endterm==0||endterm==0.0){
                                       endtermstr = " "; 
                                     }  else{
                                       endtermstr = df2.format(endterm); 
                                     } 


                                                        
                                      }
                                      
                                    }  
                                 }                                    
                        
                    %>
                    <tr>
                         <td width="3%"><%=count%></td>
                         <td class="center"><%=studeadmno%></td> 
                         <td class="center"><%=studename%></td>
                         <td class="center"><%=cat1str%></td>
                         <td class="center"><%=cat2str%></td>
                         <td class="center"><%=endtermstr%></td>
                         <td class="center"><%=paper1str%></td>  
                         <td class="center"><%=paper2str%></td>  
                         <td class="center"><%=paper3str%></td>  
                    </tr>

                    <%     
                         
                           count++;
                       }    
                     }    
                    %>
                </tbody>
            </table>  

       </div>   


    </div>

</div>


<jsp:include page="footer.jsp" />
