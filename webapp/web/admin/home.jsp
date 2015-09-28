

<%@page import="com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<body>

<%
 AccountDAO accountDAO = AccountDAO.getInstance();
 List<SchoolAccount> schoolList = new ArrayList(); 
 schoolList = accountDAO.getAllSchools();


%>




 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   
    <div id="tooplate_middle">
        
   <div id="middle_left" >               
            <a class="btn" href="addschool.jsp" title="add School account" data-rel="tooltip">Add</a>  
        </div>
        
        <div id="middle_left">
            <p>Schools Details</p>
           
        </div>


   
        
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>School Name</th>
                        <th>UserName</th>                
                        <th>Mobile</th>
                        <th>Email</th>
                        <th>actions</th>
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      int count = 1;
                       for (SchoolAccount s : schoolList) {
                       
                    %>
                    <tr>
                        <td width="10%"><%=count%></td>
                         <td class="center"><%=s.getSchoolName()%></td> 
                         <td class="center"><%=s.getUsername()%></td>
                         <td class="center"><%=s.getMobile()%></td>
                        <td class="center"><%=s.getEmail()%></td>  
                         <td class="center">
                            <form name="edit" method="post" action="editschool.jsp"> 
                                <input type="hidden" name="schoolname" value="<%=s.getSchoolName()%>">
                                <input type="hidden" name="username" value="<%=s.getUsername()%>">
                                <input type="hidden" name="mobile" value="<%=s.getMobile()%>">
                                <input type="hidden" name="email" value="<%=s.getEmail()%>">
                                <input type="hidden" name="schooluuid" value="<%=s.getUuid()%>">
                     <input class="btn btn-success" type="submit" name="edit" id="submit" value="Edit" /> 
                                </form>                          
                        </td>   
                    </tr>

                    <%
                           count++;
                            } 
                    %>
                </tbody>
            </table>  



       

	</div>
	
    <div id="tooplate_main_top"></div>        
    <div id="tooplate_main">
        
    
    	
        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>