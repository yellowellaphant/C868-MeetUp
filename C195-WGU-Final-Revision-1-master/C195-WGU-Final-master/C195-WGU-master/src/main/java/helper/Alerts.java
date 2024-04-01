package helper;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class contains a helper function for alerts to be used application wide
 *
 * @author Ella Upchurch
 */
public class Alerts implements Initializable {

    static ResourceBundle languageBundle = ResourceBundle.getBundle("language");

    /**
     * This method displays various alert messages during input validation
     */
    public static void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

        switch (alertType) {

            case 1: //Username field is blank
                alert.setTitle(languageBundle.getString("Error"));
                alert.setHeaderText(languageBundle.getString("blankUsername"));
                alert.showAndWait();
                break;
            case 2: //Password field is blank
                alert.setTitle(languageBundle.getString("Error"));
                alert.setHeaderText(languageBundle.getString("blankPassword"));
                alert.showAndWait();
                break;
            case 3: //incorrect username or password
                alert.setTitle(languageBundle.getString("Error"));
                alert.setHeaderText(languageBundle.getString("incorrectUserPass"));
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Please select a customer");
                alert.showAndWait();
                break;
            case 5: //no appointments in next 15 minutes
                confirm.setTitle("Alert");
                confirm.setHeaderText("You have no upcoming appointments");
                confirm.showAndWait();
                break;
            case 6: //delete customer confirmation
                confirm.setTitle("Alert");
                confirm.setHeaderText("Are you sure you want to delete this customer? This action cannot be undone");
                Optional<ButtonType> result = confirm.showAndWait();
                //confirm.showAndWait();
                break;
            case 7: //cancel customer/appointment add/update
                confirm.setTitle("Alert");
                confirm.setHeaderText("Are you sure you want to cancel? Any changes you made will be lost");
                confirm.showAndWait();
                break;
            case 8: //Customer fields left blank
                alert.setTitle("Error");
                alert.setHeaderText("One or more fields have been left blank");
                alert.showAndWait();
                break;
            case 9: //Customer Added pop-up
                confirm.setTitle("Alert");
                confirm.setHeaderText("Customer successfully added");
                confirm.showAndWait();
                break;
            case 10: //Customer Deleted pop-up
                confirm.setTitle("Alert");
                confirm.setHeaderText("Customer successfully deleted");
                confirm.showAndWait();
                break;
            case 11: //Customer Updated pop-up
                confirm.setTitle("Alert");
                confirm.setHeaderText("Customer successfully updated");
                confirm.showAndWait();
                break;
            case 12: //No Appointment selected pop-up
                alert.setTitle("Error");
                alert.setHeaderText("Please select an appointment");
                alert.showAndWait();
                break;
            case 13: //Appointment Deleted pop-up
                confirm.setTitle("Alert");
                confirm.setHeaderText("Appointment successfully deleted");
                confirm.showAndWait();
                break;
            case 14: //Appointment Added pop-up
                confirm.setTitle("Alert");
                confirm.setHeaderText("Appointment successfully added");
                confirm.showAndWait();
                break;
            case 15: //Appointment updated pop-up
                confirm.setTitle("Alert");
                confirm.setHeaderText("Appointment successfully updated");
                confirm.showAndWait();
                break;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
