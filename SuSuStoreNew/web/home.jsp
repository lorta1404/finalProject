<%-- 
    Document   : home.jsp
    Created on : Mar 9, 2025, 11:03:04 AM
    Author     : ADMIN
--%>


<%@page import="Model.ProductDTO"%>
<%@page import="Model.MyDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="./css/home.css"> 
    </head>
        <div class="container">

            <div class="header-top">
                <div class="storeName">
                    <h1>SUSUSTORE</h1>
                </div>

                <div class="information">
                    <div class="hotline">Hello</div>
                    <div class="check-user">CHECK USER</div>
                </div>
            </div>     
            <div class="div-fixed">
                
                <div class="category">
                    <a href="MainController?action=LIST&search=&page=1">Introduction</a>
                    <a href="MainController?action=LISTBYBRAND&brand=Nike&page=1">Nike</a>
                    <a href="MainController?action=LISTBYBRAND&brand=Adidas&page=1">Adidas</a>
                    <a href="MainController?action=LISTBYBRAND&brand=Gucci&page=1">Gucci</a>
                    <a href="MainController?action=LISTBYBRAND&brand=Puma&page=1">Puma</a>
                </div>

                <div class="logo">
                    <div class="logo-img">
                        <img src="image/logo.jpg" alt="KINGSHOES.VN">
                    </div>
                    <div class="logo-content">
                        <p>SUSUSTORE</p>
                    </div>

                    <div class="search-cart">
                        <form action="MainController" method="GET">
                            <input type="text" name="search" value="${search}" placeholder="Nhập từ cần tìm">
                            <input type="hidden" name="action" value="LIST">
                            <input type="hidden" name="page" value="1">                            
                            <button type="submit">Search</button>
                        </form>

                    </div>
                </div>
            </div>
            <c:set var="list" value="${requestScope.list}"  />

            <div class="content">
                <div class="product-card">       
                    <c:if test="${not empty list}">
                        <div class="product-grid">   
                            <c:forEach var="dto" items="${list}" >

                                <a href="MainController?action=PRODUCTDETAIL&id=${dto.productID}">
                                    <div class="product">
                                        <div class="discount">-28%</div>
                                        <img src="${dto.imageURL}" alt="NIKE STRUCTURE 25">
                                        <h3>${dto.productName}</h3>
                                        <div class="price">
                                            <span class="new-price">${dto.price}$</span>

                                        </div>
                                    </div>
                                </a>
                            </c:forEach>

                        </div>
                    </c:if>
                </div>
            </div>








            <footer>
                <div class="footer">
                    <div class="left">
                        <p>Công ty Cổ Phần Tập Đoàn Eagle</p>
                        <p>Địa chỉ: 115 Phạm Văn Đồng, phường Vỹ Dạ, thành phố Huế</p>
                        <p>Điện thoại: 0914 510 246</p>
                        <p>Email: info@eaglegroupvietnam.com</p>
                        <p>Website: eaglegroupvietnam.com</p>
                    </div>
                    <div class="middle">
                        <p>Liên kết website</p>
                        <p>dulichdeibeng.com</p>
                        <p>eaglemedia.vn</p>
                        <p>nhahangcontoc.com</p>
                        <p>efoodvietnam.com</p>
                        <p>tamgianglegoon.com</p>
                    </div>
                    <div class="right">
                        <p>Fanpage</p>
                        <img src="path/to/your/fanpage-image.jpg" alt="Eagle Group Fanpage">
                        <p>Theo dõi trang</p>
                        <p>Chia sẻ</p>
                    </div>
                </div>
                <div class="social-icons">
                    <p>Kết nối với chúng tôi</p>
                    <img src="path/to/facebook-icon.png" alt="Facebook">
                    <img src="path/to/twitter-icon.png" alt="Twitter">
                    <img src="path/to/instagram-icon.png" alt="Instagram">
                    <img src="path/to/youtube-icon.png" alt="YouTube">
                    <img src="path/to/google-plus-icon.png" alt="Google Plus">
                </div>

            </footer>
        </div>
        <c:set var="currentPage" value="${requestScope.page}" />
        <c:set var="totalPage" value="${requestScope.totalPage}" />
        <c:set var="brand" value="${requestScope.brand}" />
        <c:set var="search" value="${requestScope.search}" />



        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <c:choose>
                    <c:when test="${not empty brand}">
                        <a href="MainController?action=LISTBYBRAND&brand=${brand}&page=${currentPage - 1}">|<|</a>
                    </c:when>
                    <c:otherwise>
                        <a href="MainController?action=LIST&search=${search}&page=${currentPage - 1}">|<|</a>
                    </c:otherwise>
                </c:choose>

            </c:if>

            <span>|${currentPage}|</span>

            <c:if test="${currentPage < totalPage}">
                <c:choose>
                    <c:when test="${not empty brand}">
                        <a href="MainController?action=LISTBYBRAND&brand=${brand}&page=${currentPage + 1}">>|</a>
                    </c:when>
                    <c:otherwise>
                        <a href="MainController?action=LIST&search=${search}&page=${currentPage + 1}">>|</a>
                    </c:otherwise>
                </c:choose>

            </c:if>
        </div>
        <p>Debug Search: ${search}</p>
        <p>Debug Total Page: ${totalPage}</p>
        <p>Debug Current Page: ${currentPage}</p>


    </body>
</html>
