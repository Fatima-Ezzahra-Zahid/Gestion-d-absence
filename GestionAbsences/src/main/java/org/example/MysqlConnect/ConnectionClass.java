package org.example.MysqlConnect;

import java.sql.*;

public class ConnectionClass{

<<<<<<< HEAD
    String PILOTE = "com.mysql.jdbc.Driver";
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

=======
    String PILOTE = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/gestiondabsence";
    static String utilisateur = "simo";
    static String password = "1234";
    static Connection connection = null;


    public static Connection getMyConnexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, utilisateur, password);
>>>>>>> main
        return connection;
    }
}
