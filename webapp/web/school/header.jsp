<!DOCTYPE html>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>

<%@page import="com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.staff.ClassTeacher"%>
<%@page import="com.yahoo.petermwenda83.bean.classroom.ClassRoom"%>
<%@page import="com.yahoo.petermwenda83.persistence.classroom.RoomDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>

<%@page import="com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO"%>
<%@page import="com.yahoo.petermwenda83.bean.exam.ExamConfig"%>

<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>
<%@page import="com.yahoo.petermwenda83.server.servlet.util.PropertiesConfig"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%   

    CacheManager mgr = CacheManager.getInstance();
    Cache accountsCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
    Cache statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_SCHOOL_ACCOUNT);
    SessionStatistics statistics = new SessionStatistics();
    String username = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
    

    SchoolAccount school = new SchoolAccount();
    Element element;
   

    String classuuid = "";
    String room  ="";
    String staffPosition  ="";

    if ((element = accountsCache.get(username)) != null) {
        school = (SchoolAccount) element.getObjectValue();
    }

     String accountuuid = school.getUuid();

    ExamConfigDAO examConfigDAO = ExamConfigDAO.getInstance();
    ExamConfig examConfig = examConfigDAO.getExamConfig(accountuuid);

     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
     staffPosition = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_POSITION);

     ClassTeacherDAO classTeacherDAO = ClassTeacherDAO.getInstance();
     RoomDAO roomDAO = RoomDAO.getInstance();
     
     if(stffID !=null){  
     ClassTeacher ct = classTeacherDAO.getClassTeacher(stffID); 
       if(ct !=null){
       classuuid = ct.getClassRoomUuid();
          }
              }

     
     ClassRoom cr = roomDAO.getroom(accountuuid, classuuid);
      if(cr !=null){
      room = cr.getRoomName(); 
       }
    final String FORM1 = "FORM 1";
	  final String FORM2 = "FORM 2";
	  final String FORM3 = "FORM 3";
	  final String FORM4 = "FORM 4";

	//out.println("pos"+staffPosition);
	        String pos_Pricipal =(String)  PropertiesConfig.getConfigValue("POSITION_PRINCIPAL");
            String pos_DeputyPricipal =(String)  PropertiesConfig.getConfigValue("POSITION_DEPUTY");
            String pos_Teacher =(String) PropertiesConfig.getConfigValue("POSITION_TEACHER");
            String pos_HOD =(String) PropertiesConfig.getConfigValue("POSITION_HOD");
            String pos_CM =(String) PropertiesConfig.getConfigValue("POSITION_CM");
            String pos_Secretary =(String) PropertiesConfig.getConfigValue("POSITION_SECRETARY");
            String pos_Bursar =(String) PropertiesConfig.getConfigValue("POSITION_BURSAR");

%>                       

<html lang="en">
    <head>
        
        <meta charset="utf-8">
        <title>Fastech School Management System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content=".">
        <meta name="author" content="Peter Mwenda" >
        
         <script src="../js/jquery/jquery-1.8.2.min.js"></script>  
         <script src="../js/jquery/jquery-1.7.2.min.js"></script>
         <script src="../js/searchstudent.js"></script>
         
         
        <link href="../css/bootstrap/bootstrap-cerulean.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-bottom: 40px;
            }
            .sidebar-nav {
                padding: 9px 0;
            }
        </style>
        <!-- jQuery -->	
       
	
      
        
        <link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet">
        <link href="../css/fastech/charisma-app.css" rel="stylesheet">
        <link href="../css/jquery/jquery-ui-1.8.21.custom.css" rel="stylesheet">
        <link href='../css/fastech/fullcalendar.css' rel='stylesheet'>
        <link href='../css/fastech/fullcalendar.print.css' rel='stylesheet'  media='print'>
        <link href='../css/fastech/chosen.css' rel='stylesheet'>
        <link href='../css/fastech/uniform.default.css' rel='stylesheet'>
        <link href='../css/fastech/colorbox.css' rel='stylesheet'>
        <link href='../css/jquery/jquery.cleditor.css' rel='stylesheet'>
        <link href='../css/jquery/jquery.noty.css' rel='stylesheet'>
        <link href='../css/fastech/noty_theme_default.css' rel='stylesheet'>
        <link href='../css/fastech/elfinder.min.css' rel='stylesheet'>
        <link href='../css/fastech/elfinder.theme.css' rel='stylesheet'>
        <link href='../css/jquery/jquery.iphone.toggle.css' rel='stylesheet'>
        <link href='../css/fastech/opa-icons.css' rel='stylesheet'>
        <link href='../css/fastech/uploadify.css' rel='stylesheet'>
        <link href='../css/fastech/template.css' rel='stylesheet'>
        <link href='../css/fastech/checkpass.css' rel='stylesheet'>
        <link href='../css/fastech/styles.css' rel='stylesheet'>


        
        
        <!-- The fav icon -->
        <link rel="shortcut icon" href="img/favicon.ico">
        
       
  
	
        
   


    </head>

    <body>

        <!-- topbar starts -->
        <div class="navbar">
            <div class="navbar-inner">

                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                 
                    
		    <!-- user dropdown starts -->
                    <div class="btn-group pull-right" >
                         <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user"></i><span class="hidden-phone"><%=staffUsername%></span>
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a href="#">Profile</a></li>
                            <li class="divider"></li>
                            <li><a href="../schoolLogout">Logout</a></li>
                            </li>

                        </ul>


                    </div>

                    <!-- user dropdown ends -->
                    
                    
                    
                    
                    
                    
                </div>
                            
                       <!-- top menu -->        
                <div id='cssmenu'>
               <ul>

                <!--SECRETARY-->  

                  <%  if(StringUtils.equals(staffPosition,pos_Secretary)){ %>
                <li class='has-sub' ><a href="studentIndex.jsp"><span>Students</span></a>
                  <ul>
                <li><a href="addStudent.jsp" ><span>Basic details</span></a></li>
                <li><a href="addParent.jsp" ><span>Parent Details</span></a></li>
                <li><a href="addHouse.jsp" ><span>Student House</span></a></li>
                <li class='last'><a href="addSponsor.jsp"><span>Student Sponsor</span></a></li>
                </ul>
                </li>
                 <%}  %>

               <!--BURSAR/ACCOUNT C-->
                
                  <%  if(StringUtils.equals(staffPosition,pos_Bursar)){ %>
                <li class='has-sub' ><a href="fee.jsp"><span>Fee Management</span></a>  </li>
                <li class='has-sub' ><a href="pocketM.jsp" ><span>Pocket Money</span></a></li>
              
                 <%}  %>

               <!--CLASS TEACHER-->
             
                <%
               if(StringUtils.contains(room, FORM1) || StringUtils.contains(room, FORM2)){ 
                 %>
		       <li class='has-sub'><a href="exam.jsp"><span>My Class</span></a></li>
		        <%
	            }else if(StringUtils.contains(room, FORM3) || StringUtils.contains(room, FORM4)){
	            %>
	            <li class='has-sub'><a href="examIndex.jsp"><span>My Class</span></a></li>
		        <%
	           }else{
	          
		       response.sendRedirect("../index.jsp");
	           }
   
               %>
           
            <!--TEACHER-->

             <%  if(StringUtils.equals(staffPosition,pos_Teacher)){ %>

             <% if(StringUtils.equalsIgnoreCase(examConfig.getExamMode(),"ON")){ %>
            <li class='has-sub'><a href="examUpload.jsp"><span>Upload Exam</span></a></li>
             <%} %>
            <li class='has-sub'><a href="mySubjects.jsp"><span>MySubjects</span></a></li>
          
             <% }  %>


            <!--CM-CURRICULUM MASTER-->

             <%  if(StringUtils.equals(staffPosition,pos_CM)){ %>
              <li class='has-sub'><a href="reports.jsp"><span>Reports</span></a></li>
              <li class='has-sub'><a href='#'><span>Subjects</span></a></li>
             <% }  %>
            
             <!--HOD-->

              <%  if(StringUtils.equals(staffPosition,pos_HOD)){ %>
              <li class='has-sub'><a href="reports.jsp"><span>Reports</span></a></li>
              <li class='has-sub'><a href='#'><span>Subjects</span></a></li>
             <% }  %>


             <!--DEPUTY PRINCIPAL-->

              <%  if(StringUtils.equals(staffPosition,pos_DeputyPricipal)){ %>
              <li class='has-sub' ><a href="studentIndex.jsp"><span>Students</span></a>
                  <ul>
                <li><a href="addStudent.jsp" ><span>Basic details</span></a></li>
                <li><a href="addParent.jsp" ><span>Parent Details</span></a></li>
                 <li><a href="addHouse.jsp" ><span>Student House</span></a></li>
                <li class='last'><a href="addSponsor.jsp"><span>Student Sponsor</span></a></li>
                </ul>
                </li>
            <li class='has-sub'><a href="mySubjects.jsp"><span>MySubjects</span></a></li>
            <% if(StringUtils.equalsIgnoreCase(examConfig.getExamMode(),"ON")){ %>
            <li class='has-sub'><a href="examUpload.jsp"><span>Upload Exam</span></a></li>
             <%} %>
            <li class='has-sub'><a href="reports.jsp"><span>Reports</span></a></li>
            <li class='has-sub'><a href="examConfig.jsp"><span>ExamConfig</span></a></li>
            <li class='has-sub'><a href="staff.jsp"><span>Staff Management </span></a></li>
            <li class='has-sub'><a href="classTeachers.jsp"><span>Class Teachers </span></a></li>
             <% }  %>

             <!--PRINCIPAL-->

              <%  if(StringUtils.equals(staffPosition,pos_Pricipal)){ %>
                 <li class='has-sub' ><a href="studentIndex.jsp"><span>Students</span></a>
                  <ul>
                <li><a href="addStudent.jsp" ><span>Basic details</span></a></li>
                <li><a href="addParent.jsp" ><span>Parent Details</span></a></li>
                 <li><a href="addHouse.jsp" ><span>Student House</span></a></li>
                <li class='last'><a href="addSponsor.jsp"><span>Student Sponsor</span></a></li>
                </ul>
                </li>
            <li class='has-sub'><a href="mySubjects.jsp"><span>MySubjects</span></a></li>
            <% if(StringUtils.equalsIgnoreCase(examConfig.getExamMode(),"ON")){ %>
            <li class='has-sub'><a href="examUpload.jsp"><span>Upload Exam</span></a></li>
             <%} %>
            <li class='has-sub'><a href="reports.jsp"><span>Reports</span></a></li>
            <li class='has-sub'><a href="examConfig.jsp"><span>ExamConfig</span></a></li>
            <li class='has-sub'><a href="staff.jsp"><span>Staff Management </span></a></li>
            <li class='has-sub'><a href="classTeachers.jsp"><span>Class Teachers </span></a></li>
            

             <% }  %>
        </ul>

            </div>             

            </div>

        </div>
        <!-- topbar ends -->

        <div class="container-fluid">
            <div class="row-fluid">


                <!-- left menu starts -->
                <div class="span2 main-menu-span">
                    <div class="well nav-collapse sidebar-nav">
                        <ul class="nav nav-tabs nav-stacked main-menu">
                            <!--menu to change depending on page requested-->
                            <li class="nav-header hidden-tablet">Main</li>                                                     


                            <li><a class="ajax-link" href="schoolIndex.jsp"><i class="icon-home"></i><span class="hidden-tablet">Home</span></a></li>
                              <%  if(StringUtils.equals(staffPosition,pos_Pricipal)){ %>
                            <li><a class="ajax-link" href="parents.jsp"><i class="icon-trash"></i><span class="hidden-tablet">Parents</span></a></li>
                            <li><a class="ajax-link" href="studentSponsor.jsp"><i class="icon-envelope"></i><span class="hidden-tablet">Sponsor</span></a></li>
                            <li><a class="ajax-link" href="studentHouse.jsp"><i class="icon-edit"></i><span class="hidden-tablet">House</span></a></li>
                           
                           
                           <li class="nav-header hidden-tablet">My School</li> 
                           
                            <li><a class="ajax-link" href="#"><i class="icon-home"></i><span class="hidden-tablet">Notices</span></a></li>
                            <li><a class="ajax-link" href="#"><i class="icon-envelope"></i><span class="hidden-tablet">Payments</span></a></li>
                             <% }  %>
                        </ul>
                        <!--<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>-->
                    </div><!--/.well -->
                </div><!--/span-->
                <!-- left menu ends -->

                <noscript>
                <div class="alert alert-block span10">
                    <h4 class="alert-heading">Warning!</h4>
                    <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
                </div>
                </noscript>

                <div id="content" class="span10">
                    <!-- content starts -->

