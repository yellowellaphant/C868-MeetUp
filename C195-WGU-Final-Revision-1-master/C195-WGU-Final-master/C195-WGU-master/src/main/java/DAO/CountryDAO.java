package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * accesses country data from database
 *
 * @author Ella Upchurch
 */
public class CountryDAO {

    /**
     * Retrieves all countries from countries table
     * and adds data to an observable list
     *
     * @return country list
     */
    public static ObservableList<Country> getAllCountry() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement country = JDBC.conn.prepareStatement(sql);
            ResultSet rs = country.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryID, countryName);
                countryList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryList;
    }

    /**
     * retrieves country ID and country name based on given countryID
     *
     * @param countryID country ID
     * @return countryID and countryName
     */
    public static Country returnCountry(int countryID) {
        try {
            String sql = "SELECT Country_ID, Country FROM countries WHERE country_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, countryID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchCountryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country c = new Country(searchCountryID, countryName);
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Observable List displays country totals for report
     *
     * @return customerCountry observable list
     */
    public static ObservableList<Country> countryTotals() {
        ObservableList<Country> customerCountry = FXCollections.observableArrayList();
        try {
            String sql = "SELECT countries.Country, COUNT(customers.Customer_ID) AS Count FROM countries INNER JOIN first_level_divisions ON  countries.Country_ID = first_level_divisions.Country_ID INNER JOIN customers ON customers.Division_ID = first_level_divisions.Division_ID group by countries.Country";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String countryMonth = rs.getString("Country");
                int countryMonthTotal = rs.getInt("Count");
                //Country results = new Country(countryMonth, countryMonthTotal); FIX ADD TO COUNTRY MODEL
                //customerCountry.add(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCountry;
    }



}
