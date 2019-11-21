package testng.lab4;

import main.lab4.model.CountMedicine;
import main.lab4.model.Medicine;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestCountMedicine {
    Medicine medicine;

    @BeforeTest
    public void before() {
        medicine = new Medicine.Builder()
                .setId(1)
                .setName("Name")
                .setForm("form")
                .setPrice(200.0)
                .setOverdueDay(LocalDate.of(2019, 12, 11))
                .build();
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void countMedicineBuilderNegativeTest() {
        CountMedicine countMedicine = new CountMedicine.Builder()
                .setId(-1)
                .setMedicine(medicine)
                .setCount(-3)
                .build();
    }

    @Test
    public void countMedicineBuilderTest() {
        CountMedicine countMedicine = new CountMedicine.Builder()
                .setId(2)
                .setMedicine(medicine)
                .setCount(300)
                .build();
    }


}
