package testng.lab4;

import main.lab4.model.CountMedicine;
import main.lab4.model.Medicine;
import main.lab4.model.Person;
import main.lab4.model.Pharmacy;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPharmacy {

    private List<CountMedicine> countMedicines = new ArrayList<>();
    private Person person;

    @BeforeTest
    public void before() {
        Medicine medicine = new Medicine.Builder()
                .setId(1)
                .setName("Name")
                .setForm("form")
                .setPrice(200.0)
                .setOverdueDay(LocalDate.of(2019, 12, 11))
                .build();
        CountMedicine countMedicine = new CountMedicine.Builder()
                .setId(1)
                .setCount(200)
                .setMedicine(medicine)
                .build();
       countMedicines.add(countMedicine);
        person = new Person.Builder()
                .setId(1)
                .setBirthDay(LocalDate.of(2000, 1, 1))
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setSalary(2000.0)
                .build();
    }

    @Test
    public void pharmacyBuilderTest() {
        Pharmacy pharmacy = new Pharmacy.Builder()
                .setId(1)
                .setCountMedicines(countMedicines)
                .setName("Name")
                .setPharmacist(person)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void pharmacyBuilderNegativeTest() {
        Pharmacy pharmacy = new Pharmacy.Builder()
                .setId(-1)
                .setCountMedicines(countMedicines)
                .setName("")
                .setPharmacist(person)
                .build();
    }
}
