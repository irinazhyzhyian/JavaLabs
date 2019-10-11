package testng;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import lab1.Medicine;
import lab1.Pharmacy;
import lab1.Person;
import lab1.CountMedicine;
import lab1.PharmacyService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestLab1 {
    public static final String LINESTRING = "there are more than twenty characters";
    List<Medicine> arrayMedicine = new ArrayList<>();
    List<CountMedicine> count_med = new ArrayList<>();
    Pharmacy pharmacy = new Pharmacy();

    @BeforeTest
    public void before() {
        arrayMedicine.add(new Medicine("Парацетамол", "таблетки", 80.00, LocalDate.of(2019, 12, 1)));
        arrayMedicine.add(new Medicine("Пектолван", "сироп", 45.50, LocalDate.of(2019, 12, 19)));
        arrayMedicine.add(new Medicine("Валер'янка", "каплі", 63.25, LocalDate.of(2020, 1, 2)));
        arrayMedicine.add(new Medicine("Називін", "каплі назальні", 55.00, LocalDate.now()));
        arrayMedicine.add(new Medicine("Зіпелор", "спрей", 150.00, LocalDate.of(2019, 6, 8)));
        for (int i = 0; i < arrayMedicine.size(); i++)
            count_med.add(new CountMedicine(arrayMedicine.get(i), (i + 10) * 50));


        Person person = new Person.Builder().build();
        pharmacy = Pharmacy.builder().name("Pharmacy #1")
                             .pharmacist(person)
                             .countMedicines(count_med)
                              .build();
    }

    @Test
    public void personBuilderTest() {
        Person person = new Person.Builder()
                .setFirstName("Vasya")
                .setLastName("Testing")
                .setBirthDay(LocalDate.now())
                .setSalary(0.0)
                .build();

        System.out.println(person);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void negativeBuilderFirstNameTest() {
        new Person.Builder().setFirstName(LINESTRING).build();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetFormTest(){
        new Medicine().setForm(LINESTRING);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetNameMedTest(){
        new Medicine().setName(LINESTRING);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetPriceMedTest(){
        new Medicine().setPrice(-120.50);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetCountTest(){
        new CountMedicine().setCount(-300);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetFirstNameTest(){
        Person person = new Person.Builder().build();
        person.setFirst_name(LINESTRING);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetLastNameTest(){
        Person person = new Person.Builder().build();
        person.setLastName(LINESTRING);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetSalaryNameTest(){
        Person person = new Person.Builder().build();
        person.setSalary(-100.00);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void negativeSetNamePharmacyTest(){
        new Pharmacy().setName(LINESTRING);
    }

    @Test(dataProvider = "listMedicineProvider")
    public void getListMedicineTest(PharmacyService actual, List<Medicine> expected) {
        assertEquals(actual.getListMedicine(), expected);
    }

    @DataProvider
    public Object[][] listMedicineProvider() {
        return new Object[][]{{PharmacyService.builder().pharmacy(pharmacy).build(), arrayMedicine}};
    }

    @Test(dataProvider = "ifMedicineExistProvider")
    public void ifMedicineExist(PharmacyService obj, Medicine medicine, boolean res) {
        assertEquals(obj.ifMedicineExist(medicine), res);
    }

    @DataProvider
    public Object[][] ifMedicineExistProvider() {
        Medicine falseMedicine = new Medicine("Лазолван", "таблетки", 45.50, LocalDate.of(2019, 12, 1));
        PharmacyService pharmacyService = PharmacyService.builder().pharmacy(pharmacy).build();
        return new Object[][]{{pharmacyService, arrayMedicine.get(2), true}, {pharmacyService, falseMedicine, false}};
    }

    @Test(dataProvider = "sellMedicineProvider")
    public void sellMedicineTest(PharmacyService obj, Medicine med, Integer count, boolean res){
        assertEquals(obj.sellMedicine(med, count), res);
    }

    @DataProvider
    public Object[][] sellMedicineProvider() {
        PharmacyService pharmacyService = PharmacyService.builder().pharmacy(pharmacy).build();
        return new Object[][]{{pharmacyService, count_med.get(0).getMedicine(), count_med.get(0).getCount(), true},
                {pharmacyService, count_med.get(0).getMedicine(), count_med.get(0).getCount()+2, false}};
    }

    @Test(dataProvider = "CheckOverdueDayProvider")
    public void checkOverdueDayTest(PharmacyService obj, Medicine med, boolean res) {
        assertEquals(obj.checkOverdueDay(med), res);
    }

    @DataProvider
    public Object[][] CheckOverdueDayProvider() {
        PharmacyService pharmacyService = PharmacyService.builder().pharmacy(pharmacy).build();
        return new Object[][]{{pharmacyService, arrayMedicine.get(1), true},
                {pharmacyService, count_med.get(3).getMedicine(), false}};
    }

    @Test(dataProvider = "sortedListProvider")
    public void getSortedByPricesTest(PharmacyService obj, List<Medicine> res) {
        assertEquals(obj.getSortedByPrice(), res);
    }

    @DataProvider
    public Object[][] sortedListProvider() {
        List<Medicine> sortedArray = new ArrayList<>();
        sortedArray.add(new Medicine("Пектолван", "сироп", 45.50, LocalDate.of(2019, 12, 19)));
        sortedArray.add(new Medicine("Називін", "каплі назальні", 55.00, LocalDate.now()));
        sortedArray.add(new Medicine("Валер'янка", "каплі", 63.25, LocalDate.of(2020, 1, 2)));
        sortedArray.add(new Medicine("Парацетамол", "таблетки", 80.00, LocalDate.of(2019, 12, 1)));
        sortedArray.add(new Medicine("Зіпелор", "спрей", 150.00, LocalDate.of(2019, 6, 8)));
        return new Object[][] {{PharmacyService.builder().pharmacy(pharmacy).build(), sortedArray}};
    }
}
