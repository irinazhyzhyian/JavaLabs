package testng.lab3;

import main.lab3.model.CountMedicine;
import main.lab3.model.Medicine;
import main.lab3.model.Person;
import main.lab3.model.Pharmacy;
import main.lab3.service.PharmacyService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestPharmacyService {

   private List<Medicine> arrayMedicine = new ArrayList<>();
   private List<CountMedicine> count_med = new ArrayList<>();
    private PharmacyService pharmacyService = new PharmacyService();

    @BeforeTest
    public void before() {
        arrayMedicine.add(new Medicine("Парацетамол", "таблетки", 80.00, LocalDate.of(2019, 12, 1)));
        arrayMedicine.add(new Medicine("Парацетамол", "каплі", 81.00, LocalDate.of(2019, 12, 1)));
        arrayMedicine.add(new Medicine("Називін", "каплі назальні", 55.00, LocalDate.of(2019, 1, 1)));
        for (int i = 0; i < arrayMedicine.size(); i++)
            count_med.add(new CountMedicine(arrayMedicine.get(i), (i + 10) * 50));


        Person person = new Person.Builder().build();
        Pharmacy pharmacy = new Pharmacy.Builder().setName("Pharmacy #1")
                .setPharmacist(person)
                .setCountMedicines(count_med)
                .build();
        pharmacyService.setPharmacy(pharmacy);
    }

    @Test
    public void getListMedicineTest() {
        assertEquals(pharmacyService.getListMedicine(), arrayMedicine);
    }

    @Test(dataProvider = "sellMedicineProvider")
    public void sellMedicineTest(PharmacyService obj, Medicine med, Integer count, boolean res){
        assertEquals(obj.sellMedicine(med, count), res);
    }

    @DataProvider
    public Object[][] sellMedicineProvider() {
        return new Object[][]{{pharmacyService, count_med.get(0).getMedicine(), count_med.get(0).getCount(), true},
                {pharmacyService, count_med.get(0).getMedicine(), count_med.get(0).getCount()+2, false}};
    }

    @Test(dataProvider = "sortedListProvider")
    public void getSortedByPricesTest(PharmacyService obj, List<Medicine> res) {
        assertEquals(obj.getSortedByPrice(), res);
    }

    @DataProvider
    public Object[][] sortedListProvider() {
        List<Medicine> sortedArray = new ArrayList<>();
        sortedArray.add(new Medicine("Називін", "каплі назальні", 55.00, LocalDate.of(2019, 1, 1)));
        sortedArray.add(new Medicine("Парацетамол", "таблетки", 80.00, LocalDate.of(2019, 12, 1)));
        sortedArray.add(new Medicine("Парацетамол", "каплі", 81.00, LocalDate.of(2019, 12, 1)));
        return new Object[][] {{pharmacyService, sortedArray}};
    }

    @Test(dataProvider = "getSortedByCountProvider")
    public void getSortedByCountTest(PharmacyService obj, List<CountMedicine> res) {
        assertEquals(obj.getSortedByCount(), res);
    }

    @DataProvider
    public Object[][] getSortedByCountProvider() {
        List<CountMedicine> sortedArray = new ArrayList<>();
        sortedArray.add(count_med.get(2));
        sortedArray.add(count_med.get(1));
        sortedArray.add(count_med.get(0));
        return new Object[][] {{pharmacyService, sortedArray}};
    }

    @Test(dataProvider = "findMedicineProvider")
    public void findMedicineByNameTest(List<CountMedicine> actual, List<CountMedicine> expected) {
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] findMedicineProvider() {
        List<CountMedicine> array = new ArrayList<>();
        array.add(count_med.get(0));
        array.add(count_med.get(1));
        return new Object[][] {{pharmacyService.findMedicinesByName("Парацетамол"), array},
                {pharmacyService.findMedicinesByName("NotExist"), new ArrayList<CountMedicine>()}};
    }

    public void removeOverdueMedicineTest() {
        assertTrue(pharmacyService.removeOverdueMedicine());
    }

}
