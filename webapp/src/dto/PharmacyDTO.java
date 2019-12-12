package dto;

import lab5.model.CountMedicine;
import lab5.model.Person;

import java.util.List;

public class PharmacyDTO {

    private Integer id;
    private String name;
    private List<CountMedicine> countMedicines;
    private Person pharmacist;

    public PharmacyDTO() {}

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

    public List<CountMedicine> getCountMedicines() {
        return countMedicines;
    }

    public void setCountMedicines(List<CountMedicine> countMedicines) {
        this.countMedicines = countMedicines;
    }

    public Person getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Person pharmacist) {
        this.pharmacist = pharmacist;
    }
}
