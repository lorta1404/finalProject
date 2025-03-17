/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.DBUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO {

    private String jdbcUrl = "jdbc:sqlserver://your_server:1433;databaseName=your_database"; // Replace with your database details
    private String jdbcUser = "your_user";
    private String jdbcPassword = "your_password";

    

    public List<CategoryDTO> getAllCategories() {

        List<CategoryDTO> list = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT * FROM Categories";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CategoryDTO category = new CategoryDTO(
                            rs.getString("CategoryID"),
                            rs.getString("CategoryName")
                    );
                    list.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

     // Lấy danh mục theo ID
    public CategoryDTO getCategoryById(String id) {
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CategoryDTO(rs.getString("CategoryID"), rs.getString("CategoryName"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // Thêm danh mục mới
    public boolean createCategory(CategoryDTO category) {
        String sql = "INSERT INTO Categories (CategoryID, CategoryName) VALUES (?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryID());
            stmt.setString(2, category.getCategoryName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Cập nhật danh mục
    public boolean updateCategory(CategoryDTO category) {
        String sql = "UPDATE Categories SET CategoryName = ? WHERE CategoryID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getCategoryID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Xóa danh mục
    public boolean deleteCategory(String id) {
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
