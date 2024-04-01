package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * accesses first level division data from database
 *
 * @author Ella Upchurch
 */
public class FirstLevelDivDAO {

    /**
     * retrieves all division ID's and puts them into an observable list
     *
     * @return divisionList
     */
    public static ObservableList<FirstLevelDivision> getAllDivisionID() {

        ObservableList<FirstLevelDivision> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement divID = JDBC.conn.prepareStatement(sql);
            ResultSet rs = divID.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                Timestamp Create_Date = rs.getTimestamp("Create_Date");
                LocalDateTime createDate = Create_Date.toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                Timestamp Last_Updated = rs.getTimestamp("Last_Update");
                LocalDateTime lastUpdate = Last_Updated.toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                FirstLevelDivision d = new FirstLevelDivision(divisionID, division, countryID, createDate, createdBy,
                        lastUpdate, lastUpdatedBy);
                divisionList.add(d);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divisionList;
    }

    /**
     * retrieves division name based on given division ID
     *
     * @param divisionID
     * @return f divisionID + division
     */
    public static FirstLevelDivision returnDivisionLevel(int divisionID) {
        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, divisionID);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedDivisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            FirstLevelDivision f = new FirstLevelDivision(searchedDivisionID, division);
            return f;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * retrieves associated division ID + name based on given country ID
     *
     * @param countryID
     * @return divisionCountryOpt
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> displayDivision(int countryID) throws SQLException {

        ObservableList<FirstLevelDivision> divisionCountryOpt = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryID;
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();

        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            countryID = rs.getInt("Country_ID");
            Timestamp Create_Date = rs.getTimestamp("Create_Date");
            LocalDateTime createDate = Create_Date.toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            Timestamp Last_Updated = rs.getTimestamp("Last_Update");
            LocalDateTime lastUpdated = Last_Updated.toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            FirstLevelDivision l = new FirstLevelDivision(divisionID, division, countryID, createDate,
                    createdBy, lastUpdated, lastUpdatedBy);
            divisionCountryOpt.add(l);
        }
        return divisionCountryOpt;
    }


}
