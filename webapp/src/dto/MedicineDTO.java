package dto;

import java.time.LocalDate;

public class MedicineDTO {

    private Integer id;
    private String name;
    private String form;
    private Double price;
    private LocalDate overdueDay;

    public MedicineDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(LocalDate overdueDay) {
        this.overdueDay = overdueDay;
    }
}
