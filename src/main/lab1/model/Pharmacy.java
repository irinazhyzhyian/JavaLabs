package main.lab1.model;

import java.util.*;

public class Pharmacy {
    public static final Integer MAX_NAME_LENGTH = 20;
    private String name;
    private List<CountMedicine> countMedicines = new ArrayList<>();
    private Person pharmacist;

    public Pharmacy() {
    }

    /**
     *
     * @param name Pharmacy name
     * @param countMedicines list of all medicines and their count
     * @param pharmacist person who works at this pharmacy
     */
    public Pharmacy(String name, List<CountMedicine> countMedicines, Person pharmacist) {
        this.name = name;
        this.countMedicines = countMedicines;
        this.pharmacist = pharmacist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > MAX_NAME_LENGTH)
            throw new RuntimeException("Wrong input!");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(name, pharmacy.name) &&
                Objects.equals(countMedicines, pharmacy.countMedicines) &&
                Objects.equals(pharmacist, pharmacy.pharmacist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countMedicines, pharmacist);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "name='" + name + '\'' +
                ", countMedicines=" + countMedicines +
                ", pharmacist=" + pharmacist +
                '}';
    }

    /**
     * inner class builder which implements
     * pattern "Builder"
     */
    public static class Builder {
        Pharmacy pharmacy;

        public Builder() {
            pharmacy = new Pharmacy();
        }

        public Builder setName(String name) throws IllegalArgumentException {
            if (name.length() > MAX_NAME_LENGTH)
                throw new IllegalArgumentException("FirstName length must be less than " + MAX_NAME_LENGTH.toString());
            pharmacy.name = name;
            return this;
        }

        public Builder setCountMedicines(List<CountMedicine> countMedicines) {
            pharmacy.countMedicines = countMedicines;
            return this;
        }

        public Builder setPharmacist(Person pharmacist) {
            pharmacy.pharmacist = pharmacist;
            return this;
        }

        public Pharmacy build() {
            return pharmacy;
        }
    }
}
