package main.lab1.service;

import main.lab1.model.CountMedicine;
import main.lab1.model.Medicine;
import main.lab1.model.Pharmacy;

import java.time.LocalDate;
import java.util.*;

public class PharmacyService {
    private Pharmacy pharmacy;

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    /**
     * @return list of all medicines in the pharmacy
     */
    public List<Medicine> getListMedicine() {
        List<Medicine> listMedicine = new ArrayList<>();
        for (CountMedicine countMedicine : pharmacy.getCountMedicines()) {
            listMedicine.add(countMedicine.getMedicine());
        }
        return listMedicine;
    }

    /**
     * @param med is a Medicine
     * @return true if med exist in the pharmacy
     */
    public boolean ifMedicineExist(Medicine med) {
        List<Medicine> listMedicine = this.getListMedicine();
        for (Medicine medicine : listMedicine) {
            if (medicine.equals(med))
                return true;
        }
        return false;

    }

    /**
     * @param med   is a Medicine
     * @param count count of medicines to be sold
     * @return true if the pharmacy has medicines and subtract count from current medicine count
     */
    public boolean sellMedicine(Medicine med, Integer count) {
        if (ifMedicineExist(med) && checkOverdueDay(med)) {
            for (CountMedicine countMed : pharmacy.getCountMedicines()) {
                if (countMed.getMedicine().equals(med) && countMed.getCount() >= count) {
                    countMed.setCount(countMed.getCount() - count);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param med is a Medicine
     * @return true if medicine isn`t overdue
     */
    public boolean checkOverdueDay(Medicine med) {
        List<Medicine> listMedicine = this.getListMedicine();
        for (Medicine medicine : listMedicine) {
            if (medicine.equals(med) && LocalDate.now().isBefore(medicine.getOverdueDay()))
                return true;
        }
        return false;
    }

    /**
     * @return list of sorted by Price medicines
     */
    public List<Medicine> getSortedByPrice() {

        List<Medicine> copyMedicine = this.getListMedicine();
        Collections.sort(copyMedicine);
        return copyMedicine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyService that = (PharmacyService) o;
        return Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacy);
    }

    @Override
    public String toString() {
        return "PharmacyService{" +
                "pharmacy=" + pharmacy +
                '}';
    }

    /**
     * inner class builder which implements
     * pattern "Builder"
     */
    public static class Builder {
        PharmacyService pharmacyService;

        public Builder() {
            pharmacyService = new PharmacyService();
        }

        public Builder setPharmacy(Pharmacy pharm) {
            pharmacyService.pharmacy = pharm;
            return this;
        }

        public PharmacyService build() {
            return pharmacyService;
        }
    }

    public static void main(String[] args) {

    }
}
