package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for Customers Screen
 * @author Ella Upchurch
 */
public class CustomersController implements Initializable {

    private static Customer customerToUpdate;
    public static Customer getCustomerToUpdate() {return customerToUpdate;}

    @FXML
    private TableView<Customer> customerInfoTable;
    @FXML
    private TableColumn<Customer, Integer> customerIDCol;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> postalCol;
    @FXML
    private TableColumn<Country, String> countryCol;
    @FXML
    private TableColumn<FirstLevelDivision, String> divisionCol;

    ObservableList<Customer> customerList = CustomerDAO.getCustomerList();

    /**
     * Initializes controller; sets table data
     *
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerInfoTable.setItems(CustomerDAO.getCustomerList());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("customerCountryName"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionName"));

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
     * Takes user to Customer Add screen
     * @param actionEvent add button click
     * @throws IOException
     */
    public void onAddCustomerButtonClick(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/CustomerAddScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * takes user to update customer screen for selected customer
     * @param actionEvent ActionEvent
     * @throws IOException IOException
     */
    public void onUpdateCustomerButtonClick(ActionEvent actionEvent) throws IOException {

        customerToUpdate = customerInfoTable.getSelectionModel().getSelectedItem();

        if (customerInfoTable.getSelectionModel().getSelectedItem() == null) {
            helper.Alerts.displayAlert(4);
        } else {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/CustomerUpdateScreen.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }
    }

    /**
     * Deletes selected customer; Warning triggered if customer has associated appointments
     * @param actionEvent
     */
    public void onDeleteCustomerButtonClick(ActionEvent actionEvent) {
        int count = 0;

        ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();
        Customer selectedCustomer = customerInfoTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            helper.Alerts.displayAlert(4);
            return;
        }

        int selectedCust = customerInfoTable.getSelectionModel().getSelectedItem().getCustomerID();
        for (Appointment appointment : appointmentList) {
            int aptCustomerID = appointment.getAptCustomerID();
            if (aptCustomerID == selectedCust) {
                count++;
            }
        }

        if (count > 0) { //Associated appointments
            Alert assocAppointment = new Alert(Alert.AlertType.WARNING);
            assocAppointment.setTitle("Warning");
            assocAppointment.setHeaderText("Warning: There are " + count + " appointment(s) for the selected customer. \n" +
                    "Are you sure you want to delete this customer? This customer will also delete their associated appointments.");
            assocAppointment.getButtonTypes().clear();
            assocAppointment.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            assocAppointment.showAndWait();
            if (assocAppointment.getResult() == ButtonType.OK) {
                for (Appointment appointment : appointmentList) {
                    if (appointment.getAptCustomerID() == selectedCust)
                        AppointmentDAO.deleteAppointment(appointment.getAppointmentID());
                }
                CustomerDAO.deleteCustomer(customerInfoTable.getSelectionModel().getSelectedItem().getCustomerID());
                helper.Alerts.displayAlert(10);
                customerList = CustomerDAO.getCustomerList();
                customerInfoTable.setItems(customerList);
                customerInfoTable.refresh();
            } else if (assocAppointment.getResult() == ButtonType.CANCEL) {
                assocAppointment.close();
            }
        }
        if (count == 0) {
            Alert confirmRemove = new Alert(Alert.AlertType.WARNING);
            confirmRemove.setTitle("Warning");
            confirmRemove.setHeaderText("Are you sure you want to delete this customer? This action cannot be undone.");
            confirmRemove.getButtonTypes().clear();
            confirmRemove.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            confirmRemove.showAndWait();
            if (confirmRemove.getResult() == ButtonType.OK) {
                CustomerDAO.deleteCustomer(customerInfoTable.getSelectionModel().getSelectedItem().getCustomerID());
                helper.Alerts.displayAlert(10);
                customerList = CustomerDAO.getCustomerList();
                customerInfoTable.setItems(customerList);
                customerInfoTable.refresh();
            } else if (confirmRemove.getResult() == ButtonType.CANCEL) {
                confirmRemove.close();
            }
        }
    }



}
