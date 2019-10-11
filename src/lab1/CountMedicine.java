package lab1;

import java.util.Objects;

public class CountMedicine {
    private Medicine medicine;
    private Integer count;

    public CountMedicine() {
    }

    public CountMedicine(Medicine medicine, Integer count) {
        this.medicine = medicine;
        if (count < 0)
            throw new RuntimeException("Wrong input!");
        this.count = count;

    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count < 0)
            throw new RuntimeException("Wrong input!");
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountMedicine that = (CountMedicine) o;
        return Objects.equals(medicine, that.medicine) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicine, count);
    }

    @Override
    public String toString() {
        return "CountMedicine{" +
                "medicine=" + medicine +
                ", count=" + count +
                '}';
    }
}


