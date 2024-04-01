package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FirstLevelDivDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for Customer Update Screen
 * @author Ella Upchurch
 */
public class CustomerUpdateController implements Initializable {

    private Customer selectedCustomer;
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private ComboBox<FirstLevelDivision> divisionComboBox;

    /**
     * Initializes controller; loads country combo box data; populates data of customer selected to update
     * @param url Url
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(CountryDAO.getAllCountry());
        selectedCustomer = CustomersController.getCustomerToUpdate();

        try {
            customerIDField.setText(Integer.toString(selectedCustomer.getCustomerID()));
            customerNameField.setText(selectedCustomer.getCustomerName());
            addressField.setText(selectedCustomer.getAddress());
            postalCodeField.setText(selectedCustomer.getPostalCode());
            phoneField.setText(selectedCustomer.getPhone());
            FirstLevelDivision fld = FirstLevelDivDAO.returnDivisionLevel(selectedCustomer.getCustomerDivisionID());
            divisionComboBox.setValue(fld);
            Country c1 = CountryDAO.returnCountry(selectedCustomer.getCustomerCountryID());
            countryComboBox.setValue(c1);
            Country c2 = countryComboBox.getValue();
            divisionComboBox.setItems(FirstLevelDivDAO.displayDivision(c2.getCountryID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * retrieves division options based on selected country
     * @param actionEvent countryComboBox selection is made
     */
    public void onSelectCountry(ActionEvent actionEvent) {
        Country C = countryComboBox.getValue();
        try {
            divisionComboBox.setItems(FirstLevelDivDAO.displayDivision(C.getCountryID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cancels changes and returns to Customers screen
     * @param actionEvent cancel button click
     * @throws IOException unhandled exceptions
     */
    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException{

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to cancel? Any changes you made will be lost");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToCustomers(actionEvent);
        }
    }

    /**
     * Updates existing customer when fields are filled correctly
     * @param actionEvent save button click
     */
    public void onSaveButtonClick(ActionEvent actionEvent) {
        try {
            if (customerNameField.getText().isBlank() || addressField.getText().isBlank() || postalCodeField.getText().isBlank() ||
                    phoneField.getText().isBlank() || countryComboBox.getValue() == null || divisionComboBox.getValue() == null) {
                helper.Alerts.displayAlert(8);
            } else {
                String cName = customerNameField.getText();
                String cAddress = addressField.getText();
                String cPostalCode = postalCodeField.getText();
                String cPhone = phoneField.getText();
                FirstLevelDivision dID = divisionComboBox.getValue();
                int divisionID = dID.getDivisionID();

                Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
                String lastUpdatedBy = "script";
                int customerID = Integer.parseInt(customerIDField.getText());

                CustomerDAO.updateCustomer(customerID, cName, cAddress, cPostalCode, cPhone, lastUpdatedBy, lastUpdate, divisionID);
                helper.Alerts.displayAlert(11);
                returnToCustomers(actionEvent);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns to Customers screen
     *
     * @param actionEvent returns to Customers screen
     * @throws IOException unhandled exceptions
     */
    public void returnToCustomers(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/CustomersScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




}
