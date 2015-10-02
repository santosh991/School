


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









<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>School Management System</title>
<meta name="keywords" content="" />
<meta name="description" content="" />

<link href="../css/tooplate_style.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript">

function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;
}

</script>


<link rel="stylesheet" href="../css/nivo-slider.css" type="text/css" media="screen" />
<link href="../css/bootstrap/bootstrap-cerulean.css" rel="stylesheet">
 <link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet">
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script src="../js/jquery.nivo.slider.js" type="text/javascript"></script>

<script type="text/javascript">
$(window).load(function() {
	$('#slider').nivoSlider({
		effect:'random',
		slices:15,
		animSpeed:500,
		pauseTime:3000,
		startSlide:0, //Set starting Slide (0 index)
		directionNav:false,
		directionNavHide:false, //Only show on hover
		controlNav:false, //1,2,3...
		controlNavThumbs:false, //Use thumbnails for Control Nav
		pauseOnHover:true, //Stop animation while hovering
		manualAdvance:false, //Force manual transitions
		captionOpacity:0.8, //Universal caption opacity
		beforeChange: function(){},
		afterChange: function(){},
		slideshowEnd: function(){} //Triggers after all slides have been shown
	});
});
</script>

</head>

<div id="tooplate_header">
    	
        <div id="site_title"><h1><a href="#">School Management System</a></h1></div>
        
        <div id="social_box">
            <a href="#"><img src="../images/facebook.png" alt="facebook" /></a>
            <a href="#"><img src="../images/myspace.png" alt="myspace" /></a>
            <a href="#"><img src="../images/twitter.png" alt="twitter" /></a>
        </div>
        
        <div class="cleaner"></div>
    </div>
    
    <div id="tooplate_menu">
        <ul>
            <li><a href="home.jsp" class="current">Home</a></li>
            <li><a href="gallery.jsp">Exams</a></li>
             <li><a href="#">Reports</a></li>
            <li><a href="news.jsp">Money</a></li>
            <li><a href="blog.jsp">Staffs</a></li>
            <li class="last"><a href="../index.jsp">Logout(<%=username%>)</a></li>
        
        </ul>    	
    
    </div> <!-- end of tooplate_menu -->
