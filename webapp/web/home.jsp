

<%//@page import="com.yahoo.petermwenda83.persistence.registration.StudentDAO"%>
<%//@page import="com.yahoo.petermwenda83.persistence.registration.PrimaryDAO"%>

<%//@page import="com.yahoo.petermwenda83.bean.student.Student"%>
<%//@page import="com.yahoo.petermwenda83.bean.student.Primary"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<body>

<%
 /*StudentDAO stuDAO = StudentDAO.getInstance();
 List<Student> studntList = new ArrayList(); 
 studntList = stuDAO.getAllStudents();

  PrimaryDAO priDAO = PrimaryDAO.getInstance();
  List<Primary> pList = new ArrayList(); 
  pList = priDAO.getAllPrimary();*/

%>




 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   
    <div id="tooplate_middle">



       <!-- <p>Students Primary School Details</p>
        
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>School Name</th>
                        <th>Index</th>                
                        <th>K.C.P.E Year</th>
                        <th>K.C.P.E Mark</th>
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      /*  int count = 1;
                       for (Primary p : pList) {
                       
                    %>
                    <tr>
                        <td width="10%"><%=count%></td>
                         <td class="center"><%=p.getSchoolname()%></td> 
                         <td class="center"><%=p.getIndex()%></td>
                         <td class="center"><%=p.getKcpeyear()%></td>
                        <td class="center"><%=p.getKcpemarks()%></td>                       
                    </tr>

                    <%
                           count++;
                            } */
                    %>
                </tbody>
            </table>  -->







     
        <div id="middle_left">
            <h3>School Panel</h3>
           
        </div>
      






       

	</div>
	
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>