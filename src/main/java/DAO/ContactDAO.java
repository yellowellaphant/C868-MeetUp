package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * accesses contact data from database
 *
 * @author Ella Upchurch
 */
public class ContactDAO {

    /**
     * retrieves all contacts from the database and puts them into an observable list
     *
     * @return contactList
     */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement contacts = JDBC.conn.prepareStatement(sql);
            ResultSet rs = contacts.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contact c = new Contact(contactID, contactName, contactEmail);
                contactList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

    /**
     * retrieves contact info based on given contact ID
     *
     * @param contactID
     * @return c
     */
    public static Contact returnContactList(int contactID) {
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, contactID);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedContactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            Contact c = new Contact(searchedContactID, contactName, contactEmail);
            return c;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * retrieves contact ID based on given contact name
     *
     * @param contactName
     * @return contactID
     * @throws SQLException
     */
    public static int returnContactID(String contactName) throws SQLException {
        int contactID = 0;
        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contactID = rs.getInt("Contact_ID");
        }
        return contactID;
    }


}
