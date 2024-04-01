package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for Directory Screen
 * @author Ella Upchurch
 */
public class DirectoryController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Exits the application
     *
     * @param actionEvent exit button click
     */
    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * takes user to Customers screen
     *
     * @param actionEvent Customers button clicked
     * @throws IOException
     */
    public void onCustomersButtonClick(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/CustomersScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * takes user to Appointments Screen
     *
     * @param actionEvent Appointments button clicked
     * @throws IOException
     */
    public void onAppointmentsButtonClick(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/AppointmentsScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * takes user to Reports screen
     *
     * @param actionEvent Reports button clicked
     * @throws IOException
     */
    public void onReportsButtonClick(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/ReportsScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


}
