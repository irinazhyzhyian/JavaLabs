package lab1;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    public static final Integer MAXFIRSTNAMELENGTH = 20;
    public static final Double MINSALARY = 1000.00;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Double salary;

    // private to disallow creating Person from another classes
    // can be created using Builder
    private Person() {
    }

    public void setFirst_name(String firstName) {
        if (firstName.length() > MAXFIRSTNAMELENGTH)
            throw new RuntimeException("Wrong input!");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > MAXFIRSTNAMELENGTH)
            throw new RuntimeException("Wrong input!");
        this.lastName = lastName;
    }

    public void setSalary(Double salary) {
        if (salary < MINSALARY)
            throw new RuntimeException("Wrong input!");
        this.salary = salary;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }

    public static class Builder {
        Person person;

        public Builder() {
            this.person = new Person();
        }

        /**
         * Sets firstName for person
         * @param firstName String
         * @return instance of this builder
         * @throws IllegalArgumentException if length of firstName > MAXFIRSTNAMELENGTH
         */
        public Builder setFirstName(String firstName) throws IllegalArgumentException {
            if (firstName.length() > MAXFIRSTNAMELENGTH)
                throw new IllegalArgumentException("FirstName length must be less than " + MAXFIRSTNAMELENGTH.toString());
            person.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public Builder setBirthDay(LocalDate birthDay) {
            person.birthday = birthDay;
            return this;
        }

        /**
         * Sets salary for person
         * @param salary Double must be less than MAXSALARY
         * @return instance of this builder
         */
        public Builder setSalary(Double salary) {
            if (salary < MINSALARY)
                person.salary = MINSALARY;
            else
                person.salary = salary;
            return this;
        }

        /**
         * Call it after setting all parameters
         * @return instance of class Person
         */
        public Person build() {
            return person;
        }
    }
}
