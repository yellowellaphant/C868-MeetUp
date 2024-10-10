package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * accesses user data from database. Used to check login credentials
 *
 * @author Ella Upchurch
 */
public class UserDAO {

    /**
     * gets all users from database and adds them to an observable list
     *
     * @return an observable list of all users
     */
    public static ObservableList<User> getUserList() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String username = rs.getString("User_Name");

                User u = new User(userID, username);
                userList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /**
     * retrieves user ID based on given username
     *
     * @param username username
     * @return userID
     * @throws SQLException sqlexception
     */
    public static int getUserID(String username) throws SQLException {
        int userID = 0;
        String sqlStatement = "SELECT User_ID, User_Name FROM users WHERE User_Name = '" + username + "'";
        PreparedStatement ps = JDBC.conn.prepareStatement(sqlStatement);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            userID = rs.getInt("User_ID");
            username = rs.getString("User_Name");
        }
        return userID;
    }

    /**
     * returns username matching the given user ID
     *
     * @param userID userID
     * @return username
     */
    public static User returnUserID(int userID) {
        try {
            String sql = "SELECT User_ID, User_Name FROM users WHERE User_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedUserID = rs.getInt("User_ID");
            String username = rs.getString("User_Name");
            User u = new User(searchedUserID, username);
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves username and password from database
     *
     * @param User_Name username
     * @param Password password
     * @return boolean: true/false
     */
    public static boolean userLogin(String User_Name, String Password) {
        try (PreparedStatement ps = JDBC.database().prepareStatement("SELECT * FROM Users WHERE User_Name = ? AND Password = ?")) {
            ps.setString(1, User_Name);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * validates username
     *
     * @param User_Name username
     * @return boolean: true/false
     */
    public static boolean usernameValidation(String User_Name) {
        try (PreparedStatement ps =JDBC.database().prepareStatement("SELECT * FROM Users WHERE BINARY User_Name = ?")) {
            ps.setString(1, User_Name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * validates password
     *
     * @param Password password
     * @return boolean: true/false
     */
    public static boolean passwordValidation(String Password) {
        try (PreparedStatement ps = JDBC.database().prepareStatement("SELECT * FROM Users WHERE BINARY Password = ?")) {
            ps.setString(1, Password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
