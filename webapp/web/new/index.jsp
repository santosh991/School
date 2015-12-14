<%
%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

%> 
<jsp:include page="header.jsp" />


<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">Home</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">Accounts</a>
        </li>
    </ul>
</div>

<div class="row-fluid sortable">		
    <div class="box span12">
        <div class="box-header well" data-original-title>
          <!--  <a class="btn" href="accounts.jsp" title="view accounts" data-rel="tooltip">View</a>  -->                
            <a class="btn" href="addaccount.jsp" title="add accounts" data-rel="tooltip">Add</a>  
            <div class="box-icon">
                <a class="btn" href="#" title="refresh page" data-rel="tooltip"><i class="icon-refresh"></i> </a>                  
                <a class="btn" href="#" title="delete message" data-rel="tooltip"><i class="icon-trash"></i></a> 
                <a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            
            


            <div>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>Admission Number</th>
                        <th>Full name</th>                
                        <th>Class</th>
                        <th>Fee Balance</th>
                        <th>action</th>
                    </tr>
                </thead>   
                <tbody>
                    <%
                    %>
                    <tr>
                        <td width="10%"><%//=count%></td>
                         <td class="center"><%//=code.getUsername()%></td> 
                         <td class="center"><%//=accountstatusHash.get(code.getStatusuuid())%></td>
                         <td class="center"><%//=url%></td>
                        <td class="center"><%//=code.getMobile()%></td>						
                        <td class="center">
                                                   
                        </td>      


                    </tr>

                    <%
                            
                    %>
                </tbody>
            </table>  
            </div>

       <br>   <br>   <br>




        </div>
    </div><!--/span-->

</div><!--/row-->


<jsp:include page="footer.jsp" />
