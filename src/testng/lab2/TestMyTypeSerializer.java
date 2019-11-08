package testng.lab2;

import main.lab2.exception.ConvertException;
import main.lab2.model.Medicine;
import main.lab2.service.MyTypeSerializer;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestMyTypeSerializer {
    private Medicine medicine;

    @BeforeTest
    public void createMedicine() {
         medicine = new Medicine.Builder()
                .setName("Name")
                .setForm("form")
                .setPrice(200.0)
                .setOverdueDay(LocalDate.parse("2019-12-01"))
                .build();
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "Name---form---200.0---2019-12-01";
        String medstr = new MyTypeSerializer().serializeToString(medicine);
        Assert.assertEquals(expected, medstr);
    }

    @Test
    public void deserializeFromStringTest() throws ConvertException {
        String string = "Name---form---200.0---2019-12-01";
        Assert.assertEquals(medicine, new MyTypeSerializer().deserializeFromString(string));
    }
}
