/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author Alexa
 */
public class DbConnectionFactory {
     private static DbConnectionFactory instance;
    private Connection connection;

    public DbConnectionFactory() {
        try {
            if (connection == null || connection.isClosed()) {

                
                String URL = Konfiguracija.getInstance().getProperty("url");
                String USERNAME = Konfiguracija.getInstance().getProperty("username");
                String PASS = Konfiguracija.getInstance().getProperty("password");

                connection = DriverManager.getConnection(URL, USERNAME, PASS);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
