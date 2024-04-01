package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for Appointment Update Screen
 * @author Ella Upchurch
 */
public class AppointmentUpdateController implements Initializable {

    private Appointment selectedAppointment;
    @FXML
    private TextField appointmentIDField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField typeField;
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private ComboBox<User> userIDComboBox;
    @FXML
    private ComboBox<Customer> customerIDComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private ComboBox<LocalTime> startTimeCombo;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<LocalTime> endTimeCombo;

    /**
     * Initializes controller and sets appointment data for modification
     * @param url Url
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedAppointment = AppointmentsController.getAppointmentToUpdate();

        startTimeCombo.setItems(Appointment.getTimes());
        endTimeCombo.setItems(Appointment.getTimes());
        ObservableList<Contact> contactList = ContactDAO.getAllContacts();
        contactComboBox.setItems(contactList);
        ObservableList<User> userList = UserDAO.getUserList();
        userIDComboBox.setItems(userList);
        ObservableList<Customer> customerList = CustomerDAO.getCustomerList();
        customerIDComboBox.setItems(customerList);

        try {
            appointmentIDField.setText(Integer.toString(selectedAppointment.getAppointmentID()));
            titleField.setText(selectedAppointment.getTitle());
            descriptionField.setText(selectedAppointment.getDescription());
            locationField.setText(selectedAppointment.getLocation());
            typeField.setText(selectedAppointment.getType());

            //add setters for combo boxes
            startTimeCombo.setValue(selectedAppointment.getStart().toLocalTime());
            startDatePicker.setValue(selectedAppointment.getStart().toLocalDate());
            endTimeCombo.setValue(selectedAppointment.getEnd().toLocalTime());
            endDatePicker.setValue(selectedAppointment.getEnd().toLocalDate());

            Contact c1 = ContactDAO.returnContactList(selectedAppointment.getAptContactID());
            contactComboBox.setValue(c1);
            User u = UserDAO.returnUserID(selectedAppointment.getAptUserID());
            userIDComboBox.setValue(u);
            Customer c2 = CustomerDAO.returnCustomerList(selectedAppointment.getAptCustomerID());
            customerIDComboBox.setValue(c2);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Cancels changes and returns to appointments screen
     *
     * @param actionEvent ActionEvent
     * @throws IOException IOException
     */
    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to cancel? Any changes you made will be lost");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToAppointments(actionEvent);
        }
    }

    /**
     *
     * @param actionEvent save button click
     * @throws SQLException  sql exception
     * @throws IOException IO exception
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        int appointmentID = Integer.parseInt(appointmentIDField.getText());
        String appointmentTitle = titleField.getText();
        String appointmentDescription = descriptionField.getText();
        String appointmentType = typeField.getText();


        Contact contact = contactComboBox.getValue();
        if (contact == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        LocalDate startPicker = startDatePicker.getValue();
        if (startPicker == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        LocalTime start = startTimeCombo.getValue();
        if (start == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        LocalDate endPicker = endDatePicker.getValue();
        if (endPicker == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        LocalTime end = endTimeCombo.getValue();
        if (end == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        Customer customer = customerIDComboBox.getValue();
        if (customer == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        User user = userIDComboBox.getValue();
        if (user == null) {
            helper.Alerts.displayAlert(8);
            return;
        }

        String appointmentLocation = locationField.getText();


        LocalDateTime appointmentStart = LocalDateTime.of(startPicker, start);
        LocalDateTime appointmentEnd = LocalDateTime.of(endPicker, end);
        int appointmentContact = contact.getContactID();
        int appointmentCustomerId = customer.getCustomerID();
        int appointmentUserId = user.getUserID();


        if (appointmentTitle == null || appointmentDescription == null || appointmentType == null || appointmentLocation == null) {
            helper.Alerts.displayAlert(8);
            return;
        } else if (Appointment.businessHours(appointmentStart, appointmentEnd)){
            return;
        } else if (Appointment.overlapCheck(appointmentID, appointmentCustomerId, appointmentStart, appointmentEnd)) {
            return;
        } else {

            // Convert start and end times to UTC
            ZoneId systemZone = ZoneId.systemDefault();
            ZonedDateTime startDateTime = ZonedDateTime.of(startPicker.atTime(start), systemZone);
            ZonedDateTime endDateTime = ZonedDateTime.of(endPicker.atTime(end), systemZone);

            // Convert to UTC
            ZonedDateTime utcStartDateTime = startDateTime.withZoneSameInstant(ZoneOffset.UTC);
            ZonedDateTime utcEndDateTime = endDateTime.withZoneSameInstant(ZoneOffset.UTC);

            // Extract LocalDateTime from ZonedDateTime
            LocalDateTime appointmentStartUTC = utcStartDateTime.toLocalDateTime();
            LocalDateTime appointmentEndUTC = utcEndDateTime.toLocalDateTime();

            AppointmentDAO.updateAppointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                    appointmentType, appointmentStartUTC, appointmentEndUTC, appointmentCustomerId, appointmentUserId, appointmentContact);
            returnToAppointments(actionEvent);
            helper.Alerts.displayAlert(15);
        }
    }

    /**
     * returns to Appointments screen
     *
     * @param actionEvent returns to Appointments screen
     * @throws IOException unhandled exceptions
     */
    public void returnToAppointments(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/AppointmentsScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
