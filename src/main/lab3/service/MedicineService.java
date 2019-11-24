package main.lab3.service;

import main.lab3.model.Medicine;
import java.time.LocalDate;

public class MedicineService {
    private Medicine medicine;

    public MedicineService(Medicine med) {
        this.medicine=med;
    }

    /**
     * @return true if medicine is overdue
     */
    public boolean checkOverdueDay() {
        return LocalDate.now().isAfter(medicine.getOverdueDay());
    }
}
