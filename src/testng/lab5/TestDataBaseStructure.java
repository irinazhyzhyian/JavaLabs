package testng.lab5;

import main.lab5.database.DataBaseStructure;
import org.testng.annotations.Test;

public class TestDataBaseStructure {


    @Test
    public void createPersonsTableTest() throws Exception {
        DataBaseStructure.createPersonsTable();
    }

    @Test
    public void createMedicinesTableTest() throws Exception {
        DataBaseStructure.createMedicinesTable();
    }

    @Test
    public void createPharmacyTableTest() throws Exception {
        DataBaseStructure.createPharmacyTable();
    }

    @Test
    public void createCountMedicinesTableTest() throws Exception {
        DataBaseStructure.createCountMedicineTable();
    }

    @Test
    public void dropPharmacyTableTest() throws Exception {
        DataBaseStructure.dropPharmacyTable();
    }

    @Test
    public void dropCountMedicinesTableTest() throws Exception {
        DataBaseStructure.dropCountMedicineTable();
    }

   @Test
    public void dropPersonsTableTest() throws Exception {
        DataBaseStructure.dropPersonsTable();
    }

    @Test
    public void dropMedicinesTableTest() throws Exception {
        DataBaseStructure.dropMedicinesTable();
    }

}
