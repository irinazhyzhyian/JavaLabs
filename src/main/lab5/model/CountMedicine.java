package main.lab5.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CountMedicine implements Comparable<CountMedicine> {

    @NotNull(message = " field can`t be null")
    private Integer id;

    private Medicine medicine;

    private Integer count;

    public CountMedicine() {
    }

    public CountMedicine(Medicine medicine, Integer count) {
        this.medicine = medicine;
        this.count = count;

    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        CountMedicine that = (CountMedicine) o;
        return Objects.equals(medicine, that.medicine) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicine, count);
    }

    @Override
    public String toString() {
        return "CountMedicine{" +
                "medicine=" + medicine +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(CountMedicine o) {
        if(this.count < o.count)
            return 1;
       else return -1;
    }

    public static class Builder {
        CountMedicine countMed;

        public Builder() {
            countMed = new CountMedicine();
        }

        public Builder setId(Integer id) {
            countMed.id = id;
            return this;
        }

        public Builder setMedicine(Medicine med) {
            countMed.medicine = med;
            return this;
        }

        public Builder setCount(Integer count) {
            countMed.count = count;
            return this;
        }

        public CountMedicine build() throws IllegalStateException {
            try {
                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<CountMedicine>> constraintViolations = validator.validate(countMed);

                if (constraintViolations.isEmpty())
                    return countMed;
                else {
                    Set<String> exceptions = new HashSet<>();
                    for (ConstraintViolation constraintViolation : constraintViolations) {
                        String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                        exceptions.add(fieldName + " " + constraintViolation.getMessage());
                    }
                    exceptions.forEach(System.out::println);
                    throw new IllegalStateException(exceptions.toString() + " ");
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException(e.getMessage());

            }
        }
    }

}


