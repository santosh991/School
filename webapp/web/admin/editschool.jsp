

<body>
 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   <div id="tooplate_middle">

 <div class="box-content">
 <div class="row-fluid sortable">		
    <div class="box span12">

            <form class="form-horizontal" action="editSchool" method="POST"  >
                <fieldset>

                    <div class="control-group">
                        <label class="control-label" for="schoolname">schoolname</label>
                        <div class="controls">
                            <input class="input-xlarge"   name="schoolname" type="text" value="<%=request.getParameter("schoolname")%>">
                        </div>
                    </div>
                        
                         <div class="control-group">
                        <label class="control-label" for="username">username</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="name" type="text" value="<%=request.getParameter("username")%>">
                        </div>
                    </div>
                    
                    <div class="control-group">
                        <label class="control-label" for="mobile">mobile</label>
                        <div class="controls">
                            <input class="input-xlarge focused"   name="mobile" type="text" value="<%=request.getParameter("mobile")%>">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">email</label>
                        <div class="controls">
                            <input class="input-xlarge focused"  name="email" type="text" value="<%=request.getParameter("email")%>">
                        </div>
                    </div>

                   
                    <div class="form-actions">
                        <input type="hidden" name="schooluuid" value="<%=request.getParameter("schooluuid")%>">
                        <button type="submit" class="btn btn-primary">Save changes</button>
                        <a href="home.jsp"><button class="btn">Cancel</button></a>
                    </div>

                </fieldset>
            </form>

        </div>



    </div><!--/span-->

</div><!--/row-->


        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>