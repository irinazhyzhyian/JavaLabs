package testng.lab5;

import main.lab5.model.Medicine;
import main.lab5.DAO.MedicineDAO;
import main.lab5.database.ConnectionManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class TestMedicineDAO {
    private MedicineDAO medicineDAO;
    private Medicine medicine;
    private Medicine medicineForUpdate;
    private Connection connection;

    @BeforeTest
    public void before() throws SQLException {
        connection = ConnectionManager.getConnection();
        medicineDAO = new MedicineDAO(connection);
        medicine = new Medicine.Builder()
                .setId(4)
                .setForm("form")
                .setName("Name")
                .setOverdueDay(LocalDate.of(2019, 12, 12))
                .setPrice(100.0)
                .build();
        medicineForUpdate = new Medicine.Builder()
                .setId(4)
                .setForm("form")
                .setName("Name")
                .setOverdueDay(LocalDate.of(2019, 12, 12))
                .setPrice(200.0)
                .build();
    }

    @AfterTest
    public void after() throws SQLException {
        connection.close();
    }

    @Test
    public void createTest() {
        assertEquals(medicineDAO.create(medicine), true);
    }

    @Test
    public void readTest() {
        assertEquals(medicineDAO.read(4), medicine);
    }

    @Test
    public void resultSetToObjTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicines  WHERE id=4");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
            assertEquals(medicineDAO.resultSetToObj(resultSet), medicine);
    }

    @Test
    public void updateTest() {
        assertEquals(medicineDAO.update(medicineForUpdate), true);
    }

    @Test
    public void deleteTest() {
        assertEquals(medicineDAO.delete(medicine), true);
    }

}
