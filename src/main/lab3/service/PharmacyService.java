package main.lab3.service;

import main.lab3.model.CountMedicine;
import main.lab3.model.Medicine;
import main.lab3.model.Pharmacy;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PharmacyService {

     private Pharmacy pharmacy;

    public PharmacyService() {

    }

    public PharmacyService(Pharmacy pharmacy) {
         this.pharmacy = pharmacy;
     }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    /**
     * @return list of all medicines in the pharmacy
     */
    public List<Medicine> getListMedicine() {
        List<Medicine> result = new ArrayList<>();

        pharmacy.getCountMedicines()
                .stream()
                .forEach(e -> result.add(e.getMedicine()));

        return result;
    }

    /**
     * @return list of sorted by Price medicines
     */
    public List<Medicine> getSortedByPrice() {
        List<Medicine> listMedicine = this.getListMedicine();
        return listMedicine.stream()
                           .sorted(Medicine::compareTo)
                           .collect(Collectors.toList());
    }

    /**
     * @return list of CountMedicine sorted by count of medicines (from higher to lower)
     */
    public List<CountMedicine> getSortedByCount() {
        List<CountMedicine> countMedicine = new LinkedList<>(pharmacy.getCountMedicines());
        countMedicine.sort(Comparator.comparingInt(CountMedicine::getCount).reversed());
        return countMedicine;
       /* return pharmacy.getCountMedicines()
                       .stream()
                       .sorted(CountMedicine::compareTo)
                       .collect(Collectors.toList());
        */
    }

    /**
     * Search Medicine by name
     * @param name name of medicine
     * @return list with Medicines only given name
     */
    public List<CountMedicine> findMedicinesByName(String name) {

       return pharmacy.getCountMedicines().stream()
                .filter(a->a.getMedicine().getName().equals(name))
                .collect(Collectors.toList());
    }

    /**
     * @param med is a Medicine
     * @return true if medicine isn`t overdue
     */
    public boolean checkOverdueDay(Medicine med) {
        List<Medicine> listMedicine = this.getListMedicine();
        return listMedicine.stream()
                            .anyMatch(a -> a.equals(med) && LocalDate.now().isBefore(a.getOverdueDay()));
    }

    /**
     * @param med   is a Medicine
     * @param count count of medicines to be sold
     * @return true if the pharmacy has medicines and subtract count from current medicine count
     */
    public boolean sellMedicine(Medicine med, Integer count) {
        return pharmacy.getCountMedicines().stream()
                .anyMatch(
                          a-> {
                              if(a.getMedicine().equals(med) && LocalDate.now().isBefore(a.getMedicine().getOverdueDay())&& a.getCount()>=count) {
                                  a.setCount(a.getCount() - count);
                                  return true;
                              }
                              else
                                  return false;
                          }
                );
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

}
