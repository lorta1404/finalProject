<%-- 
    Document   : shoppingCart
    Created on : Mar 9, 2025, 6:04:55 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart Page</title>
        <link rel="stylesheet" href="./css/shoppingCart.css"> 
    </head>
    <body>
        <div class="header-top">
            <div class="storeName">
                <h1>SUSUSTORE</h1>
            </div>
            <div class="information">
                <div class="hotline">Hotline: 0909300746</div>
                <div class="check-user">CHECK USER</div>
            </div>
        </div>    
        <<div class="div-fixed">

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
        <c:set var="total" value="${requestScope.total}" />
        <c:forEach var="dto" items="${list}" >
            <form action="MainController" method="GET">
                <div class="cart-item">
                    <div class="item-image">
                        <img src="${dto.imageURL}" alt="Giày JEEP OUTDOOR">
                    </div>
                    <div class="item-details">
                        <h2 class="item-title">${dto.productName}</h2>

                        <p class="item-code">Mã sản phẩm: ${dto.productID}</p>

                        <div class="quantity-controls">

                            <span>Số lượng: ${dto.quantity}</span>

                            <span class="item-price">x ${dto.price}$</span>
                        </div>
                        <p class="subtotal">Thành tiền: ${dto.price * dto.quantity}$</p>
                    </div>
                    <input type="hidden" name="proid" value="${dto.productID}">
                    <input type="hidden" name="action" value="DELETECART">
                    <button type="submit" class="remove-item">X</button>
                </div>
            </form>
        </c:forEach>
        <div class="cart-summary">
            <p class="total">Tổng tiền: ${total}$</p>
            <div class="action-buttons">
                <button class="continue-shopping">MUA TIẾP ></button>
                <button class="checkout"><a href="orderProduct.jsp">ĐẶT HÀNG ></a></button>
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
    </body>
</html>
