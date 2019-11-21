package main.lab3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import main.lab2.service.LocalDateDeserializer;
import main.lab2.service.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class Medicine implements Comparable<Medicine> {
    public static final Integer MAXNAMELENGTH = 20;
    private String name;
    private String form;
    private Double price;
    @JsonFormat(pattern = "yyyyMMdd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate overdueDay;

    // private to disallow creating Person from another classes
    // can be created using Builder
    public Medicine() { }

    public Medicine(String name, String form, Double price, LocalDate overdueDay) {
        this.name = name;
        this.form = form;
        this.price = price;
        this.overdueDay = overdueDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > MAXNAMELENGTH)
            throw new RuntimeException("Wrong input!");
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        if (form.length() > 30)
            throw new RuntimeException("Wrong input!");
        this.form = form;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price <= 0)
            throw new RuntimeException("Wrong input!");
        this.price = price;
    }

    public void setOverdueDay( LocalDate date) {
        if(date.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Wrong input!");
        this.overdueDay = date;
    }

    public LocalDate getOverdueDay() {
        return overdueDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(name, medicine.name) &&
                Objects.equals(form, medicine.form) &&
                Objects.equals(overdueDay, medicine.overdueDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, form, overdueDay);
    }

    @Override
    public int compareTo(Medicine o) {
        if (this.getPrice() < o.getPrice())
            return -1;
        if (this.getPrice().equals(o.getPrice()))
            return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", form='" + form + '\'' +
                ", price=" + price +
                ", overdueDay=" + overdueDay +
                '}';
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
        public Builder setName(String name) throws IllegalArgumentException {
            if (name.length() > MAXNAMELENGTH)
                throw new IllegalArgumentException("FirstName length must be less than " + MAXNAMELENGTH.toString());
            medicine.name = name;
            return this;
        }

        public Builder setForm(String form) {
            if (form.length() > 10)
                throw new RuntimeException("Wrong input!");
            medicine.form = form;
            return this;
        }

        public Builder setPrice(Double price) {
            medicine.price = price;
            return this;
        }

        public Builder setOverdueDay( LocalDate date) {
            if(date.isBefore(LocalDate.now()))
                throw new IllegalArgumentException("Wrong input!");
            medicine.overdueDay = date;
            return this;
        }

        public Medicine build() { return this.medicine;}
    }

}
