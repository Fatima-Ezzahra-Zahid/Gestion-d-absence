package org.example.MysqlConnect;

import java.sql.*;

public class ConnectionClass{

    String PILOTE = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/gestiondabsence";
    static String utilisateur = "root";
    static String password = "";
    static Connection connection = null;


    public static Connection getMyConnexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, utilisateur, password);
        return connection;
    }
}
