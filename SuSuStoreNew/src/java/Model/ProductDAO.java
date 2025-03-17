/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    public List<ProductDTO> getAllProducts() {

        List<ProductDTO> list = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();

            String sql = " SELECT ProductID, ProductName, Description,Price, Size, Color, ImageURL, StockQuantity, CategoryID, BrandID FROM Products ";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getString("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Description"),
                            rs.getBigDecimal("Price"),
                            rs.getString("Size"),
                            rs.getString("Color"),
                            rs.getString("ImageURL"),
                            rs.getInt("StockQuantity"),
                            rs.getString("CategoryID"),
                            rs.getString("BrandID")
                    );
                    list.add(product);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ProductDTO getProductById(String id) {
        ProductDTO product = null;
        try {
            Connection con = DBUtils.getConnection();
            String sql = " SELECT ProductID, ProductName, Description,Price, Size, Color, ImageURL, StockQuantity, CategoryID, BrandID FROM Products WHERE ProductID = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    product.setProductID(rs.getString("productID"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setDescription(rs.getString("Description"));
                    product.setPrice(rs.getBigDecimal("Price"));
                    product.setSize(rs.getString("Size"));
                    product.setColor(rs.getString("Color"));
                    product.setImageURL(rs.getString("ImageURL"));
                    product.setStockQuantity(rs.getInt("StockQuantity"));
                    product.setCategoryID(rs.getString("CategoryID"));
                    product.setBrandID(rs.getString("BrandID"));
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean createProduct(ProductDTO product) {
        String sql = "INSERT INTO Products (ProductID, ProductName, Description, Price, Size, Color, ImageURL, StockQuantity, CategoryID, BrandID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setBigDecimal(4, product.getPrice());
            stmt.setString(5, product.getSize());
            stmt.setString(6, product.getColor());
            stmt.setString(7, product.getImageURL());
            stmt.setInt(8, product.getStockQuantity());
            stmt.setString(9, product.getCategoryID());
            stmt.setString(10, product.getBrandID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(ProductDTO product) {
        String sql = "UPDATE Products SET ProductName = ?, Description = ?, Price = ?, Size = ?, Color = ?, ImageURL = ?, StockQuantity = ?, CategoryID = ?, BrandID = ? WHERE ProductID = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setString(4, product.getSize());
            stmt.setString(5, product.getColor());
            stmt.setString(6, product.getImageURL());
            stmt.setInt(7, product.getStockQuantity());
            stmt.setString(8, product.getCategoryID());
            stmt.setString(9, product.getBrandID());
            stmt.setString(10, product.getProductID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductDTO> searchProductByProductName(String productName) {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";

        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + productName + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getString("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Description"),
                            rs.getBigDecimal("Price"),
                            rs.getString("Size"),
                            rs.getString("Color"),
                            rs.getString("ImageURL"),
                            rs.getInt("StockQuantity"),
                            rs.getString("CategoryID"),
                            rs.getString("BrandID")
                    );
                    list.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
//        ProductDTO pro = dao.getProductById("Pro01");
//        
//        if(pro == null){
//            System.out.println("fail");
//        }else{
//            System.out.println(pro.getProductName());
//        }
      List<ProductDTO> list = dao.getAllProducts();
        for (ProductDTO product : list) {
            System.out.println(product.getProductName());
        }
    }
}
