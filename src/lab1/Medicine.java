package lab1;

import java.time.LocalDate;
import java.util.Objects;


public class Medicine implements Comparable<Medicine> {
    public static final Integer MAX_NAME_LENGTH = 20;
    private String name;
    private String form;
    private Double price;
    private LocalDate overdueDay;

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
        if (name.length() > MAX_NAME_LENGTH)
            throw new RuntimeException("Wrong input!");
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        if (form.length() > 10)
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
}
