package main.lab5.DAO;

import main.lab5.model.CountMedicine;
import main.lab5.model.Medicine;
import main.lab5.model.Person;
import main.lab5.model.Pharmacy;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDAO implements DAO<Pharmacy, Integer> {

    /**
     * SQL queries for pharmacy table.
     */
    enum PharmacySQL {
        GET("SELECT * FROM pharmacy  WHERE pharmacy.id = (?)"),
        INSERT("INSERT INTO pharmacy (id, name, pharmacist_id) VALUES ((?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM pharmacy WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE pharmacy SET name = (?) WHERE id = (?) RETURNING id"),

        GET_COUNT_MED_LIST("SELECT * FROM count_medicines WHERE pharmacy_id=(?)"),
        GET_OVERDUE_MEDICINES("SELECT medicines.* " +
                "FROM count_medicines JOIN medicines ON count_medicines.medicine_id=medicines.id "+
                "JOIN pharmacy ON count_medicines.pharmacy_id=pharmacy.id "+
                "WHERE pharmacy.id=(?) AND medicines.overdue_day<current_date"),
        DELETE_OVERDUE_MEDICINES_IN_PHARMACY("Delete From medicines "+
                "WHERE medicines IN(SELECT medicines " +
                "FROM count_medicines JOIN medicines ON count_medicines.medicine_id=medicines.id "+
                "JOIN pharmacy ON count_medicines.pharmacy_id=pharmacy.id "+
                "WHERE pharmacy.id=(?) AND medicines.overdue_day<current_date)");

        String QUERY;

        PharmacySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    /**
     * Connection to database
     */
    private final Connection connection;

    /**
     *  Init DB connection
     *
     * @param connection of DB
     */
    public PharmacyDAO(final Connection connection) {
        this.connection= connection;
    }

    /**
     * Create Pharmacy in DB
     *
     * @param pharmacy for create
     * @return false if item already exist, true if creating success
     * @throws SQLException
     */
    @Override
    public boolean create(Pharmacy pharmacy) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(PharmacySQL.INSERT.QUERY);
        statement.setInt(1, pharmacy.getId());
        statement.setString(2, pharmacy.getName());
        statement.setInt(3, pharmacy.getPharmacist().getId());
        return statement.executeQuery().next();
    }

    /**
     * Select Pharmacy by id
     *
     * @param id for select
     * @return return valid entity if she exist. If entity does not exist return empty object with id = -1.
     * @throws SQLException
     */
    @Override
    public Pharmacy read(Integer id) throws SQLException {
        Pharmacy result = new Pharmacy();
        result.setId(-1);
        PreparedStatement statement = connection.prepareStatement(PharmacySQL.GET.QUERY);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            result.setId(resultSet.getInt("id"));
            result.setPharmacist(new PersonDAO(connection).read(resultSet.getInt("pharmacist_id")));
            result.setCountMedicines(getListCountMedicine(result));
            result.setName(resultSet.getString("name"));
        }
        return result;
    }

    /**
     * Update Pharmacy by id
     *
     * @param pharmacy new state
     * @return true if success, false if fail
     * @throws SQLException
     */
    @Override
    public boolean update(Pharmacy pharmacy) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(PharmacySQL.UPDATE.QUERY);
        statement.setString(1, pharmacy.getName());
        statement.setInt(2, pharmacy.getId());

        return statement.executeQuery().next();
    }

    /**
     * Delete Pharmacy dy id
     *
     * @param pharmacy for delete
     * @return true if success, false if item not exist
     * @throws SQLException
     */
    @Override
    public boolean delete(Pharmacy pharmacy) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(PharmacySQL.DELETE.QUERY);
        statement.setInt(1, pharmacy.getId());

        return statement.executeQuery().next();
    }

    /**
     * Get list of Medicine and their count into Pharmacy
     *
     * @param pharmacy Pharmacy
     * @return list of CountMedicine
     * @throws SQLException
     */
    public List<CountMedicine> getListCountMedicine(Pharmacy pharmacy) throws SQLException {
        List<CountMedicine> list = new ArrayList<>();

        try( PreparedStatement statement = connection.prepareStatement(PharmacySQL.GET_COUNT_MED_LIST.QUERY)) {
            statement.setInt(1, pharmacy.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                CountMedicine countMedicine = new CountMedicineDAO(connection).resultSetToObj(resultSet);
                list.add(countMedicine);
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
        //list.forEach(System.out::println);
        return list;
    }

    /**
     * Get List of overdue medicines in pharmacy
     *
     * @param pharmacy Pharmacy
     * @return list of overdue Medicines
     * @throws SQLException
     */
    private List<Medicine> getListOfOverdueMedicines(Pharmacy pharmacy) throws SQLException {
        List<Medicine> list = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(PharmacySQL.GET_OVERDUE_MEDICINES.QUERY)) {
            statement.setInt(1, pharmacy.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medicine medicine = new MedicineDAO(connection).resultSetToObj(resultSet);
                list.add(medicine);
            }

            return list;
        }
    }

    /**
     * Delete all overdue Medicines in Pharmacy
     *
     * @param pharmacy Pharmacy
     * @throws SQLException
     */
    public void deleteOverdueMedicines(Pharmacy pharmacy) throws SQLException {
        List<Medicine> medicineList = getListOfOverdueMedicines(pharmacy);

        for (Medicine medicine: medicineList) {
            new MedicineDAO(connection).delete(medicine);
        }
    }

    /**
     * Delete all overdue Medicines in Pharmacy by SQL
     *
     * @param pharmacy Pharmacy
     * @throws SQLException
     */
    public boolean deleteOverdueMedicinesInPharmacy(Pharmacy pharmacy) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(PharmacySQL.DELETE_OVERDUE_MEDICINES_IN_PHARMACY.QUERY)) {
            statement.setInt(1,pharmacy.getId());
            return statement.execute();
        }
    }


    /**
     * Convert ResultSer into Pharmacy Object
     *
     * @param rs ResultSet to convert
     * @return Pharmacy object
     * @throws SQLException
     */
    public Pharmacy resultSetToObj(ResultSet rs) throws SQLException {
        Pharmacy pharmacy = new Pharmacy();

            pharmacy.setId(rs.getInt("id"));
            pharmacy.setName(rs.getString("name"));
            pharmacy.setCountMedicines(getListCountMedicine(pharmacy));
            pharmacy.setPharmacist(new PersonDAO(connection).read(rs.getInt("pharmacist_id")));

        return pharmacy;
    }

}
