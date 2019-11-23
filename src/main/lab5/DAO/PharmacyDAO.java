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
        GET_PERSON("SELECT * FROM persons  WHERE persons.id = (?)");

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

    public List<CountMedicine> getListCountMedicine(Pharmacy pharmacy) throws SQLException {
        List<CountMedicine> list = new ArrayList<>();

        try( PreparedStatement statement = connection.prepareStatement(PharmacySQL.GET_COUNT_MED_LIST.QUERY)) {
            statement.setInt(1, pharmacy.getId());
            ResultSet resultSet = statement.executeQuery();

            do {
                CountMedicine countMedicine = new CountMedicineDAO(connection).resultSetToObj(resultSet);
                list.add(countMedicine);
            } while (!resultSet.isLast());
        }catch (Exception e) {
            e.getStackTrace();
        }
        list.forEach(System.out::println);
        return list;
    }

    @Override
    public Pharmacy resultSetToObj(ResultSet rs) throws SQLException {
        Pharmacy pharmacy = new Pharmacy();

        if(rs.next()) {
            pharmacy.setId(rs.getInt("id"));
            pharmacy.setName(rs.getString("name"));
            pharmacy.setCountMedicines(getListCountMedicine(pharmacy));
            pharmacy.setPharmacist(new PersonDAO(connection).read(rs.getInt("pharmacist_id")));
        }

        return pharmacy;
    }

}
