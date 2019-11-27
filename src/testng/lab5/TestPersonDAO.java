package testng.lab5;

import main.lab5.DAO.PersonDAO;
import main.lab5.database.ConnectionManager;
import main.lab5.model.Person;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.testng.AssertJUnit.assertEquals;

public class TestPersonDAO {
    private Connection connection;
    private Person person;
    private Person personForUpdate;
    private PersonDAO personDAO;

    @BeforeTest
    public void before() throws SQLException {
        connection = ConnectionManager.getConnection();
        personDAO = new PersonDAO(connection);
        person = new Person.Builder()
                .setId(3)
                .setBirthDay(LocalDate.of(2000, 12, 12))
                .setFirstName("First name")
                .setLastName("Last name")
                .setSalary(2000.0)
                .build();
        personForUpdate = new Person.Builder()
                .setId(3)
                .setBirthDay(LocalDate.of(2000, 12, 12))
                .setFirstName("First name")
                .setLastName("Last name")
                .setSalary(4000.0)
                .build();

    }

    @AfterTest
    public void after() throws SQLException {
        connection.close();
    }

    @Test
    public void createTest() {
        assertEquals(personDAO.create(person), true);
    }

    @Test
    public void readTest() {
        assertEquals(personDAO.read(3), person);
    }

    @Test
    public void resultSetToObjTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM persons  WHERE id=3");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
            assertEquals(personDAO.resultSetToObj(resultSet), person);
    }

    @Test
    public void updateTest() {
        assertEquals(personDAO.update(personForUpdate), true);
    }

    @Test
    public void deleteTest() {
        assertEquals(personDAO.delete(person), true);
    }
}
