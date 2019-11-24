package testng.lab5;

import main.lab5.DAO.CountMedicineDAO;
import main.lab5.database.ConnectionManager;
import main.lab5.model.CountMedicine;
import main.lab5.model.Medicine;
import main.lab5.model.Pharmacy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestCountMedicineDAO {
    private Connection connection;
    private CountMedicine countMedicine;
    private CountMedicine countMedicineForUpdate;
    private CountMedicineDAO countMedicineDAO;

    @BeforeTest
    public void before() throws SQLException {
        connection = ConnectionManager.getConnection();
        countMedicineDAO = new CountMedicineDAO(connection);
        Medicine medicine = new Medicine.Builder()
                .setId(4)
                .setForm("form")
                .setName("Name")
                .setOverdueDay(LocalDate.of(2019, 12, 12))
                .setPrice(100.0)
                .build();
        Pharmacy pharmacy = new Pharmacy.Builder()
                .setId(1)
                .setName("Pharmacy #1")
                .setPharmacist(null)
                .build();
        countMedicine = new CountMedicine.Builder()
                .setId(5)
                .setCount(100)
                .setMedicine(medicine)
                .setPharmacy(pharmacy)
                .build();
        List<CountMedicine> list = new ArrayList<>();
        list.add(countMedicine);
        pharmacy.setCountMedicines(list);
        countMedicineForUpdate = new CountMedicine.Builder()
                .setId(5)
                .setCount(150)
                .setMedicine(medicine)
                .setPharmacy(pharmacy)
                .build();
    }

    @AfterTest
    public void after() throws SQLException {
        connection.close();
    }

    @Test
    public void createTest() throws SQLException {
        assertEquals(countMedicineDAO.create(countMedicine), true);
    }

    @Test
    public void readTest() throws SQLException {
        assertEquals(countMedicineDAO.read(5), countMedicine);
    }

    @Test
    public void resultSetToObjTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM count_medicines  WHERE id=5");
        ResultSet resultSet = statement.executeQuery();
        assertEquals(countMedicineDAO.resultSetToObj(resultSet), countMedicine);
    }

    @Test
    public void updateTest() throws SQLException {
        assertEquals(countMedicineDAO.update(countMedicineForUpdate), true);
    }

    @Test
    public void deleteTest() throws SQLException {
        assertEquals(countMedicineDAO.delete(countMedicine), true);
    }

}
