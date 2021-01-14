package org.example.MysqlConnect;

import javax.swing.*;
import java.sql.DriverManager;

public class Connection {

    java.sql.Connection conn = null;

    public static java.sql.Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost/..", "root", "");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
