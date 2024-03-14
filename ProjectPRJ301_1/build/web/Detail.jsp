<%-- 
    Document   : Detail
    Created on : Dec 29, 2020, 5:43:04 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
         <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    
    
    <body>
         <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-white">
  <!-- Container wrapper -->
  <div class="container">    
    <!-- Toggle button -->
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarSupportedContent1"
      aria-controls="navbarSupportedContent1"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Collapsible wrapper -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent1">      
      <!-- Navbar brand -->
      <a class="navbar-brand mt-2 mt-sm-0" href="home">
        <img
          src="ImgPrj/logo.jpg"
          height="20"
          alt="MDB Logo"
          loading="lazy"
        />
      </a>
      <!-- Left links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
    <c:choose>
        <c:when test="${sessionScope.account == null}">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register">Register</a>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">
                    
                    <li class="nav-item">
                        <a class="nav-link" href="home">Home</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="profile">Profile Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Hello ${sessionScope.account.username}</a>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.account.role == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="managerproduct">Manager Product</a>
                            </li>
                        </c:when>
                    </c:choose>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>
</ul>
<form action="search" method="post" class="form-inline my-2 my-lg-0 ml-auto"> <!-- Changed the class to ml-auto -->
    <div class="input-group input-group-sm">
        <input value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
        <div class="input-group-append">
            <button type="submit" class="btn btn-secondary btn-number">
                <i class="fa fa-search"></i>
            </button>
        </div>
    </div>
     <c:choose>
        <c:when test="${sessionScope.account == null}">
            <a class="btn btn-success btn-sm ml-3" href="login">Login to Add to Cart</a>
        </c:when>
        <c:otherwise>
            <a class="btn btn-success btn-sm ml-3" href="cart">
                <i class="fa fa-shopping-cart"></i> Cart
                <span class="badge badge-light"></span>
            </a>
        </c:otherwise>
    </c:choose>
</form>
  
    </div>

    
  </div>

</nav>
        
        
        <!--end of menu-->
       <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Category</a></li>
                            <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        
         <style>
    .category_block {
        display: none;
    }

    .card:hover .category_block {
        display: block;
        
    }
</style>
        
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Dòng sản phẩm</div>
                         
                        <ul class="list-group category_block">
                            <c:forEach items="${requestScope.listC}" var="o">
                                <li class="list-group-item text-white"><a href="category?cid=${o.cid}">${o.cname}</a></li>
                            </c:forEach>

                        </ul>
                    </div>
                    
                    
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Last product</div>
                        <div class="card-body">
                            <img class="img-fluid" src="${d.image_url}" />
                            <h5 class="card-title">${d.name}</h5>
                            <p class="card-text">${d.description}</p>
                            <p class="bloc_left_price">${d.price} VND</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${detail.image_url}"></a></div>
                                        </div> <!-- slider-product.// -->
                                       
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">${detail.price}</span><span class="num"> VNÐ</span>
                                            </span> 
                                            <!--<span>/per kg</span>--> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>${detail.description} </p></dd>
                                        </dl>
<!--                                        <dl class="param param-feature">
                                            <dt>Model#</dt>
                                            <dd>12345611</dd>
                                        </dl>   item-property-hor .// 
                                        <dl class="param param-feature">
                                            <dt>Color</dt>
                                            <dd>Black and white</dd>
                                        </dl>   item-property-hor .// 
                                        <dl class="param param-feature">
                                            <dt>Delivery</dt>
                                            <dd>Russia, USA, and Europe</dd>
                                        </dl>   item-property-hor .// -->

                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="item-property">
    <dt>Quantity:</dt>
    <dd>${detail.amount}</dd>
</dl>  <!-- item-property .// -->
                                            </div> <!-- col.// -->
                                            
                                        </div> <!-- row.// -->
                                        <hr>
                                        <a href="#" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                                        <c:choose>    
                                <c:when test="${sessionScope.account == null}">
                                    
                                    <button class="btn btn-lg btn-primary text-uppercase" disabled>Add to Cart (Login required)</button>
                                </c:when>
                                <c:otherwise>
                                   
                                    <a href="addtc?id=${detail.id}" class="btn btn-lg btn-primary text-uppercase">Add to Cart</a>
                                </c:otherwise>
                            </c:choose>

                                    </article> <!-- card-body.// -->
                                    <h3>Sản Phẩm Liên Quan</h3>
                                </aside> <!-- col.// -->
                                
                                <c:forEach items="${requestScope.listR}" var="c">   
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="card-img-top" src="${c.image_url}" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title show_txt"><a href="detail?pid=${c.id}" title="View Product">${c.name}</a></h4>
                    <p class="card-text show_txt">${c.description}</p>
                    <div class="row">
                        <div class="col">
                            <p class="btn btn-danger btn-block">${c.price}VND</p>
                        </div>
                        <div class="col">
                            <c:choose>    
                                <c:when test="${sessionScope.account == null}">
                                    
                                    <button class="btn btn-success btn-block" disabled>Add to Cart (Login required)</button>
                                </c:when>
                                <c:otherwise>
                                   
                                    <a href="addtc?id=${c.id}" class="btn btn-success btn-block">Add to Cart</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</c:forEach>
                            </div> <!-- row.// -->
                            
                            
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
        </div>

 
        <!-- Footer -->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-lg-4 col-xl-3">
                        <h5>About</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <p class="mb-0">
                            Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression.
                        </p>
                    </div>

                    <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                        <h5>Informations</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                        </ul>
                    </div>

                    <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
                        <h5>Others links</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                        </ul>
                    </div>

                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Contact</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-home mr-2"></i> SonPhoneS</li>
                            <li><i class="fa fa-envelope mr-2"></i> soncthe171406@fpt.edu.vn</li>

                        </ul>
                    </div>
                    <div class="col-12 copyright mt-3">
                        <p class="float-left">
                            <a href="#">Back to top</a>
                        </p>
                        <p class="text-right text-muted">created with <i class="fa fa-heart"></i> by <a href="https://t-php.fr/43-theme-ecommerce-bootstrap-4.html"><i>t-php</i></a> | <span>v. 1.0</span></p>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
