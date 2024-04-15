package controller;

import DAO.AppointmentDAO;
import DAO.TypeDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Type;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for Appointments Screen
 * @author Ella Upchurch
 */
public class AppointmentsController implements Initializable {


    private static Appointment appointmentToUpdate;
    public TextField searchField;

    public static Appointment getAppointmentToUpdate() {return appointmentToUpdate;}

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, Integer> contactCol;
    @FXML
    private TableColumn<Appointment, Integer> typeCol;
    @FXML
    private TableColumn<Appointment, Timestamp> startCol;
    @FXML
    private TableColumn<Appointment, Timestamp> endCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIDCol;
    @FXML
    private TableColumn<Appointment, Integer> userIDCol;

    ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();

    /**
     * Initializes controller; sets table data
     *
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentTable.setItems(AppointmentDAO.getAppointmentList());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("subtype"));

        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));

        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("aptCustomerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("aptUserID"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("aptContactID"));

    }

    /**
     * takes user back to directory screen
     *
     * @param actionEvent return to main button clicked
     * @throws IOException handles unhandled IO Exception
     */
    public void onReturnToMainButtonCLick(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/DirectoryScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * takes user to Add Appointment Screen
     *
     * @param actionEvent ActionEvent
     * @throws IOException IOException
     */
    public void onAddApptButtonClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/AppointmentAddScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * takes user to Update Appointment Screen for selected appointment
     *
     * @param actionEvent ActionEvent
     * @throws IOException IOException
     */
    public void onUpdateApptButtonClick(ActionEvent actionEvent) throws IOException {

        appointmentToUpdate = appointmentTable.getSelectionModel().getSelectedItem();

        if (appointmentToUpdate == null) {helper.Alerts.displayAlert(12);
        } else {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/AppointmentUpdateScreen.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

    }

    /**
     * Deletes selected Appointment
     * @param actionEvent ActionEvent
     */
    public void onDeleteApptButtonClick(ActionEvent actionEvent) {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            helper.Alerts.displayAlert(12);
            return;
        } else {
            Alert confirmRemove = new Alert(Alert.AlertType.WARNING);
            confirmRemove.setTitle("Warning");
            confirmRemove.setHeaderText("Are you sure you want to delete this appointment? This action cannot be undone.");
            confirmRemove.getButtonTypes().clear();
            confirmRemove.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            confirmRemove.showAndWait();

            if (confirmRemove.getResult() == ButtonType.OK) {
                //AppointmentDAO.deleteAppointment(appointmentTable.getSelectionModel().getSelectedItem().getAppointmentID());

                int aptToDelete = appointmentTable.getSelectionModel().getSelectedItem().getAppointmentID();
                int aptDelType = appointmentTable.getSelectionModel().getSelectedItem().getSubtypeID();
                AppointmentDAO.deleteAppointment(aptToDelete);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Appointment ID # " + aptToDelete +" of type " + aptDelType + " has been successfully deleted");
                alert.showAndWait();

                appointmentList = AppointmentDAO.getAppointmentList();
                appointmentTable.setItems(appointmentList);
                appointmentTable.refresh();

            } else if (confirmRemove.getResult() == ButtonType.CANCEL) {
                confirmRemove.close();
            }
        }
    }

    /**
     * displays all appointments in appointment table
     * @param actionEvent toggle all appointment radio
     */
    public void onSelectAllAppt(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentDAO.getAppointmentList());
    }

    /**
     * displays all appointments in appointment table
     * @param actionEvent toggle weekly appointment radio
     */
    public void onSelectWeekAppt(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentDAO.getWeeklyApt());
        appointmentTable.setPlaceholder(new Label("No appointments within this timeframe"));
    }

    /**
     * displays all appointments in appointment table
     * @param actionEvent toggle monthly appointment radio
     */
    public void onSelectMonthAppt(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentDAO.getMonthlyApt());
        appointmentTable.setPlaceholder(new Label("No appointments within this timeframe"));
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

    /**
     * searches Appointments by ID, Title, or Location
     * @param actionEvent text typed in search box
     */
    public void onSearchMeetings(ActionEvent actionEvent) {
        // Get the search keyword
        String keyword = searchField.getText().trim();

        // Search for appointments by ID, title, and location
        ObservableList<Appointment> searchResults = AppointmentDAO.searchAppointments(keyword);

        // Update the appointmentTable with the search results
        appointmentTable.setItems(searchResults);
    }

    public void onSelectInternalRadio(ActionEvent actionEvent) {
    }

    public void onSelectExternalRadio(ActionEvent actionEvent) {
    }
}
