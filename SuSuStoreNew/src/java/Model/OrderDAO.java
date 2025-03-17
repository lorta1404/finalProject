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
public class OrderDAO {

    public List<OrderDTO> getAllUsers() {

        List<OrderDTO> list = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT * FROM Orders";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    OrderDTO order = new OrderDTO(
                            rs.getString("OrderID"),
                            rs.getInt("UserID"),
                            rs.getTimestamp("OrderDate"),
                            rs.getString("ShippingAddress"),
                            rs.getString("OrderStatus"),
                            rs.getBigDecimal("TotalAmount")
                    );
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public OrderDTO getOrderById(String id) {
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderDTO(
                        rs.getString("OrderID"),
                        rs.getInt("UserID"),
                        rs.getTimestamp("OrderDate"),
                        rs.getString("ShippingAddress"),
                        rs.getString("OrderStatus"),
                        rs.getBigDecimal("TotalAmount")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean createOrder(OrderDTO order) {
        String sql = "INSERT INTO Orders (OrderID, UserID, OrderDate, ShippingAddress, OrderStatus, TotalAmount) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, order.getOrderID());
            stmt.setInt(2, order.getUserID());
            stmt.setTimestamp(3, order.getOrderDate());
            stmt.setString(4, order.getShippingAddress());
            stmt.setString(5, order.getOrderStatus());
            stmt.setBigDecimal(6, order.getTotalAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateOrder(OrderDTO order) {
        String sql = "UPDATE Orders SET UserID = ?, OrderDate = ?, ShippingAddress = ?, OrderStatus = ?, TotalAmount = ? WHERE OrderID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, order.getUserID());
            stmt.setTimestamp(2, order.getOrderDate());
            stmt.setString(3, order.getShippingAddress());
            stmt.setString(4, order.getOrderStatus());
            stmt.setBigDecimal(5, order.getTotalAmount());
            stmt.setString(6, order.getOrderID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteOrder(String id) {
        String sql1 = "DELETE FROM OrderDetails WHERE OrderID = ?";
        String sql2 = "DELETE FROM Orders WHERE OrderID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt1 = con.prepareStatement(sql1);
             PreparedStatement stmt2 = con.prepareStatement(sql2)) {
            con.setAutoCommit(false);
            stmt1.setString(1, id);
            stmt1.executeUpdate();
            stmt2.setString(1, id);
            boolean result = stmt2.executeUpdate() > 0;
            con.commit();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
