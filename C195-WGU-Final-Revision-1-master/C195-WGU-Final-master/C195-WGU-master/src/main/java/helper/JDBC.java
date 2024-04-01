package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class is responsible for connecting to the database
 *
 * @author Ella Upchurch
 */
public class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcURL = protocol + vendorName + ipAddress + databaseName;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser";
    private static String password = "Passw0rd!";
    public static Connection conn;

    public static Connection database() {return conn;}
    /**
     * Opens connection to database
     *
     * @return conn
     */
    public static Connection openConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return conn;
    }

    /**
     * Closes connection to database
     */
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
