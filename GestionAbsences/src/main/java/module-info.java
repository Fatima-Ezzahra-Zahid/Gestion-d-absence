module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;
    requires commons.dbcp2;

    opens org.example to javafx.fxml;
    exports org.example;
    requires java.management;
    opens org.example.Model to javafx.fxml;
    exports org.example.Model;
}