package testng.lab4;

import main.lab4.model.Medicine;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestMedicine {

    @Test(expectedExceptions = IllegalStateException.class)
    public void medicineBuilderTest() {
        Medicine medicine = new Medicine.Builder()
                .setId(1)
                .setName("Name")
                .setForm("form")
                .setPrice(-2.0)
                .setOverdueDay(LocalDate.of(2019, 1, 11))
                .build();
    }

    @Test
    public void futureDayValidationTest() {
        Medicine medicine = new Medicine.Builder()
                .setId(1)
                .setName("Name")
                .setForm("form")
                .setPrice(10.0)
                .setOverdueDay(LocalDate.of(2019, 12, 11))
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void futureDayValidationTestNegativeTest() {
        Medicine medicine = new Medicine.Builder()
                .setId(1)
                .setName("Name")
                .setForm("form")
                .setPrice(10.0)
                .setOverdueDay(LocalDate.of(2019, 1, 11))
                .build();
    }
}
