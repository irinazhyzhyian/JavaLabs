package main.lab5.DAO;

import main.lab5.model.CountMedicine;
import main.lab5.model.Person;
import main.lab5.model.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyDAO implements DAO<Pharmacy, Integer> {

    /**
     * SQL queries for pharmacy table.
     */
    enum PharmacySQL {
        GET("SELECT * FROM pharmacy  WHERE pharmacy.id = (?)"),
        INSERT("INSERT INTO pharmacy (id, name, count_medicine_id, pharmacist_id) VALUES ((?), (?), (?), (?))"),
        DELETE("DELETE FROM pharmacy WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE pharmacy SET name = (?) WHERE id = (?) RETURNING id");

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
        statement.setObject(3, pharmacy.getCountMedicines());
        statement.setObject(4, pharmacy.getPharmacist());
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
           // result.setCountMedicines(resultSet.getObject("medicine", CountMedicine.class));
            result.setPharmacist(resultSet.getObject("pharmacist", Person.class));
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
}
