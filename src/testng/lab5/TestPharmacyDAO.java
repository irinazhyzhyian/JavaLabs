package testng.lab5;

import main.lab5.DAO.CountMedicineDAO;
import main.lab5.DAO.MedicineDAO;
import main.lab5.DAO.PersonDAO;
import main.lab5.DAO.PharmacyDAO;
import main.lab5.database.ConnectionManager;
import main.lab5.model.CountMedicine;
import main.lab5.model.Medicine;
import main.lab5.model.Person;
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

public class TestPharmacyDAO {
    private Connection connection;
    private Pharmacy pharmacy;
    private Pharmacy pharmacyForUpdate;
    private PharmacyDAO pharmacyDAO;
    private Person person;
    List<CountMedicine> list = new ArrayList<>();

    @BeforeTest
    public void before() throws SQLException {
        connection= ConnectionManager.getConnection();
        pharmacyDAO = new PharmacyDAO(connection);
         person = new Person.Builder()
                .setId(5)
                .setSalary(1000.0)
                .setLastName("Last_name")
                .setFirstName("First name")
                .setBirthDay(LocalDate.of(1999, 12, 12))
                .build();
        Medicine medicine = new Medicine.Builder()
                .setId(4)
                .setForm("form")
                .setName("Name")
                .setOverdueDay(LocalDate.of(2019, 10, 12))
                .setPrice(100.0)
                .build();
        Medicine medicine2 = new Medicine.Builder()
                .setId(5)
                .setForm("form2")
                .setName("Name2")
                .setOverdueDay(LocalDate.of(2019, 10, 12))
                .setPrice(105.0)
                .build();
        pharmacy = new Pharmacy.Builder()
                .setId(5)
                .setName("Pharmacy #1")
                .setPharmacist(person)
                .build();
        CountMedicine countMedicine1 = new CountMedicine.Builder()
                .setId(5)
                .setCount(100)
                .setMedicine(medicine)
                .setPharmacy(pharmacy)
                .build();
        //new CountMedicineDAO(connection).create(countMedicine1);
        CountMedicine countMedicine2 = new CountMedicine.Builder()
                .setId(6)
                .setCount(150)
                .setMedicine(medicine2)
                .setPharmacy(pharmacy)
                .build();
        // new CountMedicineDAO(connection).create(countMedicine2);
        list.add(countMedicine2);
        list.add(countMedicine1);
        pharmacy.setCountMedicines(list);
        pharmacyForUpdate = new Pharmacy.Builder()
                .setId(5)
                .setCountMedicines(list)
                .setName("Pharmacy new")
                .setPharmacist(person)
                .build();
    }

    @AfterTest
    public void after() throws SQLException {
      //  new PersonDAO(connection).delete(person);
        connection.close();
    }

    @Test
    public void createTest() throws SQLException {
        assertEquals(pharmacyDAO.create(pharmacy), true);
    }

    @Test
    public void readTest() throws SQLException {
        assertEquals(pharmacyDAO.read(5), pharmacy);
    }

    @Test
    public void resultSetToObjTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM pharmacy WHERE id=5");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
             assertEquals(pharmacyDAO.resultSetToObj(resultSet), pharmacy);
    }

    @Test
    public void updateTest() throws SQLException {
        assertEquals(pharmacyDAO.update(pharmacyForUpdate), true);
    }

    @Test
    public void getListCountMedicineTest() throws SQLException {
        assertEquals(pharmacyDAO.getListCountMedicine(pharmacy), list);
    }

    @Test
    public void deleteOverdueMedicinesTest() throws SQLException {
        pharmacyDAO.deleteOverdueMedicines(pharmacy);
    }

    @Test
    public void deleteTest() throws SQLException {
        assertEquals(pharmacyDAO.delete(pharmacy), true);
    }

}
