package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDAO {

    public static ObservableList<Type> getAllType(){
        ObservableList<Type> typeList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type_ID, Type FROM types";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int typeID = rs.getInt("Type_ID");
                String typeName = rs.getString("Type");
                //ISSUE BECAUSE OF ABSTRACT
                //Type t = new Type(typeID, typeName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return typeList;
    }


}
