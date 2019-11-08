package testng.lab2;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.lab2.model.CountMedicine;
import main.lab2.model.Medicine;
import main.lab2.model.Person;
import main.lab2.model.Pharmacy;
import main.lab2.service.JsonSerializer;
import main.lab2.exception.ConvertException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestJsonSerializer {

    private JsonSerializer<Pharmacy> pharmacyJsonSerializer;
    private Pharmacy pharmacy;

    @BeforeTest
    public void beforeTest() {
        pharmacyJsonSerializer = new JsonSerializer<>(Pharmacy.class);
        List<CountMedicine> count_med = new ArrayList<>();
        List<main.lab2.model.Medicine> arrayMedicine = new ArrayList<>();
        arrayMedicine.add(new Medicine("Називін", "каплі назальні", 55.00, LocalDate.parse("2019-11-01")));
        for (int i = 0; i < arrayMedicine.size(); i++)
            count_med.add(new CountMedicine(arrayMedicine.get(i), (i + 10) * 50));
        Person person = new Person.Builder()
                .setFirstName("Name")
                .setLastName("Testing")
                .setBirthDay(LocalDate.parse("2019-11-01"))
                .setSalary( 5000.0)
                .build();
        pharmacy = new Pharmacy("Pharm", count_med, person);
    }

    @Test
    public void serializeToStringTest() throws JsonProcessingException {
        String expected = "{\"name\":\"Pharm\",\"countMedicines\":[{\"medicine\":{\"name\":\"Називін\",\"form\":\"каплі назальні\",\"price\":55.0,\"overdueDay\":\"2019-11-01\"},\"count\":500}],\"pharmacist\":{\"firstName\":\"Name\",\"lastName\":\"Testing\",\"birthday\":\"2019-11-01\",\"salary\":5000.0}}";
        String actual = pharmacyJsonSerializer.serializeToString(pharmacy);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeFromStringTest() throws IOException {
        String s = "{\"name\":\"Pharm\",\"countMedicines\":[{\"medicine\":{\"name\":\"Називін\",\"form\":\"каплі назальні\",\"price\":55.0,\"overdueDay\":\"2019-11-01\"},\"count\":500}],\"pharmacist\":{\"firstName\":\"Name\",\"lastName\":\"Testing\",\"birthday\":\"2019-11-01\",\"salary\":5000.0}}";
       Assert.assertEquals( pharmacyJsonSerializer.deserializeFromString(s), pharmacy);
    }

    @Test(dataProvider = "fileProvider")
    public void fromFileTest(File file) throws ConvertException {
        Assert.assertEquals(pharmacyJsonSerializer.fromFile(file), pharmacy);
    }

    @DataProvider
    public Object[][] fileProvider() throws ConvertException {
        File f = new File("result.json");
        pharmacyJsonSerializer.toFile(pharmacy, f);
        return new Object[][]{{f}};
    }

}
