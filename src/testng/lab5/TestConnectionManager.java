package testng.lab5;

import main.lab5.database.ConnectionManager;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionManager {

    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.close();
    }
}
