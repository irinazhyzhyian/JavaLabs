package lab5.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.*;

public class Pharmacy {

    @NotNull(message = " field can`t be null")
    private Integer id;

    private String name;

    private List<CountMedicine> countMedicines;

    private Person pharmacist;

    public Pharmacy() {
        countMedicines = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        public Builder setId(Integer id) {
            pharmacy.id=id;
            return this;
        }

        public Builder setName(String name) throws IllegalArgumentException {
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

        public Pharmacy build() throws IllegalStateException {
            try {
                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<Pharmacy>> constraintViolations = validator.validate(pharmacy);

                if (constraintViolations.isEmpty())
                    return pharmacy;
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
