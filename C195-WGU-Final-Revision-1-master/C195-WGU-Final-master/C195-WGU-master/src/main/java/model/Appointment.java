package model;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * This class creates an appointment model
 *
 * @author Ella Upchurch
 */
public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private int subtypeID;
    private String subtype;
    private LocalDateTime start;
    private LocalDateTime end;
    private int aptCustomerID;
    private int aptUserID;
    private int aptContactID;
    private String aptContactName;
    private int aptTypeTotal;

    private String formattedStart;
    private String formattedEnd;

    /**
     * Constructor for Appointment
     *
     * @param appointmentID .
     * @param title .
     * @param description .
     * @param location .
     * @param subtypeID .
     * @param start .
     * @param end .
     * @param aptCustomerID .
     * @param aptUserID .
     * @param aptContactID .
     * @param createDate .
     * @param createdBy .
     * @param lastUpdate .
     * @param lastUpdatedBy .
     */
    public Appointment(int appointmentID, String title, String description, String location, int subtypeID,
                       LocalDateTime start, LocalDateTime end, int aptCustomerID, int aptUserID, int aptContactID,
                       LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.subtypeID = subtypeID;
        this.start = start;
        this.end = end;
        this.aptCustomerID = aptCustomerID;
        this.aptUserID = aptUserID;
        this.aptContactID = aptContactID;
    }

    /**
     * Constructor for Appointment
     *
     * @param appointmentID .
     * @param title .
     * @param description .
     * @param location .
     * @param subtypeID .
     * @param start .
     * @param end .
     * @param aptCustomerID .
     * @param aptUserID .
     * @param aptContactID .
     */
    public Appointment(int appointmentID, String title, String description, String location, int subtypeID,
                       LocalDateTime start, LocalDateTime end, int aptCustomerID, int aptUserID, int aptContactID) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.subtypeID = subtypeID;
        this.start = start;
        this.end = end;
        this.aptCustomerID = aptCustomerID;
        this.aptUserID = aptUserID;
        this.aptContactID = aptContactID;
    }

    /**
     * Constructor for Appointment
     *
     * @param appointmentID .
     * @param title .
     * @param description .
     * @param aptContactID .
     * @param aptContactName .
     * @param subtypeID .
     * @param start .
     * @param end .
     * @param aptCustomerID .
     * @param aptUserID .
     * @param location .
     */
    public Appointment(int appointmentID, String title, String description, int aptContactID, String aptContactName,
                       int subtypeID, LocalDateTime start, LocalDateTime end, int aptCustomerID, int aptUserID, String location) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.aptContactID = aptContactID;
        this.aptContactName = aptContactName;
        this.subtypeID = subtypeID;
        this.start = start;
        this.end = end;
        this.aptCustomerID = aptCustomerID;
        this.aptUserID = aptUserID;
        this.location = location;


    }

    /**
     * Constructor for Appointment
     * @param appointmentID appointmentID
     * @param title title
     * @param description description
     * @param aptContactID aptContactID
     * @param aptContactName aptContactName
     * @param subtypeID subtypeID
     * @param subtype subtype
     * @param start start
     * @param end end
     * @param aptCustomerID customerID
     * @param aptUserID aptUserID
     * @param location location
     */
    public Appointment(int appointmentID, String title, String description, int aptContactID, String aptContactName,
                       int subtypeID, String subtype, LocalDateTime start, LocalDateTime end, int aptCustomerID, int aptUserID, String location) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.aptContactID = aptContactID;
        this.aptContactName = aptContactName;
        this.subtypeID = subtypeID;
        this.subtype = subtype;
        this.start = start;
        this.end = end;
        this.aptCustomerID = aptCustomerID;
        this.aptUserID = aptUserID;
        this.location = location;


    }

    /**
     * Constructor for appointment
     *
     * @param subtypeID
     * @param aptTypeTotal
     */
    public Appointment(int subtypeID, int aptTypeTotal) {
        this.subtypeID = subtypeID;
        this.aptTypeTotal = aptTypeTotal;
    }

    public Appointment(int appointmentID, String title, String description, String location, int subtypeID, String formattedStart,
                       String formattedEnd, int aptCustomerID, int aptUserID, int aptContactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.aptContactID = aptContactID;
        this.subtypeID = subtypeID;
        this.formattedStart = formattedStart;
        this.formattedEnd = formattedEnd;
        this.aptCustomerID = aptCustomerID;
        this.aptUserID = aptUserID;
        this.location = location;
    }

    public Appointment(int appointmentID, String title, String description, String location, int subtypeID, String subtype,
                       LocalDateTime start, LocalDateTime end, int aptCustomerID, int aptUserID, int aptContactID) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.aptContactID = aptContactID;
        this.subtypeID = subtypeID;
        this.subtype = subtype;
        this.start = start;
        this.end = end;
        this.aptCustomerID = aptCustomerID;
        this.aptUserID = aptUserID;
        this.location = location;
    }

    /**
     * The getter for appointment ID
     * @return appointment ID
     */
    public int getAppointmentID() {return appointmentID;}
    /**
     * The setter for appointment ID
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}

    /**
     * The getter for appointment title
     * @return appointment title
     */
    public String getTitle() {return title;}
    /**
     * The setter for appointment title
     * @param title
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * The getter for appointment description
     * @return appointment description
     */
    public String getDescription() {return description;}
    /**
     * The setter for appointment description
     * @param description
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * The getter for appointment location
     * @return appointment location
     */
    public String getLocation() {return location;}
    /**
     * The setter for appointment location
     * @param location
     */
    public void setLocation(String location) {this.location = location;}

    /**
     * The getter for appointment start time
     * @return appointment start time
     */
    public LocalDateTime getStart() {return start;}
    /**
     * The setter for appointment start time
     * @param start
     */
    public void setStart(LocalDateTime start) {this.start = start;}

    /**
     * The getter for appointment  type
     * @return appointment type
     */
    public int getSubtypeID() {return subtypeID;}
    /**
     * The setter for appointment type
     * @param subtypeID
     */
    public void setSubtypeID(int subtypeID) {this.subtypeID = subtypeID;}

    /**
     * getter for subtype name
     * @return subtype name
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * setter for subtype name
     * @param subtype subtype name
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * The getter for appointment end time
     * @return appointment end time
     */
    public LocalDateTime getEnd() {return end;}
    /**
     * The setter for appointment end time
     * @param end
     */
    public void setEnd(LocalDateTime end) {this.end = end;}

    /**
     * The getter for customer ID
     * @return aptCustomer ID
     */
    public int getAptCustomerID() {return aptCustomerID;}
    /**
     * The setter for customer ID
     * @param aptCustomerID
     */
    public void setAptCustomerID(int aptCustomerID) {this.aptCustomerID = aptCustomerID;}

    /**
     * The getter for user ID
     * @return aptUserID
     */
    public int getAptUserID() {return aptUserID;}
    /**
     * The setter for user ID
     * @param aptUserID
     */
    public void setAptUserID(int aptUserID) {this.aptUserID = aptUserID;}

    /**
     * The getter for contact ID
     * @return aptContactID
     */
    public int getAptContactID() {return aptContactID;}
    /**
     * The setter for contact ID
     * @param aptContactID
     */
    public void setAptContactID(int aptContactID) {this.aptContactID = aptContactID;}

    /**
     * getter for appointment type total
     * @return aptTypeTotal
     */
    public int getAptTypeTotal() {return aptTypeTotal;}

    /**
     * setter for appointment type total
     * @param aptTypeTotal
     */
    public void setAptTypeTotal(int aptTypeTotal) {this.aptTypeTotal = aptTypeTotal;}

    /**
     * getter for formattedStart
     * @return formattedStart
     */
    public String getFormattedStart() {return formattedStart;}

    /**
     * getter for formattedEnd
     * @return formattedEnd
     */
    public String getFormattedEnd() {return formattedEnd;}

    /**
     * setter for formattedStart
     * @param formattedStart
     */
    public void setFormattedStart(String formattedStart) {this.formattedStart = formattedStart;}

    /**
     * setter for formattedEnd
     * @param formattedEnd
     */
    public void setFormattedEnd(String formattedEnd) {this.formattedEnd = formattedEnd;    }

    public static boolean overlapCheck(int appointmentID, int aptCustomerID, LocalDateTime start, LocalDateTime end) {
        ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();

        for (Appointment a : appointmentList) {
            if (a.getAptCustomerID() == aptCustomerID) {
                continue;
            }
            // Skip checking against the same appointment
            if (a.getAppointmentID() == appointmentID) {
                continue;
            }

            LocalDateTime checkApptStart = a.getStart();
            LocalDateTime checkApptEnd = a.getEnd();

            // Check for overlap
            if ((start.isBefore(checkApptEnd) && end.isAfter(checkApptStart)) ||
                    (end.isAfter(checkApptStart) && start.isBefore(checkApptEnd)) ||
                    (start.isEqual(checkApptStart) || end.isEqual(checkApptEnd))) { // Changed || to &&
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Appointment conflicts with an existing customer appointment");
                alert.showAndWait();
                return true;
            }
        }

        return false;
    }

    /*public static boolean overlapCheck(int appointmentID, int aptCustomerID, LocalDateTime start, LocalDateTime end) {
        ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();

        for (Appointment a : appointmentList) {
            if (a.getAptCustomerID() == aptCustomerID) {
                continue;
            }
            // Skip checking against the same appointment
            if (a.getAppointmentID() == appointmentID) {
                continue;
            }

            LocalDateTime checkApptStart = a.getStart();
            LocalDateTime checkApptEnd = a.getEnd();

            // Check for overlap
            if ((start.isBefore(checkApptEnd) && end.isAfter(checkApptStart)) ||
                    (end.isAfter(checkApptStart) && start.isBefore(checkApptEnd)) ||
                    (start.isEqual(checkApptStart) && end.isEqual(checkApptEnd))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Appointment conflicts with an existing customer appointment");
                alert.showAndWait();
                return true;
            }
        }

        return false;
    }*/


    /**
     * Establishes business hours in eastern time and converts the users local timezone to ensure
     * appointment times are within business hours.
     *
     * @param start appointment start date/time
     * @param end   appointment end date/time
     * @return true/false
     */
    public static boolean businessHours(LocalDateTime start, LocalDateTime end) {
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        LocalDateTime appStartEST = start.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime appEndEST = end.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();

        LocalDateTime businessStartEST = appStartEST.withHour(8).withMinute(0);
        LocalDateTime businessEndEST = appEndEST.withHour(22).withMinute(0);

        if (appStartEST.isBefore(businessStartEST) || appEndEST.isAfter(businessEndEST)) {
            LocalTime localStart = Appointment.localStart();
            LocalTime localEnd = Appointment.localEnd();
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setContentText(String.format("The selected time is outside regular business hours\n" +
                    "Please schedule between " + localStart.format(DateTimeFormatter.ofPattern("hh:mm a")) + " - " + localEnd.format(DateTimeFormatter.ofPattern("hh:mm a")) + " local time."));
            //alert1.setContentText(String.format("The selected time is outside regular business hours\n" +
            //        "Please schedule between " + localStart.format(DateTimeFormatter.ofPattern("hh:mm")) + " - " + localEnd.format(DateTimeFormatter.ofPattern("hh:mm")) + "PM local time."));
            alert1.getDialogPane().setMinHeight(250);
            alert1.getDialogPane().setMinWidth(400);
            alert1.showAndWait();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Establishes local start time from eastern and display in business hours in local time
     * for scheduling appointments
     *
     * @return businessStartLocal
     */
    public static LocalTime localStart() {
        LocalTime businessOpen = LocalTime.of(8,0);
        ZoneId easternZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEastern = LocalDateTime.of(LocalDate.now(), businessOpen);
        LocalDateTime businessLocal = businessEastern.atZone(easternZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime businessStartLocal = businessLocal.toLocalTime();

        return businessStartLocal;
    }

    /**
     * Establishes local start time from eastern and display in business hours in local time
     * for scheduling appointments
     *
     * @return businessEndLocal
     */
    public static LocalTime localEnd() {
        LocalTime businessClose = LocalTime.of(22,0);
        ZoneId easternZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEasternDT = LocalDateTime.of(LocalDate.now(), businessClose);
        LocalDateTime businessLocalDT = businessEasternDT.atZone(easternZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime businessEndLocal = businessLocalDT.toLocalTime();

        return businessEndLocal;
    }

    /**
     * generates and adds 30-minute times to list
     * @return appointmentTimesList
     */
    public static ObservableList<LocalTime> getTimes() {

        ObservableList<LocalTime> appointmentTimeList = FXCollections.observableArrayList();

        ZoneId defaultZoneId = TimeZone.getDefault().toZoneId();

        //LocalTime start = LocalTime.of(1,00);
        //LocalTime end = LocalTime.MIDNIGHT.minusHours(1);

        // Start time is 00:00 in the system's default timezone
        ZonedDateTime start = ZonedDateTime.now(defaultZoneId).with(LocalTime.MIDNIGHT);
        // End time is 23:30 in the system's default timezone
        ZonedDateTime end = ZonedDateTime.now(defaultZoneId).with(LocalTime.of(23, 30));

        while (start.isBefore(end.plusSeconds(2))) {
            appointmentTimeList.add(start.toLocalTime());
            start = start.plusMinutes(30);
        }
        return appointmentTimeList;

    }


}
