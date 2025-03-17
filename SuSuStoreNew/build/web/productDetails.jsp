<%-- 
    Document   : productDetails
    Created on : Mar 9, 2025, 12:52:26 PM
    Author     : ADMIN
--%>

<%@page import="Model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <script>
        function increaseQuantity() {
            let quantityElement = document.getElementById("quantity");
            let quantity = parseInt(quantityElement.value, 10);
            quantityElement.value = quantity + 1;
        }

        function decreaseQuantity() {
            let quantityElement = document.getElementById("quantity");
            let quantity = parseInt(quantityElement.value, 10);
            if (quantity > 1) {
                quantityElement.value = quantity - 1;
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product details Page</title>
        <link rel="stylesheet" href="./css/productDetails.css"> 
    </head>
    <body>
        <div class="container">

            <div class="header-top">
                <div class="storeName">
                    <h1>SUSUSTORE</h1>
                </div>
                <div class="information">
                    <div class="hotline">Hotline: 0909300746</div>
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

            <div class="product-container">
                <c:set var="product" value="${requestScope.product}"  />
                <div class="product-image">
                    <img src="${product.imageURL}" alt="Nike Air Force 1 White Pink">
                </div>

                <div class="product-details">
                    <div class="rating">
                        <span class="star">★</span>
                        <span class="star">★</span>
                        <span class="star">★</span>
                        <span class="star">★</span>
                        <span class="star">★</span>
                    </div>
                    <h2>${requestScope.product.productName}</h2>
                    <p class="product-code">${requestScope.product.description}</p>
                    <p class="product-code">Color: ${requestScope.product.color}</p>
                    <p class="product-code">Category: ${requestScope.product.categoryID}</p>
                    <p class="product-code">Brand: ${requestScope.product.brandID}</p>
                    <p class="price">${requestScope.product.price}$</p>
                    <form action="MainController" method="GET">
                        <input type="hidden" name="action" value="ADDTOCART">
                        <input type="hidden" name="id" value="${product.productID}">

                        <div class="size-selection">
                            <p>CHỌN SIZE GIÀY</p>
                            <select name="size" required>
                                <c:forEach var="i" begin="${requestScope.beginSize}" end="${requestScope.endSize}">
                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="quantity-container">
                            <button type="button" class="quantity-button" onclick="decreaseQuantity()">-</button>
                            <input type="number" id="quantity" name="quantity" value="1" min="1" readonly>
                            <button type="button" class="quantity-button" onclick="increaseQuantity()">+</button>
                        </div>

                        <div class="action-buttons">
                            <button type="submit" class="add-to-cart">ADD TO CART</button>
                        </div>
                    </form>
                    <p class="order-phone">Hoặc đặt mua: 0909300746 (Tư vấn Miễn phí)</p>
                    <div class="social-icons">
                        <img src="" alt="Facebook">
                        <img src="path/to/twitter-icon.png" alt="Twitter">
                        <img src="path/to/pinterest-icon.png" alt="Pinterest">
                        <img src="path/to/messenger-icon.png" alt="Messenger">
                        <img src="path/to/zalo-icon.png" alt="Zalo">
                    </div>
                </div>

            </div>
            <div class="contact-button">
                <img src="path/to/contact-icon.png" alt="Liên hệ">
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
    </body>
</html>
