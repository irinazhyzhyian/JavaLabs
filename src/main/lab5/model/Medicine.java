package main.lab5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import main.lab2.service.LocalDateDeserializer;
import main.lab2.service.LocalDateSerializer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonDeserialize(builder = Medicine.Builder.class)
public class Medicine implements Serializable {

    @NotNull(message = " field can`t be null")
    private Integer id;

    private String name;

    private String form;

    private Double price;

    @JsonFormat(pattern = "yyyyMMdd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate overdueDay;

    // can be created using Builder
    public Medicine() { }

    public void setName(String name) {
        this.name = name;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOverdueDay(LocalDate overdueDay) {
        this.overdueDay = overdueDay;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getForm() {
        return form;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getOverdueDay() {
        return overdueDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id.equals(medicine.id) &&
                name.equals(medicine.name) &&
                form.equals(medicine.form) &&
                price.equals(medicine.price) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, form);
    }


    /**
     * inner class builder which implements
     * pattern "Builder"
     */
    public static class Builder {
        Medicine medicine;

        public Builder() {
            medicine = new Medicine();
        }

        public Builder setId(Integer id) {
            medicine.id = id;
            return this;
        }

        public Builder setName(String name) {
            medicine.name = name;
            return this;
        }

        public Builder setForm(String form) {
            medicine.form = form;
            return this;
        }

        public Builder setPrice(Double price) {
            medicine.price = price;
            return this;
        }

        @JsonFormat(pattern = "yyyyMMdd")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        public Builder setOverdueDay(LocalDate date) {
            medicine.overdueDay = date;
            return this;
        }

        public Medicine build() throws IllegalStateException {

            try {
                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<Medicine>> constraintViolations = validator.validate(medicine);

                if (constraintViolations.isEmpty())
                    return medicine;
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
