

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.net.URLEncoder"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>

<%@page import="org.joda.time.MutableDateTime"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%


  if (session == null) {
        response.sendRedirect("../index.jsp");
    }

    String username = (String) session.getAttribute(SessionConstants.ACCOUNT_SIGN_IN_KEY);
    if (StringUtils.isEmpty(username)) {
        response.sendRedirect("../index.jsp");
    }

    session.setMaxInactiveInterval(SessionConstants.SESSION_TIMEOUT);
    response.setHeader("Refresh", SessionConstants.SESSION_TIMEOUT + "; url=../logout");

    CacheManager mgr = CacheManager.getInstance();
    Cache accountsCache = mgr.getCache(CacheVariables.CACHE_ACCOUNTS_BY_USERNAME);
    Cache statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_ACCOUNT);
    SessionStatistics statistics = new SessionStatistics();
     
    


    SchoolAccount school = new SchoolAccount();
    Element element;
    

   

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

    String accountuuid = school.getUuid();

    if ((element = statisticsCache.get(accountuuid)) != null) {
        statistics = (SessionStatistics) element.getObjectValue();
    }

   

     


       
%>





<div id="tooplate_wrapper">

	<jsp:include page="header.jsp" />
    
 
  <div id="container" class="clear"> 
   
    <div id="homepage" class="clear">  
     <h3><i class="icon-edit"></i> Student Registration Form: Basic Details</h3>            
      
        
         <div class="box-content">
            <form  class="form-horizontal"   action="../addStudent" method="POST" >
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="name">Student FirtName*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentfirstname"  value="" >

                        </div>

                    </div>  

                    <div class="control-group">
                        <label class="control-label" for="name">Student SurName*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentsurname" value="" >                                    
                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Student LastName</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentlasname" value="" >

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Student AdmNo*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentadmno" value="" >

                        </div>

                    </div> 

                     <div class="control-group">
                        <label class="control-label" for="name">Student Gender*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="radio" name="studentgender" value="male" checked>Male <br> 
                            <input class="input-xlarge focused" id="receiver" type="radio" name="studentgender" value="female">Female 

                        </div>

                    </div> 

                           
                            
                      
                      <div class="control-group">
                        <label class="control-label" for="name">Student DOB*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentdob" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Birth Certificate No*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="bcertno" 
                                   value="">

                        </div>

                    </div> 

           
      
                           <div class="control-group">
                             <label class="control-label" for="name">Assign House*</label>
                           <div class="controls">
                           <select name="studenthouse"> Assign House
                           <option value="">Please select one</option> 
                            <option value="Principal">Kirima Mbogo</option>
                            <option value="HOD">Suswa</option>
                            <option value="Teacher">Mt Kenya</option>
                            <option value="Secretary">Kirima Njaro</option>
                             <option value="Account Clerk">Longonot</option>

                           </select>
                            </div>
                           </div>

                           

                    <div class="control-group">
                        <label class="control-label" for="name">Student County*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentcounty" 
                                   value="">

                        </div>

                    </div> 



                    <div class="control-group">
                        <label class="control-label" for="name">Student Location*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentlocation" 
                                   value="">

                        </div>

                    </div> 



                    <div class="control-group">
                        <label class="control-label" for="name">Student SubLocation*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="studentsubLocation" 
                                   value="">

                        </div>

                    </div> 


                   <h3><i class="icon-edit"></i>Primary School Details:</h3>    


                     
                    <div class="control-group">
                        <label class="control-label" for="name">School Name*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="prischoolname" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">K.C.P.E Index*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="kcpeindex" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">K.C.P.E Year*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="kcpeyear" 
                                   value="">

                        </div>

                    </div> 



                    <div class="control-group">
                        <label class="control-label" for="name">K.C.P.E Marsk*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="kcpemark" 
                                   value="">

                        </div>

                    </div> 

                    
                   
                   <h3><i class="icon-edit"></i>Student Parents Details:</h3>    



                  <div class="control-group">
                        <label class="control-label" for="name">Father Full Name*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="fathername" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Father Phone*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="fatherphone" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Father Occupation*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="fatheroccupation" 
                                   value="">

                        </div>

                    </div> 



                    <div class="control-group">
                        <label class="control-label" for="name">Father NationalID*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="fatherid" 
                                   value="">

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Father Email*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="fatheremail" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Mother Full Name*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="mothername" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Mother Phone*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="motherphone" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Mother Occupation*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="motheroccupation" 
                                   value="">

                        </div>

                    </div> 



                    <div class="control-group">
                        <label class="control-label" for="name">Mother NationalID*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="motherid" 
                                   value="">

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Mother Email*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="motheremail" 
                                   value="">

                        </div>

                    </div> 

                      <h3><i class="icon-edit"></i>Student Relative(Family Member) Details:</h3>  


                      
                    <div class="control-group">
                        <label class="control-label" for="name">Relative Name*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="relativename" 
                                   value="">

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Relative Phone*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="relavivephone" 
                                   value="">

                        </div>

                    </div> 
                      



                    <div class="control-group">
                        <label class="control-label" for="name">Relative NationalID*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="relaviveid" 
                                   value="">

                        </div>

                    </div> 






                       <h3><i class="icon-edit"></i>Student Sponsor Details:</h3>  


                      
                    <div class="control-group">
                        <label class="control-label" for="name">Sponsor Full Name*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="sponsorname" 
                                   value="">

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Sponsor Phone*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="sponsorphone" 
                                   value="">

                        </div>

                    </div> 



                     
                    <div class="control-group">
                        <label class="control-label" for="name">Sponsor ID/VISA No*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="sponsorid" 
                                   value="">

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Sponsor Occupation*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="sponsoroccupation" 
                                   value="">

                        </div>

                    </div> 


                    <div class="control-group">
                        <label class="control-label" for="name">Sponsor County*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="sponsorcounty" 
                                   value="">

                        </div>

                    </div> 

                    <div class="control-group">
                        <label class="control-label" for="name">Sponsor Country*</label>
                        <div class="controls">
                            <input class="input-xlarge focused" id="receiver" type="text" name="sponsorcountry" 
                                   value="">

                        </div>

                    </div> 















                    <div class="form-actions">
                        <button type="submit" name="Send" value="Send"   class="btn btn-primary">Add</button>
                    </div>
                            
                </fieldset>
            </form>


       
    </div><!--/span-->








    
             
         
    </div>
        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
   <jsp:include page="footer.jsp" />
