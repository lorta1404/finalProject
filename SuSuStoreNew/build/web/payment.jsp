<%-- 
    Document   : profile
    Created on : Mar 17, 2025, 12:06:14 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/payment.css"
    </head>
    <body>
        <div class="container">
            <h1>Phương thức thanh toán</h1>

            <form id="payment-form">
                <div class="payment-method">
                    <input type="radio" id="credit-card" name="payment-method" value="credit-card" checked>
                    <label for="credit-card">Thẻ tín dụng</label>
                </div>

                <div class="payment-method">
                    <input type="radio" id="paypal" name="payment-method" value="paypal">
                    <label for="paypal">PayPal</label>
                </div>

                <div class="payment-method">
                    <input type="radio" id="cod" name="payment-method" value="cod">
                    <label for="cod">Thanh toán khi nhận hàng (COD)</label>
                </div>

                <div id="credit-card-details" class="payment-details">
                    <label for="card-number">Số thẻ:</label>
                    <input type="text" id="card-number" name="card-number">

                    <label for="expiry-date">Ngày hết hạn:</label>
                    <input type="text" id="expiry-date" name="expiry-date">

                    <label for="cvv">CVV:</label>
                    <input type="text" id="cvv" name="cvv">
                </div>

                <div id="paypal-details" class="payment-details" style="display: none;">
                    <label for="paypal-email">Email PayPal:</label>
                    <input type="email" id="paypal-email" name="paypal-email">
                </div>

                <button type="submit">Xác nhận thanh toán</button>
            </form>
        </div>

        <script src="script.js"></script>

    </body>
</html>
