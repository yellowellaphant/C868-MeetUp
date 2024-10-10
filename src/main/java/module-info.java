module wgu.c195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens wgu.c195 to javafx.fxml;
    exports wgu.c195;
    exports controller;
    exports model;
    exports helper;
    exports DAO;
    opens controller to javafx.fxml;
}