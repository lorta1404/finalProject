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
public class PaymentDAO {

    public List<PaymentDTO> getAllPayments() {

        List<PaymentDTO> list = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT * FROM Payments";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    PaymentDTO payment = new PaymentDTO(
                            rs.getString("PaymentID"),
                            rs.getString("OrderID"),
                            rs.getString("PaymentMethod"),
                            rs.getTimestamp("PaymentDate"),
                            rs.getString("PaymentStatus")
                    );
                    list.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public PaymentDTO getPaymentById(String id) {
        String sql = "SELECT * FROM Payments WHERE PaymentID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PaymentDTO(
                        rs.getString("PaymentID"),
                        rs.getString("OrderID"),
                        rs.getString("PaymentMethod"),
                        rs.getTimestamp("PaymentDate"),
                        rs.getString("PaymentStatus")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean createPayment(PaymentDTO payment) {
        String sql = "INSERT INTO Payments (PaymentID, OrderID, PaymentMethod, PaymentDate, PaymentStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, payment.getPaymentID());
            stmt.setString(2, payment.getOrderID());
            stmt.setString(3, payment.getPaymentMethod());
            stmt.setTimestamp(4, payment.getPaymentDate());
            stmt.setString(5, payment.getPaymentStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updatePayment(PaymentDTO payment) {
        String sql = "UPDATE Payments SET OrderID = ?, PaymentMethod = ?, PaymentDate = ?, PaymentStatus = ? WHERE PaymentID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, payment.getOrderID());
            stmt.setString(2, payment.getPaymentMethod());
            stmt.setTimestamp(3, payment.getPaymentDate());
            stmt.setString(4, payment.getPaymentStatus());
            stmt.setString(5, payment.getPaymentID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deletePayment(String id) {
        String sql = "DELETE FROM Payments WHERE PaymentID = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
