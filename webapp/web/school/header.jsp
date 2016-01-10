<!DOCTYPE html>

<%@page import="com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.cache.CacheVariables"%>


<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="net.sf.ehcache.Element"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.CacheManager"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

     String staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
     String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);

     

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
            <li class='has-sub' ><a href="#"><span>Student Management</span></a>
                  <ul>
                <li><a href="#" ><span>Add Student</span></a></li>
                <li><a href="#" ><span>Update Student</span></a></li>
                <li class='last'><a href="#"><span>View Student</span></a></li>
                </ul>
               </li>
            <li class='has-sub'><a href="exam.jsp"><span>Exam Management</span></a></li>
            <li class='has-sub'><a href='#'><span>Reports Management</span></a></li>
            <li class='has-sub'><a href='#'><span>Staff Management </span></a></li>
            <li class='has-sub'><a href='#'><span>Subjects</span></a></li>
            <li class='has-sub'><a href='#'><span>Admin Settings</span></a></li>

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
                            <li><a class="ajax-link" href="#"><i class="icon-envelope"></i><span class="hidden-tablet">Subjects</span></a></li>
                            <li><a class="ajax-link" href="#"><i class="icon-edit"></i><span class="hidden-tablet">House</span></a></li>
                            <li><a class="ajax-link" href="#"><i class="icon-trash"></i><span class="hidden-tablet">Games</span></a></li>
                           
                           <li class="nav-header hidden-tablet">My School</li> 
                           
                            <li><a class="ajax-link" href="#"><i class="icon-home"></i><span class="hidden-tablet">Notices</span></a></li>
                            <li><a class="ajax-link" href="#"><i class="icon-envelope"></i><span class="hidden-tablet">Payments</span></a></li>
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

