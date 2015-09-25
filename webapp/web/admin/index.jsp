

<%@page import="com.yahoo.petermwenda83.persistence.registration.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.persistence.registration.PrimaryDAO"%>

<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Primary"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<body>

<%
 StudentDAO stuDAO = StudentDAO.getInstance();
 List<Student> studntList = new ArrayList(); 
 studntList = stuDAO.getAllStudents();

  PrimaryDAO priDAO = PrimaryDAO.getInstance();
  List<Primary> pList = new ArrayList(); 
  pList = priDAO.getAllPrimary();

%>




 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   
    <div id="tooplate_middle">

     
        <div id="middle_left">
            <h3>Admin Panel</h3>
           
        </div>

	</div>
	
    <div id="tooplate_main_top"></div>        
    <div id="tooplate_main">
        
    </div> <!-- end of main -->
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>