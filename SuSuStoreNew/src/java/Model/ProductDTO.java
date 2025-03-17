/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class ProductDTO {

    private String productID;
    private String productName;
    private String description;
    private BigDecimal price;
    private String size;
    private String color;
    private String imageURL;
    private int stockQuantity;
    private String categoryID;
    private String brandID;

    // Constructors, getters, setters, toString()
    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, String description, BigDecimal price, String size, String color, String imageURL, int stockQuantity, String categoryID, String brandID) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.size = size;
        this.color = color;
        this.imageURL = imageURL;
        this.stockQuantity = stockQuantity;
        this.categoryID = categoryID;
        this.brandID = brandID;
    }

    // Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

}
