package DAO;


import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Subtype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TypeDAO {


    public static ObservableList<Subtype> getAllSubtype() {
        ObservableList<Subtype> subtypeList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM subtypes";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                int subTypeID = rs.getInt("Subtype_ID");
                String subTypeName = rs.getString("Subtype");
                int typeID = rs.getInt("Type_ID");


                Subtype s = new Subtype(subTypeID, subTypeName, typeID);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subtypeList;
    }


    public static Subtype returnSubtypeName(int subTypeID) {
        try {
            String sql = "SELECT Subtype_ID, Subtype FROM subtypes WHERE Subtype_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, subTypeID);
            ps.execute();
            ResultSet rs = ps.getResultSet();


            rs.next();
            int searchedSubTypeID = rs.getInt("Subtype_ID");
            String subTypeName = rs.getString("Subtype");
            Subtype s = new Subtype(searchedSubTypeID, subTypeName);
            return s;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<Subtype> displaySubtype(int typeID) throws SQLException {


        ObservableList<Subtype> subTypeOList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM subtypes WHERE Type_ID =" + typeID;
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();


        while (rs.next()) {
            int subTypeID = rs.getInt("Subtype_ID");
            String subTypeName = rs.getString("Subtype");
            typeID = rs.getInt("Type_ID");


            Subtype s = new Subtype(subTypeID, subTypeName, typeID);
            subTypeOList.add(s);


        }
        return subTypeOList;
    }


}

