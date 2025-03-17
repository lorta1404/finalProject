package Model;

import Model.UserDTO;
import Utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    //1.
    private String Url = "jdbc:sqlserver://your_server:1433;databaseName=your_database"; // Replace with your database details
    private String User = "your_user";
    private String Password = "your_password";

    public List<UserDTO> getAllUsers() {

        List<UserDTO> list = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();

            String sql = "SELECT UserID, Username, Email, Password, FullName, PhoneNumber, Address, Role FROM Users";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    UserDTO user = new UserDTO(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Email"),
                            rs.getString("Password"), // Handle passwords with care
                            rs.getString("FullName"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Address"),
                            rs.getString("Role")
                    );
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserDTO getUserById(int id) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("FullName"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Address"),
                            rs.getString("Role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createUser(UserDTO user) {
        String sql = "INSERT INTO Users (Username, Email, Password, FullName, PhoneNumber, Address, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFullName());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(UserDTO user) {
        String sql = "UPDATE Users SET Username=?, Email=?, Password=?, FullName=?, PhoneNumber=?, Address=?, Role=? WHERE UserID=?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFullName());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getRole());
            stmt.setInt(8, user.getUserID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE UserID=?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserDTO checkLogin(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("FullName"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Address"),
                            rs.getString("Role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserDTO> searchUserByUsername(String username) {
        List<UserDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE Username LIKE ?";

        try (Connection con = DBUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + username + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserDTO user = new UserDTO(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("FullName"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Address"),
                            rs.getString("Role")
                    );
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

  
}
