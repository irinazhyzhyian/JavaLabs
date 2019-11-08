package main.lab3.service;

import main.lab3.model.CountMedicine;
import main.lab3.model.Medicine;
import main.lab3.model.Pharmacy;
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
        pharmacy.getCountMedicines().forEach(e -> result.add(e.getMedicine()));
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
     * @return true if medicine isn`t overdue
     */
    private List<CountMedicine> getListOfOverdueMedicine() {
        return pharmacy.getCountMedicines()
                       .stream().filter(a-> new MedicineService(a.getMedicine()).checkOverdueDay())
                       .collect(Collectors.toList());
    }

    public boolean removeOverdueMedicine() {
       return pharmacy.getCountMedicines().removeAll(getListOfOverdueMedicine());
    }

    /**
     * @param med   is a Medicine
     * @param count count of medicines to be sold
     * @return true if the pharmacy has medicines and subtract count from current medicine count
     */
    public boolean sellMedicine(Medicine med, Integer count) {
        return pharmacy.getCountMedicines().stream()
                       .filter(a->a.getMedicine().equals(med))
                       .collect(Collectors.toList())
                       .stream()
                 .anyMatch(
                         a-> {
                             if( a.getCount()>=count&& !(new MedicineService(a.getMedicine()).checkOverdueDay())) {
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
