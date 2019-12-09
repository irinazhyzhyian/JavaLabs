package lab5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lab2.service.LocalDateDeserializer;
import lab2.service.LocalDateSerializer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person implements Serializable {

    @NotNull(message = " field can`t be null")
    private Integer id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyyMMdd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    private Double salary;

    public Person() {
    }

    public void setFirstName(String firstName) {
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
        this.lastName = lastName;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    /**
     * inner class builder which implements
     * pattern "Builder"
     */
    public static class Builder {
        Person person;

        public Builder() {
            this.person = new Person();
        }

        public Builder setId(Integer id) {
            person.id=id;
            return this;
        }

        /**
         * Sets firstName for person
         * @param firstName String
         * @return instance of this builder
         */
        public Builder setFirstName(String firstName) {
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
                person.salary = salary;
            return this;
        }

        /**
         * Call it after setting all parameters
         * @return instance of class Person
         */
        public Person build() throws IllegalStateException {

            try {
                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

                if (constraintViolations.isEmpty())
                    return person;
                else {
                    Set<String> exceptions = new HashSet<>();
                    for (ConstraintViolation constraintViolation : constraintViolations) {
                        String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                        exceptions.add(fieldName + " " + constraintViolation.getMessage());
                    }
                   exceptions.forEach(System.out::println);
                    throw new IllegalStateException(exceptions.toString()+" ");
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException(e.getMessage());

            }

        }
    }
}
