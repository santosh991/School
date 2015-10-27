

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
<html>
<body>
 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />

         
     <div id="search_box">
            <form action="#" method="get">
                <input type="text" value="Search" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="" id="searchbutton" title="Search" />
            </form>
        </div>
  
         <div id="tooplate_main">        
         <div id="middle_left" >               
            <a class="btn" href="addstudent.jsp" title="add student" data-rel="tooltip">Add</a>  
         </div>

          <p>Students Management</p>
        <div id="cp_contact_form">
          
       <p>   dhdhfjjjjfjfjfjf  </p>


        </div>
                












       
    
    
    <div class="col_w900 col_w900_last">
        <div class="col_w580 float_l">
                    <div class="con_tit_01">Welcome <span>to ....... </span></div>
                    
                    <p><em>uyfyuiiuyfghoiuyfghjgvhbjuyghjughj.</em></p>
                    <p align="justify">jhdhdhfhjierjeioirioriotioio.</p>  

                    <div class="cleaner"></div>
           </div>
          
                <div class="col_w280 float_r">
                  
                    <h2>Latest Updates</h2>
                    <div class="lbe_box">
                    <p class="date">Nov 17, 2015</p>
                    <h3><a href="#">Exam Start</a></h3>
                    <p>ghjkjhghjkliuyfguiuyuioiuyghjitghjiu.</p>
                    
                    </div>
                    <div class="lbe_box">
                      <p class="date">Nov 27, 2015</p>
                        <h3><a href="#">Closing day</a></h3>
                        <p>ghjkkuyiooiuytrtghiutrghjkoiuytfghjuytfgvu.</p>
                        
                        
                    </div>
                    <div class="lbe_box">
                      <p class="date">Dec 10, 2015</p>
                        <h3><a href="#">School Opens</a></h3>
                        <p>hgfdfghjkkgfghlkoiuyfghjkiuhgvbnmlugvhnl</p>
                    </div>                 
                  <div class="cleaner"></div>
              </div>
      </div>
    <div class="cleaner"></div>
    </div> <!-- end of main -->
 <jsp:include page="footer.jsp" />
        
</div> <!-- end of wrapper -->

</body>
</html>