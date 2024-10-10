package wgu.c195;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates a meeting management application
 * @author Ella Upchurch
 */

public class Main extends Application {
    /**
     *The start method.
     *Creates the FXML and loads initial screen
     *@param stage Stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 382, 509);
        stage.setTitle("");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        try (Statement stmt = JDBC.database().createStatement()) {
            stmt.execute("SET time_zone = '+00:00'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
        JDBC.closeConnection();
    }
}
