<%-- 
    Document   : Register
    Created on : Oct 12, 2023, 2:29:22 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
 <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>Login Form</title>
    </head>
    <style>
      #intro {
        background-image: url(ImgPrj/banner2.jpg);
        width: 100%
      }

      /* Height for devices larger than 576px */
      @media (min-width: 992px) {
        #intro {
          margin-top: 15px;
        }
      }

      .navbar .nav-link {
        color: #0056b3;
      }
    </style>
    <body id="intro">
        <div id="logreg-forms">
            <form class="form-signin" action="changepass" method="post">
                <h3 style="color:red">${requestScope.error}</h3>
      
            
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Change PassWord</h1>
                <input name="username"  type="text" id="inputEmail" class="form-control" readonly value="${sessionScope.account.username}">
                <input name="opass" type="text" id="user-name" class="form-control" placeholder="Old Password" required="" autofocus="">
                <input name="pass" type="password" id="user-pass" class="form-control" placeholder="New Password" required autofocus="">
                <input name="rpass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus="">

                <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> CHANGE</button>
                <a href="Profile.jsp" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
            
            <br>
            </form>
    </body>
</html>
