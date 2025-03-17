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
public class OrderDetailDAO {

    public List<OrderDetailDTO> getAllOrderDetails() {

        List<OrderDetailDTO> list = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT * FROM OrderDetails";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    OrderDetailDTO orderdetail = new OrderDetailDTO(
                            rs.getInt("OrderDetailID"),
                            rs.getString("OrderID"),
                            rs.getString("ProductID"),
                            rs.getInt("Quantity"),
                            rs.getDouble("UnitPrice")
                    );
                    list.add(orderdetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public OrderDetailDTO getOrderDetailById(int id) {
        String sql = "SELECT * FROM OrderDetails WHERE OrderDetailID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderDetailDTO(
                        rs.getInt("OrderDetailID"),
                        rs.getString("OrderID"),
                        rs.getString("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getDouble("UnitPrice")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean createOrderDetail(OrderDetailDTO orderDetail) {
        String sql = "INSERT INTO OrderDetails (OrderDetailID, OrderID, ProductID, Quantity, UnitPrice) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getOrderDetailID());
            stmt.setString(2, orderDetail.getOrderID());
            stmt.setString(3, orderDetail.getProductID());
            stmt.setInt(4, orderDetail.getQuantity());
            stmt.setDouble(5, orderDetail.getUnitPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateOrderDetail(OrderDetailDTO orderDetail) {
        String sql = "UPDATE OrderDetails SET OrderID = ?, ProductID = ?, Quantity = ?, UnitPrice = ? WHERE OrderDetailID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, orderDetail.getOrderID());
            stmt.setString(2, orderDetail.getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getUnitPrice());
            stmt.setInt(5, orderDetail.getOrderDetailID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteOrderDetail(int id) {
        String sql = "DELETE FROM OrderDetails WHERE OrderDetailID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
