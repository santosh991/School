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
<style type="text/css">
   input{
    height: 26px !important;
   }
   
 </style>
</head>
<body>






<div id="tooplate_header">
    	
        <div id="site_title"><h1><a href="#">Admin Panel:School Management System </a></h1></div>
        
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
            <li><a href="#">Manage Schools</a></li>
             <li><a href="#">Notifications</a></li>
            <li class="last"><a href="#">Payments</a></li>
        </ul>    	
        
        <div id="search_box">
            <form action="#" method="get">
                <input type="text" value="Search" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                <input type="submit" name="Search" value="" id="searchbutton" title="Search" />
            </form>
        </div>
        <div class="cleaner"></div>
    </div> <!-- end of tooplate_menu -->

    </body>
    </html>