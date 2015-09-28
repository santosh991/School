
<body>

<div id="tooplate_wrapper">

	<jsp:include page="header.jsp" />
    
    <div id="tooplate_middle_subpage">
        <h2>Contact Information</h2>
        <p>Suspendisse faucibus felis ut justo hendrerit at porttitor dolor aliquet. Pellentesque iaculis aliquam mi sit amet rhoncus. Proin aliquet aliquam tincidunt. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> and <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>
    </div>
	
    <div id="tooplate_main_top"></div>        
    <div id="tooplate_main">
        
        <div class="col_w900 col_w900_last">
            <div class="col_w420 float_l">
           	  <h3>Send a message</h3>
              <div id="cp_contact_form">
                    <form method="post" name="contact" action="#">
					
						<label for="author">Name:</label> <input type="text" id="author" name="author" class="required input_field" />
						<div class="cleaner h10"></div>
													
						<label for="email">Email:</label> <input type="text" class="validate-email required input_field" name="email" id="email" />
						<div class="cleaner h10"></div>
											
						<label for="subject">Subject:</label> <input type="text" class="validate-subject required input_field" name="subject" id="subject"/>				               
						<div class="cleaner h10"></div>
							
						<label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0" class="required"></textarea>
						<div class="cleaner h10"></div>				
												
						<input type="submit" value="Send" id="submit" name="submit" class="submit_btn float_l" />
						<input type="reset" value="Reset" id="reset" name="reset" class="submit_btn float_r" />
						
					</form>
    
                </div>
               
            </div>
            
            <div class="col_w420 float_r" id="map">
            	<h3>Our Location</h3>
                <a href="images/map_big.jpg" title="Our Location">
                    <img src="images/map_thumb.jpg" alt="Our Location in Yangon" />
                </a>

				<div class="cleaner h30"></div>
                                
                <h3>Our Address</h3>
                <h6><strong>Company Name</strong></h6>
                640-980 Duis lacinia dictum, <br />
                Quisque ligula felis, 10840<br />
                Nam rhoncus, diam a mollis tempor<br /><br />
                <b>Tel:</b> 010-040-2410<br />
				<b>Fax:</b> 020-010-8720 
            </div>
        	</div>
        <div class="cleaner"></div>
    </div> <!-- end of main -->
    
   <jsp:include page="footer.jsp" />
        
</div> <!-- end of wrapper -->
</body>
</html>