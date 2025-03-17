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
public class CartDAO {
    

        public List<CartDTO> getAllCarts() {
        List<CartDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Cart";

        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CartDTO cart = new CartDTO(
                        rs.getInt("UserID"),
                        rs.getString("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getTimestamp("AddedDate")
                );
                list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
