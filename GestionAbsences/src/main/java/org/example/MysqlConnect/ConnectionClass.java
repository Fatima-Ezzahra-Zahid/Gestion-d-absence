package org.example.MysqlConnect;

import javax.swing.*;
import java.sql.*;

public class ConnectionClass  implements DriverAction {

    // implementing deregister method of DriverAction interface
    @Override
    public void deregister() {
        System.out.println("Driver deregistered");
    }
    public static void main(String args[]){
        try{
            // Creating driver instance
            Driver driver = new com.mysql.cj.jdbc.Driver();
            // Creating Action Driver
            DriverAction da = new ConnectionClass();
            // Registering driver by passing driver and driverAction
            DriverManager.registerDriver(driver, da);
            // Creating connection
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/g_et","root","");
            //Here student is database name, root is username and password is mysql
            Statement stmt=con.createStatement();
            // Executing SQL query
            ResultSet rs=stmt.executeQuery("select * from specialite");
            while(rs.next()){
                System.out.println(rs.getInt(1)+""+rs.getString(2));
            }
            // Closing connection
            con.close();
            // Calling deregisterDriver method
            DriverManager.deregisterDriver(driver);
        }catch(Exception e){ System.out.println(e);}
    }
}
