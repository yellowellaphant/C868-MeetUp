package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.TimeZone;

/**
 * accesses appointment data from database
 *
 * @author Ella Upchurch
 */
public class AppointmentDAO {
    static ZoneId utcZone = ZoneId.of("UTC"); // Define UTC ZoneId
    static ZoneId userTimeZone = ZoneId.systemDefault(); //Define user timezone
    /**
     * retrieves all data from appointments in database and puts it into an observable list
     * @return appointmentList
     */
    public static ObservableList<Appointment> getAppointmentList() {

        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int aptContactID = rs.getInt("Contact_ID");
                String aptContact = rs.getString("Contact_Name");
                String aptType = rs.getString("Type");
                LocalDateTime aptStart = rs.getTimestamp("Start").toLocalDateTime();
                //Timestamp aptStart = rs.getTimestamp("Start");
                LocalDateTime aptEnd = rs.getTimestamp("End").toLocalDateTime();

                int aptCustomerID = rs.getInt("Customer_ID");
                int aptUserID = rs.getInt("User_ID");
                String aptLocation = rs.getString("Location");

                // Convert to local time
                ZoneId userTimeZone = ZoneId.systemDefault();
                System.out.println(userTimeZone);

                //aptStart = aptStart.atZone(userTimeZone).toLocalDateTime();
                //aptEnd = aptEnd.atZone(userTimeZone).toLocalDateTime();

                // Convert start and end times to the user's local time zone
                aptStart = aptStart.atZone(utcZone).withZoneSameInstant(userTimeZone).toLocalDateTime();
                System.out.println(aptStart);
                aptEnd = aptEnd.atZone(utcZone).withZoneSameInstant(userTimeZone).toLocalDateTime();
                System.out.println(aptEnd);

                Appointment a = new Appointment(appointmentID, title, description, aptContactID, aptContact, aptType,
                        aptStart, aptEnd, aptCustomerID,aptUserID, aptLocation);
                appointmentList.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    /**
     * Adds a new appointment to the database based on given data values
     *
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment start time
     * @param end appointment end time
     * @param aptCustomerID customer ID for appointment
     * @param aptUserID user ID for appointment
     * @param aptContactID contact ID for appointment
     * @throws SQLException
     */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,
                                      int aptCustomerID, int aptUserID, int aptContactID) throws SQLException {
        try {
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement aptAdd = JDBC.conn.prepareStatement(sql);

            aptAdd.setString(1, title);
            aptAdd.setString(2, description);
            aptAdd.setString(3, location);
            aptAdd.setString(4, type);

            /*Instant startInstant = start.atZone(ZoneId.systemDefault()).toInstant();
            Instant endInstant = end.atZone(ZoneId.systemDefault()).toInstant();

            aptAdd.setTimestamp(5, Timestamp.from(startInstant));
            aptAdd.setTimestamp(6, Timestamp.from(endInstant));*/
            //aptAdd.setTimestamp(5, Timestamp.valueOf(start));
            //aptAdd.setTimestamp(6, Timestamp.valueOf(end));

            aptAdd.setObject(5, start);
            aptAdd.setObject(6, end);

            aptAdd.setInt(7, aptCustomerID);
            aptAdd.setInt(8, aptUserID);
            aptAdd.setInt(9, aptContactID);

            aptAdd.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * deletes an appointment from the database based on a given appointment ID
     *
     * @param appointmentID appointment ID
     */
    public static void deleteAppointment(int appointmentID) {
        try {
            String sqldelete = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement aptDelete = JDBC.conn.prepareStatement(sqldelete);
            aptDelete.setInt(1, appointmentID);
            aptDelete.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates appointment in database with given values
     *
     * @param appointmentID appointment ID
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment start time
     * @param end appointment end time
     * @param aptCustomerID customer ID for appointment
     * @param aptUserID user ID for appointment
     * @param aptContactID contact ID for appointment
     */
    public static void updateAppointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start,
                                         LocalDateTime end, int aptCustomerID, int aptUserID, int aptContactID) {
        try {

            //LocalDateTime startConverted = start.atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            //LocalDateTime endConverted = end.atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement aptUpdate = JDBC.conn.prepareStatement(sql);

            aptUpdate.setString(1, title);
            aptUpdate.setString(2, description);
            aptUpdate.setString(3, location);
            aptUpdate.setString(4, type);
            aptUpdate.setTimestamp(5, Timestamp.valueOf(start));
            aptUpdate.setTimestamp(6, Timestamp.valueOf(end));
            aptUpdate.setInt(7, aptCustomerID);
            aptUpdate.setInt(8, aptUserID);
            aptUpdate.setInt(9, aptContactID);
            aptUpdate.setInt(10, appointmentID);

            aptUpdate.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of appointments based on associated user ID
     *
     * @param userID
     * @return userAppointments
     */
    public static ObservableList<Appointment> getUserAppointments(int userID) {
        ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Appointments WHERE User_ID = '" + userID + "'";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                //LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                //LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime().atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime().atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

                int aptCustomerID = rs.getInt("Customer_ID");
                int aptUserID = rs.getInt("User_ID");
                int aptContactID = rs.getInt("Contact_ID");
                Appointment results = new Appointment(appointmentID, title, description, location, type, start, end,
                        aptCustomerID, aptUserID, aptContactID);
                userAppointments.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAppointments;
    }

    /**
     * Retrieves a list of appointments based on associated contact ID
     * @param contactID
     * @return contactAppointments
     */
    public static ObservableList<Appointment> getContactAppointments(int contactID) {
        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID  = '" + contactID + "'";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int aptContactID = rs.getInt("Contact_ID");
                String aptContact = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int aptCustomerID = rs.getInt("Customer_ID");
                int aptUserID = rs.getInt("User_ID");
                String location = rs.getString("Location");

                Appointment results = new Appointment(appointmentID, title, description, aptContactID, aptContact, type, start, end,
                        aptCustomerID, aptUserID, location);
                contactAppointments.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactAppointments;
    }


    /**
     * retrieves a list of appointments for the week
     * @return weekList
     */
    public static ObservableList<Appointment> getWeeklyApt() {
        ObservableList<Appointment> weekList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE YEARWEEK(START) = YEARWEEK(NOW()) ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int aptContactID = rs.getInt("Contact_ID");
                String aptContact = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                //LocalDateTime aptStart = rs.getTimestamp("Start").toLocalDateTime();
                //LocalDateTime aptEnd = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime aptStart = rs.getTimestamp("Start").toLocalDateTime().atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime aptEnd = rs.getTimestamp("End").toLocalDateTime().atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

                int aptCustomerID = rs.getInt("Customer_ID");
                int aptUserID = rs.getInt("User_ID");
                String aptLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentID, title, description, aptContactID, aptContact,
                        type, aptStart, aptEnd, aptCustomerID, aptUserID, aptLocation);
                weekList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weekList;
    }

    /**
     * retrieves a list of appointments for the month
     * @return monthList
     */
    public static ObservableList<Appointment> getMonthlyApt() {
        ObservableList<Appointment> monthList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(START) = MONTH(NOW()) ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int aptContactID = rs.getInt("Contact_ID");
                String aptContact = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                //LocalDateTime aptStart = rs.getTimestamp("Start").toLocalDateTime();
                //LocalDateTime aptEnd = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime aptStart = rs.getTimestamp("Start").toLocalDateTime().atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime aptEnd = rs.getTimestamp("End").toLocalDateTime().atZone(utcZone).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

                int aptCustomerID = rs.getInt("Customer_ID");
                int aptUserID = rs.getInt("User_ID");
                String aptLocation = rs.getString("Location");
                Appointment month = new Appointment(appointmentID, title, description, aptContactID, aptContact,
                        type, aptStart, aptEnd, aptCustomerID, aptUserID, aptLocation);
                monthList.add(month);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monthList;
    }

    /**
     * counts and totals appointments by type
     * @return aptTypeTotalList
     */
    public static ObservableList<Appointment> getAptTypeTotal() {
        ObservableList<Appointment> aptTypeTotalList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type, Count(*) AS NUM FROM appointments GROUP BY Type";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String aptType = rs.getString("Type");
                int aptTypeTotal = rs.getInt("NUM");
                Appointment results = new Appointment(aptType, aptTypeTotal);
                aptTypeTotalList.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aptTypeTotalList;
    }

    /**
     * counts and totals appointments by month
     * @return aptMonthTotalList
     */
    public static ObservableList<Appointment> getAptMonthTotal() {
        ObservableList<Appointment> aptMonthTotalList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT(MONTHNAME(Start)) AS Month, Count(*) AS NUM FROM appointments GROUP BY Month";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String aptType = rs.getString("Month");
                int aptTypeTotal = rs.getInt("NUM");
                Appointment results = new Appointment(aptType, aptTypeTotal);
                aptMonthTotalList.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aptMonthTotalList;
    }


    //Retrieves data on existing appointments to check for overlapping appointments
    //no longer in use

    /*public static boolean overlapCheck(int appointmentID, int aptCustomerID, LocalDateTime start, LocalDateTime end) {
        try {
            String sql = "SELECT * FROM appointments WHERE ((Start BETWEEN ? AND ?) OR (End BETWEEN ? AND ?) OR (? BETWEEN Start AND End) OR (? BETWEEN Start AND End)) AND Appointment_ID != ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(start));
            ps.setTimestamp(2, Timestamp.valueOf(end));
            ps.setTimestamp(3, Timestamp.valueOf(start));
            ps.setTimestamp(4, Timestamp.valueOf(end));
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, appointmentID); // Exclude the appointment being updated
            ResultSet rs = ps.executeQuery();

            boolean overlap;
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Appointment conflicts with an existing customer appointment");
                alert.showAndWait();
                overlap = true;
            } else {
                overlap = false;
            }
            return overlap;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/




}
