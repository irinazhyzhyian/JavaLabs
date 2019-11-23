package main.lab5.database;

import main.lab5.database.exceptions.DataBaseConnectException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private final static String PROPERTIES_PATH = "database.properties";

    private Properties properties = new Properties();

    private Connection makeConnection() throws SQLException {
        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
            Class.forName(properties.getProperty("jdbc.drivers"));
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
            return connection;
        }catch (FileNotFoundException ex ) {
            throw new SQLException( ex );

        }catch (Exception e) {
            throw new DataBaseConnectException(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return new ConnectionManager().makeConnection();
    }
}
