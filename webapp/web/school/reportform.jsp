
<%@page import="com.yahoo.petermwenda83.persistence.student.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.Perfomance"%>

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

    

     PerfomanceDAO perfomanceDAO = PerfomanceDAO.getInstance();
     List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
     List<Perfomance> pDistinctList = new ArrayList<Perfomance>();
     pDistinctList = perfomanceDAO.getPerfomanceListDistinct(SCH_UUID, FORM_ONE_N);

    
    

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
          
                    <%                 
                             
                             
                         perfomanceList = getPerformance.getPerfomanceList(SCH_UUID,FORM_ONE_N,studentuuid);               
                         

                    %>
            
            </div>

       


    </div>

</div>


<jsp:include page="footer.jsp" />
