<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.persistence.student.HouseDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.House"%>

<%@page import="com.yahoo.petermwenda83.persistence.student.StudentHouseDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.student.StudentHouse"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>


<%@page import="org.apache.commons.lang3.StringUtils"%>


<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>


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

    HouseDAO houseDAO = HouseDAO.getInstance();
    List<House> houseList = new ArrayList<House>();
    houseList = houseDAO.getHouseList(accountuuid);

    StudentHouseDAO studentHouseDAO = StudentHouseDAO.getInstance();
    List<StudentHouse> studenthouseList = new ArrayList<StudentHouse>();


    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../schoolLogout");
   
   
     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     

 %>






<jsp:include page="header.jsp" />




<div class="row-fluid sortable">



               



    <div class="box span12">
        <div class="box-header well" data-original-title>
   <p>[<a href="schoolIndex.jsp">Back</a>]  <%=schoolname%> :Student House Registration Panel: TERM <%=examConfig.getTerm()%>:<%=examConfig.getYear()%> </p>
        </div>
        <div class="box-content">
          
               <%
                    HashMap<String, String> paramHash = (HashMap<String, String>) session.getAttribute(SessionConstants.HOUSE_PARAM);

                        if (paramHash == null) {
                             paramHash = new HashMap<String, String>();
                            }
                             

                                String findErrStr = "";
                                String findSuccessStr = "";
                                String addError = "";
                                String addsuccess = "";
                                session = request.getSession(false);

                                     findErrStr = (String) session.getAttribute(SessionConstants.STUDENT_FIND_ERROR);
                                     findSuccessStr = (String) session.getAttribute(SessionConstants.STUDENT_FIND_SUCCESS); 
                                     addError = (String) session.getAttribute(SessionConstants.HOUSE_ADD_ERROR);
                                     addsuccess = (String) session.getAttribute(SessionConstants.HOUSE_ADD_SUCCESS); 

                                if (StringUtils.isNotEmpty(findErrStr)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + findErrStr);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.STUDENT_FIND_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(findSuccessStr)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + findSuccessStr);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.STUDENT_FIND_SUCCESS, null);
                                  } 

                                   if (StringUtils.isNotEmpty(addError)) {
                                    out.println("<p style='color:red;'>");                 
                                    out.println("error: " + addError);
                                    out.println("</p>");                                 
                                    session.setAttribute(SessionConstants.HOUSE_ADD_ERROR, null);
                                  } 
                                   else if (StringUtils.isNotEmpty(addsuccess)) {
                                    out.println("<p style='color:green;'>");                                 
                                    out.println("success: " + addsuccess);
                                    out.println("</p>");                                   
                                    session.setAttribute(SessionConstants.HOUSE_ADD_SUCCESS, null);
                                  } 


                           String admNumber =""; 
                           String firstname ="";
                           String lastname =""; 
                           String surname ="";  
                          if(StringUtils.isEmpty(paramHash.get("admNumber"))){
                           admNumber = " ";
                          }else{
                           admNumber = paramHash.get("admNumber"); 
                           }

                           if(StringUtils.isEmpty(paramHash.get("firstname"))){
                           firstname = " ";
                          }else{
                           firstname = paramHash.get("firstname"); 
                           }


                           if(StringUtils.isEmpty(paramHash.get("lastname"))){
                           lastname = " ";
                          }else{
                           lastname = paramHash.get("lastname"); 
                           }


                           if(StringUtils.isEmpty(paramHash.get("surname"))){
                           surname = " ";
                          }else{
                           surname = paramHash.get("surname"); 
                           }



                     %>



              <form  class="form-horizontal"   action="findStudentH" method="POST" >
              <fieldset>
                            <div class="control-group">
                                <label class="control-label" for="name">Admission Number:</label>
                                <div class="controls">
                                    <input class="input-xlarge focused" id="receiver" type="text" name="AdmNo" 
                                    value=""  >
                                </div>
                            </div> <!--end of form find-->
                         
                            <!--submit form -->  
                            <div class="form-actions">
                                <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                <button type="submit" name="Find" value="Find"   class="btn btn-primary">Find</button> 
                            </div>

              </fieldset>
              </form>


            
            <form  class="form-horizontal" action="" method="POST" >
            <fieldset>


                              <div class="control-group">
                                  <label class="control-label" for="name">Admission Number:</label>
                                  <div class="controls">
                                   <input class="input-xlarge focused" id="receiver" type="text" name="admnumber" 
                                      value="<%=admNumber%>" readonly >

                                  </div>
                              </div>  


                               <div class="control-group">
                                  <label class="control-label" for="name">FirstName:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="firstname" 
                                       value="<%=firstname%>" readonly >                                    
                                  </div>
                              </div> 


                              <div class="control-group">
                                  <label class="control-label" for="name">LastName:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="lastname"
                                        value="<%=lastname%>"  readonly>
                                  </div>
                              </div>

                              <div class="control-group">
                                  <label class="control-label" for="name">SurName:</label>
                                  <div class="controls">
                                      <input class="input-xlarge focused" id="receiver" type="text" name="surname"
                                        value="<%=surname%>" readonly >
                                  </div>
                              </div>
                  </fieldset>
                  </form>


                 <form  class="form-horizontal"   action="addStudentHouse" method="POST" >
                 <fieldset>
                    
                           
                                      <h3><i class="icon-edit"></i>House :</h3> 
                                     

                                     <div class="control-group">
                                        <label class="control-label" for="name">House*:</label>
                                        <div class="controls">
                                         <select name="house" >
                                           <option value="">Please select one</option> 
                                                 <%
                                                    int count = 1;
                                                    if (houseList != null) {
                                                        for (House hs : houseList) {
                                                          studenthouseList = studentHouseDAO.getHouseList(hs.getUuid()); 
                                                           int STUDENT_COUNT = 0;
                                                           for(StudentHouse sh : studenthouseList){
                                                              STUDENT_COUNT++;
                                                              }
                                                              String displays = "";
                                                             
                                                              if(STUDENT_COUNT <=1){
                                                                  displays = "Student";
                                                              }else{
                                                                   displays = "Students";
                                                              }
                                                %>
                                                <option value="<%=hs.getUuid()%>"> <%=hs.getHouseName()+" (" + STUDENT_COUNT+" "+displays+ ")"%> </option>
                                                <%
                                                            count++;
                                                            STUDENT_COUNT = 0;
                                                        }
                                                    }
                                                %>
                                          </select>   
                                        </div>
                                    </div> 

                                    
                                    
                                    <div class="form-actions">
                                        <input type="hidden" name="studentUuid" value="<%=StringUtils.trimToEmpty(paramHash.get("studentuuid"))%>">
                                        <input type="hidden" name="schooluuid" value="<%=accountuuid%>">
                                        <input type="hidden" name="systemuser" value="<%=staffUsername%>">
                                        <button type="submit" class="btn btn-primary">Register</button>
                                    </div> 

              </fieldset>
              </form>
       


    </div>
     </div>

</div>


<jsp:include page="footer.jsp" />
