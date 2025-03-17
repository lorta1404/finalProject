<%-- 
    Document   : orderProduct
    Created on : Mar 9, 2025, 6:16:32 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Product Page</title>
        <link rel="stylesheet" href="./css/orderProduct.css"> 
    </head>
    <body>
        <div class="container">
            <div class="container-left">
                <div class="container-left-content">
                    <div class="logo">
                        <div class="logo-img">
                            <img src="image/logo.jpg" alt="KINGSHOES.VN">
                        </div>

                        <div class="logo-content">
                            <p>You're King in Your Way.!!!</p>
                        </div>
                    </div>
                    <div class="tabs">
                        <a href="#" class="active">Giỏ hàng</a>
                        <a href="#">Thông tin gian hàng</a>
                        <a href="#">Phương thức thanh toán</a>
                    </div>
                    <form action="" method="POST">
                        <div class="delivery-info">
                            <h2>Thông tin giao hàng</h2>
                            <input class="name" type="text" placeholder="Họ Và Tên">
                            <input class="phone" type="text" placeholder="Số điện thoại">
                            <input class="address" type="text" placeholder="Địa Chỉ">
                            <select>
                                <option value="">Chọn Tỉnh/Thành</option>
                            </select>
                            <select>
                                <option value="">Chọn Quận/Huyện</option>
                            </select>
                            <select>
                                <option value="">Chọn Phường/Xã</option>
                            </select>
                            <input type="email" placeholder="example@domain.com">
                            <textarea placeholder="Nội dung"></textarea>
                        </div>
                        <div class="navigation">
                            <a href="#">&lt; Giỏ hàng</a>
                            <button class="next-step">Tiếp tục đến phương thức thanh toán</button>
                        </div>
                    </form>
                </div>
            </div>

            <div class="container-right">
                <div class="container-right-content">
                    <div class="order-summary">
                        <div class="item">
                            <div class="item-image">
                                <img src="path/to/your/shoe-image.jpg" alt="Giày JEEP OUTDOOR">
                            </div>
                            <div class="item-details">
                                <h3>GIÀY JEEP OUTDOOR</h3>
                                <p>Size Giày: 38</p>
                            </div>
                            <span class="item-price">1,650,000₫</span>
                        </div>
                        <div class="discount">
                            <input type="text" placeholder="Mã giảm giá">
                            <button>Hủy</button>
                            <button>Sử dụng</button>
                        </div>
                        <div class="totals">
                            <p>Tạm tính: <span>1,650,000₫</span></p>
                            <p>Phí giao hàng: <span>Chưa bao gồm</span></p>
                            <p class="total">Tổng cộng: <span>1,650,000₫</span></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
