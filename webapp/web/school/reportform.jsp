
<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.Perfomance"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.StudentExamDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.StudentExam"%>

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

<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%
      final String SCH_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";


      final String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
      final String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
      final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";

      final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
      final String FORM_ONE_E ="A41016A0-2848-4969-8197-878D1D9687D6";         



     HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
     HashMap<String, String> studNameHash = new HashMap<String, String>();

     StudentDAO studentDAO = StudentDAO.getInstance();
     List<Student> studentList = new ArrayList<Student>(); 
     studentList = studentDAO.getAllStudents(SCH_UUID,FORM_ONE_N);
    
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

     perfomanceList = perfomanceDAO.getPerfomanceList(SCH_UUID,FORM_ONE_N);


     StudentExamDAO studentExamDAO = StudentExamDAO.getInstance();
     List<StudentExam> sutexamList = new ArrayList<StudentExam>(); 
     sutexamList = studentExamDAO.getStudentExamList(SCH_UUID,FORM_ONE_N); 
     


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
                                        

 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">




    <div class="box span12">
        <div class="box-header well" data-original-title>
          <p>Personal exam report form</p>
        </div>
        <div class="box-content">
            
            


            <div>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        
                        <th>ADMNO </th>
                        <th>FULL NAME </th>
                        <th>ENG</th>                
                        <th>KIS</th>
                        <th>PHY</th>                      
                        <th>POS </th>
                        <th>MEAN </th>
                        <th>GRADE </th>         
                        
                    </tr>
                </thead>   
                <tbody>

                    <%                 
                             
                             
                                        
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
                                                       engscoreHash.put(student.getStudentUuid(),engscore); 
                                                       
                                                  }

                                                  if(StringUtils.equals(KISWA_UUID,p.getSubjectUuid())){
                                                       
                                                       kswscore = examcattotal;
                                                       kiswscoreHash.put(student.getStudentUuid(),kswscore); 
                                                       
                                                  }

                                                  if(StringUtils.equals(PHY_UUID,p.getSubjectUuid())){
                                                         physcore = examcattotal;
                                                         physcoreHash.put(student.getStudentUuid(),physcore); 
                                                             
                                                       
                                                       }
  
                                                      
                                              }
                                                  
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
                                        out.println("<td width=\"3%\" >" + count + "</td>");
                                        out.println("<td class=\"center\" >" + studeadmno + "</td>");
                                        out.println("<td class=\"center\" >" + studename + "</td>");
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
            </div>

       


    </div>

</div>


<jsp:include page="footer.jsp" />
