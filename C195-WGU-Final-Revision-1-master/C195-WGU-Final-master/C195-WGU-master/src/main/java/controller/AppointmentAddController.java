package controller;

import DAO.*;
import javafx.collections.FXCollections;
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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller for appointment add screen
 *
 * @author Ella Upchurch
 */
public class AppointmentAddController implements Initializable {
    public ComboBox<Type> typeCombo;
    public ComboBox<Subtype> subtypeCombo;
    @FXML
    private TextField appointmentIDField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    //@FXML
    //private TextField typeField;
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

    private final int daysToAdd = 0;

    /**
     * LAMBDA EXPRESSION: Line 76 - Data Picker: takes value from start date and sets the value in the end date picker.
     * LAMBDA EXPRESSION: Line 77 - Time Combo: takes the time selected in the start combo and sets the value in the end time for 30 minutes later
     * Initializes the combo box fields and sets the end date picker to match the start date picker. Adds
     * 30 minutes to end time based on selected start time
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentIDField.setId(appointmentIDField.getId());

        startTimeCombo.setItems(Appointment.getTimes());
        endTimeCombo.setItems(Appointment.getTimes());

        ObservableList<Contact> contactList = ContactDAO.getAllContacts();
        contactComboBox.setItems(contactList);
        ObservableList<User> userList = UserDAO.getUserList();
        userIDComboBox.setItems(userList);
        ObservableList<Customer> customerList = CustomerDAO.getCustomerList();
        customerIDComboBox.setItems(customerList);

        ObservableList<Type> typeList = TypeDAO.getAllType();
        typeCombo.setItems(typeList);

        startDatePicker.valueProperty().addListener((ov, oldValueDate, newValueDate) -> endDatePicker.setValue(newValueDate.plusDays(daysToAdd)));
        startTimeCombo.valueProperty().addListener((observableValue, oldValueTime, newValueTime) -> endTimeCombo.setValue(newValueTime.plusMinutes(30)));

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
     * Creates and saves new appointment based on given information;
     * shows alert when fields are invalid or appointment overlaps etc...
     * @param actionEvent ActionEvent
     * @throws IOException IOException
     * @throws SQLException SQLException
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws IOException, SQLException {


        String appointmentTitle = titleField.getText();
        String appointmentDescription = descriptionField.getText();
        //String appointmentType = typeField.getText();


        Subtype subtype = subtypeCombo.getValue();
        if (subtype == null) {
            helper.Alerts.displayAlert(8);
        }

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
        int appointmentSubtype = subtype.getSubTypeID();


        if (appointmentTitle == null || appointmentDescription == null || appointmentLocation == null) {
            helper.Alerts.displayAlert(8);
            return;
        } else if (Appointment.businessHours(appointmentStart, appointmentEnd)){
            return;
        } else if (Appointment.overlapCheck(appointmentCustomerId, 0, appointmentStart, appointmentEnd)) {
            return;
        } else {

            // Convert start and end times to UTC
            ZoneId systemZone = ZoneId.systemDefault();
            //ZonedDateTime startDateTime = ZonedDateTime.of(startPicker, start, systemZone);
            //ZonedDateTime endDateTime = ZonedDateTime.of(endPicker, end, systemZone);
            ZonedDateTime startDateTime = ZonedDateTime.of(startPicker.atTime(start), systemZone);
            ZonedDateTime endDateTime = ZonedDateTime.of(endPicker.atTime(end), systemZone);

            // Convert to UTC
            ZonedDateTime utcStartDateTime = startDateTime.withZoneSameInstant(ZoneOffset.UTC);
            ZonedDateTime utcEndDateTime = endDateTime.withZoneSameInstant(ZoneOffset.UTC);

            // Extract LocalDateTime from ZonedDateTime
            LocalDateTime appointmentStartUTC = utcStartDateTime.toLocalDateTime();
            LocalDateTime appointmentEndUTC = utcEndDateTime.toLocalDateTime();

            AppointmentDAO.addAppointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentSubtype,
                    appointmentStartUTC, appointmentEndUTC, appointmentCustomerId, appointmentUserId, appointmentContact);
            returnToAppointments(actionEvent);
            helper.Alerts.displayAlert(14);
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


    public void onTypeSelect(ActionEvent actionEvent) {
        Type t = typeCombo.getValue();
        try {
            subtypeCombo.setItems(TypeDAO.displaySubtype(t.getTypeID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
