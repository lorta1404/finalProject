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
import Model.ProductDTO;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author dell
 */
public class MyDAO {

    int pageSize = 16;

    public List<ProductDTO> getProductsBySearch(String search, int page) {
        List<ProductDTO> list = new ArrayList<>();

        int offset = (page - 1) * pageSize;

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT * FROM Products";
            if (search != null && !search.trim().isEmpty()) {
                sql += " WHERE ProductName LIKE ?";
            }
            sql += " ORDER BY ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stmt = con.prepareStatement(sql);

            int paramIndex = 1;
            if (search != null && !search.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + search + "%");
            }
            stmt.setInt(paramIndex++, offset);
            stmt.setInt(paramIndex, pageSize);

            ResultSet rs = stmt.executeQuery();

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

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getTotalPages(String search) {

        int totalPages = 0;

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT COUNT(*) FROM Products";
            if (search != null && !search.trim().isEmpty()) {
                sql += " WHERE ProductName LIKE ?";
            }

            PreparedStatement stmt = con.prepareStatement(sql);

            if (search != null && !search.trim().isEmpty()) {
                stmt.setString(1, "%" + search + "%");
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int totalRecords = rs.getInt(1);
                totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPages;
    }

    public ProductDTO getProductByID(String productID) {
        ProductDTO product = null;

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, "
                    + "CAST(p.Size AS VARCHAR) AS Size, p.Color, p.ImageURL, "
                    + "p.StockQuantity, c.CategoryName, b.BrandName "
                    + "FROM Products p "
                    + "JOIN Categories c ON p.CategoryID = c.CategoryID "
                    + "JOIN Brands b ON p.BrandID = b.BrandID "
                    + "WHERE p.ProductID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, productID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new ProductDTO(
                        rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getBigDecimal("Price"),
                        rs.getString("Size"), // Lấy size dưới dạng String
                        rs.getString("Color"),
                        rs.getString("ImageURL"),
                        rs.getInt("StockQuantity"),
                        rs.getString("CategoryName"), // Lấy tên Category
                        rs.getString("BrandName") // Lấy tên Brand
                );
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public List<ProductDTO> getProductsByBrand(String brand, int page) {
        List<ProductDTO> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT p.* FROM Products p "
                    + "JOIN Brands b ON p.BrandID = b.BrandID "
                    + "WHERE b.BrandName = ? "
                    + "ORDER BY p.ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, brand);
            stmt.setInt(2, offset);
            stmt.setInt(3, pageSize);

            ResultSet rs = stmt.executeQuery();

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

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getTotalPagesByBrand(String brand) {

        int totalPages = 0;

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT COUNT(*) FROM Products p "
                    + "JOIN Brands b ON p.BrandID = b.BrandID "
                    + "WHERE b.BrandName = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, brand);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int totalRecords = rs.getInt(1);
                totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPages;
    }

    public boolean addProductToCart(int userID, String productID, int quantity) {
        String sql = "MERGE INTO Cart AS target "
                + "USING (SELECT ? AS UserID, ? AS ProductID, ? AS Quantity) AS source "
                + "ON target.UserID = source.UserID AND target.ProductID = source.ProductID "
                + "WHEN MATCHED THEN "
                + "    UPDATE SET target.Quantity = target.Quantity + source.Quantity, "
                + "               target.AddedDate = GETDATE() " // Cập nhật thời gian mới
                + "WHEN NOT MATCHED THEN "
                + "    INSERT (UserID, ProductID, Quantity, AddedDate) "
                + "    VALUES (source.UserID, source.ProductID, source.Quantity, GETDATE());";

        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setString(2, productID);
            stmt.setInt(3, quantity);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CartDTO> getAllCartById(int userID) {
        List<CartDTO> list = new ArrayList<>();
        String sql = "SELECT c.ProductID, c.Quantity, c.AddedDate, p.ProductName, p.ImageURL, p.Price "
                + "FROM Cart c "
                + "JOIN Products p ON c.ProductID = p.ProductID "
                + "WHERE c.UserID = ?";

        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    String productID = rs.getString("ProductID");
                    int quantity = rs.getInt("Quantity");
                    Timestamp addedDate = rs.getTimestamp("AddedDate");
                    String productName = rs.getString("ProductName");
                    String imgURL = rs.getString("ImageURL");
                    BigDecimal price = rs.getBigDecimal("Price");
                    list.add(new CartDTO(userID, productID, quantity, addedDate, productName, imgURL, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        MyDAO dao = new MyDAO();
        List<CartDTO> list = dao.getAllCartById(4);
        for (CartDTO o : list) {
            System.out.println(o.getPrice());
        }
        System.out.println(dao.getTotalPagesByBrand("Nike"));
    }

    public boolean deleteCart(int userID, String productID) {
        boolean isDeleted = false;

        try {
            Connection con = DBUtils.getConnection();

            String sql = "DELETE FROM Cart WHERE UserID = ? AND ProductID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userID);
            stmt.setString(2, productID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true; // Nếu có hàng bị xóa thành công
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted; // Trả về true nếu xóa thành công, false nếu không
    }

    

}
