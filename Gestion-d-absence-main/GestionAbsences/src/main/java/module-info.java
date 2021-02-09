module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;
    requires commons.dbcp2;
    exports org.example.Controller;
    opens org.example.Controller to javafx.fxml;
    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.DAO;
    requires java.management;
    requires javafx.graphics;
    requires jbcrypt;
    opens org.example.Model to javafx.fxml;
    exports org.example.Model;
}