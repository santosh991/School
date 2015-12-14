<!DOCTYPE html>
<%
 
%>

<%@page import="org.apache.commons.lang3.StringUtils"%>


<%

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
        <link href="../css/tawi/charisma-app.css" rel="stylesheet">
        <link href="../css/jquery/jquery-ui-1.8.21.custom.css" rel="stylesheet">
        <link href='../css/tawi/fullcalendar.css' rel='stylesheet'>
        <link href='../css/tawi/fullcalendar.print.css' rel='stylesheet'  media='print'>
        <link href='../css/tawi/chosen.css' rel='stylesheet'>
        <link href='../css/tawi/uniform.default.css' rel='stylesheet'>
        <link href='../css/tawi/colorbox.css' rel='stylesheet'>
        <link href='../css/jquery/jquery.cleditor.css' rel='stylesheet'>
        <link href='../css/jquery/jquery.noty.css' rel='stylesheet'>
        <link href='../css/tawi/noty_theme_default.css' rel='stylesheet'>
        <link href='../css/tawi/elfinder.min.css' rel='stylesheet'>
        <link href='../css/tawi/elfinder.theme.css' rel='stylesheet'>
        <link href='../css/jquery/jquery.iphone.toggle.css' rel='stylesheet'>
        <link href='../css/tawi/opa-icons.css' rel='stylesheet'>
        <link href='../css/tawi/uploadify.css' rel='stylesheet'>
        <link href='../css/tawi/template.css' rel='stylesheet'>
        <link href='../css/tawi/checkpass.css' rel='stylesheet'>
        <link href='../css/tawi/styles.css' rel='stylesheet'>
        
      
        
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
                   <a class="brand" href="index.html">  <span>My School</span></a>
                    
		    <!-- user dropdown starts -->
                    <div class="btn-group pull-right" >
                         <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user"></i><span class="hidden-phone"><%%></span>
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a href="#">Profile</a></li>
                            <li class="divider"></li>
                            <li><a href="Logout">Logout</a></li>
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
            <li class='has-sub'><a href='#'><span>Exam Management</span></a></li>
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


                            <li><a class="ajax-link" href="accounts.jsp"><i class="icon-home"></i><span class="hidden-tablet"> Money</span></a></li>
                            <li><a class="ajax-link" href="credit.jsp"><i class="icon-envelope"></i><span class="hidden-tablet">Subjects</span></a></li>
                            <li><a class="ajax-link" href="source.jsp"><i class="icon-edit"></i><span class="hidden-tablet">House</span></a></li>
                            <li><a class="ajax-link" href="network.jsp"><i class="icon-trash"></i><span class="hidden-tablet">Games</span></a></li>
                           
                           <li class="nav-header hidden-tablet">Accounts</li> 
                           
                            <li><a class="ajax-link" href="systemnotices.jsp"><i class="icon-home"></i><span class="hidden-tablet">Notices</span></a></li>
                            <li><a class="ajax-link" href="adminnotices.jsp"><i class="icon-envelope"></i><span class="hidden-tablet">Payments</span></a></li>
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

