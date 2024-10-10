package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.UserDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * controller for reports screen
 * @author Ella Upchurch
 */
public class ReportsController implements Initializable {


    @FXML
    private TableView<Appointment> aptTypeTable;
    @FXML
    private TableColumn<Appointment, String> aptTypeTotalCol;
    @FXML
    private TableColumn<Appointment, Integer> typeTotalCol;
    @FXML
    private TableView<Appointment> aptMonthTable;
    @FXML
    private TableColumn<Appointment, String> aptMonthTotalCol;
    @FXML
    private TableColumn<Appointment, Integer> monthTotalCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, Timestamp> startTimeCol;
    @FXML
    private TableColumn<Appointment, Timestamp> endTimeCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIDCol;
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private TableView<Appointment> contactAptTable;
    @FXML
    private TableView<Appointment> userAptTable;
    @FXML
    private TableColumn<Appointment, Integer> userAppointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> userTitleCol;
    @FXML
    private TableColumn<Appointment, String> userTypeCol;
    @FXML
    private TableColumn<Appointment, String> userDescriptionCol;
    @FXML
    private TableColumn<Appointment, Timestamp> userStartTimeCol;
    @FXML
    private TableColumn<Appointment, Timestamp> userEndTimeCol;
    @FXML
    private TableColumn<Appointment, Integer> userCustomerIDCol;
    @FXML
    private ComboBox<User> userComboBox;

    ObservableList<Contact> contactList = ContactDAO.getAllContacts();
    ObservableList<User> userList = UserDAO.getUserList();

    /**
     * Initializes controller and sets table data
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        aptTypeTable.setItems(AppointmentDAO.getAptTypeTotal());
        aptTypeTotalCol.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        typeTotalCol.setCellValueFactory(new PropertyValueFactory<>("aptTypeTotal"));



        aptMonthTable.setItems(AppointmentDAO.getAptMonthTotal());
        aptMonthTotalCol.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        monthTotalCol.setCellValueFactory(new PropertyValueFactory<>("aptTypeTotal"));

        contactComboBox.setItems(contactList);
        contactComboBox.setVisibleRowCount(10);
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("aptCustomerID"));

        userComboBox.setItems(userList);
        userAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        userTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        userDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        userStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        userEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        userCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("aptCustomerID"));

    }

    /**
     * populates Contact Appointment table with data based on selected contact
     * @param actionEvent
     * @throws SQLException
     */
    public void onContactSelect(ActionEvent actionEvent) throws SQLException {
        String contactName = String.valueOf(contactComboBox.getValue());
        int contactID = ContactDAO.returnContactID(contactName);

        if (AppointmentDAO.getContactAppointments(contactID).isEmpty()) {
            contactAptTable.setPlaceholder(new Label("No appointments for " + contactName));
            contactAptTable.refresh();
            for (int i = 0; i < contactAptTable.getItems().size(); i++) {
                contactAptTable.getItems().clear();
                contactAptTable.setPlaceholder(new Label("No appointments for " + contactName));
            }
        } else {
            contactAptTable.setItems(AppointmentDAO.getContactAppointments(contactID));
        }
    }

    /**
     * populates User Appointment table with data based on selected user
     * @param actionEvent
     * @throws SQLException
     */
    public void onUserSelect(ActionEvent actionEvent) throws SQLException {
        String username = String.valueOf(userComboBox.getValue());
        int userID = UserDAO.getUserID(username);

        if (AppointmentDAO.getUserAppointments(userID).isEmpty()) {
            userAptTable.setPlaceholder(new Label("No appointments for " + username));
            userAptTable.refresh();
            for (int i = 0; i < userAptTable.getItems().size(); i++) {
                userAptTable.getItems().clear();
                userAptTable.setPlaceholder(new Label("No appointments for " + username));
            }
        } else {
            userAptTable.setItems(AppointmentDAO.getUserAppointments(userID));
        }
    }

    /**
     * takes user back to directory screen
     *
     * @param actionEvent return to main button clicked
     * @throws IOException handles unhandled IO Exception
     */
    public void onReturnToMainClick(ActionEvent actionEvent) throws IOException
    {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/DirectoryScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /*
    /**
     * displays all appointments in appointment table
     * @param actionEvent toggle all appointment radio
     */
    /*
    public void onSelectAllAppt(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentDAO.getAppointmentList());
    }

    /**
     * displays all appointments in appointment table
     * @param actionEvent toggle weekly appointment radio
     */
    /*
    public void onSelectWeekAppt(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentDAO.getWeeklyApt());
        appointmentTable.setPlaceholder(new Label("No appointments within this timeframe"));
    }
    /**
     * displays all appointments in appointment table
     * @param actionEvent toggle monthly appointment radio
     */
    /*
    public void onSelectMonthAppt(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentDAO.getMonthlyApt());
        appointmentTable.setPlaceholder(new Label("No appointments within this timeframe"));
    }
     */


}
