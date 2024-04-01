package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * accesses customer data from database
 *
 * @author Ella Upchurch
 */
public class CustomerDAO {

    /**
     * retrieves all customer information and adds it into an observable list
     * @return customerList
     */
    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Create_Date, customers.Last_Update, customers.Created_By, customers.Last_Updated_By, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY customers.Customer_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String createdBy = rs.getString("Created_By");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerDivisionID = rs.getInt("Division_ID");
                String customerDivisionName = rs.getString("Division");
                int customerCountryID = rs.getInt("Country_ID");
                String customerCountryName = rs.getString("Country");
                Customer c = new Customer(customerID, customerName, address, postalCode, phone, createdBy, lastUpdatedBy,
                        customerDivisionID, customerDivisionName, customerCountryID, customerCountryName);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    /**
     * Adds new customer to the database, customer ID is created by database
     *
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param lastUpdate
     * @param divisionID
     * @throws SQLException
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phone, LocalDateTime createDate,
                                   LocalDateTime lastUpdate, int divisionID) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement addCust = JDBC.conn.prepareStatement(sql);
        addCust.setString(1, customerName);
        addCust.setString(2, address);
        addCust.setString(3, postalCode);
        addCust.setString(4, phone);
        addCust.setTimestamp(5, Timestamp.valueOf(createDate));
        addCust.setTimestamp(6, Timestamp.valueOf(lastUpdate));
        addCust.setInt(7, divisionID);
        addCust.executeUpdate();
    }

    /**
     * deletes customer from database based on given customer ID
     * @param customerID
     */
    public static void deleteCustomer(int customerID) {
        try {
            String sqldelete = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement deleteCust = JDBC.conn.prepareStatement(sqldelete);
            deleteCust.setInt(1, customerID);
            deleteCust.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates customer information in the database based on given customer ID
     *
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param lastUpdatedBy
     * @param lastUpdated
     * @param customerDivisionID
     */
    public static void updateCustomer(int customerID, String customerName, String address, String postalCode, String phone,
                                      String lastUpdatedBy, Timestamp lastUpdated, int customerDivisionID) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Last_Update = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement updateCust = JDBC.conn.prepareStatement(sql);
            updateCust.setString(1, customerName);
            updateCust.setString(2, address);
            updateCust.setString(3, postalCode);
            updateCust.setString(4, phone);
            updateCust.setString(5, lastUpdatedBy);
            updateCust.setTimestamp(6, lastUpdated);
            updateCust.setInt(7, customerDivisionID);
            updateCust.setInt(8, customerID);

            updateCust.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns customer name based on given customer ID
     *
     * @param customerID
     * @return c CustomerID + CustomerName
     * @throws SQLException
     */
    public static Customer returnCustomerList(int customerID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.execute();
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int searchCustomerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");

            Customer c = new Customer(searchCustomerID, customerName);
            return c;
        }
        return null;
    }

}
