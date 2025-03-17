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
public class CartDTO {
    private int userID;
    private String productID;
    private int quantity;
    private Timestamp addedDate;
    private String imageURL;
    private String productName;
    private BigDecimal price;

    // Constructors
    public CartDTO() {
    }

    public CartDTO(int userID, String productID, int quantity, Timestamp addedDate) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }

    

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public CartDTO(int userID, String productID, int quantity, Timestamp addedDate,String productName, String imageURL, BigDecimal price ) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.addedDate = addedDate;
        this.imageURL = imageURL;
        this.productName = productName;
        this.price = price;
    }

    

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
