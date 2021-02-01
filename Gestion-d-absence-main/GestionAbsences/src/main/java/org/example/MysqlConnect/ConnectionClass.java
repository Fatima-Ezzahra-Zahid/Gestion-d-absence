package org.example.MysqlConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass{

    String PILOTE = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/gestiondabsence";
    static String utilisateur = "root";
    static String password = "";
    static Connection connection = null;


    public static Connection getMyConnexion() throws ClassNotFoundException,
            SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Pilote de la base de donnees est charge");
        connection = DriverManager.getConnection(url, utilisateur, password);
        System.out.println("La connexion a est bien etablie");

        return connection;
    }

    public static void main (String[] arg) throws SQLException, ClassNotFoundException {
        getMyConnexion();

    }
}
