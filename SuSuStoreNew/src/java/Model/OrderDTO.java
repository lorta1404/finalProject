/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class OrderDTO {
    private String orderID;
    private int userID;
    private Timestamp orderDate;
    private String shippingAddress;
    private String orderStatus;
    private BigDecimal totalAmount;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, int userID, Timestamp orderDate, String shippingAddress, String orderStatus, BigDecimal totalAmount) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
}
