

<%@page import="com.yahoo.petermwenda83.persistence.registration.StudentDAO"%>
<%@page import="com.yahoo.petermwenda83.persistence.registration.PrimaryDAO"%>

<%@page import="com.yahoo.petermwenda83.bean.student.Student"%>
<%@page import="com.yahoo.petermwenda83.bean.student.Primary"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<body>

<%
 StudentDAO stuDAO = StudentDAO.getInstance();
 List<Student> studntList = new ArrayList(); 
 studntList = stuDAO.getAllStudents();

  PrimaryDAO priDAO = PrimaryDAO.getInstance();
  List<Primary> pList = new ArrayList(); 
  pList = priDAO.getAllPrimary();

%>




 <div id="tooplate_wrapper">

<jsp:include page="header.jsp" />
   
    <div id="tooplate_middle">



       <!-- <p>Students Primary School Details</p>
        
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>*</th>
                        <th>School Name</th>
                        <th>Index</th>                
                        <th>K.C.P.E Year</th>
                        <th>K.C.P.E Mark</th>
                    </tr>
                </thead>   
                <tbody>
                    <%                                                          
                      /*  int count = 1;
                       for (Primary p : pList) {
                       
                    %>
                    <tr>
                        <td width="10%"><%=count%></td>
                         <td class="center"><%=p.getSchoolname()%></td> 
                         <td class="center"><%=p.getIndex()%></td>
                         <td class="center"><%=p.getKcpeyear()%></td>
                        <td class="center"><%=p.getKcpemarks()%></td>                       
                    </tr>

                    <%
                           count++;
                            } */
                    %>
                </tbody>
            </table>  -->







     
        <div id="middle_left">
            <h3>School Mission</h3>
            <p>Impact knowledge to all.</p>
            <h3>School Vision</h3>
            <p>To be the best School in the world.</p>
        </div>
        <div id="slider_wrapper">    
            <div id="slider">
                <a href="#"><img src="images/slideshow/01.jpg" alt="Image 01" title="School Laboratory ." /></a>
                <a href="#"><img src="images/slideshow/02.jpg" alt="Image 02" title="Administration Block." /></a>
                <a href="#"><img src="images/slideshow/03.jpg" alt="Image 03" title="Our Classes." /></a>
                <a href="#"><img src="images/slideshow/04.jpg" alt="Image 04" title="Our Students taking meals ." /></a>
                <a href="#"><img src="images/slideshow/05.jpg" alt="Image 05" title="Our Bus." /></a>
            </div>
        </div>
    





















       

	</div>
	
    <div id="tooplate_main_top"></div>        
    <div id="tooplate_main">
        <div class="col_w900">
            <div class="col_allw280 fp_service_box">
                <div class="con_tit_02">HOD Sciences</div>
                <img src="images/onebit_18.png" alt="Image" />
                <p>Credit goes to <a rel="nofollow" href="http://www.icojoy.com">icojoy.com</a> for Onebit icons #2 used in this template. Etiam vel elit massa, sit amet.</p>
                <a class="more" href="#">Detail</a>
            </div>
      <div class="col_allw280 fp_service_box">
                <div class="con_tit_02">HOD Languanges</div>
                <img src="images/onebit_15.png" alt="Image" />
                <p>Nunc imperdiet eros in libero pharetra sit amet euismod quam facilisis. Nullam quis erat mi.</p>
                <a class="more" href="#">Detail</a>
            </div>
            <div class="col_allw280 fp_service_box col_last">
                <div class="con_tit_02">HOD Humanities</div>
                <img src="images/onebit_16.png" alt="Image" />
                <p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
                <a class="more" href="#">Detail</a>
            </div>
            <div class="cleaner"></div>
		</div>
    
    	 <div class="col_w900 col_w900_last">
    		<div class="col_w580 float_l">
                    <div class="con_tit_01">HOD <span>Techinicals</span></div>
                    <img src="images/tooplate_image_01.jpg" alt="Image" class="image_wrapper image_fl" />
                    <p><em>Ut faucibus massa nec ligula facilisis ac commodo diam porta. Sed volutpat suscipit nunc nec tempus. Duis leo libero, iaculis nec sed. Pellentesque congue viverra mauris, a semper elit viverra malesuada. Suspendisse pellentesque est et enim tincidunt sed facilisis libero dapibus.</em></p>
                    <p align="justify">Sunflower Theme is free website template by tooplate. Credits go to <a rel="nofollow" href="http://www.photovaco.com">Free Photos</a> for photos and <a rel="nofollow" href="http://www.brushking.eu/361/abstract-brush-pack-vol-7.html">forty-winks</a> for Photoshop brushes. Quisque in diam a justo condimentum molestie. Vivamus a velit. Vivamus leo velit, convallis id, ultrices sit amet, tempor a, libero. Quisque nulla quis sem. Mauris quis nulla sed ipsum pretium sagittis. Suspendisse feugiat. Ut sodales libero ut odio. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> and <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>	
                    <div class="cleaner"></div>
           </div>
	        
                <div class="col_w280 float_r">
                	
                    <h2>Latest Updates</h2>
                    <div class="lbe_box">
                    <p class="date">March 27, 2048</p>
                    <h3><a href="#">Clinic Day</a></h3>
                    <p>Morbi pellentesque, libero vitae fermentum tincidunt libero accumsan erat.</p>
                    
                    </div>
                    <div class="lbe_box">
                    	<p class="date">March 17, 2048</p>
                        <h3><a href="#">Visiting</a></h3>
                        <p>Libero accumsan erat, sit amet ornare lectus urna a turpis libero nibh vulputate.</p>
                        
                        
                    </div>
                    <div class="lbe_box">
                    	<p class="date">March 10, 2048</p>
                        <h3><a href="#">Closing date</a></h3>
                        <p>Nam ac iaculis sapien. Duis nunc nisl, dignissim sed dictum in, eleifend a turpis.</p>
                    </div>                 
                	<div class="cleaner"></div>
            	</div>
			</div>
        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
  <jsp:include page="footer.jsp" />

        
</div> <!-- end of wrapper -->
</body>