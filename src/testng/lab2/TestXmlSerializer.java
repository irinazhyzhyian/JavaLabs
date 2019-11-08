package testng.lab2;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.lab2.model.Pharmacy;
import main.lab2.model.CountMedicine;
import main.lab2.model.Medicine;
import main.lab2.model.Person;
import main.lab2.service.XmlSerializer;
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

public class TestXmlSerializer {
    private XmlSerializer<Pharmacy> pharmacyXmlSerializer;
    private Pharmacy pharmacy;

    @BeforeTest
    public void beforeTest() {
        pharmacyXmlSerializer = new XmlSerializer<>(Pharmacy.class);
        List<CountMedicine> count_med = new ArrayList<>();
        List<main.lab2.model.Medicine> arrayMedicine = new ArrayList<>();
        arrayMedicine.add(new main.lab2.model.Medicine("Парацетамол", "таблетки", 80.00, LocalDate.of(2019, 12, 1)));
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
        String s = "<Pharmacy xmlns=\"\"><name>Pharm</name><countMedicines><countMedicines><medicine><name>Парацетамол</name><form>таблетки</form><price>80.0</price><overdueDay>2019-12-01</overdueDay></medicine><count>500</count></countMedicines><countMedicines><medicine><name>Називін</name><form>каплі назальні</form><price>55.0</price><overdueDay>2019-11-01</overdueDay></medicine><count>550</count></countMedicines></countMedicines><pharmacist><firstName>Name</firstName><lastName>Testing</lastName><birthday>2019-11-01</birthday><salary>5000.0</salary></pharmacist></Pharmacy>";
         Assert.assertEquals( s , pharmacyXmlSerializer.serializeToString(pharmacy));
    }

    @Test
    public void deserializeFromStringTest() throws IOException {
        String s = "<Pharmacy xmlns=\"\"><name>Pharm</name><countMedicines><countMedicines><medicine><name>Парацетамол</name><form>таблетки</form><price>80.0</price><overdueDay>2019-12-01</overdueDay></medicine><count>500</count></countMedicines><countMedicines><medicine><name>Називін</name><form>каплі назальні</form><price>55.0</price><overdueDay>2019-11-01</overdueDay></medicine><count>550</count></countMedicines></countMedicines><pharmacist><firstName>Name</firstName><lastName>Testing</lastName><birthday>2019-11-01</birthday><salary>5000.0</salary></pharmacist></Pharmacy>\n";
        Assert.assertEquals(pharmacy, pharmacyXmlSerializer.deserializeFromString(s));
    }

    @Test(dataProvider = "fileProvider")
    public void fromFileTest(File file) throws ConvertException {
        Assert.assertEquals(pharmacyXmlSerializer.fromFile(file), pharmacy);
    }

    @DataProvider
    public Object[][] fileProvider() throws ConvertException {
        File f = new File("result.json");
        pharmacyXmlSerializer.toFile(pharmacy, f);
        return new Object[][]{{f}};
    }
}
