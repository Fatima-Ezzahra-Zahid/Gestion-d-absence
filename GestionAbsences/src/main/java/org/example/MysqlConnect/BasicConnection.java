package org.example.MysqlConnect;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BasicConnection {
        private static BasicDataSource dataSource;
        public static DataSource AccesConnection(){
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/gestiondabsence?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Africa/Casablanca");
            dataSource.setUsername("simo");
            dataSource.setPassword("1234");
            //Pool de connection
            //System.out.println("success");
            return dataSource;
        }
}