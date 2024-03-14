<%-- 
    Document   : Otp-password
    Created on : Nov 9, 2023, 1:11:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
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
    <body id="intro" class="bg-image shadow-2-strong">
        
        <c:set var="cookie" value="${pageContext.request.cookies}"/>
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-8">
                    <h1 class="text-center">Nhập mã OTP xác thực</h1>
                    <form action="otp-password" method="POST">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" type="email" id="email" name="email" value="${cookie.cEmail.value}" required readonly>
                        </div>
                        <div class="form-group">
                            <label for="otp">Mã OTP</label>
                            <input class="form-control" type="text" id="otp" name="otp" required>
                        </div>
                        <c:if test="${error != null}">
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </c:if>
                        <div class="form-group">
                            <button class="btn btn-primary btn-block" type="submit">Xác nhận</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        

        <!--Script-->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    </body>
</html>
