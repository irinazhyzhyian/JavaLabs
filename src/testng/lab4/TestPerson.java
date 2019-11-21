package testng.lab4;

import main.lab4.model.Person;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestPerson {

    @Test(expectedExceptions = IllegalStateException.class)
    public void personBuilderTest() {
        Person person = new Person.Builder()
                .setId(-1)
                .setBirthDay(LocalDate.now())
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setSalary(1000.0)
                .build();
    }

    @Test
    public void ageValidationTest() {
        Person person = new Person.Builder()
                .setId(1)
                .setBirthDay(LocalDate.of(2000, 1, 1))
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setSalary(1500.0)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void ageValidationNegativeTest() {
        Person person = new Person.Builder()
                .setId(1)
                .setBirthDay(LocalDate.of(2005, 1, 1))
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setSalary(1500.0)
                .build();
    }

    @Test
    public void minSalaryValidationTest() {
        Person person = new Person.Builder()
                .setId(1)
                .setBirthDay(LocalDate.of(2000, 1, 1))
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setSalary(2000.0)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void minSalaryValidationNegativeTest() {
        Person person = new Person.Builder()
                .setId(1)
                .setBirthDay(LocalDate.of(2000, 1, 1))
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setSalary(10.0)
                .build();
    }
}
