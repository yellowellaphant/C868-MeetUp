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
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for Customer Add Screen
 * @author Ella Upchurch
 */
public class CustomerAddController implements Initializable {

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
     * Initializes the controller and loads country combo box data
     *
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(CountryDAO.getAllCountry());
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
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * cancels changes and returns to Customers screen
     * @param actionEvent cancel button click
     * @throws IOException unhandled exceptions
     */
    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to cancel? Any changes you made will be lost");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToCustomers(actionEvent);
        }
    }

    /**
     * creates a new customer when all fields have been filled appropriately
     *
     * @param actionEvent save button clicked
     * @throws SQLException
     */
    public void onSaveButtonClick(ActionEvent actionEvent) throws SQLException {
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
                Country cCountry = countryComboBox.getValue();
                int divisionID = dID.getDivisionID();

                LocalDateTime cCreateDate = LocalDateTime.now();
                LocalDateTime cLastUpdate = LocalDateTime.now();


                CustomerDAO.addCustomer(cName, cAddress, cPostalCode, cPhone, cCreateDate, cLastUpdate, divisionID);
                helper.Alerts.displayAlert(9);
                returnToCustomers(actionEvent);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }



}
