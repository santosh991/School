
<%@page import="com.yahoo.petermwenda83.server.session.SessionStatistics"%>
<%@page import="com.yahoo.petermwenda83.server.session.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>


<!DOCTYPE html>

 <html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login Form</title>
  <link rel="stylesheet" href="css/style.css">
 
</head>

<body>
 <div class="alert alert-info">
                           




  <div class="container">

                          
         
                         <%
                                String loginErrStr = "";
                                session = request.getSession(false);

                                if(session != null) {
                                    loginErrStr = (String) session.getAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_ERROR);
                                }                        

                                if (StringUtils.isNotEmpty(loginErrStr)) {
                                    out.println("<p style='color:red;'>");                   
                                    out.println("Login error: " + loginErrStr);
                                    out.println("</p>");                                    
                                    session.setAttribute(SessionConstants.ACCOUNT_SCHOOL_LOGIN_ERROR, null);
                                  } 
                            %>






    <section class="register">


      <h1>Login to Your School</h1>
      <form method="post" action="Login">
      <div class="reg_section personal_info">
      <h3>School Username</h3>
      <input type="text" name="schoolusername" value="" placeholder="School Username" required="true">
    <!--  <input type="password" name="schoolpassword" value="" placeholder="School Password" required="true"> -->
      </div>
      <div class="reg_section password">
      <h3>Your Position</h3>
      <select name="position" required="true">
       <option value="">Please select one</option> 
        <option value="Principal">Principal</option>
        <option value="HOD">HOD</option>
        <option value="Teacher">Teacher</option>
        <option value="Secretary">Secretary</option>
         <option value="Account Clerk">Bursar</option>
      </select>
      </div>

      <div class="reg_section password">
       <h3> Users' Username </h3>
       <input type="text" name="user_username" value="" placeholder="User username" required="true">
      <h3> Users' Passworld</h3>
      <input type="password" name="user_password" value="" placeholder="Users' Passworld" required="true">
      </div>
      
      <p class="terms">
        <label>
          <input type="checkbox" name="remember_me" id="remember_me" required="true">
           I accept  <a href=#">Fastech Solutions</a>Terms & Condition
        </label>
      </p>
      <p class="submit"><input type="submit" name="submit" value="Login"></p>
      </form>
    </section>
  </div>


</body>
</html>