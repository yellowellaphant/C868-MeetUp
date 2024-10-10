package DAO;


import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.External;
import model.Internal;
import model.Subtype;
import model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TypeDAO {

    public static ObservableList<Type> getAllType() {
        ObservableList<Type> typeList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM types";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int typeID = rs.getInt("Type_ID");
                String typeName = rs.getString("Type");

                if (typeID == 1) {
                    Internal i = new Internal(typeID, typeName);
                    typeList.add(i);
                } else if (typeID == 2) {
                    External e = new External(typeID, typeName);
                    typeList.add(e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return typeList;
    }


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
                subtypeList.add(s);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subtypeList;
    }


    public static Subtype returnSubtype(int subTypeID) {
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

    public static Subtype returnSubtype2(int subTypeID) {
        try {
            String sql = "SELECT Subtype_ID, Subtype, Type_ID FROM subtypes WHERE Subtype_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, subTypeID);
            ps.execute();
            ResultSet rs = ps.getResultSet();


            rs.next();
            int searchedSubTypeID = rs.getInt("Subtype_ID");
            String subTypeName = rs.getString("Subtype");
            int typeID = rs.getInt("Type_ID");
            Subtype s = new Subtype(searchedSubTypeID, subTypeName, typeID);
            return s;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns Type using given subtypeID
     * @param subtypeID subtypeID
     * @return type
     */
    public static Type returnTypeBySubtypeID(int subtypeID) {
        try {
            String sql = "SELECT t.Type_ID, t.Type FROM types t " +
                    "JOIN subtypes s ON t.Type_ID = s.Type_ID " +
                    "WHERE s.Subtype_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, subtypeID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // Check if result set is not empty
                int typeID = rs.getInt("Type_ID");
                String typeName = rs.getString("Type");

                if (typeID == 1) {
                    return new Internal(typeID, typeName);
                } else if (typeID == 2) {
                    return new External(typeID, typeName);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving Type by Subtype ID: " + subtypeID, e);
        }

        // Handle case where no data is found for the given subtypeID
        throw new RuntimeException("No Type found for Subtype ID: " + subtypeID);
    }

    public static Type returnType(int typeID) {
        Type t = null;
        try {
            String sql = "Select Type_ID, Type FROM types WHERE Type_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, typeID);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedTypeID = rs.getInt("Type_ID");
            String searchedTypeName = rs.getString("Type");

            if (typeID == 1) {
                Internal i = new Internal(searchedTypeID, searchedTypeName);
                t = i;
            } else if (typeID == 2) {
                External e = new External(searchedTypeID, searchedTypeName);
                t = e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t;
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

