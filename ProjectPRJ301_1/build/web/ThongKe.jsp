<%-- 
    Document   : ThongKe
    Created on : Nov 2, 2023, 1:07:08 AM
    Author     : Admin
--%>


  
    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Thống kê</title>
    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <link href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css" rel="stylesheet">
    <link href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #fbfbfb;
            margin: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        h1 {
            color: #333;
        }

        p {
            font-size: 18px;
            margin: 10px 0;
        }

        .card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 1.25rem;
        }

        header {
            position: fixed;
            top: 0;
            left: 0;
            background-color: #fff;
            width: 240px;
            height: 100%;
            padding: 20px;
            border-right: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            z-index: 1000;
        }

        header a {
            color: #333;
            text-decoration: none;
            display: block;
            margin: 10px 0;
            font-size: 18px;
        }

        header a:hover {
            color: #007BFF;
        }

        .content {
            margin-left: 240px;
            padding: 20px;
        }
    </style>
</head>

<body>
    <header>
        <!-- Sidebar -->
        <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">
            <div class="position-sticky">
                <div class="list-group list-group-flush mx-3 mt-4">
                    <a href="home"><i class="fas fa-chart-bar fa-fw me-3"></i>Home</a>
                    <a href="thongke"><i class="fas fa-chart-bar fa-fw me-3"></i>Thống Kê</a>
                    <a href="chart"><i class="fas fa-chart-bar fa-fw me-3"></i>Doanh thu tháng</a>
                    <a href="chartquantity"><i class="fas fa-chart-bar fa-fw me-3"></i>Doanh số bán được</a>
                    <a href="managerproduct"><i class="fas fa-shoe-prints fa-fw me-3"></i>Quản lý sản phẩm</a>
                </div>
            </div>
        </nav>
        <!-- Sidebar -->
    </header>
    <div class="content">

        <div class="container">
            <section class="product-statistics">
                <h1>Thống kê sản phẩm bán chạy nhất</h1>
                <c:choose>
                    <c:when test="${not empty productName}">
                        <div class="card">
                            <div class="card-body">
                                <i class="fas fa-shopping-bag fa-5x text-primary mb-4"></i>
                                <p><strong>Tên sản phẩm:</strong> ${productName}</p>
                                <p><strong>Số lượng bán được:</strong> ${damua}</p>
                            </div>
                        </div>
                       
                    </c:when>
                    <c:otherwise>
                        <p>Không có dữ liệu sản phẩm bán chạy nhất.</p>
                    </c:otherwise>
                </c:choose>
            </section>
            </div>

            <div class="container">
            <section class="product-last-7-days">
                <h1>Thống kê sản phẩm bán được trong 7 ngày gần nhất</h1>
                <c:choose>
                    <c:when test="${not empty hotProductsLast7Days}">
                        <div class="table-container">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Tên sản phẩm</th>
                                        <th class="text-center">Số lượng bán được</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${hotProductsLast7Days}" var="topPro">
                                        <tr>
                                            <td>${topPro.productName}</td>
                                            <td class="text-center">${topPro.totalPurchases}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p class="text-center">Không có dữ liệu sản phẩm bán được trong 7 ngày gần nhất.</p>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        
         <div class="container">
         <a href="5product" class="list-group-item list-group-item-action py-2 ripple">
                            <i class="fas fa-chart-bar fa-fw me-3"></i><span>5 sản phẩm bán chạy nhất</span>
                        </a>
         </div>

        <div class="container">
            <h1>Người mua nhiều nhất</h1>
            <c:choose>
                <c:when test="${not empty username}">
                    <div class="card">
                        <div class="card-body">
                            <i class="fas fa-user fa-5x text-primary mb-4"></i>
                            <p><strong>Tên Account: </strong> ${username}</p>
                            <p><strong>Số lượng mua: </strong> ${totalOrders}</p>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Không có dữ liệu người mua nhiều nhất.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="container">
            <h1>Thống kê người mua trong 7 ngày gần nhất</h1>
            <c:choose>
                <c:when test="${not empty topBuyersLast7Days}">
                    <div class="table-container">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Tên Account</th>
                                    <th>Số lượng mua</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${topBuyersLast7Days}" var="topBuyer">
                                    <tr>
                                        <td>${topBuyer.username}</td>
                                        <td>${topBuyer.totalOrders}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Không có dữ liệu người mua trong 7 ngày gần nhất.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="container">
            <a href="5acc" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fas fa-chart-bar fa-fw me-3"></i><span>5 Account có lượt mua nhiều nhất</span>
            </a>
        </div>

        <div class="container">
            <h1>Tổng doanh thu</h1>
            <div class="card">
                <div class="card-body">
                    <i class="fas fa-money-bill fa-5x text-primary mb-4"></i>
                    <c:if test="${not empty totalMoneyByMonth}">
                        <c:set var="totalMoneySum" value="0" scope="page" />
                        <c:forEach items="${totalMoneyByMonth}" var="totalMoney" varStatus="loop">
                            <c:set var="totalMoneySum" value="${totalMoneySum + totalMoney}" scope="page" />
                        </c:forEach>
                        <div>
                            <p>Tổng tiền: ${totalMoneySum}</p>
                        </div>
                    </c:if>
                </div>
            </div>
            <div>
                <p>Doanh thu trong 3 ngày gần nhất: ${totalRevenueLast3Days}</p>
                <p>Doanh thu trong 7 ngày gần nhất: ${totalRevenueLast7Days}</p>
                <p>Doanh thu trong 14 ngày gần nhất: ${totalRevenueLast14Days}</p>
                <p>Doanh thu trong 21 ngày gần nhất: ${totalRevenueLast21Days}</p>
            </div>
        </div>
    </div>

</body>

</html>
