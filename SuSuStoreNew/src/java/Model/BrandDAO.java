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
public class BrandDAO {
    public List<BrandDTO> getAllBrands() {
        List<BrandDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Brands";

        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BrandDTO brand = new BrandDTO(
                        rs.getString("BrandID"),
                        rs.getString("BrandName")
                );
                list.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public BrandDTO getBrandById(String id) {
        String sql = "SELECT * FROM Brands WHERE BrandID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new BrandDTO(rs.getString("BrandID"), rs.getString("BrandName"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean createBrand(BrandDTO brand) {
        String sql = "INSERT INTO Brands (BrandID, BrandName) VALUES (?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, brand.getBrandID());
            stmt.setString(2, brand.getBrandName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateBrand(BrandDTO brand) {
        String sql = "UPDATE Brands SET BrandName = ? WHERE BrandID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, brand.getBrandName());
            stmt.setString(2, brand.getBrandID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteBrand(String id) {
        String sql = "DELETE FROM Brands WHERE BrandID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
