package controller;

import DAO.AppointmentDAO;
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
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import static DAO.UserDAO.*;

/**
 * Controller for Login Screen
 * @author Ella Upchurch
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label titleLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    boolean loginSuccess = false;

    ResourceBundle languageBundle = ResourceBundle.getBundle("language");
    LocalDateTime currentTime = LocalDateTime.now();
    //ZonedDateTime zoneConvert = currentTime.atZone(ZoneId.systemDefault());
    LocalDateTime next15Minutes = LocalDateTime.now().plusMinutes(15);

    /**
     * Initializes controller, changes/sets location label, changes/sets button and label text based on user's language settings
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ZoneId zoneId = ZoneId.systemDefault();
        String location = ZoneId.systemDefault().toString();
        locationLabel.setText(location);
        titleLabel.setText(languageBundle.getString("Welcome"));
        usernameLabel.setText(languageBundle.getString("Username"));
        passwordLabel.setText(languageBundle.getString("Password"));
        loginButton.setText(languageBundle.getString("Login"));
        exitButton.setText(languageBundle.getString("Exit"));
    }

    /**
     * validates username and password, displays error messages when incorrect credentials are entered
     * logs login attempts in login_activity.txt file
     *
     * @param actionEvent login button pressed
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException, SQLException {
        String User_Name = usernameField.getText();
        String Password = passwordField.getText();

        if (User_Name.isBlank()) { //blank username field
            helper.Alerts.displayAlert(1);
        } else if (Password.isBlank()) { //blank password field
            helper.Alerts.displayAlert(2);
            loginSuccess = false;
            loginActivity();
        } else if (!passwordValidation(Password) && !usernameValidation(User_Name)) { //username + password incorrect
            helper.Alerts.displayAlert(3);
            loginSuccess = false;
            loginActivity();
        } else if (!usernameValidation(User_Name)) { //username incorrect
            helper.Alerts.displayAlert(3);
            loginSuccess = false;
            loginActivity();
        } else if (!passwordValidation(Password)) { //password incorrect
            helper.Alerts.displayAlert(3);
            loginSuccess = false;
            loginActivity();
        } else if (userLogin(User_Name, Password)) { //login successful

            int userID = getUserID(User_Name);
            ObservableList<Appointment> userAppointments = AppointmentDAO.getUserAppointments(userID);

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/wgu/c195/DirectoryScreen.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            loginSuccess = true;
            loginActivity();

            boolean isValid = false;
            for (Appointment appointment : userAppointments) {
                LocalDateTime startTime = appointment.getStart();
                if ((startTime.isAfter(currentTime) || startTime.isEqual(next15Minutes)) &&
                        (startTime.isBefore(next15Minutes) || startTime.isEqual(currentTime))) {
                    Alert upcomingAppointment = new Alert(Alert.AlertType.WARNING);
                    upcomingAppointment.setTitle("Alert");
                    upcomingAppointment.setContentText("Appointment " + appointment.getAppointmentID() + " begins at " + appointment.getStart());
                    upcomingAppointment.getButtonTypes().clear();
                    upcomingAppointment.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                    upcomingAppointment.showAndWait();
                    isValid = true;
                    break; // No need to continue the loop once a valid appointment is found
                }
            }
            if (!isValid) {
                helper.Alerts.displayAlert(5);
            }

        }
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
     * gets file name for ActivityLog lambda
     */
    interface ActivityLog {
        String getFileName();
    }

    /**
     * lambda expression gets file name for login activity tracking
     */
    ActivityLog activityLog = () -> {
        return "login_activity.txt";
    };

    /**
     * monitors logins and appends date/time, username, and success of login to login_activity.txt file
     * uses lambda function on line 162
     *
     * @throws IOException
     */
    public void loginActivity() throws IOException {

        FileWriter fileWriter = new FileWriter(activityLog.getFileName(), true);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss");
        ZoneId localZone = ZoneId.systemDefault();

        String userName = usernameField.getText();
        String isSuccess = "";
        if (!loginSuccess) {
            isSuccess = "failed";
        } else {
            isSuccess = "successful";
        }
        //prints output in console
        System.out.println(dateTimeFormatter.format(currentTime) + " Login attempted by user: <" + userName + "> Status: " + isSuccess);

        fileWriter.write(dateTimeFormatter.format(currentTime) + " Login attempted by user: <" + userName + "> Status: " + isSuccess);
        fileWriter.write("\n");
        fileWriter.close();
    }



}
