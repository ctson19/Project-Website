<%-- 
    Document   : Profile
    Created on : Oct 17, 2023, 12:15:59 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="css/profile.css" rel="stylesheet" type="text/css"/>
        
        <title>Profile</title>
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
           
        <form class="form-signin" action="profile">
            <h3 style="color: red">${requestScope.error}</h3>
            <c:set var="c" value="${requestScope.dataacc}" />
        </form>
        <form class="form-signup" action="profile" method="post">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Profile</h1>
             
            <input name="username" type="text" class="form-control" readonly value="${sessionScope.account.username}">
            <input name="email" type="email" class="form-control" placeholder="Email" value="${sessionScope.account.email}">
            <button type="submit">Thêm Email</button>
           
            
        </form>
    

              
</script>
               <a href="changepass" class="cta-button">Change Password</a><br/>
    <a href="home" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
            </form>
            <br>
            </div>
    </body>
</html>
